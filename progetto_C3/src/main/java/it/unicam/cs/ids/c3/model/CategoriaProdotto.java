package it.unicam.cs.ids.c3.model;

import it.unicam.cs.ids.c3.utilities.Controllore;

import java.util.Objects;

public class CategoriaProdotto implements HasID{
    private String ID;
    private String nome;
    private String descrizione;

    public CategoriaProdotto(String nome, String descrizione) {
        IDGenerator.generateID(this);
        this.nome = nome;
        this.descrizione = descrizione;
    }

    public String getID() { return ID;
    }

    public void setID(String ID) {
        Controllore.getInstance().controllaID(ID);
        this.ID = ID;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        Controllore.getInstance().controllaStringa(nome);
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
        if (o == null || getClass() != o.getClass()) return false;
        CategoriaProdotto that = (CategoriaProdotto) o;
        return ID.equals(that.ID) && nome.equals(that.nome);
    }

    @Override
    public int hashCode() {
        return Objects.hash(ID, nome);
    }
}