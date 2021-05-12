package it.unicam.cs.ids.c3.utenti.corriere;

import it.unicam.cs.ids.c3.database.DBManager;
import it.unicam.cs.ids.c3.database.Deserializer;
import it.unicam.cs.ids.c3.database.SerializerElimina;
import it.unicam.cs.ids.c3.utenti.cliente.Cliente;
import it.unicam.cs.ids.c3.utenti.cliente.ControllerCliente;
import it.unicam.cs.ids.c3.utilities.GestoreRicerche;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.sql.ResultSet;
import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.*;
class ControllerCorriereTest {

    @BeforeAll
    static void init(){
        DBManager.getInstance().setDBManager("root", "toor");
    }

    @Test
    void getInstance() {
        assertNotNull(ControllerCorriere.getInstance());
    }

    @Test
    void getCorriere() throws SQLException {
        ControllerCorriere.getInstance().creaCorriere("user", "password", "mail@alice.it", "ragSoc");
        ControllerCorriere.getInstance().loginCorriere("user", "password");
        assertEquals("ragSoc", ControllerCorriere.getInstance().getCorriere().getRagioneSociale());
        SerializerElimina.getInstance().eliminaCorriere(ControllerCorriere.getInstance().getCorriere().getID());
    }

    @Test
    void creaCorriere() throws SQLException {
        ControllerCorriere.getInstance().creaCorriere("username", "password", "email@gmail.com", "ragSoc");
        ResultSet resultSet = DBManager.getInstance().executeQuery("select * from corriere where username ='username' AND password='password'");

        CorriereInterface corriere = Deserializer.getInstance().deserializzaCorrieri(resultSet).get(0);
        assertNotNull(corriere);
        SerializerElimina.getInstance().eliminaCorriere(corriere.getID());
    }

    @Test
    void loginCorriere() throws SQLException {
        ControllerCorriere.getInstance().creaCorriere("username", "password", "email@gmail.com", "nome");
        assertTrue(ControllerCorriere.getInstance().loginCorriere("username", "password"));
        ResultSet resultSet = DBManager.getInstance().executeQuery("select * from corriere where username ='username' AND password='password'");
        CorriereInterface corriere = Deserializer.getInstance().deserializzaCorrieri(resultSet).get(0);
        SerializerElimina.getInstance().eliminaCorriere(corriere.getID());
    }

    @Test
    void modificaCorriere() throws SQLException {
        CorriereInterface corriere = new Corriere("id123456", "usern", "pass", "mail@alice.it", "ragSoc");
        ControllerCorriere.getInstance().setCorriere(corriere);
        ControllerCorriere.getInstance().modificaCorriere("usern", "pwd", "mail@alice.it", "ragSoc", "0733666666", "via prova 2", StatoCorriere.DISPONIBILE);
        assertEquals("pwd", ControllerCorriere.getInstance().getCorriere().getPassword());
        assertEquals("0733666666", ControllerCorriere.getInstance().getCorriere().getTelefono());
        assertEquals(StatoCorriere.DISPONIBILE, ControllerCorriere.getInstance().getCorriere().getStato());
        assertEquals("via prova 2", ControllerCorriere.getInstance().getCorriere().getIndirizzo());
    }

    @Test
    void eliminaAccount() throws SQLException {
        ControllerCorriere.getInstance().creaCorriere("user", "password", "mail@alice.it", "nomeee");
        ControllerCorriere.getInstance().loginCorriere("user", "password");
        ControllerCorriere.getInstance().eliminaAccount();
        assertFalse(ControllerCorriere.getInstance().loginCorriere("user", "password"));
        assertNull(ControllerCorriere.getInstance().getCorriere());
    }

    @Test
    void logout() {
        ControllerCorriere.getInstance().logout();
        assertNull(ControllerCorriere.getInstance().getCorriere());
    }
}