package fr.isika.cda21.annuaire.vues;

import fr.isika.cda21.annuaire.models.ImpressionPDF;
import fr.isika.cda21.annuaire.models.Stagiaire;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;

import java.util.List;

public class TableStagiaireScene extends Scene implements StyleGeneral{

    // attributs
    private BorderPane myRoot;
    private Button boutonModifier;
    private Button boutonSupprimer;
    private Button boutonImprimer;
    private Button boutonRetour;
    private HBox hb;
    private AnchorPane monTableau;
    private Label titre;

    // constructeur
    public TableStagiaireScene(Stage stage, List<Stagiaire> listeDeStagiaires,Stagiaire criteres, Boolean administrateur) {

        super(new BorderPane(), 1000, 600);
        myRoot = (BorderPane) this.getRoot();

        myRoot.setPadding(new Insets(50, 50, 10, 50));

        boutonModifier = new Button("Modifier");
        boutonSupprimer = new Button("Supprimer");
        boutonImprimer = new Button("Imprimer");
        boutonRetour = new Button("Retour");
        hb = new HBox();
        monTableau = new AnchorPane();
        titre = new Label("Liste des stagiaires");
        titre.setFont(POLICE_TITRE);
        titre.setStyle(GRAS);
        titre.setStyle(POLICE_COULEUR);

        //On crée des DropShadow effect
        DropShadow dropShadow = new DropShadow();
        dropShadow.setOffsetX(5);
        dropShadow.setOffsetY(5);

        //On applique les DropShadow effect à nos éléments
        titre.setEffect(dropShadow);

        ObservableList<Stagiaire> observableStagiaires = FXCollections.observableArrayList(listeDeStagiaires);

        // on instancie une Tableview que l'on bind à la liste observable
        TableView<Stagiaire> tableView = new TableView<>(observableStagiaires);
        tableView.setEditable(true);

        // on instancie les colonnes de la tableview
        TableColumn<Stagiaire, String> nomCol = new TableColumn<Stagiaire, String>("Nom");
        nomCol.setMinWidth(100);

        // on donne le nom du stagiaire à la colonne
        nomCol.setCellValueFactory( // objet qui va construire la valeur à afficher dans la case
                new PropertyValueFactory<Stagiaire, String>("nom"));

        // prenom
        TableColumn<Stagiaire, String> prenomCol = new TableColumn<Stagiaire, String>("Prénom");
        prenomCol.setMinWidth(100);

        prenomCol.setCellValueFactory( // objet qui va construire la valeur à afficher dans la case
                new PropertyValueFactory<Stagiaire, String>("prenom"));

        // departement
        TableColumn<Stagiaire, String> departementCol = new TableColumn<Stagiaire, String>("Département");
        departementCol.setMinWidth(100);

        departementCol.setCellValueFactory( // objet qui va construire la valeur à afficher dans la case
                new PropertyValueFactory<Stagiaire, String>("departement"));

        // promo
        TableColumn<Stagiaire, String> promoCol = new TableColumn<Stagiaire, String>("Promo");
        promoCol.setMinWidth(100);

        promoCol.setCellValueFactory( // objet qui va construire la valeur à afficher dans la case
                new PropertyValueFactory<Stagiaire, String>("promo"));

        // année
        TableColumn<Stagiaire, String> anneeCol = new TableColumn<Stagiaire, String>("Année de formation");
        anneeCol.setMinWidth(100);

        anneeCol.setCellValueFactory( // objet qui va construire la valeur à afficher dans la case
                new PropertyValueFactory<Stagiaire, String>("anneeDeFormation"));

        // On ajoute les colonnes au tableview
        tableView.getColumns().addAll(nomCol, prenomCol, departementCol, promoCol, anneeCol);

        // on équilibre les colonnes du tableau pour qu'il prenne toute la place
        // disponible
        tableView.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);

        // on ajoute le tableview à la liste des Nodes enfants de l'Anchorpane
        monTableau.getChildren().add(tableView);

        // on définit la taille de l'anchorpane et de ses ancres
        monTableau.setPrefSize(300, 110);
        monTableau.setTopAnchor(tableView, 0.);
        monTableau.setLeftAnchor(tableView, 0.);
        monTableau.setRightAnchor(tableView, 0.);
        monTableau.setBottomAnchor(tableView, 0.);

        // dimension des boutons
        boutonModifier.setPrefSize(160, 10);
        boutonSupprimer.setPrefSize(160, 10);
        boutonImprimer.setPrefSize(160, 10);
        boutonRetour.setPrefSize(160, 10);

