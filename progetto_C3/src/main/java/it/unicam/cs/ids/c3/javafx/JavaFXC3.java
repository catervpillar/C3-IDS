package it.unicam.cs.ids.c3.javafx;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.IOException;

public class JavaFXC3 extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/loginC3.fxml"));
        stage.setTitle("C3 v1.0");
        stage.setScene(new Scene(root, 450, 500));
        stage.setResizable(false);
//        stage.getIcons().add(new Image("/images/jbudget_icon.png"));
        stage.show();
    }
}
