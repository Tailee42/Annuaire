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

    // On instancie un bouton pour rechercher un stagiaire :
    private final StyledButton btnRechercher;

    // On instancie un bouton pour ajouter un stagiaire :
    private final StyledButton btnAjouter;

    // On instancie un bouton pour accéder à la liste :
    private final StyledButton btnAccesListe;

    // On instancie un bouton pour l'accès administrateur :
    private final StyledButton btnAccesAdmin;

    // On instancie un bouton pour l'accès administrateur :
    private final StyledButton btnDeconnexion;
    private final StyledTextField txtNom;
    private final StyledTextField txtPrenom;
    private final StyledTextField txtPromo;
    private final ChoiceBox<String> txtDepartement;
    private final StyledTextField txtAnneeDeFormation;
    private final Label validationAjout;

    // Constructeur de la scene
    public AccueilUtilisateurScene(Stage stage, Boolean administrateur) {
        super(new VBox(), 1000, 600);

        // On récupère la racine de la scene et on la détermine comme VBox : On déclare un attribut
        VBox myRoot = (VBox) this.getRoot();
        // On déclare une GridPane pour y placer le formulaire:
        GridPane formulaire = new GridPane();
        LabelTitre labelTitre = new LabelTitre();
        validationAjout = new Label();

        if(administrateur){
            labelTitre.setText("Accueil Administrateur");
        } else {
            labelTitre.setText("Accueil Utilisateur");
        }

        // On instancie les labels, champs de texte et boutons
        Label nom = new Label("Nom ");
        txtNom = new StyledTextField();

        Label prenom = new Label("Prénom ");
        txtPrenom = new StyledTextField();

        Label promo = new Label("Promotion ");
        txtPromo = new StyledTextField();

        //département
        Label departement = new Label("Lieu d'habitation ");
        txtDepartement = new ChoiceBox<String>();
        Departement(txtDepartement); //ajoute tous les départements à la choiceBox
        txtDepartement.getSelectionModel().select(0);
        txtDepartement.setPrefWidth(TAILLE_CHAMP_TEXTE);

        // Ajout de l'année de formation
        Label anneeDeFormation = new Label("Année de formation ");
        txtAnneeDeFormation = new StyledTextField();

        // On instancie une HBox pour y placer la GridPane et 2 HBox pour y placer les 4 boutons que l'on va instancier juste
        // après :
        HBox hBoxFormulaire = new HBox();
        hBoxFormulaire.getChildren().add(formulaire); //on ajoute la hBox au GridePane
        hBoxFormulaire.setAlignment(Pos.CENTER);

        HBox hBoxRechercherAjouter = new HBox();
        HBox hBoxConnexionListe = new HBox();

        hBoxRechercherAjouter.setAlignment(Pos.CENTER);
        hBoxRechercherAjouter.setPadding(new Insets(10));
        hBoxRechercherAjouter.setSpacing(300);

        hBoxConnexionListe.setAlignment(Pos.CENTER);
        hBoxConnexionListe.setPadding(new Insets(10));
        hBoxConnexionListe.setSpacing(300);


        // On instancie un bouton pour rechercher un stagiaire :
        btnRechercher = new StyledButton("Rechercher", "Rechercher.png");

        // On instancie un bouton pour ajouter un stagiaire :
        btnAjouter = new StyledButton("Ajouter", "Ajouter.png");

        // On instancie un bouton pour accéder à la liste :
        btnAccesListe = new StyledButton("Accès liste", "Liste.png");

        // On instancie un bouton pour l'accès administrateur :
        btnAccesAdmin = new StyledButton("Connexion", "Administrateur.png");

        // On instancie un bouton pour l'accès administrateur :
        btnDeconnexion = new StyledButton("Déconnexion", "Deconnexion.png");

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
        myRoot.setSpacing(30);


        // -------Actions-------
        actionBtnAccesAdmin (stage);
        actionBtnDeconnexion (stage);
        actionBtnAccesListe(stage, administrateur);
        actionBtnAjouter();
        actionBtnRechercher (stage, administrateur);
    }

    private void actionBtnAccesAdmin (Stage stage){
        btnAccesAdmin.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                LoginScene loginScene = new LoginScene(stage);
                loginScene.getStylesheets().add("style.css");
                stage.setScene(loginScene);
            }
        });
    }

    private void actionBtnDeconnexion (Stage stage) {
        btnDeconnexion.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                AccueilUtilisateurScene accueilRetour = new AccueilUtilisateurScene(stage, false);
                accueilRetour.getStylesheets().add("style.css");
                stage.setScene(accueilRetour);
            }
        });
    }

    private void actionBtnAccesListe(Stage stage, Boolean administrateur) {
        btnAccesListe.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                try {
                    //Instanciation stagiaire criteres avec attributs vide pour le donner a tableStagiaireScene qui le donnera ensuite a popupscene et
                    //modifScene pour permettre le retour sur la tableview apres validation des actions (supp ou modif)

                    Stagiaire criteres = new Stagiaire("", "", "", "", 0);
                    List<Stagiaire> listeDeStagiaires = new ArrayList<>();
                    ArbreBinaire.debutParcoursAlphabetique(listeDeStagiaires, GestionFichiers.getRaf());
                    TableStagiaireScene tableStagiaireScene = new TableStagiaireScene(stage, listeDeStagiaires, criteres, administrateur);
                    tableStagiaireScene.getStylesheets().add("style.css");
                    stage.setScene(tableStagiaireScene);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        });
    }

    private void actionBtnAjouter() {
        btnAjouter.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                String nom = txtNom.getText();
                String prenom = txtPrenom.getText();
                String promotion = txtPromo.getText();
                String departement = txtDepartement.getSelectionModel().getSelectedItem();
                String departementCourt = departement.substring(0,2);

                int annee = transformeStringEnInt();;

                if(!nom.equals("") && !prenom.equals("") && !promotion.equals("") && !departementCourt.equals("Dé") && annee !=0 ){
                    try {
                        Stagiaire stagiaireAAjouter = new Stagiaire(nom, prenom, departementCourt, promotion, annee);
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
                    txtAnneeDeFormation.clear();

                } else {
                    validationAjout.setText("Il manque des informations.");
                }
            }
        });
    }

    private void actionBtnRechercher (Stage stage, Boolean administrateur) {
        btnRechercher.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                String nom = txtNom.getText();
                String prenom = txtPrenom.getText();
                String promotion = txtPromo.getText();
                String departement = txtDepartement.getSelectionModel().getSelectedItem().substring(0,2);

                if (departement.equals("Dé") || departement.equals(("Et"))) {
                    departement = "";
                }

                int annee = transformeStringEnInt();

                Stagiaire criteres = new Stagiaire(nom, prenom, departement, promotion, annee);
                List<Stagiaire> listStagiairesRecherches = new ArrayList<>();

                try {
                    ArbreBinaire.dbtRechAv(listStagiairesRecherches, criteres, GestionFichiers.getRaf());
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }

                TableStagiaireScene tableStagiaireScene = new TableStagiaireScene(stage, listStagiairesRecherches, criteres, administrateur);
                tableStagiaireScene.getStylesheets().add("style.css");
                stage.setScene(tableStagiaireScene);
            }
        });
    }

    private int transformeStringEnInt() {
        String anneeString = txtAnneeDeFormation.getText();
        int annee = 0;

        if (!anneeString.equals("")){
            try {
                annee = Integer.parseInt(anneeString);
            } catch (NumberFormatException e) {
                validationAjout.setText("L'année de formation n'est pas un nombre.");
                txtAnneeDeFormation.clear();
            }
        }
        return annee;
    }
}
