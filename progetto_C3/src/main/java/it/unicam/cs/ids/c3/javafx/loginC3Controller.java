package it.unicam.cs.ids.c3.javafx;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;

import java.io.IOException;

public class loginC3Controller implements JavaFXController {

    @FXML
    Button loginButton;
    @FXML
    Hyperlink registratiHyperlink;

    @FXML
    Text erroreLoginText;
    @FXML
    TextField usernameTextField;
    @FXML
    TextField passwordVisibleText;
    @FXML
    PasswordField passwordField;

    @FXML
    Hyperlink recuperaPasswordHyperlink;

    @FXML
    CheckBox mostraPasswordCheckBox;

    @FXML
    public void accedi() {
        if (!controllaCampi())
            mostraErroreLogin();
        else {
            usernameTextField.setStyle("-fx-background-color: #478C64");
            passwordField.setStyle("-fx-background-color: #478C64");
            passwordVisibleText.setStyle("-fx-background-color: #478C64");
            erroreLoginText.setText("TUTTO OK!");
            erroreLoginText.setVisible(true);
        }
    }

    @FXML
    public void registrati() throws IOException {
        startWindow("Registrati", "/tipoAccount.fxml", new AccountTypePicker());
    }

    @FXML
    public void mostraPassword() {
        if (mostraPasswordCheckBox.isSelected()) {
            passwordVisibleText.setText(passwordField.getText());
            passwordVisibleText.setVisible(true);
            passwordField.setVisible(false);
            return;
        }
        passwordField.setText(passwordVisibleText.getText());
        passwordField.setVisible(true);
        passwordVisibleText.setVisible(false);
    }

    private void mostraErroreLogin() {
        erroreLoginText.setVisible(true);
        if (usernameTextField.getText().isEmpty())
            usernameTextField.setStyle("-fx-background-color: #923F55");
        if (passwordField.getText().isEmpty()) {
            passwordField.setStyle("-fx-background-color: #923F55");
            passwordVisibleText.setStyle("-fx-background-color: #923F55");
        }
    }

    private boolean controllaCampi() {
        if (mostraPasswordCheckBox.isSelected())
            return (usernameTextField.getText().isEmpty() && passwordVisibleText.getText().isEmpty());
        else return (usernameTextField.getText().isEmpty() && passwordField.getText().isEmpty());
    }

    @FXML
    private void resetUsernameFieldBackgroundColor() {
        usernameTextField.setStyle("-fx-background-color: #4D6068");
    }

    @FXML
    private void resetPasswordFieldBackgroundColor() {
        passwordField.setStyle("-fx-background-color: #4D6068");
        passwordVisibleText.setStyle("-fx-background-color: #4D6068");
    }

    @FXML
    public void recuperaPassword() {

    }
}
