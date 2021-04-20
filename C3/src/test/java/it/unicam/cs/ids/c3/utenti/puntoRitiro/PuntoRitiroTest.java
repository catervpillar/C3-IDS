package it.unicam.cs.ids.c3.utenti.puntoRitiro;

import it.unicam.cs.ids.c3.utenti.cliente.Cliente;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PuntoRitiroTest {

    private PuntoRitiro puntoRitiro;

    @BeforeEach
    void init(){
        puntoRitiro = new PuntoRitiro("12345678", "leonardo99", "pwd", "leocipo@gmail.com", "leo deposito");
    }

    @Test
    void getRagioneSociale() {
        assertEquals("leonardo99", puntoRitiro.getUsername());
    }

    @Test
    void setRagioneSociale() {
        puntoRitiro.setRagioneSociale("magazzino cipolletta");
        assertEquals("magazzino cipolletta", puntoRitiro.getRagioneSociale());
    }

    @Test
    void testToString() {
        assertEquals("leo deposito", puntoRitiro.toString());
    }
}