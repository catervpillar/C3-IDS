package it.unicam.cs.ids.c3.controller;

import it.unicam.cs.ids.c3.model.Corriere;
import it.unicam.cs.ids.c3.model.StatoTracking;
import it.unicam.cs.ids.c3.model.TipoConsegna;
import it.unicam.cs.ids.c3.model.Tracking;
import it.unicam.cs.ids.c3.utilities.AppList;
import it.unicam.cs.ids.c3.utilities.Controllore;

import java.util.Objects;

public class ControllerCorriere implements Controller {
    private static ControllerCorriere instance;

    private ControllerCorriere() {
    }

    public static ControllerCorriere getInstance() {
        if (Objects.isNull(instance)) instance = new ControllerCorriere();
        return instance;
    }

    public void creaCorriere(String username, String password, String email, String ragioneSociale) {
        Controllore.getInstance().controllaCorriere(username, password, email, ragioneSociale);
        Corriere corriere = new Corriere(username, password, email, ragioneSociale);

        //TODO
    }

    public void aggiornaTracking(Tracking tracking, StatoTracking statoTracking) {
        tracking.setStato(statoTracking);

//        if (statoTracking.equals(StatoTracking.CONSEGNATO))
//            for (int i = 0; i < AppList.getInstance().getRitiri().size(); i++) {
//                if (AppList.getInstance().getRitiri().get(i).getID().equals(tracking.getIDRitiro())) {
//                    if (AppList.getInstance().getRitiri().get(i).getTipoConsegna().equals(TipoConsegna.CONSEGNA_A_DOMICILIO))
//                        AppList.getInstance().getRitiri().get(i).setRitirato(true);
//                }
//            }
    }

    @Override
    public void creaUtente(String username, String password, String email, String ragioneSociale) {
        Controllore.getInstance().controllaCorriere(username, password, email, ragioneSociale);
        Corriere corriere = new Corriere(username, password, email, ragioneSociale);

        //TODO
    }
}
