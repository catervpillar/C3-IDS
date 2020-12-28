package it.unicam.cs.ids.c3.controller;

import it.unicam.cs.ids.c3.model.*;
import it.unicam.cs.ids.c3.utilities.AppList;

import java.util.List;

public final class GestoreRitiri {

    private AppList appList;
    private static GestoreRitiri instance;

    public static GestoreRitiri getInstance(){
        if (instance == null)
            instance = new GestoreRitiri();
        return instance;
    }

    public void creaRitiro( List<Prodotto> listaProdotti, Commerciante commerciante, Cliente cliente, Corriere corriere, String destinazione, TipoConsegna tipoConsegna){

        Ritiro ritiro = new Ritiro(IDGenerator.getInstance().generateIDRitiro(), listaProdotti, commerciante, cliente, corriere, destinazione, tipoConsegna);
        aggiungiRitiro(ritiro);
    }

    private void aggiungiRitiro(Ritiro ritiro){
        appList.getRitiri().add(ritiro);
    }

    public void rimuoviRitiro(Ritiro ritiro){
        appList.getRitiri().remove(ritiro);
    }
}
