package it.unicam.cs.ids.c3.model;


public class PuntoRitiro extends Utente {
    private String ragioneSociale;

    public PuntoRitiro(String username, String password, String email, String ragioneSociale) {
        super(username, password, email);
        this.ragioneSociale = ragioneSociale;
    }

    public String getRagioneSociale() {
        return ragioneSociale;
    }

    public void setRagioneSociale(String ragioneSociale) {
        this.ragioneSociale = ragioneSociale;
    }
}