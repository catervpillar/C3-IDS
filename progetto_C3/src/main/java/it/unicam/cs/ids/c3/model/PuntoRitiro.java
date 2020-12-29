package it.unicam.cs.ids.c3.model;

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
        this.ragioneSociale = ragioneSociale;
    }

    public String getIndirizzo() {
        return indirizzo;
    }

    public void setIndirizzo(String indirizzo) {
        this.indirizzo = indirizzo;
    }
}