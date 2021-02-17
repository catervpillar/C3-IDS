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


    List<Commerciante> cercaCommerciante(String ragioneSociale) {
        if (!Objects.isNull(ragioneSociale)) {
            try {
                ResultSet resultSet = DBManager.getInstance().executeQuery("select * from commerciante where ragioneSociale = \"" + ragioneSociale + "\"");
                commercianti.addAll(Deserializer.getInstance().deserializzaCommercianti(resultSet));
            } catch (SQLException e) {
                System.out.println("Errore nella ricerca: nessun risultato trovato");
            }
        } else {
            try {
                ResultSet resultSet = DBManager.getInstance().executeQuery("select * from commerciante");
                commercianti.addAll(Deserializer.getInstance().deserializzaCommercianti(resultSet));
            } catch (SQLException e) {
                System.out.println("Errore nella ricerca: nessun risultato trovato");
            }
        }
        return commercianti;
    }

    List<Corriere> cercaCorriere(String ragioneSociale) {
        if (!Objects.isNull(ragioneSociale)) {
            try {
                ResultSet resultSet = DBManager.getInstance().executeQuery("select * from corriere where ragioneSociale = \"" + ragioneSociale + "\"");
                corrieri.addAll(Deserializer.getInstance().deserializzaCorrieri(resultSet));
            } catch (SQLException e) {
                System.out.println("Errore nella ricerca: nessun risultato trovato");
            }
        } else {
            try {
                ResultSet resultSet = DBManager.getInstance().executeQuery("select * from corriere");
                corrieri.addAll(Deserializer.getInstance().deserializzaCorrieri(resultSet));
            } catch (SQLException e) {
                System.out.println("Errore nella ricerca: nessun risultato trovato");
            }
        }
        return corrieri;
    }

    List<PuntoRitiro> cercaPuntoRitiro(String ragioneSociale) {
        if (!Objects.isNull(ragioneSociale)) {
            try {
                ResultSet resultSet = DBManager.getInstance().executeQuery("select * from punto_ritiro where ragioneSociale = \"" + ragioneSociale + "\"");
                puntiDiRitiro.addAll(Deserializer.getInstance().deserializzaPuntiRitiro(resultSet));
            } catch (SQLException e) {
                System.out.println("Errore nella ricerca: nessun risultato trovato");
            }
        } else {
            try {
                ResultSet resultSet = DBManager.getInstance().executeQuery("select * from punto_ritiro");
                puntiDiRitiro.addAll(Deserializer.getInstance().deserializzaPuntiRitiro(resultSet));
            } catch (SQLException e) {
                System.out.println("Errore nella ricerca: nessun risultato trovato");
            }
        }
        return puntiDiRitiro;
    }

    List<Prodotto> cercaProdotto(String nome) {
        if (!Objects.isNull(nome)) {
            try {
                ResultSet resultSet = DBManager.getInstance().executeQuery("select * from prodotto where nome = \"" + nome + "\"");
                prodotti.addAll(Deserializer.getInstance().deserializzaProdotti(resultSet));
            } catch (SQLException e) {
                System.out.println("Errore nella ricerca: nessun risultato trovato");
            }
        } else {
            try {
                DBManager.getInstance().executeQuery("select * from prodotto");
            } catch (SQLException e) {
                System.out.println("Errore nella ricerca: nessun risultato trovato");
            }
        }
        return prodotti;
    }

    List<Promozione> getPromozioni() {
        try {
            ResultSet resultSet = DBManager.getInstance().executeQuery("select * from promozione");
            promozioni.addAll(Deserializer.getInstance().deserializzaPromozioni(resultSet));
        } catch (SQLException e) {
            System.out.println("Errore nella ricerca: nessun risultato trovato");
        }

        return promozioni;
    }

    List<Recensione> getRecensionii() {
        try {
            ResultSet resultSet = DBManager.getInstance().executeQuery("select * from recensione");
            recensioni.addAll(Deserializer.getInstance().deserializzaRecensioni(resultSet));
        } catch (SQLException e) {
            System.out.println("Errore nella ricerca: nessun risultato trovato");
        }

        return recensioni;
    }

    List<Ritiro> getRitiri() {
        try {
            ResultSet resultSet = DBManager.getInstance().executeQuery("select * from ritiro");
            ritiri.addAll(Deserializer.getInstance().deserializzaRitiri(resultSet));
        } catch (SQLException e) {
            System.out.println("Errore nella ricerca: nessun risultato trovato");
        }

        return ritiri;
    }


}
