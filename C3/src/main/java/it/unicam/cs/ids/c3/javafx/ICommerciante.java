package it.unicam.cs.ids.c3.javafx;

import it.unicam.cs.ids.c3.controller.ControllerCommerciante;
import it.unicam.cs.ids.c3.controller.GestorePromozioni;
import it.unicam.cs.ids.c3.model.Prodotto;
import it.unicam.cs.ids.c3.model.Promozione;
import it.unicam.cs.ids.c3.model.Ritiro;
import it.unicam.cs.ids.c3.services.SerializerElimina;
import it.unicam.cs.ids.c3.utilities.Controllore;
import javafx.animation.FadeTransition;
import javafx.animation.TranslateTransition;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.util.Duration;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.*;

public class ICommerciante implements Initializable, JavaFXController {
    private static ICommerciante instance;

    @FXML
    private ImageView menuImageView;
    @FXML
    private AnchorPane blackPane, menuPane, homePane, prodottiPane, ritiriPane, promozioniPane, accountPane, impostazioniPane;
    @FXML
    private Button homeButton, prodottiButton, ritiriButton, promozioniButton, accountButton, impostazioniButton;

    //Home pane
    @FXML
    private Text bentornatoText;

    //Prodotti pane
    @FXML
    private Label elencoProdottiLabel;
    @FXML
    private Accordion prodottiAccordion;
    @FXML
    private Button aggiungiProdottoButton, modificaProdottoButton, eliminaProdottoButton;

    //Ritiri pane
    @FXML
    private Button prenotaRitiroButton, annullaRitiroButton;
    @FXML
    private Label elencoRitiriLabel;
    @FXML
    private Accordion ritiriAccordion;

    //Promozioni pane
    @FXML
    private Label elencoPromozioniLabel;
    @FXML
    private Accordion promozioniAccordion;
    @FXML
    private Button creaPromozioneButton, modificaPromozioneButton, eliminaPromozioneButton;

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


    private ICommerciante() {
    }

    public static ICommerciante getInstance() {
        if (Objects.isNull(instance))
            instance = new ICommerciante();
        return instance;
    }

