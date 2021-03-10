package it.unicam.cs.ids.c3.controller;

import it.unicam.cs.ids.c3.model.*;
import it.unicam.cs.ids.c3.services.SerializerAggiunta;

import java.sql.SQLException;
import java.util.ArrayList;
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
        Ritiro ritiro = new Ritiro(IDCommerciante, IDCliente, IDCorriere, destinazione, tipoConsegna);
        ritiro.getListaIDProdotti().addAll(listaIDProdotti);
        SerializerAggiunta.getInstance().serializzaRitiro(ritiro);
    }

    public void modificaRitiro() {

    }

    public void eliminaRitiro() {

    }
}
