package fr.isika.cda21.annuaire.vues;

import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

public interface StyleGeneral {

    // police des boutons et du texte

    Font font = Font.font("Courier New" , FontWeight.BOLD, 14);
    public static final Font POLICE_BOUTON_TEXTE = (font);
    Font fontTitre = Font.font("Courier New", FontWeight.BOLD, 28);
    public static final Font POLICE_TITRE = (fontTitre);

    //taille des champs de texte
    public static final int TAILLE_CHAMP_TEXTE = 200;

    //choix du style et de la couleur de la police:
    public static final String POLICE_COULEUR = ("-fx-text-fill: rgb(247, 157, 79)");

    public static final String GRAS = ("-fx-font-weight: BOLD");

    public static final String CONTOUR_BOUTON = "-fx-border-color: rgb(247, 157, 79)";

    public static final String FOND_BOUTON = "-fx-background-color: rgb(247, 157, 79)";

    public static final String CONTOUR_TABLEAU = "-fx-border-color: rgb(0, 0, 0)";

    //couleur de fond des pop-ups
    public static final String COULEUR_FOND = "-fx-background-color: #A9A9A9";

}