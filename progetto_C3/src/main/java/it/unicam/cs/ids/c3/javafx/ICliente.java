package it.unicam.cs.ids.c3.javafx;

import it.unicam.cs.ids.c3.controller.ControllerCliente;
import it.unicam.cs.ids.c3.model.*;
import javafx.beans.property.SimpleObjectProperty;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;

import java.io.IOException;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Objects;

public class ICliente implements JavaFXController {
    private static ICliente instance;

    private ICliente() {
    }

    public static ICliente getInstance() {
        if (Objects.isNull(instance))
            instance = new ICliente();
        return instance;
    }

    @FXML
    Label nomeCognomeLabel;
    @FXML
    TableView<Promozione> tabellaPromozioni;
    @FXML
    TableColumn<Promozione, String> IDColonna;
    @FXML
    TableColumn<Promozione, String> nomeColonna;
    @FXML
    TableColumn<Promozione, String> descrizioneColonna;
    @FXML
    TableColumn<Promozione, String> dataInizioColonna;
    @FXML
    TableColumn<Promozione, String> dataScadenzaColonna;
    @FXML
    ImageView aggiornaButton;
    @FXML
    Button aggiungiRecensione;
    @FXML
    Button modificaRecensione;
    @FXML
    Button eliminaRecensione;
    @FXML
    TableView<Recensione> tabellaRecensioni;
    @FXML
    TableColumn<Recensione, String> IDColonnaRec;
    @FXML
    TableColumn<Recensione, String> titoloColonnaRec;
    @FXML
    TableColumn<Recensione, String> testoColonnaRec;
    @FXML
    TableColumn<Recensione, String> prodottoColonnaRec;
    @FXML
    TableColumn<Recensione, String> commercianteColonnaRec;
    @FXML
    TableColumn<Recensione, String> valutazioneColonnaRec;
    @FXML
    TextField ricerca;
    @FXML
    RadioButton corriere;
    @FXML
    RadioButton commerciante;
    @FXML
    RadioButton punto_ritiro;
    @FXML
    TableView<Commerciante> tabCommercianti;
    @FXML
    TableView<Corriere> tabCorrieri;
    @FXML
    TableView<PuntoRitiro> tabPuntiRitiro;
    @FXML
    TableColumn<Commerciante, String> IDComm;
    @FXML
    TableColumn<Commerciante, String> ragSocComm;
    @FXML
    TableColumn<Commerciante, String> indirizzoComm;
    @FXML
    TableColumn<Commerciante, String> telComm;
    @FXML
    TableColumn<Corriere, String> IDCorr;
    @FXML
    TableColumn<Corriere, String> ragSocCorr;
    @FXML
    TableColumn<Corriere, String> indirizzoCorr;
    @FXML
    TableColumn<Corriere, String> telCorr;
    @FXML
    TableColumn<PuntoRitiro, String> IDPunto;
    @FXML
    TableColumn<PuntoRitiro, String> ragSocPunto;
    @FXML
    TableColumn<PuntoRitiro, String> indirizzoPunto;
    @FXML
    TableColumn<PuntoRitiro, String> telPunto;
    @FXML
    TableView<Ritiro> tabOrdini;
    @FXML
    TableColumn<Ritiro, String> IDOrdine;
    @FXML
    TableColumn<Ritiro, String> commOrdine;
    @FXML
    TableColumn<Ritiro, String> dataOrdine;
    @FXML
    TableColumn<Ritiro, String> destOrdine;
    @FXML
    TableColumn<Ritiro, String> trackOrdine;
    @FXML
    Button logout;
    @FXML
    Button aggiornaPromozioni;
    @FXML
    Button aggiornaRecensioni;
    @FXML
    Button aggiornaRicerche;
    @FXML
    Button aggiornaOrdini;
    @FXML
    Button cercaUtente;
    @FXML
    TextField ricercaTextField;


