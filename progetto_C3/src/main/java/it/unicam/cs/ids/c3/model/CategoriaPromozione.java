package it.unicam.cs.ids.c3.model;

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
}
