package it.unicam.cs.ids.c3.javafx;

import it.unicam.cs.ids.c3.controller.ControllerCliente;
import it.unicam.cs.ids.c3.model.Prodotto;
import it.unicam.cs.ids.c3.model.VotoRecensioni;
import it.unicam.cs.ids.c3.utilities.Controllore;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;

import java.net.URL;
import java.sql.SQLException;
import java.util.List;
import java.util.Objects;
import java.util.ResourceBundle;

public class CreaRecensione implements Initializable, JavaFXController {
    private static CreaRecensione instance;

    @FXML
    private Button pubblicaButton, annullaButton;

    @FXML
    private ChoiceBox<Prodotto> prodottoChoiceBox;
    @FXML
    private TextField titoloTextField;
    @FXML
    private TextArea textArea;
    @FXML
    private ImageView stella1, stella2, stella3, stella4, stella5, stellaBlu1, stellaBlu2, stellaBlu3, stellaBlu4, stellaBlu5;


    private CreaRecensione() {
    }

    public static CreaRecensione getInstance() {
        if (Objects.isNull(instance))
            instance = new CreaRecensione();
        return instance;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        try {
            riempiChoiceBox();
        } catch (SQLException e) {
            createErrorAlert(e.getMessage());
        }

        stella1.setOnMouseClicked(event -> {
            stellaBlu1.setVisible(true);
        });
        stella2.setOnMouseClicked(event -> {
            stellaBlu1.setVisible(true);
            stellaBlu2.setVisible(true);
        });
        stella3.setOnMouseClicked(event -> {
            stellaBlu1.setVisible(true);
            stellaBlu2.setVisible(true);
            stellaBlu3.setVisible(true);
        });
        stella4.setOnMouseClicked(event -> {
            stellaBlu1.setVisible(true);
            stellaBlu2.setVisible(true);
            stellaBlu3.setVisible(true);
            stellaBlu4.setVisible(true);
        });
        stella5.setOnMouseClicked(event -> {
            stellaBlu1.setVisible(true);
            stellaBlu2.setVisible(true);
            stellaBlu3.setVisible(true);
            stellaBlu4.setVisible(true);
            stellaBlu5.setVisible(true);
        });

        stellaBlu1.setOnMouseClicked(event -> {
            stellaBlu2.setVisible(false);
            stellaBlu3.setVisible(false);
            stellaBlu4.setVisible(false);
            stellaBlu5.setVisible(false);
        });
        stellaBlu2.setOnMouseClicked(event -> {
            stellaBlu3.setVisible(false);
            stellaBlu4.setVisible(false);
            stellaBlu5.setVisible(false);
        });
        stellaBlu3.setOnMouseClicked(event -> {
            stellaBlu4.setVisible(false);
            stellaBlu5.setVisible(false);
        });
        stellaBlu4.setOnMouseClicked(event -> {
            stellaBlu5.setVisible(false);
        });
        stellaBlu5.setOnMouseClicked(event -> {
        });
    }

    private void riempiChoiceBox() throws SQLException {
        if (Objects.isNull(prodottoChoiceBox.getValue())) {
            List<Prodotto> listaProdotti = ControllerCliente.getInstance().getListaProdottiAcquistati();
            prodottoChoiceBox.setItems(FXCollections.observableArrayList(listaProdotti));
        }
    }


    @FXML
    private void pubblica() {
        try {
            controllaCampi();
            ControllerCliente.getInstance().pubblicaRecensione(titoloTextField.getText(), textArea.getText(),
                    prodottoChoiceBox.getValue().getIDCommerciante(), prodottoChoiceBox.getValue().getID(),
                    getVoto());
            close(pubblicaButton);
            ICliente.getInstance().aggiornaListaRecensioni();
        } catch (IllegalArgumentException | SQLException e) {
            createErrorAlert(e.getMessage());
        }
    }

    private void controllaCampi() {
        Controllore.getInstance().controllaStringa(titoloTextField.getText(), "Inserisci un titolo per la recensione");
        Controllore.getInstance().controllaStringa(textArea.getText(), "Inserisci una testo per la recensione");
        Controllore.getInstance().controllaChoiceBox(prodottoChoiceBox, "Seleziona un prodotto da recensire");
        controllaStelle();
    }

    private void controllaStelle() {
        if (!stellaBlu1.isVisible())
            throw new IllegalArgumentException("Valuta il prodotto");
    }

    private VotoRecensioni getVoto() {
        if (stellaBlu5.isVisible()) return VotoRecensioni.CINQUE_STELLE;
        if (stellaBlu4.isVisible()) return VotoRecensioni.QUATTRO_STELLE;
        if (stellaBlu3.isVisible()) return VotoRecensioni.TRE_STELLE;
        if (stellaBlu2.isVisible()) return VotoRecensioni.DUE_STELLE;
        return VotoRecensioni.UNA_STELLA;
    }

    @FXML
    private void annulla() {
        close(annullaButton);
    }
}
