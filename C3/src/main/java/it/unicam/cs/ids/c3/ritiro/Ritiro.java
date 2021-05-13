package it.unicam.cs.ids.c3.ritiro;

import it.unicam.cs.ids.c3.promozione.Promozione;
import it.unicam.cs.ids.c3.recensione.Recensione;
import it.unicam.cs.ids.c3.utenti.cliente.Cliente;
import it.unicam.cs.ids.c3.utenti.corriere.Corriere;
import it.unicam.cs.ids.c3.utenti.commerciante.Commerciante;
import it.unicam.cs.ids.c3.utilities.CodiciRitiroGenerator;
import it.unicam.cs.ids.c3.utilities.HasID;
import it.unicam.cs.ids.c3.utilities.IDGenerator;
import it.unicam.cs.ids.c3.prodotto.Prodotto;

import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Objects;

/**
 * Questa classe ha la responsabilità di gestire un singolo {@link Ritiro}.
 */
public class Ritiro implements HasID, RitiroInterface {
    private String ID;
    private List<String> listaIDProdotti = new ArrayList<>();
    private String IDCommerciante;
    private String IDCliente;
    private String IDCorriere;
    private String destinazione;
    private String codiceRitiro;
    private GregorianCalendar dataPrenotazione;
    private GregorianCalendar dataConsegna;
    private boolean ritirato;
    private TipoConsegna tipoConsegna;
    private StatoTracking statoTracking;

    /**
     * Costruttore utilizzato per la creazione di un {@link Ritiro}.
     *
     * @param IDCommerciante - ID del {@link Commerciante} associato al {@link Ritiro}
     * @param IDCliente - ID del {@link Cliente} associato al {@link Ritiro}
     * @param IDCorriere - ID del {@link Corriere} associato al {@link Ritiro}
     * @param destinazione - destinazione del {@link Ritiro}
     * @param tipoConsegna - tipo della consegna del {@link Ritiro}
     */
    public Ritiro(String IDCommerciante, String IDCliente, String IDCorriere, String destinazione, TipoConsegna tipoConsegna) {
        IDGenerator.generateID(this);
        this.IDCommerciante = IDCommerciante;
        this.IDCliente = IDCliente;
        this.IDCorriere = IDCorriere;
        this.destinazione = destinazione;
        this.ritirato = false;
        this.dataPrenotazione = new GregorianCalendar();
        this.dataConsegna = null;
        this.codiceRitiro = CodiciRitiroGenerator.getInstance().generaCodice();
        this.tipoConsegna = tipoConsegna;
        this.statoTracking = StatoTracking.IN_ELABORAZIONE;
    }

    /**
     * Costruisce un {@link Ritiro} senza generare l'ID; utilizzato quando si deserializza dal database
     *
     * @param ID - ID del {@link Ritiro} da creare
     * @param IDCommerciante - ID del {@link Commerciante} associato al {@link Ritiro}
     * @param IDCliente - ID del {@link Cliente} associato al {@link Ritiro}
     * @param IDCorriere - ID del {@link Corriere} associato al {@link Ritiro}
     * @param destinazione - destinazione del {@link Ritiro}
     * @param tipoConsegna - tipo della consegna del {@link Ritiro}
     */
    public Ritiro(String ID, String IDCommerciante, String IDCliente, String IDCorriere, String destinazione, TipoConsegna tipoConsegna) {
        this.ID = ID;
        this.IDCommerciante = IDCommerciante;
        this.IDCliente = IDCliente;
        this.IDCorriere = IDCorriere;
        this.destinazione = destinazione;
        this.tipoConsegna = tipoConsegna;
    }

    /**
     * Metodo getter per l'attributo del tipo di consegna del {@link Ritiro}.
     *
     * @return il tipo di consegna del {@link Ritiro}
     */
    public TipoConsegna getTipoConsegna() {
        return tipoConsegna;
    }

