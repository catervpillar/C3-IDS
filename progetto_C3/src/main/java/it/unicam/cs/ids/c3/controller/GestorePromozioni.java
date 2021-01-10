package it.unicam.cs.ids.c3.controller;

import it.unicam.cs.ids.c3.model.*;


import java.util.*;
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
        for (Promozione promozione : listaPromozioni) {
            GregorianCalendar now = new GregorianCalendar();
            if (promozione.getID().equals(IDPromozione)) {
                if (nome != null)
                    promozione.setNome(nome);
                if (descrizione != null)
                    promozione.setDescrizione(descrizione);
                if ((dataInizio != null) && (dataInizio.compareTo(now)) >= 0)
                    promozione.setDataInizio(dataInizio);
                if ((dataScadenza != null) && (dataInizio.compareTo(now)) >= 0)
                    promozione.setDataScadenza(dataScadenza);
            }
        }
    }

    public void rimuoviPromozione(String IDPromozione){
        listaPromozioni.removeIf(promozione -> promozione.getID().equals(IDPromozione));
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
