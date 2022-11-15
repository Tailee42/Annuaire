package fr.isika.cda21.annuaire.vues;

import fr.isika.cda21.annuaire.models.ArbreBinaire;
import fr.isika.cda21.annuaire.models.Stagiaire;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


public class PopUpScene extends Scene implements StyleGeneral {

    private final StyledButton boutonValider;
    private final StyledButton boutonAnnuler;
    private final ArbreBinaire arbre;

    // constructeur
    public PopUpScene(Stage stagePopup, Stage stageTableau, Stagiaire stagiaireASupprimer, Stagiaire criteres) throws FileNotFoundException {

        super(new BorderPane(), 500, 230);
        arbre = new ArbreBinaire("src/main/resources/ecriturearbrebinaire.bin");

        // attributs
        BorderPane myRoot = (BorderPane) this.getRoot();

        myRoot.setPadding(new Insets(10, 10, 10, 10));

        HBox hb = new HBox();
        boutonValider = new StyledButton("Valider");
        boutonAnnuler = new StyledButton("Annuler");
        Label texte = new Label("Vous allez supprimer le stagiaire : \n\n" + stagiaireASupprimer.getNom() + "\n" + stagiaireASupprimer.getPrenom() + "\n"
                + stagiaireASupprimer.getDepartement() + "\n" + stagiaireASupprimer.getPromo() + "\n" + stagiaireASupprimer.getAnneeDeFormation() + "                 "
                + "\n \n Voulez-vous continuer ?");
        texte.setFont(POLICE_BOUTON_TEXTE);

        hb.setAlignment(Pos.CENTER);
        hb.setSpacing(100);

        // pour que le texte s'affiche sur plusieurs lignes et soit centré
        texte.setWrapText(true);
        texte.setTextAlignment(TextAlignment.CENTER);

        //couleur
        myRoot.setStyle(COULEUR_FOND);

        // ajout des boutons à la HBox
        hb.getChildren().addAll(boutonValider, boutonAnnuler);
        hb.setSpacing(50);

        // on ajoute le label et la HBox à la racine
        myRoot.setTop(texte);
        myRoot.setBottom(hb);
        texte.setTextAlignment(TextAlignment.CENTER);
        BorderPane.setMargin(texte, (new Insets(0, 0, 0, 0)));

        // donner une action aux boutons valider et annuler
        actionBtnValider(stagiaireASupprimer, criteres, stageTableau, stagePopup);
        actionBtnAnnuler(criteres, stageTableau, stagePopup);
    }

    private void actionBtnValider(Stagiaire stagiaireASupprimer, Stagiaire criteres, Stage stageTableau, Stage stagePopup) {
        boutonValider.setOnAction(actionEvent -> {

            try {
                arbre.supprimerUnStagiaire(stagiaireASupprimer);
                contructionTableau(criteres, stageTableau, stagePopup);

            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }

    private void actionBtnAnnuler(Stagiaire criteres, Stage stageTableau, Stage stagePopup) {
        boutonAnnuler.setOnAction(actionEvent -> {
            try {
                contructionTableau(criteres, stageTableau, stagePopup);

            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }

    private void contructionTableau(Stagiaire criteres, Stage stageTableau, Stage stagePopup) throws IOException {
        List<Stagiaire> listeDeResultat = new ArrayList<>();
        arbre.dbtRechAv(listeDeResultat, criteres);

        TableStagiaireScene tableStagiaireScene = new TableStagiaireScene(stageTableau, listeDeResultat, criteres, true);
        tableStagiaireScene.getStylesheets().add("style.css");
        stageTableau.setScene(tableStagiaireScene);
        stagePopup.close();
    }
}

