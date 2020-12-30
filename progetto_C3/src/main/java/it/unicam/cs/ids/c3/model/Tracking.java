package it.unicam.cs.ids.c3.model;

import java.util.Objects;

public class Tracking implements HasID {
    private String ID;
    private StatoTracking stato;
    private String IDRitiro;

    public Tracking(String IDRitiro) {
        IDGenerator.generateID(this);
        this.IDRitiro = IDRitiro;
        this.stato = StatoTracking.IN_ELABORAZIONE;
    }

    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public StatoTracking getStato() {
        return stato;
    }

    public void setStato(StatoTracking stato) {
        this.stato = stato;
    }

    public String getIDRitiro() {
        return IDRitiro;
    }

    public void setIDRitiro(String IDritiro) {
        this.IDRitiro = IDritiro;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Tracking tracking = (Tracking) o;
        return ID.equals(tracking.ID);
    }

    @Override
    public int hashCode() {
        return Objects.hash(ID);
    }
}
