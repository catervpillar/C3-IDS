package it.unicam.cs.ids.c3.recensione;

import it.unicam.cs.ids.c3.prodotto.Prodotto;
import it.unicam.cs.ids.c3.promozione.Promozione;
import it.unicam.cs.ids.c3.utenti.commerciante.Commerciante;
import it.unicam.cs.ids.c3.utilities.HasID;
import it.unicam.cs.ids.c3.utenti.cliente.Cliente;
import it.unicam.cs.ids.c3.utilities.IDGenerator;

import java.util.GregorianCalendar;
import java.util.Objects;

/**
 * Questa classe ha la responsabilità di gestire una singola {@link Recensione}.
 */
public class Recensione implements HasID, RecensioneInterface {
    private String ID;
    private String titolo;
    private String testo;
    private String IDCliente;
    private String IDCommerciante;
    private String IDProdotto;
    private VotoRecensioni votoRecensioni;
    private GregorianCalendar data;

    /**
     * Costruttore utilizzato per la creazione di una {@link Recensione}.
     *
     * @param titolo - titolo della {@link Recensione}
     * @param testo - testo della {@link Recensione}
     * @param IDCliente - ID del {@link Cliente} associato alla {@link Recensione}
     * @param IDCommerciante - ID del {@link Commerciante} associato alla {@link Recensione}
     * @param IDProdotto - ID del {@link Prodotto} associato alla {@link Recensione}
     * @param votoRecensioni - voto della {@link Recensione} (da a 1 a 5 stelle)
     */
    public Recensione(String titolo, String testo, String IDCliente, String IDCommerciante, String IDProdotto, VotoRecensioni votoRecensioni) {
        IDGenerator.generateID(this);
        this.titolo = titolo;
        this.testo = testo;
        this.IDCliente = IDCliente;
        this.IDCommerciante = IDCommerciante;
        this.IDProdotto = IDProdotto;
        this.votoRecensioni = votoRecensioni;
        this.data = new GregorianCalendar();
    }

    /**
     * Costruisce una {@link Recensione} senza generare l'ID; utilizzato quando si deserializza dal database
     * @param ID - ID della {@link Recensione}
     * @param titolo - tiolo della {@link Recensione}
     * @param testo - testo della {@link Recensione}
     * @param IDCliente - ID del {@link Cliente} associato alla {@link Recensione}
     * @param IDCommerciante - ID del {@link Commerciante} associato alla {@link Recensione}
     * @param IDProdotto - ID del {@link Prodotto} associato alla {@link Recensione}
     * @param votoRecensioni - voto della {@link Recensione} (da 1 a 5 stelle)
     */
    public Recensione(String ID, String titolo, String testo, String IDCliente, String IDCommerciante, String IDProdotto, VotoRecensioni votoRecensioni) {
        this.ID = ID;
        this.titolo = titolo;
        this.testo = testo;
        this.IDCliente = IDCliente;
        this.IDCommerciante = IDCommerciante;
        this.IDProdotto = IDProdotto;
        this.votoRecensioni = votoRecensioni;
        this.data = new GregorianCalendar();
    }

    /**
     * Metodo getter per l'attributo dell'ID della {@link Recensione}.
     *
     * @return l'ID della {@link Recensione}
     */
    @Override
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
     * Metodo getter per l'attributo del titolo della {@link Recensione}.
     *
     * @return il titolo della {@link Recensione}
     */
    public String getTitolo() {
        return titolo;
    }

    /**
     * Metodo setter per l'attributo del titolo della {@link Promozione}.
     *
     * @param titolo - il nuovo titolo della {@link Promozione}
     */
    public void setTitolo(String titolo) {
        this.titolo = titolo;
    }

    /**
     * Metodo getter per l'attributo del testo della {@link Recensione}.
     *
     * @return il testo della {@link Recensione}
     */
    public String getTesto() {
        return testo;
    }

    /**
     * Metodo setter per l'attributo del testo della {@link Promozione}.
     *
     * @param testo - il nuovo testo della {@link Promozione}
     */
    public void setTesto(String testo) {
        this.testo = testo;
    }

