package it.unicam.cs.ids.c3.model;

import java.util.Objects;

public class CategoriaPromozione {
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
