package it.unicam.cs.ids.c3.model;

import it.unicam.cs.ids.c3.utilities.Controllore;

import java.util.Objects;

public class Corriere extends Utente {
    private String ragioneSociale;
    private StatoCorriere stato;

    public Corriere(String username, String password, String email, String telephone, String ragioneSociale) {
        super(username, password, email, telephone);
        this.ragioneSociale = ragioneSociale;
        this.stato = StatoCorriere.NON_DISPONIBILE;
    }

    public String getRagioneSociale() {
        return ragioneSociale;
    }

    public void setRagioneSociale(String ragioneSociale) {
        Controllore.getInstance().controllaStringa(ragioneSociale);
        this.ragioneSociale = ragioneSociale;
    }

    public StatoCorriere getStato() {
        return stato;
    }

    public void setStato(StatoCorriere stato) {
        if(Objects.isNull(stato))
            throw new NullPointerException();
        this.stato = stato;
    }
}