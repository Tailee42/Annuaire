package fr.isika.cda21.annuaire.tests;

import fr.isika.cda21.annuaire.vues.LoginScene;
import javafx.application.Application;
import javafx.scene.image.Image;
import javafx.stage.Stage;

public class TestLoginScene extends Application {

    public static void main(String[] args) {
        launch(args);

    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        LoginScene scene = new LoginScene(primaryStage);
        scene.getStylesheets().add("style.css");
        primaryStage.setTitle("ANNUAIRE");
        primaryStage.setScene(scene);
        primaryStage.getIcons().add(new Image("LogoIsika.jpg"));
        primaryStage.show();
    }

}
