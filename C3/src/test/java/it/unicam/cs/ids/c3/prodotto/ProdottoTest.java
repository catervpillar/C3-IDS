package it.unicam.cs.ids.c3.prodotto;

import it.unicam.cs.ids.c3.promozione.Promozione;
import it.unicam.cs.ids.c3.utenti.commerciante.Commerciante;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.GregorianCalendar;

import static org.junit.jupiter.api.Assertions.*;

class ProdottoTest {

    private Commerciante commerciante;
    private Promozione promozione;
    private Prodotto prodotto;
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
        assertEquals("ssssssss", prodotto.getID());
    }

    @Test
    void setID() {
        prodotto.setID("kokokoko");
        assertEquals("kokokoko", prodotto.getID());
    }

    @Test
    void getNome() {
        assertEquals("prodotto", prodotto.getNome());
    }

    @Test
    void setNome() {
        prodotto.setNome("nome prodotto");
        assertEquals("nome prodotto", prodotto.getNome());
    }

    @Test
    void getIDCommerciante() {
        assertEquals("bbbbbbbb", prodotto.getIDCommerciante());
    }

    @Test
    void setIDCommerciante() {
        prodotto.setIDCommerciante("pa9eir7s");
        assertEquals("pa9eir7s", prodotto.getIDCommerciante());
    }

    @Test
    void getPrezzo() {
        assertEquals(330, prodotto.getPrezzo());
    }

    @Test
    void setPrezzo() {
        prodotto.setPrezzo(500);
        assertEquals(500, prodotto.getPrezzo());
    }

    @Test
    void getQuantita() {
        assertEquals(10, prodotto.getQuantita());
    }

    @Test
    void setQuantita() {
        prodotto.setQuantita(30);
        assertEquals(30, prodotto.getQuantita());
    }

    @Test
    void getURLImmagine() {
        assertNull(prodotto.getURLImmagine());
    }

    @Test
    void testEquals() {
    }

    @Test
    void testHashCode() {
    }

    @Test
    void testToString() {
        assertEquals("prodotto, " +"\u20AC330.0, Commerciante: bbbbbbbb", prodotto.toString());
    }
}