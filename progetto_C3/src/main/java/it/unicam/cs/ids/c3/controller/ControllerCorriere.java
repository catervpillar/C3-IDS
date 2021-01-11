package it.unicam.cs.ids.c3.controller;

import it.unicam.cs.ids.c3.model.StatoTracking;
import it.unicam.cs.ids.c3.model.TipoConsegna;
import it.unicam.cs.ids.c3.model.Tracking;
import it.unicam.cs.ids.c3.utilities.AppList;

public class ControllerCorriere {

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
}
