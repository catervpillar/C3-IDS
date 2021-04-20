package it.unicam.cs.ids.c3.utenti.puntoRitiro;

import it.unicam.cs.ids.c3.database.DBManager;
import it.unicam.cs.ids.c3.database.Deserializer;
import it.unicam.cs.ids.c3.database.SerializerElimina;
import it.unicam.cs.ids.c3.utenti.cliente.Cliente;
import it.unicam.cs.ids.c3.utenti.cliente.ControllerCliente;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.sql.ResultSet;
import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.*;

class ControllerPuntoRitiroTest {
    @BeforeAll
    static void init(){
        DBManager.getInstance().setDBManager("root", "toor");
    }

    @Test
    void getInstance() {
        assertNotNull(ControllerPuntoRitiro.getInstance());
    }

    @Test
    void getPuntoRitiro() throws SQLException {
        ControllerPuntoRitiro.getInstance().creaPuntoRitiro("user", "password", "mail@alice.it", "ragione sociale");
        ControllerPuntoRitiro.getInstance().loginPuntoRitiro("user", "password");
        assertEquals("ragione sociale", ControllerPuntoRitiro.getInstance().getPuntoRitiro().getRagioneSociale());
        SerializerElimina.getInstance().eliminaPuntoDiRitiro(ControllerPuntoRitiro.getInstance().getPuntoRitiro().getID());
    }

    @Test
    void creaPuntoRitiro() throws SQLException {
        ControllerPuntoRitiro.getInstance().creaPuntoRitiro("username", "password", "email@gmail.com", "ragSoc");
        ResultSet resultSet = DBManager.getInstance().executeQuery("select * from punto_ritiro where username ='username' AND password='password'");
        PuntoRitiro puntoRitiro = Deserializer.getInstance().deserializzaPuntiRitiro(resultSet).get(0);
        assertNotNull(puntoRitiro);
        SerializerElimina.getInstance().eliminaPuntoDiRitiro(puntoRitiro.getID());
    }

    @Test
    void loginPuntoRitiro() throws SQLException {
        ControllerPuntoRitiro.getInstance().creaPuntoRitiro("username", "password", "email@gmail.com", "ragSoc");
        assertTrue(ControllerPuntoRitiro.getInstance().loginPuntoRitiro("username", "password"));
        ResultSet resultSet = DBManager.getInstance().executeQuery("select * from punto_ritiro where username ='username' AND password='password'");
        PuntoRitiro puntoRitiro = Deserializer.getInstance().deserializzaPuntiRitiro(resultSet).get(0);
        SerializerElimina.getInstance().eliminaPuntoDiRitiro(puntoRitiro.getID());
    }

    @Test
    void modificaPuntoRitiro() throws SQLException {
        PuntoRitiro puntoRitiro = new PuntoRitiro("id123456", "usern", "pass", "mail@alice.it", "ragSoc");
        ControllerPuntoRitiro.getInstance().setPuntoRitiro(puntoRitiro);
        ControllerPuntoRitiro.getInstance().modificaPuntoRitiro("usern", "pwd", "mail@alice.it", "ragSoc", "0733633458", "via prova 2");
        assertEquals("pwd", ControllerPuntoRitiro.getInstance().getPuntoRitiro().getPassword());
        assertEquals("0733633458", ControllerPuntoRitiro.getInstance().getPuntoRitiro().getTelefono());
        assertEquals("via prova 2", ControllerPuntoRitiro.getInstance().getPuntoRitiro().getIndirizzo());
    }

    @Test
    void eliminaAccount() throws SQLException {
        ControllerPuntoRitiro.getInstance().creaPuntoRitiro("user", "password", "mail@alice.it", "ragSoc");
        ControllerPuntoRitiro.getInstance().loginPuntoRitiro("user", "password");
        ControllerPuntoRitiro.getInstance().eliminaAccount();
        assertFalse(ControllerPuntoRitiro.getInstance().loginPuntoRitiro("user", "password"));
        assertNull(ControllerPuntoRitiro.getInstance().getPuntoRitiro());
    }

    @Test
    void logout() {
        ControllerPuntoRitiro.getInstance().logout();
        assertNull(ControllerPuntoRitiro.getInstance().getPuntoRitiro());
    }
}