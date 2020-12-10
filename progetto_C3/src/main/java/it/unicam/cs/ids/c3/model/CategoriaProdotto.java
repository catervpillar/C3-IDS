package it.unicam.cs.ids.c3.model;

public class CategoriaProdotto {
    private int ID;
    private String nome;
    private String descrizione;

    public CategoriaProdotto(int ID, String nome, String descrizione) {
        this.ID = ID;
        this.nome = nome;
        this.descrizione = descrizione;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDescrizione() {
        return descrizione;
    }

    public void setDescrizione(String descrizione) {
        this.descrizione = descrizione;
    }
}