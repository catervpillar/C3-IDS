package it.unicam.cs.ids.c3.controller;

import it.unicam.cs.ids.c3.App;
import it.unicam.cs.ids.c3.model.CategoriaProdotto;
import it.unicam.cs.ids.c3.model.Commerciante;
import it.unicam.cs.ids.c3.model.Prodotto;
import it.unicam.cs.ids.c3.model.PuntoRitiro;
import it.unicam.cs.ids.c3.utilities.AppList;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class ControllerCliente {

    public List<PuntoRitiro> cercaPuntiRitiro(String ragioneSociale){
        return GestoreRicerche.getInstance().cercaPuntiRitiro(ragioneSociale);
    }

    public List<Commerciante> cercaCommerciante(List<CategoriaProdotto> categorieCommercianti, String ragioneSociale){
        return GestoreRicerche.getInstance().cercaCommerciante(categorieCommercianti, ragioneSociale);
    }

    public List<Prodotto> cercaProdotto(List<CategoriaProdotto> categorieProdotti, String nome){
        return GestoreRicerche.getInstance().cercaProdotto(categorieProdotti, nome);
    }
}
