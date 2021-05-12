package it.unicam.cs.ids.c3.utenti.cliente;

/**
 * Questa interfaccia è implementata da tutte le classi che devono gestire un singolo {@link Cliente}.
 */
public interface ClienteInterface {
    /**
     * Metodo getter per l'attributo ID.
     *
     * @return l'attributo ID.
     */
    String getID();

    /**
     * Metodo setter per l'attributo ID.
     *
     * @param ID - Il nuovo attributo il ID.
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
     * @param username - Il nuovo attributo il username.
     */
    void setUsername(String username);

    /**
     * Metodo getter per l'attributo password.
     *
     * @return l'attributo password.
     */
    String getPassword();

    /**
     * Metodo setter per l'attributo password.
     *
     * @param password - Il nuovo attributo il password.
     */
    void setPassword(String password);

    /**
     * Metodo getter per l'attributo indirizzo.
     *
     * @return l'attributo indirizzo.
     */
    String getIndirizzo();

    /**
     * Metodo setter per l'attributo indirizzo.
     *
     * @param indirizzo - Il nuovo attributo il indirizzo.
     */
    void setIndirizzo(String indirizzo);

    /**
     * Metodo getter per l'attributo email.
     *
     * @return l'attributo email.
     */
    String getEmail();

    /**
     * Metodo setter per l'attributo email.
     *
     * @param email - Il nuovo attributo il email.
     */
    void setEmail(String email);

    /**
     * Metodo getter per l'attributo telefono.
     *
     * @return l'attributo telefono.
     */
    String getTelefono();

    /**
     * Metodo setter per l'attributo telefono.
     *
     * @param telefono - Il nuovo attributo il telefono.
     */
    void setTelefono(String telefono);

    /**
     * Metodo getter per l'attributo nome.
     *
     * @return l'attributo nome.
     */
    String getNome();

    /**
     * Metodo setter per l'attributo nome.
     *
     * @param nome - Il nuovo attributo il nome.
     */
    void setNome(String nome);

    /**
     * Metodo getter per l'attributo cognome.
     *
     * @return l'attributo cognome.
     */
    String getCognome();

    /**
     * Metodo setter per l'attributo cognome.
     *
     * @param cognome - Il nuovo attributo il cognome.
     */
    void setCognome(String cognome);
}
