package it.unicam.cs.ids.c3.utenti.corriere;

import it.unicam.cs.ids.c3.javafx.commerciante.ICommerciante;
import it.unicam.cs.ids.c3.utenti.cliente.Cliente;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CorriereTest {

    private Corriere corriere;

    @BeforeEach
    void init(){
        corriere = new Corriere("12345678", "leonardo99", "pwd", "leocipo@gmail.com", "leonardo ups");
    }

    @Test
    void getRagioneSociale() {
        assertEquals("leonardo ups", corriere.getRagioneSociale());
    }

    @Test
    void setRagioneSociale() {
        corriere.setRagioneSociale("leonardo corriere");
        assertEquals("leonardo corriere", corriere.getRagioneSociale());
    }

    @Test
    void getStato() {
        assertEquals(StatoCorriere.NON_DISPONIBILE, corriere.getStato());
    }

    @Test
    void setStato() {
        corriere.setStato(StatoCorriere.DISPONIBILE);
        assertEquals(StatoCorriere.DISPONIBILE, corriere.getStato());
    }

    @Test
    void testToString() {
        assertEquals("leonardo ups", corriere.toString());
    }
}