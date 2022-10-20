package fr.isika.cda21.annuaire.tests;

import fr.isika.cda21.annuaire.vues.AccueilUtilisateurScene;
import javafx.application.Application;
import javafx.stage.Stage;

public class TestAccueilScene extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {

        //On crée la scene en utilisant notre constructeur AcceuilUtilisateurScene
        AccueilUtilisateurScene scene = new AccueilUtilisateurScene(primaryStage,false);

        //On associe la stage et la scene
        primaryStage.setScene(scene);

        //On lui demande d'afficher la stage
        primaryStage.show();

    }

}
