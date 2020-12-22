package it.unicam.cs.ids.c3.model;

import java.util.List;
import java.util.Objects;

public class Prodotto implements HasID{
    private int ID;
    private String nome;
    private Commerciante commerciante;
    private List<CategoriaProdotto> listaCategorie;
    private double prezzo;


    public Prodotto(int ID, String nome, Commerciante commerciante, List<CategoriaProdotto> listaCategorie, double prezzo) {
        this.ID = ID;
        this.nome = nome;
        this.commerciante = commerciante;
        this.listaCategorie = listaCategorie;
        this.prezzo = prezzo;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public double getPrezzo() {
        return prezzo;
    }

    public void setPrezzo(double prezzo) {
        this.prezzo = prezzo;
    }

    public String getNome() { return nome; }

    public void setNome(String nome) {  this.nome = nome; }

    public Commerciante getCommerciante() {
        return commerciante;
    }

    public void setCommerciante(Commerciante commerciante) {
        this.commerciante = commerciante;
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
