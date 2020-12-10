package it.unicam.cs.ids.c3.model;

public class Tracking {
    private int ID;
    private StatoTracking stato;
    private Ritiro ritiro;

    public Tracking(int ID, Ritiro ritiro) {
        this.ID = ID;
        this.ritiro = ritiro;
        this.stato = StatoTracking.IN_ELABORAZIONE;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public StatoTracking getStato() {
        return stato;
    }

    public void setStato(StatoTracking stato) {
        this.stato = stato;
    }

    public Ritiro getRitiro() {
        return ritiro;
    }

    public void setRitiro(Ritiro ritiro) {
        this.ritiro = ritiro;
    }
}
