package it.unicam.cs.ids.c3.javafx;

import it.unicam.cs.ids.c3.controller.ControllerCommerciante;
import it.unicam.cs.ids.c3.controller.GestoreRicerche;
import it.unicam.cs.ids.c3.model.Corriere;
import it.unicam.cs.ids.c3.model.Prodotto;
import it.unicam.cs.ids.c3.model.PuntoRitiro;
import it.unicam.cs.ids.c3.model.TipoConsegna;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;

import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.ResourceBundle;
import java.util.concurrent.atomic.AtomicInteger;

public class PrenotaRitiro implements Initializable, JavaFXController {
    private static PrenotaRitiro instance;

    @FXML
    private RadioButton consegnaDomicilioRadioButton, consegnaPuntoRadioButton;
    @FXML
    private Button prenotaButton, annullaButton;
    @FXML
    private ChoiceBox<PuntoRitiro> puntoRitiroChoiceBox;
    @FXML
    private ChoiceBox<Corriere> corriereChoiceBox;
    @FXML
    private TextField destinazioneTextField, IDClienteTextField;

    @FXML
    private TableView<Prodotto> prodottiTableView;
    @FXML
    private TableColumn<Prodotto, CheckBox> colonnaCheckBox;
    @FXML
    private TableColumn<Prodotto, String> colonnaID;
    @FXML
    private TableColumn<Prodotto, String> colonnaNome;
    @FXML
    private TableColumn<Prodotto, String> colonnaPrezzo;
    @FXML
    private TableColumn<Prodotto, String> colonnaQuantita;


    private PrenotaRitiro() {
    }

    public static PrenotaRitiro getInstance() {
        if (Objects.isNull(instance))
            instance = new PrenotaRitiro();
        return instance;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        puntoRitiroChoiceBox.setVisible(false);
        riempiCorriereChoiceBox();
        riempiPuntoRitiroChoiceBox();

        puntoRitiroChoiceBox.setOnAction(event -> {
            if (!Objects.isNull(puntoRitiroChoiceBox.getValue())) {
                destinazioneTextField.setPromptText(puntoRitiroChoiceBox.getValue().getIndirizzo());
                destinazioneTextField.setEditable(false);
            }
        });
        try {
            aggiornaTabellaProdotti();
        } catch (SQLException e) {
            createErrorAlert(e.getMessage());
        }
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

    private void riempiCorriereChoiceBox() {
        if (Objects.isNull(corriereChoiceBox.getValue())) {
            List<Corriere> listaCorriere = GestoreRicerche.getInstance().cercaCorriere(null);
            corriereChoiceBox.setItems(FXCollections.observableArrayList(listaCorriere));
        }
    }

    @FXML
    private void riempiPuntoRitiroChoiceBox() {
        if (Objects.isNull(puntoRitiroChoiceBox.getValue())) {
            List<PuntoRitiro> listaPuntiRitiro = GestoreRicerche.getInstance().cercaPuntoRitiro(null);
            puntoRitiroChoiceBox.setItems(FXCollections.observableArrayList(listaPuntiRitiro));
        }
    }

    @FXML
    private void selezionaDomicilio() {
        puntoRitiroChoiceBox.setVisible(false);
        consegnaDomicilioRadioButton.setSelected(true);
        consegnaPuntoRadioButton.setSelected(false);
        destinazioneTextField.setEditable(true);
        if (!Objects.isNull(destinazioneTextField.getPromptText())) {
            destinazioneTextField.setPromptText(null);
        }

    }

    @FXML
    private void selezionaPressoPunto() {
        puntoRitiroChoiceBox.setValue(null);
        destinazioneTextField.clear();
        consegnaDomicilioRadioButton.setSelected(false);
        consegnaPuntoRadioButton.setSelected(true);
        puntoRitiroChoiceBox.setVisible(true);
    }

    @FXML
    private void prenota() {
        try {
            controllaCampi();
            ControllerCommerciante.getInstance().prenotaRitiro(IDClienteTextField.getText(),
                    corriereChoiceBox.getValue().getID(),
                    getDestinazione(), getTipoConsegna(), getListaIDProdotti());
            ICommerciante.getInstance().aggiornaListaRitiri();
            close(prenotaButton);

        } catch (IllegalArgumentException | SQLException e) {
            createErrorAlert(e.getMessage());
        }
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

    private String getDestinazione() {
        if (consegnaDomicilioRadioButton.isSelected())
            return destinazioneTextField.getText();
        else return destinazioneTextField.getPromptText();
    }

    private TipoConsegna getTipoConsegna() {
        if (consegnaDomicilioRadioButton.isSelected())
            return TipoConsegna.CONSEGNA_A_DOMICILIO;
        else return TipoConsegna.CONSEGNA_PRESSO_PUNTO;
    }

    @FXML
    private void annulla() {
        close(annullaButton);
    }

    private void controllaCampi() {
        if (!consegnaDomicilioRadioButton.isSelected() && !consegnaPuntoRadioButton.isSelected())
            throw new IllegalArgumentException("Seleziona un tipo di consegna");
        if (destinazioneTextField.getText().isBlank() && consegnaDomicilioRadioButton.isSelected())
            throw new IllegalArgumentException("Inserisci una destinazione");
        if (Objects.isNull(puntoRitiroChoiceBox.getValue()) && consegnaPuntoRadioButton.isSelected())
            throw new IllegalArgumentException("Seleziona un punto di ritiro");
        if (IDClienteTextField.getText().isBlank())
            throw new IllegalArgumentException("Inserisci l'ID del cliente che ha acquistato la merce");
        if (Objects.isNull(corriereChoiceBox.getValue()))
            throw new IllegalArgumentException("Seleziona un corriere a cui affidare il ritiro");
    }
}
