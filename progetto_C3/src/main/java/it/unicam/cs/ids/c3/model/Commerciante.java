package it.unicam.cs.ids.c3.model;


import java.util.List;

public class Commerciante extends Utente {
    private String ragioneSociale;
    private List<CategoriaProdotto> listaCategorie;

    public Commerciante(String username, String password, String email, String ragioneSociale, List<CategoriaProdotto> listaCategorie) {
        super(username, password, email);
        this.ragioneSociale = ragioneSociale;
        this.listaCategorie = listaCategorie;
    }

    public String getRagioneSociale() {
        return ragioneSociale;
    }

    public void setRagioneSociale(String ragioneSociale) {
        this.ragioneSociale = ragioneSociale;
    }

    public List<CategoriaProdotto> getListaCategorie() {
        return listaCategorie;
    }

    public void setListaCategorie(List<CategoriaProdotto> listaCategorie) {
        this.listaCategorie = listaCategorie;
    }
}