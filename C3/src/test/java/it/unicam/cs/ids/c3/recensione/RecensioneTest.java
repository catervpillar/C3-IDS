package it.unicam.cs.ids.c3.recensione;

import it.unicam.cs.ids.c3.prodotto.Prodotto;
import it.unicam.cs.ids.c3.prodotto.ProdottoInterface;
import it.unicam.cs.ids.c3.ritiro.GestoreRitiri;
import it.unicam.cs.ids.c3.ritiro.Ritiro;
import it.unicam.cs.ids.c3.ritiro.RitiroInterface;
import it.unicam.cs.ids.c3.ritiro.TipoConsegna;
import it.unicam.cs.ids.c3.utenti.cliente.Cliente;
import it.unicam.cs.ids.c3.utenti.cliente.ClienteInterface;
import it.unicam.cs.ids.c3.utenti.commerciante.Commerciante;
import it.unicam.cs.ids.c3.utenti.commerciante.CommercianteInterface;
import it.unicam.cs.ids.c3.utenti.corriere.Corriere;
import it.unicam.cs.ids.c3.utenti.corriere.CorriereInterface;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.GregorianCalendar;

import static org.junit.jupiter.api.Assertions.*;

class RecensioneTest {
    private RitiroInterface ritiro;
    private CommercianteInterface commerciante;
    private CorriereInterface corriere;
    private ClienteInterface cliente;
    private Recensione recensione;
    private ProdottoInterface prodotto;
    @BeforeEach
    void init(){
        cliente = new Cliente("aaaaaaaa", "leocipo", "pwd", "leocipo@alice.it", "leonardo", "cipolletta");
        commerciante = new Commerciante("bbbbbbbb", "tomm99", "pwd", "tom srl");
        corriere = new Corriere("cccccccc", "paolino99", "pwd", "paolo@gmail.com", "paolo corrieri");
        ritiro = new Ritiro("12345678", "bbbbbbbb", "aaaaaaaa", "cccccccc", "via roma 1", TipoConsegna.CONSEGNA_A_DOMICILIO);
        recensione = new Recensione("25463871", "titolo", "testo", "aaaaaaaa", "bbbbbbbb", "ssssssss", VotoRecensioni.CINQUE_STELLE);
        prodotto = new Prodotto("ssssssss", "prodotto", 330, 10, "bbbbbbbb", null);
    }

    @Test
    void getID() {
        assertEquals("25463871", recensione.getID());
    }

    @Test
    void setID() {
        recensione.setID("98720dsf");
        assertEquals("98720dsf", recensione.getID());
    }

    @Test
    void getTitolo() {
        assertEquals("titolo", recensione.getTitolo());
    }

    @Test
    void setTitolo() {
        recensione.setTitolo("ottimo prodotto");
        assertEquals("ottimo prodotto", recensione.getTitolo());
    }

    @Test
    void getTesto() {
        assertEquals("testo", recensione.getTesto());
    }

    @Test
    void setTesto() {
        recensione.setTesto("testo esempio");
        assertEquals("testo esempio", recensione.getTesto());
    }

    @Test
    void getIDCliente() {
        assertEquals("aaaaaaaa", recensione.getIDCliente());
    }

    @Test
    void getIDCommerciante() {
        assertEquals("bbbbbbbb", recensione.getIDCommerciante());
    }

    @Test
    void getIDProdotto() {
        assertEquals("ssssssss", recensione.getIDProdotto());
    }

    @Test
    void setData() {
        GregorianCalendar gregorianCalendar = new GregorianCalendar();
        recensione.setData(gregorianCalendar);
        assertEquals(gregorianCalendar, recensione.getData());
    }

    @Test
    void getVotoRecensioni() {
        assertEquals(VotoRecensioni.CINQUE_STELLE, recensione.getVotoRecensioni());
    }

    @Test
    void setVotoRecensioni() {
        recensione.setVotoRecensioni(VotoRecensioni.QUATTRO_STELLE);
        assertEquals(VotoRecensioni.QUATTRO_STELLE, recensione.getVotoRecensioni());
    }

    @Test
    void testEquals() {
        Recensione recensione2 = new Recensione("98765iru", "titolo", "testo", "aaaaaaaa", "bbbbbbbb", "ssssssss", VotoRecensioni.CINQUE_STELLE);
        assertNotEquals(recensione2, recensione);
        assertEquals(recensione2, recensione2);
    }
}