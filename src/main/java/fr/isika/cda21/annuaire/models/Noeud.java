package fr.isika.cda21.annuaire.models;

import java.io.IOException;
import java.io.RandomAccessFile;

public class Noeud {

    //attributs

    private Stagiaire stagiaire;
    private int filsGauche;
    private int filsDroit;
    public static final int TAILLE_FILS_GAUCHE_OCTETS = 4;
    public static final int TAILLE_FILS_DROIT_OCTETS = 4;
    public static final int TAILLE_NOEUD_OCTETS = Stagiaire.TAILLE_STAGIAIRE_OCTETS + TAILLE_FILS_GAUCHE_OCTETS +TAILLE_FILS_DROIT_OCTETS;

    //Constructeur

    public Noeud(Stagiaire stagiaire, int filsGauche, int filsDroit) {
        super();
        this.stagiaire = stagiaire;
        this.filsGauche = filsGauche;
        this.filsDroit = filsDroit;
    }

    public Noeud(Stagiaire stagiaire) {
        super();
        this.stagiaire = stagiaire;
        this.filsGauche = -1;
        this.filsDroit = -1;
    }

    //méthodes spécifiques

    @Override
    public String toString() {
        return "{" +
                "stagiaire=" + stagiaire +
                ", filsGauche=" + filsGauche +
                ", filsDroit=" + filsDroit +
                '}';
    }

    public void ecrireNoeudBinaire(RandomAccessFile raf) throws IOException {
        this.stagiaire.ecritureStagiaireBinaire(raf);
        raf.writeInt(filsGauche);
        raf.writeInt(filsDroit);
    }

    public void ajouterNoeud(Stagiaire stagiaireAAjouter, RandomAccessFile raf) throws IOException {
        if (this.stagiaire.compareTo(stagiaireAAjouter) == 0) {

            // TODO Faire une sortie utilisateur
            System.out.println("Il existe déjà");

        } else if(this.stagiaire.compareTo(stagiaireAAjouter) < 0){
            if(this.filsGauche == -1) {
                int indexStagiaireAAjouter = (int) (raf.length() / TAILLE_NOEUD_OCTETS);
                raf.seek(raf.getFilePointer()-(TAILLE_FILS_GAUCHE_OCTETS+TAILLE_FILS_DROIT_OCTETS));
                raf.writeInt(indexStagiaireAAjouter);
                raf.seek(raf.length());
                new Noeud(stagiaireAAjouter).ecrireNoeudBinaire(raf);
            } else  {
                raf.seek(this.filsGauche*TAILLE_NOEUD_OCTETS);
                Noeud noeudFilsGauche = GestionFichier.lectureNoeud();
                noeudFilsGauche.ajouterNoeud(stagiaireAAjouter,raf);
            }
        } else {
            if(this.filsDroit == -1) {
                int indexStagiaireAAjouter = (int) (raf.length() / TAILLE_NOEUD_OCTETS);
                raf.seek(raf.getFilePointer()-(TAILLE_FILS_DROIT_OCTETS));
                raf.writeInt(indexStagiaireAAjouter);
                raf.seek(raf.length());
                new Noeud(stagiaireAAjouter).ecrireNoeudBinaire(raf);
            } else  {
                raf.seek(this.filsDroit*TAILLE_NOEUD_OCTETS);
                Noeud noeudFilsDroit = GestionFichier.lectureNoeud();
                noeudFilsDroit.ajouterNoeud(stagiaireAAjouter,raf);
            }
        }
    }





    //getters & setters

    public Stagiaire getStagiaire() {
        return stagiaire;
    }

    public void setStagiaire(Stagiaire stagiaire) {
        this.stagiaire = stagiaire;
    }

    public int getFilsGauche() {
        return filsGauche;
    }

    public void setFilsGauche(int filsGauche) {
        this.filsGauche = filsGauche;
    }

    public int getFilsDroit() {
        return filsDroit;
    }

    public void setFilsDroit(int filsDroit) {
        this.filsDroit = filsDroit;
    }

}

