package it.unicam.cs.ids.c3.controller;

import it.unicam.cs.ids.c3.model.*;
import it.unicam.cs.ids.c3.utilities.AppList;

import java.util.List;

public final class GestoreRitiri {
    private AppList appList;
    private static GestoreRitiri instance;

    public static GestoreRitiri getInstance() {
        if (instance == null)
            instance = new GestoreRitiri();
        return instance;
    }

    public void creaRitiro(List<String> listaIDProdotti, String IDCommerciante, String IDCliente, String IDCorriere, String destinazione, TipoConsegna tipoConsegna) {
        Ritiro ritiro = new Ritiro(listaIDProdotti, IDCommerciante, IDCliente, IDCorriere, destinazione, tipoConsegna);
        aggiungiRitiro(ritiro);
    }

    private void aggiungiRitiro(Ritiro ritiro) {
        appList.getRitiri().add(ritiro);
    }

    public void rimuoviRitiro(Ritiro ritiro) {
        appList.getRitiri().remove(ritiro);
    }
}
