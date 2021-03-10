package it.unicam.cs.ids.c3.controller;

import it.unicam.cs.ids.c3.model.*;
import it.unicam.cs.ids.c3.services.*;
import it.unicam.cs.ids.c3.utilities.Controllore;


import java.sql.ResultSet;
import java.sql.SQLException;
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

    public void setCommerciante(Commerciante commerciante) {
        this.commerciante = commerciante;
    }

    public void creaCommerciante(String username, String password, String email, String ragioneSociale) throws SQLException {
        Controllore.getInstance().controllaCommerciante(username, password, email, ragioneSociale);
        Commerciante commerciante = new Commerciante(username, password, email, ragioneSociale);
        SerializerAggiunta.getInstance().serializzaCommerciante(commerciante);
    }

    public void modificaCommerciante(String username, String password, String email, String ragioneSociale, String telefono, String indirizzo) throws SQLException {
        Controllore.getInstance().controllaCommerciante(username, password, email, ragioneSociale);
        Commerciante commerciante = new Commerciante(this.commerciante.getID(), username, password, email, ragioneSociale);

        if (!indirizzo.isBlank()) {
            Controllore.getInstance().controllaIndirizzo(indirizzo);
            commerciante.setIndirizzo(indirizzo);
        }
        if (!telefono.isBlank()) {
            Controllore.getInstance().controllaNumero(telefono, 10);
            commerciante.setTelefono(telefono);
        }
        setCommerciante(commerciante);
        SerializerModifica.getInstance().modificaCommerciante(commerciante);
    }

    public void eliminaAccount() throws SQLException {
        SerializerElimina.getInstance().eliminaCommerciante(this.commerciante.getID());
        setCommerciante(null);
        GestoreRicerche.getInstance().reset();
    }

    public void logout() {
        GestoreRicerche.getInstance().reset();
        setCommerciante(null);
    }

    public boolean loginCommerciante(String username, String password) throws SQLException {
        String sql = "select * from commerciante where username = \"" + username + "\" and password = \"" + password + "\";";
        ResultSet resultSet = DBManager.getInstance().executeQuery(sql);

        if (resultSet.last()) {
            resultSet.beforeFirst();
            List<Commerciante> listaCommercianti = Deserializer.getInstance().deserializzaCommercianti(resultSet);
            this.commerciante = listaCommercianti.get(0);
            return true;
        } else return false;
    }

    public List<Prodotto> getProdotti() throws SQLException {
        return GestoreRicerche.getInstance().cercaProdotto(null, null, this.commerciante.getID());
    }

    public List<Ritiro> getRitiri() throws SQLException {
        return GestoreRicerche.getInstance().getRitiri(this.commerciante.getID(), null, null, null);
    }

    public void creaProdotto(String nome, Double prezzo, int quantita, String URLimmagine) throws SQLException {
        SerializerAggiunta.getInstance().serializzaProdotto(new Prodotto(nome, prezzo, quantita, this.commerciante.getID(), URLimmagine));
    }

    public void prenotaRitiro(String IDCliente, String IDCorriere, String destinazione, TipoConsegna tipoConsegna, List<String> listaIDProdotti) throws SQLException {
        GestoreRitiri.getInstance().creaRitiro(this.commerciante.getID(), IDCliente, IDCorriere, destinazione, tipoConsegna, listaIDProdotti);
    }

    public void modificaRitiro() throws SQLException {
        GestoreRitiri.getInstance().modificaRitiro();
    }

    public void eliminaRitiro() throws SQLException {
        GestoreRitiri.getInstance().eliminaRitiro();
    }

    public List<Promozione> getPromozioni() throws SQLException {
        return GestoreRicerche.getInstance().getPromozioni(this.commerciante.getID());
    }

    public void creaPromozione(String nome, String descrizione, GregorianCalendar dataInizio, GregorianCalendar dataScadenza, List<String> listaIDProdotti) throws SQLException {
        GestorePromozioni.getInstance().creaPromozione(nome, this.commerciante.getID(), listaIDProdotti, descrizione, dataInizio, dataScadenza);
    }

    public void modificaPromozione(List<String> listaIDCommercianti, String nome, String descrizione, List<String> listaIDProdotti, GregorianCalendar dataInizio, GregorianCalendar dataScadenza, String IDPromozione) {
        GestorePromozioni.getInstance().modificaPromozione(nome, listaIDCommercianti, listaIDProdotti, descrizione, dataInizio, dataScadenza, IDPromozione);
    }

    public void rimuoviPromozione(String IDPromozione) {
        GestorePromozioni.getInstance().rimuoviPromozione(IDPromozione);
    }
}
