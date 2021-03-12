package it.unicam.cs.ids.c3.javafx;

import it.unicam.cs.ids.c3.model.Prodotto;
import it.unicam.cs.ids.c3.model.Promozione;
import it.unicam.cs.ids.c3.model.Ritiro;
import javafx.scene.control.DateCell;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Font;

import java.time.LocalDate;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Objects;

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

    public AnchorPane getRitiroAnchorPane(Ritiro ritiro) {
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


        Label tipoConsegna = getLabel("Tipo consegna: " + ritiro.getTipoConsegna().name(), 16, 25, 165);
        Label IDCliente = getLabel("ID cliente: " + ritiro.getIDCliente(), 16, 25, 190);
        Label IDCorriere = getLabel("ID corriere: " + ritiro.getIDCorriere(), 16, 25, 215);
        Label statoTracking = getLabel("Stato tracking: " + ritiro.getStatoTracking().name(), 16, 25, 240);

        anchorPane.getChildren().addAll(IDRitiro, ID, destinazione, codiceRitiro, dataPrenotazione,
                dataConsegna, ritirato, tipoConsegna, IDCliente, IDCorriere, statoTracking);

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


        TextField nome = getTextField(promozione.getNome(),13,140,45);
        TextField descrizione = getTextField(promozione.getDescrizione(),13,140,80);

        anchorPane.getChildren().addAll(IDPromozione, ID, nomeLabel, nome, descrizioneLabel, descrizione, dataInizioLabel, dataInizioDatePicker, dataScadenzaLabel, dataScadenzaDatePicker);
        return anchorPane;
    }
}
