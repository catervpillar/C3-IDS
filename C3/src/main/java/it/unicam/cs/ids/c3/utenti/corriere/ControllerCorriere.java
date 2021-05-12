package it.unicam.cs.ids.c3.utenti.corriere;

import it.unicam.cs.ids.c3.ritiro.*;
import it.unicam.cs.ids.c3.utilities.GestoreRicerche;
import it.unicam.cs.ids.c3.database.*;
import it.unicam.cs.ids.c3.utilities.Controllore;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Objects;

public class ControllerCorriere {
    private static ControllerCorriere instance;
    private CorriereInterface corriere;

    private ControllerCorriere() {
    }

    public static ControllerCorriere getInstance() {
        if (Objects.isNull(instance)) instance = new ControllerCorriere();
        return instance;
    }

    public CorriereInterface getCorriere() {
        return corriere;
    }

    public void setCorriere(CorriereInterface corriere) {
        this.corriere = corriere;
    }

    public void creaCorriere(String username, String password, String email, String ragioneSociale) throws SQLException {
        Controllore.getInstance().controllaCorriere(username, password, email, ragioneSociale);
        CorriereInterface corriere = new Corriere(username, password, email, ragioneSociale);
        SerializerAggiunta.getInstance().serializzaCorriere(corriere);
    }

    public boolean loginCorriere(String username, String password) throws SQLException {
        String sql = "select * from corriere where username = \"" + username + "\" and password = \"" + password + "\";";
        ResultSet resultSet = DBManager.getInstance().executeQuery(sql);

        if (resultSet.last()) {
            resultSet.beforeFirst();
            List<CorriereInterface> listaCorrieri = Deserializer.getInstance().deserializzaCorrieri(resultSet);
            this.corriere = listaCorrieri.get(0);
            return true;
        } else return false;
    }

    public void modificaCorriere(String username, String password, String email, String ragioneSociale, String telefono, String indirizzo, StatoCorriere stato) throws SQLException {
        Controllore.getInstance().controllaCorriere(username, password, email, ragioneSociale);
        CorriereInterface corriere = new Corriere(this.corriere.getID(), username, password, email, ragioneSociale);

        if (!indirizzo.isBlank()) {
            Controllore.getInstance().controllaIndirizzo(indirizzo);
            corriere.setIndirizzo(indirizzo);
        }
        if (!telefono.isBlank()) {
            Controllore.getInstance().controllaNumero(telefono, 10);
            corriere.setTelefono(telefono);
        }
        if (!Objects.isNull(stato))
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

    public List<RitiroInterface> getRitiri() {
        return GestoreRicerche.getInstance().getRitiri(null, null, this.corriere.getID(), null);
    }

    public void aggiornaTracking(String ID, String IDCommerciante, String IDCliente, String destinazione, boolean ritirato, TipoConsegna tipoConsegna, StatoTracking stato) throws SQLException {
        GestoreRitiri.getInstance().modificaRitiro(ID, IDCommerciante, IDCliente, this.corriere.getID(), destinazione, ritirato, tipoConsegna, stato);
    }
}
