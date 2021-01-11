package it.unicam.cs.ids.c3.model;

import java.util.GregorianCalendar;
import java.util.List;
import java.util.Objects;

public class Ritiro implements HasID {
    private String ID;
    private List<String> listaIDProdotti;
    private String IDCommerciante;
    private String IDCliente;
    private String IDCorriere;
    private String destinazione;
    private String codiceRitiro;
    private String IDTracking;
    private GregorianCalendar dataPrenotazione;
    private GregorianCalendar dataConsegna;
    private boolean ritirato;
    private TipoConsegna tipoConsegna;

    public Ritiro(List<String> listaIDProdotti, String IDCommerciante, String IDCliente, String IDCorriere, String destinazione, TipoConsegna tipoConsegna) {
        IDGenerator.generateID(this);
        this.listaIDProdotti = listaIDProdotti;
        this.IDCommerciante = IDCommerciante;
        this.IDCliente = IDCliente;
        this.IDCorriere = IDCorriere;
        this.destinazione = destinazione;
        this.ritirato = false;
        this.dataPrenotazione = new GregorianCalendar();
        this.codiceRitiro = CodiciRitiroGenerator.getInstance().generaCodice();
        this.tipoConsegna = tipoConsegna;
    }

    public TipoConsegna getTipoConsegna() {
        return tipoConsegna;
    }

    public void setTipoConsegna(TipoConsegna tipoConsegna) {
        if (Objects.isNull(tipoConsegna))
            throw new NullPointerException("Il tipo della consegna e' nullo");
        this.tipoConsegna = tipoConsegna;
    }

    public GregorianCalendar getDataConsegna() {
        return dataConsegna;
    }

    public void setDataConsegna(GregorianCalendar dataConsegna) {
        if (Objects.isNull(dataConsegna))
            throw new NullPointerException("La data di consegna e' nulla");
        if (dataConsegna.compareTo(new GregorianCalendar()) < 0)
            throw new IllegalArgumentException("La data di consegna non e' valida");
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
        if (Objects.isNull(ID))
            throw new NullPointerException(ID_NULLO);
        this.ID = ID;
    }

    public GregorianCalendar getDataPrenotazione() {
        return dataPrenotazione;
    }

    public void setDataPrenotazione(GregorianCalendar dataPrenotazione) {
        if (Objects.isNull(dataPrenotazione))
            throw new NullPointerException("La data di prenotazione e' nulla");
        if (dataPrenotazione.compareTo(new GregorianCalendar()) < 0)
            throw new IllegalArgumentException("La data di prenotazione non e' valida");
        this.dataPrenotazione = dataPrenotazione;
    }

    public List<String> getListaIDProdotti() {
        return listaIDProdotti;
    }

    public void setListaIDProdotti(List<String> listaIDProdotti) {
        if (Objects.isNull(listaIDProdotti))
            throw new NullPointerException("La lista dei prodotti e' nulla");
        if (listaIDProdotti.isEmpty())
            throw new IllegalArgumentException("La lista dei prodotti e' vuota");
        this.listaIDProdotti = listaIDProdotti;
    }

    public String getIDCommerciante() {
        return IDCommerciante;
    }

    public void setIDCommerciante(String IDCommerciante) {
        if (Objects.isNull(IDCommerciante))
            throw new NullPointerException(ID_NULLO);
        this.IDCommerciante = IDCommerciante;
    }

    public String getIDCliente() {
        return IDCliente;
    }

    public void setIDCliente(String IDCliente) {
        if (Objects.isNull(IDCliente))
            throw new NullPointerException(ID_NULLO);
        this.IDCliente = IDCliente;
    }

    public String getIDCorriere() {
        return IDCorriere;
    }

    public void setIDCorriere(String IDCorriere) {
        if (Objects.isNull(IDCorriere))
            throw new NullPointerException(ID_NULLO);
        this.IDCorriere = IDCorriere;
    }

    public String getDestinazione() {
        return destinazione;
    }

    public void setDestinazione(String destinazione) {
        if (Objects.isNull(destinazione))
            throw new NullPointerException("La destinazione e' nulla");
        if (destinazione.equals(""))
            throw new NullPointerException("La destinazione non e' valida");
        this.destinazione = destinazione;
    }

    public String getCodiceRitiro() {
        return codiceRitiro;
    }

    public void setCodiceRitiro(String codiceRitiro) {
        if (Objects.isNull(codiceRitiro))
            throw new NullPointerException("Il codice di ritiro e' nullo");
        if (codiceRitiro.length() != 6)
            throw new IllegalArgumentException("Il codice di ritiro non e' valido");
        this.codiceRitiro = codiceRitiro;
    }

    public String getIDTracking() {
        return IDTracking;
    }

    public void setIDTracking(String IDTracking) {
        if (Objects.isNull(IDTracking))
            throw new NullPointerException(ID_NULLO);
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
