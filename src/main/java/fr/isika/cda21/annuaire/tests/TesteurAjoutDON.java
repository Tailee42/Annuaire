package fr.isika.cda21.annuaire.tests;

import fr.isika.cda21.annuaire.models.GestionFichier;
import fr.isika.cda21.annuaire.models.Noeud;

import java.io.IOException;

public class TesteurAjoutDON {
    public static void main(String[] args) {
        try {
            GestionFichier.verificationImportFichierDon();

            GestionFichier.getRaf().seek(0);
            for (int i = 0; i < (GestionFichier.getRaf().length()/ Noeud.TAILLE_NOEUD_OCTETS); i++ ){
                System.out.println(GestionFichier.lectureNoeud());
            }

            GestionFichier.fermetureAccessFile();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }
}
