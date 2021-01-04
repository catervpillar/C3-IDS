package it.unicam.cs.ids.c3.controller;

import it.unicam.cs.ids.c3.model.*;


import java.util.GregorianCalendar;
import java.util.List;

public class ControllerCommerciante {

    public void prenotaRitiro(String destinazione, List<String> listaIDProdotti, String IDCommerciante, String IDCliente, String IDCorriere, TipoConsegna tipoConsegna){
        GestoreRitiri.getInstance().creaRitiro(listaIDProdotti, IDCommerciante, IDCliente, IDCorriere, destinazione, tipoConsegna);
    }

    public void creaPromozione(List<String> listaIDCommercianti, String nome, String descrizione, List<String> listaIDProdotti, GregorianCalendar dataInizio, GregorianCalendar dataScadenza){
        GestorePromozioni.getInstance().creaPromozione(nome, listaIDCommercianti, listaIDProdotti, descrizione, dataInizio, dataScadenza);
    }


}
