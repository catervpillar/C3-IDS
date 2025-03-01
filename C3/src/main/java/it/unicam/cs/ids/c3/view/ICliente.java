package it.unicam.cs.ids.c3.view;

import it.unicam.cs.ids.c3.javafx.JavaFXController;
import it.unicam.cs.ids.c3.javafx.LoginC3Controller;
import it.unicam.cs.ids.c3.javafx.Utils;
import it.unicam.cs.ids.c3.javafx.CreaRecensione;
import it.unicam.cs.ids.c3.prodotto.ProdottoInterface;
import it.unicam.cs.ids.c3.promozione.PromozioneInterface;
import it.unicam.cs.ids.c3.recensione.RecensioneInterface;
import it.unicam.cs.ids.c3.recensione.VotoRecensioni;
import it.unicam.cs.ids.c3.ritiro.RitiroInterface;
import it.unicam.cs.ids.c3.utenti.cliente.ControllerCliente;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.List;
import java.util.Objects;
import java.util.ResourceBundle;

public class ICliente implements Initializable, JavaFXController, IUtente {
    private static ICliente instance;

    private ICliente() {
    }

    public static ICliente getInstance() {
        if (Objects.isNull(instance))
            instance = new ICliente();
        return instance;
    }

    @FXML
    private ImageView menuImageView;
    @FXML
    private AnchorPane blackPane, menuPane, homePane, prodottiPane, ritiriPane, promozioniPane, recensioniPane, accountPane, impostazioniPane;
    @FXML
    private Button homeButton, prodottiButton, ritiriButton, promozioniButton, recensioniButton, accountButton, impostazioniButton;

    //Home pane
    @FXML
    private Text bentornatoText;

    //Prodotti pane
    @FXML
    private Label elencoProdottiLabel;
    @FXML
    private Accordion prodottiAccordion;
    @FXML
    private TextField ricercaProdottoTextField;
    @FXML
    private Button cercaProdottoButton;

    //Ritiri pane
    @FXML
    private Label elencoRitiriLabel;
    @FXML
    private Accordion ritiriAccordion;

    //Promozioni pane
    @FXML
    private Label elencoPromozioniLabel;
    @FXML
    private Accordion promozioniAccordion;

    //Recensioni pane
    @FXML
    private Label elencoRecensioniLabel;
    @FXML
    private Accordion recensioniAccordion;
    @FXML
    private Button aggiungiRecensioneButton, modificaRecensioneButton, eliminaRecensioneButton;

    // Account pane
    @FXML
    private Button salvaModificheButton, logoutButton, eliminaAccountButton;
    @FXML
    private CheckBox mostraPasswordCheckBox;
    @FXML
    private Label IDutenteLabel;
    @FXML
    private TextField usernameTextField, emailTextField, nomeTextField, cognomeTextField, telefonoTextField, indirizzoTextField, mostraPasswordTextField;
    @FXML
    private PasswordField passwordField;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        setMenu(menuPane, blackPane, menuImageView);

