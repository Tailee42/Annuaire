package fr.isika.cda21.annuaire.tests;

import fr.isika.cda21.annuaire.models.ArbreBinaire;
import fr.isika.cda21.annuaire.models.GestionFichiers;
import fr.isika.cda21.annuaire.models.Noeud;
import fr.isika.cda21.annuaire.models.Stagiaire;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/*
Attention pour l'utiliser :
	- supprimer le fichier .bin
	- lancer le testeur TesteurAjoutStagaire
	- lancer le testeurSupprimer
	-
 */


public class TesteurSupprimer {

	public static void main(String[] args) {
        try {
			List<Stagiaire> listeDeStagiaire = new ArrayList<>();
			ArbreBinaire.debutParcoursAlphabetique(listeDeStagiaire, GestionFichiers.getRaf());

			if (listeDeStagiaire.size() == 0 ) {
				System.out.println("Cette liste est vide");
			}

			for (Stagiaire stagiaire :
					listeDeStagiaire) {
				System.out.println(stagiaire);
			}

			ArbreBinaire.supprimerUnStagiaire(new Stagiaire("Lacroix", "Charles", "23", "AL10", 2018), GestionFichiers.getRaf());

			System.out.println();
			GestionFichiers.getRaf().seek(0);
			for (int i = 0; i < (GestionFichiers.getRaf().length() / Noeud.TAILLE_NOEUD_OCTETS); i++) {
				System.out.println(GestionFichiers.lectureNoeud());
			}

			System.out.println("Liste avec la suppression");

			List<Stagiaire> listeDeStagiaireAvecSuppression = new ArrayList<>();
					ArbreBinaire.debutParcoursAlphabetique(listeDeStagiaireAvecSuppression, GestionFichiers.getRaf());


			for (Stagiaire stagiaire :
					listeDeStagiaireAvecSuppression) {
				System.out.println(stagiaire);
			}


		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			
		}

	}

}
