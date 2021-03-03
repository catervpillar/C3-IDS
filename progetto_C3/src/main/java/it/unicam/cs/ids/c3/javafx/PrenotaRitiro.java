package it.unicam.cs.ids.c3.javafx;

import it.unicam.cs.ids.c3.controller.GestoreRicerche;
import it.unicam.cs.ids.c3.model.Corriere;
import it.unicam.cs.ids.c3.model.PuntoRitiro;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;

import java.net.URL;
import java.util.List;
import java.util.Objects;
import java.util.ResourceBundle;
import java.util.stream.Collectors;

public class PrenotaRitiro implements Initializable, JavaFXController {
    private static PrenotaRitiro instance;

    @FXML
    private RadioButton consegnaDomicilioRadioButton, consegnaPuntoRadioButton;
    @FXML
    private Button prenotaButton, annullaButton;
    @FXML
    private ChoiceBox<PuntoRitiro> puntoRitiroChoiceBox = new ChoiceBox<>();
    @FXML
    private ChoiceBox<Corriere> corriereChoiceBox = new ChoiceBox<>();
    @FXML
    private DatePicker datePicker;
    @FXML
    private TextField destinazioneTextField, IDClienteTextField;


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

    }

    @FXML
    private void annulla() {
        close(annullaButton);
    }

    @FXML
    private void riempiDestinazione() {
        if (!Objects.isNull(puntoRitiroChoiceBox.getValue())) {
            destinazioneTextField.setText(puntoRitiroChoiceBox.getValue().getIndirizzo());
            destinazioneTextField.setDisable(true);
        }
    }

}