        //Pour faire apparitre les boutons supprimer ou modifier en fonction du compte
        boutonModifier.setVisible(administrateur);
        boutonSupprimer.setVisible(administrateur);


        // couleurs et style
        tableView.setStyle(CONTOUR_TABLEAU);

        boutonModifier.setStyle(CONTOUR_BOUTON);
        Image imgModifier = new Image(("Modifier.png"));
        ImageView imgViewModifier = new ImageView(imgModifier);
        imgViewModifier.setFitHeight(15);
        imgViewModifier.setPreserveRatio(true);
        boutonModifier.setGraphic(imgViewModifier);
        boutonModifier.setFont(POLICE_BOUTON_TEXTE);

        boutonSupprimer.setStyle(CONTOUR_BOUTON);
        Image imgSupprimer = new Image(("Supprimer.png"));
        ImageView imgViewSupprimer = new ImageView(imgSupprimer);
        imgViewSupprimer.setFitHeight(17);
        imgViewSupprimer.setPreserveRatio(true);
        boutonSupprimer.setGraphic(imgViewSupprimer);
        boutonSupprimer.setFont(POLICE_BOUTON_TEXTE);

        boutonImprimer.setStyle(CONTOUR_BOUTON);
        Image imgImprimer = new Image(("Imprimer.png"));
        ImageView imgViewImprimer = new ImageView(imgImprimer);
        imgViewImprimer.setFitHeight(17);
        imgViewImprimer.setPreserveRatio(true);
        boutonImprimer.setGraphic(imgViewImprimer);
        boutonImprimer.setFont(POLICE_BOUTON_TEXTE);

        boutonRetour.setStyle(CONTOUR_BOUTON);
        Image imgRetour = new Image(("Retour.png"));
        ImageView imgViewRetour = new ImageView(imgRetour);
        imgViewRetour.setFitHeight(15);
        imgViewRetour.setPreserveRatio(true);
        boutonRetour.setGraphic(imgViewRetour);
        boutonRetour.setFont(POLICE_BOUTON_TEXTE);


        // on ajoute les boutons à la HBox
        hb.getChildren().addAll(boutonModifier, boutonSupprimer, boutonImprimer, boutonRetour);

        // on dimensionne la HBox
        hb.setPrefSize(300, 60);

        // on centre les boutons de la HBox et on les espace
        hb.setAlignment(Pos.CENTER);
        hb.setSpacing(80);

        // on place l'anchorpane, la HBox et le label dans le borderpane + espacements entre les composants + centrage du titre
        myRoot.setCenter(monTableau);
        myRoot.setBottom(hb);
        myRoot.setTop(titre);
        myRoot.setStyle(COULEUR_FOND);

        BorderPane.setMargin(monTableau,(new Insets(5, 10, 20, 10)));
        BorderPane.setAlignment(titre, Pos.BASELINE_CENTER);

