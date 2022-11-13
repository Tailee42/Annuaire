package fr.isika.cda21.annuaire.vues;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import fr.isika.cda21.annuaire.models.ArbreBinaire;
import fr.isika.cda21.annuaire.models.GestionFichiers;
import fr.isika.cda21.annuaire.models.Stagiaire;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class ModificationScene extends Scene implements StyleGeneral {

    private final StyledButton btnValider;
    private final StyledButton btnAnnuler;
    private final TextField txtNom;
    private final TextField txtPrenom;
    private final TextField txtPromo;
    private final TextField txtDepartement;
    private final TextField txtAnneeDeFormation;

    // Constructeur de la scene
    public ModificationScene(Stage stage, Stagiaire stagiaireAModifier, Stagiaire criteres) {
        super(new VBox(), 1000, 600);

        // On récupère la racine de la scene et on la détermine comme BorderPane:
        // On déclare un attribut
        VBox myRoot = (VBox) this.getRoot();
        // On déclare une GridPane pour y placer le formulaire:
        GridPane formulaire = new GridPane();

        LabelTitre labelTitre = new LabelTitre("Modification du stagiaire");

        // On instancie les labels, champs de texte et boutons
        Label nom = new Label("Nom ");
        txtNom = new TextField();
        txtNom.setText(stagiaireAModifier.getNom().trim());
        txtNom.setPrefWidth(TAILLE_CHAMP_TEXTE);

        Label prenom = new Label("Prénom ");
        txtPrenom = new TextField();
        txtPrenom.setText(stagiaireAModifier.getPrenom().trim());
        txtPrenom.setPrefWidth(TAILLE_CHAMP_TEXTE);

        Label promo = new Label("Promotion ");
        txtPromo = new TextField();
        txtPromo.setText(stagiaireAModifier.getPromo().trim());
        txtPromo.setPrefWidth(TAILLE_CHAMP_TEXTE);

        Label departement = new Label("Lieu d'habitation ");
        txtDepartement = new TextField();
        txtDepartement.setText(stagiaireAModifier.getDepartement().trim());
        txtDepartement.setPrefWidth(TAILLE_CHAMP_TEXTE);

        Label anneeDeFormation = new Label("Année de formation ");
        txtAnneeDeFormation = new TextField();
        txtAnneeDeFormation.setText("" + stagiaireAModifier.getAnneeDeFormation());
        txtAnneeDeFormation.setPrefWidth(TAILLE_CHAMP_TEXTE);

        // On instancie une HBox pour y placer la GridPane et 2 HBox pour y placer les 4
        // boutons que l'on va instancier juste après :

        // ***************************** éléments basiques instanciés************************
        HBox hBoxFormulaire = new HBox();
        hBoxFormulaire.getChildren().add(formulaire); // on ajoute la hBox au GridePane
        hBoxFormulaire.setAlignment(Pos.CENTER);

        HBox hBoxValiderAnnuler = new HBox();

        hBoxValiderAnnuler.setAlignment(Pos.CENTER);
        hBoxValiderAnnuler.setPadding(new Insets(10));
        hBoxValiderAnnuler.setSpacing(150);

        // On instancie un bouton pour rechercher un stagiaire:
        btnValider = new StyledButton("Valider");// lance méthode modifier
        actionBtnValider(stagiaireAModifier,criteres, stage);

        // On instancie un bouton pour ajouter un stagiaire:
        btnAnnuler = new StyledButton("Annuler");
        actionBtnAnnuler(stage, criteres);

        // On relie les boutons aux HBox:
        hBoxValiderAnnuler.getChildren().addAll(btnValider, btnAnnuler);

        // On intègre le formulaire au GridPane:
        formulaire.addRow(0, nom, txtNom);
        formulaire.addRow(1, prenom, txtPrenom);
        formulaire.addRow(2, promo, txtPromo);
        formulaire.addRow(3, departement, txtDepartement);
        formulaire.addRow(4, anneeDeFormation, txtAnneeDeFormation);
        formulaire.setHgap(25);
        formulaire.setVgap(15);

        formulaire.getColumnConstraints().add(new ColumnConstraints(300)); // column 0 is 100 wide

        // On ajoute les éléments dans la VBox (myRoot):
        myRoot.getChildren().addAll(labelTitre, hBoxFormulaire, hBoxValiderAnnuler);

        // Mise en page des éléments
        myRoot.setAlignment(Pos.CENTER);
        myRoot.setPadding(new Insets(60));
        myRoot.setSpacing(30);
        myRoot.setStyle(COULEUR_FOND);
    }

    private void actionBtnValider(Stagiaire stagiaireAModifier, Stagiaire criteres, Stage stage) {
        btnValider.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                try {
                    int anneeInt = 0;

                    if(!txtAnneeDeFormation.getText().trim().equals("")) {
                        try {
                            anneeInt = Integer.parseInt(txtAnneeDeFormation.getText().trim());
                        } catch (NumberFormatException e) {
                            txtAnneeDeFormation.clear();
                        }
                    }

                    Stagiaire stagiaireAJour = new Stagiaire(txtNom.getText(), txtPrenom.getText(),
                            txtDepartement.getText(), txtPromo.getText(), anneeInt);

                    ArbreBinaire.modification(stagiaireAModifier, stagiaireAJour, GestionFichiers.getRaf());

                    List<Stagiaire> listeDeStagiaire = new ArrayList<>();
                    ArbreBinaire.dbtRechAv(listeDeStagiaire, criteres, GestionFichiers.getRaf());

                    TableStagiaireScene tableStagiaireScene = new TableStagiaireScene(stage, listeDeStagiaire, criteres, true);
                    tableStagiaireScene.getStylesheets().add("style.css");
                    stage.setScene(tableStagiaireScene);

                } catch (IOException e) {
                    e.printStackTrace();
                }

            }
        });
    }

    private void actionBtnAnnuler(Stage stage, Stagiaire criteres) {
        btnAnnuler.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {

                try {
                    List<Stagiaire> listeDeStagiaire = new ArrayList<>();
                    ArbreBinaire.debutParcoursAlphabetique(listeDeStagiaire, GestionFichiers.getRaf());

                    TableStagiaireScene tableStagiaireScene = new TableStagiaireScene(stage, listeDeStagiaire, criteres, true);
                    tableStagiaireScene.getStylesheets().add("style.css");
                    stage.setScene(tableStagiaireScene);

                } catch (IOException e) {
                    e.printStackTrace();
                }

            }
        });
    }

}

