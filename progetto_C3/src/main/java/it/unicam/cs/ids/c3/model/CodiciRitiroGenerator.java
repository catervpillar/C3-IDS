package it.unicam.cs.ids.c3.model;

import java.util.List;

public final class CodiciRitiroGenerator {

    private static CodiciRitiroGenerator codiciRitiroGenerator;
    private CodiciRitiroGenerator(){}
    private List<String> codici;

    private String alphanumericString = "ABCDEFGHIJKLMNOPQRSTUVWXYZ" + "0123456789";


    public static CodiciRitiroGenerator getInstance(){
        if (codiciRitiroGenerator==null)
            codiciRitiroGenerator = new CodiciRitiroGenerator();
        return codiciRitiroGenerator;
    }

    private String getAlphanumericString(){
        StringBuilder sb = new StringBuilder(6);
        for (int i=0; i<6; i++){
            int index = (int) (alphanumericString.length() * Math.random());
            sb.append(alphanumericString.charAt(index));
        }

        return sb.toString();
    }

    public String generaCodice(){
        String codice;

        do {
            codice = getAlphanumericString();
        }while(codici.contains(codice));
        codici.add(codice);
        return codice;
    }



}
