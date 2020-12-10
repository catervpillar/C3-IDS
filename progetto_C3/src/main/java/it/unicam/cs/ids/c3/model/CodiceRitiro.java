package it.unicam.cs.ids.c3.model;

public class CodiceRitiro {

    private String codice;
    private Ritiro ritiro;

    public CodiceRitiro(String codice, Ritiro ritiro) {
        this.codice = codice;
        this.ritiro = ritiro;
    }

    public String getCodice() {
        return codice;
    }

    public void setCodice(String codice) {
        this.codice = codice;
    }

    public Ritiro getRitiro() {
        return ritiro;
    }

    public void setRitiro(Ritiro ritiro) {
        this.ritiro = ritiro;
    }
}
