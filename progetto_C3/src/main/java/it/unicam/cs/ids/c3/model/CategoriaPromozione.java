package it.unicam.cs.ids.c3.model;

import java.util.Objects;

public class CategoriaPromozione implements HasID{
    private int ID;
    private String descrizione, nome;

    public CategoriaPromozione(int ID, String descrizione, String nome) {
        this.ID = ID;
        this.descrizione = descrizione;
        this.nome = nome;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    @Override
    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
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
        if (!(o instanceof CategoriaPromozione)) return false;
        CategoriaPromozione that = (CategoriaPromozione) o;
        return ID == that.ID &&
                nome.equals(that.nome);
    }

    @Override
    public int hashCode() {
        return Objects.hash(ID, nome);
    }
}
