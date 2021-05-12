package it.unicam.cs.ids.c3.utenti.cliente;

import it.unicam.cs.ids.c3.utenti.Utente;

/**
 * Questa classe implementa l'interfaccia {@link ClienteInterface} ed estende la classe astratta {@link Utente}.
 * Ha la responsabilità di gestire un singolo {@link Cliente}.
 */
public class Cliente extends Utente implements ClienteInterface {
    private String nome;
    private String cognome;

    /**
     * Costruisce un nuovo {@link Cliente}.
     *
     * @param username - L'username del {@link Cliente}.
     * @param password - La password del {@link Cliente}.
     * @param email    - L'email del {@link Cliente}.
     * @param nome     - Il nome del {@link Cliente}.
     * @param cognome  - Il cognome del {@link Cliente}.
     */
    public Cliente(String username, String password, String email, String nome, String cognome) {
        super(username, password, email);
        this.nome = nome;
        this.cognome = cognome;
    }

    /**
     * Costruisce un {@link Cliente} senza generarne l'ID, utilizzato quando si deserializza dal database.
     *
     * @param ID       - L'ID del {@link Cliente}.
     * @param username - L'username del {@link Cliente}.
     * @param password - la password del {@link Cliente}.
     * @param email    - l'email del {@link Cliente}.
     * @param nome     - il nome del {@link Cliente}.
     * @param cognome  - il cognome del {@link Cliente}.
     */
    public Cliente(String ID, String username, String password, String email, String nome, String cognome) {
        super(ID, username, password, email);
        this.nome = nome;
        this.cognome = cognome;
    }

    /**
     * Metodo getter per l'attributo nome del {@link Cliente}.
     *
     * @return il nome del {@link Cliente}.
     */
    public String getNome() {
        return nome;
    }

    /**
     * Metodo setter per l'attributo nome del {@link Cliente}.
     *
     * @param nome - Il nuovo nome.
     */
    public void setNome(String nome) {
        this.nome = nome;
    }

    /**
     * Metodo getter per l'attributo cognome del {@link Cliente}.
     *
     * @return il cognome del {@link Cliente}.
     */
    public String getCognome() {
        return cognome;
    }

    /**
     * Metodo setter per l'attributo cognome del {@link Cliente}.
     *
     * @param cognome - Il nuovo cognome.
     */
    public void setCognome(String cognome) {
        this.cognome = cognome;
    }

    /**
     * Metodo toString del {@link Cliente}.
     *
     * @return nome e cognome del {@link Cliente}.
     */
    @Override
    public String toString() {
        return super.toString() + ", " +
                "nome=" + nome +
                ", cognome=" + cognome +
                '}';
    }
}
