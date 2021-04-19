package it.unicam.cs.ids.c3.ritiro;

import java.util.GregorianCalendar;
import java.util.List;

public interface RitiroInterface {
    public TipoConsegna getTipoConsegna();

    public void setTipoConsegna(TipoConsegna tipoConsegna);

    public GregorianCalendar getDataConsegna();

    public void setDataConsegna(GregorianCalendar dataConsegna);

    public boolean isRitirato();

    public void setRitirato(boolean ritirato);

    public GregorianCalendar getDataPrenotazione();

    public void setDataPrenotazione(GregorianCalendar dataPrenotazione);

    public List<String> getListaIDProdotti();

    public void setListaIDProdotti(List<String> listaIDProdotti);

    public String getIDCommerciante();

    public void setIDCommerciante(String IDCommerciante);

    public String getIDCliente();

    public void setIDCliente(String IDCliente);

    public String getIDCorriere();

    public void setIDCorriere(String IDCorriere);

    public String getDestinazione();

    public void setDestinazione(String destinazione);

    public String getCodiceRitiro();

    public void setCodiceRitiro(String codiceRitiro);

    public StatoTracking getStatoTracking();

    public void setStatoTracking(StatoTracking statoTracking);
}
