package it.unicam.cs.ids.c3.prodotto;

import it.unicam.cs.ids.c3.utenti.cliente.Cliente;
import it.unicam.cs.ids.c3.utilities.HasID;
import it.unicam.cs.ids.c3.utilities.IDGenerator;
import it.unicam.cs.ids.c3.utenti.commerciante.Commerciante;

import java.util.Objects;

/**
 * Questa classe ha la responsabilità di gestire un singolo {@link Prodotto}.
 */

public class Prodotto implements HasID, ProdottoInterface {
    private String ID;
    private String nome;
    private double prezzo;
    private int quantita;
    private String IDCommerciante;
    private String URLImmagine;

    /**
     * Costruttore utilizzato per la creazione di un {@link Prodotto}.
     *
     * @param nome - Il nome del {@link Prodotto} da creare
     * @param prezzo - Il prezzo del {@link Prodotto} da creare
     * @param quantita - La quantita del {@link Prodotto} da creare
     * @param IDCommerciante - L'ID del {@link it.unicam.cs.ids.c3.utenti.commerciante.Commerciante} che intende aggiungere il nuovo {@link Prodotto}.
     * @param URLImmagine - L'URL dell'immagine rappresentante il nuovo {@link Prodotto}.
     */
    public Prodotto(String nome, double prezzo, int quantita, String IDCommerciante, String URLImmagine) {
        IDGenerator.generateID(this);
        this.nome = nome;
        this.prezzo = prezzo;
        this.quantita = quantita;
        this.IDCommerciante = IDCommerciante;
        this.URLImmagine = URLImmagine;
    }

    /**
     * Costruisce un {@link Prodotto} senza generare l'ID; utilizzato quando si deserializza dal database
     * @param ID - ID del {@link Prodotto}
     * @param nome - Il nome del {@link Prodotto}
     * @param prezzo - Il prezzo del {@link Prodotto}
     * @param quantita - La quantita del {@link Prodotto}
     * @param IDCommerciante - ID del {@link Commerciante} associato al {@link Prodotto}
     * @param URLImmagine - URL dell'immagine rappresentante il {@link Prodotto}
     */
    public Prodotto(String ID, String nome, double prezzo, int quantita, String IDCommerciante, String URLImmagine) {
        this.ID = ID;
        this.nome = nome;
        this.prezzo = prezzo;
        this.quantita = quantita;
        this.IDCommerciante = IDCommerciante;
        this.URLImmagine = URLImmagine;
    }

    /**
     * Metodo getter per l'attributo dell'ID del {@link Prodotto}.
     *
     * @return l'ID del {@link Prodotto}
     */
    @Override
    public String getID() {
        return ID;
    }

    /**
     * Metodo setter per l'attributo dell'ID del {@link Prodotto}.
     *
     * @param ID - il nuovo ID del {@link Prodotto}
     */
    @Override
    public void setID(String ID) {
        this.ID = ID;
    }

    /**
     * Metodo getter per l'attributo del nome del {@link Prodotto}.
     *
     * @return il nome del {@link Prodotto}
     */
    public String getNome() {
        return nome;
    }

    /**
     * Metodo setter per l'attributo del nome del {@link Prodotto}.
     *
     * @param nome - il nuovo nome del {@link Prodotto}
     */
    public void setNome(String nome) {
        this.nome = nome;
    }

    /**
     * Metodo getter per l'attributo dell'ID del {@link Commerciante} associato al {@link Prodotto}.
     *
     * @return l'ID del {@link Commerciante} associato al {@link Prodotto}
     */
    public String getIDCommerciante() {
        return IDCommerciante;
    }

    /**
     * Metodo setter per l'attributo dell'ID del {@link Commerciante} associato al {@link Prodotto}.
     *
     * @param IDCommerciante - il nuovo ID del {@link Commerciante} associato al {@link Prodotto}
     */
    public void setIDCommerciante(String IDCommerciante) {
        this.IDCommerciante = IDCommerciante;
    }

    /**
     * Metodo getter per l'attributo del prezzo del {@link Prodotto}.
     *
     * @return il prezzo del {@link Prodotto}
     */
    public double getPrezzo() {
        return prezzo;
    }

    /**
     * Metodo setter per l'attributo del prezzo del {@link Prodotto}.
     *
     * @param prezzo - il nuovo prezzo del {@link Prodotto}
     */
    public void setPrezzo(double prezzo) {
        this.prezzo = prezzo;
    }

    /**
     * Metodo getter per l'attributo della quantita del {@link Prodotto}.
     *
     * @return la quantita del {@link Prodotto}
     */
    public int getQuantita() {
        return quantita;
    }

    /**
     * Metodo setter per l'attributo della quantita del {@link Prodotto}.
     *
     * @param quantita - la nuova quantita del {@link Prodotto}
     */
    public void setQuantita(int quantita) {
        this.quantita = quantita;
    }

    /**
     * Metodo getter per l'attributo dell'URL dell'immagine rappresentatnte il {@link Prodotto}.
     *
     * @return l'URL dell'iummagine del {@link Prodotto}
     */
    public String getURLImmagine() {
        return URLImmagine;
    }

    /**
     * Metodo setter per l'attributo dell'URL dell'immagine rappresentante il {@link Prodotto}.
     *
     * @param URLImmagine - il nuovo URL dell'immagine rappresentante il {@link Prodotto}
     */
    public void setURLImmagine(String URLImmagine) {
        this.URLImmagine = URLImmagine;
    }

    /**
     * Metodo equals di un {@link Prodotto}.
     *
     * @param o - l'oggetto da confrontare
     * @return true se i prodotti sono uguali, false altrimenti
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Prodotto prodotto = (Prodotto) o;
        return ID.equals(prodotto.ID);
    }

    /**
     * Metodo hasCode di un {@link Prodotto}
     *
     * @return l'hasCode
     */
    @Override
    public int hashCode() {
        return Objects.hash(ID);
    }

    /**
     * Metodo toString del {@link Prodotto}.
     *
     * @return nome, prezzo e IDCommerciante del {@link Prodotto}.
     */
    @Override
    public String toString() {
        return nome + ", \u20AC" + prezzo + ", Commerciante: " + IDCommerciante;
    }
}
