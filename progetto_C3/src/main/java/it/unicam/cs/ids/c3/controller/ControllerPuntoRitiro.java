package it.unicam.cs.ids.c3.controller;

import it.unicam.cs.ids.c3.model.Ritiro;

import java.util.ArrayList;
import java.util.List;

public class ControllerPuntoRitiro {
    private final List<Ritiro> listaOrdini = new ArrayList<>();


    public List<Ritiro> getListaOrdini() {
        return listaOrdini;
    }
}
