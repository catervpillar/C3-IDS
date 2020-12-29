package it.unicam.cs.ids.c3.model;

import java.util.GregorianCalendar;
import java.util.List;
import java.util.Objects;

public class Ritiro implements HasID{
    private String ID;
    private GregorianCalendar data;
    private List<String> listaIDProdotti;
    private String IDCommerciante;
    private String IDCliente;
    private String IDCorriere;
    private String destinazione;
    private String codiceRitiro;
    private String IDTracking;
    private GregorianCalendar dataConsegna;
    private boolean ritirato;
    private TipoConsegna tipoConsegna;

    public Ritiro(List<String> listaIDProdotti, String IDCommerciante, String IDCliente, String IDCorriere, String destinazione, TipoConsegna tipoConsegna) {
        IDGenerator.generateID(this);
        IDGenerator.generateID(this);
        this.listaIDProdotti = listaIDProdotti;
        this.IDCommerciante = IDCommerciante;
        this.IDCliente = IDCliente;
        this.IDCorriere = IDCorriere;
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

    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public GregorianCalendar getData() {
        return data;
    }

    public void setData(GregorianCalendar data) {
        this.data = data;
    }

    public List<String> getListaIDProdotti() {
        return listaIDProdotti;
    }

    public void setListaIDProdotti(List<String> listaIDProdotti) {
        this.listaIDProdotti = listaIDProdotti;
    }

    public String getIDCommerciante() {
        return IDCommerciante;
    }

    public void setIDCommerciante(String IDCommerciante) {
        this.IDCommerciante = IDCommerciante;
    }

    public String getIDCliente() {
        return IDCliente;
    }

    public void setIDCliente(String IDCliente) {
        this.IDCliente = IDCliente;
    }

    public String getIDCorriere() {
        return IDCorriere;
    }

    public void setIDCorriere(String IDCorriere) {
        this.IDCorriere = IDCorriere;
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

    public String getIDTracking() {
        return IDTracking;
    }

    public void setIDTracking(String IDTracking) {
        this.IDTracking = IDTracking;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Ritiro ritiro = (Ritiro) o;
        return ID.equals(ritiro.ID);
    }

    @Override
    public int hashCode() {
        return Objects.hash(ID);
    }
}
