package it.unicam.cs.ids.c3.utenti.commerciante;

import it.unicam.cs.ids.c3.utenti.Utente;

/**
 * Questa classe implementa l'interfaccia {@link CommercianteInterface} ed estende la classe astratta {@link Utente}.
 * Ha la responsabilità di gestire un singolo {@link Commerciante}.
 */
public class Commerciante extends Utente implements CommercianteInterface {
    private String ragioneSociale;

    /**
     * Costruisce un nuovo {@link Commerciante}.
     *
     * @param username       - L'username del {@link Commerciante}.
     * @param password       - La password del {@link Commerciante}.
     * @param email          - L'email del {@link Commerciante}.
     * @param ragioneSociale - La ragione sociale del {@link Commerciante}.
     */
    public Commerciante(String username, String password, String email, String ragioneSociale) {
        super(username, password, email);
        this.ragioneSociale = ragioneSociale;
    }

    /**
     * Costruisce un {@link Commerciante} senza generarne l'ID, utilizzato quando si deserializza dal database.
     *
     * @param ID             - L'ID del {@link Commerciante}.
     * @param username       - L'username del {@link Commerciante}.
     * @param password       - La password del {@link Commerciante}.
     * @param email          - L'email del {@link Commerciante}.
     * @param ragioneSociale - La ragione sociale del {@link Commerciante}.
     */
    public Commerciante(String ID, String username, String password, String email, String ragioneSociale) {
        super(ID, username, password, email);
        this.ragioneSociale = ragioneSociale;
    }

    /**
     * Metodo getter per l'attributo ragioneSociale del {@link Commerciante}.
     *
     * @return la ragione sociale del {@link Commerciante}.
     */
    public String getRagioneSociale() {
        return ragioneSociale;
    }

    /**
     * Metodo setter per l'attributo ragioneSociale del {@link Commerciante}.
     *
     * @param ragioneSociale - La nuova ragione sociale.
     */
    public void setRagioneSociale(String ragioneSociale) {
        this.ragioneSociale = ragioneSociale;
    }

    /**
     * Metodo toString del {@link Commerciante}.
     *
     * @return la ragione sociale del {@link Commerciante}.
     */
    @Override
    public String toString() {
        return super.toString() + ", " +
                "ragioneSociale=" + ragioneSociale +
                '}';
    }
}