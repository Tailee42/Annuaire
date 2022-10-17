package fr.isika.cda21.annuaire.tests;

import fr.isika.cda21.annuaire.models.GestionFichiers;
import fr.isika.cda21.annuaire.models.Noeud;

import java.io.IOException;

public class TesteurAjoutDON {
    public static void main(String[] args) {
        try {
            GestionFichiers.verificationImportFichierDon();

            GestionFichiers.getRaf().seek(0);
            for (int i = 0; i < (GestionFichiers.getRaf().length() / Noeud.TAILLE_NOEUD_OCTETS); i++) {
                System.out.println(GestionFichiers.lectureNoeud());
            }

            GestionFichiers.fermetureAccessFile();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }
}
