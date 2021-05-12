package it.unicam.cs.ids.c3.view;

import javafx.animation.FadeTransition;
import javafx.animation.TranslateTransition;
import javafx.fxml.FXML;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.util.Duration;

public interface IUtente {

    default void nascondiMenu(AnchorPane blackPane, AnchorPane menuPane) {
        FadeTransition fadeTransition1 = new FadeTransition(Duration.seconds(0.5), blackPane);
        fadeTransition1.setFromValue(0.15);
        fadeTransition1.setToValue(0);
        fadeTransition1.play();

        fadeTransition1.setOnFinished(event1 -> {
            blackPane.setVisible(false);
        });

        TranslateTransition translateTransition1 = new TranslateTransition(Duration.seconds(0.5), menuPane);
        translateTransition1.setByX(-600);
        translateTransition1.play();
    }

    default void mostraTransition(AnchorPane pane) {
        pane.setVisible(true);
        FadeTransition fadeTransition = new FadeTransition(Duration.seconds(0.5), pane);
        fadeTransition.setFromValue(0);
        fadeTransition.setToValue(1);
        fadeTransition.play();
    }

    default void nascondiTutto(AnchorPane homePane, AnchorPane ritiriPane, AnchorPane accountPane,AnchorPane impostazioniPane){
        homePane.setVisible(false);
        ritiriPane.setVisible(false);
        accountPane.setVisible(false);
        impostazioniPane.setVisible(false);
    }

    default void setMenu(AnchorPane menuPane, AnchorPane blackPane, ImageView menuImageView){
        menuPane.setVisible(true);
        blackPane.setVisible(false);

        FadeTransition fadeTransition = new FadeTransition(Duration.seconds(0.5), blackPane);
        fadeTransition.setFromValue(1);
        fadeTransition.setToValue(0);
        fadeTransition.play();

        TranslateTransition translateTransition = new TranslateTransition(Duration.seconds(0.5), menuPane);
        translateTransition.setByX(-600);
        translateTransition.play();

        menuImageView.setOnMouseClicked(event -> {
            blackPane.setVisible(true);

            FadeTransition fadeTransition1 = new FadeTransition(Duration.seconds(0.5), blackPane);
            fadeTransition1.setFromValue(0);
            fadeTransition1.setToValue(0.15);
            fadeTransition1.play();

            TranslateTransition translateTransition1 = new TranslateTransition(Duration.seconds(0.5), menuPane);
            translateTransition1.setByX(+600);
            translateTransition1.play();

        });
    }
    void mostraHome();
    void mostraAccount();
    void mostraRitiri();
    void mostraImpostazioni();
}