        nascondiTutto();
        mostraTransition(homePane);
        try {
            aggiornaListaRitiri();
            aggiornaListaPromozioni();
            aggiornaListaRecensioni();
        } catch (SQLException e) {
            createErrorAlert(e.getMessage());
        }
        setDatiAccount();
        bentornatoText.setText("Bentornato, " + ControllerCliente.getInstance().getCliente().getUsername() + "!");

    }

    @FXML
    private void nascondiMenu() {
        nascondiMenu(blackPane, menuPane);
    }


    private void nascondiTutto() {
        nascondiTutto(homePane, ritiriPane, accountPane, impostazioniPane);
        prodottiPane.setVisible(false);
        promozioniPane.setVisible(false);
        recensioniPane.setVisible(false);
        prodottiAccordion.setExpandedPane(null);
        ritiriAccordion.setExpandedPane(null);
        promozioniAccordion.setExpandedPane(null);
        recensioniAccordion.setExpandedPane(null);
    }

    @FXML
    @Override
    public void mostraHome() {
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
    public void aggiornaListaProdotti() throws SQLException {
        prodottiAccordion.getPanes().clear();
        List<ProdottoInterface> listaProdotti = ControllerCliente.getInstance().getProdotti(ricercaProdottoTextField.getText());
        listaProdotti.forEach(prodotto -> {
            prodottiAccordion.getPanes().add(new TitledPane(prodotto.getNome(), Utils.getInstance().getProdottoAnchorPane(prodotto)));
        });
    }

    //RITIRI-----------------------------------------------------------------------------------------------------

    @FXML
    @Override
    public void mostraRitiri() {
        nascondiMenu();
        nascondiTutto();
        mostraTransition(ritiriPane);
    }

    public void aggiornaListaRitiri() throws SQLException {
        ritiriAccordion.getPanes().clear();
        List<RitiroInterface> listaRitiri = ControllerCliente.getInstance().getRitiri();
        listaRitiri.forEach(ritiro -> {
            ritiriAccordion.getPanes().add(new TitledPane(ritiro.getID() + " " + ritiro.getDestinazione(),
                    Utils.getInstance().getRitiroAnchorPaneCliente(ritiro)));
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

    public void aggiornaListaPromozioni() throws SQLException {
        promozioniAccordion.getPanes().clear();
        List<PromozioneInterface> listaPromozioni = ControllerCliente.getInstance().getPromozioni();
        listaPromozioni.forEach(promo -> promozioniAccordion.getPanes().add(new TitledPane(promo.getNome(),
                Utils.getInstance().getPromozioneAnchorPane(promo))));
        if (promozioniAccordion.getPanes().isEmpty())
            elencoPromozioniLabel.setText("Nessuna promozione attiva.");
        else elencoPromozioniLabel.setText("Elenco delle promozioni attive:");
    }

    //RECENSIONI--------------------------------------------------------------------------------------------------

    @FXML
    private void mostraRecensioni() {
        nascondiMenu();
        nascondiTutto();
        mostraTransition(recensioniPane);
    }

    public void aggiornaListaRecensioni() throws SQLException {
        recensioniAccordion.getPanes().clear();
        List<RecensioneInterface> listaRecensioni = ControllerCliente.getInstance().getRecensioni();
        listaRecensioni.forEach(r -> recensioniAccordion.getPanes().add(new TitledPane(r.getTitolo(),
                Utils.getInstance().getRecensioneAnchorPane(r))));
        if (recensioniAccordion.getPanes().isEmpty())
            elencoPromozioniLabel.setText("Nessuna recensione pubblicata.");
        else elencoPromozioniLabel.setText("Elenco delle recensioni pubblicate:");
    }

    @FXML
    private void aggiungiRecensione() throws IOException {
        startWindow("Pubblica una recensione", "/pubblicaRecensione.fxml", CreaRecensione.getInstance());
    }

    @FXML
    private void modificaRecensione() {
        try {
            Utils.getInstance().controllaAccordion(recensioniAccordion, "prodotto");
            AnchorPane anchorPane = (AnchorPane) recensioniAccordion.getExpandedPane().getContent();
            Label ID = (Label) anchorPane.getChildren().get(1);
            TextField nome = (TextField) anchorPane.getChildren().get(3);
            TextArea descrizione = (TextArea) anchorPane.getChildren().get(5);

            Node child = anchorPane.getChildren().get(7);
            VotoRecensioni votoRecensione;

            if (child instanceof ChoiceBox) {
                ChoiceBox votoRecensioneChoicebox = (ChoiceBox) child;
                votoRecensione = VotoRecensioni.valueOf(votoRecensioneChoicebox.getValue().toString());
            } else throw new IllegalArgumentException("Not a Choicebox");

            ControllerCliente.getInstance().modificaRecensione(nome.getText(), descrizione.getText(), votoRecensione, ID.getText());
            aggiornaListaRecensioni();

        } catch (IllegalArgumentException | SQLException e) {
            createErrorAlert(e.getMessage());
        }

    }

    @FXML
    private void eliminaRecensione() {
        try {
            Utils.getInstance().controllaAccordion(recensioniAccordion, "recensione");
            if (createConfirmationAlert("Sei sicuro di voler eliminare la recensione selezionata?")) {
                ControllerCliente.getInstance().rimuoviRecensione(Utils.getInstance().getExpandedItemID(recensioniAccordion, 1));

            }
            aggiornaListaRecensioni();
        } catch (IllegalArgumentException | SQLException e) {
            createErrorAlert(e.getMessage());
        }
    }

    //ACCOUNT-----------------------------------------------------------------------------------------------------

    @FXML
    @Override
    public void mostraAccount() {
        nascondiMenu();
        nascondiTutto();
        mostraTransition(accountPane);
    }

    private void setDatiAccount() {
        IDutenteLabel.setText("ID UTENTE: " + ControllerCliente.getInstance().getCliente().getID());
        usernameTextField.setText(ControllerCliente.getInstance().getCliente().getUsername());
        passwordField.setText(ControllerCliente.getInstance().getCliente().getPassword());
        emailTextField.setText(ControllerCliente.getInstance().getCliente().getEmail());
        nomeTextField.setText(ControllerCliente.getInstance().getCliente().getNome());
        cognomeTextField.setText(ControllerCliente.getInstance().getCliente().getCognome());
        if (!Objects.isNull(ControllerCliente.getInstance().getCliente().getTelefono()))
            telefonoTextField.setText(ControllerCliente.getInstance().getCliente().getTelefono());
        if (!Objects.isNull(ControllerCliente.getInstance().getCliente().getIndirizzo()))
            indirizzoTextField.setText(ControllerCliente.getInstance().getCliente().getIndirizzo());
    }

    @FXML
    private void mostraPassword() {
        mostraPassword(mostraPasswordCheckBox, passwordField, mostraPasswordTextField);
    }

    @FXML
    private void salvaModifiche() throws SQLException {
        ControllerCliente.getInstance().modificaCliente(usernameTextField.getText(),
                getPassword(), emailTextField.getText(), nomeTextField.getText(), cognomeTextField.getText(),
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
            ControllerCliente.getInstance().logout();
            close(logoutButton);
            startWindow("C3 v1.0", "/loginC3.fxml", LoginC3Controller.getInstance());
        }
    }

    @FXML
    private void eliminaAccount() throws SQLException, IOException {
        if (createConfirmationAlert("Sei sicuro di voler eliminare l'account?\nL'operazione sara' irreversibile.")) {
            ControllerCliente.getInstance().eliminaAccount();
            close(logoutButton);
            startWindow("C3 v1.0", "/loginC3.fxml", LoginC3Controller.getInstance());
        }
    }

    @FXML
    @Override
    public void mostraImpostazioni() {
        nascondiMenu();
        nascondiTutto();
        mostraTransition(impostazioniPane);
    }
}

