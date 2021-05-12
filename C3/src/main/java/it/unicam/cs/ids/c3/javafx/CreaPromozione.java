package it.unicam.cs.ids.c3.javafx;

import it.unicam.cs.ids.c3.prodotto.ProdottoInterface;
import it.unicam.cs.ids.c3.utenti.commerciante.ControllerCommerciante;
import it.unicam.cs.ids.c3.utilities.Controllore;
import it.unicam.cs.ids.c3.view.ICommerciante;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;

import java.net.URL;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;

public class CreaPromozione implements Initializable, JavaFXController {
    private static CreaPromozione instance;

    @FXML
    private TextField nomeTextField;
    @FXML
    private TextArea descrizioneTextArea;
    @FXML
    private DatePicker dataInizioDatePicker, dataScadenzaDatePicker;

    @FXML
    private Button creaButton, annullaButton;

    @FXML
    private TableView<ProdottoInterface> prodottiTableView;
    @FXML
    private TableColumn<ProdottoInterface, CheckBox> colonnaCheckBox;
    @FXML
    private TableColumn<ProdottoInterface, String> colonnaID;
    @FXML
    private TableColumn<ProdottoInterface, String> colonnaNome;
    @FXML
    private TableColumn<ProdottoInterface, String> colonnaPrezzo;
    @FXML
    private TableColumn<ProdottoInterface, String> colonnaQuantita;

    private CreaPromozione() {
    }

    public static CreaPromozione getInstance() {
        if (Objects.isNull(instance))
            instance = new CreaPromozione();
        return instance;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        try {
            aggiornaTabellaProdotti();
        } catch (SQLException e) {
            createErrorAlert(e.getMessage());
        }

        dataInizioDatePicker.setDayCellFactory(picker -> new DateCell() {
            public void updateItem(LocalDate date, boolean empty) {
                super.updateItem(date, empty);
                LocalDate today = LocalDate.now();

                setDisable(empty || date.compareTo(today) < 0 );
            }
        });

        dataScadenzaDatePicker.setDayCellFactory(picker -> new DateCell() {
            public void updateItem(LocalDate date, boolean empty) {
                super.updateItem(date, empty);
                LocalDate today = LocalDate.now();

                setDisable(empty || date.compareTo(today) < 0 );
            }
        });

        prodottiTableView.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
    }

    public void aggiornaTabellaProdotti() throws SQLException {
        riempiTabellaProdotti();
        prodottiTableView.getItems().clear();
        prodottiTableView.setItems(FXCollections.observableArrayList(ControllerCommerciante.getInstance().getProdotti()));
    }

    private void riempiTabellaProdotti() {
        colonnaCheckBox.setCellValueFactory(p -> new SimpleObjectProperty<>(new CheckBox()));
        colonnaID.setCellValueFactory(p -> new SimpleStringProperty(p.getValue().getID()));
        colonnaNome.setCellValueFactory(p -> new SimpleStringProperty(p.getValue().getNome()));
        colonnaPrezzo.setCellValueFactory(p -> new SimpleStringProperty("\u20AC " + String.valueOf((p.getValue().getPrezzo()))));
        colonnaQuantita.setCellValueFactory(p -> new SimpleStringProperty(String.valueOf((p.getValue().getQuantita()))));
    }

    private List<String> getListaIDProdotti() {
        List<String> listaIDProdotti = new ArrayList<>();

        AtomicInteger i = new AtomicInteger();
        prodottiTableView.getItems().forEach(row -> {
            if (prodottiTableView.getSelectionModel().isSelected(i.getAndIncrement()))
                listaIDProdotti.add(row.getID());
        });
        return listaIDProdotti;
    }

    @FXML
    private void crea() {
        try {
            controllaCampi();
            GregorianCalendar dataInizio = new GregorianCalendar();
            dataInizio.setTime(java.sql.Date.valueOf(dataInizioDatePicker.getValue()));
            GregorianCalendar dataScadenza = new GregorianCalendar();
            dataScadenza.setTime(java.sql.Date.valueOf(dataScadenzaDatePicker.getValue()));
            ControllerCommerciante.getInstance().creaPromozione(nomeTextField.getText(), descrizioneTextArea.getText(),
                    dataInizio, dataScadenza, getListaIDProdotti());
            ICommerciante.getInstance().aggiornaListaPromozioni();
            close(creaButton);
        } catch (IllegalArgumentException | SQLException e) {
            createErrorAlert(e.getMessage());
        }
    }

    private void controllaCampi() {
        Controllore.getInstance().controllaStringa(nomeTextField.getText(),"Inserisci un nome per la promozione");
        Controllore.getInstance().controllaStringa(descrizioneTextArea.getText(),"Inserisci una descrizione per la promozione");
        Controllore.getInstance().controllaDatePicker(dataInizioDatePicker,"Selezionare una data d'inizio della promozione");
        Controllore.getInstance().controllaDatePicker(dataScadenzaDatePicker,"Selezionare una data di scadenza della promozione");
        if (dataInizioDatePicker.getValue().compareTo(dataScadenzaDatePicker.getValue()) > 0)
            throw new IllegalArgumentException("La data di scadenza non puo' essere precedente alla data di inizio");
    }

    @FXML
    private void annulla() {
        close(annullaButton);
    }
}
