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
        if (Objects.isNull(ID))
            throw new NullPointerException(ID_NULLO);
        this.ID = ID;
    }

    public StatoTracking getStato() {
        return stato;
    }

    public void setStato(StatoTracking stato) {
        if (Objects.isNull(stato))
            throw new NullPointerException("Lo stato del tracking e' nullo");
        this.stato = stato;
    }

    public String getIDRitiro() {
        return IDRitiro;
    }

    public void setIDRitiro(String IDRitiro) {
        if (Objects.isNull(IDRitiro))
            throw new NullPointerException("L'ID del ritiro del tracking e' nullo");
        this.IDRitiro = IDRitiro;
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
