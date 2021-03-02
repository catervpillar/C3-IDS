package it.unicam.cs.ids.c3.javafx;

import it.unicam.cs.ids.c3.controller.ControllerCommerciante;
import it.unicam.cs.ids.c3.model.Prodotto;
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
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.ResourceBundle;
import java.util.concurrent.atomic.AtomicInteger;

public class ICommerciante2 implements Initializable, JavaFXController {
    private static ICommerciante2 instance;

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


    private ICommerciante2() {
    }

    public static ICommerciante2 getInstance() {
        if (Objects.isNull(instance))
            instance = new ICommerciante2();
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
        } catch (SQLException throwables) {
            System.out.println("\n\nERROREE\n\n");
            throwables.printStackTrace();
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
            controllaAccordion();
            startWindow("Modifica prodotto", "/modificaProdotto.fxml", ModificaProdotto.getInstance());
        } catch (IllegalArgumentException | IOException e) {
            createErrorAlert(e.getMessage());
        }
    }

    @FXML
    private void eliminaProdotto() {
        try {
            controllaAccordion();
            if (createConfirmationAlert("Sei sicuro di eliminare il prodotto selezionato?")) {
                SerializerElimina.getInstance().eliminaProdotto(getExpandedProdottoID());
                aggiornaListaProdotti();
            }
        } catch (IllegalArgumentException | SQLException e) {
            createErrorAlert(e.getMessage());
        }
    }

    private void controllaAccordion() {
        TitledPane expandedPane = prodottiAccordion.getExpandedPane();
        if (Objects.isNull(expandedPane))
            throw new IllegalArgumentException("Seleziona prima un prodotto");
    }

    private String getExpandedProdottoID() {
        AnchorPane anchorPane = (AnchorPane) prodottiAccordion.getExpandedPane().getContent();
        Label ID = (Label) anchorPane.getChildren().get(2);
        return ID.getText();
    }

    public void aggiornaListaProdotti() throws SQLException {
        prodottiAccordion.getPanes().clear();
        List<Prodotto> listaProdotti = ControllerCommerciante.getInstance().getProdotti();
//        List<String> imagesUrl = new ArrayList<>();
//        imagesUrl.add("https://bit.ly/37NHgxh");
//        imagesUrl.add("https://bit.ly/3uyZe0l");
//        imagesUrl.add("https://bit.ly/3dTIxH4");
//        AtomicInteger i = new AtomicInteger(0);
//        listaProdotti.forEach(prodotto -> {
//            prodottiAccordion.getPanes().add(new TitledPane(prodotto.getNome(), getProdottoAnchorPane(prodotto, imagesUrl.get(i.getAndIncrement()))));
//        });

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
    private void annullaRitiro(){

    }

    @FXML
    private void mostraPromozioni() {
        nascondiMenu();
        nascondiTutto();
        mostraTransition(promozioniPane);
    }

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
