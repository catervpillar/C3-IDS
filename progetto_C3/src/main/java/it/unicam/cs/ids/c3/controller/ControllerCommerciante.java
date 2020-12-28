package it.unicam.cs.ids.c3.controller;

import it.unicam.cs.ids.c3.model.*;
import it.unicam.cs.ids.c3.utilities.AppList;

import java.util.GregorianCalendar;
import java.util.List;

public class ControllerCommerciante {


    public void prenotaRitiro(String destinazione, List<Prodotto> prodotti, Commerciante commerciante, Cliente cliente, Corriere corriere, TipoConsegna tipoConsegna){
        GestoreRitiri.getInstance().creaRitiro(prodotti, commerciante, cliente, corriere, destinazione, tipoConsegna);
    }

    public void creaPromozione(List<Commerciante> commercianti, String nome, String descrizione, List<Prodotto> listaProdotti, GregorianCalendar dataInizio, GregorianCalendar dataScadenza){
        GestorePromozioni.getInstance().creaPromozione(nome, commercianti, listaProdotti, descrizione, dataInizio, dataScadenza);
    }

    

}
