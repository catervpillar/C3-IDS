package it.unicam.cs.ids.c3.controller;

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

    public void modificaRecensione(String titolo, String testo, String IDRecensione, VotoRecensioni votoRecensioni){
        int index = -1;
        for (int i=0; i<listaRecensioni.size(); i++) {
            if (IDRecensione.equals(listaRecensioni.get(i).getID()))
                index = i;
        }
        if (index!=-1) {
            if (titolo != null)
                listaRecensioni.get(index).setTitolo(titolo);
            if (testo != null)
                listaRecensioni.get(index).setTesto(testo);
            if (votoRecensioni != null)
                listaRecensioni.get(index).setVotoRecensioni(votoRecensioni);
        }
    }

    public void rimuoviRecensione(String IDRecensione){
        int index = -1;
        for (int i=0; i<listaRecensioni.size(); i++){
            if (listaRecensioni.get(i).getID().equals(IDRecensione))
                index = i;
        }
        if (index>=0)
            listaRecensioni.remove(index);
    }
}
