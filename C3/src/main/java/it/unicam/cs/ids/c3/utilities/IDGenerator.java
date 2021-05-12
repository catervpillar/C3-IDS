package it.unicam.cs.ids.c3.utilities;

import java.util.UUID;

/**
 * Questa classe ha il compito di generare gli ID univoci che andranno ad identificare pressoche' ogni oggetto.
 */
public final class IDGenerator {
    /**
     * Genera un identificativo alfanumerico univoco di 8 cifre e lo imposta come ID dell'oggetto passato in input.
     *
     * @param hasID - L'oggetto a cui impostare l'ID.
     */
    public static void generateID(HasID hasID) {
        hasID.setID(UUID.randomUUID().toString().substring(0, 8));
    }
}
