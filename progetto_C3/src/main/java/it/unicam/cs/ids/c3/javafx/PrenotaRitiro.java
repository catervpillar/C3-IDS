package it.unicam.cs.ids.c3.javafx;

import it.unicam.cs.ids.c3.controller.GestoreRicerche;
import it.unicam.cs.ids.c3.model.Corriere;
import it.unicam.cs.ids.c3.model.PuntoRitiro;
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
    private ChoiceBox<String> puntoRitiroChoiceBox = new ChoiceBox<>();
    @FXML
    private ChoiceBox<String> corriereChoiceBox = new ChoiceBox<>();
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
        riempiCorriereChoiceBox();
    }

    private void riempiCorriereChoiceBox() {
        if (Objects.isNull(corriereChoiceBox.getValue())) {
            List<Corriere> listaCorriere = GestoreRicerche.getInstance().cercaCorriere(null);

            List<String> listaNomi = listaCorriere.stream().map(Corriere::getRagioneSociale).collect(Collectors.toList());

            corriereChoiceBox.setItems(FXCollections.observableArrayList(listaNomi));
        }
    }

    private void riempiPuntoRitiroChoiceBox() {

    }

    @FXML
    private void selezionaDomicilio() {

    }

    @FXML
    private void selezionaPressoPunto() {

    }

    @FXML
    private void prenota() {

    }

    @FXML
    private void annulla() {
        close(annullaButton);
    }
}
