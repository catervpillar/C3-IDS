package it.unicam.cs.ids.c3.controller;

import it.unicam.cs.ids.c3.model.*;
import it.unicam.cs.ids.c3.services.*;
import it.unicam.cs.ids.c3.utilities.*;


import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;


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
        ResultSet resultSet = DBManager.getInstance().executeQuery(sql);
        if (resultSet.last()) {
            this.cliente = Deserializer.getInstance().deserializzaCliente(resultSet);
            return true;
        } else return false;
    }

    public List<Prodotto> getProdotti(String nome) {
        return GestoreRicerche.getInstance().cercaProdotto(nome, null);
    }

    public List<Prodotto> getListaProdottiAcquistati() throws SQLException {
        List<Ritiro> listaRitiro = getRitiri();
        List<String> listaIDProdotti = new ArrayList<>();
        List<Prodotto> listaProdotti = new ArrayList<>();

        listaRitiro.forEach(r -> {
            String query = "select distinct prodottoInVendita_ID from ritiro_has_prodotto where ritiro_ID = \"" + r.getID() + "\";";
            try {
                listaIDProdotti.addAll(Deserializer.getInstance().deserializzaIDProdotti(DBManager.getInstance().executeQuery(query)));
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        });

        listaIDProdotti.stream().distinct().collect(Collectors.toList()).forEach(ID -> {
            String query = "select * from prodotto where ID = \"" + ID + "\";";
            try{
                listaProdotti.addAll(Deserializer.getInstance().deserializzaProdotti(DBManager.getInstance().executeQuery(query)));
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        });

        return listaProdotti;
    }

    public void pubblicaRecensione(String titolo, String testo, String IDCommerciante, String IDProdotto, VotoRecensioni votoRecensione) throws SQLException {
        GestoreRecensioni.getInstance().creaRecensione(titolo, testo, this.cliente.getID(), IDCommerciante, IDProdotto, votoRecensione);
    }

    public void modificaRecensione(String titolo, String testo, VotoRecensioni votoRecensioni, String IDRecensione) throws SQLException {
        GestoreRecensioni.getInstance().modificaRecensione(titolo, testo, votoRecensioni, IDRecensione);
    }

    public void rimuoviRecensione(String IDRecensione) throws SQLException {
        GestoreRecensioni.getInstance().rimuoviRecensione(IDRecensione);
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public List<Promozione> getPromozioni() throws SQLException {
        return GestoreRicerche.getInstance().getPromozioni(null);
    }

    public List<Recensione> getRecensioni() throws SQLException {
        return GestoreRicerche.getInstance().getRecensioni("select * from recensione where cliente_ID = \"" + this.cliente.getID() + "\";");
    }

    public List<Ritiro> getRitiri() throws SQLException {
        return GestoreRicerche.getInstance().getRitiri(null, this.cliente.getID(), null, null);
    }

    public void modificaCliente(String username, String password, String email, String nome, String cognome, String telefono, String indirizzo) throws SQLException {
        Controllore.getInstance().controllaCliente(username, password, email, nome, cognome);
        Cliente cliente = new Cliente(this.cliente.getID(), username, password, email, nome, cognome);

        if (!indirizzo.isBlank()) {
            Controllore.getInstance().controllaIndirizzo(indirizzo);
            cliente.setIndirizzo(indirizzo);
        }
        if (!telefono.isBlank()) {
            Controllore.getInstance().controllaNumero(telefono, 10);
            cliente.setTelefono(telefono);
        }
        setCliente(cliente);
        SerializerModifica.getInstance().modificaCliente(cliente);
    }

    public void logout() {
        GestoreRicerche.getInstance().reset();
        setCliente(null);
    }

    public void eliminaAccount() throws SQLException {
        SerializerElimina.getInstance().eliminaCliente(this.cliente.getID());
        setCliente(null);
        GestoreRicerche.getInstance().reset();
    }
}
