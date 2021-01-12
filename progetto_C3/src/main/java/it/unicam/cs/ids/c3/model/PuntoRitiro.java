package it.unicam.cs.ids.c3.model;

import it.unicam.cs.ids.c3.utilities.Controllore;

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
        Controllore.getInstance().controllaStringa(ragioneSociale);
        this.ragioneSociale = ragioneSociale;
    }

    public String getIndirizzo() {
        return indirizzo;
    }

    public void setIndirizzo(String indirizzo) {
        Controllore.getInstance().controllaIndirizzo(indirizzo);
        this.indirizzo = indirizzo;
    }
}