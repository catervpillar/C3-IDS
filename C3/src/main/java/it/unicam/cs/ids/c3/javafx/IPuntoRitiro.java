package it.unicam.cs.ids.c3.javafx;

import it.unicam.cs.ids.c3.utenti.puntoRitiro.ControllerPuntoRitiro;
import it.unicam.cs.ids.c3.ritiro.Ritiro;
import it.unicam.cs.ids.c3.ritiro.StatoTracking;
import it.unicam.cs.ids.c3.ritiro.TipoConsegna;
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

public class IPuntoRitiro implements Initializable, JavaFXController, IUtente {
    private static IPuntoRitiro instance;

    private IPuntoRitiro() {
    }

    public static IPuntoRitiro getInstance() {
        if (Objects.isNull(instance))
            instance = new IPuntoRitiro();
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
    private Button contrassegnaButton;

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
        bentornatoText.setText("Bentornato, " + ControllerPuntoRitiro.getInstance().getPuntoRitiro().getUsername() + "!");
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
        List<Ritiro> listaRitiri = ControllerPuntoRitiro.getInstance().getRitiri();
        listaRitiri.forEach(ritiro -> {
            ritiriAccordion.getPanes().add(new TitledPane(ritiro.getID() + " " + ritiro.getDestinazione(),
                    Utils.getInstance().getRitiroAnchorPanePuntoRitiro(ritiro)));
        });
        if (ritiriAccordion.getPanes().isEmpty())
            elencoRitiriLabel.setText("Nessun ritiro attivo.");
        else elencoRitiriLabel.setText("Elenco dei ritiri affidati:");
    }

    @FXML
    private void contrassegna() {
        try {
            Utils.getInstance().controllaAccordion(ritiriAccordion, "ritiro");
            AnchorPane anchorPane = (AnchorPane) ritiriAccordion.getExpandedPane().getContent();

            String ID = ((Label) anchorPane.getChildren().get(1)).getText();
            String IDCommerciante = ((Label) anchorPane.getChildren().get(12)).getText();
            String IDCliente = ((Label) anchorPane.getChildren().get(10)).getText();
            String IDCorriere = ((Label) anchorPane.getChildren().get(13)).getText();

            String tipo = ((Label) anchorPane.getChildren().get(8)).getText();
            TipoConsegna tipoConsegna = TipoConsegna.valueOf(tipo);

            String statoTracking = ((Label) anchorPane.getChildren().get(15)).getText().substring(16);
            StatoTracking stato = StatoTracking.valueOf(statoTracking);

            Node child = anchorPane.getChildren().get(14);
            boolean isRitirato;

            if (child instanceof ChoiceBox) {
                ChoiceBox ritiratoChoiceBox = (ChoiceBox) child;
                isRitirato = ritiratoChoiceBox.getValue().equals("RITIRATO");
            } else throw new IllegalArgumentException("Not a Choicebox");

            ControllerPuntoRitiro.getInstance().contrassegna(ID, IDCommerciante, IDCliente, IDCorriere, isRitirato, tipoConsegna, stato);

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

    private void setDatiAccount() {
        IDutenteLabel.setText("ID UTENTE: " + ControllerPuntoRitiro.getInstance().getPuntoRitiro().getID());
        usernameTextField.setText(ControllerPuntoRitiro.getInstance().getPuntoRitiro().getUsername());
        passwordField.setText(ControllerPuntoRitiro.getInstance().getPuntoRitiro().getPassword());
        emailTextField.setText(ControllerPuntoRitiro.getInstance().getPuntoRitiro().getEmail());
        ragioneSocialeTextField.setText(ControllerPuntoRitiro.getInstance().getPuntoRitiro().getRagioneSociale());
        if (!Objects.isNull(ControllerPuntoRitiro.getInstance().getPuntoRitiro().getTelefono()))
            telefonoTextField.setText(ControllerPuntoRitiro.getInstance().getPuntoRitiro().getTelefono());
        if (!Objects.isNull(ControllerPuntoRitiro.getInstance().getPuntoRitiro().getIndirizzo()))
            indirizzoTextField.setText(ControllerPuntoRitiro.getInstance().getPuntoRitiro().getIndirizzo());
    }

    @FXML
    private void mostraPassword() {
        mostraPassword(mostraPasswordCheckBox, passwordField, mostraPasswordTextField);
    }

    @FXML
    private void salvaModifiche() throws SQLException {
        ControllerPuntoRitiro.getInstance().modificaPuntoRitiro(usernameTextField.getText(),
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
            ControllerPuntoRitiro.getInstance().logout();
            close(logoutButton);
            startWindow("C3 v1.0", "/loginC3.fxml", LoginC3Controller.getInstance());
        }
    }

    @FXML
    private void eliminaAccount() throws SQLException, IOException {
        if (createConfirmationAlert("Sei sicuro di voler eliminare l'account?\nL'operazione sara' irreversibile.")) {
            ControllerPuntoRitiro.getInstance().eliminaAccount();
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
