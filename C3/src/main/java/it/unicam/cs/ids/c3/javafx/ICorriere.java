package it.unicam.cs.ids.c3.javafx;

import it.unicam.cs.ids.c3.controller.ControllerCliente;
import it.unicam.cs.ids.c3.controller.ControllerCommerciante;
import it.unicam.cs.ids.c3.controller.ControllerCorriere;
import it.unicam.cs.ids.c3.model.Prodotto;
import it.unicam.cs.ids.c3.model.Ritiro;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.List;
import java.util.Objects;
import java.util.ResourceBundle;

public class ICorriere implements Initializable, JavaFXController, IUtente {
    private static ICorriere instance;

    private ICorriere() {
    }

    public static ICorriere getInstance() {
        if (Objects.isNull(instance))
            instance = new ICorriere();
        return instance;
    }

    @FXML
    private ImageView menuImageView;
    @FXML
    private AnchorPane blackPane, menuPane, homePane, ritiriPane, accountPane, impostazioniPane;
    @FXML
    private Button homeButton, ritiriButton, accountButton, impostazioniButton;

    //Home pane
    @FXML
    private Text bentornatoText;

    //Ritiri pane
    @FXML
    private Label elencoRitiriLabel;
    @FXML
    private Accordion ritiriAccordion;
    @FXML
    private Button aggiornaTracking;

    // Account pane
    @FXML
    private Button salvaModificheButton, logoutButton, eliminaAccountButton;
    @FXML
    private CheckBox mostraPasswordCheckBox;
    @FXML
    private Label IDutenteLabel;
    @FXML
    private TextField usernameTextField, emailTextField, ragioneSocialeTextField, telefonoTextField, indirizzoTextField, mostraPasswordTextField;
    @FXML
    private PasswordField passwordField;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    @Override
    public void nascondiTutto() {
        homePane.setVisible(false);
        ritiriPane.setVisible(false);
        accountPane.setVisible(false);
        impostazioniPane.setVisible(false);
        ritiriAccordion.setExpandedPane(null);
    }

    @FXML
    private void mostraHome() {
        nascondiMenu(blackPane, menuPane);
        nascondiTutto();
        mostraTransition(homePane);
    }

    //RITIRI-----------------------------------------------------------------------------------------------------

    @FXML
    private void mostraRitiri() {
        nascondiMenu(blackPane, menuPane);
        nascondiTutto();
        mostraTransition(ritiriPane);
    }

    private void aggiornaListaRitiri() throws SQLException {
        ritiriAccordion.getPanes().clear();
        List<Ritiro> listaRitiri = ControllerCorriere.getInstance().getRitiri();
        listaRitiri.forEach(ritiro -> {
            ritiriAccordion.getPanes().add(new TitledPane(ritiro.getID() + " " + ritiro.getDestinazione(),
                    Utils.getInstance().getRitiroAnchorPane(ritiro)));
        });
        if (ritiriAccordion.getPanes().isEmpty())
            elencoRitiriLabel.setText("Nessun ritiro attivo.");
        else elencoRitiriLabel.setText("Elenco dei ritiri commissionati:");
    }

    @FXML
    private void aggiornaTracking(){

    }

    //ACCOUNT-----------------------------------------------------------------------------------------------------

    @FXML
    private void mostraAccount() {
        nascondiMenu(blackPane, menuPane);
        nascondiTutto();
        mostraTransition(accountPane);
    }

    private void setDatiAccount() {
        IDutenteLabel.setText("ID UTENTE: " + ControllerCorriere.getInstance().getCorriere().getID());
        usernameTextField.setText(ControllerCorriere.getInstance().getCorriere().getUsername());
        passwordField.setText(ControllerCorriere.getInstance().getCorriere().getPassword());
        emailTextField.setText(ControllerCorriere.getInstance().getCorriere().getEmail());
        ragioneSocialeTextField.setText(ControllerCorriere.getInstance().getCorriere().getRagioneSociale());
        if (!Objects.isNull(ControllerCorriere.getInstance().getCorriere().getTelefono()))
            telefonoTextField.setText(ControllerCorriere.getInstance().getCorriere().getTelefono());
        if (!Objects.isNull(ControllerCorriere.getInstance().getCorriere().getIndirizzo()))
            indirizzoTextField.setText(ControllerCorriere.getInstance().getCorriere().getIndirizzo());
    }

    @FXML
    private void mostraPassword() {
        mostraPassword(mostraPasswordCheckBox, passwordField, mostraPasswordTextField);
    }

    @FXML
    private void salvaModifiche() throws SQLException {
        ControllerCorriere.getInstance().modificaCorriere(usernameTextField.getText(),
                getPassword(), emailTextField.getText(), ragioneSocialeTextField.getText(),
                telefonoTextField.getText(), indirizzoTextField.getText());
        setDatiAccount();
    }

    private String getPassword() {
        if (mostraPasswordCheckBox.isSelected())
            return mostraPasswordTextField.getText();
        else return passwordField.getText();
    }

    @FXML
    private void logout() throws IOException {
        if (createConfirmationAlert("Sei sicuro di voler uscire?")) {
            ControllerCorriere.getInstance().logout();
            close(logoutButton);
            startWindow("C3 v1.0", "/loginC3_2.fxml", LoginC3Controller.getInstance());
        }
    }

    @FXML
    private void eliminaAccount() throws SQLException, IOException {
        if (createConfirmationAlert("Sei sicuro di voler eliminare l'account?\nL'operazione sara' irreversibile.")) {
            ControllerCommerciante.getInstance().eliminaAccount();
            close(logoutButton);
            startWindow("C3 v1.0", "/loginC3_2.fxml", LoginC3Controller.getInstance());
        }
    }

    @FXML
    private void mostraImpostazioni() {
        nascondiMenu(blackPane, menuPane);
        nascondiTutto();
        mostraTransition(impostazioniPane);
    }
}
