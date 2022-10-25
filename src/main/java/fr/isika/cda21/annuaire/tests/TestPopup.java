package fr.isika.cda21.annuaire.tests;

import fr.isika.cda21.annuaire.models.GestionFichiers;
import fr.isika.cda21.annuaire.models.Stagiaire;
import fr.isika.cda21.annuaire.vues.PopUpScene;
import fr.isika.cda21.annuaire.vues.TableStagiaireScene;
import javafx.application.Application;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.List;

public class TestPopup extends Application {

    public static void main(String[] args) {
        launch(args);

    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        GestionFichiers.getRaf().seek(0);
        Stagiaire stagiaireASupprimer = GestionFichiers.lectureNoeud().getStagiaire();

        Stagiaire criteres = new Stagiaire(null, null, null, null, 0);

        Stage stageTableau = new Stage();

        PopUpScene scene = new PopUpScene(primaryStage, stageTableau, stagiaireASupprimer, criteres);
        scene.getStylesheets().add("style.css");
        //on interdit le redimensionnement du stage
        primaryStage.setResizable(false);
        primaryStage.setScene(scene);
        primaryStage.show();

    }


}