package fr.isika.cda21.annuaire.vues;

import fr.isika.cda21.annuaire.models.Stagiaire;
import fr.isika.cda21.annuaire.tests.StagiaireDaoTest;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;

public class TableStagiaireScene extends Scene {

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
    public TableStagiaireScene() {

        super(new BorderPane(), 800, 500);
        myRoot = (BorderPane) this.getRoot();

        myRoot.setPadding(new Insets(50, 50, 10, 50));

        boutonModifier = new Button("Modifier");
        boutonSupprimer = new Button("Supprimer");
        boutonImprimer = new Button("Imprimer");
        boutonRetour = new Button("Retour");
        hb = new HBox();
        monTableau = new AnchorPane();
        titre = new Label("Résultat de la recherche");

        StagiaireDaoTest dao = new StagiaireDaoTest();

        ObservableList<Stagiaire> observableStagiaires = FXCollections.observableArrayList(dao.getStagiaires());

        // on instancie une Tableview que l'on bind à la liste observable
        TableView<Stagiaire> tableView = new TableView<>(observableStagiaires);
        tableView.setEditable(true);

        // on instancie les colonnes de la tableview
        TableColumn<Stagiaire, String> nomCol = new TableColumn<Stagiaire, String>("Nom");
        nomCol.setMinWidth(100);

        // on donne le nom du stagiaire à la colonne
        nomCol.setCellValueFactory( // objet qui va construire la valeur à afficher dans la case
                new PropertyValueFactory<Stagiaire, String>("Nom"));

        // prenom
        TableColumn<Stagiaire, String> prenomCol = new TableColumn<Stagiaire, String>("Prénom");
        prenomCol.setMinWidth(100);

        prenomCol.setCellValueFactory( // objet qui va construire la valeur à afficher dans la case
                new PropertyValueFactory<Stagiaire, String>("Prenom"));

        // departement
        TableColumn<Stagiaire, String> departementCol = new TableColumn<Stagiaire, String>("Département");
        departementCol.setMinWidth(100);

        departementCol.setCellValueFactory( // objet qui va construire la valeur à afficher dans la case
                new PropertyValueFactory<Stagiaire, String>("Departement"));

        // promo
        TableColumn<Stagiaire, String> promoCol = new TableColumn<Stagiaire, String>("Promo");
        promoCol.setMinWidth(100);

        promoCol.setCellValueFactory( // objet qui va construire la valeur à afficher dans la case
                new PropertyValueFactory<Stagiaire, String>("Promo"));

        // année
        TableColumn<Stagiaire, String> anneeCol = new TableColumn<Stagiaire, String>("Année de formation");
        anneeCol.setMinWidth(100);

        anneeCol.setCellValueFactory( // objet qui va construire la valeur à afficher dans la case
                new PropertyValueFactory<Stagiaire, String>("AnneeDeFormation"));

        // On ajoute les colonnes au tableview
        tableView.getColumns().addAll(nomCol, prenomCol, departementCol, promoCol, anneeCol);

        // on équilibre les colonnes du tableau pour qu'il prenne toute la place
        // disponible
        tableView.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);

        // on ajoute le tableview à la liste des Nodes enfants de l'Anchorpane
        monTableau.getChildren().add(tableView);

        // on définit la taille de l'anchorpane et de ses ancres
        monTableau.setPrefSize(300, 100);
        monTableau.setTopAnchor(tableView, 5.);
        monTableau.setLeftAnchor(tableView, 5.);
        monTableau.setRightAnchor(tableView, 5.);
        monTableau.setBottomAnchor(tableView, 5.);

        // dimension des boutons
        boutonModifier.setPrefSize(80, 10);
        boutonSupprimer.setPrefSize(80, 10);
        boutonImprimer.setPrefSize(80, 10);
        boutonRetour.setPrefSize(80, 10);

        // on ajoute les boutons à la HBox
        hb.getChildren().addAll(boutonModifier, boutonSupprimer, boutonImprimer, boutonRetour);

        // on dimensionne la HBox
        hb.setPrefSize(300, 60);

        // on centre les boutons de la HBox et on les espace
        hb.setAlignment(Pos.CENTER);
        hb.setSpacing(100);

        // on place l'anchorpane, la HBox et le label dans le borderpane + espacements entre les composants + centrage du titre
        myRoot.setCenter(monTableau);
        myRoot.setBottom(hb);
        myRoot.setTop(titre);
        myRoot.setMargin(monTableau,(new Insets(5, 10, 20, 10)));
        myRoot.setAlignment(titre, Pos.BASELINE_CENTER);

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
