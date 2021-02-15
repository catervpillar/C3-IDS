package it.unicam.cs.ids.c3.model;

public class ProdottoInVendita implements HasID{

    private String IDArticolo;
    private String IDCommerciante;
    private double prezzo;
    private int quantita;
    private String ID;

    public ProdottoInVendita(String IDArticolo, String IDCommerciante, double prezzo, int quantita) {
        this.IDArticolo = IDArticolo;
        this.IDCommerciante = IDCommerciante;
        this.prezzo = prezzo;
        this.quantita = quantita;
        IDGenerator.generateID(this);
    }

    @Override
    public String getID() {
        return ID;
    }

    @Override
    public void setID(String ID) {
        this.ID = ID;
    }

    public String getIDArticolo() {
        return IDArticolo;
    }

    public void setIDArticolo(String IDArticolo) {
        this.IDArticolo = IDArticolo;
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
}
