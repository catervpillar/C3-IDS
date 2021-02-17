package it.unicam.cs.ids.c3.model;


public class Commerciante extends Utente {
    private String ragioneSociale;

    public Commerciante(String username, String password, String email, String ragioneSociale) {
        super(username, password, email);
        this.ragioneSociale = ragioneSociale;
    }

    public Commerciante(String ID, String username, String password, String email, String ragioneSociale) {
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
        return super.toString() + "Commerciante{" +
                "ragioneSociale='" + ragioneSociale + '\'' +
                '}';
    }
}