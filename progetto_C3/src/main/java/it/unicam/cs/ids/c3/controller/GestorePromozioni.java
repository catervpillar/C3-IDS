package it.unicam.cs.ids.c3.controller;

import it.unicam.cs.ids.c3.model.*;
import it.unicam.cs.ids.c3.services.SerializerAggiunta;


import java.sql.SQLException;
import java.util.*;
import java.util.stream.Collectors;

public final class GestorePromozioni {
    private static GestorePromozioni instance;

    public static GestorePromozioni getInstance() {
        if (instance == null)
            instance = new GestorePromozioni();
        return instance;
    }

    public void creaPromozione(String nome, String IDCommerciante, List<String> listaIDProdotti, String descrizione, GregorianCalendar dataInizio, GregorianCalendar dataScadenza) throws SQLException {
        Promozione promozione = new Promozione(nome, IDCommerciante, descrizione, dataInizio, dataScadenza);
        promozione.getListaIDProdotti().addAll(listaIDProdotti);
        SerializerAggiunta.getInstance().serializzaPromozione(promozione);
    }

    public void modificaPromozione(String nome, List<String> listaIDCommercianti, List<String> listaIDProdotti, String descrizione, GregorianCalendar dataInizio, GregorianCalendar dataScadenza, String IDPromozione) {
//        for (Promozione promozione : listaPromozioni) {
//            GregorianCalendar now = new GregorianCalendar();
//            if (promozione.getID().equals(IDPromozione)) {
//                if (nome != null)
//                    promozione.setNome(nome);
//                if (descrizione != null)
//                    promozione.setDescrizione(descrizione);
//                if ((dataInizio != null) && (dataInizio.compareTo(now)) >= 0)
//                    promozione.setDataInizio(dataInizio);
//                if ((dataScadenza != null) && (dataInizio.compareTo(now)) >= 0)
//                    promozione.setDataScadenza(dataScadenza);
//            }
//        }
    }

    public void rimuoviPromozione(String IDPromozione) {
        //listaPromozioni.removeIf(promozione -> promozione.getID().equals(IDPromozione));
    }
}