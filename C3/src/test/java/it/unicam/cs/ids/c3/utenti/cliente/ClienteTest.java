package it.unicam.cs.ids.c3.utenti.cliente;

import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.*;

class ClienteTest {
    private Cliente cliente1;

    @BeforeEach
    void init(){
        cliente1 = new Cliente("12345678", "tommaso99", "pwd", "tommaso99@gmail.com", "Tommaso", "Catervi");
    }

    @Test
    @Order(1)
    void getNome() {
        assertEquals("Tommaso", cliente1.getNome());
    }
    @Test
    @Order(2)
    void getCognome() {
        assertEquals("Catervi", cliente1.getCognome());
    }

    @Test
    @Order(3)
    void setNome() {
        cliente1.setNome("Leonardo");
        assertEquals("Leonardo", cliente1.getNome());
    }

    @Test
    @Order(4)
    void setCognome() {
        cliente1.setCognome("Cipolletta");
        assertEquals("Cipolletta", cliente1.getCognome());
    }

    @Test
    @Order(5)
    void testToString() {
        assertEquals("{ID=12345678, username=tommaso99, password=pwd, email=tommaso99@gmail.com, telefono=null, indirizzo=null, nome=Tommaso, cognome=Catervi}", cliente1.toString());
    }
}