package it.unicam.cs.ids.c3.controller;

import it.unicam.cs.ids.c3.model.PuntoRitiro;
import it.unicam.cs.ids.c3.model.Ritiro;
import it.unicam.cs.ids.c3.utilities.Controllore;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class ControllerPuntoRitiro implements Controller {
    private static ControllerPuntoRitiro instance;
    private final List<Ritiro> listaOrdini = new ArrayList<>();

    private ControllerPuntoRitiro() {
    }

    public static ControllerPuntoRitiro getInstance() {
        if (Objects.isNull(instance)) instance = new ControllerPuntoRitiro();
        return instance;
    }

    public void creaPuntoRitiro(String username, String password, String email, String ragioneSociale) {
        Controllore.getInstance().controllaPuntoRitiro(username, password, email, ragioneSociale);
        PuntoRitiro puntoRitiro = new PuntoRitiro(username, password, email, ragioneSociale);

        //TODO
    }

    public List<Ritiro> getListaOrdini() {
        return listaOrdini;
    }

    @Override
    public void creaUtente(String username, String password, String email, String ragioneSociale) {
        Controllore.getInstance().controllaPuntoRitiro(username, password, email, ragioneSociale);
        PuntoRitiro puntoRitiro = new PuntoRitiro(username, password, email, ragioneSociale);

        //TODO
    }
}
