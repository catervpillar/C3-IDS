package it.unicam.cs.ids.c3.utenti.corriere;

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
 * Questa classe è un sigleton e si occupa di gestire le operazioni che i {@link Corriere} possono
 * svolgere, nonché della creazione dei suddetti.
 */
public class ControllerCorriere {
    private static ControllerCorriere instance;
    private CorriereInterface corriere;

    /**
     * Costruttore privato usato solamente all'interno di questa classe.
     */
    private ControllerCorriere() {
    }

    /**
     * Metodo getter per l'attributo instance. Se instance è nulla, viene inizializzata.
     *
     * @return l'attributo instance.
     */
    public static ControllerCorriere getInstance() {
        if (Objects.isNull(instance)) instance = new ControllerCorriere();
        return instance;
    }

    /**
     * Metodo getter per l'attributo corriere.
     *
     * @return l'attributo corriere.
     */
    public CorriereInterface getCorriere() {
        return corriere;
    }

    /**
     * Metodo setter per l'attributo corriere.
     *
     * @param corriere - Il nuovo attributo corriere.
     */
    public void setCorriere(CorriereInterface corriere) {
        this.corriere = corriere;
    }

    /**
     * Crea un {@link Corriere} dopo averne controllato i parametri e successivamente lo
     * fa serializzare nel database.
     *
     * @param username       - L'username del {@link Corriere}.
     * @param password       - La password del {@link Corriere}.
     * @param email          - L'email del {@link Corriere}.
     * @param ragioneSociale - La ragione sociale del {@link Corriere}.
     * @throws SQLException in caso di problemi col database.
     */
    public void creaCorriere(String username, String password, String email, String ragioneSociale) throws SQLException {
        Controllore.getInstance().controllaCorriere(username, password, email, ragioneSociale);
        CorriereInterface corriere = new Corriere(username, password, email, ragioneSociale);
        SerializerAggiunta.getInstance().serializzaCorriere(corriere);
    }

    /**
     * Fa cercare nella tabella corrieri del database le credenziali date in input e, se esistono, deserializza
     * tale {@link Corriere} e ritorna true, altrimenti ritorna false.
     *
     * @param username - L'username del {@link Corriere} da cercare.
     * @param password - La password del {@link Corriere} da cercare.
     * @return true se il {@link Corriere} esiste, false altrimenti.
     * @throws SQLException in caso di errori col database.
     */
    public boolean loginCorriere(String username, String password) throws SQLException {
        String sql = "select * from corriere where username = \"" + username + "\" and password = \"" + password + "\";";
        ResultSet resultSet = DBManager.getInstance().executeQuery(sql);

        if (resultSet.last()) {
            resultSet.beforeFirst();
            List<CorriereInterface> listaCorrieri = Deserializer.getInstance().deserializzaCorrieri(resultSet);
            this.corriere = listaCorrieri.get(0);
            return true;
        } else return false;
    }

    /**
     * Permette di modificare il {@link Corriere} corrente e di salvare le modifiche nel database.
     *
     * @param username       - Il nuovo username del {@link Corriere}.
     * @param password       - La nuova password del {@link Corriere}.
     * @param email          - La nuova email del {@link Corriere}.
     * @param ragioneSociale - La nuova ragione sociale del {@link Corriere}.
     * @param telefono       - Il nuovo telefono del {@link Corriere}.
     * @param indirizzo      - Il nuovo indirizzo del {@link Corriere}.
     * @param stato          - Il nuovo stato del {@link Corriere}.
     * @throws SQLException in caso di errori col database.
     */
    public void modificaCorriere(String username, String password, String email, String ragioneSociale, String telefono, String indirizzo, StatoCorriere stato) throws SQLException {
        Controllore.getInstance().controllaCorriere(username, password, email, ragioneSociale);
        CorriereInterface corriere = new Corriere(this.corriere.getID(), username, password, email, ragioneSociale);

        if (!indirizzo.isBlank()) {
            Controllore.getInstance().controllaIndirizzo(indirizzo);
            corriere.setIndirizzo(indirizzo);
        }
        if (!telefono.isBlank()) {
            Controllore.getInstance().controllaNumero(telefono, 10);
            corriere.setTelefono(telefono);
        }
        if (!Objects.isNull(stato))
            corriere.setStato(stato);
        this.corriere = corriere;
        SerializerModifica.getInstance().modificaCorriere(corriere);
    }

    /**
     * Consente di eliminare il {@link Corriere} corrente dal database.
     *
     * @throws SQLException in caso di problemi col database.
     */
    public void eliminaAccount() throws SQLException {
        SerializerElimina.getInstance().eliminaCorriere(this.corriere.getID());
        this.corriere = null;
        GestoreRicerche.getInstance().reset();
    }

    /**
     * Consente di effettuare il logout resettando il gestore delle ricerche e impostando
     * l'attributo cliente null.
     */
    public void logout() {
        GestoreRicerche.getInstance().reset();
        this.corriere = null;
    }

    /**
     * Ritorna la lista di tutti i ritiri commissionati a questo {@link Corriere}.
     *
     * @return la lista dei ritiri.
     */
    public List<RitiroInterface> getRitiri() {
        return GestoreRicerche.getInstance().getRitiri(null, null, this.corriere.getID(), null);
    }

    /**
     * Permette di aggiornare il tracking di spedizione di un {@link Ritiro} mediante l'apposito gestore.
     *
     * @param ID             - L'ID del {@link Ritiro} di cui si vuole aggiornare il tracking.
     * @param IDCommerciante - L'ID del {@link Commerciante} del {@link Ritiro} di cui si vuole aggiornare il tracking.
     * @param IDCliente      - L'ID del {@link Cliente} del {@link Ritiro} di cui si vuole aggiornare il tracking.
     * @param destinazione   - La destinazione del {@link Ritiro} di cui si vuole aggiornare il tracking.
     * @param ritirato       - Lo stato di ritiro del {@link Ritiro} di cui si vuole aggiornare il tracking.
     * @param tipoConsegna   - Il {@link TipoConsegna} del {@link Ritiro} di cui si vuole aggiornare il tracking.
     * @param stato          - Il nuovo stato tracking del {@link Ritiro}.
     * @throws SQLException in caso di problemi col database.
     */
    public void aggiornaTracking(String ID, String IDCommerciante, String IDCliente, String destinazione, boolean ritirato, TipoConsegna tipoConsegna, StatoTracking stato) throws SQLException {
        GestoreRitiri.getInstance().modificaRitiro(ID, IDCommerciante, IDCliente, this.corriere.getID(), destinazione, ritirato, tipoConsegna, stato);
    }
}
