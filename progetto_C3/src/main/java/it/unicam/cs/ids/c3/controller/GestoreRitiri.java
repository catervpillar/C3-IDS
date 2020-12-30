package it.unicam.cs.ids.c3.controller;

import it.unicam.cs.ids.c3.model.*;
import it.unicam.cs.ids.c3.utilities.AppList;

import java.util.ArrayList;
import java.util.List;

public final class GestoreRitiri {
    private static GestoreRitiri instance;
    private final List<Ritiro> listaRitiri = new ArrayList<>();

    public static GestoreRitiri getInstance() {
        if (instance == null)
            instance = new GestoreRitiri();
        return instance;
    }

    public List<Ritiro> getListaRitiri() {
        return listaRitiri;
    }

    public void creaRitiro(List<String> listaIDProdotti, String IDCommerciante, String IDCliente, String IDCorriere, String destinazione, TipoConsegna tipoConsegna) {
        Ritiro ritiro = new Ritiro(listaIDProdotti, IDCommerciante, IDCliente, IDCorriere, destinazione, tipoConsegna);
        GestoreTracking.getInstance().creaTracking(ritiro);
        this.listaRitiri.add(ritiro);
    }
}
