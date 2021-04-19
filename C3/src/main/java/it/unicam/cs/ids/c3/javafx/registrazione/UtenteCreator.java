package it.unicam.cs.ids.c3.javafx.registrazione;

import javafx.scene.control.Button;
import javafx.scene.control.TextField;

public interface UtenteCreator {

    default void controllaContenutoTextField(TextField textField, Button procediButton) {
        textField.textProperty().addListener((observable, oldValue, newValue) -> {
            if (textField.getText().isBlank()) procediButton.setDisable(true);
        });
    }

    default void confrontaPassword(TextField passwordTextField, TextField confermaPasswordTextField) {
        if (!passwordTextField.getText().equals(confermaPasswordTextField.getText()))
            throw new IllegalStateException("Le due password non coincidono");
    }
}
