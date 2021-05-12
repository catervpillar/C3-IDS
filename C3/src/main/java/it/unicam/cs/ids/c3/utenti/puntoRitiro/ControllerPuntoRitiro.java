package it.unicam.cs.ids.c3.utenti.puntoRitiro;

import it.unicam.cs.ids.c3.ritiro.*;
import it.unicam.cs.ids.c3.utenti.cliente.Cliente;
import it.unicam.cs.ids.c3.utenti.commerciante.Commerciante;
import it.unicam.cs.ids.c3.utilities.GestoreRicerche;
import it.unicam.cs.ids.c3.database.*;
import it.unicam.cs.ids.c3.utilities.Controllore;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Objects;

/**
 * Questa classe è un sigleton e si occupa di gestire le operazioni che i {@link PuntoRitiro} possono
 * svolgere, nonché della creazione dei suddetti.
 */
public class ControllerPuntoRitiro {
    private static ControllerPuntoRitiro instance;
    private PuntoRitiroInterface puntoRitiro;

    /**
     * Costruttore privato usato solamente all'interno di questa classe.
     */
    private ControllerPuntoRitiro() {
    }

    /**
     * Metodo getter per l'attributo instance. Se instance è nulla, viene inizializzata.
     *
     * @return l'attributo instance.
     */
    public static ControllerPuntoRitiro getInstance() {
        if (Objects.isNull(instance)) instance = new ControllerPuntoRitiro();
        return instance;
    }

    /**
     * Metodo getter per l'attributo puntoRitiro.
     *
     * @return l'attributo puntoRitiro.
     */
    public PuntoRitiroInterface getPuntoRitiro() {
        return puntoRitiro;
    }

    /**
     * Metodo setter per l'attributo puntoRitiro.
     *
     * @param puntoRitiro - Il nuovo attributo puntoRitiro.
     */
    public void setPuntoRitiro(PuntoRitiroInterface puntoRitiro) {
        this.puntoRitiro = puntoRitiro;
    }

    /**
     * Crea un {@link PuntoRitiro} dopo averne controllato i parametri e successivamente lo
     * fa serializzare nel database.
     *
     * @param username       - L'username del {@link PuntoRitiro}.
     * @param password       - La password del {@link PuntoRitiro}.
     * @param email          - L'email del {@link PuntoRitiro}.
     * @param ragioneSociale - La ragione sociale del {@link PuntoRitiro}.
     * @throws SQLException in caso di problemi col database.
     */
    public void creaPuntoRitiro(String username, String password, String email, String ragioneSociale) throws SQLException {
        Controllore.getInstance().controllaPuntoRitiro(username, password, email, ragioneSociale);
        PuntoRitiroInterface puntoRitiro = new PuntoRitiro(username, password, email, ragioneSociale);
        SerializerAggiunta.getInstance().serializzaPuntoRitiro(puntoRitiro);
    }

    /**
     * Fa cercare nella tabella punti_ritiro del database le credenziali date in input e, se esistono, deserializza
     * tale {@link PuntoRitiro} e ritorna true, altrimenti ritorna false.
     *
     * @param username - L'username del {@link PuntoRitiro} da cercare.
     * @param password - La password del {@link PuntoRitiro} da cercare.
     * @return true se il {@link PuntoRitiro} esiste, false altrimenti.
     * @throws SQLException in caso di errori col database.
     */
    public boolean loginPuntoRitiro(String username, String password) throws SQLException {
        String sql = "select * from punto_ritiro where username = \"" + username + "\" and password = \"" + password + "\";";
        ResultSet resultSet = DBManager.getInstance().executeQuery(sql);

        if (resultSet.last()) {
            resultSet.beforeFirst();
            List<PuntoRitiroInterface> listaPuntiRitiro = Deserializer.getInstance().deserializzaPuntiRitiro(resultSet);
            this.puntoRitiro = listaPuntiRitiro.get(0);
            return true;
        } else return false;
    }

