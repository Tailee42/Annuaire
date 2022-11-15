package fr.isika.cda21.annuaire.tests;

import fr.isika.cda21.annuaire.models.ArbreBinaire;
import fr.isika.cda21.annuaire.models.Noeud;

import java.io.FileNotFoundException;
import java.io.IOException;

public class TesteurAjoutDON {
    public static void main(String[] args) throws FileNotFoundException {

        ArbreBinaire arbre = new ArbreBinaire("src/main/resources/ecriturearbrebinaire.bin");

        try {
            arbre.getRafFichierDom().verificationImportFichierDon(arbre);

            arbre.getRafFichierDom().getRaf().seek(0);
            for (int i = 0; i < (arbre.getRafFichierDom().getRaf().length() / Noeud.TAILLE_NOEUD_OCTETS); i++) {
                System.out.println(arbre.getRafFichierDom().lectureNoeud());
            }

            arbre.getRafFichierDom().fermetureAccessFile();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }
}