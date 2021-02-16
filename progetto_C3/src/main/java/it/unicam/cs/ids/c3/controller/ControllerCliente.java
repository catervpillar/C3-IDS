package it.unicam.cs.ids.c3.controller;

import it.unicam.cs.ids.c3.model.*;
import it.unicam.cs.ids.c3.utilities.Controllore;


import java.util.ArrayList;
import java.util.List;
import java.util.Objects;


public class ControllerCliente {
    private static ControllerCliente instance;
    private final List<Ritiro> listaOrdini = new ArrayList<>();

    private ControllerCliente() {
    }

    public static ControllerCliente getInstance() {
        if (Objects.isNull(instance)) instance = new ControllerCliente();
        return instance;
    }

    public void creaCliente(String username, String password, String email, String nome, String cognome) {
        Controllore.getInstance().controllaCliente(username, password, email, nome, cognome);
        Cliente cliente = new Cliente(username, password, email, nome, cognome);

        //TODO
    }

    public List<PuntoRitiro> cercaPuntiRitiro(String ragioneSociale) {
        return GestoreRicerche.getInstance().cercaPuntiRitiro(ragioneSociale);
    }

    public List<Commerciante> cercaCommerciante(String ragioneSociale) {
        return GestoreRicerche.getInstance().cercaCommerciante(ragioneSociale);
    }

    public void pubblicaRecensione(String titolo, String testo, String IDCliente, String IDCommerciante, String IDProdotto, VotoRecensioni votoRecensioni) {
        GestoreRecensioni.getInstance().creaRecensione(titolo, testo, IDCliente, IDCommerciante, IDProdotto, votoRecensioni);
    }

    public void modificaRecensione(String titolo, String testo, VotoRecensioni votoRecensioni, String IDRecensione) {
        GestoreRecensioni.getInstance().modificaRecensione(titolo, testo, votoRecensioni, IDRecensione);
    }

    public void rimuoviRecensione(String IDRecensione) {
        GestoreRecensioni.getInstance().rimuoviRecensione(IDRecensione);
    }

    public List<Ritiro> getListaOrdini() {
        return listaOrdini;
    }

}
