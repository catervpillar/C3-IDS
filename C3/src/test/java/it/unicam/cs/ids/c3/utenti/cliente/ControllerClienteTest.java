package it.unicam.cs.ids.c3.utenti.cliente;

import com.mysql.cj.xdevapi.Client;
import it.unicam.cs.ids.c3.database.DBManager;
import it.unicam.cs.ids.c3.database.Deserializer;
import it.unicam.cs.ids.c3.database.SerializerAggiunta;
import it.unicam.cs.ids.c3.database.SerializerElimina;
import it.unicam.cs.ids.c3.prodotto.Prodotto;
import it.unicam.cs.ids.c3.ritiro.Ritiro;
import it.unicam.cs.ids.c3.ritiro.TipoConsegna;
import it.unicam.cs.ids.c3.utenti.commerciante.Commerciante;
import it.unicam.cs.ids.c3.utenti.commerciante.ControllerCommerciante;
import it.unicam.cs.ids.c3.utenti.corriere.Corriere;
import org.checkerframework.checker.units.qual.C;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

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
        Cliente cliente = Deserializer.getInstance().deserializzaCliente(resultSet);
        assertNotNull(cliente);
        SerializerElimina.getInstance().eliminaCliente(cliente.getID());
    }

    @Test
    void loginCliente() throws SQLException {
        ControllerCliente.getInstance().creaCliente("username", "password", "email@gmail.com", "nome", "cognome");
        assertTrue(ControllerCliente.getInstance().loginCliente("username", "password"));
        ResultSet resultSet = DBManager.getInstance().executeQuery("select * from cliente where username ='username' AND password='password'");
        assertTrue(resultSet.last());
        Cliente cliente = Deserializer.getInstance().deserializzaCliente(resultSet);
        SerializerElimina.getInstance().eliminaCliente(cliente.getID());
    }



    @Test
    void pubblicaRecensione() {
    }

    @Test
    void modificaRecensione() {
    }

    @Test
    void rimuoviRecensione() {
    }

    @Test
    void getCliente() {
    }

    @Test
    void setCliente() {
    }

    @Test
    void getPromozioni() {
    }

    @Test
    void getRecensioni() {
    }

    @Test
    void getRitiri() {
    }

    @Test
    void modificaCliente() {
    }

    @Test
    void logout() {
    }

    @Test
    void eliminaAccount() {
    }
}