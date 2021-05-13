package it.unicam.cs.ids.c3.database;

import it.unicam.cs.ids.c3.prodotto.Prodotto;
import it.unicam.cs.ids.c3.prodotto.ProdottoInterface;
import it.unicam.cs.ids.c3.promozione.PromozioneInterface;
import it.unicam.cs.ids.c3.recensione.RecensioneInterface;
import it.unicam.cs.ids.c3.ritiro.RitiroInterface;
import it.unicam.cs.ids.c3.ritiro.StatoTracking;
import it.unicam.cs.ids.c3.utenti.cliente.ClienteInterface;
import it.unicam.cs.ids.c3.utenti.commerciante.CommercianteInterface;
import it.unicam.cs.ids.c3.utenti.corriere.CorriereInterface;
import it.unicam.cs.ids.c3.utenti.puntoRitiro.PuntoRitiroInterface;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.GregorianCalendar;
import java.util.Objects;

/**
 * Questa classe e' un singleton ed ha la responsabilita' di modificare record gia' presenti nel database.
 */
public class SerializerModifica {
    private static SerializerModifica instance;

    /**
     * Costruttore privato usato solamente all'interno di questa classe.
     */
    private SerializerModifica() {
    }

    /**
     * Metodo getter per l'attributo instance. Se instance è nulla, viene inizializzata.
     *
     * @return l'attributo instance.
     */
    public static SerializerModifica getInstance() {
        if (Objects.isNull(instance))
            instance = new SerializerModifica();
        return instance;
    }

    /**
     * Aggiorna nel database il {@link Prodotto} passato in input.
     *
     * @param prodotto - Il {@link Prodotto} da aggiornare.
     * @throws SQLException in caso di problemi col database.
     */
    public void modificaProdotto(ProdottoInterface prodotto) throws SQLException {
        String sql = "UPDATE prodotto SET nome = ?, prezzo = ?, quantita = ?, commerciante_ID = ?, URL_immagine = ? WHERE ID = ?;";

        PreparedStatement preparedStatement = DBManager.getInstance().getPreparedStatement(sql);
        preparedStatement.setString(1, prodotto.getNome());
        preparedStatement.setDouble(2, prodotto.getPrezzo());
        preparedStatement.setInt(3, prodotto.getQuantita());
        preparedStatement.setString(4, prodotto.getIDCommerciante());
        preparedStatement.setString(5, prodotto.getURLImmagine());
        preparedStatement.setString(6, prodotto.getID());
        preparedStatement.executeUpdate();
        DBManager.getInstance().disconnect(preparedStatement);
    }

    /**
     * Aggiorna nel database il {@link ClienteInterface} passato in input.
     *
     * @param cliente - Il {@link ClienteInterface} da aggiornare.
     * @throws SQLException in caso di problemi col database.
     */
    public void modificaCliente(ClienteInterface cliente) throws SQLException {
        String sql = "UPDATE cliente SET nome = ?, cognome = ?, username = ?, password = ?, indirizzo = ?, email = ?, telefono = ? WHERE ID = ?;";

        PreparedStatement preparedStatement = DBManager.getInstance().getPreparedStatement(sql);
        preparedStatement.setString(1, cliente.getNome());
        preparedStatement.setString(2, cliente.getCognome());
        preparedStatement.setString(3, cliente.getUsername());
        preparedStatement.setString(4, cliente.getPassword());
        preparedStatement.setString(5, cliente.getIndirizzo());
        preparedStatement.setString(6, cliente.getEmail());
        preparedStatement.setString(7, cliente.getTelefono());
        preparedStatement.setString(8, cliente.getID());
        preparedStatement.executeUpdate();
        DBManager.getInstance().disconnect(preparedStatement);
    }

    /**
     * Aggiorna nel database il {@link CommercianteInterface} passato in input.
     *
     * @param commerciante - Il {@link CommercianteInterface} da aggiornare.
     * @throws SQLException in caso di problemi col database.
     */
    public void modificaCommerciante(CommercianteInterface commerciante) throws SQLException {
        String sql = "UPDATE commerciante SET username = ?, password = ?, indirizzo = ?, email = ?, telefono = ?, ragioneSociale = ? WHERE ID = ?;";

        PreparedStatement preparedStatement = DBManager.getInstance().getPreparedStatement(sql);
        preparedStatement.setString(1, commerciante.getUsername());
        preparedStatement.setString(2, commerciante.getPassword());
        preparedStatement.setString(3, commerciante.getIndirizzo());
        preparedStatement.setString(4, commerciante.getEmail());
        preparedStatement.setString(5, commerciante.getTelefono());
        preparedStatement.setString(6, commerciante.getRagioneSociale());
        preparedStatement.setString(7, commerciante.getID());
        preparedStatement.executeUpdate();
        DBManager.getInstance().disconnect(preparedStatement);
    }

