package it.unicam.cs.ids.c3.javafx;

import it.unicam.cs.ids.c3.utenti.commerciante.ControllerCommerciante;
import it.unicam.cs.ids.c3.utilities.GestoreRicerche;
import it.unicam.cs.ids.c3.prodotto.Prodotto;
import it.unicam.cs.ids.c3.database.SerializerModifica;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Accordion;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;

import java.net.URL;
import java.sql.SQLException;
import java.util.Objects;
import java.util.ResourceBundle;

public class ModificaProdotto implements Initializable, JavaFXController {
    private static ModificaProdotto instance;
    private Prodotto prodottoDaModificare;

    @FXML
    private TextField nomeTextField, prezzoTextField, disponibilitaTextField, URLimmagineTextField;
    @FXML
    private Button annullaButton, salvaButton;

    private ModificaProdotto() {
    }

    public static ModificaProdotto getInstance() {
        if ((Objects.isNull(instance)))
            instance = new ModificaProdotto();
        return instance;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        setDati();
    }

    private void setDati() {
        Accordion accordion = ICommerciante.getInstance().getProdottiAccordion();
        AnchorPane anchorPane = (AnchorPane) accordion.getExpandedPane().getContent();
        Label ID = (Label) anchorPane.getChildren().get(2);
        try {
            this.prodottoDaModificare = getProdotto(ID.getText());
        } catch (IllegalArgumentException e) {
            createErrorAlert(e.getMessage());
        }

        nomeTextField.setText(this.prodottoDaModificare.getNome());
        prezzoTextField.setText(String.valueOf(this.prodottoDaModificare.getPrezzo()));
        disponibilitaTextField.setText(String.valueOf(this.prodottoDaModificare.getQuantita()));
        URLimmagineTextField.setText(this.prodottoDaModificare.getURLImmagine());
    }

    private Prodotto getProdotto(String ID) {
        for (Prodotto p : GestoreRicerche.getInstance().getProdotti()) {
            if (p.getID().equals(ID)) {
                return p;
            }
        }
        throw new IllegalArgumentException("Non ho trovato il prodotto");
    }

    @FXML
    private void salvaModifiche() throws SQLException {
        prodottoDaModificare.setNome(nomeTextField.getText());
        prodottoDaModificare.setPrezzo(Double.parseDouble(prezzoTextField.getText()));
        prodottoDaModificare.setQuantita(Integer.parseInt(disponibilitaTextField.getText()));
        prodottoDaModificare.setURLImmagine(URLimmagineTextField.getText());
        ControllerCommerciante.getInstance().modificaProdotto(prodottoDaModificare);
        ICommerciante.getInstance().aggiornaListaProdotti();
        this.prodottoDaModificare = null;
        close(salvaButton);
    }

    @FXML
    private void annulla() {
        close(annullaButton);
    }
}
