package it.unicam.cs.ids.c3.promozione;

import it.unicam.cs.ids.c3.prodotto.Prodotto;
import it.unicam.cs.ids.c3.utilities.HasID;
import it.unicam.cs.ids.c3.utilities.IDGenerator;
import it.unicam.cs.ids.c3.utenti.commerciante.Commerciante;

import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Objects;

/**
 * Questa classe ha la responsabilità di gestire una singola {@link Promozione}.
 */
public class Promozione implements HasID, PromozioneInterface{
    private String ID;
    private String nome;
    private String descrizione;
    private String IDCommerciante;
    private List<String> listaIDProdotti = new ArrayList<>();
    private GregorianCalendar dataInizio;
    private GregorianCalendar dataScadenza;

    /**
     * Costruttore utilizzato per la creazione di una {@link Promozione}.
     *
     * @param nome - Il nome della {@link Promozione}
     * @param IDCommerciante - l'ID del {@link Commerciante} associato alla {@link Promozione}
     * @param descrizione - la descrizione della {@link Promozione}
     * @param dataInizio - data d'inizio della {@link Promozione}
     * @param dataScadenza - data di scadenza della {@link Promozione}
     */
    public Promozione(String nome, String IDCommerciante, String descrizione, GregorianCalendar dataInizio, GregorianCalendar dataScadenza) {
        IDGenerator.generateID(this);
        this.nome = nome;
        this.IDCommerciante = IDCommerciante;
        this.descrizione = descrizione;
        this.dataInizio = dataInizio;
        this.dataScadenza = dataScadenza;
    }

    /**
     * Costruisce una {@link Promozione} senza generare l'ID; utilizzato quando si deserializza dal database
     *
     * @param ID - ID della {@link Promozione}
     * @param nome - il nome della {@link Promozione}
     * @param IDCommerciante - l'ID del {@link Commerciante} associato alla {@link Promozione}
     * @param descrizione - la descrizione della {@link Promozione}
     * @param dataInizio - la data d'inizio della {@link Promozione}
     * @param dataScadenza - la data di scadenza della {@link Promozione}
     */
    public Promozione(String ID, String nome, String IDCommerciante, String descrizione, GregorianCalendar dataInizio, GregorianCalendar dataScadenza) {
        this.ID = ID;
        this.nome = nome;
        this.IDCommerciante = IDCommerciante;
        this.descrizione = descrizione;
        this.dataInizio = dataInizio;
        this.dataScadenza = dataScadenza;
    }

    /**
     * Metodo getter per l'attributo dell'ID della {@link Promozione}.
     *
     * @return l'ID della {@link Promozione}
     */
    public String getID() {
        return ID;
    }

    /**
     * Metodo setter per l'attributo dell'ID della {@link Promozione}.
     *
     * @param ID - il nuovo ID della {@link Promozione}
     */
    public void setID(String ID) {
        this.ID = ID;
    }

    /**
     * Metodo getter per l'attributo dell'ID del {@link Commerciante} associato alla {@link Promozione}.
     *
     * @return l'ID del {@link Commerciante} associato alla {@link Promozione}
     */
    public String getIDCommerciante() {
        return IDCommerciante;
    }

    /**
     * Metodo setter per l'attributo dell'ID del {@link Commerciante} associato alla {@link Promozione}
     *
     * @param IDCommerciante - il nuovo ID del {@link Commerciante} associato alla {@link Promozione}
     */
    public void setIDCommerciante(String IDCommerciante) {
        this.IDCommerciante = IDCommerciante;
    }

    /**
     * Metodo getter per l'attributo della descrizione della {@link Promozione}.
     *
     * @return la descrizione della {@link Promozione}
     */
    public String getDescrizione() {
        return descrizione;
    }

    /**
     * Metodo setter per l'attributo della descrizione della {@link Promozione}.
     *
     * @param descrizione - la nuova descrizione della {@link Promozione}
     */
    public void setDescrizione(String descrizione) {
        this.descrizione = descrizione;
    }

    /**
     * Metodo getter per la lista degli ID dei {@link Prodotto} coinvolti nella  {@link Promozione}.
     *
     * @return la lista degli ID dei {@link Prodotto} coinvolti {@link Promozione}
     */
    public List<String> getListaIDProdotti() {
        return listaIDProdotti;
    }

    /**
     * Metodo setter per la lista degli ID dei {@link Prodotto} coinvolti nella {@link Promozione}.
     *
     * @param listaIDProdotti - la nuova lista degli ID dei {@link Prodotto} coivolti nella {@link Promozione}
     */
    public void setListaIDProdotti(List<String> listaIDProdotti) {
        this.listaIDProdotti = listaIDProdotti;
    }

    /**
     * Metodo getter per l'attributo del nome della {@link Promozione}.
     *
     * @return il nome della {@link Promozione}
     */
    public String getNome() {
        return nome;
    }

    /**
     * Metodo setter per l'attributo del nome della {@link Promozione}.
     *
     * @param nome - il nuovo nome della {@link Promozione}
     */
    public void setNome(String nome) {
        this.nome = nome;
    }

    /**
     * Metodo getter per l'attributo della data d'inizio della {@link Promozione}.
     *
     * @return la data d'inizio della {@link Promozione}
     */
    public GregorianCalendar getDataInizio() {
        return dataInizio;
    }

    /**
     * Metodo setter per l'attributo della data d'inizio della {@link Promozione}.
     *
     * @param dataInizio - la nuova data d'inizio della {@link Promozione}
     */
    public void setDataInizio(GregorianCalendar dataInizio) {
        this.dataInizio = dataInizio;
    }

    /**
     * Metodo getter per l'attributo della data di scadenza della {@link Promozione}.
     *
     * @return la data di scadenza della {@link Promozione}
     */
    public GregorianCalendar getDataScadenza() {
        return dataScadenza;
    }

    /**
     * Metodo setter per l'attributo della data di scadenza della {@link Promozione}.
     *
     * @param dataScadenza - la nuova data di scadenza della {@link Promozione}
     */
    public void setDataScadenza(GregorianCalendar dataScadenza) {
        this.dataScadenza = dataScadenza;
    }

    /**
     * Metodo equals di una {@link Promozione}.
     *
     * @param o - l'oggetto da confrontare
     * @return true se le promozioni sono uguali, false altrimenti
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Promozione that = (Promozione) o;
        return ID.equals(that.ID);
    }

    /**
     * Metodo hasCode di una {@link Promozione}
     *
     * @return l'hasCode
     */
    @Override
    public int hashCode() {
        return Objects.hash(ID);
    }

    /**
     * Metodo toString della {@link Promozione}.
     *
     * @return l'ID, nome, descrizione, ID del commerciante, la lista degli ID dei prodotti, la data d'inizio e la data di scadenza della {@link Promozione}.
     */
    @Override
    public String toString() {
        return "Promozione{" +
                "ID=" + ID +
                ", nome=" + nome +
                ", descrizione=" + descrizione +
                ", IDCommerciante=" + IDCommerciante +
                ", listaIDProdotti=" + listaIDProdotti +
                ", dataInizio=" + dataInizio +
                ", dataScadenza=" + dataScadenza +
                '}';
    }
}
