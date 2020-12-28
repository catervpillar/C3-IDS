package it.unicam.cs.ids.c3.controller;

import it.unicam.cs.ids.c3.model.BachecaPromozioni;
import it.unicam.cs.ids.c3.model.Commerciante;
import it.unicam.cs.ids.c3.model.Prodotto;
import it.unicam.cs.ids.c3.model.Promozione;
import it.unicam.cs.ids.c3.utilities.AppList;

import java.util.GregorianCalendar;
import java.util.List;

public final class GestorePromozione {

    private BachecaPromozioni bachecaPromozioni;
    private static GestorePromozione instance;

    public static GestorePromozione getInstance(){
        if (instance == null)
            instance = new GestorePromozione();
        return instance;
    }

    public void creaPromozione(int ID, String nome, List<Commerciante> listaCommercianti, List<Prodotto> listaProdotti, String descrizione, GregorianCalendar dataInizio, GregorianCalendar dataScadenza){

        Promozione promozione = new Promozione(ID, nome, listaCommercianti, listaProdotti, descrizione, dataInizio, dataScadenza);
        pubblicaPromozione(promozione);
    }


    private void pubblicaPromozione(Promozione promozione) {
        bachecaPromozioni.getBacheca().add(promozione);
    }

    public void rimuoviPromozione(Promozione promozione){
        bachecaPromozioni.getBacheca().remove(promozione);
    }



}
