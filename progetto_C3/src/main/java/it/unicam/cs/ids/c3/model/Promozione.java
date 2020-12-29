package it.unicam.cs.ids.c3.model;

import java.util.GregorianCalendar;
import java.util.List;
import java.util.Objects;

public class Promozione implements HasID{
    private String ID;
    private String nome;
    private List<String> listaIDCommercianti;
    private List<String> listaIDProdotti;
    private String descrizione;
    private GregorianCalendar dataInizio;
    private GregorianCalendar dataScadenza;

    public Promozione(String nome, List<String> listaIDCommercianti, List<String> listaIDProdotti, String descrizione, GregorianCalendar dataInizio, GregorianCalendar dataScadenza) {
        IDGenerator.generateID(this);
        this.listaIDProdotti = listaIDProdotti;
        this.listaIDCommercianti = listaIDCommercianti;
        this.descrizione = descrizione;
        this.dataInizio = dataInizio;
        this.dataScadenza = dataScadenza;
        this.nome = nome;
    }

    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public List<String> getListaIDCommercianti() {
        return listaIDCommercianti;
    }

    public void setListaIDCommercianti(List<String> listaIDCommercianti) {
        this.listaIDCommercianti = listaIDCommercianti;
    }

    public String getDescrizione() {
        return descrizione;
    }

    public void setDescrizione(String descrizione) {
        this.descrizione = descrizione;
    }

    public List<String> getListaIDProdotti() {
        return listaIDProdotti;
    }

    public void setListaIDProdotti(List<String> listaIDProdotti) {
        this.listaIDProdotti = listaIDProdotti;
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
        if (o == null || getClass() != o.getClass()) return false;
        Promozione that = (Promozione) o;
        return ID.equals(that.ID);
    }

    @Override
    public int hashCode() {
        return Objects.hash(ID);
    }
}
