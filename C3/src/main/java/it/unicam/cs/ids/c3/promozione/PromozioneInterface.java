package it.unicam.cs.ids.c3.promozione;

import java.util.GregorianCalendar;
import java.util.List;

public interface PromozioneInterface {
     String getID();

     void setID(String ID);

     String getIDCommerciante();

     void setIDCommerciante(String IDCommerciante);

     String getDescrizione();

     void setDescrizione(String descrizione);

     List<String> getListaIDProdotti();

     void setListaIDProdotti(List<String> listaIDProdotti);

     String getNome();

     void setNome(String nome);

     GregorianCalendar getDataInizio();

     void setDataInizio(GregorianCalendar dataInizio);

     GregorianCalendar getDataScadenza();

     void setDataScadenza(GregorianCalendar dataScadenza);
}
