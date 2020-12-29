package it.unicam.cs.ids.c3.model;

public interface HasID {
    String getID();
    void setID(String ID);
    default String generaID(){
        return "ciao";
    }
}
