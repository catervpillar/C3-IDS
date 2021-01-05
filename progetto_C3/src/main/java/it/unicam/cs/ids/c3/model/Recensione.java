package it.unicam.cs.ids.c3.model;

public class Recensione implements HasID{

    private String ID;
    private String descrizione;

    public Recensione(String descrizione){
        IDGenerator.generateID(this);
        this.descrizione = descrizione;
    }

    @Override
    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }
}
