package it.unicam.cs.ids.c3.utenti.cliente;
import it.unicam.cs.ids.c3.database.DBManager;
import it.unicam.cs.ids.c3.database.Deserializer;
import it.unicam.cs.ids.c3.database.SerializerElimina;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import java.sql.ResultSet;
import java.sql.SQLException;
import static org.junit.jupiter.api.Assertions.*;

class ControllerClienteTest {

    @BeforeAll
    static void init(){
        DBManager.getInstance().setDBManager("root", "toor");
    }
    @Test
    void getInstance() {
        assertNotNull(ControllerCliente.getInstance());
    }

    @Test
    void creaCliente() throws SQLException {
        ControllerCliente.getInstance().creaCliente("username", "password", "email@gmail.com", "nome", "cognome");
        ResultSet resultSet = DBManager.getInstance().executeQuery("select * from cliente where username ='username' AND password='password'");
        assertTrue(resultSet.last());
        ClienteInterface cliente = Deserializer.getInstance().deserializzaCliente(resultSet);
        assertNotNull(cliente);
        SerializerElimina.getInstance().eliminaCliente(cliente.getID());
    }

    @Test
    void loginCliente() throws SQLException {
        ControllerCliente.getInstance().creaCliente("username", "password", "email@gmail.com", "nome", "cognome");
        assertTrue(ControllerCliente.getInstance().loginCliente("username", "password"));
        ResultSet resultSet = DBManager.getInstance().executeQuery("select * from cliente where username ='username' AND password='password'");
        assertTrue(resultSet.last());
        ClienteInterface cliente = Deserializer.getInstance().deserializzaCliente(resultSet);
        SerializerElimina.getInstance().eliminaCliente(cliente.getID());
    }

    @Test
    void getCliente() throws SQLException {
        ControllerCliente.getInstance().creaCliente("user", "password", "mail@alice.it", "nomeee", "cognome");
        ControllerCliente.getInstance().loginCliente("user", "password");
        assertEquals("nomeee", ControllerCliente.getInstance().getCliente().getNome());
        SerializerElimina.getInstance().eliminaCliente(ControllerCliente.getInstance().getCliente().getID());
    }

    @Test
    void setCliente() {
        ClienteInterface cliente = new Cliente("id123456", "usern", "pass", "mail@alice.it", "name", "surname");
        ControllerCliente.getInstance().setCliente(cliente);
        assertEquals("usern", ControllerCliente.getInstance().getCliente().getUsername());
    }

    @Test
    void modificaCliente() throws SQLException {
        ClienteInterface cliente = new Cliente("id123456", "usern", "pass", "mail@alice.it", "name", "surname");
        ControllerCliente.getInstance().setCliente(cliente);
        ControllerCliente.getInstance().modificaCliente("usern", "pwd", "mail@alice.it", "name", "surname", "0733633458", "via prova 2");
        assertEquals("pwd", ControllerCliente.getInstance().getCliente().getPassword());
        assertEquals("0733633458", ControllerCliente.getInstance().getCliente().getTelefono());
        assertEquals("via prova 2", ControllerCliente.getInstance().getCliente().getIndirizzo());
    }

    @Test
    void logout() {
        ControllerCliente.getInstance().logout();
        assertNull(ControllerCliente.getInstance().getCliente());
    }

    @Test
    void eliminaAccount() throws SQLException {
        ControllerCliente.getInstance().creaCliente("user", "password", "mail@alice.it", "nomeee", "cognome");
        ControllerCliente.getInstance().loginCliente("user", "password");
        ControllerCliente.getInstance().eliminaAccount();
        assertFalse(ControllerCliente.getInstance().loginCliente("user", "password"));
        assertNull(ControllerCliente.getInstance().getCliente());
    }
}