package it.unicam.cs.ids.c3.utilities;

import it.unicam.cs.ids.c3.prodotto.Prodotto;
import it.unicam.cs.ids.c3.prodotto.ProdottoInterface;
import it.unicam.cs.ids.c3.promozione.PromozioneInterface;
import it.unicam.cs.ids.c3.recensione.RecensioneInterface;
import it.unicam.cs.ids.c3.ritiro.Ritiro;
import it.unicam.cs.ids.c3.ritiro.RitiroInterface;
import it.unicam.cs.ids.c3.utenti.cliente.Cliente;
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

/**
 * Questa classe e' un singleton e si occupa di gestire le varie ricerche e di salvare temporaneamente in
 * locale gli oggetti che vengono deserializzati.
 */
public final class GestoreRicerche {
    private static GestoreRicerche instance;

    private final List<CommercianteInterface> commercianti = new ArrayList<>();
    private final List<PuntoRitiroInterface> puntiDiRitiro = new ArrayList<>();
    private final List<CorriereInterface> corrieri = new ArrayList<>();
    private final List<ProdottoInterface> prodotti = new ArrayList<>();
    private final List<PromozioneInterface> promozioni = new ArrayList<>();
    private final List<RecensioneInterface> recensioni = new ArrayList<>();
    private final List<RitiroInterface> ritiri = new ArrayList<>();

    /**
     * Costruttore privato usato solamente all'interno di questa classe.
     */
    private GestoreRicerche() {
    }

    /**
     * Metodo getter per l'attributo instance. Se instance è nulla, viene inizializzata.
     *
     * @return l'attributo instance.
     */
    public static GestoreRicerche getInstance() {
        if (instance == null)
            instance = new GestoreRicerche();
        return instance;
    }

    /**
     * Metodo getter per l'attributo commercianti.
     *
     * @return la lista dei commercianti deserializzati.
     */
    public List<CommercianteInterface> getCommercianti() {
        return commercianti;
    }

    /**
     * Metodo getter per l'attributo recensioni.
     *
     * @return la lista delle recensioni deserializzate.
     */
    public List<RecensioneInterface> getRecensioni() {
        return recensioni;
    }

    /**
     * Metodo getter per l'attributo puntiDiRitiro.
     *
     * @return la lista dei punti di ritiro deserializzati.
     */
    public List<PuntoRitiroInterface> getPuntiDiRitiro() {
        return puntiDiRitiro;
    }

    /**
     * Metodo getter per l'attributo corrieri.
     *
     * @return la lista dei corrieri deserializzati.
     */
    public List<CorriereInterface> getCorrieri() {
        return corrieri;
    }

    /**
     * Metodo getter per l'attributo prodotti.
     *
     * @return la lista dei prodotti deserializzati.
     */
    public List<ProdottoInterface> getProdotti() {
        return prodotti;
    }

    /**
     * Ritorna la lista dei commercianti trovati in base alla ragione sociale data in input.
     *
     * @param ragioneSociale - La ragione sociale del {@link Commerciante} da cercare.
     * @return la lista dei risultati.
     */
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

    /**
     * Ritorna la lista dei corrieri trovati in base alla ragione sociale data in input.
     *
     * @param ragioneSociale - La ragione sociale del {@link Corriere} da cercare.
     * @return la lista dei risultati.
     */
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

    /**
     * Ritorna la lista dei punti di ritiro trovati in base alla ragione sociale data in input.
     *
     * @param ragioneSociale - La ragione sociale del {@link PuntoRitiro} da cercare.
     * @return la lista dei risultati.
     */
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

    /**
     * Ritorna la lista dei prodotti trovati in base al nome o all'ID del {@link Commerciante} dati in input.
     *
     * @param nome           - Il nome del {@link Prodotto} da cercare.
     * @param IDCommerciante - L'ID del {@link Commerciante} che vende il {@link Prodotto} da cercare.
     * @return la lista dei risultati.
     */
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

    /**
     * Ritorna la lista delle promozioni trovate in base all'ID del {@link Commerciante} che le ha lanciate.
     *
     * @param IDCommerciante - L'ID del {@link Commerciante} delle promozioni da cercare.
     * @return la lista dei risultati.
     */
    public List<PromozioneInterface> getPromozioni(String IDCommerciante) {
        promozioni.clear();
        try {
            ResultSet resultSet;
            if (!Objects.isNull(IDCommerciante)) {
                resultSet = DBManager.getInstance().executeQuery("select * from promozione where commerciante_ID = \"" + IDCommerciante + "\";");
            } else {
                resultSet = DBManager.getInstance().executeQuery("select * from promozione");
            }
            promozioni.addAll(Deserializer.getInstance().deserializzaPromozioni(resultSet));
        } catch (SQLException e) {
            System.out.println("Errore nella ricerca: nessun risultato trovato");
        }

        return promozioni;
    }

    /**
     * Ritorna la lista delle recensioni trovate in base ad una query da eseguire passata in in input.
     *
     * @param query - la query da eseguire.
     * @return la lista dei risultati.
     */
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

    /**
     * Ritorna la lista dei ritiri trovati in base all'ID del {@link Commerciante}, del {@link Cliente},
     * del {@link Corriere} o in base alla destinazione.
     *
     * @param IDCommerciante - L'ID del {@link Commerciante} che ha commissionato il {@link Ritiro}.
     * @param IDCliente      - L'ID del {@link Cliente} che che deve ritirare il {@link Ritiro}.
     * @param IDCorriere     - L'ID del {@link Corriere} che trasporta il {@link Ritiro}.
     * @param destinazione   - La destinazione del {@link Ritiro}.
     * @return la lista dei risultati
     */
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

    /**
     * Svuota tutte le liste degli oggetti deserializzati e salvati in locale.
     */
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
