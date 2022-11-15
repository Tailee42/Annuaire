package fr.isika.cda21.annuaire.vues;

import fr.isika.cda21.annuaire.models.ImpressionPDF;
import fr.isika.cda21.annuaire.models.Stagiaire;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

import java.io.FileNotFoundException;
import java.util.List;

public class TableStagiaireScene extends Scene implements StyleGeneral{

    // attributs
    private final BorderPane myRoot;
    private final StyledButton boutonModifier;
    private final StyledButton boutonSupprimer;
    private final StyledButton boutonImprimer;
    private final StyledButton boutonRetour;

    // constructeur
    public TableStagiaireScene(Stage stage, List<Stagiaire> listeDeStagiaires,Stagiaire criteres, Boolean administrateur) {

        super(new BorderPane(), 1000, 600);
        myRoot = (BorderPane) this.getRoot();

        myRoot.setPadding(new Insets(50, 50, 10, 50));

        boutonModifier = new StyledButton("Modifier", "Modifier.png");
        boutonSupprimer = new StyledButton("Supprimer", "Supprimer.png");
        boutonImprimer = new StyledButton("Imprimer", "Imprimer.png");
        boutonRetour = new StyledButton("Retour", "Retour.png");
        HBox hb = new HBox();
        AnchorPane monTableau = new AnchorPane();
        LabelTitre titre = new LabelTitre("Liste des stagiaires");

        ObservableList<Stagiaire> observableStagiaires = FXCollections.observableArrayList(listeDeStagiaires);

        // on instancie une Tableview que l'on bind à la liste observable
        TableView<Stagiaire> tableView = new TableView<>(observableStagiaires);
        tableView.setEditable(true);

        // on instancie les colonnes de la tableview
        TableColumn<Stagiaire, String> nomCol = new TableColumn<Stagiaire, String>("Nom");

        // on donne le nom du stagiaire à la colonne
        nomCol.setCellValueFactory(new PropertyValueFactory<Stagiaire, String>("nom")); // objet qui va construire la valeur à afficher dans la case

        // prenom
        TableColumn<Stagiaire, String> prenomCol = new TableColumn<Stagiaire, String>("Prénom");
        prenomCol.setCellValueFactory(new PropertyValueFactory<Stagiaire, String>("prenom")); // objet qui va construire la valeur à afficher dans la case

        // departement
        TableColumn<Stagiaire, String> departementCol = new TableColumn<Stagiaire, String>("Département");
        departementCol.setCellValueFactory( new PropertyValueFactory<Stagiaire, String>("departement"));

        // promo
        TableColumn<Stagiaire, String> promoCol = new TableColumn<Stagiaire, String>("Promotion");
        promoCol.setCellValueFactory(new PropertyValueFactory<Stagiaire, String>("promo"));

        // année
        TableColumn<Stagiaire, String> anneeCol = new TableColumn<Stagiaire, String>("Année de formation");
        anneeCol.setCellValueFactory(new PropertyValueFactory<Stagiaire, String>("anneeDeFormation"));

        // On ajoute les colonnes au tableview
        tableView.getColumns().addAll(nomCol, prenomCol, departementCol, promoCol, anneeCol);

        // on équilibre les colonnes du tableau pour qu'il prenne toute la place disponible
        tableView.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);

        // on ajoute le tableview à la liste des Nodes enfants de l'Anchorpane
        monTableau.getChildren().add(tableView);

        // on définit la taille de l'anchorpane et de ses ancres
        AnchorPane.setTopAnchor(tableView, 0.);
        AnchorPane.setLeftAnchor(tableView, 0.);
        AnchorPane.setRightAnchor(tableView, 0.);
        AnchorPane.setBottomAnchor(tableView, 0.);

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
        monTableau.setStyle(CONTOUR_TABLEAU);

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
        actionBtnSupprimer(tableView, stage, criteres);
        actionBtnImprimer(listeDeStagiaires);
        actionBtnModifer(tableView, stage, criteres);
        actionBtnRetour(stage, administrateur);

    }

    private void actionBtnSupprimer(TableView<Stagiaire> tableView, Stage stage, Stagiaire criteres) {
        boutonSupprimer.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                // creation du stagiaire a supprimer depuis la liste
                Stage stagePopup = new Stage();

                Stagiaire stagiaireASupprimer = tableView.getSelectionModel().getSelectedItem();
                if (stagiaireASupprimer != null) {

                    stagePopup.setResizable(false);
                    stagePopup.setTitle("ANNUAIRE");
                    try {
                        stagePopup.setScene(new PopUpScene(stagePopup, stage, stagiaireASupprimer, criteres));
                    } catch (FileNotFoundException e) {
                        throw new RuntimeException(e);
                    }
                    stagePopup.getIcons().add(new Image("LogoIsika.jpg"));
                    stagePopup.show();
                } else {
                    // TODO: voir s'il est possible d'afficher le texte pour une durée donnée, ajout couleur
                    Label attention = new Label("Veuillez selectionner un stagiaire");
                    attention.setFont(POLICE_TITRE);
                    myRoot.setTop(attention);
                }
            }
        });
    }

    private void actionBtnImprimer(List<Stagiaire> listeDeStagiaires) {
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
    }

    private void actionBtnModifer(TableView<Stagiaire> tableView, Stage stage, Stagiaire criteres) {
        boutonModifier.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {

                Stagiaire stagiaireAModifier = tableView.getSelectionModel().getSelectedItem();
                if (stagiaireAModifier != null) {
                    ModificationScene modificationScene = null;
                    try {
                        modificationScene = new ModificationScene(stage, stagiaireAModifier, criteres);
                    } catch (FileNotFoundException e) {
                        throw new RuntimeException(e);
                    }
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
    }

    private void actionBtnRetour(Stage stage, Boolean administrateur) {
        boutonRetour.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                AccueilUtilisateurScene accueilUtilisateurScene = null;
                try {
                    accueilUtilisateurScene = new AccueilUtilisateurScene(stage, administrateur);
                } catch (FileNotFoundException e) {
                    throw new RuntimeException(e);
                }
                accueilUtilisateurScene.getStylesheets().add("style.css");
                stage.setScene(accueilUtilisateurScene);
            }
        });
    }

}
