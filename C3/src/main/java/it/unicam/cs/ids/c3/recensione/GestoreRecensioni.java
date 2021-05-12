package it.unicam.cs.ids.c3.recensione;

import it.unicam.cs.ids.c3.prodotto.Prodotto;
import it.unicam.cs.ids.c3.promozione.Promozione;
import it.unicam.cs.ids.c3.utenti.commerciante.Commerciante;
import it.unicam.cs.ids.c3.utenti.cliente.Cliente;
import it.unicam.cs.ids.c3.utilities.GestoreRicerche;
import it.unicam.cs.ids.c3.database.SerializerAggiunta;
import it.unicam.cs.ids.c3.database.SerializerElimina;
import it.unicam.cs.ids.c3.database.SerializerModifica;

import java.sql.SQLException;
import java.util.stream.Collectors;

/**
 * Questa classe è un sigleton e si occupa di gestire le {@link Recensione}.
 * Permette infatti la creazione, modifica e eliminazione di esse.
 */

public final class GestoreRecensioni {

    private static GestoreRecensioni instance;

    /**
     * Costruttore privato usato solamente all'interno di questa classe
     */
    private GestoreRecensioni() {
    }

    /**
     * Metodo getter per l'attributo instance. Se instance è nulla, viene inizializzata.
     *
     * @return l'attributo instance.
     */
    public static GestoreRecensioni getInstance() {
        if (instance == null)
            instance = new GestoreRecensioni();
        return instance;
    }

    /**
     * Crea una {@link Recensione}, e successivamente la fa serializzare nel database.
     *
     * @param titolo - il titolo della {@link Recensione}
     * @param testo - il testo della {@link Recensione}
     * @param IDCliente - l'ID del {@link Cliente} associato alla {@link Recensione}
     * @param IDCommerciante - l'ID del {@link Commerciante} associato alla {@link Recensione}
     * @param IDProdotto- l'ID del {@link Prodotto} associato alla {@link Recensione}
     * @param votoRecensioni - il voto della {@link Recensione} (da 1 a 5 stelle)
     * @throws SQLException in caso di errori con il database
     */
    public void creaRecensione(String titolo, String testo, String IDCliente, String IDCommerciante, String IDProdotto, VotoRecensioni votoRecensioni) throws SQLException {
        RecensioneInterface recensione = new Recensione(titolo, testo, IDCliente, IDCommerciante, IDProdotto, votoRecensioni);
        SerializerAggiunta.getInstance().serializzaRecensione(recensione);
    }

    /**
     * Permette di modificare una {@link Recensione} e successivamente serializza le modifiche nel database.
     *
     * @param titolo - il titolo della {@link Recensione}
     * @param testo - il testo della {@link Recensione}
     * @param votoRecensioni - il voto della {@link Recensione} (da 1 a 5 stelle)
     * @param IDRecensione - l'ID della {@link Recensione} da modificare
     * @throws SQLException
     */
    public void modificaRecensione(String titolo, String testo, VotoRecensioni votoRecensioni, String IDRecensione) throws SQLException {
        RecensioneInterface recensione = GestoreRicerche.getInstance().getRecensioni().stream().filter(r -> r.getID().equals(IDRecensione)).collect(Collectors.toList()).get(0);
        recensione.setTesto(testo);
        recensione.setTitolo(titolo);
        recensione.setVotoRecensioni(votoRecensioni);
        SerializerModifica.getInstance().modificaRecensione(recensione);
    }

    /**
     * Permette di eliminare una {@link Recensione} e successivamente la elimina dal database.
     *
     * @param IDRecensione - l'ID della {@link Recensione} da eliminare
     * @throws SQLException in caso di errori con il database
     */
    public void rimuoviRecensione(String IDRecensione) throws SQLException {
        SerializerElimina.getInstance().eliminaRecensione(IDRecensione);
    }
}
