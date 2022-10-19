package fr.isika.cda21.annuaire.tests;

import com.itextpdf.text.DocumentException;

import fr.isika.cda21.annuaire.models.ArbreBinaire;
import fr.isika.cda21.annuaire.models.GestionFichiers;
import fr.isika.cda21.annuaire.models.ImpressionPDF;
import fr.isika.cda21.annuaire.models.Stagiaire;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class TestLibrairiePDF {
    public static final String DESTINATION = "src/main/resources/hello_word.pdf";

    public static void main(String[] args) {
        try {
            List<Stagiaire> listeRecherche = new ArrayList<>();
            Stagiaire criteresRecherche = new Stagiaire(null, null,"75","",0 );
            ArbreBinaire.dbtRechAv(listeRecherche, criteresRecherche, GestionFichiers.getRaf());

            ImpressionPDF.createPDF(listeRecherche, criteresRecherche);


        } catch (IOException | DocumentException e) {
            throw new RuntimeException(e);
        }
    }

}