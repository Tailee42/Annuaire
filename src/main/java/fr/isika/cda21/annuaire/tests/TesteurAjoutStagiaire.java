package fr.isika.cda21.annuaire.tests;

import fr.isika.cda21.annuaire.models.ArbreBinaire;
import fr.isika.cda21.annuaire.models.GestionFichier;
import fr.isika.cda21.annuaire.models.Noeud;
import fr.isika.cda21.annuaire.models.Stagiaire;

import java.io.IOException;

public class TesteurAjoutStagiaire {

    public static void main(String[] args) {
        try {

            ArbreBinaire.ajouterUnStagiaire(new Stagiaire("Hurlin", "Nathalie", "05", "CDA 21", 2022), GestionFichier.getRaf());
            ArbreBinaire.ajouterUnStagiaire(new Stagiaire("LAcroix", "Charles", "23", "AL10", 2018),GestionFichier.getRaf());
            ArbreBinaire.ajouterUnStagiaire(new Stagiaire("Boucher", "Caroline", "19", "BOBI 4", 2016),GestionFichier.getRaf());
            ArbreBinaire.ajouterUnStagiaire(new Stagiaire("Bernaudon", "Jean-Marc", "76", "ARD 4", 2015),GestionFichier.getRaf());
            ArbreBinaire.ajouterUnStagiaire(new Stagiaire("Pasquali", "Séverine", "56", "GHI 12", 2017),GestionFichier.getRaf());
            ArbreBinaire.ajouterUnStagiaire(new Stagiaire("Crocq", "Camille", "01", "BOBI 12", 2017),GestionFichier.getRaf());

            GestionFichier.getRaf().seek(0);
            for (int i = 0; i < (GestionFichier.getRaf().length()/ Noeud.TAILLE_NOEUD_OCTETS); i++ ){
                System.out.println(GestionFichier.lectureNoeud());
            }


            /*GestionFichier.getRaf().seek(0);
            Noeud racine = GestionFichier.lectureNoeud();
            System.out.println(new Stagiaire("Hurlin", "Nathalie", "05", "CDA 21", 2022).compareTo(racine.getStagiaire()));*/


            GestionFichier.fermetureAccessFile();


        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
