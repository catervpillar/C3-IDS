package it.unicam.cs.ids.c3.javafx;

import it.unicam.cs.ids.c3.controller.ControllerCommerciante;
import it.unicam.cs.ids.c3.model.Prodotto;
import it.unicam.cs.ids.c3.model.Promozione;
import it.unicam.cs.ids.c3.model.Ritiro;
import it.unicam.cs.ids.c3.services.SerializerElimina;
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
            prodottiAccordion.getPanes().add(new TitledPane(prodotto.getNome(), getProdottoAnchorPane(prodotto)));
        });
        if (prodottiAccordion.getPanes().isEmpty())
            elencoProdottiLabel.setText("Nessun prodotto attualmente in vendita.");
        else elencoProdottiLabel.setText("Elenco dei tuoi prodotti attualmente in vendita:");
    }

    private AnchorPane getProdottoAnchorPane(Prodotto prodotto) {
        AnchorPane anchorPane = new AnchorPane();
        Image image;
        if (!Objects.isNull(prodotto.getURLImmagine()) && !prodotto.getURLImmagine().isBlank())
            image = new Image(prodotto.getURLImmagine(), 150, 150, false, false);
        else
            image = new Image("https://bit.ly/3011Ztl", 150, 150, false, false);
        ImageView imageView = new ImageView();
        imageView.setImage(image);
        imageView.setX(15);
        imageView.setY(15);

        Label nomeProdotto = new Label("Nome prodotto: " + prodotto.getNome());
        nomeProdotto.setFont(new Font(16));
        nomeProdotto.setLayoutX(188);
        nomeProdotto.setLayoutY(15);

        Label IDprodotto = new Label("ID prodotto: ");
        IDprodotto.setFont(new Font(16));
        IDprodotto.setLayoutX(188);
        IDprodotto.setLayoutY(40);

        Label ID = new Label(prodotto.getID());
        ID.setFont(new Font(16));
        ID.setLayoutX(280);
        ID.setLayoutY(40);

        Label prezzo = new Label("Prezzo: " + "\u20AC" + prodotto.getPrezzo());
        prezzo.setFont(new Font(16));
        prezzo.setLayoutX(188);
        prezzo.setLayoutY(65);

        Label disponibilita = new Label("Disponibilita': " + prodotto.getQuantita());
        disponibilita.setFont(new Font(16));
        disponibilita.setLayoutX(188);
        disponibilita.setLayoutY(90);

        anchorPane.getChildren().addAll(nomeProdotto, IDprodotto, ID, prezzo, disponibilita, imageView);

        return anchorPane;
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
                    getRitiroAnchorPane(ritiro)));
        });
        if (ritiriAccordion.getPanes().isEmpty())
            elencoRitiriLabel.setText("Nessun ritiro attivo.");
        else elencoRitiriLabel.setText("Elenco dei ritiri prenotati:");
    }

    private AnchorPane getRitiroAnchorPane(Ritiro ritiro) {
        AnchorPane anchorPane = new AnchorPane();

        Label IDRitiro = new Label("ID ritiro: ");
        IDRitiro.setFont(new Font(16));
        IDRitiro.setLayoutX(25);
        IDRitiro.setLayoutY(15);

        Label ID = new Label(ritiro.getID());
        ID.setFont(new Font(16));
        ID.setLayoutX(90);
        ID.setLayoutY(15);

        Label destinazione = new Label("Destinazione: " + ritiro.getDestinazione());
        destinazione.setFont(new Font(16));
        destinazione.setLayoutX(25);
        destinazione.setLayoutY(40);

        Label codiceRitiro = new Label("Codice ritiro: " + ritiro.getCodiceRitiro());
        codiceRitiro.setFont(new Font(16));
        codiceRitiro.setLayoutX(25);
        codiceRitiro.setLayoutY(65);

        Label dataPrenotazione = new Label("Data prenotazione: " +
                ritiro.getDataPrenotazione().get(Calendar.DAY_OF_MONTH) + "/" +
                (ritiro.getDataPrenotazione().get(Calendar.MONTH) + 1) + "/" +
                ritiro.getDataPrenotazione().get(Calendar.YEAR));


        dataPrenotazione.setFont(new Font(16));
        dataPrenotazione.setLayoutX(25);
        dataPrenotazione.setLayoutY(90);

        Label dataConsegna;
        if (!Objects.isNull(ritiro.getDataConsegna()))
            dataConsegna = new Label("Data consegna: " + ritiro.getDataConsegna().get(Calendar.DAY_OF_MONTH) + "/" +
                    (ritiro.getDataConsegna().get(Calendar.MONTH) + 1) + "/" +
                    ritiro.getDataConsegna().get(Calendar.YEAR));
        else
            dataConsegna = new Label("Data consegna: Non ancora consegnato");
        dataConsegna.setFont(new Font(16));
        dataConsegna.setLayoutX(25);
        dataConsegna.setLayoutY(115);

        Label ritirato;
        if (ritiro.isRitirato())
            ritirato = new Label("Ritirato dal cliente: RITIRATO");
        else ritirato = new Label("Ritirato dal cliente: NON RITIRATO");
        ritirato.setFont(new Font(16));
        ritirato.setLayoutX(25);
        ritirato.setLayoutY(140);

        Label tipoConsegna = new Label("Tipo consegna: " + ritiro.getTipoConsegna().name());
        tipoConsegna.setFont(new Font(16));
        tipoConsegna.setLayoutX(25);
        tipoConsegna.setLayoutY(165);

        Label IDCliente = new Label("ID cliente: " + ritiro.getIDCliente());
        IDCliente.setFont(new Font(16));
        IDCliente.setLayoutX(25);
        IDCliente.setLayoutY(190);

        Label IDCorriere = new Label("ID corriere: " + ritiro.getIDCorriere());
        IDCorriere.setFont(new Font(16));
        IDCorriere.setLayoutX(25);
        IDCorriere.setLayoutY(215);


        Label statoTracking = new Label("Stato tracking: " + ritiro.getStatoTracking().name());
        statoTracking.setFont(new Font(16));
        statoTracking.setLayoutX(25);
        statoTracking.setLayoutY(240);

        anchorPane.getChildren().addAll(IDRitiro, ID, destinazione, codiceRitiro, dataPrenotazione,
                dataConsegna, ritirato, tipoConsegna, IDCliente, IDCorriere, statoTracking);

        return anchorPane;
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
            startWindow("Modifica promozione", "/modificaPromozione.fxml", ModificaPromozione.getInstance());

        } catch (IllegalArgumentException | IOException e) {
            createErrorAlert(e.getMessage());
        }
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
                getPromozioneAnchorPane(promo))));
        if (promozioniAccordion.getPanes().isEmpty())
            elencoPromozioniLabel.setText("Nessuna promozione attiva.");
        else elencoPromozioniLabel.setText("Elenco delle promozioni attive:");
    }

    private AnchorPane getPromozioneAnchorPane(Promozione promozione) {
        AnchorPane anchorPane = new AnchorPane();

        Label IDPromozione = new Label("ID promozione: ");
        IDPromozione.setFont(new Font(13));
        IDPromozione.setLayoutX(25);
        IDPromozione.setLayoutY(15);

        Label ID = new Label(promozione.getID());
        ID.setFont(new Font(13));
        ID.setLayoutX(142);
        ID.setLayoutY(15);

        Label dataInizioLabel = new Label("Data inizio: ");
        dataInizioLabel.setFont(new Font(13));
        dataInizioLabel.setLayoutX(25);
        dataInizioLabel.setLayoutY(120);

        DatePicker dataInizio = new DatePicker();
        dataInizio.setValue(LocalDate.of(promozione.getDataInizio().get(Calendar.YEAR),
                promozione.getDataInizio().get(Calendar.MONTH) + 1,
                promozione.getDataInizio().get(Calendar.DAY_OF_MONTH)));
        dataInizio.setLayoutX(140);
        dataInizio.setLayoutY(115);

        Label dataScadenzaLabel = new Label("Data scadenza: ");
        dataScadenzaLabel.setFont(new Font(13));
        dataScadenzaLabel.setLayoutX(25);
        dataScadenzaLabel.setLayoutY(155);

        DatePicker dataScadenza = new DatePicker();
        dataScadenza.setValue(LocalDate.of(promozione.getDataScadenza().get(Calendar.YEAR),
                promozione.getDataScadenza().get(Calendar.MONTH) + 1,
                promozione.getDataScadenza().get(Calendar.DAY_OF_MONTH)));
        dataScadenza.setLayoutX(140);
        dataScadenza.setLayoutY(150);

        Label nomeLabel = new Label("Nome: ");
        nomeLabel.setFont(new Font(13));
        nomeLabel.setLayoutX(25);
        nomeLabel.setLayoutY(50);

        TextField nome = new TextField(promozione.getNome());
        nome.setFont(new Font(13));
        nome.setLayoutX(140);
        nome.setLayoutY(45);
        nome.setMinHeight(dataInizio.getMinHeight());

        Label descrizioneLabel = new Label("Descrizione: ");
        descrizioneLabel.setFont(new Font(13));
        descrizioneLabel.setLayoutX(25);
        descrizioneLabel.setLayoutY(85);

        TextField descrizione = new TextField(promozione.getDescrizione());
        descrizione.setFont(new Font(13));
        descrizione.setLayoutX(140);
        descrizione.setLayoutY(80);
        descrizione.setMinHeight(dataInizio.getMinHeight());

        anchorPane.getChildren().addAll(IDPromozione, ID, nomeLabel, nome, descrizioneLabel, descrizione, dataInizioLabel, dataInizio, dataScadenzaLabel, dataScadenza);
        return anchorPane;
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
        if (mostraPasswordCheckBox.isSelected()) {
            mostraPasswordTextField.setText(passwordField.getText());
            mostraPasswordTextField.setVisible(true);
            passwordField.setVisible(false);
            return;
        }
        passwordField.setText(mostraPasswordTextField.getText());
        passwordField.setVisible(true);
        mostraPasswordTextField.setVisible(false);
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
