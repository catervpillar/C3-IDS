package it.unicam.cs.ids.c3.controller;

import it.unicam.cs.ids.c3.model.*;
import it.unicam.cs.ids.c3.services.DBManager;
import it.unicam.cs.ids.c3.services.Deserializer;
import it.unicam.cs.ids.c3.services.SerializerAggiunta;
import it.unicam.cs.ids.c3.utilities.Controllore;


import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Objects;


public class ControllerCliente {
    private static ControllerCliente instance;
    private Cliente cliente;

    private ControllerCliente() {
    }

    public static ControllerCliente getInstance() {
        if (Objects.isNull(instance)) instance = new ControllerCliente();
        return instance;
    }

    public void creaCliente(String username, String password, String email, String nome, String cognome) throws SQLException {
        Controllore.getInstance().controllaCliente(username, password, email, nome, cognome);
        Cliente cliente = new Cliente(username, password, email, nome, cognome);
        SerializerAggiunta.getInstance().serializzaCliente(cliente);
    }

    public boolean loginCliente(String username, String password) throws SQLException {
        String sql = "select * from cliente where username = \"" + username + "\" and password = \"" + password + "\";";
        //String sql = "select * from cliente where username = ? and password ? + password;";
        ResultSet resultSet = DBManager.getInstance().executeQuery(sql);
        int i=0;
        while(resultSet.next()){
            i++;
        }
        if (i!=0) {
            this.cliente = Deserializer.getInstance().deserializzaCliente(resultSet);
            return true;
        }
        else return false;
    }

//    public List<PuntoRitiro> cercaPuntiRitiro(String ragioneSociale) {
//        return GestoreRicerche.getInstance().cercaPuntiRitiro(ragioneSociale);
//    }

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

    public Cliente getCliente() {
        return cliente;
    }
}
