package it.unicam.cs.ids.c3.utenti.puntoRitiro;

import it.unicam.cs.ids.c3.utilities.GestoreRicerche;
import it.unicam.cs.ids.c3.ritiro.GestoreRitiri;
import it.unicam.cs.ids.c3.ritiro.Ritiro;
import it.unicam.cs.ids.c3.ritiro.StatoTracking;
import it.unicam.cs.ids.c3.ritiro.TipoConsegna;
import it.unicam.cs.ids.c3.database.*;
import it.unicam.cs.ids.c3.utilities.Controllore;

import java.sql.ResultSet;
import java.sql.SQLException;
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

    public void modificaPuntoRitiro(String username, String password, String email, String ragioneSociale, String telefono, String indirizzo) throws SQLException {
        Controllore.getInstance().controllaPuntoRitiro(username, password, email, ragioneSociale);
        PuntoRitiro puntoRitiro = new PuntoRitiro(this.puntoRitiro.getID(), username, password, email, ragioneSociale);

        if (!indirizzo.isBlank()) {
            Controllore.getInstance().controllaIndirizzo(indirizzo);
            puntoRitiro.setIndirizzo(indirizzo);
        }
        if (!telefono.isBlank()) {
            Controllore.getInstance().controllaNumero(telefono, 10);
            puntoRitiro.setTelefono(telefono);
        }
        this.puntoRitiro = puntoRitiro;
        SerializerModifica.getInstance().modificaPuntoRitiro(puntoRitiro);
    }

    public void eliminaAccount() throws SQLException {
        SerializerElimina.getInstance().eliminaPuntoDiRitiro(this.puntoRitiro.getID());
        this.puntoRitiro = null;
        GestoreRicerche.getInstance().reset();
    }

    public void logout() {
        GestoreRicerche.getInstance().reset();
        this.puntoRitiro = null;
    }

    public List<Ritiro> getRitiri() {
        return GestoreRicerche.getInstance().getRitiri(null, null, null, this.puntoRitiro.getIndirizzo());
    }

    public void contrassegna(String ID, String IDCommerciante, String IDCliente, String IDCorriere, boolean ritirato, TipoConsegna tipoConsegna, StatoTracking stato) throws SQLException {
        GestoreRitiri.getInstance().modificaRitiro(ID, IDCommerciante, IDCliente, IDCorriere, this.puntoRitiro.getIndirizzo(), ritirato, tipoConsegna, stato);
    }
}
