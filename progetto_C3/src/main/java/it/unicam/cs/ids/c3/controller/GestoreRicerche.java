package it.unicam.cs.ids.c3.controller;

import it.unicam.cs.ids.c3.model.CategoriaProdotto;
import it.unicam.cs.ids.c3.model.Commerciante;
import it.unicam.cs.ids.c3.model.Prodotto;
import it.unicam.cs.ids.c3.model.PuntoRitiro;
import it.unicam.cs.ids.c3.utilities.AppList;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public final class GestoreRicerche {
    private static GestoreRicerche instance;

    public static GestoreRicerche getInstance() {
        if (instance == null)
            instance = new GestoreRicerche();
        return instance;
    }

    List<Prodotto> cercaProdotto(List<CategoriaProdotto> categorieProdotti, String nome) {
        if (nome == null)
            return AppList.getInstance().getProdotti().stream().filter(p -> p.getListaCategorie().containsAll(categorieProdotti)).collect(Collectors.toList());
            //TODO confrontare le sottostringhe
        else if (categorieProdotti.isEmpty() || Objects.isNull(categorieProdotti))
            return AppList.getInstance().getProdotti().stream().filter(p -> p.getNome().contains(nome)).collect(Collectors.toList());
        else if ((categorieProdotti.isEmpty() || Objects.isNull(categorieProdotti)) && nome == null)
            return AppList.getInstance().getProdotti();
        else
            return AppList.getInstance().getProdotti().stream().filter(p -> p.getListaCategorie().containsAll(categorieProdotti) && p.getNome().contains(nome)).collect(Collectors.toList());
    }

    List<Commerciante> cercaCommerciante(List<CategoriaProdotto> categorieCommercianti, String ragioneSociale) {
        if (ragioneSociale == null)
            return AppList.getInstance().getCommercianti().stream().filter(p -> p.getListaCategorie().containsAll(categorieCommercianti)).collect(Collectors.toList());
            //TODO confrontare le sottostringhe
        else if (categorieCommercianti.isEmpty() || categorieCommercianti == null)
            return AppList.getInstance().getCommercianti().stream().filter(p -> p.getListaCategorie().containsAll(categorieCommercianti)).collect(Collectors.toList());
        else if ((categorieCommercianti.isEmpty() || categorieCommercianti == null) && ragioneSociale == null)
            return AppList.getInstance().getCommercianti();
        else
            return AppList.getInstance().getCommercianti().stream().filter(p -> p.getListaCategorie().containsAll(categorieCommercianti) && p.getRagioneSociale().contains(ragioneSociale)).collect(Collectors.toList());
    }

    List<PuntoRitiro> cercaPuntiRitiro(String ragioneSociale) {
        return AppList.getInstance().getPuntiDiRitiro().stream().filter(p -> p.getRagioneSociale().contains(ragioneSociale)).collect(Collectors.toList());
    }
}
