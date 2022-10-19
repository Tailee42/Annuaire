package fr.isika.cda21.annuaire.tests;

import fr.isika.cda21.annuaire.models.ArbreBinaire;
import fr.isika.cda21.annuaire.models.GestionFichiers;
import fr.isika.cda21.annuaire.models.Stagiaire;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class TesteurParcoursRechercheNom {
    public static void main(String[] args) {
        try {

            GestionFichiers.getRaf().seek(0);

            //************************ Test Affichage ordre alphabetique
            List<Stagiaire> listeDeStagiaire = new ArrayList<>();
            ArbreBinaire.debutParcoursAlphabetique(listeDeStagiaire,
                    GestionFichiers.getRaf());

            for (Stagiaire stagiaire : listeDeStagiaire) {
                System.out.println(stagiaire);
            }

            // ********************** Test Recherche classique nom *******************************
            List<Stagiaire> listeResultats = new ArrayList<>();

            ArbreBinaire.debutRechercheStagiaire(listeResultats, new Stagiaire("TRAN", "", "", "", 0),GestionFichiers.getRaf());

            for (Stagiaire stagiaire : listeResultats) {
                System.out.println("Trouvé : " + stagiaire);
            }

            //	**************************** Test Recherche Avancée *******************************************
            List<Stagiaire> rechercheAvancee = new ArrayList<>();

            ArbreBinaire.dbtRechAv(rechercheAvancee, new Stagiaire(null, null,null,"ATOD 17",0 ), GestionFichiers.getRaf());

            if(rechercheAvancee.size()== 0) {
                System.out.println("La liste est vide.");
            }


            for(Stagiaire stagiaire : rechercheAvancee) {
                System.out.println("Trouvé : "+stagiaire);
            }

            GestionFichiers.fermetureAccessFile();

        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }
}