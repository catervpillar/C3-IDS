package it.unicam.cs.ids.c3.javafx;

import it.unicam.cs.ids.c3.prodotto.Prodotto;
import it.unicam.cs.ids.c3.promozione.Promozione;
import it.unicam.cs.ids.c3.recensione.Recensione;
import it.unicam.cs.ids.c3.recensione.VotoRecensioni;
import it.unicam.cs.ids.c3.ritiro.Ritiro;
import it.unicam.cs.ids.c3.ritiro.StatoTracking;
import javafx.collections.FXCollections;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Font;

import java.time.LocalDate;
import java.util.*;

public class Utils {
    private static Utils instance;

    private Utils() {
    }

    public static Utils getInstance() {
        if (Objects.isNull(instance))
            instance = new Utils();
        return instance;
    }

    private Label getLabel(String text, int font, int x, int y) {
        Label label = new Label(text);
        label.setFont(new Font(font));
        label.setLayoutX(x);
        label.setLayoutY(y);
        return label;
    }

    private ImageView getImageView(String url, int larghezza, int altezza, int x, int y) {
        Image image;
        if (!Objects.isNull(url) && !url.isBlank())
            image = new Image(url, larghezza, altezza, false, false);
        else
            image = new Image("https://bit.ly/3011Ztl", larghezza, altezza, false, false);
        ImageView imageView = new ImageView();
        imageView.setImage(image);
        imageView.setX(x);
        imageView.setY(y);
        return imageView;
    }

    private TextField getTextField(String text, int font, int x, int y) {
        TextField textField = new TextField(text);
        textField.setFont(new Font(font));
        textField.setLayoutX(x);
        textField.setLayoutY(y);
        return textField;
    }

    private TextArea getTextArea(String text, int font, int x, int y) {
        TextArea textArea = new TextArea(text);
        textArea.setFont(new Font(font));
        textArea.setLayoutX(x);
        textArea.setLayoutY(y);
        textArea.setMaxHeight(200);
        return textArea;
    }

    private DatePicker getDatePicker(GregorianCalendar data, int x, int y) {
        DatePicker datePicker = new DatePicker();
        datePicker.setValue(LocalDate.of(data.get(Calendar.YEAR),
                data.get(Calendar.MONTH) + 1,
                data.get(Calendar.DAY_OF_MONTH)));
        datePicker.setLayoutX(x);
        datePicker.setLayoutY(y);
        disabilitaDatePassate(datePicker);
        return datePicker;
    }

    private <T> ChoiceBox<T> getChoiceBox(List<T> listaValori, T valore, int x, int y) {
        ChoiceBox<T> choiceBox = new ChoiceBox<>();
        choiceBox.setItems(FXCollections.observableArrayList(listaValori));
        choiceBox.setValue(valore);
        choiceBox.setLayoutX(x);
        choiceBox.setLayoutY(y);
        return choiceBox;
    }

    private void disabilitaDatePassate(DatePicker datePicker) {
        datePicker.setDayCellFactory(picker -> new DateCell() {
            public void updateItem(LocalDate date, boolean empty) {
                super.updateItem(date, empty);
                LocalDate today = LocalDate.now();

                setDisable(empty || date.compareTo(today) < 0);
            }
        });
    }

    public AnchorPane getProdottoAnchorPane(Prodotto prodotto) {
        AnchorPane anchorPane = new AnchorPane();

        ImageView imageView = getImageView(prodotto.getURLImmagine(), 150, 150, 15, 15);

        Label nomeProdotto = getLabel("Nome prodotto: " + prodotto.getNome(), 16, 188, 15);
        Label IDprodotto = getLabel("ID prodotto: ", 16, 188, 40);
        Label ID = getLabel(prodotto.getID(), 16, 280, 40);
        Label prezzo = getLabel("Prezzo: " + "\u20AC" + prodotto.getPrezzo(), 16, 188, 65);
        Label disponibilita = getLabel("Disponibilita': " + prodotto.getQuantita(), 16, 188, 90);

        anchorPane.getChildren().addAll(nomeProdotto, IDprodotto, ID, prezzo, disponibilita, imageView);

        return anchorPane;
    }

    public AnchorPane getRitiroAnchorPaneCliente(Ritiro ritiro) {
        AnchorPane anchorPane = getRitiroAnchorPane(ritiro);
        Label statoTracking = getLabel("Stato tracking: " + ritiro.getStatoTracking().name(), 16, 25, 265);

        anchorPane.getChildren().addAll(statoTracking);

        return anchorPane;
    }

    public AnchorPane getRitiroAnchorPaneCorriere(Ritiro ritiro) {
        AnchorPane anchorPane = getRitiroAnchorPane(ritiro);
        Label statoTracking = getLabel("Stato tracking: ", 16, 25, 265);
        ChoiceBox<StatoTracking> stato = getChoiceBox(Arrays.asList(StatoTracking.values().clone()), ritiro.getStatoTracking(), 140, 265);
        anchorPane.getChildren().addAll(statoTracking, stato);

        return anchorPane;
    }

    public AnchorPane getRitiroAnchorPanePuntoRitiro(Ritiro ritiro) {
        AnchorPane anchorPane = getRitiroAnchorPane(ritiro);
        ((Label) anchorPane.getChildren().get(6)).setText("Ritirato dal cliente:");

        ArrayList<String> list = new ArrayList<>();
        list.add("RITIRATO");
        list.add("NON RITIRATO");

        ChoiceBox<String> choiceBox;
        if (ritiro.isRitirato())
            choiceBox = getChoiceBox(list, "RITIRATO", 160, 140);
        else choiceBox = getChoiceBox(list, "NON RITIRATO", 160, 140);

        Label statoTracking = getLabel("Stato tracking: " + ritiro.getStatoTracking().name(), 16, 25, 265);

        anchorPane.getChildren().addAll(choiceBox, statoTracking);
        return anchorPane;
    }

