package it.unicam.cs.ids.c3.controller;

import it.unicam.cs.ids.c3.model.Cliente;
import it.unicam.cs.ids.c3.model.Corriere;
import it.unicam.cs.ids.c3.model.StatoTracking;
import it.unicam.cs.ids.c3.services.DBManager;
import it.unicam.cs.ids.c3.services.Deserializer;
import it.unicam.cs.ids.c3.services.SerializerAggiunta;
import it.unicam.cs.ids.c3.utilities.Controllore;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Objects;

public class ControllerCorriere{
    private static ControllerCorriere instance;
    private Corriere corriere;

    private ControllerCorriere() {
    }

    public static ControllerCorriere getInstance() {
        if (Objects.isNull(instance)) instance = new ControllerCorriere();
        return instance;
    }

    public void creaCorriere(String username, String password, String email, String ragioneSociale) throws SQLException {
        Controllore.getInstance().controllaCorriere(username, password, email, ragioneSociale);
        Corriere corriere = new Corriere(username, password, email, ragioneSociale);
        SerializerAggiunta.getInstance().serializzaCorriere(corriere);
    }

    public boolean loginCorriere(String username, String password) throws SQLException {
        String sql = "select * from corriere where username = \"" + username + "\" and password = \"" + password + "\";";
        ResultSet resultSet = DBManager.getInstance().executeQuery(sql);
        int i=0;
        while(resultSet.next()){
            i++;
        }
        if (i==1){
            this.corriere = Deserializer.getInstance().deserializzaCorrieri(resultSet).get(0);
            return true;
        }
        else return false;
    }
//    public void aggiornaTracking(Tracking tracking, StatoTracking statoTracking) {
//        tracking.setStato(statoTracking);

//        if (statoTracking.equals(StatoTracking.CONSEGNATO))
//            for (int i = 0; i < AppList.getInstance().getRitiri().size(); i++) {
//                if (AppList.getInstance().getRitiri().get(i).getID().equals(tracking.getIDRitiro())) {
//                    if (AppList.getInstance().getRitiri().get(i).getTipoConsegna().equals(TipoConsegna.CONSEGNA_A_DOMICILIO))
//                        AppList.getInstance().getRitiri().get(i).setRitirato(true);
//                }
//            }
//    }


}
