package it.unicam.cs.ids.c3.utenti.commerciante;

/**
 * Questa interfaccia è implementata da tutte le classi che devono gestire un singolo {@link Commerciante}.
 */
public interface CommercianteInterface {
    /**
     * Metodo getter per l'attributo ID.
     *
     * @return l'attributo ID.
     */
    String getID();

    /**
     * Metodo setter per l'attributo ID.
     *
     * @param ID - Il nuovo attributo ID.
     */
    void setID(String ID);

    /**
     * Metodo getter per l'attributo username.
     *
     * @return l'attributo username.
     */
    String getUsername();

    /**
     * Metodo setter per l'attributo username.
     *
     * @param username - Il nuovo attributo username.
     */
    void setUsername(String username);

    /**
     * Metodo getter per l'attributo email.
     *
     * @return l'attributo email.
     */
    String getEmail();

    /**
     * Metodo setter per l'attributo email.
     *
     * @param email - Il nuovo attributo email.
     */
    void setEmail(String email);

    /**
     * Metodo getter per l'attributo password.
     *
     * @return l'attributo password.
     */
    String getPassword();

    /**
     * Metodo setter per l'attributo password.
     *
     * @param password - Il nuovo attributo password.
     */
    void setPassword(String password);

    /**
     * Metodo getter per l'attributo telefono.
     *
     * @return l'attributo telefono.
     */
    String getTelefono();

    /**
     * Metodo setter per l'attributo telefono.
     *
     * @param telefono - Il nuovo attributo telefono.
     */
    void setTelefono(String telefono);

    /**
     * Metodo getter per l'attributo indirizzo.
     *
     * @return l'attributo indirizzo.
     */
    String getIndirizzo();

    /**
     * Metodo setter per l'attributo indirizzo.
     *
     * @param indirizzo - Il nuovo attributo indirizzo.
     */
    void setIndirizzo(String indirizzo);

    /**
     * Metodo getter per l'attributo ragioneSociale.
     *
     * @return l'attributo ragioneSociale.
     */
    String getRagioneSociale();

    /**
     * Metodo setter per l'attributo ragioneSociale.
     *
     * @param ragioneSociale - Il nuovo attributo ragioneSociale.
     */
    void setRagioneSociale(String ragioneSociale);
}
