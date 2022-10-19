package fr.isika.cda21.annuaire.vues;

import fr.isika.cda21.annuaire.models.Administrateur;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;

public class LoginScene extends Scene {

    public Label identifiant;
    public Label motDePasse;
    public TextField txtIdentifiant;
    public PasswordField txtMotDePasse;
    public Button btnValider;
    public Button btnAnnuler;
    public VBox myRoot;
    public boolean administrateur;




    //constructeur
    public LoginScene() {
        super(new VBox(), 400, 200);
        identifiant = new Label ("Identifiant");
        motDePasse = new Label ("Mot de passe");
        txtIdentifiant = new TextField();
        txtMotDePasse = new PasswordField();
        btnValider = new Button ("Valider");
        btnAnnuler = new Button ("Annuler");

        myRoot = ((VBox)this.getRoot());
        myRoot.setPadding(new Insets(10));
        myRoot.setSpacing(10);
        myRoot.setAlignment(Pos.CENTER);

        GridPane formulaireId = new GridPane();
        formulaireId.setVgap(20);
        formulaireId.setHgap(20);
        formulaireId.setAlignment(Pos.CENTER);


        //On instancie des objets à afficher sur le panneau, on les stylise et on les positionne:
        Label labelTitre= new Label("Accès Administrateur");
        Label erreurIdentification = new Label( "");

        myRoot.getChildren().add(labelTitre);
        myRoot.getChildren().add(formulaireId);
        myRoot.getChildren().add(erreurIdentification);

        formulaireId.addRow(0, identifiant, txtIdentifiant);
        formulaireId.addRow(1, motDePasse, txtMotDePasse);
        formulaireId.addRow(2, btnValider, btnAnnuler);

        btnValider.setOnAction(eventAction -> {
            if (Administrateur.IDENTIFIANT.equals(txtIdentifiant.getText()) && Administrateur.MOTDEPASSE.equals(txtMotDePasse)) {
                administrateur = true;

                // TODO Retour à la scène Accueil Utilisateur


            } else {
                erreurIdentification.setText("Il y a une erreur d'identification");
            }
        });

        btnAnnuler.setOnAction(eventAction -> {

            // TODO Retour à la scène Accueil Utilisateur

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

    public boolean isAdministrateur() {
        return administrateur;
    }

    public void setAdministrateur(boolean administrateur) {
        this.administrateur = administrateur;
    }

    public VBox getMyRoot() {
        return myRoot;
    }

    public void setMyRoot(VBox myRoot) {
        this.myRoot = myRoot;
    }
}