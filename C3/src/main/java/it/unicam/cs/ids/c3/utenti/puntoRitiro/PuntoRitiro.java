package it.unicam.cs.ids.c3.utenti.puntoRitiro;


import it.unicam.cs.ids.c3.utenti.Utente;

public class PuntoRitiro extends Utente implements PuntoRitiroInterface {
    private String ragioneSociale;

    public PuntoRitiro(String username, String password, String email, String ragioneSociale) {
        super(username, password, email);
        this.ragioneSociale = ragioneSociale;
    }

    public PuntoRitiro(String ID, String username, String password, String email, String ragioneSociale) {
        super(ID, username, password, email);
        this.ragioneSociale = ragioneSociale;
    }

    public String getRagioneSociale() {
        return ragioneSociale;
    }

    public void setRagioneSociale(String ragioneSociale) {
        this.ragioneSociale = ragioneSociale;
    }

    @Override
    public String toString() {
        return ragioneSociale;
    }
}