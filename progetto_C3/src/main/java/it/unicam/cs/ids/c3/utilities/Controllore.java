package it.unicam.cs.ids.c3.utilities;

import it.unicam.cs.ids.c3.model.HasID;
import org.apache.commons.validator.routines.EmailValidator;

import java.util.GregorianCalendar;
import java.util.List;
import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public final class Controllore {
    private static Controllore instance;

    private final String STRINGA_VUOTA = "La stringa passata e' vuota";
    private final String STRINGA_NON_VALIDA = "La stringa passata non e' valida";
    private final String ID_NON_VALIDO = "L'ID passato non e' valido";


    public static Controllore getInstance() {
        if (Objects.isNull(instance))
            instance = new Controllore();
        return instance;
    }

    public void controllaStringa(String stringa) {
        if (stringa.length() == 0) throw new IllegalArgumentException(STRINGA_VUOTA);
        if (Objects.isNull(stringa))
            throw new NullPointerException();

        boolean ok = false;
        for (int i = 0; i < stringa.length(); i++) {
            if (Character.isLetterOrDigit(stringa.charAt(i))) {
                ok = true;
                break;
            }
        }
        if (!ok) throw new IllegalArgumentException(STRINGA_NON_VALIDA);
    }

    public void controllaID(String ID) {
        controllaStringa(ID);
        if (ID.length() != 8) throw new IllegalArgumentException();

        for (int i = 0; i < ID.length(); i++) {
            if (!Character.isLetterOrDigit(ID.charAt(i))) {
                throw new IllegalArgumentException(ID_NON_VALIDO);
            }
        }
    }

    public void controllaUsername(String username) {
        controllaStringa(username);
        for (int i = 0; i < username.length(); i++) {
            if (!Character.isLetterOrDigit(username.charAt(i))) {
                if (username.charAt(i) != '.' || username.charAt(i) != '_' || username.charAt(i) != '-')
                    throw new IllegalArgumentException(ID_NON_VALIDO);
            }
        }
    }

    public void controllaEmail(String email) {
        controllaStringa(email);
        if (!(EmailValidator.getInstance().isValid(email)))
            throw new IllegalArgumentException();
    }

    public void controllaTelefono(String telefono){
        controllaStringa(telefono);
        if (telefono.length() != 10) throw new IllegalArgumentException();
        for (int i=0; i<telefono.length(); i++){
            if(!(Character.isDigit(telefono.charAt(i))))
                throw new IllegalArgumentException();
        }
    }

    public void controllaNome(String nome){
        controllaStringa(nome);
        for (int i=0; i<nome.length(); i++){
            if (!(Character.isLetter(nome.charAt(i)))) {
                    if((nome.charAt(i) != ' '))
                        throw new IllegalArgumentException();
            }
        }
    }

    public void controllaIndirizzo(String indirizzo){
        controllaStringa(indirizzo);
        for (int i = 0; i < indirizzo.length(); i++) {
            if (!Character.isLetterOrDigit(indirizzo.charAt(i))) {
                throw new IllegalArgumentException(ID_NON_VALIDO);
            }
        }
    }

    public void controllaLista(List<?> listaCategorie){
        if (Objects.isNull(listaCategorie))
            throw new NullPointerException("La lista delle categorie del prodotto e' nulla");
        if (listaCategorie.isEmpty())
            throw new IllegalArgumentException("La lista delle categorie del prodotto e' vuota");
    }

    public void controllaData(GregorianCalendar data){
        if (Objects.isNull(data))
            throw new NullPointerException();
        if (data.compareTo(new GregorianCalendar()) < 0)
            throw new IllegalArgumentException();

    }
}
