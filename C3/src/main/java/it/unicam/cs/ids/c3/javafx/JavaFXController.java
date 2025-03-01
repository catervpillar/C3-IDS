package it.unicam.cs.ids.c3.javafx;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;

public interface JavaFXController {
    /**
     * Creates a new error {@link Alert} window with a specific message and then shows it.
     *
     * @param message the message to show on the {@link Alert} window.
     */
    default void createErrorAlert(String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setContentText(message);
        alert.show();
    }

    /**
     * Creates a new confirmation {@link Alert} window with a specific message and then shows it.
     *
     * @param message the message to show on the {@link Alert} window.
     * @return true if ButtonType.OK is selected, false otherwise.
     */
    default boolean createConfirmationAlert(String message) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setContentText(message);
        alert.showAndWait();
        return alert.getResult() == ButtonType.OK;
    }

    /**
     * Opens a new FXML window from a specific path and then sets its title with
     * the given {@code title} parameter and its controller with the given one.
     *
     * @param title      the title to set as the window title.
     * @param path       the path of the .fxml file to be read.
     * @param controller the {@link JavaFXController} that will manage the window.
     * @throws IOException if an error with the reading of the .fxml occurs.
     */
    default void startWindow(String title, String path, JavaFXController controller) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(path));
        fxmlLoader.setController(controller);
        Stage stage = new Stage();
        stage.setTitle(title);
//        stage.getIcons().add(new Image("/images/jbudget_icon.png"));
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setScene(new Scene(fxmlLoader.load()));
        stage.setResizable(false);
        stage.show();
    }

    /**
     * Opens a new FXML window from a specific path and then sets its title with
     * the given {@code title} parameter.
     *
     * @param title the title to set as the window title.
     * @param path  the path of the .fxml file to be read.
     * @throws IOException if an error with the reading of the .fxml occurs.
     */
    default void startResizableWindow(String title, String path, JavaFXController controller) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(path));
        fxmlLoader.setController(controller);
        Stage stage = new Stage();
        stage.setTitle(title);
        stage.setResizable(true);
        stage.setMinWidth(450);
        stage.setMinHeight(475);
//        stage.getIcons().add(new Image("/images/jbudget_icon.png"));
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setScene(new Scene(fxmlLoader.load()));
        stage.show();
    }

    /**
     * Closes the window of a JavaFX controller by closing the stage.
     * The stage to close is obtained from a FXML control element of the controller,
     * usually the {@code abortButton}.
     *
     * @param control the FXML control element whose stage needs to be closed.
     */
    default void close(Control control) {
        Stage stage = (Stage) control.getScene().getWindow();
        stage.close();
    }


    default void mostraPassword(CheckBox mostraPassword, PasswordField passwordField, TextField passwordTextField) {
        if (mostraPassword.isSelected()) {
            passwordTextField.setText(passwordField.getText());
            passwordTextField.setVisible(true);
            passwordField.setVisible(false);
            return;
        }
        passwordField.setText(passwordTextField.getText());
        passwordField.setVisible(true);
        passwordTextField.setVisible(false);
    }
}
