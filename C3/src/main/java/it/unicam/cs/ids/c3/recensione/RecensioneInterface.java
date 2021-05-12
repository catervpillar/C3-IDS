package it.unicam.cs.ids.c3.recensione;

import java.util.GregorianCalendar;

public interface RecensioneInterface {
     String getID();

     void setID(String ID);

     String getTitolo();

     void setTitolo(String titolo);

     String getTesto();

     void setTesto(String testo);

     String getIDCliente();

     void setIDCliente(String IDCliente);

     String getIDCommerciante();

     void setIDCommerciante(String IDCommerciante);

     String getIDProdotto();

     void setIDProdotto(String IDProdotto);

     GregorianCalendar getData();

     void setData(GregorianCalendar data);

     VotoRecensioni getVotoRecensioni();

     void setVotoRecensioni(VotoRecensioni votoRecensioni);
}
