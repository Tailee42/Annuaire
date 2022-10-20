package fr.isika.cda21.annuaire.tests;

import fr.isika.cda21.annuaire.vues.PopUpScene;
import javafx.application.Application;
import javafx.stage.Stage;

public class TestPopup extends Application {

    public static void main(String[] args) {
        launch(args);

    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        PopUpScene scene = new PopUpScene(primaryStage);

        //on interdit le redimensionnement du stage
        primaryStage.setResizable(false);
        primaryStage.setScene(scene);
        primaryStage.show();

    }


}