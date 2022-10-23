package fr.isika.cda21.annuaire.vues;

import fr.isika.cda21.annuaire.models.ArbreBinaire;
import fr.isika.cda21.annuaire.models.GestionFichiers;
import fr.isika.cda21.annuaire.models.Stagiaire;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
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
        super(new VBox(), 1000, 600);

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

        //On applique le style et la taille de police définis dans l'interface StyleGeneral au titre en appelant les constantes :
        labelTitre.setFont(POLICE_TITRE);
        labelTitre.setStyle(GRAS);
        labelTitre.setStyle(POLICE_COULEUR);

        //On crée des DropShadow effect
        DropShadow dropShadow = new DropShadow();
        dropShadow.setOffsetX(5);
        dropShadow.setOffsetY(5);


        //On applique les DropShadow effect à nos éléments
        labelTitre.setEffect(dropShadow);

        // On instancie les labels, champs de texte et boutons
        Label nom = new Label("Nom: ");
        TextField txtNom = new TextField();
        txtNom.setPrefWidth(TAILLE_CHAMP_TEXTE);

        Label prenom = new Label("Prénom: ");
        TextField txtPrenom = new TextField();
        txtPrenom.setPrefWidth(TAILLE_CHAMP_TEXTE);

        Label promo = new Label("Promotion: ");
        TextField txtPromo = new TextField();
        txtPromo.setPrefWidth(TAILLE_CHAMP_TEXTE);

        //département
        Label departement = new Label("Lieu d'habitation: ");
        ChoiceBox<String> txtDepartement = new ChoiceBox<String>();
        Departement(txtDepartement); //ajoute tous les départements à la choiceBox
        txtDepartement.getSelectionModel().select(0);
        txtDepartement.setPrefSize(200, 10);
        txtDepartement.setPrefWidth(TAILLE_CHAMP_TEXTE);

        // Ajout de l'année de formation
        Label anneeDeFormation = new Label("Année de formation: ");
        TextField txtAnneeDeFormation = new TextField();
        txtAnneeDeFormation.setPrefWidth(TAILLE_CHAMP_TEXTE);

        // On instancie une HBox pour y placer la GridPane et 2 HBox pour y placer les 4 boutons que l'on va instancier juste
        // après:

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


        // On instancie un bouton pour rechercher un stagiaire:
        Button btnRechercher = new Button("Rechercher");

        // On instancie un bouton pour ajouter un stagiaire:
        Button btnAjouter = new Button("Ajouter");

        // On instancie un bouton pour accéder à la liste:
        Button btnAccesListe = new Button("Accès liste");

        // On instancie un bouton pour l'accès administrateur:
        Button btnAccesAdmin = new Button("Connexion");

        // On instancie un bouton pour l'accès administrateur:
        Button btnDeconnexion = new Button("Déconnexion");


        //taille des boutons
        btnRechercher.setPrefSize(200, 10);
        btnAjouter.setPrefSize(200, 10);
        btnAccesListe.setPrefSize(200, 10);
        btnAccesAdmin.setPrefSize(200, 10);
        btnDeconnexion.setPrefSize(200, 10);

        //couleurs et style des boutons
        btnRechercher.setStyle(CONTOUR_BOUTON);
        Image imgRechercher = new Image(("Rechercher.png"));
        ImageView imgViewRechercher = new ImageView(imgRechercher);
        imgViewRechercher.setFitHeight(17);
        imgViewRechercher.setPreserveRatio(true);
        btnRechercher.setGraphic(imgViewRechercher);
        btnRechercher.setFont(POLICE_BOUTON_TEXTE);

        btnAjouter.setStyle(CONTOUR_BOUTON);
        Image imgAjouter = new Image(("Ajouter.png"));
        ImageView imgViewAjouter = new ImageView(imgAjouter);
        imgViewAjouter.setFitHeight(13);
        imgViewAjouter.setPreserveRatio(true);
        btnAjouter.setGraphic(imgViewAjouter);
        btnAjouter.setFont(POLICE_BOUTON_TEXTE);

        btnAccesListe.setStyle(CONTOUR_BOUTON);
        Image imgListe = new Image(("Liste.png"));
        ImageView imgViewListe = new ImageView(imgListe);
        imgViewListe.setFitHeight(19);
        imgViewListe.setPreserveRatio(true);
        btnAccesListe.setGraphic(imgViewListe);
        btnAccesListe.setFont(POLICE_BOUTON_TEXTE);

        btnAccesAdmin.setStyle(CONTOUR_BOUTON);
        Image imgAdmin = new Image(("Administrateur.png"));
        ImageView imgViewAdmin = new ImageView(imgAdmin);
        imgViewAdmin.setFitHeight(18);
        imgViewAdmin.setPreserveRatio(true);
        btnAccesAdmin.setGraphic(imgViewAdmin);
        btnAccesAdmin.setFont(POLICE_BOUTON_TEXTE);

        btnDeconnexion.setStyle(CONTOUR_BOUTON);
        Image imgDeconnexion = new Image(("Deconnexion.png"));
        ImageView imgViewDeconnexion = new ImageView(imgDeconnexion);
        imgViewDeconnexion.setFitHeight(18);
        imgViewDeconnexion.setPreserveRatio(true);
        btnDeconnexion.setGraphic(imgViewDeconnexion);
        btnDeconnexion.setFont(POLICE_BOUTON_TEXTE);

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
        myRoot.setStyle(COULEUR_FOND);

        //On applique le style et la taille de police définis dans l'interface StyleGeneral à tous les labels :
        nom.setFont(POLICE_BOUTON_TEXTE);
        prenom.setFont(POLICE_BOUTON_TEXTE);
        promo.setFont(POLICE_BOUTON_TEXTE);
        departement.setFont(POLICE_BOUTON_TEXTE);
        anneeDeFormation.setFont(POLICE_BOUTON_TEXTE);
        btnRechercher.setFont(POLICE_BOUTON_TEXTE);
        btnAjouter.setFont(POLICE_BOUTON_TEXTE);
        btnAccesListe.setFont(POLICE_BOUTON_TEXTE);
        btnAccesAdmin.setFont(POLICE_BOUTON_TEXTE);
        btnDeconnexion.setFont(POLICE_BOUTON_TEXTE);

        // -------Actions-------
        //passage de la souris
        btnRechercher.setOnMouseEntered(new EventHandler<Event>() {

            @Override
            public void handle(Event arg0) {
                btnRechercher.setStyle(FOND_BOUTON);
            }
        });


        btnRechercher.setOnMouseExited(new EventHandler<Event>() {
            @Override
            public void handle(Event arg0) {
                btnRechercher.setStyle(CONTOUR_BOUTON);

            }
        });

        btnAjouter.setOnMouseEntered(new EventHandler<Event>() {

            @Override
            public void handle(Event arg0) {
                btnAjouter.setStyle(FOND_BOUTON);
            }
        });


        btnAjouter.setOnMouseExited(new EventHandler<Event>() {
            @Override
            public void handle(Event arg0) {
                btnAjouter.setStyle(CONTOUR_BOUTON);

            }
        });


        btnAccesListe.setOnMouseEntered(new EventHandler<Event>() {

            @Override
            public void handle(Event arg0) {
                btnAccesListe.setStyle(FOND_BOUTON);
            }
        });


        btnAccesListe.setOnMouseExited(new EventHandler<Event>() {
            @Override
            public void handle(Event arg0) {
                btnAccesListe.setStyle(CONTOUR_BOUTON);

            }
        });

        btnAccesAdmin.setOnMouseEntered(new EventHandler<Event>() {

            @Override
            public void handle(Event arg0) {
                btnAccesAdmin.setStyle(FOND_BOUTON);
            }
        });


        btnAccesAdmin.setOnMouseExited(new EventHandler<Event>() {
            @Override
            public void handle(Event arg0) {
                btnAccesAdmin.setStyle(CONTOUR_BOUTON);

            }
        });

        btnDeconnexion.setOnMouseEntered(new EventHandler<Event>() {

            @Override
            public void handle(Event arg0) {
                btnDeconnexion.setStyle(FOND_BOUTON);
            }
        });


        btnDeconnexion.setOnMouseExited(new EventHandler<Event>() {
            @Override
            public void handle(Event arg0) {
                btnDeconnexion.setStyle(CONTOUR_BOUTON);

            }
        });

        //Action des boutons
        btnAccesAdmin.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                LoginScene loginScene = new LoginScene(stage, administrateur);
                loginScene.getStylesheets().add("style.css");
                stage.setScene(loginScene);
            }
        });

        btnDeconnexion.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                AccueilUtilisateurScene accueilRetour = new AccueilUtilisateurScene(stage, false);
                accueilRetour.getStylesheets().add("style.css");
                stage.setScene(accueilRetour);
            }
        });

        btnAccesListe.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                try {
                    //TODO: Instanciation stagiaire criteres avec attributs vide pour le donner a tableStagiaireScene qui le donnera ensuite a popupscene et
                    //modifScene pour permettre le retour sur la tableview apres validation des actions (supp ou modif)

                    Stagiaire criteres = new Stagiaire("", "", "", "", 0);
                    List<Stagiaire> listeDeStagiaires = new ArrayList<>();
                    ArbreBinaire.debutParcoursAlphabetique(listeDeStagiaires, GestionFichiers.getRaf());
                    TableStagiaireScene tableStagiaireScene = new TableStagiaireScene(stage, listeDeStagiaires, criteres,administrateur);
                    tableStagiaireScene.getStylesheets().add("style.css");
                    stage.setScene(tableStagiaireScene);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        });

        btnAjouter.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                String nom = txtNom.getText();
                String prenom = txtPrenom.getText();
                String promotion = txtPromo.getText();
                String departement = txtDepartement.getSelectionModel().getSelectedItem();
                String departementCourt = departement.substring(0,2);

                String anneeString = txtAnneeDeFormation.getText();
                int annee = 0;

                if (!anneeString.equals("")){
                    annee = Integer.parseInt(anneeString);
                }

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

                String anneeString = txtAnneeDeFormation.getText();
                int annee = 0;

                if (!anneeString.equals("")){
                    annee = Integer.parseInt(anneeString);
                }

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
}
