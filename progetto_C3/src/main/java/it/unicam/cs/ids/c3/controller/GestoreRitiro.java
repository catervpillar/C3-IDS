package it.unicam.cs.ids.c3.controller;

import it.unicam.cs.ids.c3.model.*;
import it.unicam.cs.ids.c3.utilities.AppList;

import java.util.List;

public final class GestoreRitiro {

    private AppList appList;
    private static GestoreRitiro instance;

    public static GestoreRitiro getInstance(){
        if (instance == null)
            instance = new GestoreRitiro();
        return instance;
    }

    public void creaRitiro(int ID, List<Prodotto> listaProdotti, Commerciante commerciante, Cliente cliente, Corriere corriere, String destinazione, TipoConsegna tipoConsegna){

        Ritiro ritiro = new Ritiro(ID, listaProdotti, commerciante, cliente, corriere, destinazione, tipoConsegna);
        aggiungiRitiro(ritiro);
    }

    private void aggiungiRitiro(Ritiro ritiro){
        appList.getRitiri().add(ritiro);
    }

    public void rimuoviRitiro(Ritiro ritiro){
        appList.getRitiri().remove(ritiro);
    }
}
