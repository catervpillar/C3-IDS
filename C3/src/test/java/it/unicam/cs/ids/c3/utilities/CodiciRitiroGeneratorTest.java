package it.unicam.cs.ids.c3.utilities;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CodiciRitiroGeneratorTest {

    @Test
    void getInstance() {
        assertNotNull(CodiciRitiroGenerator.getInstance());
    }

    @Test
    void generaCodice() {
        String codiceRitiro = CodiciRitiroGenerator.getInstance().generaCodice();
        assertNotNull(codiceRitiro);
        assertEquals(6, codiceRitiro.length());
        for (int i=0; i<6; i++){
            assertTrue(Character.isLetterOrDigit(codiceRitiro.charAt(i)));
        }
    }
}