package it.unicam.cs.ids.c3.javafx.registrazione;

import it.unicam.cs.ids.c3.javafx.JavaFXController;
import it.unicam.cs.ids.c3.utenti.cliente.ControllerCliente;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Objects;

public class ClienteCreator implements JavaFXController, UtenteCreator {
    private static ClienteCreator instance;

    private ClienteCreator() {
    }

    public static ClienteCreator getInstance() {
        if (Objects.isNull(instance))
            instance = new ClienteCreator();
        return instance;
    }

    @FXML
    TextField nomeTextField;
    @FXML
    TextField cognomeTextField;
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
            ControllerCliente.getInstance().creaCliente(usernameTextField.getText(),
                    passwordTextField.getText(), emailTextField.getText(),
                    nomeTextField.getText(),
                    cognomeTextField.getText());
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

        controllaContenutoTextField(nomeTextField, procediButton);
        controllaContenutoTextField(cognomeTextField, procediButton);
        controllaContenutoTextField(emailTextField, procediButton);
        controllaContenutoTextField(usernameTextField, procediButton);
        controllaContenutoTextField(passwordTextField, procediButton);
        controllaContenutoTextField(confermaPasswordTextField, procediButton);

    }
}
