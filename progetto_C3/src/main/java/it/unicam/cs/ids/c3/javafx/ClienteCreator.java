package it.unicam.cs.ids.c3.javafx;

import it.unicam.cs.ids.c3.controller.ControllerCliente;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Objects;

public class ClienteCreator implements JavaFXController {
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
            confrontaPassword();
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

    private void confrontaPassword() {
        if (!passwordTextField.getText().equals(confermaPasswordTextField.getText()))
            throw new IllegalStateException("Le due password non coincidono");
    }

    @FXML
    private void controllaCampiCompilati() {
        boolean sblocca = true;
        if (nomeTextField.getText().isEmpty()) sblocca = false;
        if (cognomeTextField.getText().isEmpty()) sblocca = false;
        if (emailTextField.getText().isEmpty()) sblocca = false;
        if (usernameTextField.getText().isEmpty()) sblocca = false;
        if (passwordTextField.getText().isEmpty()) sblocca = false;
        if (confermaPasswordTextField.getText().isEmpty()) sblocca = false;
        if (sblocca)
            procediButton.setDisable(false);

        controllaContenutoTextField(nomeTextField);
        controllaContenutoTextField(cognomeTextField);
        controllaContenutoTextField(emailTextField);
        controllaContenutoTextField(usernameTextField);
        controllaContenutoTextField(passwordTextField);
        controllaContenutoTextField(confermaPasswordTextField);

    }

    public void controllaContenutoTextField(TextField textField) {
        textField.textProperty().addListener((observable, oldValue, newValue) -> {
            if (textField.getText().isEmpty()) procediButton.setDisable(true);
        });
    }
}
