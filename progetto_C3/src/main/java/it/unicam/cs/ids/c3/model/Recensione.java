package it.unicam.cs.ids.c3.model;

import java.util.GregorianCalendar;
import java.util.Objects;

public class Recensione implements HasID{

    private String ID;
    private String titolo;
    private String testo;
    private String IDCliente;
    private String IDCommerciante;
    private String IDProdotto;
    private VotoRecensioni votoRecensioni;
    private GregorianCalendar data;

    public Recensione(String titolo, String testo, String IDCliente, String IDCommerciante, String IDProdotto, VotoRecensioni votoRecensioni){
        IDGenerator.generateID(this);
        this.titolo = titolo;
        this.testo = testo;
        this.IDCliente = IDCliente;
        this.IDCommerciante = IDCommerciante;
        this.IDProdotto = IDProdotto;
        this.votoRecensioni = votoRecensioni;
        this.data = new GregorianCalendar();
    }

    @Override
    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public String getTitolo() {
        return titolo;
    }

    public void setTitolo(String titolo) {
        this.titolo = titolo;
    }

    public String getTesto() {
        return testo;
    }

    public void setTesto(String testo) {
        this.testo = testo;
    }

    public String getIDCliente() {
        return IDCliente;
    }

    public void setIDCliente(String IDCliente) {
        this.IDCliente = IDCliente;
    }

    public String getIDCommerciante() {
        return IDCommerciante;
    }

    public void setIDCommerciante(String IDCommerciante) {
        this.IDCommerciante = IDCommerciante;
    }

    public String getIDProdotto() {
        return IDProdotto;
    }

    public void setIDProdotto(String IDProdotto) {
        this.IDProdotto = IDProdotto;
    }

    public GregorianCalendar getData() {
        return data;
    }

    public void setData(GregorianCalendar data) {
        this.data = data;
    }

    public VotoRecensioni getVotoRecensioni() {
        return votoRecensioni;
    }

    public void setVotoRecensioni(VotoRecensioni votoRecensioni) {
        this.votoRecensioni = votoRecensioni;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Recensione that = (Recensione) o;
        return ID.equals(that.ID);
    }

    @Override
    public int hashCode() {
        return Objects.hash(ID);
    }
}
