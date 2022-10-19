package fr.isika.cda21.annuaire.tests;

import fr.isika.cda21.annuaire.vues.LoginScene;
import javafx.application.Application;
import javafx.stage.Stage;

public class TestLoginScene extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        LoginScene scene = new LoginScene();
        primaryStage.setTitle("ANNUAIRE");
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}