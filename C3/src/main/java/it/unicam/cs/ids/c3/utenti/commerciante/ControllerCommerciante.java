package it.unicam.cs.ids.c3.utenti.commerciante;

import it.unicam.cs.ids.c3.prodotto.GestoreProdotti;
import it.unicam.cs.ids.c3.prodotto.ProdottoInterface;
import it.unicam.cs.ids.c3.promozione.GestorePromozioni;
import it.unicam.cs.ids.c3.promozione.Promozione;
import it.unicam.cs.ids.c3.promozione.PromozioneInterface;
import it.unicam.cs.ids.c3.ritiro.Ritiro;
import it.unicam.cs.ids.c3.ritiro.RitiroInterface;
import it.unicam.cs.ids.c3.utenti.cliente.Cliente;
import it.unicam.cs.ids.c3.utenti.corriere.Corriere;
import it.unicam.cs.ids.c3.utilities.GestoreRicerche;
import it.unicam.cs.ids.c3.ritiro.GestoreRitiri;
import it.unicam.cs.ids.c3.prodotto.Prodotto;
import it.unicam.cs.ids.c3.ritiro.TipoConsegna;
import it.unicam.cs.ids.c3.database.*;
import it.unicam.cs.ids.c3.utilities.Controllore;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Objects;

/**
 * Questa classe è un sigleton e si occupa di gestire le operazioni che i {@link Commerciante} possono
 * svolgere, nonché della creazione dei suddetti.
 */
public class ControllerCommerciante {
    private static ControllerCommerciante instance;
    private CommercianteInterface commerciante;

    /**
     * Costruttore privato usato solamente all'interno di questa classe.
     */
    private ControllerCommerciante() {
    }

    /**
     * Metodo getter per l'attributo instance. Se instance è nulla, viene inizializzata.
     *
     * @return l'attributo instance.
     */
    public static ControllerCommerciante getInstance() {
        if (Objects.isNull(instance)) instance = new ControllerCommerciante();
        return instance;
    }

    /**
     * Metodo getter per l'attributo commerciante.
     *
     * @return l'attributo commerciante.
     */
    public CommercianteInterface getCommerciante() {
        return commerciante;
    }

    /**
     * Metodo setter per l'attributo commerciante.
     *
     * @param commerciante - Il nuovo attributo commerciante.
     */
    public void setCommerciante(CommercianteInterface commerciante) {
        this.commerciante = commerciante;
    }

    /**
     * Crea un {@link Commerciante} dopo averne controllato i parametri e successivamente lo
     * fa serializzare nel database.
     *
     * @param username       - L'username del {@link Commerciante}.
     * @param password       - La password del {@link Commerciante}.
     * @param email          - L'email del {@link Commerciante}.
     * @param ragioneSociale - La ragione sociale del {@link Commerciante}.
     * @throws SQLException in caso di problemi col database.
     */
    public void creaCommerciante(String username, String password, String email, String ragioneSociale) throws SQLException {
        Controllore.getInstance().controllaCommerciante(username, password, email, ragioneSociale);
        CommercianteInterface commerciante = new Commerciante(username, password, email, ragioneSociale);
        SerializerAggiunta.getInstance().serializzaCommerciante(commerciante);
    }

    /**
     * Permette di modificare il {@link Commerciante} corrente e di salvare le modifiche nel database.
     *
     * @param username       - Il nuovo username del {@link Commerciante}.
     * @param password       - La nuova password del {@link Commerciante}.
     * @param email          - La nuova email del {@link Commerciante}.
     * @param ragioneSociale - La nuova ragione sociale del {@link Commerciante}.
     * @param telefono       - Il nuovo telefono del {@link Commerciante}.
     * @param indirizzo      - Il nuovo indirizzo del {@link Commerciante}.
     * @throws SQLException in caso di problemi col database.
     */
    public void modificaCommerciante(String username, String password, String email, String ragioneSociale, String telefono, String indirizzo) throws SQLException {
        Controllore.getInstance().controllaCommerciante(username, password, email, ragioneSociale);
        CommercianteInterface commerciante = new Commerciante(this.commerciante.getID(), username, password, email, ragioneSociale);

        if (!indirizzo.isBlank()) {
            Controllore.getInstance().controllaIndirizzo(indirizzo);
            commerciante.setIndirizzo(indirizzo);
        }
        if (!telefono.isBlank()) {
            Controllore.getInstance().controllaNumero(telefono, 10);
            commerciante.setTelefono(telefono);
        }
        setCommerciante(commerciante);
        SerializerModifica.getInstance().modificaCommerciante(commerciante);
    }

