package fr.isika.cda21.annuaire.vues;

import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;


public class LoginScene extends Scene {

    public LoginScene(Parent root) {
        super(root);
    }


    //attributs
    public Label identifiant;
    public Label motDePasse;
    public TextField txtIdentifiant;
    public TextField txtMotDePasse;
    public Button btnValider;
    public Button btnAnnuler;
    public GridPane root;


    //constructeur
    public LoginScene() {
        super(new GridPane(), 400, 200);
        identifiant = new Label("Identifiant");
        motDePasse = new Label("Mot de passe");
        txtIdentifiant = new TextField();
        txtMotDePasse = new TextField();
        btnValider = new Button("Valider");
        btnAnnuler = new Button("Annuler");
        root = ((GridPane) this.getRoot());

        //On instancie des objets à afficher sur le panneau, on les stylise et on les positionne:
        Label labelTitre= new Label("Accès Administrateur");
//		label.setTextFill(Color.HOTPINK);
// 	 	label.setFont(Font.font("Brush Script MT", 35));
//		label.relocate(300, 450);

        root.addRow(0, labelTitre);
        root.addRow(1, identifiant, txtIdentifiant);
        root.addRow(2, motDePasse, txtMotDePasse);
        root.addRow(3, btnValider, btnAnnuler);

        btnValider.setOnAction(eventAction -> {
            Label myText = new Label(txtIdentifiant.getText() + " " + txtMotDePasse.getText());
            root.addRow(3, myText);
        });

        btnAnnuler.setOnAction(eventAction -> {
            Label myText = new Label(txtIdentifiant.getText() + " " + txtMotDePasse.getText());
            root.addRow(3, myText);
        });

        root.setPadding(new Insets(10, 10, 10, 10));
        root.setVgap(20);
        root.setHgap(20);

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
        motDePasse = motDePasse;
    }

    public TextField getTxtIdentifiant() {
        return txtIdentifiant;
    }

    public void setTxtLogin(TextField txtIdentifiant) {
        this.txtIdentifiant = txtIdentifiant;
    }

    public TextField getTxtMdp() {
        return txtMotDePasse;
    }

    public void setTxtMotDePasse(TextField txtMotDePasse) {
        this.txtMotDePasse = txtMotDePasse;
    }

    public Button getSoumettre() {
        return btnValider;
    }

    public void setSoumettre(Button soumettre) {
        this.btnValider = soumettre;
        this.btnAnnuler = soumettre;
    }
}

