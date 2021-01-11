package it.unicam.cs.ids.c3.model;

import java.util.Objects;

public class PuntoRitiro extends Utente {
    private String ragioneSociale;
    private String indirizzo;

    public PuntoRitiro(String username, String password, String email, String telephone, String ragioneSocial, String address) {
        super(username, password, email, telephone);
        this.ragioneSociale = ragioneSocial;
        this.indirizzo = address;
    }

    public String getRagioneSociale() {
        return ragioneSociale;
    }

    public void setRagioneSociale(String ragioneSociale) {
        if (Objects.isNull(ragioneSociale))
            throw new NullPointerException("La ragione sociale e' nulla");
        if (ragioneSociale.equals(""))
            throw new NullPointerException("La ragione sociale non e' valida");
        this.ragioneSociale = ragioneSociale;
    }

    public String getIndirizzo() {
        return indirizzo;
    }

    public void setIndirizzo(String indirizzo) {
        if (Objects.isNull(indirizzo))
            throw new NullPointerException("L'indirizzo sociale e' nulla");
        if (indirizzo.equals(""))
            throw new NullPointerException("L'indirizzo sociale non e' valida");
        this.indirizzo = indirizzo;
    }
}