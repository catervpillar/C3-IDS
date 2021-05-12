package it.unicam.cs.ids.c3.utenti.commerciante;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CommercianteTest {
    Commerciante commerciante;

    @BeforeEach
    void init(){
        commerciante = new Commerciante("12345678", "tommaso99", "pwd", "tommaso99@gmail.com", "tom srl");
    }
    @Test
    void getRagioneSociale() {
        assertEquals("tom srl", commerciante.getRagioneSociale());
    }

    @Test
    void setRagioneSociale() {
        commerciante.setUsername("tommy99");
        assertEquals("tommy99", commerciante.getUsername());
    }

    @Test
    void testToString() {
        assertEquals("{ID=12345678, username=tommaso99, password=pwd, email=tommaso99@gmail.com, telefono=null, indirizzo=null, ragioneSociale=tom srl}", commerciante.toString());
    }
}