    /**
     * Metodo setter per l'attributo del tipo di consegna del {@link Ritiro}.
     *
     * @param tipoConsegna - il nuovo tipo di consegna del {@link Ritiro}
     */
    public void setTipoConsegna(TipoConsegna tipoConsegna) {
        this.tipoConsegna = tipoConsegna;
    }

    /**
     * Metodo getter per l'attributo della data di consegna del {@link Ritiro}.
     *
     * @return la data di consegna del {@link Ritiro}
     */
    public GregorianCalendar getDataConsegna() {
        return dataConsegna;
    }

    /**
     * Metodo setter per l'attributo della data di consegna del {@link Ritiro}.
     *
     * @param dataConsegna - la nuova data di consegna del {@link Ritiro}
     */
    public void setDataConsegna(GregorianCalendar dataConsegna) {
        this.dataConsegna = dataConsegna;
    }

    /**
     * Metodo getter per indicare se il {@link Ritiro} e' stato ritirato
     *
     * @return true se il ritiro e' stato ritirato; false altrimenti
     */
    public boolean isRitirato() {
        return ritirato;
    }

    /**
     * Metodo setter per l'attributo ritirato del ritiro {@link Ritiro}.
     *
     * @param ritirato - il nuovo valore dell'attributo ritirato del {@link Ritiro}
     */
    public void setRitirato(boolean ritirato) {
        this.ritirato = ritirato;
    }

    /**
     * Metodo getter per l'attributo dell'ID del {@link Ritiro}.
     *
     * @return l'ID del {@link Ritiro}
     */
    public String getID() {
        return ID;
    }

    /**
     * Metodo setter per l'attributo dell'ID del {@link Ritiro}.
     *
     * @param ID - il nuovo ID del {@link Ritiro}
     */
    public void setID(String ID) {
        this.ID = ID;
    }

    /**
     * Metodo getter per l'attributo della data di prenotazione del {@link Ritiro}.
     *
     * @return la data di prenotazione del {@link Ritiro}
     */
    public GregorianCalendar getDataPrenotazione() {
        return dataPrenotazione;
    }

    /**
     * Metodo setter per l'attributo della data di prenotazione del {@link Ritiro}.
     *
     * @param dataPrenotazione - la nuova data di prenotazione del {@link Ritiro}
     */
    public void setDataPrenotazione(GregorianCalendar dataPrenotazione) {
        this.dataPrenotazione = dataPrenotazione;
    }

    /**
     * Metodo getter per la lista degli ID dei {@link Prodotto} associati al {@link Ritiro}.
     *
     * @return la lista degli ID dei {@link Prodotto} associati al {@link Ritiro}
     */
    public List<String> getListaIDProdotti() {
        return listaIDProdotti;
    }

    /**
     * Metodo setter per la lista degli ID dei {@link Prodotto} associati al {@link Ritiro}.
     *
     * @param listaIDProdotti - la nuova lista degli ID dei {@link Prodotto} associati al {@link Ritiro}
     */
    public void setListaIDProdotti(List<String> listaIDProdotti) {
        this.listaIDProdotti = listaIDProdotti;
    }

    /**
     * Metodo getter per l'attributo ID del {@link Commerciante} associato al {@link Ritiro}.
     *
     * @return l'ID del {@link Commerciante} associato al {@link Ritiro}
     */
    public String getIDCommerciante() {
        return IDCommerciante;
    }

    /**
     * Metodo setter per l'attributo dell'ID del {@link Commerciante} associato al {@link Ritiro}.
     *
     * @param IDCommerciante - il nuovo ID del {@link Commerciante} associato al{@link Ritiro}
     */
    public void setIDCommerciante(String IDCommerciante) {
        this.IDCommerciante = IDCommerciante;
    }

    /**
     * Metodo getter per l'attributo ID del {@link Cliente} associato al {@link Ritiro}.
     *
     * @return l'ID del {@link Cliente} associato al {@link Ritiro}
     */
    public String getIDCliente() {
        return IDCliente;
    }

