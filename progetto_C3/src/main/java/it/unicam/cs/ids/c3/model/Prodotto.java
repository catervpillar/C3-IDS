package it.unicam.cs.ids.c3.model;

import it.unicam.cs.ids.c3.utilities.Controllore;

import java.util.List;
import java.util.Objects;

public class Prodotto implements HasID {
    private String ID;
    private String nome;
    private String IDCommerciante;
    private List<CategoriaProdotto> listaCategorie;
    private double prezzo;

    public Prodotto(String nome, String IDCommerciante, List<CategoriaProdotto> listaCategorie, double prezzo) {
        IDGenerator.generateID(this);
        this.nome = nome;
        this.IDCommerciante = IDCommerciante;
        this.listaCategorie = listaCategorie;
        this.prezzo = prezzo;
    }

    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        Controllore.getInstance().controllaID(ID);
        this.ID = ID;
    }

    public double getPrezzo() {
        return prezzo;
    }

    public void setPrezzo(double prezzo) {
        if (prezzo < 0)
            throw new IllegalArgumentException("Il prezzo del prodotto e' nullo");
        this.prezzo = prezzo;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        Controllore.getInstance().controllaStringa(nome);
        this.nome = nome;
    }

    public String getIDCommerciante() {
        return IDCommerciante;
    }

    public void setIDCommerciante(String IDCommerciante) {
        Controllore.getInstance().controllaID(IDCommerciante);
        this.IDCommerciante = IDCommerciante;
    }

    public List<CategoriaProdotto> getListaCategorie() {
        return listaCategorie;
    }

    public void setListaCategorie(List<CategoriaProdotto> listaCategorie) {
        Controllore.getInstance().controllaLista(listaCategorie);
        this.listaCategorie = listaCategorie;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Prodotto prodotto = (Prodotto) o;
        return ID.equals(prodotto.ID);
    }

    @Override
    public int hashCode() {
        return Objects.hash(ID);
    }
}
