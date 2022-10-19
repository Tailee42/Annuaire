package fr.isika.cda21.annuaire.models;

import java.io.*;

public class GestionFichiers {

    private static RandomAccessFile raf;

    static {
        try {
            raf = new RandomAccessFile("src/main/resources/ecriturearbrebinaire.bin", "rw");
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    //Méthodes Spécifiques
    public static void fermetureAccessFile() throws IOException {
        raf.close();
    }

    public static void verificationImportFichierDon() throws IOException {
        if (raf.length() == 0) {
            GestionFichiers.ecritureAPartirDuFichierDom();
        }
    }

    public static void ecritureAPartirDuFichierDom() throws IOException {

        FileReader reader = new FileReader("src/main/resources/STAGIAIRES.DON");
        BufferedReader br = new BufferedReader(reader);

        String nom = br.readLine().trim();
        String prenom = br.readLine().trim();
        String departement = br.readLine().trim();
        String promo = br.readLine().trim();
        int anneeDeFormation = Integer.parseInt(br.readLine().trim());
        br.readLine();

        new Noeud(new Stagiaire(nom, prenom, departement, promo, anneeDeFormation)).ecrireNoeudBinaire(raf);

        while (reader.ready()) {
            nom = br.readLine().trim();
            prenom = br.readLine().trim();
            departement = br.readLine().trim();
            promo = br.readLine().trim();
            anneeDeFormation = Integer.parseInt(br.readLine().trim());
            br.readLine();

            if (!nom.equals("")) {
                ArbreBinaire.ajouterUnStagiaire(new Stagiaire(nom, prenom, departement, promo, anneeDeFormation), raf);
            }

        }

        br.close();
        reader.close();
    }

    public static Noeud lectureNoeud() throws IOException {
        String nom = lectureAttributStringStagiaire();
        String prenom = lectureAttributStringStagiaire();
        String departement = lectureAttributStringStagiaire();
        String promo = lectureAttributStringStagiaire();
        int anneeDeFormation = raf.readInt();

        int listeChainee = raf.readInt();
        int filsGauche = raf.readInt();
        int filsDroit = raf.readInt();

        Stagiaire stagiaireLu = new Stagiaire(nom, prenom, departement, promo, anneeDeFormation);
        return new Noeud(stagiaireLu, listeChainee, filsGauche, filsDroit);
    }

    private static String lectureAttributStringStagiaire() throws IOException {
        String attribut = "";
        for (int i = 0; i < Stagiaire.TAILLE_MAX_STRING; i++) {
            attribut += raf.readChar();
        }
        return attribut;
    }

    //getters && setters
    public static RandomAccessFile getRaf() {
        return raf;
    }

    public static void setRaf(RandomAccessFile raf) {
        GestionFichiers.raf = raf;
    }
}

