package it.unicam.cs.ids.c3.ritiro;

import it.unicam.cs.ids.c3.database.SerializerAggiunta;
import it.unicam.cs.ids.c3.database.SerializerElimina;
import it.unicam.cs.ids.c3.database.SerializerModifica;
import it.unicam.cs.ids.c3.prodotto.Prodotto;
import it.unicam.cs.ids.c3.utenti.commerciante.Commerciante;
import it.unicam.cs.ids.c3.utenti.cliente.Cliente;
import it.unicam.cs.ids.c3.utenti.corriere.Corriere;

import java.sql.SQLException;
import java.util.List;

/**
 * Questa classe è un sigleton e si occupa di gestire i {@link Ritiro}.
 * Permette infatti la creazione, modifica e eliminazione di essi.
 */
public final class GestoreRitiri {
    private static GestoreRitiri instance;

    /**
     * Costruttore privato usato solamente all'interno di questa classe
     */
    private GestoreRitiri() {
    }

    /**
     * Metodo getter per l'attributo instance. Se instance è nulla, viene inizializzata.
     *
     * @return l'attributo instance.
     */
    public static GestoreRitiri getInstance() {
        if (instance == null)
            instance = new GestoreRitiri();
        return instance;
    }

    /**
     * Crea un {@link Ritiro}, aggiunge ad esso tutti gli ID dei prodotti passati in input e successivamente lo fa serializzare nel database.
     *
     * @param IDCommerciante - ID del {@link Commerciante} associato al {@link Ritiro}
     * @param IDCliente - ID del {@link Cliente} associato al {@link Ritiro}
     * @param IDCorriere - ID del {@link Corriere} associato al {@link Ritiro}
     * @param destinazione - destinazione del {@link Ritiro}
     * @param tipoConsegna - il tipo della consegna del {@link Ritiro}
     * @param listaIDProdotti - lista dei {@link Prodotto} associati al {@link Ritiro}
     * @throws SQLException in caso di errore con il database
     */
    public void creaRitiro(String IDCommerciante, String IDCliente, String IDCorriere, String destinazione, TipoConsegna tipoConsegna, List<String> listaIDProdotti) throws SQLException {
        RitiroInterface ritiro = new Ritiro(IDCommerciante, IDCliente, IDCorriere, destinazione, tipoConsegna);
        ritiro.getListaIDProdotti().addAll(listaIDProdotti);
        SerializerAggiunta.getInstance().serializzaRitiro(ritiro);
    }

    /**
     *Permette di modificare un {@link Ritiro} e successivamente serializza le modifiche nel database.
     *
     * @param ID - ID del ritiro da modificare
     * @param IDCommerciante - ID del {@link Commerciante} associato al {@link Ritiro}
     * @param IDCliente - ID del {@link Cliente} associato al {@link Ritiro}
     * @param IDCorriere - ID del {@link Corriere} associato al {@link Ritiro}
     * @param destinazione - destinazione del {@link Ritiro}
     * @param ritirato - attributo per verificare se il {@link Ritiro} e' stato ritirato; true se il {@link Ritiro} e' stato ritirato, false altrimenti
     * @param tipoConsegna - tipo della consegna del {@link Ritiro}
     * @param stato - tracking del ritiro
     * @throws SQLException in caso di errore con il database
     */
    public void modificaRitiro(String ID, String IDCommerciante, String IDCliente, String IDCorriere, String destinazione, boolean ritirato, TipoConsegna tipoConsegna, StatoTracking stato) throws SQLException {
        RitiroInterface ritiro = new Ritiro(ID, IDCommerciante, IDCliente, IDCorriere, destinazione, tipoConsegna);
        ritiro.setStatoTracking(stato);
        ritiro.setRitirato(ritirato);
        SerializerModifica.getInstance().modificaRitiro(ritiro);
    }

    /**
     * Permette di eliminare un {@link Ritiro} eliminandolo dal database.
     *
     * @param ID - ID del {@link Ritiro} da eliminare
     * @throws SQLException in caso di errore con il database
     */
    public void eliminaRitiro(String ID) throws SQLException {
        SerializerElimina.getInstance().eliminaRitiro(ID);
    }
}
