package fr.isika.cda21.annuaire.vues;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;


public class PopUpScene extends Scene implements StyleGeneral{

    // attributs
    private BorderPane myRoot;
    private HBox hb;
    private Button boutonValider;
    private Button boutonAnnuler;
    private Label texte;

    // constructeur
    public PopUpScene(Stage stage) {

        super(new BorderPane(), 300, 120);
        myRoot = (BorderPane) this.getRoot();

        myRoot.setPadding(new Insets(10, 10, 10, 10));

        hb = new HBox();
        boutonValider = new Button("Valider");
        boutonAnnuler = new Button("Annuler");
        texte = new Label("Vous vous apprêtez à faire une action irréversible. Voulez-vous continuer ?");
        texte.setFont(POLICE_BOUTON_TEXTE);

        // taille des boutons + centrage dans la HBox
        boutonValider.setPrefSize(80, 10);
        boutonAnnuler.setPrefSize(80, 10);
        boutonValider.setFont(POLICE_BOUTON_TEXTE);
        boutonAnnuler.setFont(POLICE_BOUTON_TEXTE);
        hb.setAlignment(Pos.CENTER);
        hb.setSpacing(100);

        // pour que le texte s'affiche sur plusieurs lignes et soit centré
        texte.setWrapText(true);
        texte.setTextAlignment(TextAlignment.CENTER);

        //couleur
        myRoot.setStyle(("-fx-background-color:rgb(247, 157, 79)"));

        // ajout des boutons à la HBox
        hb.getChildren().addAll(boutonValider, boutonAnnuler);
        hb.setSpacing(50);

        // on ajoute le label et la HBox à la racine
        myRoot.setTop(texte);
        myRoot.setBottom(hb);
        texte.setTextAlignment(TextAlignment.CENTER);
        BorderPane.setMargin(texte, (new Insets(0, 0, 0, 0)));

        // donner une action aux boutons valider et annuler
        boutonValider.setOnAction(actionEvent -> {

            stage.close();

        });

        boutonAnnuler.setOnAction(actionEvent -> {

            stage.close();

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