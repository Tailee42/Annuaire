package fr.isika.cda21.annuaire.models;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.ArrayList;
import java.util.List;

public class ArbreBinaire {

    //Méthodes spécifiques
    public static void ajouterUnStagiaire(Stagiaire stagaireAAjouter, RandomAccessFile raf) throws IOException {
        if (raf.length() != 0) {
            raf.seek(0);
            Noeud premierNoeud = GestionFichiers.lectureNoeud();
            premierNoeud.ajouterNoeud(stagaireAAjouter, raf);
        }
    }

    public static void debutParcoursAlphabetique(List<Stagiaire> listeDeStagiaire, RandomAccessFile raf ) throws IOException {
        if (raf.length() != 0) {
            raf.seek(0);
            Noeud premierNoeud = GestionFichiers.lectureNoeud();
            premierNoeud.ordreAlphabetique(listeDeStagiaire, raf);
        }
    }

    //Recherche lancée depuis la classe arbre. Args list qui contiendra les resultats correspondant : stagiaire recherché, raf pour lire le fichier
    public static void debutRechercheStagiaire(List<Stagiaire> listeResultats,Stagiaire stagiaireRecherche, RandomAccessFile raf ) throws IOException {
        if (raf.length() != 0) {
            raf.seek(0);
            Noeud premierNoeud = GestionFichiers.lectureNoeud();
            premierNoeud.rechercheStagiaire(listeResultats, stagiaireRecherche, raf);
        } else {
            // TODO retour visuel, fichier vide
            System.out.println("Ce fichier ne contient aucun stagiaire.");
        }
    }

    //Recherche lancée depuis la classe arbre. Args list qui contiendra les resultats correspondant : stagiaire recherché, raf pour lire le fichier
    public static void dbtRechAv(List<Stagiaire> rechercheAvancee ,Stagiaire stagiaireToFind, RandomAccessFile raf ) throws IOException {
        List<Stagiaire> ordreAlpha = new ArrayList<>();

        if (raf.length() != 0) {
            raf.seek(0);
            Noeud premierNoeud = GestionFichiers.lectureNoeud();
            premierNoeud.ordreAlphabetique(ordreAlpha, raf);
            for(Stagiaire stagiaire : ordreAlpha) {
                System.out.println(stagiaire);
            }
            for(Stagiaire stagiaire : ordreAlpha) {
                stagiaire.rechercheAvancee(rechercheAvancee, stagiaireToFind);
            }
        } else {
            //retour visuel, fichier vide
            System.out.println("Aucun stagiaire ne correspond aux critères.");
        }
    }

}
