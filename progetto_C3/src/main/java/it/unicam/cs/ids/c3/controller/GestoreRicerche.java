package it.unicam.cs.ids.c3.controller;

import it.unicam.cs.ids.c3.model.*;
import it.unicam.cs.ids.c3.utilities.AppList;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public final class GestoreRicerche {
    private static GestoreRicerche instance;

    private final List<Commerciante> commercianti = new ArrayList<>();
    private final List<PuntoRitiro> puntiDiRitiro = new ArrayList<>();
    private final List<Corriere> corrieri = new ArrayList<>();
    private final List<Prodotto> prodottiInVendita = new ArrayList<>();
    private final List<Promozione> promozioni = new ArrayList<>();
    private final List<Recensione> recensioni = new ArrayList<>();
    private final List<Ritiro> ritiri = new ArrayList<>();


    private GestoreRicerche() {
    }

    public static GestoreRicerche getInstance() {
        if (instance == null)
            instance = new GestoreRicerche();
        return instance;
    }


    List<Commerciante> cercaCommerciante(String ragioneSociale) {
//        if (ragioneSociale == null)
//            return AppList.getInstance().getCommercianti().stream().filter(p -> p.getListaCategorie().containsAll(categorieCommercianti)).collect(Collectors.toList());
//            //TODO confrontare le sottostringhe
//        else if (categorieCommercianti.isEmpty() || categorieCommercianti == null)
//            return AppList.getInstance().getCommercianti().stream().filter(p -> p.getListaCategorie().containsAll(categorieCommercianti)).collect(Collectors.toList());
//        else if ((categorieCommercianti.isEmpty() || categorieCommercianti == null) && ragioneSociale == null)
//            return AppList.getInstance().getCommercianti();
//        else
//            return AppList.getInstance().getCommercianti().stream().filter(p -> p.getListaCategorie().containsAll(categorieCommercianti) && p.getRagioneSociale().contains(ragioneSociale)).collect(Collectors.toList());
        return null;
    }

    List<PuntoRitiro> cercaPuntiRitiro(String ragioneSociale) {
        return AppList.getInstance().getPuntiDiRitiro().stream().filter(p -> p.getRagioneSociale().contains(ragioneSociale)).collect(Collectors.toList());
    }
}