        //Actions des boutons
        boutonSupprimer.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                // creation du stagiaire a supprimer depuis la liste
                Stagiaire stagiaireASupprimer = tableView.getSelectionModel().getSelectedItem();
                if (stagiaireASupprimer != null) {
                    stage.setScene(new PopUpScene(stage, stagiaireASupprimer, criteres));

                } else {
                    // TODO: voir s'il est possible d'afficher le texte pour une durée donnée, ajout couleur
                    Label attention = new Label("Veuillez selectionner un stagiaire");
                    attention.setFont(POLICE_TITRE);
                    myRoot.setTop(attention);
                }
            }
        });

        boutonImprimer.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                ImpressionPDF pdf = new ImpressionPDF();
                Stage stage = (Stage) boutonImprimer.getScene().getWindow();

                try {
                    pdf.createPDF(listeDeStagiaires);
                    pdf.start(stage);
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            }
        });

        boutonModifier.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {

                Stagiaire stagiaireAModifier = tableView.getSelectionModel().getSelectedItem();
                if (stagiaireAModifier != null) {
                    ModificationScene modificationScene = new ModificationScene(stage, stagiaireAModifier, criteres);
                    modificationScene.getStylesheets().add("style.css");
                    stage.setScene(modificationScene);
                } else {
                    // TODO: voir s'il est possible d'afficher le texte pour une durée donnée, ajout couleur
                    Label attention = new Label("Veuillez selectionner un stagiaire");
                    attention.setFont(POLICE_TITRE);
                    myRoot.setTop(attention);
                }
            }

        });

        boutonRetour.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                AccueilUtilisateurScene accueilUtilisateurScene = new AccueilUtilisateurScene(stage, administrateur);
                accueilUtilisateurScene.getStylesheets().add("style.css");
                stage.setScene(accueilUtilisateurScene);
            }
        });


        //changement couleur bouton au passage de la souris
        boutonModifier.setOnMouseEntered(new EventHandler<Event>() { // OnMouseEntered = évenement qui va se produire
            // au passage de la souris

            @Override
            public void handle(Event arg0) {
                boutonModifier.setStyle(FOND_BOUTON);
            }
        });


        boutonModifier.setOnMouseExited(new EventHandler<Event>() {
            @Override
            public void handle(Event arg0) {
//				boutonModifier.setStyle("-fx-background-color:rgb(224, 224, 224)");
                boutonModifier.setStyle(CONTOUR_BOUTON);

            }
        });


        boutonSupprimer.setOnMouseEntered(new EventHandler<Event>() { // OnMouseEntered = évenement qui va se produire
            // au passage de la souris

            @Override
            public void handle(Event arg0) {
                boutonSupprimer.setStyle(FOND_BOUTON);
            }
        });
        boutonSupprimer.setOnMouseExited(new EventHandler<Event>() {
            @Override
            public void handle(Event arg0) {
//				boutonSupprimer.setStyle("-fx-background-color:rgb(224, 224, 224)");
                boutonSupprimer.setStyle(CONTOUR_BOUTON);
            }
        });

        boutonSupprimer.setOnMouseClicked(new EventHandler<Event>() { // OnMouseEntered = évenement qui va se produire
            // au passage de la souris

            @Override
            public void handle(Event arg0) {
                boutonSupprimer.setStyle(FOND_BOUTON);
            }
        });
        boutonSupprimer.setOnMouseReleased(new EventHandler<Event>() {
            @Override
            public void handle(Event arg0) {
//				boutonSupprimer.setStyle("-fx-background-color:rgb(224, 224, 224)");
                boutonSupprimer.setStyle(CONTOUR_BOUTON);
            }
        });



        boutonImprimer.setOnMouseEntered(new EventHandler<Event>() { // OnMouseEntered = évenement qui va se produire
            // au passage de la souris

            @Override
            public void handle(Event arg0) {
                boutonImprimer.setStyle(FOND_BOUTON);
            }
        });
        boutonImprimer.setOnMouseExited(new EventHandler<Event>() {
            @Override
            public void handle(Event arg0) {
//				boutonImprimer.setStyle("-fx-background-color:rgb(224, 224, 224)");
                boutonImprimer.setStyle(CONTOUR_BOUTON);
            }
        });



        boutonRetour.setOnMouseEntered(new EventHandler<Event>() { // OnMouseEntered = évenement qui va se produire
            // au passage de la souris

            @Override
            public void handle(Event arg0) {
                boutonRetour.setStyle(FOND_BOUTON);
            }
        });
        boutonRetour.setOnMouseExited(new EventHandler<Event>() {
            @Override
            public void handle(Event arg0) {
//				boutonRetour.setStyle("-fx-background-color:rgb(224, 224, 224)");
                boutonRetour.setStyle(CONTOUR_BOUTON);
            }
        });
    }

    // getters et setters
    public BorderPane getMyRoot() {
        return myRoot;
    }

    public void setMyRoot(BorderPane myRoot) {
        this.myRoot = myRoot;
    }

    public Button getBoutonModifier() {
        return boutonModifier;
    }

    public void setBoutonModifier(Button boutonModifier) {
        this.boutonModifier = boutonModifier;
    }

    public Button getBoutonSupprimer() {
        return boutonSupprimer;
    }

    public void setBoutonSupprimer(Button boutonSupprimer) {
        this.boutonSupprimer = boutonSupprimer;
    }

    public Button getBoutonImprimer() {
        return boutonImprimer;
    }

    public void setBoutonImprimer(Button boutonImprimer) {
        this.boutonImprimer = boutonImprimer;
    }

    public Button getBoutonRetour() {
        return boutonRetour;
    }

    public void setBoutonRetour(Button boutonRetour) {
        this.boutonRetour = boutonRetour;
    }

    public HBox getHb() {
        return hb;
    }

    public void setHb(HBox hb) {
        this.hb = hb;
    }

    public AnchorPane getMonTableau() {
        return monTableau;
    }

    public void setMonTableau(AnchorPane monTableau) {
        this.monTableau = monTableau;
    }

    public Label getTitre() {
        return titre;
    }

    public void setTitre(Label titre) {
        this.titre = titre;
    }

}
