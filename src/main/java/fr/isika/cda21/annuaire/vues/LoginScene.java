package fr.isika.cda21.annuaire.vues;

import fr.isika.cda21.annuaire.models.Administrateur;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.effect.DropShadow;
import javafx.scene.effect.Reflection;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class LoginScene extends Scene implements StyleGeneral{

    public Label identifiant;
    public Label motDePasse;
    public TextField txtIdentifiant;
    public PasswordField txtMotDePasse;
    public Button btnValider;
    public Button btnAnnuler;
    public VBox myRoot;
    public HBox hbBoutons;



    //constructeur
    public LoginScene(Stage stage, boolean administrateur) {
        super(new VBox(), 500, 400);
        identifiant = new Label ("Identifiant");
        motDePasse = new Label ("Mot de passe");
        txtIdentifiant = new TextField();
        txtMotDePasse = new PasswordField();
        btnValider = new Button ("Valider");
        btnAnnuler = new Button ("Annuler");
        hbBoutons = new HBox();


        // style des boutons
        btnValider.setFont(POLICE_BOUTON_TEXTE);
        btnValider.setStyle(CONTOUR_BOUTON);
        btnValider.setPrefSize(100, 10);
        btnAnnuler.setFont(POLICE_BOUTON_TEXTE);
        btnAnnuler.setStyle(CONTOUR_BOUTON);
        btnAnnuler.setPrefSize(100, 10);

        //police labels
        identifiant.setFont(POLICE_BOUTON_TEXTE);
        motDePasse.setFont(POLICE_BOUTON_TEXTE);

        //on met les boutons dans la HBox
        hbBoutons.getChildren().addAll(btnValider, btnAnnuler);


        myRoot = ((VBox)this.getRoot());
        myRoot.setPadding(new Insets(10));
        myRoot.setSpacing(50);
        myRoot.setAlignment(Pos.CENTER);

        GridPane formulaireId = new GridPane();
        formulaireId.setVgap(20);
        formulaireId.setHgap(20);
        formulaireId.setAlignment(Pos.CENTER);

        //Reflection pour gridPane
        Reflection r = new Reflection();
        r.setFraction(0.7f);
        formulaireId.setEffect(r);

        //On crée des DropShadow effect
        DropShadow dropShadow = new DropShadow();
        dropShadow.setOffsetX(5);

        //On instancie des objets à afficher sur le panneau, on les stylise et on les positionne:
        Label labelTitre= new Label("Accès Administrateur");
        Label erreurIdentification = new Label( "");
        erreurIdentification.setFont(POLICE_BOUTON_TEXTE);

        //On applique le style et la taille de police définis dans l'interface StyleGeneral au titre en appelant les constantes :
        labelTitre.setFont(POLICE_TITRE);
        labelTitre.setStyle(GRAS);
        labelTitre.setStyle(POLICE_COULEUR);

        //On applique les DropShadow effect à nos éléments
        labelTitre.setEffect(dropShadow);

        myRoot.getChildren().add(labelTitre);
        myRoot.getChildren().add(formulaireId);
        myRoot.getChildren().add(erreurIdentification);
        myRoot.getChildren().add(hbBoutons);

        // couleur de fond
        myRoot.setStyle(COULEUR_FOND);

        hbBoutons.setAlignment(Pos.CENTER);
        hbBoutons.setSpacing(50);

        formulaireId.addRow(0, identifiant, txtIdentifiant);
        formulaireId.addRow(1, motDePasse, txtMotDePasse);


        // -----Actions Boutons ------
        btnValider.setOnAction(eventAction -> {
            if (Administrateur.IDENTIFIANT.equals(txtIdentifiant.getText()) && Administrateur.MOTDEPASSE.equals(txtMotDePasse.getText())) {
                AccueilUtilisateurScene accueilUtilisateurScene = new AccueilUtilisateurScene(stage,true);
                accueilUtilisateurScene.getStylesheets().add("style.css");
                stage.setScene(accueilUtilisateurScene);

            } else {
                erreurIdentification.setText("Il y a une erreur d'identification");
            }
        });

        btnAnnuler.setOnAction(eventAction -> {

            AccueilUtilisateurScene accueilUtilisateurScene = new AccueilUtilisateurScene(stage,false);
            accueilUtilisateurScene.getStylesheets().add("style.css");
            stage.setScene(accueilUtilisateurScene);

        });

        //Changement de couloeur au survol des buttons
        btnValider.setOnMouseEntered(new EventHandler<Event>() {

            @Override
            public void handle(Event arg0) {
                btnValider.setStyle(FOND_BOUTON);
            }
        });
        btnValider.setOnMouseExited(new EventHandler<Event>() {
            @Override
            public void handle(Event arg0) {
//				btnValider.setStyle("-fx-background-color:rgb(224, 224, 224)");
                btnValider.setStyle(CONTOUR_BOUTON);
            }
        });


        btnAnnuler.setOnMouseEntered(new EventHandler<Event>() {

            @Override
            public void handle(Event arg0) {
                btnAnnuler.setStyle(FOND_BOUTON);
            }
        });
        btnAnnuler.setOnMouseExited(new EventHandler<Event>() {
            @Override
            public void handle(Event arg0) {
//				btnAnnuler.setStyle("-fx-background-color:rgb(224, 224, 224)");
                btnAnnuler.setStyle(CONTOUR_BOUTON);
            }
        });



    }



    //getters et setters

    public Label getIdentifiant() {
        return identifiant;
    }

    public void setIdentifiant(Label identifiant) {
        this.identifiant = identifiant;
    }

    public Label getMotDePasse() {
        return motDePasse;
    }

    public void setMotDePasse(Label motDePasse) {
        this.motDePasse = motDePasse;
    }

    public TextField getTxtIdentifiant() {
        return txtIdentifiant;
    }

    public void setTxtIdentifiant(TextField txtIdentifiant) {
        this.txtIdentifiant = txtIdentifiant;
    }

    public PasswordField getTxtMotDePasse() {
        return txtMotDePasse;
    }

    public void setTxtMotDePasse(PasswordField txtMotDePasse) {
        this.txtMotDePasse = txtMotDePasse;
    }

    public Button getBtnValider() {
        return btnValider;
    }

    public void setBtnValider(Button btnValider) {
        this.btnValider = btnValider;
    }

    public Button getBtnAnnuler() {
        return btnAnnuler;
    }

    public void setBtnAnnuler(Button btnAnnuler) {
        this.btnAnnuler = btnAnnuler;
    }

    public VBox getMyRoot() {
        return myRoot;
    }

    public void setMyRoot(VBox myRoot) {
        this.myRoot = myRoot;
    }
}