package it.unicam.cs.ids.c3.utenti.cliente;

import it.unicam.cs.ids.c3.prodotto.ProdottoInterface;
import it.unicam.cs.ids.c3.promozione.PromozioneInterface;
import it.unicam.cs.ids.c3.recensione.GestoreRecensioni;
import it.unicam.cs.ids.c3.recensione.RecensioneInterface;
import it.unicam.cs.ids.c3.ritiro.RitiroInterface;
import it.unicam.cs.ids.c3.utilities.GestoreRicerche;
import it.unicam.cs.ids.c3.recensione.VotoRecensioni;
import it.unicam.cs.ids.c3.database.*;
import it.unicam.cs.ids.c3.utilities.*;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * Questa classe è un sigleton e si occupa di gestire le operazioni che i {@link Cliente} possono
 * svolgere, nonché della creazione dei suddetti.
 */
public class ControllerCliente {
    private static ControllerCliente instance;
    private ClienteInterface cliente;

    /**
     * Costruttore privato usato solamente all'interno di questa classe
     */
    private ControllerCliente() {
    }

    /**
     * Metodo getter per l'attributo instance. Se instance è nulla, viene inizializzata.
     *
     * @return l'attributo instance.
     */
    public static ControllerCliente getInstance() {
        if (Objects.isNull(instance)) instance = new ControllerCliente();
        return instance;
    }

    /**
     * Crea un {@link Cliente} dopo averne controllato i parametri e successivamente lo
     * fa serializzare nel database.
     *
     * @param username - L'username del {@link Cliente}.
     * @param password - La password del {@link Cliente}.
     * @param email    - L'email del {@link Cliente}.
     * @param nome     - Il nome del {@link Cliente}.
     * @param cognome  - Il cognome del {@link Cliente}.
     * @throws SQLException in caso di errori col database.
     */
    public void creaCliente(String username, String password, String email, String nome, String cognome) throws SQLException {
        Controllore.getInstance().controllaCliente(username, password, email, nome, cognome);
        ClienteInterface cliente = new Cliente(username, password, email, nome, cognome);
        SerializerAggiunta.getInstance().serializzaCliente(cliente);
    }

    /**
     * Fa cercare nella tabella clienti del database le credenziali date in input e, se esistono, deserializza
     * tale {@link Cliente} e ritorna true, altrimenti ritorna false.
     *
     * @param username  - L'username del {@link Cliente} da cercare.
     * @param password- La password del {@link Cliente} da cercare.
     * @return true se il {@link Cliente} esiste, false altrimenti.
     * @throws SQLException in caso di errori col database.
     */
    public boolean loginCliente(String username, String password) throws SQLException {
        String sql = "select * from cliente where username = \"" + username + "\" and password = \"" + password + "\";";
        ResultSet resultSet = DBManager.getInstance().executeQuery(sql);
        if (resultSet.last()) {
            this.cliente = Deserializer.getInstance().deserializzaCliente(resultSet);
            return true;
        } else return false;
    }

    /**
     * Ritorna la lista dei prodotti del database filtrata in base al nome.
     *
     * @param nome - Il nome del prodotto da cercare.
     * @return la lista contenente i prodotti filtrati in base al nome.
     */
    public List<ProdottoInterface> getProdotti(String nome) {
        return GestoreRicerche.getInstance().cercaProdotto(nome, null);
    }

