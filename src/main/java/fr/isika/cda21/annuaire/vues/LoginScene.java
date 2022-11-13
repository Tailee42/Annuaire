package fr.isika.cda21.annuaire.vues;

import fr.isika.cda21.annuaire.models.Administrateur;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class LoginScene extends Scene implements StyleGeneral{

    private final TextField txtIdentifiant;
    private final PasswordField txtMotDePasse;

    //constructeur
    public LoginScene(Stage stage) {
        super(new VBox(), 500, 400);
        Label identifiant = new Label("Identifiant");
        Label motDePasse = new Label("Mot de passe");
        txtIdentifiant = new TextField();
        txtMotDePasse = new PasswordField();
        StyledButton btnValider = new StyledButton("Valider");
        StyledButton btnAnnuler = new StyledButton("Annuler");
        HBox hbBoutons = new HBox();

        //On fixe la taille des champs de texte avec la constante créée dans le fichier StyleGeneral:
        txtIdentifiant.setPrefWidth(TAILLE_CHAMP_TEXTE);
        txtMotDePasse.setPrefWidth(TAILLE_CHAMP_TEXTE);

        //on met les boutons dans la HBox
        hbBoutons.getChildren().addAll(btnValider, btnAnnuler);

        VBox myRoot = ((VBox) this.getRoot());
        myRoot.setPadding(new Insets(10));
        myRoot.setSpacing(25);
        myRoot.setAlignment(Pos.CENTER);

        GridPane formulaireId = new GridPane();
        formulaireId.setVgap(20);
        formulaireId.setHgap(20);
        formulaireId.setAlignment(Pos.CENTER);

        //On instancie des objets à afficher sur le panneau, on les stylise et on les positionne:
        LabelTitre labelTitre= new LabelTitre("Accès Administrateur");
        Label erreurIdentification = new Label( "");
        erreurIdentification.setFont(POLICE_BOUTON_TEXTE);

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
    }

}