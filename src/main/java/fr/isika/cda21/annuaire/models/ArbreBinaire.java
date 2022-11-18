package fr.isika.cda21.annuaire.models;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ArbreBinaire {

    private GestionFichiers rafFichierDom ;

    public ArbreBinaire(String nomFichier) throws FileNotFoundException {
        rafFichierDom = new GestionFichiers(nomFichier);
    }

    //Méthodes spécifiques
    public void ajouterUnStagiaire(Stagiaire stagaireAAjouter) throws IOException {
        if (rafFichierDom.getRaf().length() != 0) {
            rafFichierDom.getRaf().seek(0);
            Noeud premierNoeud = rafFichierDom.lectureNoeud();
            premierNoeud.ajouterNoeud(stagaireAAjouter, rafFichierDom);
        } else {
            rafFichierDom.getRaf().seek(0);
            Noeud premierNoeud = new Noeud(stagaireAAjouter, -1, 0);
            premierNoeud.ecrireNoeudBinaire(rafFichierDom.getRaf());
        }
    }

    public  void debutParcoursAlphabetique(List<Stagiaire> listeDeStagiaire) throws IOException {
        if (rafFichierDom.getRaf().length() != 0) {
            rafFichierDom.getRaf().seek(0);
            Noeud premierNoeud = rafFichierDom.lectureNoeud();
            premierNoeud.ordreAlphabetique(listeDeStagiaire, rafFichierDom);
        }
    }

    //Recherche lancée depuis la classe arbre. Args list qui contiendra les resultats
    // correspondant : stagiaire recherché, raf pour lire le fichier
    public void debutRechercheStagiaire(List<Stagiaire> listeResultats,Stagiaire stagiaireRecherche) throws IOException {
        if (rafFichierDom.getRaf().length() != 0) {
            rafFichierDom.getRaf().seek(0);
            Noeud premierNoeud = rafFichierDom.lectureNoeud();
            premierNoeud.rechercheStagiaire(listeResultats, stagiaireRecherche, rafFichierDom);
        } else {
            // TODO retour visuel, fichier vide
            System.out.println("Ce fichier ne contient aucun stagiaire.");
        }
    }

    //Recherche lancée depuis la classe arbre. Args list qui contiendra les resultats correspondant : stagiaire recherché, raf pour lire le fichier
    public void dbtRechAv(List<Stagiaire> rechercheAvancee ,Stagiaire stagiaireToFind) throws IOException {
        List<Stagiaire> ordreAlpha = new ArrayList<>();

        if (rafFichierDom.getRaf().length() != 0) {
            rafFichierDom.getRaf().seek(0);
            Noeud premierNoeud = rafFichierDom.lectureNoeud();
            premierNoeud.ordreAlphabetique(ordreAlpha, rafFichierDom);
            for(Stagiaire stagiaire : ordreAlpha) {
                stagiaire.rechercheAvancee(rechercheAvancee, stagiaireToFind);
            }
        } else {
            //retour visuel, fichier vide
            System.out.println("Aucun stagiaire ne correspond aux critères.");
        }
    }

    public void supprimerUnStagiaire(Stagiaire stagiaireASupprimer) throws IOException {
        if (rafFichierDom.getRaf().length() != 0) {
            rafFichierDom.getRaf().seek(0);
            Noeud premierNoeud = rafFichierDom.lectureNoeud();
            premierNoeud.supprimerNoeud(stagiaireASupprimer, rafFichierDom);
        }
    }

    public  void modification(Stagiaire stagiaireAModifier, Stagiaire stagiaireAJour)
            throws IOException {

        if (stagiaireAModifier.compareTo(stagiaireAJour) != 0) {
            if (rafFichierDom.getRaf().length() != 0) {
                ajouterUnStagiaire(stagiaireAJour);
                supprimerUnStagiaire(stagiaireAModifier);
            }
        }

    }

    public GestionFichiers getRafFichierDom() {
        return rafFichierDom;
    }

    public void setRafFichierDom(GestionFichiers rafFichierDom) {
        this.rafFichierDom = rafFichierDom;
    }
}