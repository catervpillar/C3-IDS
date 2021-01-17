package it.unicam.cs.ids.c3.javafx;

import it.unicam.cs.ids.c3.controller.ControllerCliente;
import it.unicam.cs.ids.c3.controller.ControllerCommerciante;
import it.unicam.cs.ids.c3.controller.ControllerCorriere;
import it.unicam.cs.ids.c3.controller.ControllerPuntoRitiro;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
import javafx.scene.image.ImageView;

import java.io.IOException;

public class AccountTypePicker implements JavaFXController {

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
                    new ClienteCreator(ControllerCliente.getInstance()));
            close(clienteRadioButton);
        } else if (commercianteRadioButton.isSelected()) {
            startWindow("Crea il tuo profilo commerciante",
                    "/commercianteCreator.fxml",
                    new CommercianteCreator(ControllerCommerciante.getInstance()));
            close(commercianteRadioButton);
        } else if (puntoRitiroRadioButton.isSelected()) {
            startWindow("Crea il tuo profilo punto di ritiro",
                    "/puntoRitiroCreator.fxml",
                    new PuntoRitiroCreator(ControllerPuntoRitiro.getInstance()));
            close(puntoRitiroRadioButton);
        } else if (corriereRadioButton.isSelected()) {
            startWindow("Crea il tuo profilo corriere",
                    "/corriereCreator.fxml",
                    new CorriereCreator(ControllerCorriere.getInstance()));
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
