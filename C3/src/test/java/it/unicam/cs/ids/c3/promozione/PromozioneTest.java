package it.unicam.cs.ids.c3.promozione;

import it.unicam.cs.ids.c3.prodotto.Prodotto;
import it.unicam.cs.ids.c3.prodotto.ProdottoInterface;
import it.unicam.cs.ids.c3.recensione.Recensione;
import it.unicam.cs.ids.c3.recensione.VotoRecensioni;
import it.unicam.cs.ids.c3.ritiro.Ritiro;
import it.unicam.cs.ids.c3.ritiro.TipoConsegna;
import it.unicam.cs.ids.c3.utenti.cliente.Cliente;
import it.unicam.cs.ids.c3.utenti.commerciante.Commerciante;
import it.unicam.cs.ids.c3.utenti.commerciante.CommercianteInterface;
import it.unicam.cs.ids.c3.utenti.corriere.Corriere;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Calendar;
import java.util.GregorianCalendar;

import static org.junit.jupiter.api.Assertions.*;

class PromozioneTest {

    private CommercianteInterface commerciante;
    private Promozione promozione;
    private ProdottoInterface prodotto;
    GregorianCalendar dataInizio, dataScadenza;

    @BeforeEach
    void init(){
         dataInizio = new GregorianCalendar();
         dataScadenza = new GregorianCalendar();
         commerciante = new Commerciante("bbbbbbbb", "tomm99", "pwd", "tom srl");
         promozione = new Promozione("25463871", "nome", "bbbbbbbb", "descrizione", dataInizio, dataScadenza);
         prodotto = new Prodotto("ssssssss", "prodotto", 330, 10, "bbbbbbbb", null);
    }

    @Test
    void getID() {
        assertEquals("25463871", promozione.getID());
    }

    @Test
    void setID() {
        promozione.setID("jue83746");
        assertEquals("jue83746", promozione.getID());
    }

    @Test
    void getIDCommerciante() {
        assertEquals("bbbbbbbb", promozione.getIDCommerciante());
    }

    @Test
    void setIDCommerciante() {
        promozione.setIDCommerciante("laks4d5d");
        assertEquals("laks4d5d", promozione.getIDCommerciante());
    }

    @Test
    void getDescrizione() {
        assertEquals("descrizione", promozione.getDescrizione());
    }

    @Test
    void setDescrizione() {
        promozione.setDescrizione("descrizione promozione");
        assertEquals("descrizione promozione", promozione.getDescrizione());
    }

    @Test
    void getNome() {
        assertEquals("nome", promozione.getNome());
    }

    @Test
    void setNome() {
        promozione.setNome("nome promozione");
        assertEquals("nome promozione", promozione.getNome());
    }

    @Test
    void getDataInizio() {
        assertEquals(dataInizio, promozione.getDataInizio());
    }

    @Test
    void setDataInizio() {
        GregorianCalendar data = new GregorianCalendar(2021, Calendar.NOVEMBER, 5);
        promozione.setDataInizio(data);
        assertEquals(data, promozione.getDataInizio());
    }

    @Test
    void getDataScadenza() {
        assertEquals(dataScadenza, promozione.getDataScadenza());
    }

    @Test
    void setDataScadenza() {
        GregorianCalendar data = new GregorianCalendar(2022, Calendar.APRIL, 5);
        promozione.setDataScadenza(data);
        assertEquals(data, promozione.getDataScadenza());

    }
}