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
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class ModificationScene extends Scene implements Elements, StyleGeneral {

    // récupère Stage pour pouvoir changer de vue
    private Stage stage;
    // On déclare un attribut
    public VBox myRoot;

    // On déclare une GridPane pour y placer le formulaire:
    public GridPane formulaire;

    // Constructeur de la scene
    public ModificationScene(Stage stage, Stagiaire stagiaireAModifier) {
        super(new VBox(), 750, 500);

        // On récupère la racine de la scene et on la détermine comme BorderPane:
        myRoot = (VBox) this.getRoot();
        formulaire = new GridPane();

        Label labelTitre = new Label("Modification du stagiaire");

        // On instancie les labels, champs de texte et boutons
        Label nom = new Label("Nom: ");
        TextField txtNom = new TextField();
        txtNom.setText(stagiaireAModifier.getNom().trim());
        Label prenom = new Label("Prénom: ");
        TextField txtPrenom = new TextField();
        txtPrenom.setText(stagiaireAModifier.getPrenom().trim());
        Label promo = new Label("Promotion: ");
        TextField txtPromo = new TextField();
        txtPromo.setText(stagiaireAModifier.getPromo().trim());
        Label departement = new Label("Lieu d'habitation: ");
        TextField txtDepartement = new TextField();
        txtDepartement.setText(stagiaireAModifier.getDepartement().trim());
        ;
        Label anneeDeFormation = new Label("Année de formation: ");
        TextField txtAnneeDeFormation = new TextField();
        txtAnneeDeFormation.setText("" + stagiaireAModifier.getAnneeDeFormation());

        // On instancie une HBox pour y placer la GridPane et 2 HBox pour y placer les 4
        // boutons que l'on va instancier juste
        // après:

        // ***************************** éléments basiques
        // instanciés************************
        HBox hBoxFormulaire = new HBox();
        hBoxFormulaire.getChildren().add(formulaire); // on ajoute la hBox au GridePane
        hBoxFormulaire.setAlignment(Pos.CENTER);

        HBox hBoxValiderAnnuler = new HBox();

        hBoxValiderAnnuler.setAlignment(Pos.CENTER);
        hBoxValiderAnnuler.setPadding(new Insets(10));
        hBoxValiderAnnuler.setSpacing(150);

        // On instancie un bouton pour rechercher un stagiaire:
        Button btnValider = new Button("Modifier");// lance méthode modifier
        btnValider.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                try {
                    Stagiaire stagiaireAJour = new Stagiaire(txtNom.getText(), txtPrenom.getText(),
                            txtDepartement.getText(), txtPromo.getText(),
                            Integer.parseInt(txtAnneeDeFormation.getText().trim()));

                    ArbreBinaire.modification(stagiaireAModifier, stagiaireAJour, GestionFichiers.getRaf());

                    List<Stagiaire> listeDeStagiaire = new ArrayList<>();
                    ArbreBinaire.debutParcoursAlphabetique(listeDeStagiaire, GestionFichiers.getRaf());
                    stage.setScene(new TableStagiaireScene(stage, listeDeStagiaire, true));

                } catch (IOException e) {

                    e.printStackTrace();
                }

            }
        });
        // On instancie un bouton pour ajouter un stagiaire:
        Button btnAnnuler = new Button("Annuler");
        btnAnnuler.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {

                try {
                    List<Stagiaire> listeDeStagiaire = new ArrayList<>();
                    ArbreBinaire.debutParcoursAlphabetique(listeDeStagiaire, GestionFichiers.getRaf());
                    stage.setScene(new TableStagiaireScene(stage, listeDeStagiaire, true));
                } catch (IOException e) {

                    e.printStackTrace();
                }

            }
        });

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

        formulaire.getColumnConstraints().add(new ColumnConstraints(200)); // column 0 is 100 wide

        // On ajoute les éléments dans la VBox (myRoot):
        myRoot.getChildren().addAll(labelTitre, hBoxFormulaire, hBoxValiderAnnuler);

        // Mise en page des éléments
        myRoot.setAlignment(Pos.CENTER);
        myRoot.setPadding(new Insets(60));
        myRoot.setSpacing(20);

    }
}

