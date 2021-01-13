package it.unicam.cs.ids.c3.javafx;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

import java.io.IOException;

public class loginC3Controller implements JavaFXController {

    @FXML
    Button loginButton;
    @FXML
    Hyperlink registratiHyperlink;

    @FXML
    TextField usernameTextField;
    @FXML
    PasswordField passwordTextField;

    @FXML
    Hyperlink recuperaPasswordHyperlink;

    @FXML
    public void accedi() {

    }

    @FXML
    public void registrati() throws IOException {
        startWindow("Registrati", "/tipoAccount.fxml", new AccountTypePicker());
    }

    @FXML
    public void recuperaPassword() {

    }
}
