package it.unicam.cs.ids.c3.utilities;

import java.util.ArrayList;
import java.util.List;

public final class CodiciRitiroGenerator {
    private static CodiciRitiroGenerator instance;
    private final List<String> codici = new ArrayList<>();

    private CodiciRitiroGenerator() {
    }

    public static CodiciRitiroGenerator getInstance() {
        if (instance == null)
            instance = new CodiciRitiroGenerator();
        return instance;
    }

    private String getAlphanumericString() {
        StringBuilder sb = new StringBuilder(6);
        String alphanumericString = "ABCDEFGHIJKLMNOPQRSTUVWXYZ" + "0123456789";
        for (int i = 0; i < 6; i++) {
            int index = (int) (alphanumericString.length() * Math.random());
            sb.append(alphanumericString.charAt(index));
        }

        return sb.toString();
    }

    public String generaCodice() {
        String codice;
        do {
            codice = getAlphanumericString();
        } while (codici.contains(codice));
        codici.add(codice);
        return codice;
    }
}
