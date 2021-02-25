package it.unicam.cs.ids.c3.javafx;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
import javafx.scene.image.ImageView;

import java.io.IOException;
import java.util.Objects;

public class AccountTypePicker implements JavaFXController {
    private static AccountTypePicker instance;

    private AccountTypePicker() {
    }

    public static AccountTypePicker getInstance() {
        if (Objects.isNull(instance))
            instance = new AccountTypePicker();
        return instance;
    }

    @FXML
    RadioButton clienteRadioButton;
    @FXML
    RadioButton commercianteRadioButton;
    @FXML
    RadioButton puntoRitiroRadioButton;
    @FXML
    RadioButton corriereRadioButton;

    @FXML
    ImageView clienteImageView;
    @FXML
    ImageView commercianteImageView;
    @FXML
    ImageView puntoRitiroImageView;
    @FXML
    ImageView corriereImageView;

    @FXML
    Button procediButton;
    @FXML
    Button annullaButton;

    @FXML
    public void procedi() throws IOException {
        if (clienteRadioButton.isSelected()) {
            startWindow("Crea il tuo profilo cliente",
                    "/clienteCreator.fxml",
                    ClienteCreator.getInstance());
            close(clienteRadioButton);
        } else if (commercianteRadioButton.isSelected()) {
            startWindow("Crea il tuo profilo Commerciante",
                    "/commercianteCreator.fxml",
                    CommercianteCreator.getInstance());
            close(commercianteRadioButton);
        } else if (puntoRitiroRadioButton.isSelected()) {
            startWindow("Crea il tuo profilo Punto di Ritiro",
                    "/corriereCreator.fxml",
                    new PuntoRitiroCreator());
            close(puntoRitiroRadioButton);
        } else if (corriereRadioButton.isSelected()) {
            startWindow("Crea il tuo profilo Corriere",
                    "/puntoRitiroCreator.fxml",
                    CorriereCreator.getInstance());
            close(corriereRadioButton);
        }
    }

    @FXML
    public void annulla() {
        close(annullaButton);
    }

    @FXML
    public void selezionaCliente() {
        clienteRadioButton.setSelected(true);
        commercianteRadioButton.setSelected(false);
        puntoRitiroRadioButton.setSelected(false);
        corriereRadioButton.setSelected(false);
        procediButton.setDisable(false);
    }

    @FXML
    public void selezionaCommerciante() {
        commercianteRadioButton.setSelected(true);
        clienteRadioButton.setSelected(false);
        puntoRitiroRadioButton.setSelected(false);
        corriereRadioButton.setSelected(false);
        procediButton.setDisable(false);
    }

    @FXML
    public void selezionaPuntoRitiro() {
        puntoRitiroRadioButton.setSelected(true);
        clienteRadioButton.setSelected(false);
        commercianteRadioButton.setSelected(false);
        corriereRadioButton.setSelected(false);
        procediButton.setDisable(false);
    }

    @FXML
    public void selezionaCorriere() {
        corriereRadioButton.setSelected(true);
        clienteRadioButton.setSelected(false);
        commercianteRadioButton.setSelected(false);
        puntoRitiroRadioButton.setSelected(false);
        procediButton.setDisable(false);
    }
}
