package it.unicam.cs.ids.c3.controller;

import it.unicam.cs.ids.c3.model.Commerciante;
import it.unicam.cs.ids.c3.model.Corriere;
import it.unicam.cs.ids.c3.model.PuntoRitiro;
import it.unicam.cs.ids.c3.model.Ritiro;
import it.unicam.cs.ids.c3.services.DBManager;
import it.unicam.cs.ids.c3.services.Deserializer;
import it.unicam.cs.ids.c3.services.SerializerAggiunta;
import it.unicam.cs.ids.c3.utilities.Controllore;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class ControllerPuntoRitiro {
    private static ControllerPuntoRitiro instance;
    private PuntoRitiro puntoRitiro;

    private ControllerPuntoRitiro() {
    }

    public static ControllerPuntoRitiro getInstance() {
        if (Objects.isNull(instance)) instance = new ControllerPuntoRitiro();
        return instance;
    }

    public PuntoRitiro getPuntoRitiro() {
        return puntoRitiro;
    }

    public void creaPuntoRitiro(String username, String password, String email, String ragioneSociale) throws SQLException {
        Controllore.getInstance().controllaPuntoRitiro(username, password, email, ragioneSociale);
        PuntoRitiro puntoRitiro = new PuntoRitiro(username, password, email, ragioneSociale);
        SerializerAggiunta.getInstance().serializzaPuntoRitiro(puntoRitiro);
    }

    public boolean loginPuntoRitiro(String username, String password) throws SQLException {
        String sql = "select * from punto_ritiro where username = \"" + username + "\" and password = \"" + password + "\";";
        ResultSet resultSet = DBManager.getInstance().executeQuery(sql);

        if (resultSet.last()) {
            resultSet.beforeFirst();
            List<PuntoRitiro> listaPuntiRitiro = Deserializer.getInstance().deserializzaPuntiRitiro(resultSet);
            this.puntoRitiro = listaPuntiRitiro.get(0);
            return true;
        } else return false;
    }
}
