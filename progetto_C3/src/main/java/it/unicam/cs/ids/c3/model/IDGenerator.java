package it.unicam.cs.ids.c3.model;

import java.time.Instant;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

public final class IDGenerator {

    public static void generateID(HasID hasID) {
        hasID.setID(UUID.randomUUID().toString().substring(0, 8));
    }
}
