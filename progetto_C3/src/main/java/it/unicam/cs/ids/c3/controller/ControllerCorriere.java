package it.unicam.cs.ids.c3.controller;

import it.unicam.cs.ids.c3.model.StatoTracking;
import it.unicam.cs.ids.c3.model.TipoConsegna;
import it.unicam.cs.ids.c3.model.Tracking;
import it.unicam.cs.ids.c3.utilities.AppList;

public class ControllerCorriere {

    private AppList appList;

    public void aggiornaTracking(Tracking tracking, StatoTracking statoTracking){

        tracking.setStato(statoTracking);

        if (statoTracking.equals(StatoTracking.CONSEGNATO)) {
            if (tracking.getRitiro().getTipoConsegna().equals(TipoConsegna.CONSEGNA_A_DOMICILIO))
                tracking.getRitiro().setRitirato(true);
        }
    }

}
