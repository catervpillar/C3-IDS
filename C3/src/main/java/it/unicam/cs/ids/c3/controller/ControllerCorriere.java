package it.unicam.cs.ids.c3.controller;

import it.unicam.cs.ids.c3.model.*;
import it.unicam.cs.ids.c3.services.*;
import it.unicam.cs.ids.c3.utilities.Controllore;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Objects;

public class ControllerCorriere {
    private static ControllerCorriere instance;
    private Corriere corriere;

    private ControllerCorriere() {
    }

    public static ControllerCorriere getInstance() {
        if (Objects.isNull(instance)) instance = new ControllerCorriere();
        return instance;
    }

    public Corriere getCorriere() {
        return corriere;
    }

    public void creaCorriere(String username, String password, String email, String ragioneSociale) throws SQLException {
        Controllore.getInstance().controllaCorriere(username, password, email, ragioneSociale);
        Corriere corriere = new Corriere(username, password, email, ragioneSociale);
        SerializerAggiunta.getInstance().serializzaCorriere(corriere);
    }

    public boolean loginCorriere(String username, String password) throws SQLException {
        String sql = "select * from corriere where username = \"" + username + "\" and password = \"" + password + "\";";
        ResultSet resultSet = DBManager.getInstance().executeQuery(sql);

        if (resultSet.last()) {
            resultSet.beforeFirst();
            List<Corriere> listaCorrieri = Deserializer.getInstance().deserializzaCorrieri(resultSet);
            this.corriere = listaCorrieri.get(0);
            return true;
        } else return false;
    }

    public void modificaCorriere(String username, String password, String email, String ragioneSociale, String telefono, String indirizzo, StatoCorriere stato) throws SQLException {
        Controllore.getInstance().controllaCorriere(username, password, email, ragioneSociale);
        Corriere corriere = new Corriere(this.corriere.getID(), username, password, email, ragioneSociale);

        if (!indirizzo.isBlank()) {
            Controllore.getInstance().controllaIndirizzo(indirizzo);
            corriere.setIndirizzo(indirizzo);
        }
        if (!telefono.isBlank()) {
            Controllore.getInstance().controllaNumero(telefono, 10);
            corriere.setTelefono(telefono);
        }
        if(!Objects.isNull(stato))
            corriere.setStato(stato);
        this.corriere = corriere;
        SerializerModifica.getInstance().modificaCorriere(corriere);
    }

    public void eliminaAccount() throws SQLException {
        SerializerElimina.getInstance().eliminaCorriere(this.corriere.getID());
        this.corriere = null;
        GestoreRicerche.getInstance().reset();
    }

    public void logout() {
        GestoreRicerche.getInstance().reset();
        this.corriere = null;
    }

    public List<Ritiro> getRitiri() {
        return null;
    }
}