    /**
     * Consente di eliminare il {@link Commerciante} corrente dal database.
     *
     * @throws SQLException in caso di problemi col database.
     */
    public void eliminaAccount() throws SQLException {
        SerializerElimina.getInstance().eliminaCommerciante(this.commerciante.getID());
        setCommerciante(null);
        GestoreRicerche.getInstance().reset();
    }

    /**
     * Consente di effettuare il logout resettando il gestore delle ricerche e impostando
     * l'attributo cliente null.
     */
    public void logout() {
        GestoreRicerche.getInstance().reset();
        setCommerciante(null);
    }

    /**
     * Fa cercare nella tabella commercianti del database le credenziali date in input e, se esistono, deserializza
     * tale {@link Commerciante} e ritorna true, altrimenti ritorna false.
     *
     * @param username  - L'username del {@link Commerciante} da cercare.
     * @param password- La password del {@link Commerciante} da cercare.
     * @return true se il {@link Commerciante} esiste, false altrimenti.
     * @throws SQLException in caso di errori col database.
     */
    public boolean loginCommerciante(String username, String password) throws SQLException {
        String sql = "select * from commerciante where username = \"" + username + "\" and password = \"" + password + "\";";
        ResultSet resultSet = DBManager.getInstance().executeQuery(sql);

        if (resultSet.last()) {
            resultSet.beforeFirst();
            List<CommercianteInterface> listaCommercianti = Deserializer.getInstance().deserializzaCommercianti(resultSet);
            this.commerciante = listaCommercianti.get(0);
            return true;
        } else return false;
    }

    /**
     * Ritorna la lista dei prodotti venduti dal {@link Commerciante} corrente, cercandoli ne database.
     *
     * @return la lista dei prodotti.
     */
    public List<ProdottoInterface> getProdotti() {
        return GestoreRicerche.getInstance().cercaProdotto(null, this.commerciante.getID());
    }

    /**
     * Ritorna la lista dei ritiri commissionati dal {@link Commerciante} corrente, cercandoli ne database.
     *
     * @return la lista dei ritiri.
     */
    public List<RitiroInterface> getRitiri() {
        return GestoreRicerche.getInstance().getRitiri(this.commerciante.getID(), null, null, null);
    }

    /**
     * Consente di creare un nuovo {@link Prodotto} mediante l'apposito gestore.
     *
     * @param nome        - Il nome del nuovo {@link Prodotto}.
     * @param prezzo      - Il prezzo del nuovo {@link Prodotto}.
     * @param quantita    - La quantita' del nuovo {@link Prodotto}.
     * @param URLimmagine - L'URL dell'immagine del nuovo{@link Prodotto}.
     * @throws SQLException in caso di problemi col database.
     */
    public void creaProdotto(String nome, Double prezzo, int quantita, String URLimmagine) throws SQLException {
        GestoreProdotti.getInstance().creaProdotto(nome, prezzo, quantita, this.commerciante.getID(), URLimmagine);
    }

    /**
     * Consente di modificare un {@link Prodotto} mediante l'apposito gestore.
     *
     * @param prodotto - Il {@link Prodotto} da modificare.
     * @throws SQLException in caso di problemi col database.
     */
    public void modificaProdotto(ProdottoInterface prodotto) throws SQLException {
        GestoreProdotti.getInstance().modificaProdotto(prodotto);
    }

