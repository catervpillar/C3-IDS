package it.unicam.cs.ids.c3.controller;

import it.unicam.cs.ids.c3.model.*;


import java.util.ArrayList;
import java.util.List;


public class ControllerCliente {

    private List<Ritiro> listaOrdini = new ArrayList<>();

    public List<PuntoRitiro> cercaPuntiRitiro(String ragioneSociale){
        return GestoreRicerche.getInstance().cercaPuntiRitiro(ragioneSociale);
    }

    public List<Commerciante> cercaCommerciante(List<CategoriaProdotto> categorieCommercianti, String ragioneSociale){
        return GestoreRicerche.getInstance().cercaCommerciante(categorieCommercianti, ragioneSociale);
    }

    public List<Prodotto> cercaProdotto(List<CategoriaProdotto> categorieProdotti, String nome){
        return GestoreRicerche.getInstance().cercaProdotto(categorieProdotti, nome);
    }
    public void pubblicaRecensione(String titolo, String testo, String IDCliente, String IDCommerciante, String IDProdotto, VotoRecensioni votoRecensioni){
        GestoreRecensioni.getInstance().creaRecensione(titolo, testo, IDCliente, IDCommerciante, IDProdotto, votoRecensioni);
    }
    public void modificaRecensione(String titolo, String testo, String IDRecensione, VotoRecensioni votoRecensioni){
        GestoreRecensioni.getInstance().modificaRecensione(titolo, testo, IDRecensione, votoRecensioni);
    }
    public void rimuoviRecensione(String IDRecensione){
        GestoreRecensioni.getInstance().rimuoviRecensione(IDRecensione);
    }
    public List<Ritiro> getListaOrdini(){
        return listaOrdini;
    }
    public void getTracking(String IDtracking){
        GestoreTracking.getInstance().getTracking(IDtracking);
    }

}
