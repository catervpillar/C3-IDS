package it.unicam.cs.ids.c3.controller;

import it.unicam.cs.ids.c3.model.*;
import it.unicam.cs.ids.c3.utilities.AppList;

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

    public List<Promozione> getListaPromozioni() {
        return listaPromozioni;
    }

    public List<Promozione> getPromozioniAttive() {
        GregorianCalendar now = new GregorianCalendar();
        return this.listaPromozioni.stream().filter(p -> ((p.getDataInizio().compareTo(now) < 0)
                && (p.getDataScadenza().compareTo(now) >= 0))).collect(Collectors.toList());
    }
}
