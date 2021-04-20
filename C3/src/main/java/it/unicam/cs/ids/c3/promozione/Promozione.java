package it.unicam.cs.ids.c3.promozione;


import it.unicam.cs.ids.c3.utilities.HasID;
import it.unicam.cs.ids.c3.utilities.IDGenerator;

import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Objects;

public class Promozione implements HasID, PromozioneInterface{
    private String ID;
    private String nome;
    private String descrizione;
    private String IDCommerciante;
    private List<String> listaIDProdotti = new ArrayList<>();
    private GregorianCalendar dataInizio;
    private GregorianCalendar dataScadenza;

    public Promozione(String nome, String IDCommerciante, String descrizione, GregorianCalendar dataInizio, GregorianCalendar dataScadenza) {
        IDGenerator.generateID(this);
        this.nome = nome;
        this.IDCommerciante = IDCommerciante;
        this.descrizione = descrizione;
        this.dataInizio = dataInizio;
        this.dataScadenza = dataScadenza;
    }

    public Promozione(String ID, String nome, String IDCommerciante, String descrizione, GregorianCalendar dataInizio, GregorianCalendar dataScadenza) {
        this.ID = ID;
        this.nome = nome;
        this.IDCommerciante = IDCommerciante;
        this.descrizione = descrizione;
        this.dataInizio = dataInizio;
        this.dataScadenza = dataScadenza;
    }

    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public String getIDCommerciante() {
        return IDCommerciante;
    }

    public void setIDCommerciante(String IDCommerciante) {
        this.IDCommerciante = IDCommerciante;
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

    @Override
    public String toString() {
        return "Promozione{" +
                "ID=" + ID +
                ", nome=" + nome +
                ", descrizione=" + descrizione +
                ", IDCommerciante=" + IDCommerciante +
                ", listaIDProdotti=" + listaIDProdotti +
                ", dataInizio=" + dataInizio +
                ", dataScadenza=" + dataScadenza +
                '}';
    }
}
