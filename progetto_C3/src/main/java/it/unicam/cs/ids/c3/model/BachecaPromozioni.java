package it.unicam.cs.ids.c3.model;

import java.util.ArrayList;
import java.util.List;

public final class BachecaPromozioni {
    private final List<Promozione> promozioniAttive = new ArrayList<>();
    private static BachecaPromozioni instance;

    public static BachecaPromozioni getInstance() {
        if (instance == null)
            instance = new BachecaPromozioni();
        return instance;
    }

    public List<Promozione> getPromozioniAttive() {
        return promozioniAttive;
    }
}
