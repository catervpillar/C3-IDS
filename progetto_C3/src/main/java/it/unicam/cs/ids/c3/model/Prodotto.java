package it.unicam.cs.ids.c3.model;

import java.util.Objects;

public class Prodotto implements HasID {
    private String ID;
    private String nome;
    private double prezzo;
    private int quantita;
    private String IDCommerciante;


    public Prodotto(String nome, double prezzo, int quantita, String IDCommerciante) {
        IDGenerator.generateID(this);
        this.nome = nome;
        this.prezzo = prezzo;
        this.quantita = quantita;
        this.IDCommerciante = IDCommerciante;
    }

    public Prodotto(String ID, String nome, double prezzo, int quantita, String IDCommerciante) {
        this.ID = ID;
        this.nome = nome;
        this.prezzo = prezzo;
        this.quantita = quantita;
        this.IDCommerciante = IDCommerciante;
    }

    @Override
    public String getID() {
        return ID;
    }

    @Override
    public void setID(String ID) {
        this.ID = ID;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getIDCommerciante() {
        return IDCommerciante;
    }

    public void setIDCommerciante(String IDCommerciante) {
        this.IDCommerciante = IDCommerciante;
    }

    public double getPrezzo() {
        return prezzo;
    }

    public void setPrezzo(double prezzo) {
        this.prezzo = prezzo;
    }

    public int getQuantita() {
        return quantita;
    }

    public void setQuantita(int quantita) {
        this.quantita = quantita;
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

    @Override
    public String toString() {
        return "Prodotto{" +
                "ID='" + ID + '\'' +
                ", nome='" + nome + '\'' +
                ", prezzo=" + prezzo +
                ", quantita=" + quantita +
                ", IDCommerciante='" + IDCommerciante + '\'' +
                '}';
    }
}
