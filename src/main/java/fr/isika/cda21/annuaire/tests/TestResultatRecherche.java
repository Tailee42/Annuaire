package fr.isika.cda21.annuaire.tests;

import fr.isika.cda21.annuaire.models.ArbreBinaire;
import fr.isika.cda21.annuaire.models.GestionFichiers;
import fr.isika.cda21.annuaire.models.Stagiaire;
import fr.isika.cda21.annuaire.vues.TableStagiaireScene;
import javafx.application.Application;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.List;

public class TestResultatRecherche extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        List<Stagiaire> listeDeStagiaires = new ArrayList<>();
        ArbreBinaire.debutParcoursAlphabetique(listeDeStagiaires, GestionFichiers.getRaf());

        Stagiaire criteres = new Stagiaire(null, null, null, null, 0);

        TableStagiaireScene scene = new TableStagiaireScene(primaryStage, listeDeStagiaires, criteres, true);
        scene.getStylesheets().add("style.css");
        primaryStage.setTitle("ANNUAIRE");
        //on interdit le redimensionnement du stage
        primaryStage.setResizable(false);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

}