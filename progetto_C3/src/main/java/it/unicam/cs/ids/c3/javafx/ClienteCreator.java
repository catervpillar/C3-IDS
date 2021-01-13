package it.unicam.cs.ids.c3.javafx;

import it.unicam.cs.ids.c3.controller.ControllerCliente;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

import java.io.IOException;

public class ClienteCreator implements JavaFXController {
    ControllerCliente controllerCliente;

    public ClienteCreator(ControllerCliente controllerCliente) {
        this.controllerCliente = controllerCliente;
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
            controllerCliente.creaCliente(usernameTextField.getText(),
                    passwordTextField.getText(), emailTextField.getText(),
                    nomeTextField.getText(),
                    cognomeTextField.getText());
            close(procediButton);
        } catch (IllegalArgumentException | IllegalStateException e) {
            createErrorAlert(e.getMessage());
        }
    }

    @FXML
    public void annulla() throws IOException {
        close(annullaButton);
        startWindow("Registrati", "/tipoAccount.fxml", new AccountTypePicker());
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
