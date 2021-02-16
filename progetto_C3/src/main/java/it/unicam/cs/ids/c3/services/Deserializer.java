package it.unicam.cs.ids.c3.services;

import it.unicam.cs.ids.c3.model.Prodotto;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Deserializer {
    private static Deserializer instance;

    private Deserializer() {
    }

    public static Deserializer getInstance() {
        if (Objects.isNull(instance))
            instance = new Deserializer();
        return instance;
    }

    public List<Prodotto> deserializzaProdotti(ResultSet resultSet) throws SQLException {
        List<Prodotto> listaProdotti = new ArrayList<>();

        while (resultSet.next()) {
            Prodotto prodotto = new Prodotto(resultSet.getString("ID"),
                    resultSet.getString("nome"),
                    resultSet.getDouble("prezzo"),
                    resultSet.getInt("quantita"),
                    resultSet.getString("commerciante_ID"));
            listaProdotti.add(prodotto);

        }

        return listaProdotti;
    }
}
