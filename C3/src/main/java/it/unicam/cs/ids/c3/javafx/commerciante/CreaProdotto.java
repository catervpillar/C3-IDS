package it.unicam.cs.ids.c3.javafx.commerciante;

import it.unicam.cs.ids.c3.javafx.JavaFXController;
import it.unicam.cs.ids.c3.utenti.commerciante.ControllerCommerciante;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

import java.net.URL;
import java.sql.SQLException;
import java.util.Objects;
import java.util.ResourceBundle;

public class CreaProdotto implements Initializable, JavaFXController {
    private static CreaProdotto instance;

    @FXML
    private TextField nomeTextField, prezzoTextField, disponibilitaTextField, URLimmagineTextField;
    @FXML
    private Button annullaButton, creaButton;

    private CreaProdotto() {
    }

    public static CreaProdotto getInstance() {
        if (Objects.isNull(instance))
            instance = new CreaProdotto();
        return instance;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    @FXML
    private void crea() {
        try {
            controllaCampi();
            ControllerCommerciante.getInstance().creaProdotto(nomeTextField.getText(),
                    Double.parseDouble(prezzoTextField.getText()),
                    Integer.parseInt(disponibilitaTextField.getText()),
                    URLimmagineTextField.getText());
            ICommerciante.getInstance().aggiornaListaProdotti();
            close(creaButton);
        } catch (SQLException | IllegalArgumentException e) {
            createErrorAlert(e.getMessage());
        }
    }

    private void controllaCampi() {
        if (Objects.isNull(nomeTextField.getText()) || nomeTextField.getText().isBlank())
            throw new IllegalArgumentException("Occorre completare il campo \"Nome\" per procedere");
        if (Objects.isNull(prezzoTextField.getText()) || prezzoTextField.getText().isBlank())
            throw new IllegalArgumentException("Occorre completare il campo \"Prezzo\" per procedere");
        if (Objects.isNull(disponibilitaTextField.getText()) || disponibilitaTextField.getText().isBlank())
            throw new IllegalArgumentException("Occorre completare il campo \"Disponibilita'\" per procedere");
    }

    @FXML
    private void annulla() {
        close(annullaButton);
    }
}
