package it.unicam.cs.ids.c3.database;

import it.unicam.cs.ids.c3.prodotto.Prodotto;
import it.unicam.cs.ids.c3.promozione.Promozione;
import it.unicam.cs.ids.c3.promozione.PromozioneInterface;
import it.unicam.cs.ids.c3.recensione.Recensione;
import it.unicam.cs.ids.c3.recensione.RecensioneInterface;
import it.unicam.cs.ids.c3.ritiro.Ritiro;
import it.unicam.cs.ids.c3.ritiro.RitiroInterface;
import it.unicam.cs.ids.c3.utenti.cliente.Cliente;
import it.unicam.cs.ids.c3.utenti.cliente.ClienteInterface;
import it.unicam.cs.ids.c3.utenti.commerciante.Commerciante;
import it.unicam.cs.ids.c3.utenti.commerciante.CommercianteInterface;
import it.unicam.cs.ids.c3.utenti.corriere.Corriere;
import it.unicam.cs.ids.c3.utenti.corriere.CorriereInterface;
import it.unicam.cs.ids.c3.utenti.puntoRitiro.PuntoRitiro;
import it.unicam.cs.ids.c3.utenti.puntoRitiro.PuntoRitiroInterface;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Objects;

/**
 * Questa classe e' un singleton ed ha la responsabilita' di serializzare nuovi oggetti ed aggiungerli nel database.
 */
public class SerializerAggiunta {
    private static SerializerAggiunta instance;

    /**
     * Costruttore privato usato solamente all'interno di questa classe.
     */
    private SerializerAggiunta() {
    }

    /**
     * Metodo getter per l'attributo instance. Se instance è nulla, viene inizializzata.
     *
     * @return l'attributo instance.
     */
    public static SerializerAggiunta getInstance() {
        if (Objects.isNull(instance))
            instance = new SerializerAggiunta();
        return instance;
    }

    /**
     * Serializza un nuovo {@link Prodotto} nel database.
     *
     * @param prodotto - Il nuovo {@link Prodotto} da serializzare.
     * @throws SQLException in caso di problemi col database.
     */
    public void serializzaProdotto(Prodotto prodotto) throws SQLException {
        String sql = "INSERT INTO prodotto VALUES (?,?,?,?,?,?)";
        PreparedStatement preparedStatement = DBManager.getInstance().getPreparedStatement(sql);
        preparedStatement.setString(1, prodotto.getID());
        preparedStatement.setString(2, prodotto.getNome());
        preparedStatement.setDouble(3, prodotto.getPrezzo());
        preparedStatement.setInt(4, prodotto.getQuantita());
        preparedStatement.setString(5, prodotto.getIDCommerciante());
        preparedStatement.setString(6, prodotto.getURLImmagine());
        preparedStatement.executeUpdate();
        DBManager.getInstance().disconnect(preparedStatement);
    }

    /**
     * Serializza un nuovo {@link ClienteInterface} nel database.
     *
     * @param cliente - Il nuovo {@link ClienteInterface} da serializzare.
     * @throws SQLException in caso di problemi col database.
     */
    public void serializzaCliente(ClienteInterface cliente) throws SQLException {
        String sql = "INSERT INTO cliente VALUES (?,?,?,?,?,?,?,?)";
        PreparedStatement preparedStatement = DBManager.getInstance().getPreparedStatement(sql);
        preparedStatement.setString(1, cliente.getID());
        preparedStatement.setString(2, cliente.getNome());
        preparedStatement.setString(3, cliente.getCognome());
        preparedStatement.setString(4, cliente.getUsername());
        preparedStatement.setString(5, cliente.getPassword());
        preparedStatement.setString(6, cliente.getIndirizzo());
        preparedStatement.setString(7, cliente.getEmail());
        preparedStatement.setString(8, cliente.getTelefono());
        preparedStatement.executeUpdate();
        DBManager.getInstance().disconnect(preparedStatement);
    }

