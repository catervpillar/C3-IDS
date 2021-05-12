package it.unicam.cs.ids.c3.ritiro;

import it.unicam.cs.ids.c3.prodotto.Prodotto;
import it.unicam.cs.ids.c3.prodotto.ProdottoInterface;
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

class RitiroTest {
    private Ritiro ritiro;
    private CommercianteInterface commerciante;
    private CorriereInterface corriere;
    private ClienteInterface cliente;


    @BeforeEach
    void init(){
        cliente = new Cliente("aaaaaaaa", "leocipo", "pwd", "leocipo@alice.it", "leonardo", "cipolletta");
        commerciante = new Commerciante("bbbbbbbb", "tomm99", "pwd", "tom srl");
        corriere = new Corriere("cccccccc", "paolino99", "pwd", "paolo@gmail.com", "paolo corrieri");
        ritiro = new Ritiro("12345678", "bbbbbbbb", "aaaaaaaa", "cccccccc", "via roma 1", TipoConsegna.CONSEGNA_A_DOMICILIO);
    }

    @Test
    void getTipoConsegna() {
        assertEquals(TipoConsegna.CONSEGNA_A_DOMICILIO, ritiro.getTipoConsegna());
    }

    @Test
    void setTipoConsegna() {
        ritiro.setTipoConsegna(TipoConsegna.CONSEGNA_PRESSO_PUNTO);
        assertEquals(TipoConsegna.CONSEGNA_PRESSO_PUNTO, ritiro.getTipoConsegna());
    }

    @Test
    void setDataConsegna() {
        GregorianCalendar data=new GregorianCalendar();
        ritiro.setDataConsegna(data);
        assertEquals(data, ritiro.getDataConsegna());
    }

    @Test
    void isRitirato() {
        assertFalse(ritiro.isRitirato());
    }

    @Test
    void setRitirato() {
        ritiro.setRitirato(true);
        assertTrue(ritiro.isRitirato());
    }

    @Test
    void getID() {
        assertEquals("12345678", ritiro.getID());
    }

    @Test
    void setID() {
        ritiro.setID("87654321");
        assertEquals("87654321", ritiro.getID());

    }

    @Test
    void setDataPrenotazione() {
        GregorianCalendar data=new GregorianCalendar();
        ritiro.setDataPrenotazione(data);
        assertEquals(data, ritiro.getDataPrenotazione());
    }

    @Test
    void getListaIDProdotti() {
        assertEquals(0, ritiro.getListaIDProdotti().size());
        ProdottoInterface prodotto = new Prodotto("dddddddd", "tv", 399, 52, "bbbbbbbb", null);
        ritiro.getListaIDProdotti().add("dddddddd");
        assertNotNull(ritiro.getListaIDProdotti());
    }

    @Test
    void getIDCommerciante() {
        assertEquals("bbbbbbbb", ritiro.getIDCommerciante());
    }

    @Test
    void setIDCommerciante() {
        ritiro.setIDCommerciante("oooooooo");
        assertEquals("oooooooo", ritiro.getIDCommerciante());
    }

    @Test
    void getIDCliente() {
        assertEquals("aaaaaaaa", ritiro.getIDCliente());
    }

    @Test
    void setIDCliente() {
        ritiro.setIDCliente("pppppppp");
        assertEquals("pppppppp", ritiro.getIDCliente());
    }

    @Test
    void getIDCorriere() {
        assertEquals("cccccccc", ritiro.getIDCorriere());
    }

    @Test
    void setIDCorriere() {
        ritiro.setIDCorriere("99999999");
        assertEquals("99999999", ritiro.getIDCorriere());
    }

    @Test
    void getDestinazione() {
        assertEquals("via roma 1", ritiro.getDestinazione());
    }

    @Test
    void setDestinazione() {
        ritiro.setDestinazione("via milano 2");
        assertEquals("via milano 2", ritiro.getDestinazione());
    }

    @Test
    void setCodiceRitiro() {
        ritiro.setCodiceRitiro("123456");
        assertEquals("123456", ritiro.getCodiceRitiro());
    }

    @Test
    void getStatoTracking() {
        ritiro.setStatoTracking(StatoTracking.CONSEGNATO);
        assertEquals(StatoTracking.CONSEGNATO, ritiro.getStatoTracking());
    }
}