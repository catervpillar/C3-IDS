package it.unicam.cs.ids.c3.controller;

import it.unicam.cs.ids.c3.model.*;

import java.util.GregorianCalendar;
import java.util.List;

public final class GestorePromozioni {

    private BachecaPromozioni bachecaPromozioni;
    private static GestorePromozioni instance;

    public static GestorePromozioni getInstance(){
        if (instance == null)
            instance = new GestorePromozioni();
        return instance;
    }

    public void creaPromozione(String nome, List<Commerciante> listaCommercianti, List<Prodotto> listaProdotti, String descrizione, GregorianCalendar dataInizio, GregorianCalendar dataScadenza){

        Promozione promozione = new Promozione(IDGenerator.getInstance().generateIDPromozione(), nome, listaCommercianti, listaProdotti, descrizione, dataInizio, dataScadenza);
        pubblicaPromozione(promozione);
    }


    private void pubblicaPromozione(Promozione promozione) {
        bachecaPromozioni.getPromozioniAttive().add(promozione);
    }

    public void rimuoviPromozione(Promozione promozione){
        bachecaPromozioni.getPromozioniAttive().remove(promozione);
    }



}
