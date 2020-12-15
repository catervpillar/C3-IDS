package it.unicam.cs.ids.c3.model;

import java.util.Objects;

public class CategoriaProdotto implements HasID{
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof CategoriaProdotto)) return false;
        CategoriaProdotto that = (CategoriaProdotto) o;
        return ID == that.ID &&
                nome.equals(that.nome);
    }

    @Override
    public int hashCode() {
        return Objects.hash(ID, nome);
    }
}