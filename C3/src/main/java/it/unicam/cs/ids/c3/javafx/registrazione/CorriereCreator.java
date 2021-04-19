package it.unicam.cs.ids.c3.javafx.registrazione;

import it.unicam.cs.ids.c3.javafx.JavaFXController;
import it.unicam.cs.ids.c3.utenti.corriere.ControllerCorriere;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Objects;

public class CorriereCreator implements JavaFXController, UtenteCreator {
    private static CorriereCreator instance;

    private CorriereCreator() {
    }

    public static CorriereCreator getInstance() {
        if (Objects.isNull(instance))
            instance = new CorriereCreator();
        return instance;
    }

    @FXML
    TextField ragioneSocialeTextField;
    @FXML
    TextField emailTextField;
    @FXML
    TextField usernameTextField;
    @FXML
    PasswordField passwordTextField;
    @FXML
    PasswordField confermaPasswordTextField;

    @FXML
    Button procediButton;
    @FXML
    Button annullaButton;

    @FXML
    public void procedi() {
        try {
            confrontaPassword(passwordTextField, confermaPasswordTextField);
            ControllerCorriere.getInstance().creaCorriere(usernameTextField.getText(),
                    passwordTextField.getText(), emailTextField.getText(),
                    ragioneSocialeTextField.getText());
            close(procediButton);

        } catch (IllegalArgumentException | IllegalStateException | SQLException e) {
            createErrorAlert(e.getMessage());
        }
    }

    @FXML
    public void annulla() throws IOException {
        close(annullaButton);
        startWindow("Registrati", "/tipoAccount.fxml", AccountTypePicker.getInstance());
    }


    @FXML
    private void controllaCampiCompilati() {
                controllaContenutoTextField(ragioneSocialeTextField, procediButton);
        controllaContenutoTextField(emailTextField, procediButton);
        controllaContenutoTextField(usernameTextField, procediButton);
        controllaContenutoTextField(passwordTextField, procediButton);
        controllaContenutoTextField(confermaPasswordTextField, procediButton);

    }

}
