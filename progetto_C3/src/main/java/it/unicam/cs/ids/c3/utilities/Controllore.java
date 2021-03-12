package it.unicam.cs.ids.c3.utilities;

import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import org.apache.commons.validator.routines.EmailValidator;

import java.util.GregorianCalendar;
import java.util.List;
import java.util.Objects;

public final class Controllore {
    private static Controllore instance;

    public static Controllore getInstance() {
        if (Objects.isNull(instance))
            instance = new Controllore();
        return instance;
    }

    private void controllaUtente(String username, String password, String email) {
        controllaUsername(username);
        controllaPassword(password);
        controllaEmail(email);
    }

    public void controllaCliente(String username, String password, String email, String nome, String cognome) {
        controllaNome(nome, "Il nome non e' valido");
        controllaNome(cognome, "Il cognome non e' valido");
        controllaUtente(username, password, email);
    }

    public void controllaCommerciante(String username, String password, String email, String ragioneSociale) {
        controllaUtente(username, password, email);
        controllaStringa(ragioneSociale, "La ragione sociale non e' valida");
    }

    public void controllaPuntoRitiro(String username, String password, String email, String ragioneSociale) {
        controllaUtente(username, password, email);
        controllaStringa(ragioneSociale, "La ragione sociale non e' valida");
    }

    public void controllaCorriere(String username, String password, String email, String ragioneSociale) {
        controllaUtente(username, password, email);
        controllaStringa(ragioneSociale, "La ragione sociale non e' valida");
    }

    public void controllaStringa(String stringa, String errore) {
        if (Objects.isNull(stringa))
            throw new NullPointerException("La stringa passata e' nulla");

        if (stringa.isBlank())
            throw new IllegalArgumentException(errore);
    }

    public void controllaUsername(String username) {
        controllaStringa(username, "L'username' non e' valido");

        for (int i = 0; i < username.length(); i++) {
            if (!Character.isLetterOrDigit(username.charAt(i))) {
                if (username.charAt(i) != '.' && username.charAt(i) != '_' && username.charAt(i) != '-')
                    throw new IllegalArgumentException("L'username non e' valido");
            }
        }
    }

    public void controllaPassword(String password) {
        controllaStringa(password, "La password non e' valida");
    }

    public void controllaEmail(String email) {
        controllaStringa(email, "L'email non e' valida");
        if (!(EmailValidator.getInstance().isValid(email)))
            throw new IllegalArgumentException("L'email non e' valida");
    }

    public void controllaNumero(String numero, int lunghezza) {
        controllaStringa(numero, "Il numero non e' valido");
        if (numero.length() != lunghezza) throw new IllegalArgumentException();
        for (int i = 0; i < numero.length(); i++) {
            if (!(Character.isDigit(numero.charAt(i))))
                throw new IllegalArgumentException("Il numero non e' valido");
        }
    }

    public void controllaNome(String nome, String message) {
        controllaStringa(nome, "Il nome non e' valido");
        for (int i = 0; i < nome.length(); i++) {
            if (!(Character.isLetter(nome.charAt(i)))) {
                if ((nome.charAt(i) != ' '))
                    throw new IllegalArgumentException(message);
            }
        }
    }

    public void controllaIndirizzo(String indirizzo) {
        controllaStringa(indirizzo, "L'indirizzo non e' valido");
    }

    public void controllaDatePicker(DatePicker datePicker, String errore) {
        if (Objects.isNull(datePicker.getValue()))
            throw new IllegalArgumentException(errore);
    }
}
