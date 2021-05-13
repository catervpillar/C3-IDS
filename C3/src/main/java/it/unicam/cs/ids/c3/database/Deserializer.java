package it.unicam.cs.ids.c3.database;

import it.unicam.cs.ids.c3.prodotto.Prodotto;
import it.unicam.cs.ids.c3.promozione.Promozione;
import it.unicam.cs.ids.c3.recensione.Recensione;
import it.unicam.cs.ids.c3.recensione.VotoRecensioni;
import it.unicam.cs.ids.c3.ritiro.Ritiro;
import it.unicam.cs.ids.c3.ritiro.StatoTracking;
import it.unicam.cs.ids.c3.ritiro.TipoConsegna;
import it.unicam.cs.ids.c3.utenti.cliente.Cliente;
import it.unicam.cs.ids.c3.utenti.cliente.ClienteInterface;
import it.unicam.cs.ids.c3.utenti.cliente.ControllerCliente;
import it.unicam.cs.ids.c3.utenti.commerciante.Commerciante;
import it.unicam.cs.ids.c3.utenti.commerciante.CommercianteInterface;
import it.unicam.cs.ids.c3.utenti.commerciante.ControllerCommerciante;
import it.unicam.cs.ids.c3.utenti.corriere.ControllerCorriere;
import it.unicam.cs.ids.c3.utenti.corriere.CorriereInterface;
import it.unicam.cs.ids.c3.utenti.puntoRitiro.ControllerPuntoRitiro;
import it.unicam.cs.ids.c3.utenti.corriere.Corriere;
import it.unicam.cs.ids.c3.utenti.puntoRitiro.PuntoRitiro;
import it.unicam.cs.ids.c3.utenti.corriere.StatoCorriere;
import it.unicam.cs.ids.c3.utenti.puntoRitiro.PuntoRitiroInterface;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

/**
 * Questa classe e' un singleton ed ha la responsabilita' di deserializzare i record ottenuti dall'esecuzione
 * di query nel database.
 */
public class Deserializer {
    private static Deserializer instance;

    /**
     * Costruttore privato usato solamente all'interno di questa classe.
     */
    private Deserializer() {
    }

    /**
     * Metodo getter per l'attributo instance. Se instance è nulla, viene inizializzata.
     *
     * @return l'attributo instance.
     */
    public static Deserializer getInstance() {
        if (Objects.isNull(instance))
            instance = new Deserializer();
        return instance;
    }

    /**
     * Ritorna una lista di prodotti deserializzati a partire da un {@link ResultSet}.
     *
     * @param resultSet - Il risultato della query.
     * @return la lista dei {@link Prodotto} deserializzati.
     * @throws SQLException in caso di problemi col database.
     */
    public List<Prodotto> deserializzaProdotti(ResultSet resultSet) throws SQLException {
        List<Prodotto> listaProdotti = new ArrayList<>();

        while (resultSet.next()) {
            Prodotto prodotto = new Prodotto(resultSet.getString("ID"),
                    resultSet.getString("nome"),
                    resultSet.getDouble("prezzo"),
                    resultSet.getInt("quantita"),
                    resultSet.getString("commerciante_ID"),
                    resultSet.getString("URL_immagine"));
            listaProdotti.add(prodotto);
        }

        DBManager.getInstance().disconnect(resultSet);
        return listaProdotti;
    }

    /**
     * Ritorna una lista di ID di prodotti deserializzati a partire da un {@link ResultSet}.
     *
     * @param resultSet - Il risultato della query.
     * @return la lista degli ID di {@link Prodotto} deserializzati.
     * @throws SQLException in caso di problemi col database.
     */
    public List<String> deserializzaIDProdotti(ResultSet resultSet) throws SQLException {
        List<String> listaIDProdotti = new ArrayList<>();

        while (resultSet.next()) {
            listaIDProdotti.add(resultSet.getString("prodottoInVendita_ID"));
        }

        DBManager.getInstance().disconnect(resultSet);
        return listaIDProdotti;
    }

