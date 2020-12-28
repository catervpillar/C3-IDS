package it.unicam.cs.ids.c3.model;

import java.util.ArrayList;
import java.util.List;

public final class BachecaPromozioni {


    private List<Promozione> promozioniAttive = new ArrayList<>();
    private static BachecaPromozioni instace;

    public static BachecaPromozioni getInstance(){
        if (instace == null)
            instace = new BachecaPromozioni();
        return instace;
    }

    public List<Promozione> getPromozioniAttive() {
        return promozioniAttive;
    }

    public void setPromozioniAttive(List<Promozione> promozioniAttive) {
        this.promozioniAttive = promozioniAttive;
    }
}