    /**
     * Metodo setter per l'attributo dell'ID del {@link Cliente} associato al {@link Ritiro}.
     *
     * @param IDCliente - il nuovo ID del {@link Cliente} associato al{@link Ritiro}
     */
    public void setIDCliente(String IDCliente) {
        this.IDCliente = IDCliente;
    }

    /**
     * Metodo getter per l'attributo ID del {@link Corriere} associato al {@link Ritiro}.
     *
     * @return l'ID del {@link Corriere} associato al {@link Ritiro}
     */
    public String getIDCorriere() {
        return IDCorriere;
    }

    /**
     * Metodo setter per l'attributo dell'ID del {@link Corriere} associato al {@link Ritiro}.
     *
     * @param IDCorriere - il nuovo ID del {@link Corriere} associato al{@link Ritiro}
     */
    public void setIDCorriere(String IDCorriere) {
        this.IDCorriere = IDCorriere;
    }

    /**
     * Metodo getter per l'attributo della destinazione del {@link Ritiro}.
     *
     * @return la destinazione del {@link Ritiro}
     */
    public String getDestinazione() {
        return destinazione;
    }

    /**
     * Metodo setter per l'attributo della destinazione del {@link Ritiro}.
     *
     * @param destinazione - la nuova destinazione del {@link Ritiro}
     */
    public void setDestinazione(String destinazione) {
        this.destinazione = destinazione;
    }

    /**
     * Metodo getter per l'attributo del codice di ritiro del {@link Ritiro}.
     *
     * @return il codice di ritiro del {@link Ritiro}
     */
    public String getCodiceRitiro() {
        return codiceRitiro;
    }

    /**
     * Metodo setter per l'attributo del codice di ritiro del {@link Ritiro}.
     *
     * @param codiceRitiro - il nuovo codice di ritiro del {@link Ritiro}
     */
    public void setCodiceRitiro(String codiceRitiro) {
        this.codiceRitiro = codiceRitiro;
    }

    /**
     * Metodo getter per l'attributo dello stato del tracking del {@link Ritiro}.
     *
     * @return lo stato del tracking del {@link Ritiro}
     */
    public StatoTracking getStatoTracking() {
        return statoTracking;
    }

    /**
     * Metodo setter per l'attributo dello stato tracking del {@link Ritiro}.
     *
     * @param statoTracking - la nuova data di prenotazione del {@link Ritiro}
     */
    public void setStatoTracking(StatoTracking statoTracking) {
        this.statoTracking = statoTracking;
    }

    /**
     * Metodo equals di un {@link Ritiro}.
     *
     * @param o - oggetto da confrontare
     * @return true se i ritiri sono uguali, false altrimenti
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Ritiro ritiro = (Ritiro) o;
        return ID.equals(ritiro.ID);
    }

    /**
     * Metodo hasCode di un {@link Ritiro}
     *
     * @return l'hasCode
     */
    @Override
    public int hashCode() {
        return Objects.hash(ID);
    }

    /**
     * Metodo toString del {@link Ritiro}.
     *
     * @return l'ID, ID del {@link Cliente}, ID del {@link Commerciante}, la lista degli ID dei {@link Prodotto}, ID del {@link Corriere}, destinazione,
     * codice di ritiro, data di prenotazione, data di consegna, attributo ritirato, il tipo di consegna e lo stato tracking del {@link Ritiro}
     */
    @Override
    public String toString() {
        return "Ritiro{" +
                "ID='" + ID + '\'' +
                ", listaIDProdotti=" + listaIDProdotti +
                ", IDCommerciante='" + IDCommerciante + '\'' +
                ", IDCliente='" + IDCliente + '\'' +
                ", IDCorriere='" + IDCorriere + '\'' +
                ", destinazione='" + destinazione + '\'' +
                ", codiceRitiro='" + codiceRitiro + '\'' +
                ", dataPrenotazione=" + dataPrenotazione +
                ", dataConsegna=" + dataConsegna +
                ", ritirato=" + ritirato +
                ", tipoConsegna=" + tipoConsegna +
                ", stato=" + statoTracking +
                '}';
    }
}
