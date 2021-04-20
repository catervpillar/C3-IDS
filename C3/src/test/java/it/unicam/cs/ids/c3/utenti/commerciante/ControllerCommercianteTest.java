package it.unicam.cs.ids.c3.utenti.commerciante;

import it.unicam.cs.ids.c3.database.DBManager;
import it.unicam.cs.ids.c3.database.Deserializer;
import it.unicam.cs.ids.c3.database.SerializerElimina;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.sql.ResultSet;
import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.*;

class ControllerCommercianteTest {

    @BeforeAll
    static void init(){
        DBManager.getInstance().setDBManager("root", "toor");
    }

    @Test
    void getInstance() {
        assertNotNull(ControllerCommerciante.getInstance());
    }

    @Test
    void getCommerciante() throws SQLException {
        ControllerCommerciante.getInstance().creaCommerciante("user2", "password2", "mail@gmail.it", "tommaso e leonardo");
        ControllerCommerciante.getInstance().loginCommerciante("user2", "password2");
        assertEquals("tommaso e leonardo", ControllerCommerciante.getInstance().getCommerciante().getRagioneSociale());
        SerializerElimina.getInstance().eliminaCommerciante(ControllerCommerciante.getInstance().getCommerciante().getID());
    }

    @Test
    void setCommerciante() {
        Commerciante commerciante = new Commerciante("id123456", "usern", "pass", "mail@alice.it", "ragSoc");
        ControllerCommerciante.getInstance().setCommerciante(commerciante);
        assertEquals("usern", ControllerCommerciante.getInstance().getCommerciante().getUsername());
    }

    @Test
    void creaCommerciante() throws SQLException {
        ControllerCommerciante.getInstance().creaCommerciante("username", "password", "email@gmail.com", "nome");
        ResultSet resultSet = DBManager.getInstance().executeQuery("select * from commerciante where username ='username' AND password='password'");
        Commerciante commerciante = Deserializer.getInstance().deserializzaCommercianti(resultSet).get(0);
        assertNotNull(commerciante);
        SerializerElimina.getInstance().eliminaCommerciante(commerciante.getID());
    }

    @Test
    void modificaCommerciante() throws SQLException {
        Commerciante commerciante = new Commerciante("id123456", "usern", "pass", "mail@alice.it", "ragSoc");
        ControllerCommerciante.getInstance().setCommerciante(commerciante);
        ControllerCommerciante.getInstance().modificaCommerciante("usern", "pwd", "mail@alice.it", "ragSoc", "1111111111", "via roma 5");
        assertEquals("pwd", ControllerCommerciante.getInstance().getCommerciante().getPassword());
        assertEquals("via roma 5", ControllerCommerciante.getInstance().getCommerciante().getIndirizzo());
    }

    @Test
    void eliminaAccount() throws SQLException {
        ControllerCommerciante.getInstance().creaCommerciante("user", "password", "mail@alice.it", "ragSoc");
        ControllerCommerciante.getInstance().loginCommerciante("user", "password");
        ControllerCommerciante.getInstance().eliminaAccount();
        assertFalse(ControllerCommerciante.getInstance().loginCommerciante("user", "password"));
        assertNull(ControllerCommerciante.getInstance().getCommerciante());
    }

    @Test
    void logout() {
        ControllerCommerciante.getInstance().logout();
        assertNull(ControllerCommerciante.getInstance().getCommerciante());
    }

    @Test
    void loginCommerciante() throws SQLException {
        ControllerCommerciante.getInstance().creaCommerciante("username", "password", "email@gmail.com", "ragSoc");
        assertTrue(ControllerCommerciante.getInstance().loginCommerciante("username", "password"));
        ResultSet resultSet = DBManager.getInstance().executeQuery("select * from commerciante where username ='username' AND password='password'");
        Commerciante commerciante = Deserializer.getInstance().deserializzaCommercianti(resultSet).get(0);
        SerializerElimina.getInstance().eliminaCommerciante(commerciante.getID());
    }
}