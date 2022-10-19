package fr.isika.cda21.annuaire.tests;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import fr.isika.cda21.annuaire.models.ArbreBinaire;
import fr.isika.cda21.annuaire.models.GestionFichiers;
import fr.isika.cda21.annuaire.models.Stagiaire;

public class TesteurModif {

    public static void main(String[] args) {
        try {

            ArbreBinaire.modification(new Stagiaire("HURLIN","nathalie","05","CDA 21",2022),
                    new Stagiaire("Hurlin","Nathalie","05","AL 21",2023),
                    GestionFichiers.getRaf());

            List<Stagiaire> listeDeStagiaire = new ArrayList<Stagiaire>();
            ArbreBinaire.debutParcoursAlphabetique(listeDeStagiaire, GestionFichiers.getRaf());

            for(Stagiaire stagiaire : listeDeStagiaire) {
                System.out.println(stagiaire);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
