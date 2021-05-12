package it.unicam.cs.ids.c3.utenti.puntoRitiro;

import it.unicam.cs.ids.c3.utenti.Utente;

/**
 * Questa classe implementa l'interfaccia {@link PuntoRitiroInterface} ed estende la classe astratta {@link Utente}.
 * Ha la responsabilità di gestire un singolo {@link PuntoRitiro}.
 */
public class PuntoRitiro extends Utente implements PuntoRitiroInterface {
    private String ragioneSociale;

    /**
     * Costruisce un nuovo {@link PuntoRitiro}.
     *
     * @param username       - L'username del {@link PuntoRitiro}.
     * @param password       - La password del {@link PuntoRitiro}.
     * @param email          - L'email del {@link PuntoRitiro}.
     * @param ragioneSociale - La ragione sociale del {@link PuntoRitiro}.
     */
    public PuntoRitiro(String username, String password, String email, String ragioneSociale) {
        super(username, password, email);
        this.ragioneSociale = ragioneSociale;
    }

    /**
     * Costruisce un {@link PuntoRitiro} senza generarne l'ID, utilizzato quando si deserializza dal database.
     *
     * @param ID             - L'ID del {@link PuntoRitiro}.
     * @param username       - L'username del {@link PuntoRitiro}.
     * @param password       - La password del {@link PuntoRitiro}.
     * @param email          - L'email del {@link PuntoRitiro}.
     * @param ragioneSociale - La ragione sociale del {@link PuntoRitiro}.
     */
    public PuntoRitiro(String ID, String username, String password, String email, String ragioneSociale) {
        super(ID, username, password, email);
        this.ragioneSociale = ragioneSociale;
    }

    /**
     * Metodo getter per l'attributo ragioneSociale del {@link PuntoRitiro}.
     *
     * @return la ragione sociale del {@link PuntoRitiro}.
     */
    public String getRagioneSociale() {
        return ragioneSociale;
    }

    /**
     * Metodo setter per l'attributo ragioneSociale del {@link PuntoRitiro}.
     *
     * @param ragioneSociale - La nuova ragione sociale.
     */
    public void setRagioneSociale(String ragioneSociale) {
        this.ragioneSociale = ragioneSociale;
    }

    /**
     * Metodo toString del {@link PuntoRitiro}.
     *
     * @return la ragione sociale del {@link PuntoRitiro}.
     */
    @Override
    public String toString() {
        return ragioneSociale;
    }
}