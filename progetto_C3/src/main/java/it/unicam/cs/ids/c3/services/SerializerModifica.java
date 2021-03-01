package it.unicam.cs.ids.c3.services;

import it.unicam.cs.ids.c3.model.*;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Objects;

public class SerializerModifica {
    private static SerializerModifica instance;

    private SerializerModifica() {
    }

    public static SerializerModifica getInstance() {
        if (Objects.isNull(instance))
            instance = new SerializerModifica();
        return instance;
    }

    public void modificaProdotto(Prodotto prodotto) throws SQLException {
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

    public void modificaCliente(Cliente cliente) throws SQLException {
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

    public void modificaCommerciante(Commerciante commerciante) throws SQLException {
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

    public void modificaCorriere(Corriere corriere) throws SQLException {
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

    public void modificaPuntoRitiro(PuntoRitiro puntoRitiro) throws SQLException {
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

    public void modificaPromozione(Promozione promozione) throws SQLException {
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

    public void modificaRecensione(Recensione recensione) throws SQLException {
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

    public void modificaRitiro(Ritiro ritiro) throws SQLException {
        String sql = "UPDATE ritiro SET destinazione = ?, codice_ritiro = ?, data_prenotazione = ?, data_consegna = ?, ritirato = ?, " +
                "tipo_consegna = ?, commerciante_ID = ?, cliente_ID = ?, corriere_ID = ?, stato_tracking = ? WHERE ID = ?;";

        PreparedStatement preparedStatement = DBManager.getInstance().getPreparedStatement(sql);
        preparedStatement.setString(1, ritiro.getDestinazione());
        preparedStatement.setString(2, ritiro.getCodiceRitiro());
        preparedStatement.setDate(3, new java.sql.Date(ritiro.getDataPrenotazione().getTimeInMillis()));
        if (!Objects.isNull(ritiro.getDataConsegna()))
            preparedStatement.setDate(4, new java.sql.Date(ritiro.getDataConsegna().getTimeInMillis()));
        else
            preparedStatement.setDate(4, null);

        preparedStatement.setBoolean(5, ritiro.isRitirato());
        preparedStatement.setString(6, ritiro.getTipoConsegna().name());
        preparedStatement.setString(7, ritiro.getIDCommerciante());
        preparedStatement.setString(8, ritiro.getIDCliente());
        preparedStatement.setString(9, ritiro.getIDCorriere());
        preparedStatement.setString(10, ritiro.getStato().name());
        preparedStatement.setString(11, ritiro.getID());
        preparedStatement.executeUpdate();
        DBManager.getInstance().disconnect(preparedStatement);
    }
}