    /**
     * Serializza un nuovo {@link CommercianteInterface} nel database.
     *
     * @param commerciante - Il nuovo {@link CommercianteInterface} da serializzare.
     * @throws SQLException in caso di problemi col database.
     */
    public void serializzaCommerciante(CommercianteInterface commerciante) throws SQLException {
        String sql = "INSERT INTO commerciante VALUES (?,?,?,?,?,?,?)";
        PreparedStatement preparedStatement = DBManager.getInstance().getPreparedStatement(sql);
        preparedStatement.setString(1, commerciante.getID());
        preparedStatement.setString(2, commerciante.getUsername());
        preparedStatement.setString(3, commerciante.getPassword());
        preparedStatement.setString(4, commerciante.getIndirizzo());
        preparedStatement.setString(5, commerciante.getEmail());
        preparedStatement.setString(6, commerciante.getTelefono());
        preparedStatement.setString(7, commerciante.getRagioneSociale());
        preparedStatement.executeUpdate();
        DBManager.getInstance().disconnect(preparedStatement);
    }

    /**
     * Serializza un nuovo {@link CorriereInterface} nel database.
     *
     * @param corriere - Il nuovo {@link CorriereInterface} da serializzare.
     * @throws SQLException in caso di problemi col database.
     */
    public void serializzaCorriere(CorriereInterface corriere) throws SQLException {
        String sql = "INSERT INTO corriere VALUES (?,?,?,?,?,?,?,?)";
        PreparedStatement preparedStatement = DBManager.getInstance().getPreparedStatement(sql);
        preparedStatement.setString(1, corriere.getID());
        preparedStatement.setString(2, corriere.getUsername());
        preparedStatement.setString(3, corriere.getPassword());
        preparedStatement.setString(4, corriere.getTelefono());
        preparedStatement.setString(5, corriere.getEmail());
        preparedStatement.setString(6, corriere.getIndirizzo());
        preparedStatement.setString(7, corriere.getRagioneSociale());
        preparedStatement.setString(8, corriere.getStato().name());
        preparedStatement.executeUpdate();
        DBManager.getInstance().disconnect(preparedStatement);
    }

    /**
     * Serializza un nuovo {@link PuntoRitiroInterface} nel database.
     *
     * @param puntoRitiro - Il nuovo {@link PuntoRitiroInterface} da serializzare.
     * @throws SQLException in caso di problemi col database.
     */
    public void serializzaPuntoRitiro(PuntoRitiroInterface puntoRitiro) throws SQLException {
        String sql = "INSERT INTO punto_ritiro VALUES (?,?,?,?,?,?,?)";
        PreparedStatement preparedStatement = DBManager.getInstance().getPreparedStatement(sql);
        preparedStatement.setString(1, puntoRitiro.getID());
        preparedStatement.setString(2, puntoRitiro.getUsername());
        preparedStatement.setString(3, puntoRitiro.getPassword());
        preparedStatement.setString(4, puntoRitiro.getEmail());
        preparedStatement.setString(5, puntoRitiro.getIndirizzo());
        preparedStatement.setString(6, puntoRitiro.getTelefono());
        preparedStatement.setString(7, puntoRitiro.getRagioneSociale());
        preparedStatement.executeUpdate();
        DBManager.getInstance().disconnect(preparedStatement);
    }

