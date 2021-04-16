package it.unicam.cs.ids.c3.prodotto;

import it.unicam.cs.ids.c3.database.SerializerAggiunta;
import it.unicam.cs.ids.c3.database.SerializerElimina;
import it.unicam.cs.ids.c3.database.SerializerModifica;

import java.sql.SQLException;
import java.util.Objects;

public class GestoreProdotti {
    private static GestoreProdotti instance;

    private GestoreProdotti() {
    }

    public static GestoreProdotti getInstance() {
        if (Objects.isNull(instance))
            instance = new GestoreProdotti();
        return instance;
    }

    public void creaProdotto(String nome, Double prezzo, int quantita, String URLimmagine, String IDCommerciante) throws SQLException {
        SerializerAggiunta.getInstance().serializzaProdotto(new Prodotto(nome, prezzo, quantita, IDCommerciante, URLimmagine));
    }

    public void modificaProdotto(Prodotto prodotto) throws SQLException {
        SerializerModifica.getInstance().modificaProdotto(prodotto);
    }

    public void eliminaProdotto(String IDProdotto) throws SQLException {
        SerializerElimina.getInstance().eliminaProdotto(IDProdotto);
    }
}
