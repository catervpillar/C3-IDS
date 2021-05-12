package it.unicam.cs.ids.c3.promozione;

import it.unicam.cs.ids.c3.database.SerializerAggiunta;
import it.unicam.cs.ids.c3.database.SerializerElimina;
import it.unicam.cs.ids.c3.database.SerializerModifica;


import java.sql.SQLException;
import java.util.*;

public final class GestorePromozioni {
    private static GestorePromozioni instance;

    private GestorePromozioni(){}

    public static GestorePromozioni getInstance() {
        if (instance == null)
            instance = new GestorePromozioni();
        return instance;
    }

    public void creaPromozione(String nome, String IDCommerciante, List<String> listaIDProdotti, String descrizione, GregorianCalendar dataInizio, GregorianCalendar dataScadenza) throws SQLException {
        PromozioneInterface promozione = new Promozione(nome, IDCommerciante, descrizione, dataInizio, dataScadenza);
        promozione.getListaIDProdotti().addAll(listaIDProdotti);
        SerializerAggiunta.getInstance().serializzaPromozione(promozione);
    }

    public void modificaPromozione(String IDPromozione, String IDCommerciante, String nome, String descrizione, GregorianCalendar dataInizio, GregorianCalendar dataScadenza) throws SQLException {
        PromozioneInterface promozione = new Promozione(IDPromozione, nome, IDCommerciante, descrizione, dataInizio, dataScadenza);
        SerializerModifica.getInstance().modificaPromozione(promozione);
    }

    public void rimuoviPromozione(String IDPromozione) throws SQLException {
        SerializerElimina.getInstance().eliminaPromozione(IDPromozione);
    }
}