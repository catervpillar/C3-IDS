package it.unicam.cs.ids.c3.controller;

import it.unicam.cs.ids.c3.model.*;
import it.unicam.cs.ids.c3.utilities.Controllore;


import java.util.GregorianCalendar;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;

public class ControllerCommerciante implements Controller {
    private static ControllerCommerciante instance;
    private List<CategoriaProdotto> listaCategorie;
    private List<Prodotto> listaProdotti;

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

    public void aggiungiCategoria(String nome, String descrizione) {
        CategoriaProdotto categoriaProdotto = new CategoriaProdotto(nome, descrizione);
        listaCategorie.add(categoriaProdotto);
    }

    public void modificaCategoria(String nome, String descrizione, String IDCategoria) {
        for (CategoriaProdotto categoriaProdotto : listaCategorie) {
            if (categoriaProdotto.getID().equals(IDCategoria)) {
                categoriaProdotto.setNome(nome);
                categoriaProdotto.setDescrizione(descrizione);
            }
        }
    }

    public void rimuoviCategoria(String IDCategoria) {
        listaCategorie.removeIf(categoria -> categoria.getID().equals(IDCategoria));
    }

    public void aggiungiProdotto(String nome, String IDCommerciante, List<CategoriaProdotto> categorie, double prezzo) {
        Prodotto prodotto = new Prodotto(nome, IDCommerciante, categorie, prezzo);
        listaProdotti.add(prodotto);
    }

    public void modificaProdotto(String nome, List<CategoriaProdotto> categorie, double prezzo, String IDProdotto) {
        for (Prodotto prodotto : listaProdotti) {
            if (prodotto.getID().equals(IDProdotto)) {
                prodotto.setNome(nome);
                prodotto.setListaCategorie(categorie);
                prodotto.setPrezzo(prezzo);
            }
        }
    }

    public void rimuoviProdotto(String IDProdotto) {
        listaProdotti.removeIf(p -> p.getID().equals(IDProdotto));
    }

    @Override
    public void creaUtente(String username, String password, String email, String ragioneSociale) {
        Controllore.getInstance().controllaCommerciante(username, password, email, ragioneSociale);
        Commerciante commerciante = new Commerciante(username, password, email, ragioneSociale);

        //TODO
    }
}
