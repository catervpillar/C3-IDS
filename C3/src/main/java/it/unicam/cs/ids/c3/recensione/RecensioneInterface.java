package it.unicam.cs.ids.c3.recensione;

import java.util.GregorianCalendar;
import it.unicam.cs.ids.c3.utenti.cliente.Cliente;
import it.unicam.cs.ids.c3.prodotto.Prodotto;
import it.unicam.cs.ids.c3.utenti.commerciante.Commerciante;

public interface RecensioneInterface {
     /**
      * Metodo getter per l'attributo ID.
      *
      * @return l'attributo ID.
      */
     String getID();

     /**
      * Metodo setter per l'attributo ID.
      *
      * @param ID - Il nuovo attributo ID.
      */
     void setID(String ID);

     /**
      * Metodo getter per l'attributo titolo.
      *
      * @return l'attributo titolo.
      */
     String getTitolo();

     /**
      * Metodo setter per l'attributo titolo.
      *
      * @param titolo - Il nuovo attributo ID.
      */
     void setTitolo(String titolo);

     /**
      * Metodo getter per l'attributo testo.
      *
      * @return l'attributo testo.
      */
     String getTesto();

     /**
      * Metodo setter per l'attributo testo.
      *
      * @param testo - Il nuovo attributo testo.
      */
     void setTesto(String testo);

     /**
      * Metodo getter per l'attributo ID del {@link Cliente}.
      *
      * @return l'attributo ID del {@link Cliente}.
      */
     String getIDCliente();

     /**
      * Metodo setter per l'attributo ID del {@link Cliente}.
      *
      * @param IDCliente - Il nuovo attributo ID del {@link Cliente}.
      */
     void setIDCliente(String IDCliente);

     /**
      * Metodo getter per l'attributo ID del {@link Commerciante}.
      *
      * @return l'attributo ID del {@link Commerciante}.
      */
     String getIDCommerciante();

     /**
      * Metodo setter per l'attributo ID del {@link Commerciante}.
      *
      * @param IDCommerciante - Il nuovo attributo ID del {@link Commerciante}.
      */
     void setIDCommerciante(String IDCommerciante);

     /**
      * Metodo getter per l'attributo ID del {@link Prodotto}.
      *
      * @return l'attributo ID del {@link Prodotto}.
      */
     String getIDProdotto();

     /**
      * Metodo setter per l'attributo ID del {@link Prodotto}.
      *
      * @param IDProdotto - Il nuovo attributo ID del {@link Prodotto}.
      */
     void setIDProdotto(String IDProdotto);

     /**
      * Metodo getter per l'attributo data.
      *
      * @return l'attributo data.
      */
     GregorianCalendar getData();

     /**
      * Metodo setter per l'attributo data.
      *
      * @param data - Il nuovo attributo data.
      */
     void setData(GregorianCalendar data);

     /**
      * Metodo getter per l'attributo votoRecensioni.
      *
      * @return l'attributo voto.
      */
     VotoRecensioni getVotoRecensioni();

     /**
      * Metodo setter per l'attributo votoRecensioni.
      *
      * @param votoRecensioni - Il nuovo attributo votoRecensioni.
      */
     void setVotoRecensioni(VotoRecensioni votoRecensioni);
}
