package fr.isika.cda21.annuaire.vues;

import javafx.scene.control.TextField;

import static fr.isika.cda21.annuaire.vues.StyleGeneral.TAILLE_CHAMP_TEXTE;

public class StyledTextField extends TextField {

    public StyledTextField() {
        super();
        this.setPrefWidth(TAILLE_CHAMP_TEXTE);
    }
}
