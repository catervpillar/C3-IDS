package it.unicam.cs.ids.c3.utilities;

import it.unicam.cs.ids.c3.utenti.Utente;
import it.unicam.cs.ids.c3.utenti.cliente.Cliente;
import it.unicam.cs.ids.c3.utenti.commerciante.Commerciante;
import it.unicam.cs.ids.c3.utenti.corriere.Corriere;
import it.unicam.cs.ids.c3.utenti.puntoRitiro.PuntoRitiro;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import org.apache.commons.validator.routines.EmailValidator;
import java.util.Objects;

/**
 * Questa classe e' un singleton e si occupa di controllare i vari parametri prima della creazione
 * di determinati oggetti.
 */
public final class Controllore {
    private static Controllore instance;

    /**
     * Costruttore privato usato solamente all'interno di questa classe.
     */
    private Controllore() {
    }

    /**
     * Metodo getter per l'attributo instance. Se instance è nulla, viene inizializzata.
     *
     * @return l'attributo instance.
     */
    public static Controllore getInstance() {
        if (Objects.isNull(instance))
            instance = new Controllore();
        return instance;
    }

    /**
     * Controlla i parametri necessari alla creazione di un {@link Utente}.
     *
     * @param username - L'username da controllare.
     * @param password - La password da controllare.
     * @param email    - L'email da controllare.
     */
    private void controllaUtente(String username, String password, String email) {
        controllaUsername(username);
        controllaPassword(password);
        controllaEmail(email);
    }

    /**
     * Controlla i parametri necessari alla creazione di un {@link Cliente}.
     *
     * @param username - L'username da controllare.
     * @param password - La password da controllare.
     * @param email    - L'email da controllare.
     * @param nome     - Il nome da controllare.
     * @param cognome  - Il cognome da controllare.
     */
    public void controllaCliente(String username, String password, String email, String nome, String cognome) {
        controllaNome(nome, "Il nome non e' valido");
        controllaNome(cognome, "Il cognome non e' valido");
        controllaUtente(username, password, email);
    }

    /**
     * Controlla i parametri necessari alla creazione di un {@link Commerciante}.
     *
     * @param username       - L'username da controllare.
     * @param password       - La password da controllare.
     * @param email          - L'email da controllare.
     * @param ragioneSociale - La ragione sociale da controllare.
     */
    public void controllaCommerciante(String username, String password, String email, String ragioneSociale) {
        controllaUtente(username, password, email);
        controllaStringa(ragioneSociale, "La ragione sociale non e' valida");
    }

    /**
     * Controlla i parametri necessari alla creazione di un {@link Corriere}.
     *
     * @param username       - L'username da controllare.
     * @param password       - La password da controllare.
     * @param email          - L'email da controllare.
     * @param ragioneSociale - La ragione sociale da controllare.
     */
    public void controllaPuntoRitiro(String username, String password, String email, String ragioneSociale) {
        controllaUtente(username, password, email);
        controllaStringa(ragioneSociale, "La ragione sociale non e' valida");
    }

    /**
     * Controlla i parametri necessari alla creazione di un {@link PuntoRitiro}.
     *
     * @param username       - L'username da controllare.
     * @param password       - La password da controllare.
     * @param email          - L'email da controllare.
     * @param ragioneSociale - La ragione sociale da controllare.
     */
    public void controllaCorriere(String username, String password, String email, String ragioneSociale) {
        controllaUtente(username, password, email);
        controllaStringa(ragioneSociale, "La ragione sociale non e' valida");
    }

    /**
     * Controlla che la stringa passata in input non sia nulla, vuota o composta da soli spazi.
     *
     * @param stringa - La stringa da controllare.
     * @param errore  - L'errore da stampare in caso di eccezione.
     */
    public void controllaStringa(String stringa, String errore) {
        if (Objects.isNull(stringa))
            throw new NullPointerException("La stringa passata e' nulla");

        if (stringa.isBlank())
            throw new IllegalArgumentException(errore);
    }

    /**
     * Controlla che l'username passato in input sia valido.
     *
     * @param username - L'username da controllare.
     */
    public void controllaUsername(String username) {
        controllaStringa(username, "L'username' non e' valido");

        for (int i = 0; i < username.length(); i++) {
            if (!Character.isLetterOrDigit(username.charAt(i))) {
                if (username.charAt(i) != '.' && username.charAt(i) != '_' && username.charAt(i) != '-')
                    throw new IllegalArgumentException("L'username non e' valido");
            }
        }
    }

    /**
     * Controlla che la password passata in input sia valida.
     *
     * @param password - La password da controllare.
     */
    public void controllaPassword(String password) {
        controllaStringa(password, "La password non e' valida");
    }

    /**
     * Controlla che l'email passata in input sia valida.
     *
     * @param email - L'email da controllare.
     */
    public void controllaEmail(String email) {
        controllaStringa(email, "L'email non e' valida");
        if (!(EmailValidator.getInstance().isValid(email)))
            throw new IllegalArgumentException("L'email non e' valida");
    }

    /**
     * Controlla che il numero passato in input sia valido.
     *
     * @param numero    - Il numero da controllare.
     * @param lunghezza - La lunghezza che il numero in input deve rispettare.
     */
    public void controllaNumero(String numero, int lunghezza) {
        controllaStringa(numero, "Il numero non e' valido");
        if (numero.length() != lunghezza) throw new IllegalArgumentException();
        for (int i = 0; i < numero.length(); i++) {
            if (!(Character.isDigit(numero.charAt(i))))
                throw new IllegalArgumentException("Il numero non e' valido");
        }
    }

    /**
     * Controlla che il nome passato in input sia valido.
     *
     * @param nome    - Il nome da controllare.
     * @param message - Il messaggio d'errore da stampare in caso di eccezione.
     */
    public void controllaNome(String nome, String message) {
        controllaStringa(nome, "Il nome non e' valido");
        for (int i = 0; i < nome.length(); i++) {
            if (!(Character.isLetter(nome.charAt(i)))) {
                if ((nome.charAt(i) != ' '))
                    throw new IllegalArgumentException(message);
            }
        }
    }

    /**
     * Controlla che l'indirizzo passato in input sia valido.
     *
     * @param indirizzo - L'indirizzo da controllare.
     */
    public void controllaIndirizzo(String indirizzo) {
        controllaStringa(indirizzo, "L'indirizzo non e' valido");
    }

    /**
     * Controlla che il valore del {@link DatePicker} passato non sia nullo.
     *
     * @param datePicker - Il {@link DatePicker} da controllare.
     * @param errore     - Il messaggio di errore da stampare in caso di eccezione.
     */
    public void controllaDatePicker(DatePicker datePicker, String errore) {
        if (Objects.isNull(datePicker.getValue()))
            throw new IllegalArgumentException(errore);
    }

    /**
     * Controlla che il valore della {@link ChoiceBox} passata in input non sia nullo.
     *
     * @param choiceBox - La {@link ChoiceBox} da controllare.
     * @param errore    - Il messaggio di errore da stampare in caso di eccezione.
     */
    public void controllaChoiceBox(ChoiceBox<?> choiceBox, String errore) {
        if (Objects.isNull(choiceBox.getValue()))
            throw new IllegalArgumentException(errore);
    }
}
