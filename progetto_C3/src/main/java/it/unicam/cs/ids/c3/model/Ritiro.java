package it.unicam.cs.ids.c3.model;

import java.sql.Date;
import java.time.Instant;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Objects;

public class Ritiro implements HasID{
    private int ID;
    private GregorianCalendar data;
    private List<Prodotto> listaProdotti;
    private Commerciante commerciante;
    private Cliente cliente;
    private Corriere corriere;
    private String destinazione;
    private String codiceRitiro;
    private Tracking tracking;
    private GregorianCalendar dataConsegna;
    private boolean ritirato;
    private TipoConsegna tipoConsegna;

    public Ritiro(int ID, List<Prodotto> listaProdotti, Commerciante commerciante, Cliente cliente, Corriere corriere, String destinazione, TipoConsegna tipoConsegna) {
        this.ID = ID;
        this.listaProdotti = listaProdotti;
        this.commerciante = commerciante;
        this.cliente = cliente;
        this.corriere = corriere;
        this.destinazione = destinazione;
        this.ritirato = false;
        this.data = new GregorianCalendar();
        this.codiceRitiro = CodiciRitiroGenerator.getInstance().generaCodice();
        this.tipoConsegna = tipoConsegna;
    }

    public TipoConsegna getTipoConsegna() {
        return tipoConsegna;
    }

    public void setTipoConsegna(TipoConsegna tipoConsegna) {
        this.tipoConsegna = tipoConsegna;
    }

    public GregorianCalendar getDataConsegna() {
        return dataConsegna;
    }

    public void setDataConsegna(GregorianCalendar dataConsegna) {
        this.dataConsegna = dataConsegna;
    }

    public boolean isRitirato() {
        return ritirato;
    }

    public void setRitirato(boolean ritirato) {
        this.ritirato = ritirato;
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

    public String getCodiceRitiro() {
        return codiceRitiro;
    }

    public void setCodiceRitiro(String codiceRitiro) {
        this.codiceRitiro = codiceRitiro;
    }

    public Tracking getTracking() {
        return tracking;
    }

    public void setTracking(Tracking tracking) {
        this.tracking = tracking;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Ritiro)) return false;
        Ritiro ritiro = (Ritiro) o;
        return ID == ritiro.ID &&
                codiceRitiro.equals(ritiro.codiceRitiro);
    }

    @Override
    public int hashCode() {
        return Objects.hash(ID, codiceRitiro);
    }
}