    public Accordion getProdottiAccordion() {
        return prodottiAccordion;
    }

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
            aggiornaListaProdotti();
            aggiornaListaRitiri();
            aggiornaListaPromozioni();
        } catch (SQLException e) {
            createErrorAlert(e.getMessage());
        }
        setDatiAccount();
        bentornatoText.setText("Bentornato, " + ControllerCommerciante.getInstance().getCommerciante().getUsername() + "!");

    }

    @FXML
    private void nascondiMenu() {
        FadeTransition fadeTransition1 = new FadeTransition(Duration.seconds(0.5), blackPane);
        fadeTransition1.setFromValue(0.15);
        fadeTransition1.setToValue(0);
        fadeTransition1.play();

        fadeTransition1.setOnFinished(event1 -> {
            blackPane.setVisible(false);
        });

        TranslateTransition translateTransition1 = new TranslateTransition(Duration.seconds(0.5), menuPane);
        translateTransition1.setByX(-600);
        translateTransition1.play();
    }

    private void mostraTransition(AnchorPane pane) {
        pane.setVisible(true);
        FadeTransition fadeTransition = new FadeTransition(Duration.seconds(0.5), pane);
        fadeTransition.setFromValue(0);
        fadeTransition.setToValue(1);
        fadeTransition.play();
    }

    private void nascondiTutto() {
        homePane.setVisible(false);
        prodottiPane.setVisible(false);
        ritiriPane.setVisible(false);
        promozioniPane.setVisible(false);
        accountPane.setVisible(false);
        impostazioniPane.setVisible(false);
        prodottiAccordion.setExpandedPane(null);
    }

    @FXML
    private void mostraHome() {
        nascondiMenu();
        nascondiTutto();
        mostraTransition(homePane);
    }

    //PRODOTTI---------------------------------------------------------------------------------------------------

    @FXML
    private void mostraProdotti() {
        nascondiMenu();
        nascondiTutto();
        mostraTransition(prodottiPane);
    }

    @FXML
    private void aggiungiProdotto() throws IOException {
        startWindow("Creazione nuovo prodotto", "/creaProdotto.fxml", CreaProdotto.getInstance());
    }

    @FXML
    private void modificaProdotto() {
        try {
            controllaAccordion(prodottiAccordion, "prodotto");
            startWindow("Modifica prodotto", "/modificaProdotto.fxml", ModificaProdotto.getInstance());
        } catch (IllegalArgumentException | IOException e) {
            createErrorAlert(e.getMessage());
        }
    }

    @FXML
    private void eliminaProdotto() {
        try {
            controllaAccordion(prodottiAccordion, "prodotto");
            if (createConfirmationAlert("Sei sicuro di eliminare il prodotto selezionato?")) {
                SerializerElimina.getInstance().eliminaProdotto(getExpandedItemID(prodottiAccordion, 2));
                aggiornaListaProdotti();
            }
        } catch (IllegalArgumentException | SQLException e) {
            createErrorAlert(e.getMessage());
        }
    }

    private void controllaAccordion(Accordion accordion, String word) {
        TitledPane expandedPane = accordion.getExpandedPane();
        if (Objects.isNull(expandedPane))
            throw new IllegalArgumentException("Seleziona prima un " + word);
    }

    private String getExpandedItemID(Accordion accordion, int index) {
        AnchorPane anchorPane = (AnchorPane) accordion.getExpandedPane().getContent();
        Label ID = (Label) anchorPane.getChildren().get(index);
        return ID.getText();
    }

    public void aggiornaListaProdotti() throws SQLException {
        prodottiAccordion.getPanes().clear();
        List<Prodotto> listaProdotti = ControllerCommerciante.getInstance().getProdotti();
        listaProdotti.forEach(prodotto -> {
            prodottiAccordion.getPanes().add(new TitledPane(prodotto.getNome(), Utils.getInstance().getProdottoAnchorPane(prodotto)));
        });
        if (prodottiAccordion.getPanes().isEmpty())
            elencoProdottiLabel.setText("Nessun prodotto attualmente in vendita.");
        else elencoProdottiLabel.setText("Elenco dei tuoi prodotti attualmente in vendita:");
    }

    //RITIRI-----------------------------------------------------------------------------------------------------

    @FXML
    private void mostraRitiri() {
        nascondiMenu();
        nascondiTutto();
        mostraTransition(ritiriPane);
    }

    @FXML
    private void prenotaRitiro() throws IOException {
        startWindow("Prenotazione nuovo ritiro", "/prenotaRitiro.fxml", PrenotaRitiro.getInstance());
    }

    @FXML
    private void annullaRitiro() {
        try {
            controllaAccordion(ritiriAccordion, "ritiro");
            if (createConfirmationAlert("Sei sicuro di voler annullare il ritiro selezionato?")) {
                SerializerElimina.getInstance().eliminaRitiro(getExpandedItemID(ritiriAccordion, 1));
                aggiornaListaRitiri();
            }
        } catch (IllegalArgumentException | SQLException e) {
            createErrorAlert(e.getMessage());
        }
    }

    public void aggiornaListaRitiri() throws SQLException {
        ritiriAccordion.getPanes().clear();
        List<Ritiro> listaRitiri = ControllerCommerciante.getInstance().getRitiri();
        listaRitiri.forEach(ritiro -> {
            ritiriAccordion.getPanes().add(new TitledPane(ritiro.getID() + " " + ritiro.getDestinazione(),
                    Utils.getInstance().getRitiroAnchorPane(ritiro)));
        });
        if (ritiriAccordion.getPanes().isEmpty())
            elencoRitiriLabel.setText("Nessun ritiro attivo.");
        else elencoRitiriLabel.setText("Elenco dei ritiri prenotati:");
    }

    //PROMOZIONI--------------------------------------------------------------------------------------------------

    @FXML
    private void mostraPromozioni() {
        nascondiMenu();
        nascondiTutto();
        mostraTransition(promozioniPane);
    }

    @FXML
    private void creaPromozione() throws IOException, SQLException {
        startWindow("Creazione nuova promozione", "/creaPromozione.fxml", CreaPromozione.getInstance());
    }

    @FXML
    private void modificaPromozione() {
        try {
            controllaAccordion(promozioniAccordion, "promozione");
            AnchorPane anchorPane = (AnchorPane) promozioniAccordion.getExpandedPane().getContent();
            Label ID = (Label) anchorPane.getChildren().get(1);
            TextField nome = (TextField) anchorPane.getChildren().get(3);
            TextField descrizione = (TextField) anchorPane.getChildren().get(5);

            DatePicker dataInizioDatePicker = (DatePicker) anchorPane.getChildren().get(7);
            GregorianCalendar dataInizio = new GregorianCalendar();
            dataInizio.setTime(java.sql.Date.valueOf(dataInizioDatePicker.getValue()));

            DatePicker dataScadenzaDatePicker = (DatePicker) anchorPane.getChildren().get(9);
            GregorianCalendar dataScadenza = new GregorianCalendar();
            dataScadenza.setTime(java.sql.Date.valueOf(dataScadenzaDatePicker.getValue()));

            controllaCampi(nome, descrizione, dataInizioDatePicker, dataScadenzaDatePicker);
            ControllerCommerciante.getInstance().modificaPromozione(ID.getText(), nome.getText(), descrizione.getText(), dataInizio, dataScadenza);

            aggiornaListaPromozioni();
        } catch (IllegalArgumentException | SQLException e) {
            createErrorAlert(e.getMessage());
        }
    }

    private void controllaCampi(TextField nome, TextField descrizione, DatePicker dataInizioDatePicker, DatePicker dataScadenzaDatePicker) {
        Controllore.getInstance().controllaStringa(nome.getText(), "Inserisci un nome valido per la promozione");
        Controllore.getInstance().controllaStringa(descrizione.getText(), "Inserisci una descrizione per la promozione");
        Controllore.getInstance().controllaDatePicker(dataInizioDatePicker, "Selezionare una data d'inizio della promozione");
        Controllore.getInstance().controllaDatePicker(dataScadenzaDatePicker, "Selezionare una data di scadenza della promozione");
        if (dataInizioDatePicker.getValue().compareTo(dataScadenzaDatePicker.getValue()) > 0)
            throw new IllegalArgumentException("La data di scadenza non puo' essere precedente alla data di inizio");
    }

    @FXML
    private void eliminaPromozione() {
        try {
            controllaAccordion(promozioniAccordion, "promozione");
            if (createConfirmationAlert("Sei sicuro di voler eliminare la promozione selezionata?")) {
                SerializerElimina.getInstance().eliminaPromozione(getExpandedItemID(promozioniAccordion, 1));
                aggiornaListaPromozioni();
            }
        } catch (IllegalArgumentException | SQLException e) {
            createErrorAlert(e.getMessage());
        }
    }

    public void aggiornaListaPromozioni() throws SQLException {
        promozioniAccordion.getPanes().clear();
        List<Promozione> listaPromozioni = ControllerCommerciante.getInstance().getPromozioni();
        listaPromozioni.forEach(promo -> promozioniAccordion.getPanes().add(new TitledPane(promo.getNome(),
                Utils.getInstance().getPromozioneAnchorPane(promo))));
        if (promozioniAccordion.getPanes().isEmpty())
            elencoPromozioniLabel.setText("Nessuna promozione attiva.");
        else elencoPromozioniLabel.setText("Elenco delle promozioni attive:");
    }

    //ACCOUNT-----------------------------------------------------------------------------------------------------

    @FXML
    private void mostraAccount() {
        nascondiMenu();
        nascondiTutto();
        mostraTransition(accountPane);
    }

    private void setDatiAccount() {
        IDutenteLabel.setText("ID UTENTE: " + ControllerCommerciante.getInstance().getCommerciante().getID());
        usernameTextField.setText(ControllerCommerciante.getInstance().getCommerciante().getUsername());
        passwordField.setText(ControllerCommerciante.getInstance().getCommerciante().getPassword());
        emailTextField.setText(ControllerCommerciante.getInstance().getCommerciante().getEmail());
        ragioneSocialeTextField.setText(ControllerCommerciante.getInstance().getCommerciante().getRagioneSociale());
        if (!Objects.isNull(ControllerCommerciante.getInstance().getCommerciante().getTelefono()))
            telefonoTextField.setText(ControllerCommerciante.getInstance().getCommerciante().getTelefono());
        if (!Objects.isNull(ControllerCommerciante.getInstance().getCommerciante().getIndirizzo()))
            indirizzoTextField.setText(ControllerCommerciante.getInstance().getCommerciante().getIndirizzo());
    }

    @FXML
    private void mostraPassword() {
        mostraPassword(mostraPasswordCheckBox, passwordField, mostraPasswordTextField);
    }

    @FXML
    private void salvaModifiche() throws SQLException {
        ControllerCommerciante.getInstance().modificaCommerciante(usernameTextField.getText(),
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
            ControllerCommerciante.getInstance().logout();
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
        nascondiMenu();
        nascondiTutto();
        mostraTransition(impostazioniPane);
    }
}
