package it.unicam.cs.ids.c3.utilities;

import it.unicam.cs.ids.c3.model.CategoriaProdotto;
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

    public void controllaCommerciante(String username, String password, String email, String ragioneSociale, List<CategoriaProdotto> listaCategorie) {
        controllaUtente(username, password, email);
        controllaStringa(ragioneSociale);
        controllaLista(listaCategorie);
    }

    public void controllaPuntoRitiro(String username, String password, String email, String ragioneSociale) {
        controllaUtente(username, password, email);
        controllaStringa(ragioneSociale);
    }

    public void controllaCorriere(String username, String password, String email, String ragioneSociale) {
        controllaUtente(username, password, email);
        controllaStringa(ragioneSociale);
    }

    public void controllaStringa(String stringa) {
        if (Objects.isNull(stringa))
            throw new NullPointerException("La stringa passata e' nulla");

        if (stringa.isEmpty() || stringa.isBlank())
            throw new IllegalArgumentException("La stringa passata e' vuota");
    }

    public void controllaID(String ID) {
        controllaStringa(ID);
        if (ID.length() != 8) throw new IllegalArgumentException();

        for (int i = 0; i < ID.length(); i++) {
            if (!Character.isLetterOrDigit(ID.charAt(i))) {
                throw new IllegalArgumentException("L'ID passato non e' valido");
            }
        }
    }

    public void controllaUsername(String username) {
        controllaStringa(username);

        for (int i = 0; i < username.length(); i++) {
            if (!Character.isLetterOrDigit(username.charAt(i))) {
                if (username.charAt(i) != '.' || username.charAt(i) != '_' || username.charAt(i) != '-')
                    throw new IllegalArgumentException("L'username non e' valido");
            }
        }
    }

    public void controllaPassword(String password) {
        controllaStringa(password);
    }

    public void controllaEmail(String email) {
        controllaStringa(email);
        if (!(EmailValidator.getInstance().isValid(email)))
            throw new IllegalArgumentException("L'email non e' valida");
    }

    public void controllaNumero(String numero, int lunghezza) {
        controllaStringa(numero);
        if (numero.length() != lunghezza) throw new IllegalArgumentException();
        for (int i = 0; i < numero.length(); i++) {
            if (!(Character.isDigit(numero.charAt(i))))
                throw new IllegalArgumentException("Il numero non e' valido");
        }
    }

    public void controllaNome(String nome, String message) {
        controllaStringa(nome);
        for (int i = 0; i < nome.length(); i++) {
            if (!(Character.isLetter(nome.charAt(i)))) {
                if ((nome.charAt(i) != ' '))
                    throw new IllegalArgumentException(message);
            }
        }
    }

    public void controllaIndirizzo(String via, int numeroCivico, String citta, String CAP, String provincia, String stato) {
        controllaNome(via, "La via non e' valida");
        if (numeroCivico < 1) throw new IllegalArgumentException("Il numero civico non e' valido");
        controllaNome(citta, "La citta' non e' valida");
        controllaNumero(CAP, 5);
        controllaNome(provincia, "La provincia non e' valida");
        controllaNome(stato, "Lo stato non e' valido");
    }

    public void controllaLista(List<?> listaCategorie) {
        if (Objects.isNull(listaCategorie))
            throw new NullPointerException("La lista e' nulla");
        if (listaCategorie.isEmpty())
            throw new IllegalArgumentException("La lista e' vuota");
    }

    public void controllaData(GregorianCalendar data) {
        if (Objects.isNull(data))
            throw new NullPointerException("La data e' nulla");
        if (data.compareTo(new GregorianCalendar()) < 0)
            throw new IllegalArgumentException("La data non e' valida");
    }
}
