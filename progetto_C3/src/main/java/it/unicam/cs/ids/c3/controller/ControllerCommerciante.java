package it.unicam.cs.ids.c3.controller;

import it.unicam.cs.ids.c3.model.*;
import it.unicam.cs.ids.c3.utilities.AppList;

import java.util.GregorianCalendar;
import java.util.List;

public class ControllerCommerciante {

    private AppList appList;


    public void prenotaRitiro(Cliente cliente, String destinazione, List<Prodotto> prodotti, Commerciante commerciante, Corriere corriere, TipoConsegna tipoConsegna){

        Ritiro ritiro = new Ritiro(IDGenerator.getInstance().generateIDRitiro(), prodotti, commerciante, cliente, corriere, destinazione, tipoConsegna);
        appList.getRitiri().add(ritiro);
    }

    public void creaPromozione(List<Commerciante> commercianti, String descrizione, TipoPromozione tipoPromozione, GregorianCalendar dataInizio, GregorianCalendar dataScadenza){
        Promozione promozione = new Promozione(IDGenerator.getInstance().generateIDPromozione(), commercianti, descrizione, tipoPromozione, dataInizio, dataScadenza);
        appList.getPromozioni().add(promozione);
    }


}
