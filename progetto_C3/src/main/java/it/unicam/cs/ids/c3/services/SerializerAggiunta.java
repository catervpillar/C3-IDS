package it.unicam.cs.ids.c3.services;

import it.unicam.cs.ids.c3.model.*;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Objects;

public class SerializerAggiunta {
    private static SerializerAggiunta instance;

    private SerializerAggiunta() {
    }

    public static SerializerAggiunta getInstance() {
        if (Objects.isNull(instance))
            instance = new SerializerAggiunta();
        return instance;
    }

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

    public void serializzaCliente(Cliente cliente) throws SQLException {
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

    public void serializzaCommerciante(Commerciante commerciante) throws SQLException {
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

    public void serializzaCorriere(Corriere corriere) throws SQLException {
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

    public void serializzaPuntoRitiro(PuntoRitiro puntoRitiro) throws SQLException {
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

    public void serializzaPromozione(Promozione promozione) throws SQLException {
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

    public void serializzaRecensione(Recensione recensione) throws SQLException {
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

    public void serializzaRitiro(Ritiro ritiro) throws SQLException {
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
