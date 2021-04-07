package it.unicam.cs.ids.c3.javafx;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.IOException;

public class JavaFXC3 extends Application implements JavaFXController {
    @Override
    public void start(Stage stage) throws IOException {
        startResizableWindow("C3 v1.0", "/loginC3.fxml", LoginC3Controller.getInstance());
    }
}