    /**
     * Ritorna un {@link ClienteInterface} deserializzato a partire da un {@link ResultSet}.
     *
     * @param resultSet - Il risultato della query.
     * @return il {@link ClienteInterface} deserializzato.
     * @throws SQLException in caso di problemi col database.
     */
    public ClienteInterface deserializzaCliente(ResultSet resultSet) throws SQLException {
        ClienteInterface cliente = new Cliente(resultSet.getString("ID"),
                resultSet.getString("username"),
                resultSet.getString("password"),
                resultSet.getString("email"),
                resultSet.getString("nome"),
                resultSet.getString("cognome"));
        cliente.setTelefono(resultSet.getString("telefono"));
        cliente.setIndirizzo(resultSet.getString("indirizzo"));

        DBManager.getInstance().disconnect(resultSet);
        return cliente;
    }

    /**
     * Ritorna una lista di commercianti deserializzati a partire da un {@link ResultSet}.
     *
     * @param resultSet - Il risultato della query.
     * @return la lista di commercianti deserializzati.
     * @throws SQLException in caso di problemi col database.
     */
    public List<CommercianteInterface> deserializzaCommercianti(ResultSet resultSet) throws SQLException {
        List<CommercianteInterface> listaCommercianti = new ArrayList<>();
        while (resultSet.next()) {
            CommercianteInterface commerciante = new Commerciante(resultSet.getString("ID"),
                    resultSet.getString("username"),
                    resultSet.getString("password"),
                    resultSet.getString("email"),
                    resultSet.getString("ragioneSociale"));
            commerciante.setTelefono(resultSet.getString("telefono"));
            commerciante.setIndirizzo(resultSet.getString("indirizzo"));
            listaCommercianti.add(commerciante);
        }

        DBManager.getInstance().disconnect(resultSet);
        return listaCommercianti;
    }

    /**
     * Ritorna una lista dei corrieri deserializzati a partire da un {@link ResultSet}.
     *
     * @param resultSet - Il risultato della query.
     * @return la lista dei corrieri deserializzati.
     * @throws SQLException in caso di problemi col database.
     */
    public List<CorriereInterface> deserializzaCorrieri(ResultSet resultSet) throws SQLException {
        List<CorriereInterface> listaCorriere = new ArrayList<>();
        while (resultSet.next()) {
            CorriereInterface corriere = new Corriere(resultSet.getString("ID"),
                    resultSet.getString("username"),
                    resultSet.getString("password"),
                    resultSet.getString("email"),
                    resultSet.getString("ragioneSociale"));
            corriere.setTelefono(resultSet.getString("telefono"));
            corriere.setIndirizzo(resultSet.getString("indirizzo"));
            corriere.setStato(StatoCorriere.valueOf(resultSet.getString("stato")));
            listaCorriere.add(corriere);
        }

        DBManager.getInstance().disconnect(resultSet);
        return listaCorriere;
    }

    /**
     * Ritorna una lista dei punti di ritiro deserializzati a partire da un {@link ResultSet}.
     *
     * @param resultSet - Il risultato della query.
     * @return la lista dei punti di ritiro deserializzati.
     * @throws SQLException in caso di problemi col database.
     */
    public List<PuntoRitiroInterface> deserializzaPuntiRitiro(ResultSet resultSet) throws SQLException {
        List<PuntoRitiroInterface> listaPuntiRitiro = new ArrayList<>();

        while (resultSet.next()) {
            PuntoRitiroInterface puntoRitiro = new PuntoRitiro(resultSet.getString("ID"),
                    resultSet.getString("username"),
                    resultSet.getString("password"),
                    resultSet.getString("email"),
                    resultSet.getString("ragione_sociale"));
            puntoRitiro.setTelefono(resultSet.getString("telefono"));
            puntoRitiro.setIndirizzo(resultSet.getString("indirizzo"));
            listaPuntiRitiro.add(puntoRitiro);
        }

        DBManager.getInstance().disconnect(resultSet);
        return listaPuntiRitiro;
    }

