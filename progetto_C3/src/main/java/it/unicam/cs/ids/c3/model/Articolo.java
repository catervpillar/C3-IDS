package it.unicam.cs.ids.c3.model;


import java.util.List;
import java.util.Objects;

public class Articolo implements HasID {
    private String ID;
    private String nome;
    private List<CategoriaProdotto> listaCategorie;


    public Articolo(String nome, List<CategoriaProdotto> listaCategorie) {
        IDGenerator.generateID(this);
        this.nome = nome;
        this.listaCategorie = listaCategorie;
    }

    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public List<CategoriaProdotto> getListaCategorie() {
        return listaCategorie;
    }

    public void setListaCategorie(List<CategoriaProdotto> listaCategorie) {
        this.listaCategorie = listaCategorie;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Articolo articolo = (Articolo) o;
        return ID.equals(articolo.ID) && nome.equals(articolo.nome);
    }

    @Override
    public int hashCode() {
        return Objects.hash(ID, nome);
    }
}
