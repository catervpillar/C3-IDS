package it.unicam.cs.ids.c3.model;

import java.util.List;

public class Commerciante extends Utente {
    private String ragioneSociale;
    private String indirizzo;
    private List<CategoriaProdotto> listaCategorie;

    public Commerciante(String username, String password, String email, String telephone, String ragioneSociale, String indirizzo, List<CategoriaProdotto> listaCategorie) {
        super(username, password, email, telephone);
        this.ragioneSociale = ragioneSociale;
        this.indirizzo = indirizzo;
        this.listaCategorie = listaCategorie;
    }

    public String getRagioneSociale() {
        return ragioneSociale;
    }

    public void setRagioneSociale(String ragioneSociale) {
        this.ragioneSociale = ragioneSociale;
    }

    public String getIndirizzo() {
        return indirizzo;
    }

    public void setIndirizzo(String indirizzo) {
        this.indirizzo = indirizzo;
    }

    public List<CategoriaProdotto> getListaCategorie() {
        return listaCategorie;
    }

    public void setListaCategorie(List<CategoriaProdotto> listaCategorie) {
        this.listaCategorie = listaCategorie;
    }
}