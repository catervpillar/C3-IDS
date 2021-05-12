package it.unicam.cs.ids.c3.utilities;

import it.unicam.cs.ids.c3.ritiro.Ritiro;
import java.util.ArrayList;
import java.util.List;

/**
 * Questa classe e' un singleton e si occupa di generare i codici di ritiro per ogni {@link Ritiro}.
 */
public final class CodiciRitiroGenerator {
    private static CodiciRitiroGenerator instance;
    private final List<String> codici = new ArrayList<>();

    /**
     * Costruttore privato usato solamente all'interno di questa classe.
     */
    private CodiciRitiroGenerator() {
    }

    /**
     * Metodo getter per l'attributo instance. Se instance è nulla, viene inizializzata.
     *
     * @return l'attributo instance.
     */
    public static CodiciRitiroGenerator getInstance() {
        if (instance == null)
            instance = new CodiciRitiroGenerator();
        return instance;
    }

    /**
     * Genera un nuovo codice alfanumerico di 6 cifre.
     *
     * @return il nuovo codice di ritiro.
     */
    private String getAlphanumericString() {
        StringBuilder sb = new StringBuilder(6);
        String alphanumericString = "ABCDEFGHIJKLMNOPQRSTUVWXYZ" + "0123456789";
        for (int i = 0; i < 6; i++) {
            int index = (int) (alphanumericString.length() * Math.random());
            sb.append(alphanumericString.charAt(index));
        }

        return sb.toString();
    }

    /**
     * Fa generare un nuovo codice di ritiro finché non ne viene generato uno non presente nella lista
     * di quelli gia' generati.
     *
     * @return il nuovo codice di ritiro univoco.
     */
    public String generaCodice() {
        String codice;
        do {
            codice = getAlphanumericString();
        } while (codici.contains(codice));
        codici.add(codice);
        return codice;
    }
}