    /**
     * Serializza una nuova {@link PromozioneInterface} nel database.
     *
     * @param promozione - La nuova {@link PromozioneInterface} da serializzare.
     * @throws SQLException in caso di problemi col database.
     */
    public void serializzaPromozione(PromozioneInterface promozione) throws SQLException {
        String sql = "INSERT INTO promozione VALUES (?,?,?,?,?,?)";
        PreparedStatement preparedStatement = DBManager.getInstance().getPreparedStatement(sql);
        preparedStatement.setString(1, promozione.getID());
        preparedStatement.setString(2, promozione.getNome());
        preparedStatement.setString(3, promozione.getDescrizione());
        preparedStatement.setDate(4, new java.sql.Date(promozione.getDataInizio().getTimeInMillis()));
        preparedStatement.setDate(5, new java.sql.Date(promozione.getDataScadenza().getTimeInMillis()));
        preparedStatement.setString(6, promozione.getIDCommerciante());
        preparedStatement.executeUpdate();
        DBManager.getInstance().disconnect(preparedStatement);

        sql = "INSERT INTO promozione_has_prodotto VALUES (?,?)";

        for (String s : promozione.getListaIDProdotti()) {
            preparedStatement = DBManager.getInstance().getPreparedStatement(sql);
            preparedStatement.setString(1, promozione.getID());
            preparedStatement.setString(2, s);
            preparedStatement.executeUpdate();
            DBManager.getInstance().disconnect(preparedStatement);
        }
    }

    /**
     * Serializza una nuova {@link RecensioneInterface} nel database.
     *
     * @param recensione - La nuova {@link RecensioneInterface} da serializzare.
     * @throws SQLException in caso di problemi col database.
     */
    public void serializzaRecensione(RecensioneInterface recensione) throws SQLException {
        String sql = "INSERT INTO recensione VALUES (?,?,?,?,?,?,?)";
        PreparedStatement preparedStatement = DBManager.getInstance().getPreparedStatement(sql);
        preparedStatement.setString(1, recensione.getID());
        preparedStatement.setString(2, recensione.getTitolo());
        preparedStatement.setString(3, recensione.getTesto());
        preparedStatement.setString(4, recensione.getVotoRecensioni().name());
        preparedStatement.setString(5, recensione.getIDProdotto());
        preparedStatement.setString(6, recensione.getIDCommerciante());
        preparedStatement.setString(7, recensione.getIDCliente());
        preparedStatement.executeUpdate();
        DBManager.getInstance().disconnect(preparedStatement);
    }

    /**
     * Serializza un nuovo {@link RitiroInterface} nel database.
     *
     * @param ritiro - Il nuovo {@link RitiroInterface} da serializzare.
     * @throws SQLException in caso di problemi col database.
     */
    public void serializzaRitiro(RitiroInterface ritiro) throws SQLException {
        String sql = "INSERT INTO ritiro VALUES (?,?,?,?,?,?,?,?,?,?,?)";
        PreparedStatement preparedStatement = DBManager.getInstance().getPreparedStatement(sql);
        preparedStatement.setString(1, ritiro.getID());
        preparedStatement.setString(2, ritiro.getDestinazione());
        preparedStatement.setString(3, ritiro.getCodiceRitiro());
        preparedStatement.setDate(4, new java.sql.Date(ritiro.getDataPrenotazione().getTimeInMillis()));
        if (Objects.isNull(ritiro.getDataConsegna()))
            preparedStatement.setDate(5, null);
        else
            preparedStatement.setDate(5, new java.sql.Date(ritiro.getDataConsegna().getTimeInMillis()));
        preparedStatement.setBoolean(6, ritiro.isRitirato());
        preparedStatement.setString(7, ritiro.getTipoConsegna().name());
        preparedStatement.setString(8, ritiro.getIDCommerciante());
        preparedStatement.setString(9, ritiro.getIDCliente());
        preparedStatement.setString(10, ritiro.getIDCorriere());
        preparedStatement.setString(11, ritiro.getStatoTracking().name());
        preparedStatement.executeUpdate();
        DBManager.getInstance().disconnect(preparedStatement);

        sql = "INSERT INTO ritiro_has_prodotto VALUES (?,?)";

        for (String s : ritiro.getListaIDProdotti()) {
            preparedStatement = DBManager.getInstance().getPreparedStatement(sql);
            preparedStatement.setString(1, s);
            preparedStatement.setString(2, ritiro.getID());
            preparedStatement.executeUpdate();
            DBManager.getInstance().disconnect(preparedStatement);
        }
    }
}
