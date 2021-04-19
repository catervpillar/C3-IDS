package it.unicam.cs.ids.c3.ritiro;

import it.unicam.cs.ids.c3.utilities.CodiciRitiroGenerator;
import it.unicam.cs.ids.c3.utilities.HasID;
import it.unicam.cs.ids.c3.utilities.IDGenerator;

import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Objects;

public class Ritiro implements HasID, RitiroInterface {
    private String ID;
    private List<String> listaIDProdotti = new ArrayList<>();
    private String IDCommerciante;
    private String IDCliente;
    private String IDCorriere;
    private String destinazione;
    private String codiceRitiro;
    private GregorianCalendar dataPrenotazione;
    private GregorianCalendar dataConsegna;
    private boolean ritirato;
    private TipoConsegna tipoConsegna;
    private StatoTracking statoTracking;


    public Ritiro(String IDCommerciante, String IDCliente, String IDCorriere, String destinazione, TipoConsegna tipoConsegna) {
        IDGenerator.generateID(this);
        this.IDCommerciante = IDCommerciante;
        this.IDCliente = IDCliente;
        this.IDCorriere = IDCorriere;
        this.destinazione = destinazione;
        this.ritirato = false;
        this.dataPrenotazione = new GregorianCalendar();
        this.dataConsegna = null;
        this.codiceRitiro = CodiciRitiroGenerator.getInstance().generaCodice();
        this.tipoConsegna = tipoConsegna;
        this.statoTracking = StatoTracking.IN_ELABORAZIONE;
    }

    public Ritiro(String ID, String IDCommerciante, String IDCliente, String IDCorriere, String destinazione, TipoConsegna tipoConsegna) {
        this.ID = ID;
        this.IDCommerciante = IDCommerciante;
        this.IDCliente = IDCliente;
        this.IDCorriere = IDCorriere;
        this.destinazione = destinazione;
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

    public GregorianCalendar getDataPrenotazione() {
        return dataPrenotazione;
    }

    public void setDataPrenotazione(GregorianCalendar dataPrenotazione) {
        this.dataPrenotazione = dataPrenotazione;
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

    public StatoTracking getStatoTracking() {
        return statoTracking;
    }

    public void setStatoTracking(StatoTracking statoTracking) {
        this.statoTracking = statoTracking;
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

    @Override
    public String toString() {
        return "Ritiro{" +
                "ID='" + ID + '\'' +
                ", listaIDProdotti=" + listaIDProdotti +
                ", IDCommerciante='" + IDCommerciante + '\'' +
                ", IDCliente='" + IDCliente + '\'' +
                ", IDCorriere='" + IDCorriere + '\'' +
                ", destinazione='" + destinazione + '\'' +
                ", codiceRitiro='" + codiceRitiro + '\'' +
                ", dataPrenotazione=" + dataPrenotazione +
                ", dataConsegna=" + dataConsegna +
                ", ritirato=" + ritirato +
                ", tipoConsegna=" + tipoConsegna +
                ", stato=" + statoTracking +
                '}';
    }
}
