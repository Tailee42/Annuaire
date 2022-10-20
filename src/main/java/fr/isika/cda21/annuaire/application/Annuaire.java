package fr.isika.cda21.annuaire.application;

import fr.isika.cda21.annuaire.vues.AccueilUtilisateurScene;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class Annuaire extends Application {
    public static void main(String[] args) {
        launch(args);
    }


    @Override
    public void start(Stage primaryStage) throws Exception {
        AccueilUtilisateurScene accueilUtilisateurScene = new AccueilUtilisateurScene(primaryStage, false);
        primaryStage.setTitle("ANNUAIRE");
        primaryStage.setScene(accueilUtilisateurScene);
        primaryStage.getIcons().add(new Image("LogoIsika.jpg"));
        primaryStage.show();
    }
}
