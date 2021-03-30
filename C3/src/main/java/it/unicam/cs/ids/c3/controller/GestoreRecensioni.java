package it.unicam.cs.ids.c3.controller;

import it.unicam.cs.ids.c3.javafx.Utils;
import it.unicam.cs.ids.c3.model.Promozione;
import it.unicam.cs.ids.c3.model.Recensione;
import it.unicam.cs.ids.c3.model.VotoRecensioni;
import it.unicam.cs.ids.c3.services.SerializerAggiunta;
import it.unicam.cs.ids.c3.services.SerializerElimina;
import it.unicam.cs.ids.c3.services.SerializerModifica;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public final class GestoreRecensioni {
    private static GestoreRecensioni instance;

    private GestoreRecensioni() {
    }

    public static GestoreRecensioni getInstance() {
        if (instance == null)
            instance = new GestoreRecensioni();
        return instance;
    }

    public void creaRecensione(String titolo, String testo, String IDCliente, String IDCommerciante, String IDProdotto, VotoRecensioni votoRecensioni) throws SQLException {
        Recensione recensione = new Recensione(titolo, testo, IDCliente, IDCommerciante, IDProdotto, votoRecensioni);
        SerializerAggiunta.getInstance().serializzaRecensione(recensione);
    }

    public void modificaRecensione(String titolo, String testo, VotoRecensioni votoRecensioni, String IDRecensione) throws SQLException {
        Recensione recensione = GestoreRicerche.getInstance().getRecensioni().stream().filter(r -> r.getID().equals(IDRecensione)).collect(Collectors.toList()).get(0);
        recensione.setTesto(testo);
        recensione.setTitolo(titolo);
        recensione.setVotoRecensioni(votoRecensioni);
        SerializerModifica.getInstance().modificaRecensione(recensione);
    }

    public void rimuoviRecensione(String IDRecensione) throws SQLException {
        SerializerElimina.getInstance().eliminaRecensione(IDRecensione);
    }
}
