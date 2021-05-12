package it.unicam.cs.ids.c3.prodotto;

import it.unicam.cs.ids.c3.database.SerializerAggiunta;
import it.unicam.cs.ids.c3.database.SerializerElimina;
import it.unicam.cs.ids.c3.database.SerializerModifica;
import java.sql.SQLException;
import java.util.Objects;
import it.unicam.cs.ids.c3.utenti.commerciante.Commerciante;

/**
 * Questa classe è un sigleton e si occupa di gestire i prodotti di un {@link Commerciante}.
 * Permette infatti la creazione, modifica e eliminazione dei prodotti.
 */

public class GestoreProdotti {
    private static GestoreProdotti instance;

    /**
     * Costruttore privato usato solamente all'interno di questa classe
     */
    private GestoreProdotti() {
    }

    /**
     * Metodo getter per l'attributo instance. Se instance è nulla, viene inizializzata.
     *
     * @return l'attributo instance.
     */
    public static GestoreProdotti getInstance() {
        if (Objects.isNull(instance))
            instance = new GestoreProdotti();
        return instance;
    }

    /**
     * Crea un {@link Prodotto} e successivamente lo fa serializzare nel database.
     *
     * @param nome - Il nome del {@link Prodotto}.
     * @param prezzo - Il prezzp del {@link Prodotto}.
     * @param quantita    - La quantita del {@link Prodotto}.
     * @param URLimmagine     - L'URL dell'immagine rappresentante il {@link Prodotto}.
     * @param IDCommerciante  - L'ID del {@link Commerciante} che vende il del {@link Prodotto}.
     * @throws SQLException in caso di errori col database.
     */
    public void creaProdotto(String nome, Double prezzo, int quantita, String URLimmagine, String IDCommerciante) throws SQLException {
        SerializerAggiunta.getInstance().serializzaProdotto(new Prodotto(nome, prezzo, quantita, IDCommerciante, URLimmagine));
    }

    /**
     * Permette di modificare un {@link Prodotto} e successivamente serializza le modifiche nel database.
     *
     * @param prodotto         - Il {@link Prodotto} da modificare.
     * @throws SQLException in caso di problemi col database.
     */
    public void modificaProdotto(ProdottoInterface prodotto) throws SQLException {
        SerializerModifica.getInstance().modificaProdotto(prodotto);
    }

    /**
     * Permette di eliminare un {@link Prodotto} e successivamente lo elimina dal database.
     *
     * @param IDProdotto - L'ID del {@link Prodotto} da eliminare.
     * @throws SQLException in caso di problemi col database.
     */
    public void eliminaProdotto(String IDProdotto) throws SQLException {
        SerializerElimina.getInstance().eliminaProdotto(IDProdotto);
    }

}
