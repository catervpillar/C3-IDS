package it.unicam.cs.ids.c3.model;

import java.util.UUID;

public final class IDGenerator {
    public static void generateID(HasID hasID) {
        hasID.setID(UUID.randomUUID().toString().substring(0, 8));
    }
}
