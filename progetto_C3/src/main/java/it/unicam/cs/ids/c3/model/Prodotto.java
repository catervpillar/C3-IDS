package it.unicam.cs.ids.c3.model;

import java.util.List;
import java.util.Objects;

public class Prodotto implements HasID{
    private int ID;
    private String nome;
    private List<Commerciante> listaCommercianti;
    private List<CategoriaProdotto> listaCategorie;

    public Prodotto(int ID, String nome, List<Commerciante> listaCommercianti, List<CategoriaProdotto> listaCategorie) {
        this.ID = ID;
        this.nome = nome;
        this.listaCommercianti = listaCommercianti;
        this.listaCategorie = listaCategorie;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getNome() { return nome; }

    public void setNome(String nome) {  this.nome = nome; }

    public List<Commerciante> getListaCommercianti() {
        return listaCommercianti;
    }

    public void setListaCommercianti(List<Commerciante> listaCommercianti) {
        this.listaCommercianti = listaCommercianti;
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
        if (!(o instanceof Prodotto)) return false;
        Prodotto prodotto = (Prodotto) o;
        return ID == prodotto.ID;
    }

    @Override
    public int hashCode() {
        return Objects.hash(ID);
    }

}
