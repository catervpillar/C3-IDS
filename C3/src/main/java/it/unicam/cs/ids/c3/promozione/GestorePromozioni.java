package it.unicam.cs.ids.c3.promozione;

import it.unicam.cs.ids.c3.database.SerializerAggiunta;
import it.unicam.cs.ids.c3.database.SerializerElimina;
import it.unicam.cs.ids.c3.database.SerializerModifica;
import it.unicam.cs.ids.c3.utenti.commerciante.Commerciante;
import it.unicam.cs.ids.c3.prodotto.Prodotto;


import java.sql.SQLException;
import java.util.*;

/**
 * Questa classe è un sigleton e si occupa di gestire le {@link Promozione}.
 * Permette infatti la creazione, modifica e eliminazione di esse.
 */
public final class GestorePromozioni {
    private static GestorePromozioni instance;

    /**
     * Costruttore privato usato solamente all'interno di questa classe
     */
    private GestorePromozioni(){}

    /**
     * Metodo getter per l'attributo instance. Se instance è nulla, viene inizializzata.
     *
     * @return l'attributo instance.
     */
    public static GestorePromozioni getInstance() {
        if (instance == null)
            instance = new GestorePromozioni();
        return instance;
    }

    /**
     * Crea una {@link Promozione}, aggiunge ad essa tutti gli ID dei {@link Prodotto} passati in input e
     * successivamente la fa serializzare nel database.
     *
     * @param nome - il nome della {@link Promozione}
     * @param IDCommerciante - l'ID del {@link Commerciante} associato alla {@link Promozione}
     * @param listaIDProdotti - la lista degli ID dei {@link Prodotto} che fanno parte della {@link Promozione}
     * @param descrizione - la descrizione della {@link Promozione}
     * @param dataInizio - la data d'inizio della {@link Promozione}
     * @param dataScadenza - la data di scadenza della {@link Promozione}
     * @throws SQLException in caso di errori con il database
     */
    public void creaPromozione(String nome, String IDCommerciante, List<String> listaIDProdotti, String descrizione, GregorianCalendar dataInizio, GregorianCalendar dataScadenza) throws SQLException {
        PromozioneInterface promozione = new Promozione(nome, IDCommerciante, descrizione, dataInizio, dataScadenza);
        promozione.getListaIDProdotti().addAll(listaIDProdotti);
        SerializerAggiunta.getInstance().serializzaPromozione(promozione);
    }

    /**
     * Permette di modificare una {@link Promozione} e successivamente serializza le modifiche nel database.
     *
     * @param IDPromozione - l'ID della {@link Promozione} da modificare
     * @param IDCommerciante - l'ID del {@link Commerciante} associato alla {@link Promozione}
     * @param nome - il nome della {@link Promozione}
     * @param descrizione - la descrizione della {@link Promozione}
     * @param dataInizio - data d'inizio della {@link Promozione}
     * @param dataScadenza - data di scadenza della {@link Promozione}
     * @throws SQLException in caso di errori con il database
     */
    public void modificaPromozione(String IDPromozione, String IDCommerciante, String nome, String descrizione, GregorianCalendar dataInizio, GregorianCalendar dataScadenza) throws SQLException {
        PromozioneInterface promozione = new Promozione(IDPromozione, nome, IDCommerciante, descrizione, dataInizio, dataScadenza);
        SerializerModifica.getInstance().modificaPromozione(promozione);
    }

    /**
     * Permette di eliminare una {@link Promozione} e successivamente la elimina dal database.
     *
     * @param IDPromozione - l'ID della {@link Promozione} da eliminare
     * @throws SQLException in caso di errori con il database
     */
    public void rimuoviPromozione(String IDPromozione) throws SQLException {
        SerializerElimina.getInstance().eliminaPromozione(IDPromozione);
    }
}