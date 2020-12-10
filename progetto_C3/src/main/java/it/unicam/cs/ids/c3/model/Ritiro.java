package it.unicam.cs.ids.c3.model;

import java.util.GregorianCalendar;
import java.util.List;
import java.util.Objects;

public class Ritiro {
    private int ID;
    private GregorianCalendar data;
    private List<Prodotto> listaProdotti;
    private Commerciante commerciante;
    private Cliente cliente;
    private Corriere corriere;
    private String destinazione;
    private CodiceRitiro codiceRitiro;

    public Ritiro(int ID, List<Prodotto> listaProdotti, Commerciante commerciante, Cliente cliente, Corriere corriere, String destinazione) {
        this.ID = ID;
        this.listaProdotti = listaProdotti;
        this.commerciante = commerciante;
        this.cliente = cliente;
        this.corriere = corriere;
        this.destinazione = destinazione;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public GregorianCalendar getData() {
        return data;
    }

    public void setData(GregorianCalendar data) {
        this.data = data;
    }

    public List<Prodotto> getListaProdotti() {
        return listaProdotti;
    }

    public void setListaProdotti(List<Prodotto> listaProdotti) {
        this.listaProdotti = listaProdotti;
    }

    public Commerciante getCommerciante() {
        return commerciante;
    }

    public void setCommerciante(Commerciante commerciante) {
        this.commerciante = commerciante;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public Corriere getCorriere() {
        return corriere;
    }

    public void setCorriere(Corriere corriere) {
        this.corriere = corriere;
    }

    public String getDestinazione() {
        return destinazione;
    }

    public void setDestinazione(String destinazione) {
        this.destinazione = destinazione;
    }

    public CodiceRitiro getCodiceRitiro() {
        return codiceRitiro;
    }

    public void setCodiceRitiro(CodiceRitiro codiceRitiro) {
        this.codiceRitiro = codiceRitiro;
    }

    public void addProdotto(Prodotto prodotto){
        if(Objects.isNull(prodotto))
            throw new NullPointerException("Prodotto nullo");
        if(listaProdotti.contains(prodotto))
            throw new IllegalArgumentException("Prodotto gia' inserito");
        listaProdotti.add(prodotto);
    }

}
