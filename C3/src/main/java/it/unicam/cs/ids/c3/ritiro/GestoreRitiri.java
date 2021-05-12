package it.unicam.cs.ids.c3.ritiro;

import it.unicam.cs.ids.c3.database.SerializerAggiunta;
import it.unicam.cs.ids.c3.database.SerializerElimina;
import it.unicam.cs.ids.c3.database.SerializerModifica;

import java.sql.SQLException;
import java.util.List;

public final class GestoreRitiri {
    private static GestoreRitiri instance;

    private GestoreRitiri() {
    }

    public static GestoreRitiri getInstance() {
        if (instance == null)
            instance = new GestoreRitiri();
        return instance;
    }

    public void creaRitiro(String IDCommerciante, String IDCliente, String IDCorriere, String destinazione, TipoConsegna tipoConsegna, List<String> listaIDProdotti) throws SQLException {
        RitiroInterface ritiro = new Ritiro(IDCommerciante, IDCliente, IDCorriere, destinazione, tipoConsegna);
        ritiro.getListaIDProdotti().addAll(listaIDProdotti);
        SerializerAggiunta.getInstance().serializzaRitiro(ritiro);
    }

    public void modificaRitiro(String ID, String IDCommerciante, String IDCliente, String IDCorriere, String destinazione, boolean ritirato, TipoConsegna tipoConsegna, StatoTracking stato) throws SQLException {
        RitiroInterface ritiro = new Ritiro(ID, IDCommerciante, IDCliente, IDCorriere, destinazione, tipoConsegna);
        ritiro.setStatoTracking(stato);
        ritiro.setRitirato(ritirato);
        SerializerModifica.getInstance().modificaRitiro(ritiro);
    }

    public void eliminaRitiro(String ID) throws SQLException {
        SerializerElimina.getInstance().eliminaRitiro(ID);
    }
}
