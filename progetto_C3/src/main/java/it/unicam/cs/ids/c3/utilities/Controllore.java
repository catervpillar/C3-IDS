package it.unicam.cs.ids.c3.utilities;

import java.util.Objects;

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
        //TODO
        if (email.endsWith("@gmail.com")) throw new IllegalArgumentException();
    }
}
