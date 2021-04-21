package it.unicam.cs.ids.c3.utilities;

import it.unicam.cs.ids.c3.utenti.cliente.Cliente;
import it.unicam.cs.ids.c3.utenti.corriere.ControllerCorriere;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import org.checkerframework.checker.units.qual.C;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ControlloreTest {

    @Test
    void getInstance() {
        assertNotNull(Controllore.getInstance());
    }

    @Test
    void controllaStringa() {
        String string = " ";
        assertThrows(IllegalArgumentException.class, ()->Controllore.getInstance().controllaStringa(string, "errore"));
    }

    @Test
    void controllaUsername() {
        String username = "///";
        assertThrows(IllegalArgumentException.class, ()->Controllore.getInstance().controllaUsername(username));
    }

    @Test
    void controllaEmail() {
        String email = "leonardo.it";
        String email2 = "@.it";
        String email3 = "leonardodsa@@.it";
        assertThrows(IllegalArgumentException.class, ()->Controllore.getInstance().controllaEmail(email));
        assertThrows(IllegalArgumentException.class, ()->Controllore.getInstance().controllaEmail(email2));
        assertThrows(IllegalArgumentException.class, ()->Controllore.getInstance().controllaEmail(email3));
    }

    @Test
    void controllaNumero() {
        String numero = "n123";
        assertThrows(IllegalArgumentException.class, ()->Controllore.getInstance().controllaNumero(numero, 10));
    }

    @Test
    void controllaNome() {
        String nome = " ";
        assertThrows(IllegalArgumentException.class, ()->Controllore.getInstance().controllaNome(nome, "errore"));
    }

}