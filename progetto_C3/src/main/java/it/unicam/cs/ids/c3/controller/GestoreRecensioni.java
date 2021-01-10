package it.unicam.cs.ids.c3.controller;

import it.unicam.cs.ids.c3.model.Promozione;
import it.unicam.cs.ids.c3.model.Recensione;
import it.unicam.cs.ids.c3.model.VotoRecensioni;

import java.util.ArrayList;
import java.util.List;

public final class GestoreRecensioni {

    private List<Recensione> listaRecensioni = new ArrayList<>();
    private static GestoreRecensioni instance;

    public static GestoreRecensioni getInstance(){
        if(instance == null)
            instance = new GestoreRecensioni();
        return instance;
    }

    public void creaRecensione(String titolo, String testo, String IDCliente, String IDCommerciante, String IDProdotto, VotoRecensioni votoRecensioni){
        Recensione recensione = new Recensione(titolo, testo, IDCliente, IDCommerciante, IDProdotto, votoRecensioni);
        this.listaRecensioni.add(recensione);
    }

    public void modificaRecensione(String titolo, String testo, VotoRecensioni votoRecensioni, String IDRecensione){
        for (Recensione recensione : listaRecensioni) {
            if (recensione.getID().equals(IDRecensione)) {
                if (titolo != null)
                    recensione.setTitolo(titolo);
                if (testo != null)
                    recensione.setTesto(testo);
                if (votoRecensioni != null)
                    recensione.setVotoRecensioni(votoRecensioni);
            }
        }
    }

    public void rimuoviRecensione(String IDRecensione){
        listaRecensioni.removeIf(recensione -> recensione.getID().equals(IDRecensione));
    }
}
