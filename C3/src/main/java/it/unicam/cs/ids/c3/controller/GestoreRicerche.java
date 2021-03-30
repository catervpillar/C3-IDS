package it.unicam.cs.ids.c3.controller;

import it.unicam.cs.ids.c3.model.*;
import it.unicam.cs.ids.c3.services.DBManager;
import it.unicam.cs.ids.c3.services.Deserializer;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public final class GestoreRicerche {
    private static GestoreRicerche instance;

    private final List<Commerciante> commercianti = new ArrayList<>();
    private final List<PuntoRitiro> puntiDiRitiro = new ArrayList<>();
    private final List<Corriere> corrieri = new ArrayList<>();
    private final List<Prodotto> prodotti = new ArrayList<>();
    private final List<Promozione> promozioni = new ArrayList<>();
    private final List<Recensione> recensioni = new ArrayList<>();
    private final List<Ritiro> ritiri = new ArrayList<>();


    private GestoreRicerche() {
    }

    public static GestoreRicerche getInstance() {
        if (instance == null)
            instance = new GestoreRicerche();
        return instance;
    }

    public List<Commerciante> getCommercianti() {
        return commercianti;
    }

    public List<Recensione> getRecensioni() {
        return recensioni;
    }

    public List<PuntoRitiro> getPuntiDiRitiro() {
        return puntiDiRitiro;
    }

    public List<Corriere> getCorrieri() {
        return corrieri;
    }

    public List<Prodotto> getProdotti() {
        return prodotti;
    }

    public List<Commerciante> cercaCommerciante(String ragioneSociale) {
        commercianti.clear();
        try {
            ResultSet resultSet;
            if (!Objects.isNull(ragioneSociale)) {
                resultSet = DBManager.getInstance().executeQuery("select * from commerciante where ragioneSociale = \"" + ragioneSociale + "\"");
            } else
                resultSet = DBManager.getInstance().executeQuery("select * from commerciante");
            commercianti.addAll(Deserializer.getInstance().deserializzaCommercianti(resultSet));
        } catch (SQLException e) {
            System.out.println("Errore nella ricerca: nessun risultato trovato");
        }
        return commercianti;
    }

    public List<Corriere> cercaCorriere(String ragioneSociale) {
        corrieri.clear();
        try {
            ResultSet resultSet;
            if (!Objects.isNull(ragioneSociale)) {
                resultSet = DBManager.getInstance().executeQuery("select * from corriere where ragioneSociale = \"" + ragioneSociale + "\"");
            } else
                resultSet = DBManager.getInstance().executeQuery("select * from corriere where stato = \"DISPONIBILE\";");
            corrieri.addAll(Deserializer.getInstance().deserializzaCorrieri(resultSet));
        } catch (SQLException e) {
            System.out.println("Errore nella ricerca: nessun risultato trovato");
        }
        return corrieri;
    }

    public List<PuntoRitiro> cercaPuntoRitiro(String ragioneSociale) {
        puntiDiRitiro.clear();
        try {
            ResultSet resultSet;
            if (!Objects.isNull(ragioneSociale)) {
                resultSet = DBManager.getInstance().executeQuery("select * from punto_ritiro where ragioneSociale = \"" + ragioneSociale + "\"");
            } else
                resultSet = DBManager.getInstance().executeQuery("select * from punto_ritiro");
            puntiDiRitiro.addAll(Deserializer.getInstance().deserializzaPuntiRitiro(resultSet));
        } catch (SQLException e) {
            System.out.println("Errore nella ricerca: nessun risultato trovato");
        }
        return puntiDiRitiro;
    }

    public List<Prodotto> cercaProdotto(String nome, String IDCommerciante) {
        prodotti.clear();
        try {
            if (!Objects.isNull(nome) && !nome.isBlank()) {
                ResultSet resultSet = DBManager.getInstance().executeQuery("select * from prodotto where nome = \"" + nome + "\"");
                prodotti.addAll(Deserializer.getInstance().deserializzaProdotti(resultSet));
            } else if (!Objects.isNull(IDCommerciante)) {
                ResultSet resultSet = DBManager.getInstance().executeQuery("select * from prodotto where commerciante_ID = \"" + IDCommerciante + "\"");
                prodotti.addAll(Deserializer.getInstance().deserializzaProdotti(resultSet));
            } else {
                ResultSet resultSet = DBManager.getInstance().executeQuery("select * from prodotto");
                prodotti.addAll(Deserializer.getInstance().deserializzaProdotti(resultSet));
            }
        } catch (SQLException e) {
            System.out.println("Errore nella ricerca: nessun risultato trovato");
        }
        return prodotti;
    }

    public List<Promozione> getPromozioni(String IDCommerciante) {
        promozioni.clear();
        try {
            if (!Objects.isNull(IDCommerciante)) {
                ResultSet resultSet = DBManager.getInstance().executeQuery("select * from promozione where commerciante_ID = \"" + IDCommerciante + "\";");
                promozioni.addAll(Deserializer.getInstance().deserializzaPromozioni(resultSet));
            } else {
                ResultSet resultSet = DBManager.getInstance().executeQuery("select * from promozione");
                promozioni.addAll(Deserializer.getInstance().deserializzaPromozioni(resultSet));
            }
        } catch (SQLException e) {
            System.out.println("Errore nella ricerca: nessun risultato trovato");
        }

        return promozioni;
    }

    public List<Recensione> getRecensioni(String query) {
        recensioni.clear();
        try {
            ResultSet resultSet = DBManager.getInstance().executeQuery(query);
            recensioni.addAll(Deserializer.getInstance().deserializzaRecensioni(resultSet));
        } catch (SQLException e) {
            System.out.println("Errore nella ricerca: nessun risultato trovato");
        }

        return recensioni;
    }

    public List<Ritiro> getRitiri(String IDCommerciante, String IDCliente, String IDCorriere, String destinazione) {
        ritiri.clear();
        try {
            if (!Objects.isNull(IDCommerciante)) {
                ResultSet resultSet = DBManager.getInstance().executeQuery("select * from ritiro where commerciante_ID = \"" + IDCommerciante + "\";");
                ritiri.addAll(Deserializer.getInstance().deserializzaRitiri(resultSet));
            } else if (!Objects.isNull(IDCliente)) {
                ResultSet resultSet = DBManager.getInstance().executeQuery("select * from ritiro where cliente_ID = \"" + IDCliente + "\";");
                ritiri.addAll(Deserializer.getInstance().deserializzaRitiri(resultSet));
            } else if (!Objects.isNull(IDCorriere)) {
                ResultSet resultSet = DBManager.getInstance().executeQuery("select * from ritiro where corriere_ID = \"" + IDCorriere + "\";");
                ritiri.addAll(Deserializer.getInstance().deserializzaRitiri(resultSet));
            } else if (!Objects.isNull(destinazione)) {
                ResultSet resultSet = DBManager.getInstance().executeQuery("select * from ritiro where destinazione = \"" + destinazione + "\";");
                ritiri.addAll(Deserializer.getInstance().deserializzaRitiri(resultSet));
            }
        } catch (SQLException e) {
            System.out.println("Errore nella ricerca: nessun risultato trovato");
        }
        return ritiri;
    }

    public void reset() {
        commercianti.clear();
        puntiDiRitiro.clear();
        corrieri.clear();
        prodotti.clear();
        promozioni.clear();
        recensioni.clear();
        ritiri.clear();
    }
}
