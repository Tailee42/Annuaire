package fr.isika.cda21.annuaire.vues;

import fr.isika.cda21.annuaire.models.ArbreBinaire;
import fr.isika.cda21.annuaire.models.GestionFichiers;
import fr.isika.cda21.annuaire.models.Stagiaire;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class AccueilUtilisateurScene extends Scene implements Elements, StyleGeneral{

    // On déclare un attribut
    public VBox myRoot;

    // On déclare une GridPane pour y placer le formulaire:
    public GridPane formulaire;

    // Constructeur de la scene
    public AccueilUtilisateurScene(Stage stage, Boolean administrateur) {
        super(new VBox(), 1000, 500);

        // On récupère la racine de la scene et on la détermine comme BorderPane:
        myRoot = (VBox) this.getRoot();
        formulaire = new GridPane();
        Label labelTitre = new Label();
        Label validationAjout = new Label();

        if(administrateur){
            labelTitre.setText("Accueil Administrateur");
        } else {
            labelTitre.setText("Accueil Utilisateur");
        }


        // On instancie les labels, champs de texte et boutons
        Label nom = new Label("Nom: ");
        TextField txtNom = new TextField();
        Label prenom = new Label("Prénom: ");
        TextField txtPrenom = new TextField();
        Label promo = new Label("Promotion: ");
        TextField txtPromo = new TextField();
        Label departement = new Label("Lieu d'habitation: ");
        ChoiceBox<String> txtDepartement = new ChoiceBox<String>();
        Departement(txtDepartement); //ajoute tous les départements à la choiceBox
        txtDepartement.getSelectionModel().select(0);

        // Ajout de l'année de formation
        Label anneeDeFormation = new Label("Année de formation: ");
        ChoiceBox<String> txtAnneeDeFormation = new ChoiceBox<>();
        txtAnneeDeFormation.getItems().addAll("Année de formation", "2030", "2029", "2028", "2027", "2026", "2025", "2024", "2023", "2022", "2021", "2020", "2019", "2018", "2017", "2016", "2015", "2014", "2013", "2012",
                "2011", "2010", "2009", "2008", "2007", "2006", "2005", "2004", "2003", "2002", "2001", "2000", "1999", "1998", "1997", "1996", "1995", "1990");
        txtAnneeDeFormation.getSelectionModel().select(0);

        // On instancie une HBox pour y placer la GridPane et 2 HBox pour y placer les 4 boutons que l'on va instancier juste
        // après:

        HBox hBoxFormulaire = new HBox();
        hBoxFormulaire.getChildren().add(formulaire); //on ajoute la hBox au GridePane
        hBoxFormulaire.setAlignment(Pos.CENTER);

        HBox hBoxRechercherAjouter = new HBox();
        HBox hBoxConnexionListe = new HBox();

        hBoxRechercherAjouter.setAlignment(Pos.CENTER);
        hBoxRechercherAjouter.setPadding(new Insets(10));
        hBoxRechercherAjouter.setSpacing(150);

        hBoxConnexionListe.setAlignment(Pos.CENTER);
        hBoxConnexionListe.setPadding(new Insets(10));
        hBoxConnexionListe.setSpacing(400);


        // On instancie un bouton pour rechercher un stagiaire:
        Button btnRechercher = new Button("Rechercher un stagiaire");

        // On instancie un bouton pour ajouter un stagiaire:
        Button btnAjouter = new Button("Ajouter un stagiaire");

        // On instancie un bouton pour accéder à la liste:
        Button btnAccesListe = new Button("Accès liste");

        // On instancie un bouton pour l'accès administrateur:
        Button btnAccesAdmin = new Button("Accès Administrateur");

        // On instancie un bouton pour l'accès administrateur:
        Button btnDeconnexion = new Button("Déconnexion");





        // On relie les boutons aux HBox:
        hBoxRechercherAjouter.getChildren().addAll(btnRechercher, btnAjouter);
        if(administrateur){
            hBoxConnexionListe.getChildren().addAll(btnDeconnexion, btnAccesListe);
        } else {
            hBoxConnexionListe.getChildren().addAll(btnAccesAdmin, btnAccesListe);
        }


        // On intègre le formulaire au GridPane:
        formulaire.addRow(0, nom, txtNom);

        formulaire.addRow(1, prenom, txtPrenom);
        formulaire.addRow(2, departement, txtDepartement);
        formulaire.addRow(3, promo, txtPromo);
        formulaire.addRow(4, anneeDeFormation, txtAnneeDeFormation);
        formulaire.setHgap(25);
        formulaire.setVgap(15);

        formulaire.getColumnConstraints().add(new ColumnConstraints(200)); // column 0 is 100 wide

        // On ajoute les éléments dans la VBox (myRoot):
        myRoot.getChildren().addAll(hBoxConnexionListe, labelTitre, hBoxFormulaire, hBoxRechercherAjouter, validationAjout);

        //Mise en page des éléments
        myRoot.setAlignment(Pos.CENTER);
        myRoot.setPadding(new Insets(60));
        myRoot.setSpacing(20);

        // -------Actions-------
        btnAccesAdmin.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                LoginScene loginScene = new LoginScene(stage, administrateur);
                stage.setScene(loginScene);
            }
        });

        btnDeconnexion.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                AccueilUtilisateurScene accueilRetour = new AccueilUtilisateurScene(stage, false);
                stage.setScene(accueilRetour);
            }
        });

        btnAccesListe.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                List<Stagiaire> listeDeStagiaires = new ArrayList<>();
                try {
                    ArbreBinaire.debutParcoursAlphabetique(listeDeStagiaires, GestionFichiers.getRaf());
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }

                TableStagiaireScene tableStagiaireScene = new TableStagiaireScene(stage, listeDeStagiaires, administrateur);
                stage.setScene(tableStagiaireScene);
            }
        });

        btnAjouter.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                String nom = txtNom.getText();
                String prenom = txtPrenom.getText();
                String promotion = txtPromo.getText();
                String departement = txtDepartement.getSelectionModel().getSelectedItem().substring(0,2);

                String anneeString = txtAnneeDeFormation.getSelectionModel().getSelectedItem();
                int annee = 0;

                if (!anneeString.equals("Année")){
                    annee = Integer.parseInt(anneeString);
                }

                if(!nom.equals("") && !prenom.equals("") && !promotion.equals("") && !departement.equals("Dé") && annee !=0 ){
                    try {
                        Stagiaire stagiaireAAjouter = new Stagiaire(nom, prenom, departement, promotion, annee);
                        ArbreBinaire.ajouterUnStagiaire(stagiaireAAjouter, GestionFichiers.getRaf());
                        validationAjout.setText("Le stagiaire a bien été ajouté.");
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }

                    //Remise à zéro de champs
                    txtNom.clear();
                    txtPrenom.clear();
                    txtDepartement.getSelectionModel().select(0);
                    txtPromo.clear();
                    txtAnneeDeFormation.getSelectionModel().select(0);

                } else {
                    validationAjout.setText("Il manque des informations.");
                }
            }
        });

        btnRechercher.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                String nom = txtNom.getText();
                String prenom = txtPrenom.getText();
                String promotion = txtPromo.getText();
                String departement = txtDepartement.getSelectionModel().getSelectedItem().substring(0,2);

                if (departement.equals("Dé")) {
                    departement = "";
                }

                String anneeString = txtAnneeDeFormation.getSelectionModel().getSelectedItem();
                int annee = 0;

                if (!anneeString.equals("Année")){
                    annee = Integer.parseInt(anneeString);
                }

                Stagiaire criteres = new Stagiaire(nom, prenom, departement, promotion, annee);
                List<Stagiaire> listStagiairesRecherches = new ArrayList<>();

                try {
                    ArbreBinaire.dbtRechAv(listStagiairesRecherches, criteres, GestionFichiers.getRaf());
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }

                TableStagiaireScene tableStagiaireScene = new TableStagiaireScene(stage, listStagiairesRecherches, administrateur);
                stage.setScene(tableStagiaireScene);
            }
        });


    }
}