    /**
     * Ritorna la lista di tutti i prodotti inclusi nei {@link it.unicam.cs.ids.c3.ritiro.Ritiro}
     * relativi al {@link Cliente} corrente.
     *
     * @return la lista di tutti i prodotti acquistati dal {@link Cliente}.
     * @throws SQLException in caso di errori col database.
     */
    public List<ProdottoInterface> getListaProdottiAcquistati() throws SQLException {
        List<RitiroInterface> listaRitiro = getRitiri();
        List<String> listaIDProdotti = new ArrayList<>();
        List<ProdottoInterface> listaProdotti = new ArrayList<>();

        listaRitiro.forEach(r -> {
            String query = "select distinct prodottoInVendita_ID from ritiro_has_prodotto where ritiro_ID = \"" + r.getID() + "\";";
            try {
                listaIDProdotti.addAll(Deserializer.getInstance().deserializzaIDProdotti(DBManager.getInstance().executeQuery(query)));
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        });

        listaIDProdotti.stream().distinct().collect(Collectors.toList()).forEach(ID -> {
            String query = "select * from prodotto where ID = \"" + ID + "\";";
            try {
                listaProdotti.addAll(Deserializer.getInstance().deserializzaProdotti(DBManager.getInstance().executeQuery(query)));
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        });

        return listaProdotti;
    }

    /**
     * Crea una nuova {@link it.unicam.cs.ids.c3.recensione.Recensione} tramite l'apposito gestore.
     *
     * @param titolo         - Il titolo della {@link it.unicam.cs.ids.c3.recensione.Recensione}.
     * @param testo          - Il testo della {@link it.unicam.cs.ids.c3.recensione.Recensione}.
     * @param IDCommerciante - L'ID del commerciante che ha venduto il {@link it.unicam.cs.ids.c3.prodotto.Prodotto} della {@link it.unicam.cs.ids.c3.recensione.Recensione}.
     * @param IDProdotto     - L'ID del {@link it.unicam.cs.ids.c3.prodotto.Prodotto} della {@link it.unicam.cs.ids.c3.recensione.Recensione}.
     * @param votoRecensione - Il voto della {@link it.unicam.cs.ids.c3.recensione.Recensione}.
     * @throws SQLException in caso di errori col database.
     */
    public void pubblicaRecensione(String titolo, String testo, String IDCommerciante, String IDProdotto, VotoRecensioni votoRecensione) throws SQLException {
        GestoreRecensioni.getInstance().creaRecensione(titolo, testo, this.cliente.getID(), IDCommerciante, IDProdotto, votoRecensione);
    }

    /**
     * Permette di modificare una {@link it.unicam.cs.ids.c3.recensione.Recensione} tramite l'apposito gestore.
     *
     * @param titolo         - Il nuovo titolo della {@link it.unicam.cs.ids.c3.recensione.Recensione}.
     * @param testo          - Il nuovo testo della {@link it.unicam.cs.ids.c3.recensione.Recensione}.
     * @param votoRecensioni - Il nuovo voto della {@link it.unicam.cs.ids.c3.recensione.Recensione}.
     * @param IDRecensione   - L'ID della {@link it.unicam.cs.ids.c3.recensione.Recensione} da modificare.
     * @throws SQLException in caso di problemi col database.
     */
    public void modificaRecensione(String titolo, String testo, VotoRecensioni votoRecensioni, String IDRecensione) throws SQLException {
        GestoreRecensioni.getInstance().modificaRecensione(titolo, testo, votoRecensioni, IDRecensione);
    }

    /**
     * Permette di eliminare una {@link it.unicam.cs.ids.c3.recensione.Recensione} tramite l'apposito gestore.
     *
     * @param IDRecensione - L'ID della {@link it.unicam.cs.ids.c3.recensione.Recensione} da eliminare.
     * @throws SQLException in caso di problemi col database.
     */
    public void rimuoviRecensione(String IDRecensione) throws SQLException {
        GestoreRecensioni.getInstance().rimuoviRecensione(IDRecensione);
    }

    /**
     * Metodo getter per l'attributo cliente.
     *
     * @return il {@link Cliente} loggato.
     */
    public ClienteInterface getCliente() {
        return cliente;
    }

    /**
     * Metodo setter per l'attributo cliente.
     *
     * @param cliente - Il nuovo {@link Cliente} da settare.
     */
    public void setCliente(ClienteInterface cliente) {
        this.cliente = cliente;
    }

    /**
     * Ritorna la lista delle promozioni del database mediante l'apposito gestore.
     *
     * @return la lista delle {@link it.unicam.cs.ids.c3.promozione.Promozione}.
     */
    public List<PromozioneInterface> getPromozioni() {
        return GestoreRicerche.getInstance().getPromozioni(null);
    }

    /**
     * Ritorna la lista delle recensioni del database del {@link Cliente} corrente mediante l'apposito gestore.
     *
     * @return la lista delle {@link it.unicam.cs.ids.c3.promozione.Promozione}.
     */
    public List<RecensioneInterface> getRecensioni() {
        return GestoreRicerche.getInstance().getRecensioni("select * from recensione where cliente_ID = \"" + this.cliente.getID() + "\";");
    }

    /**
     * Ritorna la lista dei ritiri del database del {@link Cliente} corrente mediante l'apposito gestore.
     *
     * @return la lista delle {@link it.unicam.cs.ids.c3.ritiro.Ritiro}.
     */
    public List<RitiroInterface> getRitiri() {
        return GestoreRicerche.getInstance().getRitiri(null, this.cliente.getID(), null, null);
    }

    /**
     * Permette di modificare il {@link Cliente} corrente e di salvare le modifiche nel database.
     *
     * @param username  - Il nuovo username del {@link Cliente}.
     * @param password  - La nuova password del {@link Cliente}.
     * @param email     - La nuova email del {@link Cliente}.
     * @param nome      - Il nuovo nome del {@link Cliente}.
     * @param cognome   - Il nuovo cognome del {@link Cliente}.
     * @param telefono  - Il nuovo telefono del {@link Cliente}.
     * @param indirizzo - Il nuovo indirizzo del {@link Cliente}.
     * @throws SQLException in caso di problemi col database
     */
    public void modificaCliente(String username, String password, String email, String nome, String cognome, String telefono, String indirizzo) throws SQLException {
        Controllore.getInstance().controllaCliente(username, password, email, nome, cognome);
        ClienteInterface cliente = new Cliente(this.cliente.getID(), username, password, email, nome, cognome);

        if (!indirizzo.isBlank()) {
            Controllore.getInstance().controllaIndirizzo(indirizzo);
            cliente.setIndirizzo(indirizzo);
        }
        if (!telefono.isBlank()) {
            Controllore.getInstance().controllaNumero(telefono, 10);
            cliente.setTelefono(telefono);
        }
        setCliente(cliente);
        SerializerModifica.getInstance().modificaCliente(cliente);
    }

    /**
     * Consente di effettuare il logout resettando il gestore delle ricerche e impostando
     * l'attributo cliente null.
     */
    public void logout() {
        GestoreRicerche.getInstance().reset();
        setCliente(null);
    }

    /**
     * Consente di eliminare il {@link Cliente} corrente dal database.
     *
     * @throws SQLException in caso di problemi col database.
     */
    public void eliminaAccount() throws SQLException {
        SerializerElimina.getInstance().eliminaCliente(this.cliente.getID());
        setCliente(null);
        GestoreRicerche.getInstance().reset();
    }
}
