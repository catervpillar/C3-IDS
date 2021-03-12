package it.unicam.cs.ids.c3.javafx;

import it.unicam.cs.ids.c3.services.Deserializer;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.text.Text;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Objects;

public class LoginC3Controller implements JavaFXController {
    private static LoginC3Controller instance;

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

    private LoginC3Controller() {
    }

    public static LoginC3Controller getInstance() {
        if (Objects.isNull(instance))
            instance = new LoginC3Controller();
        return instance;
    }

    @FXML
    public void accedi() {
        try {
            controllaCampi();
            String utente;

            if (mostraPasswordCheckBox.isSelected())
                utente = Deserializer.getInstance().cercaUtente(usernameTextField.getText(), passwordVisibleText.getText());
            else utente = Deserializer.getInstance().cercaUtente(usernameTextField.getText(), passwordField.getText());

            switch (utente) {
                case "cliente":
                    startWindow("ICliente", "/ICliente.fxml", ICliente.getInstance());
                    break;
                case "commerciante":
                    startWindow("ICommerciante", "/ICommerciante.fxml", ICommerciante.getInstance());
                    break;
                case "corriere":
                    startWindow("ICorriere", "/ICorriere.fxml", ICorriere.getInstance());
                    break;
                case "punto_ritiro":
                    startWindow("IPuntoRitiro", "/IPuntoRitiro.fxml", IPuntoRitiro.getInstance());
                    break;
                default:
                    break;
            }
            close(loginButton);
        } catch (SQLException | IOException | IllegalArgumentException e) {
            erroreLoginText.setText(e.getMessage());
            erroreLoginText.setVisible(true);
        }
    }

    @FXML
    public void registrati() throws IOException {
        startWindow("Registrati", "/tipoAccount.fxml", AccountTypePicker.getInstance());
    }

    @FXML
    public void mostraPassword() {
        mostraPassword(mostraPasswordCheckBox,passwordField,passwordVisibleText);
    }

    private void controllaCampi() {
        if (usernameTextField.getText().isBlank()) {
            usernameTextField.setStyle("-fx-background-color: #923F55");
            erroreLoginText.setVisible(true);
            throw new IllegalArgumentException("IL NOME UTENTE NON E' VALIDO");
        }
        if (!mostraPasswordCheckBox.isSelected()) {
            if (passwordField.getText().isBlank()) {
                passwordField.setStyle("-fx-background-color: #923F55");
                erroreLoginText.setVisible(true);
                throw new IllegalArgumentException("LA PASSWORD NON E' VALIDA");
            }
        } else if (passwordVisibleText.getText().isBlank()) {
            passwordVisibleText.setStyle("-fx-background-color: #923F55");
            erroreLoginText.setVisible(true);
            throw new IllegalArgumentException("LA PASSWORD NON E' VALIDA");
        }
    }

    @FXML
    private void resetUsernameFieldBackgroundColor() {
        usernameTextField.setStyle("-fx-background-color: #4D6068");
        erroreLoginText.setVisible(false);
    }

    @FXML
    private void resetPasswordFieldBackgroundColor() {
        passwordField.setStyle("-fx-background-color: #4D6068");
        passwordVisibleText.setStyle("-fx-background-color: #4D6068");
        erroreLoginText.setVisible(false);
    }

    @FXML
    public void recuperaPassword() throws IOException {
    }
}
