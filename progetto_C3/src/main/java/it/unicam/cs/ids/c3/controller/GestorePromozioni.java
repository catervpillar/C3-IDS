package it.unicam.cs.ids.c3.controller;

import it.unicam.cs.ids.c3.model.*;


import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.stream.Collectors;

public final class GestorePromozioni {
    private static GestorePromozioni instance;
    private final List<Promozione> listaPromozioni = new ArrayList<>();

    public static GestorePromozioni getInstance() {
        if (instance == null)
            instance = new GestorePromozioni();
        return instance;
    }

    public void creaPromozione(String nome, List<String> listaIDCommercianti, List<String> listaIDProdotti, String descrizione, GregorianCalendar dataInizio, GregorianCalendar dataScadenza) {
        Promozione promozione = new Promozione(nome, listaIDCommercianti, listaIDProdotti, descrizione, dataInizio, dataScadenza);
        this.listaPromozioni.add(promozione);
    }

    public void modificaPromozione(String nome, List<String> listaIDCommercianti, List<String> listaIDProdotti, String descrizione, GregorianCalendar dataInizio, GregorianCalendar dataScadenza, String IDPromozione){
        int index = -1;
        for (int i=0; i<listaPromozioni.size(); i++){
            if (IDPromozione.equals(listaPromozioni.get(i).getID()))
                index = i;
        }
        if (index!=-1) {
            if (nome != null)
                listaPromozioni.get(index).setNome(nome);
            if (listaIDCommercianti != null)
                listaPromozioni.get(index).setListaIDCommercianti(listaIDCommercianti);
            if (listaIDProdotti != null)
                listaPromozioni.get(index).setListaIDProdotti(listaIDProdotti);
            if (descrizione != null)
                listaPromozioni.get(index).setDescrizione(descrizione);
            if (dataInizio != null)
                listaPromozioni.get(index).setDataInizio(dataInizio);
            if (dataScadenza != null)
                listaPromozioni.get(index).setDataScadenza(dataScadenza);
        }
    }

    public void rimuoviPromozione(String IDPromozione){
        int index = -1;
        for (int i=0; i<listaPromozioni.size(); i++){
            if (IDPromozione.equals(listaPromozioni.get(i).getID()))
                index = i;
        }
        listaPromozioni.remove(index);
    }

    public List<Promozione> getListaPromozioni() {
        return listaPromozioni;
    }

    public List<Promozione> getPromozioniAttive() {
        GregorianCalendar now = new GregorianCalendar();
        return this.listaPromozioni.stream().filter(p -> ((p.getDataInizio().compareTo(now) < 0)
                && (p.getDataScadenza().compareTo(now) >= 0))).collect(Collectors.toList());
    }
}
