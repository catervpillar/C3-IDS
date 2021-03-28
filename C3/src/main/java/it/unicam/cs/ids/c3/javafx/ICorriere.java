package it.unicam.cs.ids.c3.javafx;

import it.unicam.cs.ids.c3.model.Prodotto;
import javafx.fxml.FXML;
import javafx.scene.control.*;

import java.util.Objects;

public class ICorriere implements JavaFXController {
    private static ICorriere instance;

    private ICorriere() {
    }

    public static ICorriere getInstance() {
        if (Objects.isNull(instance))
            instance = new ICorriere();
        return instance;
    }

    @FXML
    private Label IDutenteLabel;

    @FXML
    private Button prenotaRitiroButton;
    @FXML
    private Button vendiProdottoButton;
    @FXML
    private Button creaPromozioneButton;
    @FXML
    private Button salvaModificheButton;
    @FXML
    private Button eliminaAccountButton;
    @FXML
    private Button logoutButton;
    @FXML
    private RadioButton disponibileRadioButton;
    @FXML
    private RadioButton nonDisponibileRadioButton;

    @FXML
    private TextField usernameTextField;
    @FXML
    private TextField passwordTextField;
    @FXML
    private TextField emailTextField;
    @FXML
    private TextField ragioneSocialeTextField;
    @FXML
    private TextField telefonoTextField;
    @FXML
    private TextField indirizzoTextField;

    @FXML
    private TableView<Prodotto> tabellaProdotti;
    private TableColumn<Prodotto, String> IDtabellaProdotti;
    private TableColumn<Prodotto, String> nomeTabellaProdotti;
    private TableColumn<Prodotto, Double> prezzoTabellaProdotti;
    private TableColumn<Prodotto, Integer> quantitaTabellaProdotti;
    private TableColumn<Prodotto, String> statoTabellaProdotti;

    @FXML
    private void aggiornaRitiro() {
        createConfirmationAlert("Rinunciare agli studi?");
    }

    @FXML
    private void selezionaDisponibile() {
        disponibileRadioButton.setSelected(true);
        nonDisponibileRadioButton.setSelected(false);
    }

    @FXML
    private void selezionaNonDisponibile() {
        disponibileRadioButton.setSelected(false);
        nonDisponibileRadioButton.setSelected(true);
    }


}
