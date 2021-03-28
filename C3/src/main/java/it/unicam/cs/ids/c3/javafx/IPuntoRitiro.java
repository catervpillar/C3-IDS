package it.unicam.cs.ids.c3.javafx;

import java.util.Objects;

public class IPuntoRitiro implements JavaFXController {
    private static IPuntoRitiro instance;

    private IPuntoRitiro() {
    }

    public static IPuntoRitiro getInstance() {
        if (Objects.isNull(instance))
            instance = new IPuntoRitiro();
        return instance;
    }


}
