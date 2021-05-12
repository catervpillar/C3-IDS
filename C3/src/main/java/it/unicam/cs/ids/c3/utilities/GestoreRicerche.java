package it.unicam.cs.ids.c3.utilities;

import it.unicam.cs.ids.c3.prodotto.Prodotto;
import it.unicam.cs.ids.c3.prodotto.ProdottoInterface;
import it.unicam.cs.ids.c3.promozione.Promozione;
import it.unicam.cs.ids.c3.promozione.PromozioneInterface;
import it.unicam.cs.ids.c3.recensione.Recensione;
import it.unicam.cs.ids.c3.recensione.RecensioneInterface;
import it.unicam.cs.ids.c3.ritiro.Ritiro;
import it.unicam.cs.ids.c3.ritiro.RitiroInterface;
import it.unicam.cs.ids.c3.utenti.commerciante.Commerciante;
import it.unicam.cs.ids.c3.utenti.commerciante.CommercianteInterface;
import it.unicam.cs.ids.c3.utenti.corriere.Corriere;
import it.unicam.cs.ids.c3.utenti.corriere.CorriereInterface;
import it.unicam.cs.ids.c3.utenti.puntoRitiro.PuntoRitiro;
import it.unicam.cs.ids.c3.database.DBManager;
import it.unicam.cs.ids.c3.database.Deserializer;
import it.unicam.cs.ids.c3.utenti.puntoRitiro.PuntoRitiroInterface;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public final class GestoreRicerche {
    private static GestoreRicerche instance;

    private final List<CommercianteInterface> commercianti = new ArrayList<>();
    private final List<PuntoRitiroInterface> puntiDiRitiro = new ArrayList<>();
    private final List<CorriereInterface> corrieri = new ArrayList<>();
    private final List<ProdottoInterface> prodotti = new ArrayList<>();
    private final List<PromozioneInterface> promozioni = new ArrayList<>();
    private final List<RecensioneInterface> recensioni = new ArrayList<>();
    private final List<RitiroInterface> ritiri = new ArrayList<>();


    private GestoreRicerche() {
    }

    public static GestoreRicerche getInstance() {
        if (instance == null)
            instance = new GestoreRicerche();
        return instance;
    }

    public List<CommercianteInterface> getCommercianti() {
        return commercianti;
    }

    public List<RecensioneInterface> getRecensioni() {
        return recensioni;
    }

    public List<PuntoRitiroInterface> getPuntiDiRitiro() {
        return puntiDiRitiro;
    }

    public List<CorriereInterface> getCorrieri() {
        return corrieri;
    }

    public List<ProdottoInterface> getProdotti() {
        return prodotti;
    }

    public List<CommercianteInterface> cercaCommerciante(String ragioneSociale) {
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

    public List<CorriereInterface> cercaCorriere(String ragioneSociale) {
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

    public List<PuntoRitiroInterface> cercaPuntoRitiro(String ragioneSociale) {
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

    public List<ProdottoInterface> cercaProdotto(String nome, String IDCommerciante) {
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

    public List<PromozioneInterface> getPromozioni(String IDCommerciante) {
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

    public List<RecensioneInterface> getRecensioni(String query) {
        recensioni.clear();
        try {
            ResultSet resultSet = DBManager.getInstance().executeQuery(query);
            recensioni.addAll(Deserializer.getInstance().deserializzaRecensioni(resultSet));
        } catch (SQLException e) {
            System.out.println("Errore nella ricerca: nessun risultato trovato");
        }

        return recensioni;
    }

    public List<RitiroInterface> getRitiri(String IDCommerciante, String IDCliente, String IDCorriere, String destinazione) {
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
