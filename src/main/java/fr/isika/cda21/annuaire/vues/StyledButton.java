package fr.isika.cda21.annuaire.vues;

import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;


public class StyledButton extends Button implements StyleGeneral{

    public StyledButton(String texte, String nomFichierPNG){
        super(texte);
        this.setPrefSize(200, 10);
        this.setStyle(CONTOUR_BOUTON);

        Image imgRechercher = new Image((nomFichierPNG));
        ImageView imgViewRechercher = new ImageView(imgRechercher);
        imgViewRechercher.setFitHeight(19);
        imgViewRechercher.setPreserveRatio(true);
        this.setGraphic(imgViewRechercher);


        this.setOnMouseEntered(new EventHandler<Event>() {
            @Override
            public void handle(Event arg0) {
                setStyle(FOND_BOUTON);
            }
        });

        this.setOnMouseExited(new EventHandler<Event>() {
            @Override
            public void handle(Event arg0) {
                setStyle(CONTOUR_BOUTON);

            }
        });

    }


    public StyledButton(String texte){
        super(texte);
        this.setPrefSize(200, 10);
        this.setStyle(CONTOUR_BOUTON);

        this.setOnMouseEntered(new EventHandler<Event>() {
            @Override
            public void handle(Event arg0) {
                setStyle(FOND_BOUTON);
            }
        });

        this.setOnMouseExited(new EventHandler<Event>() {
            @Override
            public void handle(Event arg0) {
                setStyle(CONTOUR_BOUTON);

            }
        });

    }
}
