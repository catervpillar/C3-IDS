package it.unicam.cs.ids.c3.controller;

import it.unicam.cs.ids.c3.model.*;
import it.unicam.cs.ids.c3.services.DBManager;
import it.unicam.cs.ids.c3.services.Deserializer;
import it.unicam.cs.ids.c3.services.SerializerAggiunta;
import it.unicam.cs.ids.c3.utilities.Controllore;


import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Objects;

public class ControllerCommerciante {
    private static ControllerCommerciante instance;
    private Commerciante commerciante;

    private ControllerCommerciante() {
    }

    public static ControllerCommerciante getInstance() {
        if (Objects.isNull(instance)) instance = new ControllerCommerciante();
        return instance;
    }

    public Commerciante getCommerciante() {
        return commerciante;
    }

    public void creaCommerciante(String username, String password, String email, String ragioneSociale) throws SQLException {
        Controllore.getInstance().controllaCommerciante(username, password, email, ragioneSociale);
        Commerciante commerciante = new Commerciante(username, password, email, ragioneSociale);
        SerializerAggiunta.getInstance().serializzaCommerciante(commerciante);
    }

    public boolean loginCommerciante(String username, String password) throws SQLException {
        String sql = "select * from commerciante where username = \"" + username + "\" and password = \"" + password + "\";";
        ResultSet resultSet = DBManager.getInstance().executeQuery(sql);
        int i = 0;
        while (resultSet.next()) {
            i++;
        }
        if (i == 1) {
            this.commerciante = Deserializer.getInstance().deserializzaCommercianti(resultSet).get(0);
            return true;
        }
        else return false;
    }

    public void riempiListaProdotti() {
//        Deserializer.getInstance().deserializzaProdottiInVendita();
//        Deserializer.getInstance().deserializzaArticoli();
//
//        Deserializer.getInstance().deserializzaCategorie();
    }


    public void prenotaRitiro(String destinazione, List<String> listaIDProdotti, String IDCommerciante, String IDCliente, String IDCorriere, TipoConsegna tipoConsegna) {
        GestoreRitiri.getInstance().creaRitiro(listaIDProdotti, IDCommerciante, IDCliente, IDCorriere, destinazione, tipoConsegna);
    }

    public void creaPromozione(String IDCommerciante, String nome, String descrizione, List<String> listaIDProdotti, GregorianCalendar dataInizio, GregorianCalendar dataScadenza) {
        GestorePromozioni.getInstance().creaPromozione(nome, IDCommerciante, listaIDProdotti, descrizione, dataInizio, dataScadenza);
    }

    public void modificaPromozione(List<String> listaIDCommercianti, String nome, String descrizione, List<String> listaIDProdotti, GregorianCalendar dataInizio, GregorianCalendar dataScadenza, String IDPromozione) {
        GestorePromozioni.getInstance().modificaPromozione(nome, listaIDCommercianti, listaIDProdotti, descrizione, dataInizio, dataScadenza, IDPromozione);
    }

    public void rimuoviPromozione(String IDPromozione) {
        GestorePromozioni.getInstance().rimuoviPromozione(IDPromozione);
    }
}