    /**
     * Ritorna una lista di promozioni deserializzate a partire da un {@link ResultSet}.
     *
     * @param resultSet - Il risultato della query.
     * @return la lista delle promozioni deserializzate.
     * @throws SQLException in caso di problemi col database.
     */
    public List<? extends Promozione> deserializzaPromozioni(ResultSet resultSet) throws SQLException {
        List<Promozione> listaPromozioni = new ArrayList<>();

        while (resultSet.next()) {
            GregorianCalendar dataInizio = new GregorianCalendar();
            dataInizio.setTime(resultSet.getDate("data_inizio"));
            GregorianCalendar dataScadenza = new GregorianCalendar();
            dataScadenza.setTime(resultSet.getDate("data_scadenza"));

            Promozione promozione = new Promozione(resultSet.getString("ID"),
                    resultSet.getString("nome"),
                    resultSet.getString("commerciante_ID"),
                    resultSet.getString("descrizione"),
                    dataInizio,
                    dataScadenza);

            ResultSet resultSet1 = DBManager.getInstance().executeQuery("select * from promozione_has_prodotto where promozione_ID = \""
                    + promozione.getID() + "\"");
            while (resultSet1.next()) {
                promozione.getListaIDProdotti().add(resultSet1.getString("prodottoInVendita_ID"));
            }
            listaPromozioni.add(promozione);
        }

        DBManager.getInstance().disconnect(resultSet);
        return listaPromozioni;
    }

    /**
     * Ritorna una lista delle recensioni deserializzate a partire da un {@link ResultSet}.
     *
     * @param resultSet - Il risultato della query.
     * @return la lista delle recensioni deserializzate.
     * @throws SQLException in caso di problemi col database.
     */
    public List<? extends Recensione> deserializzaRecensioni(ResultSet resultSet) throws SQLException {
        List<Recensione> listaRecensioni = new ArrayList<>();

        while (resultSet.next()) {
            Recensione recensione = new Recensione(resultSet.getString("ID"),
                    resultSet.getString("titolo"),
                    resultSet.getString("testo"),
                    resultSet.getString("cliente_ID"),
                    resultSet.getString("commerciante_ID"),
                    resultSet.getString("prodottoInVendita_ID"),
                    VotoRecensioni.valueOf(resultSet.getString("voto_recensione")));
            listaRecensioni.add(recensione);
        }

        DBManager.getInstance().disconnect(resultSet);
        return listaRecensioni;
    }

    /**
     * Ritorna una lista dei ritiri deserializzati a partire da un {@link ResultSet}.
     *
     * @param resultSet - Il risultato della query.
     * @return la lista dei ritiri deserializzati.
     * @throws SQLException in caso di problemi col database.
     */
    public List<? extends Ritiro> deserializzaRitiri(ResultSet resultSet) throws SQLException {
        List<Ritiro> listaRitiri = new ArrayList<>();

        while (resultSet.next()) {
            GregorianCalendar dataPrenotazione = new GregorianCalendar();
            dataPrenotazione.setTime(resultSet.getDate("data_prenotazione"));
            GregorianCalendar dataConsegna = new GregorianCalendar();
            if (!Objects.isNull(resultSet.getDate("data_consegna")))
                dataConsegna.setTime(resultSet.getDate("data_consegna"));
            else
                dataConsegna = null;

            Ritiro ritiro = new Ritiro(resultSet.getString("ID"),
                    resultSet.getString("commerciante_ID"),
                    resultSet.getString("cliente_ID"),
                    resultSet.getString("corriere_ID"),
                    resultSet.getString("destinazione"),
                    TipoConsegna.valueOf(resultSet.getString("tipo_consegna")));
            ritiro.setCodiceRitiro(resultSet.getString("codice_ritiro"));
            ritiro.setRitirato(resultSet.getBoolean("ritirato"));
            ritiro.setStatoTracking(StatoTracking.valueOf(resultSet.getString("stato_tracking")));
            ritiro.setDataPrenotazione(dataPrenotazione);
            ritiro.setDataConsegna(dataConsegna);

            listaRitiri.add(ritiro);
        }

        DBManager.getInstance().disconnect(resultSet);
        return listaRitiri;
    }

    /**
     * Cerca le credenziali passate in input nel database e ritorna il tipo di utente.
     *
     * @param username - L'username da cercare.
     * @param password - La password da cercare.
     * @return una stringa con il tipo di utente che intende loggarsi.
     * @throws SQLException in caso di problemi col database.
     */
    public String cercaUtente(String username, String password) throws SQLException {
        if (ControllerCliente.getInstance().loginCliente(username, password))
            return "cliente";
        else if (ControllerCommerciante.getInstance().loginCommerciante(username, password))
            return "commerciante";
        else if (ControllerCorriere.getInstance().loginCorriere(username, password))
            return "corriere";
        else if (ControllerPuntoRitiro.getInstance().loginPuntoRitiro(username, password))
            return "punto_ritiro";
        else throw new IllegalArgumentException("CREDENZIALI ERRATE");
    }
}
