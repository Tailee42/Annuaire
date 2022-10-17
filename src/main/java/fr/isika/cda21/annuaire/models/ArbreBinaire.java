package fr.isika.cda21.annuaire.models;

import java.io.IOException;
import java.io.RandomAccessFile;

public class ArbreBinaire {

    //Méthodes spécifiques
    public static void ajouterUnStagiaire(Stagiaire stagaireAAjouter, RandomAccessFile raf) throws IOException {
        if (raf.length() != 0) {
            raf.seek(0);
            Noeud premierNoeud = GestionFichiers.lectureNoeud();
            premierNoeud.ajouterNoeud(stagaireAAjouter, raf);
        }
    }
}
