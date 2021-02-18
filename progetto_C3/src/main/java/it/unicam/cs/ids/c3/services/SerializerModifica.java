package it.unicam.cs.ids.c3.services;

import it.unicam.cs.ids.c3.model.Prodotto;

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
        String sql = "UPDATE prodotto VALUES (?,?,?,?,?)";
        PreparedStatement preparedStatement = DBManager.getInstance().getPreparedStatement(sql);
        preparedStatement.setString(1, prodotto.getID());
        preparedStatement.setString(2, prodotto.getNome());
        preparedStatement.setDouble(3, prodotto.getPrezzo());
        preparedStatement.setInt(4, prodotto.getQuantita());
        preparedStatement.setString(5, prodotto.getIDCommerciante());
        preparedStatement.executeUpdate();
        DBManager.getInstance().disconnect(preparedStatement);
    }
}
