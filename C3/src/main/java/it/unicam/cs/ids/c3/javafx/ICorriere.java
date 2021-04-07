package it.unicam.cs.ids.c3.javafx;

import it.unicam.cs.ids.c3.controller.ControllerCommerciante;
import it.unicam.cs.ids.c3.controller.ControllerCorriere;
import it.unicam.cs.ids.c3.model.Ritiro;
import it.unicam.cs.ids.c3.model.StatoCorriere;
import it.unicam.cs.ids.c3.model.StatoTracking;
import it.unicam.cs.ids.c3.model.TipoConsegna;
import javafx.animation.FadeTransition;
import javafx.animation.TranslateTransition;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import javafx.util.Duration;

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
    private RadioButton disponibileRadioButton, nonDisponibileRadioButton;
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
        menuPane.setVisible(true);
        blackPane.setVisible(false);

        FadeTransition fadeTransition = new FadeTransition(Duration.seconds(0.5), blackPane);
        fadeTransition.setFromValue(1);
        fadeTransition.setToValue(0);
        fadeTransition.play();

        TranslateTransition translateTransition = new TranslateTransition(Duration.seconds(0.5), menuPane);
        translateTransition.setByX(-600);
        translateTransition.play();

        menuImageView.setOnMouseClicked(event -> {
            blackPane.setVisible(true);

            FadeTransition fadeTransition1 = new FadeTransition(Duration.seconds(0.5), blackPane);
            fadeTransition1.setFromValue(0);
            fadeTransition1.setToValue(0.15);
            fadeTransition1.play();

            TranslateTransition translateTransition1 = new TranslateTransition(Duration.seconds(0.5), menuPane);
            translateTransition1.setByX(+600);
            translateTransition1.play();

        });

        nascondiTutto();
        mostraTransition(homePane);
        try {
            aggiornaListaRitiri();
        } catch (SQLException e) {
            createErrorAlert(e.getMessage());
        }
        setDatiAccount();
        bentornatoText.setText("Bentornato, " + ControllerCorriere.getInstance().getCorriere().getUsername() + "!");
    }

    @FXML
    private void nascondiMenu() {
        nascondiMenu(blackPane, menuPane);
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
                    Utils.getInstance().getRitiroAnchorPaneCorriere(ritiro)));
        });
        if (ritiriAccordion.getPanes().isEmpty())
            elencoRitiriLabel.setText("Nessun ritiro attivo.");
        else elencoRitiriLabel.setText("Elenco dei ritiri commissionati:");
    }

    @FXML
    private void aggiornaTracking() {
        try {
            Utils.getInstance().controllaAccordion(ritiriAccordion, "ritiro");
            AnchorPane anchorPane = (AnchorPane) ritiriAccordion.getExpandedPane().getContent();

            String ID = ((Label) anchorPane.getChildren().get(1)).getText();
            String IDCommerciante = ((Label) anchorPane.getChildren().get(12)).getText();
            String IDCliente = ((Label) anchorPane.getChildren().get(10)).getText();
            String destinazione = ((Label) anchorPane.getChildren().get(2)).getText();

            String tipo = ((Label) anchorPane.getChildren().get(8)).getText();
            TipoConsegna tipoConsegna = TipoConsegna.valueOf(tipo);

            String ritirato = ((Label) anchorPane.getChildren().get(6)).getText();
            boolean isRitirato = ritirato.equals("Ritirato dal cliente: RITIRATO");

            Node child = anchorPane.getChildren().get(15);
            StatoTracking statoTracking;

            if (child instanceof ChoiceBox) {
                ChoiceBox statoTrackingChoiceBox = (ChoiceBox) child;
                statoTracking = StatoTracking.valueOf(statoTrackingChoiceBox.getValue().toString());
            } else throw new IllegalArgumentException("Not a Choicebox");

            ControllerCorriere.getInstance().aggiornaTracking(ID, IDCommerciante, IDCliente, destinazione, isRitirato, tipoConsegna, statoTracking);

            aggiornaListaRitiri();
        } catch (IllegalArgumentException | SQLException e) {
            createErrorAlert(e.getMessage());
        }
    }

    //ACCOUNT-----------------------------------------------------------------------------------------------------

    @FXML
    private void mostraAccount() {
        nascondiMenu(blackPane, menuPane);
        nascondiTutto();
        mostraTransition(accountPane);
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
        if (ControllerCorriere.getInstance().getCorriere().getStato().equals(StatoCorriere.DISPONIBILE))
            selezionaDisponibile();
        else selezionaNonDisponibile();
    }

    @FXML
    private void mostraPassword() {
        mostraPassword(mostraPasswordCheckBox, passwordField, mostraPasswordTextField);
    }

    @FXML
    private void salvaModifiche() throws SQLException {
        ControllerCorriere.getInstance().modificaCorriere(usernameTextField.getText(),
                getPassword(), emailTextField.getText(), ragioneSocialeTextField.getText(),
                telefonoTextField.getText(), indirizzoTextField.getText(), getStato());
        setDatiAccount();
    }

    private StatoCorriere getStato() {
        if (disponibileRadioButton.isSelected())
            return StatoCorriere.DISPONIBILE;
        else return StatoCorriere.NON_DISPONIBILE;
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
            startWindow("C3 v1.0", "/loginC3.fxml", LoginC3Controller.getInstance());
        }
    }

    @FXML
    private void eliminaAccount() throws SQLException, IOException {
        if (createConfirmationAlert("Sei sicuro di voler eliminare l'account?\nL'operazione sara' irreversibile.")) {
            ControllerCommerciante.getInstance().eliminaAccount();
            close(logoutButton);
            startWindow("C3 v1.0", "/loginC3.fxml", LoginC3Controller.getInstance());
        }
    }

    @FXML
    private void mostraImpostazioni() {
        nascondiMenu(blackPane, menuPane);
        nascondiTutto();
        mostraTransition(impostazioniPane);
    }
}