    /**
     * Aggiorna nel database il {@link CorriereInterface} passato in input.
     *
     * @param corriere - Il {@link CorriereInterface} da aggiornare.
     * @throws SQLException in caso di problemi col database.
     */
    public void modificaCorriere(CorriereInterface corriere) throws SQLException {
        String sql = "UPDATE corriere SET username = ?, password = ?, telefono = ?, email = ?, indirizzo = ?, ragioneSociale = ?, stato = ? WHERE ID = ?;";

        PreparedStatement preparedStatement = DBManager.getInstance().getPreparedStatement(sql);
        preparedStatement.setString(1, corriere.getUsername());
        preparedStatement.setString(2, corriere.getPassword());
        preparedStatement.setString(3, corriere.getTelefono());
        preparedStatement.setString(4, corriere.getEmail());
        preparedStatement.setString(5, corriere.getIndirizzo());
        preparedStatement.setString(6, corriere.getRagioneSociale());
        preparedStatement.setString(7, corriere.getStato().name());
        preparedStatement.setString(8, corriere.getID());
        preparedStatement.executeUpdate();
        DBManager.getInstance().disconnect(preparedStatement);
    }

    /**
     * Aggiorna nel database il {@link PuntoRitiroInterface} passato in input.
     *
     * @param puntoRitiro - Il {@link PuntoRitiroInterface} da aggiornare.
     * @throws SQLException in caso di problemi col database.
     */
    public void modificaPuntoRitiro(PuntoRitiroInterface puntoRitiro) throws SQLException {
        String sql = "UPDATE punto_ritiro SET username = ?, password = ?, email = ?, indirizzo = ?, telefono = ?, ragione_sociale = ? WHERE ID = ?;";

        PreparedStatement preparedStatement = DBManager.getInstance().getPreparedStatement(sql);
        preparedStatement.setString(1, puntoRitiro.getUsername());
        preparedStatement.setString(2, puntoRitiro.getPassword());
        preparedStatement.setString(3, puntoRitiro.getEmail());
        preparedStatement.setString(4, puntoRitiro.getIndirizzo());
        preparedStatement.setString(5, puntoRitiro.getTelefono());
        preparedStatement.setString(6, puntoRitiro.getRagioneSociale());
        preparedStatement.setString(7, puntoRitiro.getID());
        preparedStatement.executeUpdate();
        DBManager.getInstance().disconnect(preparedStatement);
    }

    /**
     * Aggiorna nel database la {@link PromozioneInterface} passata in input.
     *
     * @param promozione - La {@link PromozioneInterface} da aggiornare.
     * @throws SQLException in caso di problemi col database.
     */
    public void modificaPromozione(PromozioneInterface promozione) throws SQLException {
        String sql = "UPDATE promozione SET nome = ?, descrizione = ?, data_inizio = ?, data_scadenza = ?, commerciante_ID = ? WHERE ID = ?;";

        PreparedStatement preparedStatement = DBManager.getInstance().getPreparedStatement(sql);
        preparedStatement.setString(1, promozione.getNome());
        preparedStatement.setString(2, promozione.getDescrizione());
        preparedStatement.setDate(3, new java.sql.Date(promozione.getDataInizio().getTimeInMillis()));
        preparedStatement.setDate(4, new java.sql.Date(promozione.getDataScadenza().getTimeInMillis()));
        preparedStatement.setString(5, promozione.getIDCommerciante());
        preparedStatement.setString(6, promozione.getID());
        preparedStatement.executeUpdate();
        DBManager.getInstance().disconnect(preparedStatement);
    }

    /**
     * Aggiorna nel database la {@link RecensioneInterface} passato in input.
     *
     * @param recensione - La {@link RecensioneInterface} da aggiornare.
     * @throws SQLException in caso di problemi col database.
     */
    public void modificaRecensione(RecensioneInterface recensione) throws SQLException {
        String sql = "UPDATE recensione SET titolo = ?, testo = ?, voto_recensione = ?, prodottoInVendita_ID = ?, commerciante_ID = ?, cliente_ID = ? WHERE ID = ?;";

        PreparedStatement preparedStatement = DBManager.getInstance().getPreparedStatement(sql);
        preparedStatement.setString(1, recensione.getTitolo());
        preparedStatement.setString(2, recensione.getTesto());
        preparedStatement.setString(3, recensione.getVotoRecensioni().name());
        preparedStatement.setString(4, recensione.getIDProdotto());
        preparedStatement.setString(5, recensione.getIDCommerciante());
        preparedStatement.setString(6, recensione.getIDCliente());
        preparedStatement.setString(7, recensione.getID());
        preparedStatement.executeUpdate();
        DBManager.getInstance().disconnect(preparedStatement);
    }

    /**
     * Aggiorna nel database il {@link RitiroInterface} passato in input.
     *
     * @param ritiro - Il {@link RitiroInterface} da aggiornare.
     * @throws SQLException in caso di problemi col database.
     */
    public void modificaRitiro(RitiroInterface ritiro) throws SQLException {
        String sql = "UPDATE ritiro SET data_consegna = ?, ritirato = ?, stato_tracking = ? WHERE ID = ?;";

        PreparedStatement preparedStatement = DBManager.getInstance().getPreparedStatement(sql);

        if (ritiro.getStatoTracking().equals(StatoTracking.CONSEGNATO)) {
            GregorianCalendar dataConsegna = new GregorianCalendar();
            preparedStatement.setDate(1, new java.sql.Date(dataConsegna.getTimeInMillis()));
        } else
            preparedStatement.setDate(1, null);

        preparedStatement.setBoolean(2, ritiro.isRitirato());
        preparedStatement.setString(3, ritiro.getStatoTracking().name());
        preparedStatement.setString(4, ritiro.getID());
        preparedStatement.executeUpdate();
        DBManager.getInstance().disconnect(preparedStatement);
    }
}
