package it.unicam.cs.ids.c3.controller;

import it.unicam.cs.ids.c3.model.Ritiro;
import it.unicam.cs.ids.c3.model.Tracking;

import java.util.ArrayList;
import java.util.List;

public class GestoreTracking {
    private static GestoreTracking instance;
    private final List<Tracking> listaTracking = new ArrayList<>();

    public static GestoreTracking getInstance() {
        if (instance == null)
            instance = new GestoreTracking();
        return instance;
    }

    public void creaTracking(Ritiro ritiro) {
        Tracking tracking = new Tracking(ritiro.getID());
        ritiro.setIDTracking(tracking.getID());
        this.listaTracking.add(tracking);
    }

    public List<Tracking> getListaTracking() {
        return listaTracking;
    }
}
