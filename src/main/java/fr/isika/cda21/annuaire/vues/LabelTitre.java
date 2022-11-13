package fr.isika.cda21.annuaire.vues;

import javafx.scene.control.Label;
import javafx.scene.effect.DropShadow;

import static fr.isika.cda21.annuaire.vues.StyleGeneral.POLICE_COULEUR;
import static fr.isika.cda21.annuaire.vues.StyleGeneral.POLICE_TITRE;

public class LabelTitre extends Label {

    public LabelTitre() {
        super();
        this.setFont(POLICE_TITRE);
        this.setStyle(POLICE_COULEUR);

        //On crée des DropShadow effect
        DropShadow dropShadow = new DropShadow();
        dropShadow.setOffsetX(5);
        dropShadow.setOffsetY(5);

        //On applique les DropShadow effect à nos éléments
        this.setEffect(dropShadow);
    }

    public LabelTitre(String name) {
        super(name);
        this.setFont(POLICE_TITRE);
        this.setStyle(POLICE_COULEUR);

        //On crée des DropShadow effect
        DropShadow dropShadow = new DropShadow();
        dropShadow.setOffsetX(5);
        dropShadow.setOffsetY(5);

        //On applique les DropShadow effect à nos éléments
        this.setEffect(dropShadow);

    }
}
