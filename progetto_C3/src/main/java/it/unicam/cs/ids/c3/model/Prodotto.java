package it.unicam.cs.ids.c3.model;

import java.util.List;
import java.util.Objects;

public class Prodotto {
    private int ID;
    private List<Commerciante> listaCommercianti;
    private List<CategoriaProdotto> listaCategorie;

    public Prodotto(int ID, List<Commerciante> listaCommercianti, List<CategoriaProdotto> listaCategorie) {
        this.ID = ID;
        this.listaCommercianti = listaCommercianti;
        this.listaCategorie = listaCategorie;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

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

    public void addCategoria(CategoriaProdotto categoriaProdotto){
        if(Objects.isNull(categoriaProdotto))
            throw new NullPointerException("categoria nulla");
        if(listaCategorie.contains(categoriaProdotto))
            throw new IllegalArgumentException("categoria gia' inserita");
        listaCategorie.add(categoriaProdotto);
    }

    public void addCommerciante(Commerciante commerciante){
        if(Objects.isNull(commerciante))
            throw new NullPointerException("commerciante nullo");
        if(listaCategorie.contains(commerciante))
            throw new IllegalArgumentException("commerciante gia' inserito");
        listaCommercianti.add(commerciante);
    }

}
