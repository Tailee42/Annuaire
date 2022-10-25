package fr.isika.cda21.annuaire.vues;

import fr.isika.cda21.annuaire.models.ArbreBinaire;
import fr.isika.cda21.annuaire.models.GestionFichiers;
import fr.isika.cda21.annuaire.models.Stagiaire;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


public class PopUpScene extends Scene implements StyleGeneral{

    // attributs
    private BorderPane myRoot;
    private HBox hb;
    private Button boutonValider;
    private Button boutonAnnuler;
    private Label texte;

    // constructeur
    public PopUpScene(Stage stagePopup, Stage stageTableau, Stagiaire stagiaireASupprimer, Stagiaire criteres) {

        super(new BorderPane(), 500, 230);
        myRoot = (BorderPane) this.getRoot();

        myRoot.setPadding(new Insets(10, 10, 10, 10));

        hb = new HBox();
        boutonValider = new Button("Valider");
        boutonAnnuler = new Button("Annuler");
        texte = new Label("Vous allez supprimer le stagiaire : \n\n" + stagiaireASupprimer.getNom()+"\n" +stagiaireASupprimer.getPrenom()+"\n"
                +stagiaireASupprimer.getDepartement()+"\n"+stagiaireASupprimer.getPromo()+"\n"+stagiaireASupprimer.getAnneeDeFormation()+"                 "
                + "\n \n Voulez-vous continuer ?");
        texte.setFont(POLICE_BOUTON_TEXTE);

        // taille des boutons + centrage dans la HBox
        boutonValider.setFont(POLICE_BOUTON_TEXTE);
        boutonValider.setStyle(CONTOUR_BOUTON);
        boutonValider.setPrefSize(100, 10);

        boutonAnnuler.setFont(POLICE_BOUTON_TEXTE);
        boutonAnnuler.setStyle(CONTOUR_BOUTON);
        boutonAnnuler.setPrefSize(100, 10);

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

        //Style au survole Valider
        boutonValider.setOnMouseEntered(new EventHandler<Event>() {

            @Override
            public void handle(Event arg0) {
                boutonValider.setStyle(FOND_BOUTON);
            }
        });

        boutonValider.setOnMouseExited(new EventHandler<Event>() {
            @Override
            public void handle(Event arg0) {
				boutonValider.setStyle(CONTOUR_BOUTON);
            }
        });


        //Style au survole Annuler
        boutonAnnuler.setOnMouseEntered(new EventHandler<Event>() {

            @Override
            public void handle(Event arg0) {
                boutonAnnuler.setStyle(FOND_BOUTON);
            }
        });

        boutonAnnuler.setOnMouseExited(new EventHandler<Event>() {
            @Override
            public void handle(Event arg0) {
				boutonAnnuler.setStyle(CONTOUR_BOUTON);
            }
        });

        // donner une action aux boutons valider et annuler
        boutonValider.setOnAction(actionEvent -> {

            try {
                ArbreBinaire.supprimerUnStagiaire(stagiaireASupprimer, GestionFichiers.getRaf());
                List<Stagiaire> listeDeResultat = new ArrayList<>();
                ArbreBinaire.dbtRechAv(listeDeResultat, criteres, GestionFichiers.getRaf());

                TableStagiaireScene tableStagiaireScene =new TableStagiaireScene(stageTableau, listeDeResultat, criteres, true);
                tableStagiaireScene.getStylesheets().add("style.css");
                stageTableau.setScene(tableStagiaireScene);
                stagePopup.close();

            } catch (IOException e) {
                e.printStackTrace();
            }
        });

        boutonAnnuler.setOnAction(actionEvent -> {

            try {
                List<Stagiaire> listeDeResultat = new ArrayList<>();
                ArbreBinaire.dbtRechAv(listeDeResultat, criteres, GestionFichiers.getRaf());

                TableStagiaireScene tableStagiaireScene =new TableStagiaireScene(stageTableau, listeDeResultat, criteres, true);
                tableStagiaireScene.getStylesheets().add("style.css");
                stageTableau.setScene(tableStagiaireScene);
                stagePopup.close();

            } catch (IOException e) {
                e.printStackTrace();
            }
        });

    }

    // getters et setters
    public BorderPane getMyRoot() {
        return myRoot;
    }

    public void setMyRoot(BorderPane myRoot) {
        this.myRoot = myRoot;
    }

    public HBox getHb() {
        return hb;
    }

    public void setHb(HBox hb) {
        this.hb = hb;
    }

    public Button getBoutonValider() {
        return boutonValider;
    }

    public void setBoutonValider(Button boutonValider) {
        this.boutonValider = boutonValider;
    }

    public Button getBoutonAnnuler() {
        return boutonAnnuler;
    }

    public void setBoutonAnnuler(Button boutonAnnuler) {
        this.boutonAnnuler = boutonAnnuler;
    }

    public Label getTexte() {
        return texte;
    }

    public void setTexte(Label texte) {
        this.texte = texte;
    }

}