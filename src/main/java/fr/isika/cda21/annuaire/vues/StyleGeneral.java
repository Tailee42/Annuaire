package fr.isika.cda21.annuaire.vues;

import javafx.scene.text.Font;

public interface StyleGeneral {

    // police des boutons et du texte
    public static final Font POLICE_BOUTON_TEXTE = (new Font("Courier New", 14));

    public static final Font POLICE_TITRE = (new Font("Courier New", 28));

    //choix du style et de la couleur de la police:
    public static final String POLICE_COULEUR = ("-fx-text-fill: rgb(247, 157, 79)");

    public static final String GRAS = ("-fx-font-weight: BOLD");

    public static final String CONTOUR_BOUTON = "-fx-border-color:rgb(247, 157, 79)";

    public static final String FOND_BOUTON = "-fx-background-color:rgb(247, 157, 79)";

    public static final String FOND_BACKGROUND = "-fx-background-color:rgb(250, 250, 250)";

    public static final String CONTOUR_TABLEAU = "-fx-background-color:rgb(0, 0, 0)";

    //couleur de fond des pop-ups
    public static final String COULEUR_FOND = ("-fx-background-color:#A9A9A9");

}