    @FXML
    public void aggiornaPromozioni() throws SQLException {
        riempiTabellaPromozioni();
        tabellaPromozioni.getItems().clear();
        tabellaPromozioni.getItems().addAll(ControllerCliente.getInstance().cercaPromozioni());

    }

    private void riempiTabellaPromozioni() {
        IDColonna.setCellValueFactory(promozione -> new SimpleObjectProperty<>(promozione.getValue().getID()));
        nomeColonna.setCellValueFactory(promozione -> new SimpleObjectProperty<>(promozione.getValue().getNome()));
        descrizioneColonna.setCellValueFactory(promozione -> new SimpleObjectProperty<>(promozione.getValue().getDescrizione()));
        dataInizioColonna.setCellValueFactory(promozione -> new SimpleObjectProperty<>(new SimpleDateFormat("dd-MM-yyyy").format(promozione.getValue().getDataInizio().getTime())));
        dataScadenzaColonna.setCellValueFactory(promozione -> new SimpleObjectProperty<>(new SimpleDateFormat("dd-MM-yyyy").format(promozione.getValue().getDataScadenza().getTime())));
    }

    @FXML
    public void aggiornaRecensioni() throws SQLException {
        riempiTabellaRecensioni();
        tabellaRecensioni.getItems().clear();
        tabellaRecensioni.getItems().addAll(ControllerCliente.getInstance().cercaRecensioni());

    }

    private void riempiTabellaRecensioni() {
        IDColonnaRec.setCellValueFactory(recensione -> new SimpleObjectProperty<>(recensione.getValue().getID()));
        titoloColonnaRec.setCellValueFactory(recensione -> new SimpleObjectProperty<>(recensione.getValue().getTitolo()));
        testoColonnaRec.setCellValueFactory(recensione -> new SimpleObjectProperty<>(recensione.getValue().getTesto()));
        prodottoColonnaRec.setCellValueFactory(recensione -> new SimpleObjectProperty<>(recensione.getValue().getIDProdotto()));
        commercianteColonnaRec.setCellValueFactory(recensione -> new SimpleObjectProperty<>(recensione.getValue().getIDCommerciante()));
        valutazioneColonnaRec.setCellValueFactory(recensione -> new SimpleObjectProperty<>(recensione.getValue().getVotoRecensioni().toString()));
    }

    @FXML
    public void aggiornaRicerche() throws SQLException {
        aggiornaRicercaCommercianti();
        aggiornaRicercaCorrieri();
        aggiornaRicercaPuntiRitiro();
    }

    private void aggiornaRicercaCommercianti() throws SQLException {
        riempiTabellaRicercaCommercianti();
        tabCommercianti.getItems().clear();
        tabCommercianti.getItems().addAll(ControllerCliente.getInstance().cercaCommercianti());
    }

    private void riempiTabellaRicercaCommercianti() {
        IDComm.setCellValueFactory(commerciante -> new SimpleObjectProperty<>(commerciante.getValue().getID()));
        ragSocComm.setCellValueFactory(commerciante -> new SimpleObjectProperty<>(commerciante.getValue().getRagioneSociale()));
        indirizzoComm.setCellValueFactory(commerciante -> new SimpleObjectProperty<>(commerciante.getValue().getIndirizzo()));
        telComm.setCellValueFactory(commerciante -> new SimpleObjectProperty<>(commerciante.getValue().getTelefono()));
    }

    private void aggiornaRicercaCorrieri() throws SQLException {
        riempiTabellaRicercaCorrieri();
        tabCorrieri.getItems().clear();
        tabCorrieri.getItems().addAll(ControllerCliente.getInstance().cercaCorrieri());
    }

    private void riempiTabellaRicercaCorrieri() {
        IDCorr.setCellValueFactory(corriere -> new SimpleObjectProperty<>(corriere.getValue().getID()));
        ragSocCorr.setCellValueFactory(corriere -> new SimpleObjectProperty<>(corriere.getValue().getRagioneSociale()));
        indirizzoCorr.setCellValueFactory(corriere -> new SimpleObjectProperty<>(corriere.getValue().getIndirizzo()));
        telCorr.setCellValueFactory(corriere -> new SimpleObjectProperty<>(corriere.getValue().getTelefono()));
    }