    /**
     * Permette di modificare il {@link PuntoRitiro} corrente e di salvare le modifiche nel database.
     *
     * @param username       - Il nuovo username del {@link PuntoRitiro}.
     * @param password       - La nuova password del {@link PuntoRitiro}.
     * @param email          - La nuova email del {@link PuntoRitiro}.
     * @param ragioneSociale - La nuova ragione sociale del {@link PuntoRitiro}.
     * @param telefono       - Il nuovo telefono del {@link PuntoRitiro}.
     * @param indirizzo      - Il nuovo indirizzo del {@link PuntoRitiro}.
     * @throws SQLException in caso di errori col database.
     */
    public void modificaPuntoRitiro(String username, String password, String email, String ragioneSociale, String telefono, String indirizzo) throws SQLException {
        Controllore.getInstance().controllaPuntoRitiro(username, password, email, ragioneSociale);
        PuntoRitiroInterface puntoRitiro = new PuntoRitiro(this.puntoRitiro.getID(), username, password, email, ragioneSociale);

        if (!indirizzo.isBlank()) {
            Controllore.getInstance().controllaIndirizzo(indirizzo);
            puntoRitiro.setIndirizzo(indirizzo);
        }
        if (!telefono.isBlank()) {
            Controllore.getInstance().controllaNumero(telefono, 10);
            puntoRitiro.setTelefono(telefono);
        }
        this.puntoRitiro = puntoRitiro;
        SerializerModifica.getInstance().modificaPuntoRitiro(puntoRitiro);
    }

    /**
     * Consente di eliminare il {@link PuntoRitiro} corrente dal database.
     *
     * @throws SQLException in caso di problemi col database.
     */
    public void eliminaAccount() throws SQLException {
        SerializerElimina.getInstance().eliminaPuntoDiRitiro(this.puntoRitiro.getID());
        this.puntoRitiro = null;
        GestoreRicerche.getInstance().reset();
    }

    /**
     * Consente di effettuare il logout resettando il gestore delle ricerche e impostando
     * l'attributo cliente null.
     */
    public void logout() {
        GestoreRicerche.getInstance().reset();
        this.puntoRitiro = null;
    }

    /**
     * Ritorna la lista di tutti i ritiri affidati a questo {@link PuntoRitiro}.
     *
     * @return la lista dei ritiri.
     */
    public List<RitiroInterface> getRitiri() {
        return GestoreRicerche.getInstance().getRitiri(null, null, null, this.puntoRitiro.getIndirizzo());
    }

    /**
     * Permette di aggiornare lo stato di ritiro di un {@link Ritiro} mediante l'apposito gestore.
     *
     * @param ID             - L'ID del {@link Ritiro} di cui si vuole aggiornare lo stato di ritiro.
     * @param IDCommerciante - L'ID del {@link Commerciante} del {@link Ritiro} di cui si vuole aggiornare lo stato di ritiro.
     * @param IDCliente      - L'ID del {@link Cliente} del {@link Ritiro} di cui si vuole aggiornare lo stato di ritiro.
     * @param IDCorriere     - L'ID del {@link Cliente} del {@link Ritiro} di cui si vuole aggiornare lo stato di ritiro.
     * @param ritirato       - Il nuovo stato di ritiro del {@link Ritiro}.
     * @param tipoConsegna   - Il {@link TipoConsegna} del {@link Ritiro} di cui si vuole aggiornare lo stato di ritiro.
     * @param stato          - Lo stato tracking del {@link Ritiro} di cui si vuole aggiornare lo stato di ritiro.
     * @throws SQLException in caso di problemi col database.
     */
    public void contrassegna(String ID, String IDCommerciante, String IDCliente, String IDCorriere, boolean ritirato, TipoConsegna tipoConsegna, StatoTracking stato) throws SQLException {
        GestoreRitiri.getInstance().modificaRitiro(ID, IDCommerciante, IDCliente, IDCorriere, this.puntoRitiro.getIndirizzo(), ritirato, tipoConsegna, stato);
    }
}
