package fr.isika.cda21.annuaire.vues;

import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

public interface StyleGeneral {

    // police des boutons et du texte
    Font font = Font.font("Courier New" , FontWeight.BOLD, 14);
    Font POLICE_BOUTON_TEXTE = (font);
    Font fontTitre = Font.font("Courier New", FontWeight.BOLD, 28);
    Font POLICE_TITRE = (fontTitre);

    //taille des champs de texte
    int TAILLE_CHAMP_TEXTE = 200;

    //choix du style et de la couleur de la police:
    String POLICE_COULEUR = ("-fx-text-fill: rgb(247, 157, 79)");
    String CONTOUR_BOUTON = "-fx-border-color: rgb(247, 157, 79)";
    String FOND_BOUTON = "-fx-background-color: rgb(247, 157, 79)";
    String CONTOUR_TABLEAU = "-fx-border-color: rgb(0, 0, 0)";

    //couleur de fond des popups
    String COULEUR_FOND = "-fx-background-color: #A9A9A9";

}