    private void aggiornaRicercaPuntiRitiro() throws SQLException {
        riempiTabellaRicercaPuntiRitiro();
        tabPuntiRitiro.getItems().clear();
        tabPuntiRitiro.getItems().addAll(ControllerCliente.getInstance().cercaPuntiRitiro());
    }

    private void riempiTabellaRicercaPuntiRitiro() {
        IDPunto.setCellValueFactory(puntoRitiro -> new SimpleObjectProperty<>(puntoRitiro.getValue().getID()));
        ragSocPunto.setCellValueFactory(puntoRitiro -> new SimpleObjectProperty<>(puntoRitiro.getValue().getRagioneSociale()));
        indirizzoPunto.setCellValueFactory(puntoRitiro -> new SimpleObjectProperty<>(puntoRitiro.getValue().getIndirizzo()));
        telPunto.setCellValueFactory(puntoRitiro -> new SimpleObjectProperty<>(puntoRitiro.getValue().getTelefono()));
    }

    @FXML
    public void aggiornaOrdini() throws SQLException {
        riempiTabellaOrdini();
        tabOrdini.getItems().clear();
        tabOrdini.getItems().addAll(ControllerCliente.getInstance().cercaOrdini());

    }

    private void riempiTabellaOrdini() {
        IDOrdine.setCellValueFactory(ordine -> new SimpleObjectProperty<>(ordine.getValue().getID()));
        commOrdine.setCellValueFactory(ordine -> new SimpleObjectProperty<>(ordine.getValue().getIDCommerciante()));
        dataOrdine.setCellValueFactory(ordine -> new SimpleObjectProperty<>(new SimpleDateFormat("dd-MM-yyyy").format(ordine.getValue().getDataPrenotazione().getTime())));
        destOrdine.setCellValueFactory(ordine -> new SimpleObjectProperty<>(ordine.getValue().getDestinazione()));
        trackOrdine.setCellValueFactory(ordine -> new SimpleObjectProperty<>(ordine.getValue().getStatoTracking().toString()));
    }

    @FXML
    public void cercaUtente() throws SQLException {
        if (corriere.isSelected()) {
            riempiTabellaRicercaCorrieri();
            tabCorrieri.getItems().clear();
            //if (!Objects.isNull(ControllerCliente.getInstance().cercaCorriereDalTextField(ricercaTextField.getText())))
            tabCorrieri.getItems().addAll(ControllerCliente.getInstance().cercaCorriereDalTextField(ricercaTextField.getText()));
            //else ricercaTextField.setText("errore");
        } else if (commerciante.isSelected()) {
            riempiTabellaRicercaCommercianti();
            tabCommercianti.getItems().clear();
            tabCommercianti.getItems().addAll(ControllerCliente.getInstance().cercaCommercianteDalTextField(ricercaTextField.getText()));
        } else if (punto_ritiro.isSelected()) {
            riempiTabellaRicercaPuntiRitiro();
            tabPuntiRitiro.getItems().clear();
            tabPuntiRitiro.getItems().addAll(ControllerCliente.getInstance().cercaPuntoRitiroDalTextField(ricercaTextField.getText()));
        }
    }

    @FXML
    private void setLabelBenvenuto() {
        nomeCognomeLabel.setText(ControllerCliente.getInstance().getCliente().getNome().toUpperCase() + " " + ControllerCliente.getInstance().getCliente().getCognome().toUpperCase());
    }

    @FXML
    private void logout() throws IOException {
        ControllerCliente.getInstance().logout();
        close(aggiornaPromozioni);
        startWindow("C3 v1.0", "/loginC3_2.fxml", LoginC3Controller.getInstance());

    }
}

