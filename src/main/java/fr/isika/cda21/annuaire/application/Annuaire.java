package fr.isika.cda21.annuaire.application;

import fr.isika.cda21.annuaire.models.ArbreBinaire;
import fr.isika.cda21.annuaire.vues.AccueilUtilisateurScene;
import javafx.application.Application;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.IOException;

public class Annuaire extends Application {
    public static void main(String[] args) throws IOException {

        ArbreBinaire arbre = new ArbreBinaire("src/main/resources/ecriturearbrebinaire.bin");

        arbre.getRafFichierDom().verificationImportFichierDon(arbre);
        launch(args);
        arbre.getRafFichierDom().fermetureAccessFile();
    }


    @Override
    public void start(Stage primaryStage) throws Exception {
        AccueilUtilisateurScene accueilUtilisateurScene = new AccueilUtilisateurScene(primaryStage, false);
        accueilUtilisateurScene.getStylesheets().add("style.css");

        primaryStage.setResizable(false);
        primaryStage.setTitle("ANNUAIRE");
        primaryStage.setScene(accueilUtilisateurScene);
        primaryStage.getIcons().add(new Image("LogoIsika.jpg"));
        primaryStage.show();
    }
}
