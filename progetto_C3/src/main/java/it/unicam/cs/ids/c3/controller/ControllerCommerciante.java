package it.unicam.cs.ids.c3.controller;

import it.unicam.cs.ids.c3.model.*;
import it.unicam.cs.ids.c3.services.Deserializer;
import it.unicam.cs.ids.c3.utilities.Controllore;


import java.util.GregorianCalendar;
import java.util.List;
import java.util.Objects;

public class ControllerCommerciante implements Controller {
    private static ControllerCommerciante instance;

    private ControllerCommerciante() {
    }

    public static ControllerCommerciante getInstance() {
        if (Objects.isNull(instance)) instance = new ControllerCommerciante();
        return instance;
    }

    public void creaCommerciante(String username, String password, String email, String ragioneSociale) {
        Controllore.getInstance().controllaCommerciante(username, password, email, ragioneSociale);
        Commerciante commerciante = new Commerciante(username, password, email, ragioneSociale);

        //TODO
    }

    public void riempiListaProdotti(){
//        Deserializer.getInstance().deserializzaProdottiInVendita();
//        Deserializer.getInstance().deserializzaArticoli();
//
//        Deserializer.getInstance().deserializzaCategorie();
    }


    public void prenotaRitiro(String destinazione, List<String> listaIDProdotti, String IDCommerciante, String IDCliente, String IDCorriere, TipoConsegna tipoConsegna) {
        GestoreRitiri.getInstance().creaRitiro(listaIDProdotti, IDCommerciante, IDCliente, IDCorriere, destinazione, tipoConsegna);
    }

    public void creaPromozione(List<String> listaIDCommercianti, String nome, String descrizione, List<String> listaIDProdotti, GregorianCalendar dataInizio, GregorianCalendar dataScadenza) {
        GestorePromozioni.getInstance().creaPromozione(nome, listaIDCommercianti, listaIDProdotti, descrizione, dataInizio, dataScadenza);
    }

    public void modificaPromozione(List<String> listaIDCommercianti, String nome, String descrizione, List<String> listaIDProdotti, GregorianCalendar dataInizio, GregorianCalendar dataScadenza, String IDPromozione) {
        GestorePromozioni.getInstance().modificaPromozione(nome, listaIDCommercianti, listaIDProdotti, descrizione, dataInizio, dataScadenza, IDPromozione);
    }

    public void rimuoviPromozione(String IDPromozione) {
        GestorePromozioni.getInstance().rimuoviPromozione(IDPromozione);
    }

    @Override
    public void creaUtente(String username, String password, String email, String ragioneSociale) {
        Controllore.getInstance().controllaCommerciante(username, password, email, ragioneSociale);
        Commerciante commerciante = new Commerciante(username, password, email, ragioneSociale);

        //TODO
    }
}
