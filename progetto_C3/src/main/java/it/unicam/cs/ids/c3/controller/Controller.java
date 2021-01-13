package it.unicam.cs.ids.c3.controller;

import it.unicam.cs.ids.c3.utilities.Controllore;

public interface Controller {
    default void creaIndirizzo(String via, int numeroCivico, String citta, String CAP, String provincia, String stato) {
        Controllore.getInstance().controllaIndirizzo(via, numeroCivico, citta, CAP, provincia, stato);

    }
}
