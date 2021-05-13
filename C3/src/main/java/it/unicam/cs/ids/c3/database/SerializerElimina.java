package it.unicam.cs.ids.c3.database;

import it.unicam.cs.ids.c3.prodotto.Prodotto;
import it.unicam.cs.ids.c3.prodotto.ProdottoInterface;
import it.unicam.cs.ids.c3.promozione.Promozione;
import it.unicam.cs.ids.c3.promozione.PromozioneInterface;
import it.unicam.cs.ids.c3.recensione.Recensione;
import it.unicam.cs.ids.c3.recensione.RecensioneInterface;
import it.unicam.cs.ids.c3.ritiro.Ritiro;
import it.unicam.cs.ids.c3.ritiro.RitiroInterface;
import it.unicam.cs.ids.c3.utenti.cliente.ClienteInterface;
import it.unicam.cs.ids.c3.utenti.commerciante.CommercianteInterface;
import it.unicam.cs.ids.c3.utenti.corriere.CorriereInterface;
import it.unicam.cs.ids.c3.utenti.puntoRitiro.PuntoRitiroInterface;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * Questa classe e' un singleton ed ha la responsabilita' di eliminare record gia' presenti nel database.
 */
public class SerializerElimina {
    private static SerializerElimina instance;

    /**
     * Costruttore privato usato solamente all'interno di questa classe.
     */
    private SerializerElimina() {
    }

    /**
     * Metodo getter per l'attributo instance. Se instance è nulla, viene inizializzata.
     *
     * @return l'attributo instance.
     */
    public static SerializerElimina getInstance() {
        if (Objects.isNull(instance))
            instance = new SerializerElimina();
        return instance;
    }


    /**
     * Elimina dal database il {@link ProdottoInterface} relativo all'ID passato in input.
     *
     * @param IDProdotto - L'ID del {@link ProdottoInterface} da eliminare.
     * @throws SQLException in caso di problemi col database.
     */
    public void eliminaProdotto(String IDProdotto) throws SQLException {
        String sql = "delete from ritiro_has_prodotto where prodottoInVendita_ID = ?";
        PreparedStatement preparedStatement = DBManager.getInstance().getPreparedStatement(sql);
        preparedStatement.setString(1, IDProdotto);
        preparedStatement.executeUpdate();
        DBManager.getInstance().disconnect(preparedStatement);

        sql = "delete from promozione_has_prodotto where prodottoInVendita_ID = ?";
        preparedStatement = DBManager.getInstance().getPreparedStatement(sql);
        preparedStatement.setString(1, IDProdotto);
        preparedStatement.executeUpdate();
        DBManager.getInstance().disconnect(preparedStatement);

        sql = "delete from prodotto where ID = ?";
        preparedStatement = DBManager.getInstance().getPreparedStatement(sql);
        preparedStatement.setString(1, IDProdotto);
        preparedStatement.executeUpdate();
        DBManager.getInstance().disconnect(preparedStatement);
    }

    /**
     * Elimina dal database il {@link ClienteInterface} relativo all'ID passato in input.
     *
     * @param IDCliente - L'ID del {@link ClienteInterface} da eliminare.
     * @throws SQLException in caso di problemi col database.
     */
    public void eliminaCliente(String IDCliente) throws SQLException {
        String sql = "select * from recensione where cliente_ID = \"" + IDCliente + "\";";
        ResultSet resultSet = DBManager.getInstance().executeQuery(sql);
        List<Recensione> recensioni = new ArrayList<>(Deserializer.getInstance().deserializzaRecensioni(resultSet));

        for (Recensione r : recensioni)
            eliminaRecensione(r.getID());
        sql = "select * from ritiro where cliente_ID =\"" + IDCliente + "\";";
        ResultSet resultSet2 = DBManager.getInstance().executeQuery(sql);
        List<Ritiro> ritiri = new ArrayList<>(Deserializer.getInstance().deserializzaRitiri(resultSet2));

        for (Ritiro r : ritiri)
            eliminaRitiro(r.getID());
        sql = "delete from cliente where ID = ?";
        PreparedStatement preparedStatement = DBManager.getInstance().getPreparedStatement(sql);
        preparedStatement.setString(1, IDCliente);
        preparedStatement.executeUpdate();
        DBManager.getInstance().disconnect(preparedStatement);
    }

    /**
     * Elimina dal database la {@link RecensioneInterface} relativo all'ID passato in input.
     *
     * @param IDRecensione - L'ID della {@link RecensioneInterface} da eliminare.
     * @throws SQLException in caso di problemi col database.
     */
    public void eliminaRecensione(String IDRecensione) throws SQLException {
        String sql = "delete from recensione where ID = ?";
        PreparedStatement preparedStatement = DBManager.getInstance().getPreparedStatement(sql);
        preparedStatement.setString(1, IDRecensione);
        preparedStatement.executeUpdate();
        DBManager.getInstance().disconnect(preparedStatement);
    }

