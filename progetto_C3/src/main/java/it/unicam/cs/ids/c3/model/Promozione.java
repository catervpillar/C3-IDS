package it.unicam.cs.ids.c3.model;

import java.util.GregorianCalendar;
import java.util.List;
import java.util.Objects;

public class Promozione implements HasID{

    private int ID;
    private List<Commerciante> listaCommercianti;
    private String descrizione;
    private CategoriaPromozione categoria;
    private GregorianCalendar dataInizio;
    private GregorianCalendar dataScadenza;

    public Promozione(int ID, List<Commerciante> listaCommercianti, String descrizione, CategoriaPromozione categoria, GregorianCalendar dataInizio, GregorianCalendar dataScadenza) {
        this.ID = ID;
        this.listaCommercianti = listaCommercianti;
        this.descrizione = descrizione;
        this.categoria = categoria;
        this.dataInizio = dataInizio;
        this.dataScadenza = dataScadenza;
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

    public CategoriaPromozione getCategoria() {
        return categoria;
    }

    public void setCategoria(CategoriaPromozione categoria) {
        this.categoria = categoria;
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
