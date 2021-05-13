package it.unicam.cs.ids.c3.ritiro;

import java.util.GregorianCalendar;
import java.util.List;
import it.unicam.cs.ids.c3.prodotto.Prodotto;
import it.unicam.cs.ids.c3.utenti.cliente.Cliente;
import it.unicam.cs.ids.c3.utenti.corriere.Corriere;
import it.unicam.cs.ids.c3.utenti.commerciante.Commerciante;

public interface RitiroInterface {

    /**
     * Metodo getter per l'attributo ID.
     *
     * @return l'attributo ID.
     */
    String getID();

    /**
     * Metodo setter per l'attributo ID.
     *
     * @param ID - Il nuovo attributo ID.
     */
    void setID(String ID);

    /**
     * Metodo getter per l'attributo tipoConsegna.
     *
     * @return l'attributo tipoConsegna.
     */
    TipoConsegna getTipoConsegna();

    /**
     * Metodo setter per l'attributo tipoConsegna.
     *
     * @param tipoConsegna - Il nuovo attributo tipoConsegna.
     */
    void setTipoConsegna(TipoConsegna tipoConsegna);

    /**
     * Metodo getter per l'attributo dataConsegna.
     *
     * @return l'attributo dataConsegna.
     */
    GregorianCalendar getDataConsegna();

    /**
     * Metodo setter per l'attributo dataConsegna.
     *
     * @param dataConsegna - Il nuovo attributo dataConsegna.
     */
    void setDataConsegna(GregorianCalendar dataConsegna);

    /**
     * Metodo getter per l'attributo ritirato.
     *
     * @return l'attributo ritirato.
     */
    boolean isRitirato();

    /**
     * Metodo setter per l'attributo ritirato.
     *
     * @param ritirato - Il nuovo attributo ritirato.
     */
    void setRitirato(boolean ritirato);

    /**
     * Metodo getter per l'attributo dataPrenotazione.
     *
     * @return l'attributo dataPrenotazione.
     */
    GregorianCalendar getDataPrenotazione();

    /**
     * Metodo setter per l'attributo dataPrenotazione.
     *
     * @param dataPrenotazione - Il nuovo attributo dataPrenotazione.
     */
    void setDataPrenotazione(GregorianCalendar dataPrenotazione);

    /**
     * Metodo getter per la lista degli ID dei {@link Prodotto}.
     *
     * @return la lista delgi ID dei {@link Prodotto}
     */
    List<String> getListaIDProdotti();

    /**
     * Metodo setter per la lista degli ID dei {@link Prodotto}.
     *
     * @param listaIDProdotti - La nuova lista degli ID dei {@link Prodotto}
     */
    void setListaIDProdotti(List<String> listaIDProdotti);

    /**
     * Metodo getter per l'attributo ID del {@link Commerciante}.
     *
     * @return l'attributo IDCommerciante.
     */
    String getIDCommerciante();

    /**
     * Metodo setter per l'attributo ID del {@link Commerciante}.
     *
     * @param IDCommerciante - Il nuovo attributo IDCommerciante.
     */
    void setIDCommerciante(String IDCommerciante);

    /**
     * Metodo getter per l'attributo ID del {@link Cliente}.
     *
     * @return l'attributo IDCommerciante.
     */
    String getIDCliente();

    /**
     * Metodo setter per l'attributo ID del {@link Cliente}.
     *
     * @param IDCliente - Il nuovo attributo IDCliente.
     */
    void setIDCliente(String IDCliente);

    /**
     * Metodo getter per l'attributo ID del {@link Corriere}.
     *
     * @return l'attributo IDCorriere.
     */
    String getIDCorriere();

    /**
     * Metodo setter per l'attributo ID del {@link Corriere}.
     *
     * @param IDCorriere - Il nuovo attributo IDCorriere.
     */
    void setIDCorriere(String IDCorriere);

    /**
     * Metodo getter per l'attributo destinazione.
     *
     * @return l'attributo destinazione.
     */
    String getDestinazione();

    /**
     * Metodo setter per l'attributo destinazione.
     *
     * @param destinazione - Il nuovo attributo destinazione
     */
    void setDestinazione(String destinazione);

    /**
     * Metodo getter per l'attributo codiceRitiro.
     *
     * @return l'attributo codiceRitiro.
     */
    String getCodiceRitiro();

    /**
     * Metodo setter per l'attributo codiceRitiro.
     *
     * @param codiceRitiro - Il nuovo attributo codiceRitiro
     */
    void setCodiceRitiro(String codiceRitiro);

    /**
     * Metodo getter per l'attributo statoTracking.
     *
     * @return l'attributo statoTracking.
     */
    StatoTracking getStatoTracking();

    /**
     * Metodo setter per l'attributo statoTracking.
     *
     * @param statoTracking - Il nuovo attributo statoTracking
     */
    void setStatoTracking(StatoTracking statoTracking);
}