    /**
     * Consente di eliminare un {@link Prodotto} mediante l'apposito gestore.
     *
     * @param IDProdotto - L'ID del {@link Prodotto} da eliminare.
     * @throws SQLException in caso di problemi col database.
     */
    public void eliminaProdotto(String IDProdotto) throws SQLException {
        GestoreProdotti.getInstance().eliminaProdotto(IDProdotto);
    }

    /**
     * Consente di creare un nuovo {@link Ritiro} mediante l'apposito gestore.
     *
     * @param IDCliente       - L'ID del {@link Cliente} che ha acquistato la merce.
     * @param IDCorriere      - L'ID del {@link Corriere} che ha acquistato la merce.
     * @param destinazione    - La destinazione di consegna del {@link Ritiro}.
     * @param tipoConsegna    - Il {@link TipoConsegna} del {@link Ritiro}.
     * @param listaIDProdotti - La lista degli ID dei prodotti facenti parte del {@link Ritiro}.
     * @throws SQLException in caso di problemi col database.
     */
    public void prenotaRitiro(String IDCliente, String IDCorriere, String destinazione, TipoConsegna tipoConsegna, List<String> listaIDProdotti) throws SQLException {
        GestoreRitiri.getInstance().creaRitiro(this.commerciante.getID(), IDCliente, IDCorriere, destinazione, tipoConsegna, listaIDProdotti);
    }

    /**
     * Consente di eliminare un {@link Ritiro} mediante l'apposito gestore.
     *
     * @param ID - L'ID del {@link Ritiro} da eliminare.
     * @throws SQLException in caso di problemi col database.
     */
    public void eliminaRitiro(String ID) throws SQLException {
        GestoreRitiri.getInstance().eliminaRitiro(ID);
    }

    /**
     * Ritorna tutte le promozioni create dal {@link Commerciante} corrente.
     *
     * @return la lista delle promozioni.
     */
    public List<PromozioneInterface> getPromozioni() {
        return GestoreRicerche.getInstance().getPromozioni(this.commerciante.getID());
    }

    /**
     * Consente di creare una nuova {@link Promozione} mediante l'apposito gestore.
     *
     * @param nome            - Il nome della nuova {@link Promozione}.
     * @param descrizione     - La descrizione della nuova {@link Promozione}.
     * @param dataInizio      - La data di inizio della nuova {@link Promozione}.
     * @param dataScadenza    - La data di scadenza della nuova {@link Promozione}.
     * @param listaIDProdotti - La lista degli ID dei prodotti facenti parte della nuova {@link Promozione}.
     * @throws SQLException in caso di problemi col database.
     */
    public void creaPromozione(String nome, String descrizione, GregorianCalendar dataInizio, GregorianCalendar dataScadenza, List<String> listaIDProdotti) throws SQLException {
        GestorePromozioni.getInstance().creaPromozione(nome, this.commerciante.getID(), listaIDProdotti, descrizione, dataInizio, dataScadenza);
    }

    /**
     * Consente di modificare una {@link Promozione} mediante l'apposito gestore.
     *
     * @param IDPromozione - L'ID della {@link Promozione} da modificare.
     * @param nome         - Il nuovo nome della {@link Promozione} da modificare.
     * @param descrizione  - La nuova descrizione della {@link Promozione} da modificare.
     * @param dataInizio   - La nuova data di inizio della {@link Promozione} da modificare.
     * @param dataScadenza - La nuova data di scadenza della {@link Promozione} da modificare.
     * @throws SQLException in caso di problemi col database.
     */
    public void modificaPromozione(String IDPromozione, String nome, String descrizione, GregorianCalendar dataInizio, GregorianCalendar dataScadenza) throws SQLException {
        GestorePromozioni.getInstance().modificaPromozione(IDPromozione, this.commerciante.getID(), nome, descrizione, dataInizio, dataScadenza);
    }

    /**
     * Consente di modificare una {@link Promozione} mediante l'apposito gestore.
     *
     * @param IDPromozione - L'ID della {@link Promozione} da eliminare.
     * @throws SQLException in caso di problemi col database.
     */
    public void rimuoviPromozione(String IDPromozione) throws SQLException {
        GestorePromozioni.getInstance().rimuoviPromozione(IDPromozione);
    }
}