    /**
     * Metodo getter per l'attributo dell'ID del {@link Cliente} associato alla {@link Recensione}.
     *
     * @return l'ID del {@link Cliente} associato alla {@link Recensione}
     */
    public String getIDCliente() {
        return IDCliente;
    }

    /**
     * Metodo setter per l'attributo dell'ID del {@link Cliente} associato {@link Promozione}.
     *
     * @param IDCliente - il nuovo ID del {@link Cliente} associato alla {@link Promozione}
     */
    public void setIDCliente(String IDCliente) {
        this.IDCliente = IDCliente;
    }

    /**
     * Metodo getter per l'attributo dell'ID del {@link Commerciante} associato alla {@link Recensione}.
     *
     * @return l'ID del {@link Commerciante} associato alla {@link Recensione}
     */
    public String getIDCommerciante() {
        return IDCommerciante;
    }

    /**
     * Metodo setter per l'attributo dell'ID del {@link Commerciante} associato alla {@link Promozione}.
     *
     * @param IDCommerciante - il nuovo ID del {@link Commerciante} associato alla {@link Promozione}
     */
    public void setIDCommerciante(String IDCommerciante) {
        this.IDCommerciante = IDCommerciante;
    }

    /**
     * Metodo getter per l'attributo dell'ID del {@link Prodotto} associato alla {@link Recensione}.
     *
     * @return l'ID del {@link Prodotto} associato alla {@link Recensione}
     */
    public String getIDProdotto() {
        return IDProdotto;
    }

    /**
     * Metodo setter per l'attributo dell'ID del {@link Prodotto} associato alla {@link Promozione}.
     *
     * @param IDProdotto - il nuovo ID del {@link Prodotto} associato alla {@link Promozione}
     */
    public void setIDProdotto(String IDProdotto) {
        this.IDProdotto = IDProdotto;
    }

    /**
     * Metodo getter per l'attributo della data della {@link Recensione}.
     *
     * @return la data della {@link Recensione}
     */
    public GregorianCalendar getData() {
        return data;
    }

    /**
     * Metodo setter per l'attributo della data della {@link Promozione}.
     *
     * @param data - la nuova data della {@link Promozione}
     */
    public void setData(GregorianCalendar data) {
        this.data = data;
    }

    /**
     * Metodo getter per l'attributo del voto della {@link Recensione}.
     *
     * @return il voto della {@link Recensione}
     */
    public VotoRecensioni getVotoRecensioni() {
        return votoRecensioni;
    }

    /**
     * Metodo setter per l'attributo del voto della {@link Promozione} (da 1 a 5 stelle).
     *
     * @param votoRecensioni - il nuovo voto della {@link Promozione}
     */
    public void setVotoRecensioni(VotoRecensioni votoRecensioni) {
        this.votoRecensioni = votoRecensioni;
    }

    /**
     * Metodo equals di una {@link Recensione}.
     *
     * @param o - oggetto da confrontare
     * @return true se le promozioni sono uguali, false altrimenti-
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Recensione that = (Recensione) o;
        return ID.equals(that.ID);
    }

    /**
     * Metodo hasCode di una {@link Recensione}
     *
     * @return l'hasCode
     */
    @Override
    public int hashCode() {
        return Objects.hash(ID);
    }

    /**
     * Metodo toString della {@link Recensione}.
     *
     * @return l'ID, titolo, testo, ID del {@link Cliente}, ID del {@link Commerciante}, ID del {@link Prodotto}, il voto e la data della {@link Recensione}
     */
    @Override
    public String toString() {
        return "Recensione{" +
                "ID='" + ID + '\'' +
                ", titolo='" + titolo + '\'' +
                ", testo='" + testo + '\'' +
                ", IDCliente='" + IDCliente + '\'' +
                ", IDCommerciante='" + IDCommerciante + '\'' +
                ", IDProdotto='" + IDProdotto + '\'' +
                ", votoRecensioni=" + votoRecensioni +
                ", data=" + data +
                '}';
    }
}
