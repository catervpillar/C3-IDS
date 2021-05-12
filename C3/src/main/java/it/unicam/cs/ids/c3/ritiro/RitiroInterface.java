package it.unicam.cs.ids.c3.ritiro;

import java.util.GregorianCalendar;
import java.util.List;

public interface RitiroInterface {
    String getID();

    void setID(String ID);

    TipoConsegna getTipoConsegna();

    void setTipoConsegna(TipoConsegna tipoConsegna);

    GregorianCalendar getDataConsegna();

    void setDataConsegna(GregorianCalendar dataConsegna);

    boolean isRitirato();

    void setRitirato(boolean ritirato);

    GregorianCalendar getDataPrenotazione();

    void setDataPrenotazione(GregorianCalendar dataPrenotazione);

    List<String> getListaIDProdotti();

    void setListaIDProdotti(List<String> listaIDProdotti);

    String getIDCommerciante();

    void setIDCommerciante(String IDCommerciante);

    String getIDCliente();

    void setIDCliente(String IDCliente);

    String getIDCorriere();

    void setIDCorriere(String IDCorriere);

    String getDestinazione();

    void setDestinazione(String destinazione);

    String getCodiceRitiro();

    void setCodiceRitiro(String codiceRitiro);

    StatoTracking getStatoTracking();

    void setStatoTracking(StatoTracking statoTracking);
}
