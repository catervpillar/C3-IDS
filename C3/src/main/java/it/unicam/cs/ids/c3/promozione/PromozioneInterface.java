package it.unicam.cs.ids.c3.promozione;

import java.util.GregorianCalendar;
import java.util.List;
import it.unicam.cs.ids.c3.utenti.commerciante.Commerciante;
import it.unicam.cs.ids.c3.prodotto.Prodotto;

public interface PromozioneInterface {
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
      * Metodo getter per l'attributo ID.
      *
      * @return l'attributo ID.
      */
     String getIDCommerciante();

     /**
      * Metodo setter per l'attributo ID del {@link Commerciante}.
      *
      * @param IDCommerciante - Il nuovo attributo ID del {@link Commerciante}.
      */
     void setIDCommerciante(String IDCommerciante);

     /**
      * Metodo getter per l'attributo descrizione.
      *
      * @return l'attributo descrizione.
      */
     String getDescrizione();

     /**
      * Metodo setter per l'attributo descrizione.
      *
      * @param descrizione - Il nuovo attributo descrizione.
      */
     void setDescrizione(String descrizione);

     /**
      * Metodo getter per la lista degli ID dei {@link Prodotto}}.
      *
      * @return l'attributo ID.
      */
     List<String> getListaIDProdotti();

     /**
      * Metodo setter per la lista degli ID dei {@link Prodotto}.
      *
      * @param listaIDProdotti - La nuova lista degli ID dei {@link Prodotto}.
      */
     void setListaIDProdotti(List<String> listaIDProdotti);

     /**
      * Metodo getter per l'attributo nome.
      *
      * @return l'attributo nome.
      */
     String getNome();

     /**
      * Metodo setter per l'attributo nome.
      *
      * @param nome - Il nuovo attributo nome.
      */
     void setNome(String nome);

     /**
      * Metodo getter per l'attributo data d'inizio.
      *
      * @return l'attributo data d'inizio.
      */
     GregorianCalendar getDataInizio();

     /**
      * Metodo setter per l'attributo data d'inizio.
      *
      * @param dataInizio - Il nuovo attributo data d'inizio.
      */
     void setDataInizio(GregorianCalendar dataInizio);

     /**
      * Metodo getter per l'attributo data di scadenza.
      *
      * @return l'attributo data di scadenza.
      */
     GregorianCalendar getDataScadenza();

     /**
      * Metodo setter per l'attributo data di scadenza.
      *
      * @param dataScadenza - Il nuovo attributo data di scadenza.
      */
     void setDataScadenza(GregorianCalendar dataScadenza);
}
