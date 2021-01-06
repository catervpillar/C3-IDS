package it.unicam.cs.ids.c3.controller;

import it.unicam.cs.ids.c3.model.*;


import java.util.GregorianCalendar;
import java.util.List;

public class ControllerCommerciante {

    private List<CategoriaProdotto> listaCategorie;
    private List<Prodotto> listaProdotti;

    public void prenotaRitiro(String destinazione, List<String> listaIDProdotti, String IDCommerciante, String IDCliente, String IDCorriere, TipoConsegna tipoConsegna){
        GestoreRitiri.getInstance().creaRitiro(listaIDProdotti, IDCommerciante, IDCliente, IDCorriere, destinazione, tipoConsegna);
    }

    public void creaPromozione(List<String> listaIDCommercianti, String nome, String descrizione, List<String> listaIDProdotti, GregorianCalendar dataInizio, GregorianCalendar dataScadenza){
        GestorePromozioni.getInstance().creaPromozione(nome, listaIDCommercianti, listaIDProdotti, descrizione, dataInizio, dataScadenza);
    }
    public void modificaPromozione(List<String> listaIDCommercianti, String nome, String descrizione, List<String> listaIDProdotti, GregorianCalendar dataInizio, GregorianCalendar dataScadenza, String IDPromozione){
        GestorePromozioni.getInstance().modificaPromozione(nome, listaIDCommercianti, listaIDProdotti, descrizione, dataInizio, dataScadenza, IDPromozione);
    }
    public void rimuoviPromozione(String IDPromozione){
        GestorePromozioni.getInstance().rimuoviPromozione(IDPromozione);
    }
    public void aggiungiCategoria(String nome, String descrizione){
        CategoriaProdotto categoriaProdotto = new CategoriaProdotto(nome, descrizione);
    }



}
