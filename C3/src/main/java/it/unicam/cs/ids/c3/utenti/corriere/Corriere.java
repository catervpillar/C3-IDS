package it.unicam.cs.ids.c3.utenti.corriere;

import it.unicam.cs.ids.c3.utenti.Utente;

/**
 * Questa classe implementa l'interfaccia {@link CorriereInterface} ed estende la classe astratta {@link Utente}.
 * Ha la responsabilità di gestire un singolo {@link Corriere}.
 */
public class Corriere extends Utente implements CorriereInterface {
    private String ragioneSociale;
    private StatoCorriere stato;

    /**
     * Costruisce un nuovo {@link Corriere}.
     *
     * @param username       - L'username del {@link Corriere}.
     * @param password       - La password del {@link Corriere}.
     * @param email          - L'email del {@link Corriere}.
     * @param ragioneSociale - La ragione sociale del {@link Corriere}.
     */
    public Corriere(String username, String password, String email, String ragioneSociale) {
        super(username, password, email);
        this.ragioneSociale = ragioneSociale;
        this.stato = StatoCorriere.NON_DISPONIBILE;
    }

    /**
     * Costruisce un {@link Corriere} senza generarne l'ID, utilizzato quando si deserializza dal database.
     *
     * @param ID             - L'ID del {@link Corriere}.
     * @param username       - L'username del {@link Corriere}.
     * @param password       - La password del {@link Corriere}.
     * @param email          - L'email del {@link Corriere}.
     * @param ragioneSociale - La ragione sociale del {@link Corriere}.
     */
    public Corriere(String ID, String username, String password, String email, String ragioneSociale) {
        super(ID, username, password, email);
        this.ragioneSociale = ragioneSociale;
        this.stato = StatoCorriere.NON_DISPONIBILE;
    }

    /**
     * Metodo getter per l'attributo ragioneSociale del {@link Corriere}.
     *
     * @return la ragione sociale del {@link Corriere}.
     */
    public String getRagioneSociale() {
        return ragioneSociale;
    }

    /**
     * Metodo setter per l'attributo ragioneSociale del {@link Corriere}.
     *
     * @param ragioneSociale - La nuova ragione sociale.
     */
    public void setRagioneSociale(String ragioneSociale) {
        this.ragioneSociale = ragioneSociale;
    }

    /**
     * Metodo getter per l'attributo stato del {@link Corriere}.
     *
     * @return lo stato di disponibilita' del {@link Corriere}.
     */
    public StatoCorriere getStato() {
        return stato;
    }

    /**
     * Metodo setter per l'attributo stato del {@link Corriere}.
     *
     * @param stato - Il nuovo stato.
     */
    public void setStato(StatoCorriere stato) {
        this.stato = stato;
    }

    /**
     * Metodo toString del {@link Corriere}.
     *
     * @return la ragione sociale del {@link Corriere}.
     */
    @Override
    public String toString() {
        return ragioneSociale;
    }
}