    private AnchorPane getRitiroAnchorPane(Ritiro ritiro) {
        AnchorPane anchorPane = new AnchorPane();

        Label IDRitiro = getLabel("ID ritiro: ", 16, 25, 15);
        Label ID = getLabel(ritiro.getID(), 16, 90, 15);
        Label destinazione = getLabel("Destinazione: " + ritiro.getDestinazione(), 16, 25, 40);
        Label codiceRitiro = getLabel("Codice ritiro: " + ritiro.getCodiceRitiro(), 16, 25, 65);

        Label dataPrenotazione = getLabel("Data prenotazione: " +
                ritiro.getDataPrenotazione().get(Calendar.DAY_OF_MONTH) + "/" +
                (ritiro.getDataPrenotazione().get(Calendar.MONTH) + 1) + "/" +
                ritiro.getDataPrenotazione().get(Calendar.YEAR), 16, 25, 90);

        Label dataConsegna;
        if (!Objects.isNull(ritiro.getDataConsegna()))
            dataConsegna = getLabel("Data consegna: " + ritiro.getDataConsegna().get(Calendar.DAY_OF_MONTH) + "/" +
                    (ritiro.getDataConsegna().get(Calendar.MONTH) + 1) + "/" +
                    ritiro.getDataConsegna().get(Calendar.YEAR), 16, 25, 115);
        else
            dataConsegna = getLabel("Data consegna: Non ancora consegnato", 16, 25, 115);

        Label ritirato;
        if (ritiro.isRitirato())
            ritirato = getLabel("Ritirato dal cliente: RITIRATO", 16, 25, 140);
        else
            ritirato = getLabel("Ritirato dal cliente: NON RITIRATO", 16, 25, 140);


        Label tipoConsegna = getLabel("Tipo consegna: ", 16, 25, 165);
        Label tipo = getLabel(ritiro.getTipoConsegna().name(), 16, 135, 165);
        Label IDClienteLabel = getLabel("ID cliente: ", 16, 25, 190);
        Label IDCliente = getLabel(ritiro.getIDCliente(), 16, 100, 190);
        Label IDCommercianteLabel = getLabel("ID commerciante: ", 16, 25, 215);
        Label IDCommerciante = getLabel(ritiro.getIDCommerciante(), 16, 155, 215);

        Label IDCorriere = getLabel("ID corriere: " + ritiro.getIDCorriere(), 16, 25, 240);

        anchorPane.getChildren().addAll(IDRitiro, ID, destinazione, codiceRitiro, dataPrenotazione,
                dataConsegna, ritirato, tipoConsegna, tipo, IDClienteLabel, IDCliente, IDCommercianteLabel, IDCommerciante, IDCorriere);

        return anchorPane;
    }

    public AnchorPane getPromozioneAnchorPane(Promozione promozione) {
        AnchorPane anchorPane = new AnchorPane();

        Label IDPromozione = getLabel("ID promozione: ", 13, 25, 15);
        Label ID = getLabel(promozione.getID(), 13, 142, 15);
        Label dataInizioLabel = getLabel("Data inizio: ", 13, 25, 120);
        Label nomeLabel = getLabel("Nome: ", 13, 25, 50);
        Label descrizioneLabel = getLabel("Descrizione: ", 13, 25, 85);
        Label dataScadenzaLabel = getLabel("Data scadenza: ", 13, 25, 155);

        DatePicker dataInizioDatePicker = getDatePicker(promozione.getDataInizio(), 140, 115);
        DatePicker dataScadenzaDatePicker = getDatePicker(promozione.getDataScadenza(), 140, 150);


        TextField nome = getTextField(promozione.getNome(), 13, 140, 45);
        TextField descrizione = getTextField(promozione.getDescrizione(), 13, 140, 80);

        anchorPane.getChildren().addAll(IDPromozione, ID, nomeLabel, nome, descrizioneLabel, descrizione, dataInizioLabel, dataInizioDatePicker, dataScadenzaLabel, dataScadenzaDatePicker);
        return anchorPane;
    }

    public AnchorPane getRecensioneAnchorPane(Recensione recensione) {
        AnchorPane anchorPane = new AnchorPane();

        Label IDRecensione = getLabel("ID recensione: ", 13, 25, 15);
        Label ID = getLabel(recensione.getID(), 13, 142, 15);
        Label titolo = getLabel("Titolo: ", 13, 25, 50);
        TextField titoloTextField = getTextField(recensione.getTitolo(), 13, 140, 45);
        Label testo = getLabel("Testo: ", 13, 25, 100);
        TextArea testoTextArea = getTextArea(recensione.getTesto(), 13, 140, 95);

        Label voto = getLabel("Voto: ", 13, 25, 305);
        ChoiceBox<VotoRecensioni> votoChoiceBox = getChoiceBox(Arrays.asList(VotoRecensioni.values().clone()), recensione.getVotoRecensioni(), 140, 300);

        anchorPane.getChildren().addAll(IDRecensione, ID, titolo, titoloTextField, testo, testoTextArea, voto, votoChoiceBox);

        return anchorPane;
    }

    public void controllaAccordion(Accordion accordion, String word) {
        TitledPane expandedPane = accordion.getExpandedPane();
        if (Objects.isNull(expandedPane))
            throw new IllegalArgumentException("Seleziona prima un " + word);
    }

    public String getExpandedItemID(Accordion accordion, int index) {
        AnchorPane anchorPane = (AnchorPane) accordion.getExpandedPane().getContent();
        Label ID = (Label) anchorPane.getChildren().get(index);
        return ID.getText();
    }
}
