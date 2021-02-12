package it.unicam.cs.ids.c3.javafx;

import it.unicam.cs.ids.c3.controller.Controller;
import it.unicam.cs.ids.c3.controller.ControllerCommerciante;
import it.unicam.cs.ids.c3.controller.ControllerX;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;

import java.io.IOException;

public class AttivitaCreator implements JavaFXController {
    private final Controller controller;

    public AttivitaCreator(Controller controller, String titolo, String percorsoIcona) {
        this.controller = controller;
        this.titoloText.setText("Creazione di un profilo " + titolo);
        this.iconaView.setImage(new Image(percorsoIcona));
    }

    public Controller getController() {
        return controller;
    }

    @FXML
    Text titoloText = new Text("Creazione di un profilo Attività");

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
    ImageView iconaView = new ImageView();

    @FXML
    public void procedi() {
        try {
            confrontaPassword();
            controller.creaUtente(usernameTextField.getText(),
                    passwordTextField.getText(), emailTextField.getText(),
                    ragioneSocialeTextField.getText());
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
        if (ragioneSocialeTextField.getText().isEmpty()) sblocca = false;
        if (emailTextField.getText().isEmpty()) sblocca = false;
        if (usernameTextField.getText().isEmpty()) sblocca = false;
        if (passwordTextField.getText().isEmpty()) sblocca = false;
        if (confermaPasswordTextField.getText().isEmpty()) sblocca = false;
        if (sblocca)
            procediButton.setDisable(false);

        controllaContenutoTextField(ragioneSocialeTextField);
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
