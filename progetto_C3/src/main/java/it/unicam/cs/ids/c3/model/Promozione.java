package it.unicam.cs.ids.c3.model;

import java.util.GregorianCalendar;
import java.util.List;
import java.util.Objects;

public class Promozione implements HasID{

    private int ID;
    private String nome;
    private List<Commerciante> listaCommercianti;
    private List<Prodotto> listaProdotti;
    private String descrizione;
    private GregorianCalendar dataInizio;
    private GregorianCalendar dataScadenza;

    public Promozione(int ID, String nome, List<Commerciante> listaCommercianti, List<Prodotto> listaProdotti, String descrizione, GregorianCalendar dataInizio, GregorianCalendar dataScadenza) {
        this.ID = ID;
        this.listaProdotti = listaProdotti;
        this.listaCommercianti = listaCommercianti;
        this.descrizione = descrizione;
        this.dataInizio = dataInizio;
        this.dataScadenza = dataScadenza;
        this.nome = nome;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public List<Commerciante> getListaCommercianti() {
        return listaCommercianti;
    }

    public void setListaCommercianti(List<Commerciante> listaCommercianti) {
        this.listaCommercianti = listaCommercianti;
    }

    public String getDescrizione() {
        return descrizione;
    }

    public void setDescrizione(String descrizione) {
        this.descrizione = descrizione;
    }

    public List<Prodotto> getListaProdotti() {
        return listaProdotti;
    }

    public void setListaProdotti(List<Prodotto> listaProdotti) {
        this.listaProdotti = listaProdotti;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public GregorianCalendar getDataInizio() {
        return dataInizio;
    }

    public void setDataInizio(GregorianCalendar dataInizio) {
        this.dataInizio = dataInizio;
    }

    public GregorianCalendar getDataScadenza() {
        return dataScadenza;
    }

    public void setDataScadenza(GregorianCalendar dataScadenza) {
        this.dataScadenza = dataScadenza;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Promozione)) return false;
        Promozione that = (Promozione) o;
        return ID == that.ID;
    }

    @Override
    public int hashCode() {
        return Objects.hash(ID);
    }
}
