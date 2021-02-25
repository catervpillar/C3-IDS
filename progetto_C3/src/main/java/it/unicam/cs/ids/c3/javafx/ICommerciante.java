package it.unicam.cs.ids.c3.javafx;

import it.unicam.cs.ids.c3.controller.ControllerCommerciante;
import it.unicam.cs.ids.c3.model.Prodotto;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Objects;

public class ICommerciante implements JavaFXController {
    private static ICommerciante instance;

    private ICommerciante() {
    }

    public static ICommerciante getInstance() {
        if (Objects.isNull(instance))
            instance = new ICommerciante();
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
    private final TableView<Prodotto> tabellaProdotti = new TableView<Prodotto>();
    private TableColumn<Prodotto, String> IDtabellaProdotti = new TableColumn<Prodotto, String>();
    private TableColumn<Prodotto, String> nomeTabellaProdotti = new TableColumn<Prodotto, String>();
    private TableColumn<Prodotto, Double> prezzoTabellaProdotti = new TableColumn<Prodotto, Double>();
    private TableColumn<Prodotto, Integer> quantitaTabellaProdotti = new TableColumn<Prodotto, Integer>();
    private TableColumn<Prodotto, String> statoTabellaProdotti = new TableColumn<Prodotto, String>();


    @FXML
    public void prenotaRitiro() {

    }

    @FXML
    public void vendiProdotto() {

    }

    @FXML
    public void creaPromozione() {

    }

    @FXML
    public void salvaModifiche() throws SQLException {
        ControllerCommerciante.getInstance().modificaCommerciante(usernameTextField.getText(),
                passwordTextField.getText(), emailTextField.getText(), ragioneSocialeTextField.getText(),
                telefonoTextField.getText(), indirizzoTextField.getText());
        setDatiAccount();
    }

    @FXML
    public void eliminaAccount() throws SQLException, IOException {
        ControllerCommerciante.getInstance().eliminaAccount();
        close(logoutButton);
        startWindow("C3 v1.0", "/loginC3_2.fxml", LoginC3Controller.getInstance());
    }

    @FXML
    public void logout() throws IOException {
        ControllerCommerciante.getInstance().logout();
        close(logoutButton);
        startWindow("C3 v1.0", "/loginC3_2.fxml", LoginC3Controller.getInstance());
    }

    @FXML
    private void aggiornaTutto() throws SQLException {
        aggiornaTabellaProdotti();
        setDatiAccount();
    }


    private void setDatiAccount() {
        IDutenteLabel.setText("ID UTENTE: " + ControllerCommerciante.getInstance().getCommerciante().getID());
        usernameTextField.setText(ControllerCommerciante.getInstance().getCommerciante().getUsername());
        passwordTextField.setText(ControllerCommerciante.getInstance().getCommerciante().getPassword());
        emailTextField.setText(ControllerCommerciante.getInstance().getCommerciante().getEmail());
        ragioneSocialeTextField.setText(ControllerCommerciante.getInstance().getCommerciante().getRagioneSociale());
        if (!Objects.isNull(ControllerCommerciante.getInstance().getCommerciante().getTelefono()))
            telefonoTextField.setText(ControllerCommerciante.getInstance().getCommerciante().getTelefono());
        if (!Objects.isNull(ControllerCommerciante.getInstance().getCommerciante().getIndirizzo()))
            indirizzoTextField.setText(ControllerCommerciante.getInstance().getCommerciante().getIndirizzo());
    }

    public void aggiornaTabellaProdotti() throws SQLException {
        riempiTabellaProdotti();
        tabellaProdotti.getItems().clear();
        tabellaProdotti.setItems(FXCollections.observableArrayList(ControllerCommerciante.getInstance().getProdotti()));
        //tabellaProdotti.getItems().setAll(ControllerCommerciante.getInstance().getProdotti());
    }

    private void riempiTabellaProdotti() {
//        IDtabellaProdotti.setCellValueFactory(p -> new SimpleStringProperty(p.getValue().getID()));
//        nomeTabellaProdotti.setCellValueFactory(p -> new SimpleStringProperty(p.getValue().getNome()));
//        //prezzoTabellaProdotti.setCellValueFactory(p -> new SimpleStringProperty(p.getValue().getPrezzo()));
//        //quantitaTabellaProdotti.setCellValueFactory(p -> new SimpleIntegerProperty(p.getValue().getQuantita()));
//        statoTabellaProdotti.setCellValueFactory(p -> new SimpleStringProperty(getDisponibilita(p.getValue().getQuantita())));

        //IDtabellaProdotti.setCellValueFactory(new PropertyValueFactory<Prodotto, String>("ID"));
        //nomeTabellaProdotti.setCellValueFactory(new PropertyValueFactory<Prodotto, String>("Nome"));
        //prezzoTabellaProdotti.setCellValueFactory(new PropertyValueFactory<Prodotto, Double>("Prezzo"));
        quantitaTabellaProdotti.setCellValueFactory(new PropertyValueFactory<Prodotto, Integer>("Nome"));
        //statoTabellaProdotti.setCellValueFactory(new PropertyValueFactory<Prodotto, String>(Stat));

    }

    private String getDisponibilita(int disponibilita) {
        if (disponibilita > 0) return "DISPONIBILE";
        else return "NON DISPONIBILE";
    }
}
