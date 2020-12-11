package it.unicam.cs.ids.c3.model;

import java.util.Objects;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Tracking)) return false;
        Tracking tracking = (Tracking) o;
        return ID == tracking.ID;
    }

    @Override
    public int hashCode() {
        return Objects.hash(ID);
    }
}
