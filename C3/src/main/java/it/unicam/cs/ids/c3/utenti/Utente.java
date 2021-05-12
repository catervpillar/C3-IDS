package it.unicam.cs.ids.c3.utenti;

import it.unicam.cs.ids.c3.utilities.HasID;
import it.unicam.cs.ids.c3.utilities.IDGenerator;
import java.util.Objects;

/**
 * Questa classe implementa l'interfaccia {@link HasID} ed ha la responsabilità di rappresentare un utente generico.
 */
public abstract class Utente implements HasID {
    private String ID;
    private String username;
    private String password;
    private String email;
    private String telefono;
    private String indirizzo;

    /**
     * Costruisce un nuovo {@link Utente}
     *
     * @param username - L'username del nuovo {@link Utente}.
     * @param password - La password del nuovo {@link Utente}.
     * @param email    - L'email del nuovo {@link Utente}.
     */
    public Utente(String username, String password, String email) {
        IDGenerator.generateID(this);
        this.username = username;
        this.password = password;
        this.email = email;
        this.telefono = null;
        this.indirizzo = null;
    }

    /**
     * Costruisce un nuovo {@link Utente} senza generarne l'ID, utilizzato quando si deserializza dal database.
     *
     * @param ID       - L'ID del nuovo {@link Utente}.
     * @param username - L'username del nuovo {@link Utente}.
     * @param password - La password del nuovo {@link Utente}.
     * @param email    - L'email del nuovo {@link Utente}.
     */
    public Utente(String ID, String username, String password, String email) {
        this.ID = ID;
        this.username = username;
        this.password = password;
        this.email = email;
        this.telefono = null;
        this.indirizzo = null;
    }

    /**
     * Metodo getter per l'attributo ID dell'{@link Utente}.
     *
     * @return l'ID dell'{@link Utente}.
     */
    public String getID() {
        return ID;
    }

    /**
     * Metodo setter per l'attributo ID dell'{@link Utente}.
     *
     * @param ID - Il nuovo ID dell'{@link Utente}.
     */
    public void setID(String ID) {
        this.ID = ID;
    }

    /**
     * Metodo getter per l'attributo username dell'{@link Utente}.
     *
     * @return l'username dell'{@link Utente}.
     */
    public String getUsername() {
        return username;
    }

    /**
     * Metodo setter per l'attributo username dell'{@link Utente}.
     *
     * @param username - Il nuovo username dell'{@link Utente}.
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * Metodo getter per l'attributo password dell'{@link Utente}.
     *
     * @return la password dell'{@link Utente}.
     */
    public String getPassword() {
        return password;
    }

    /**
     * Metodo setter per l'attributo password dell'{@link Utente}.
     *
     * @param password - La nuova password dell'{@link Utente}.
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * Metodo getter per l'attributo email dell'{@link Utente}.
     *
     * @return l'email dell'{@link Utente}.
     */
    public String getEmail() {
        return email;
    }

    /**
     * Metodo setter per l'attributo email dell'{@link Utente}.
     *
     * @param email - La nuova email dell'{@link Utente}.
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * Metodo getter per l'attributo telefono dell'{@link Utente}.
     *
     * @return il telefono dell'{@link Utente}.
     */
    public String getTelefono() {
        return telefono;
    }

    /**
     * Metodo setter per l'attributo telefono dell'{@link Utente}.
     *
     * @param telefono - Il nuovo telefono dell'{@link Utente}.
     */
    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    /**
     * Metodo getter per l'attributo indirizzo dell'{@link Utente}.
     *
     * @return l'indirizzo dell'{@link Utente}.
     */
    public String getIndirizzo() {
        return indirizzo;
    }

    /**
     * Metodo setter per l'attributo indirizzo dell'{@link Utente}.
     *
     * @param indirizzo - Il nuovo indirizzo dell'{@link Utente}.
     */
    public void setIndirizzo(String indirizzo) {
        this.indirizzo = indirizzo;
    }

    /**
     * Metodo equals dell'{@link Utente}.
     *
     * @param o - L'oggetto da confrontare.
     * @return true se i due oggetti sono uguali, false altrimenti.
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Utente utente = (Utente) o;
        return ID.equals(utente.ID) && username.equals(utente.username) && email.equals(utente.email);
    }

    /**
     * Metodo hashCode dell'{@link Utente}.
     *
     * @return l'hashCode dell'{@link Utente}.
     */
    @Override
    public int hashCode() {
        return Objects.hash(ID, username, email);
    }

    /**
     * Metodo toString dell'{@link Utente}.
     *
     * @return le informazioni dell'{@link Utente}.
     */
    @Override
    public String toString() {
        return "{" +
                "ID=" + ID +
                ", username=" + username +
                ", password=" + password +
                ", email=" + email +
                ", telefono=" + telefono +
                ", indirizzo=" + indirizzo;
    }
}