    /**
     * Elimina dal database il {@link RitiroInterface} relativo all'ID passato in input.
     *
     * @param IDRitiro - L'ID del {@link RitiroInterface} da eliminare.
     * @throws SQLException in caso di problemi col database.
     */
    public void eliminaRitiro(String IDRitiro) throws SQLException {
        String sql = "delete from ritiro_has_prodotto where ritiro_ID = ?";
        PreparedStatement preparedStatement = DBManager.getInstance().getPreparedStatement(sql);
        preparedStatement.setString(1, IDRitiro);
        preparedStatement.executeUpdate();

        sql = "delete from ritiro where ID = ?";
        preparedStatement = DBManager.getInstance().getPreparedStatement(sql);
        preparedStatement.setString(1, IDRitiro);
        preparedStatement.executeUpdate();
    }

    /**
     * Elimina dal database il {@link PuntoRitiroInterface} relativo all'ID passato in input.
     *
     * @param IDPuntoRitiro - L'ID del {@link PuntoRitiroInterface} da eliminare.
     * @throws SQLException in caso di problemi col database.
     */
    public void eliminaPuntoDiRitiro(String IDPuntoRitiro) throws SQLException {
        String sql = "delete from punto_ritiro where ID = ?";
        PreparedStatement preparedStatement = DBManager.getInstance().getPreparedStatement(sql);
        preparedStatement.setString(1, IDPuntoRitiro);
        preparedStatement.executeUpdate();
        DBManager.getInstance().disconnect(preparedStatement);
    }

    /**
     * Elimina dal database il {@link CorriereInterface} relativo all'ID passato in input.
     *
     * @param IDCorriere - L'ID del {@link CorriereInterface} da eliminare.
     * @throws SQLException in caso di problemi col database.
     */
    public void eliminaCorriere(String IDCorriere) throws SQLException {
        String sql = "select * from ritiro where corriere_ID = \"" + IDCorriere + "\";";
        ResultSet resultSet = DBManager.getInstance().executeQuery(sql);
        List<Ritiro> ritiri = new ArrayList<>(Deserializer.getInstance().deserializzaRitiri(resultSet));
        for (Ritiro r : ritiri)
            eliminaRitiro(r.getID());

        sql = "delete from corriere where ID = ?";
        PreparedStatement preparedStatement = DBManager.getInstance().getPreparedStatement(sql);
        preparedStatement.setString(1, IDCorriere);
        preparedStatement.executeUpdate();
    }

    /**
     * Elimina dal database il {@link CommercianteInterface} relativo all'ID passato in input.
     *
     * @param IDCommerciante - L'ID del {@link CommercianteInterface} da eliminare.
     * @throws SQLException in caso di problemi col database.
     */
    public void eliminaCommerciante(String IDCommerciante) throws SQLException {
        String sql = "select * from recensione where commerciante_ID = \"" + IDCommerciante + "\";";
        ResultSet resultSet = DBManager.getInstance().executeQuery(sql);
        List<Recensione> recensioni = new ArrayList<>(Deserializer.getInstance().deserializzaRecensioni(resultSet));
        for (Recensione p : recensioni)
            eliminaRecensione(p.getID());

        sql = "select * from promozione where commerciante_ID = \"" + IDCommerciante + "\";";
        resultSet = DBManager.getInstance().executeQuery(sql);
        List<Promozione> promozioni = new ArrayList<>(Deserializer.getInstance().deserializzaPromozioni(resultSet));
        for (Promozione p : promozioni)
            eliminaPromozione(p.getID());

        sql = "select * from ritiro where commerciante_ID = \"" + IDCommerciante + "\";";
        resultSet = DBManager.getInstance().executeQuery(sql);
        List<Ritiro> ritiri = new ArrayList<>(Deserializer.getInstance().deserializzaRitiri(resultSet));
        for (Ritiro r : ritiri)
            eliminaRitiro(r.getID());

        sql = "select * from prodotto where commerciante_ID = \"" + IDCommerciante + "\";";
        resultSet = DBManager.getInstance().executeQuery(sql);
        List<Prodotto> prodotti = new ArrayList<>(Deserializer.getInstance().deserializzaProdotti(resultSet));
        for (Prodotto p : prodotti)
            eliminaProdotto(p.getID());

        sql = "delete from commerciante where ID = ?";
        PreparedStatement preparedStatement = DBManager.getInstance().getPreparedStatement(sql);
        preparedStatement.setString(1, IDCommerciante);
        preparedStatement.executeUpdate();
        DBManager.getInstance().disconnect(preparedStatement);
    }

    /**
     * Elimina dal database la {@link PromozioneInterface} relativo all'ID passato in input.
     *
     * @param IDpromozione - L'ID del {@link PromozioneInterface} da eliminare.
     * @throws SQLException in caso di problemi col database.
     */
    public void eliminaPromozione(String IDpromozione) throws SQLException {
        String sql = "delete from promozione_has_prodotto where promozione_ID = ?";
        PreparedStatement preparedStatement = DBManager.getInstance().getPreparedStatement(sql);
        preparedStatement.setString(1, IDpromozione);
        preparedStatement.executeUpdate();
        DBManager.getInstance().disconnect(preparedStatement);

        sql = "delete from promozione where ID = ?";
        preparedStatement = DBManager.getInstance().getPreparedStatement(sql);
        preparedStatement.setString(1, IDpromozione);
        preparedStatement.executeUpdate();
        DBManager.getInstance().disconnect(preparedStatement);
    }
}
