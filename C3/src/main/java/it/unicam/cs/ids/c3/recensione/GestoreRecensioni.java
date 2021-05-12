package it.unicam.cs.ids.c3.recensione;

import it.unicam.cs.ids.c3.utilities.GestoreRicerche;
import it.unicam.cs.ids.c3.database.SerializerAggiunta;
import it.unicam.cs.ids.c3.database.SerializerElimina;
import it.unicam.cs.ids.c3.database.SerializerModifica;

import java.sql.SQLException;
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
        RecensioneInterface recensione = new Recensione(titolo, testo, IDCliente, IDCommerciante, IDProdotto, votoRecensioni);
        SerializerAggiunta.getInstance().serializzaRecensione(recensione);
    }

    public void modificaRecensione(String titolo, String testo, VotoRecensioni votoRecensioni, String IDRecensione) throws SQLException {
        RecensioneInterface recensione = GestoreRicerche.getInstance().getRecensioni().stream().filter(r -> r.getID().equals(IDRecensione)).collect(Collectors.toList()).get(0);
        recensione.setTesto(testo);
        recensione.setTitolo(titolo);
        recensione.setVotoRecensioni(votoRecensioni);
        SerializerModifica.getInstance().modificaRecensione(recensione);
    }

    public void rimuoviRecensione(String IDRecensione) throws SQLException {
        SerializerElimina.getInstance().eliminaRecensione(IDRecensione);
    }
}
