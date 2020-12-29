package it.unicam.cs.ids.c3.controller;

import it.unicam.cs.ids.c3.model.*;

import java.util.GregorianCalendar;
import java.util.List;

public final class GestorePromozioni {
    private static GestorePromozioni instance;

    public static GestorePromozioni getInstance() {
        if (instance == null)
            instance = new GestorePromozioni();
        return instance;
    }

    public void creaPromozione(String nome, List<String> listaIDCommercianti, List<String> listaIDProdotti, String descrizione, GregorianCalendar dataInizio, GregorianCalendar dataScadenza) {
        Promozione promozione = new Promozione(nome, listaIDCommercianti, listaIDProdotti, descrizione, dataInizio, dataScadenza);
        pubblicaPromozione(promozione);
    }

    private void pubblicaPromozione(Promozione promozione) {
        BachecaPromozioni.getInstance().getPromozioniAttive().add(promozione);
    }

    public void rimuoviPromozione(Promozione promozione) {
        BachecaPromozioni.getInstance().getPromozioniAttive().remove(promozione);
    }
}
