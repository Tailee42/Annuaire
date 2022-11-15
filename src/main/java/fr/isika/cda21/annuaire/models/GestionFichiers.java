package fr.isika.cda21.annuaire.models;

import java.io.*;

public class GestionFichiers {

    private  RandomAccessFile raf;

//    static {
//        try {
//            raf = new RandomAccessFile("src/main/resources/ecriturearbrebinaire.bin", "rw");
//        } catch (FileNotFoundException e) {
//            throw new RuntimeException(e);
//        }
//    }

    public GestionFichiers(String cheminFichier) throws FileNotFoundException {
        raf = new RandomAccessFile(cheminFichier, "rw");
    }

    //Méthodes Spécifiques
    public void fermetureAccessFile() throws IOException {
        raf.close();
    }

    public void verificationImportFichierDon(ArbreBinaire arbre) throws IOException {
        if (raf.length() == 0) {
            ecritureAPartirDuFichierDom(arbre);
        }
    }

    public void ecritureAPartirDuFichierDom(ArbreBinaire arbre) throws IOException {

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
                arbre.ajouterUnStagiaire(new Stagiaire(nom, prenom, departement, promo, anneeDeFormation));
            }

        }

        br.close();
        reader.close();
    }

    public Noeud lectureNoeud() throws IOException {
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

    private String lectureAttributStringStagiaire() throws IOException {
        String attribut = "";
        for (int i = 0; i < Stagiaire.TAILLE_MAX_STRING; i++) {
            attribut += raf.readChar();
        }
        return attribut;
    }

    //getters && setters
    public RandomAccessFile getRaf() {
        return raf;
    }

    public void setRaf(RandomAccessFile raf) {
        this.raf = raf;
    }
}

