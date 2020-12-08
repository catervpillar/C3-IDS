package it.unicam.cs.ids.c3.model;

import java.util.List;

public class Dealer extends User {
    private String ragioneSociale;
    private String indirizzo;
    private List<ProductCategory> categoryList;

    public Dealer(int ID, String username, String password, String email, String telephone, String ragioneSociale, String indirizzo, List<ProductCategory> categoryList) {
        super(ID, username, password, email, telephone);
        this.ragioneSociale = ragioneSociale;
        this.indirizzo = indirizzo;
        this.categoryList = categoryList;
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

    public List<ProductCategory> getCategoryList() {
        return categoryList;
    }

    public void setCategoryList(List<ProductCategory> categoryList) {
        this.categoryList = categoryList;
    }
}