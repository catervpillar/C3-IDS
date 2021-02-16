package it.unicam.cs.ids.c3.utilities;


import it.unicam.cs.ids.c3.model.*;

import java.util.ArrayList;
import java.util.List;

public final class AppList {
    //stringa connessiona al db
    //TODO: sposta le liste nei rispettivi controller (?)

    private final List<Commerciante> commercianti = new ArrayList<>();
    private final List<Cliente> clienti = new ArrayList<>();
    private final List<PuntoRitiro> puntiDiRitiro = new ArrayList<>();
    private final List<Corriere> corrieri = new ArrayList<>();

    private static AppList instance;

    public static AppList getInstance() {
        if (instance == null)
            instance = new AppList();
        return instance;
    }


    public List<Commerciante> getCommercianti() {
        return commercianti;
    }

    public List<Cliente> getClienti() {
        return clienti;
    }

    public List<PuntoRitiro> getPuntiDiRitiro() {
        return puntiDiRitiro;
    }

    public List<Corriere> getCorrieri() {
        return corrieri;
    }
}
