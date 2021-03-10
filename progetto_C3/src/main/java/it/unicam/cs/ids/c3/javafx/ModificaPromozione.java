package it.unicam.cs.ids.c3.javafx;

import javafx.fxml.Initializable;

import java.net.URL;
import java.util.Objects;
import java.util.ResourceBundle;

public class ModificaPromozione implements Initializable, JavaFXController {
    private static ModificaPromozione instance;

    private ModificaPromozione() {
    }

    public static ModificaPromozione getInstance() {
        if (Objects.isNull(instance))
            instance = new ModificaPromozione();
        return instance;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }
}
