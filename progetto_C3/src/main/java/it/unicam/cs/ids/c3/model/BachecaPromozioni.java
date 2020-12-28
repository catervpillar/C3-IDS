package it.unicam.cs.ids.c3.model;

import java.util.ArrayList;
import java.util.List;

public class BachecaPromozioni {


    private List<Promozione> bacheca = new ArrayList<>();

    public List<Promozione> getBacheca() {
        return bacheca;
    }

    public void setBacheca(List<Promozione> bacheca) {
        this.bacheca = bacheca;
    }
}
