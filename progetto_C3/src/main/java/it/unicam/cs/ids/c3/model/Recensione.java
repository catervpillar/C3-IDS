package it.unicam.cs.ids.c3.model;

import java.util.GregorianCalendar;
import java.util.Objects;

public class Recensione implements HasID {
    private String ID;
    private String titolo;
    private String testo;
    private String IDCliente;
    private String IDCommerciante;
    private String IDProdotto;
    private VotoRecensioni votoRecensioni;
    private GregorianCalendar data;

    public Recensione(String titolo, String testo, String IDCliente, String IDCommerciante, String IDProdotto, VotoRecensioni votoRecensioni) {
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
        if (Objects.isNull(ID))
            throw new NullPointerException(ID_NULLO);
        this.ID = ID;
    }

    public String getTitolo() {
        return titolo;
    }

    public void setTitolo(String titolo) {
        if (Objects.isNull(titolo))
            throw new NullPointerException("Il titolo e' nullo");
        if (titolo.equals(""))
            throw new NullPointerException("Il titolo non e' valido");
        this.titolo = titolo;
    }

    public String getTesto() {
        return testo;
    }

    public void setTesto(String testo) {
        if (Objects.isNull(testo))
            throw new NullPointerException("Il testo e' nullo");
        this.testo = testo;
    }

    public String getIDCliente() {
        return IDCliente;
    }

    public void setIDCliente(String IDCliente) {
        if (Objects.isNull(IDCliente))
            throw new NullPointerException(ID_NULLO);
        this.IDCliente = IDCliente;
    }

    public String getIDCommerciante() {
        return IDCommerciante;
    }

    public void setIDCommerciante(String IDCommerciante) {
        if (Objects.isNull(IDCommerciante))
            throw new NullPointerException(ID_NULLO);
        this.IDCommerciante = IDCommerciante;
    }

    public String getIDProdotto() {
        return IDProdotto;
    }

    public void setIDProdotto(String IDProdotto) {
        if (Objects.isNull(IDProdotto))
            throw new NullPointerException(ID_NULLO);
        this.IDProdotto = IDProdotto;
    }

    public GregorianCalendar getData() {
        return data;
    }

    public void setData(GregorianCalendar data) {
        if (Objects.isNull(data))
            throw new NullPointerException("La data e' nulla");
        if (data.compareTo(new GregorianCalendar()) < 0)
            throw new IllegalArgumentException("La data non e' valida");
        this.data = data;
    }

    public VotoRecensioni getVotoRecensioni() {
        return votoRecensioni;
    }

    public void setVotoRecensioni(VotoRecensioni votoRecensioni) {
        if (Objects.isNull(votoRecensioni))
            throw new NullPointerException("Il voto e' nullo");
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
