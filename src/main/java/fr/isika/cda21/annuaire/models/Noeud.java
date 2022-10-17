package fr.isika.cda21.annuaire.models;

import java.io.IOException;
import java.io.RandomAccessFile;

public class Noeud {

    //attributs
    private Stagiaire stagiaire;
    private int listeChainee;
    private int filsGauche;
    private int filsDroit;
    private static final int FILS_NUL = -1; //car 0 est pour la racine
    private static final int LISTE_VIDE = -1;
    public static final int TAILLE_FILS_GAUCHE_OCTETS = 4;
    public static final int TAILLE_FILS_DROIT_OCTETS = 4;
    public static final int TAILLE_LISTECHAINEE_OCTETS = 4;
    public static final int TAILLE_NOEUD_OCTETS =
            Stagiaire.TAILLE_STAGIAIRE_OCTETS + TAILLE_FILS_GAUCHE_OCTETS + TAILLE_FILS_DROIT_OCTETS + TAILLE_LISTECHAINEE_OCTETS;

    //Constructeur
    public Noeud(Stagiaire stagiaire,int listeChainee, int filsGauche, int filsDroit) {
        super();
        this.stagiaire = stagiaire;
        this.listeChainee = listeChainee;
        this.filsGauche = filsGauche;
        this.filsDroit = filsDroit;
    }

    public Noeud(Stagiaire stagiaire) {
        super();
        this.stagiaire = stagiaire;
        this.listeChainee = LISTE_VIDE;
        this.filsGauche = FILS_NUL;
        this.filsDroit = FILS_NUL;
    }

    //méthodes spécifiques
    @Override
    public String toString() {
        return "{" +
                "stagiaire=" + stagiaire +
                ", listeChainée=" + listeChainee +
                ", filsGauche=" + filsGauche +
                ", filsDroit=" + filsDroit +
                '}';
    }

    public void ecrireNoeudBinaire(RandomAccessFile raf) throws IOException {
        this.stagiaire.ecritureStagiaireBinaire(raf);
        raf.writeInt(listeChainee);
        raf.writeInt(filsGauche);
        raf.writeInt(filsDroit);
    }

    public void ajouterNoeud(Stagiaire stagiaireAAjouter, RandomAccessFile raf) throws IOException {
        if (this.stagiaire.getNom().compareToIgnoreCase(stagiaireAAjouter.getNom()) == 0){
            if (this.stagiaire.compareTo(stagiaireAAjouter) == 0) {

                // TODO Faire une sortie utilisateur
                System.out.println("Il existe déjà");

            } else {
                Noeud noeudDeLaListeChainee = this;

                while (noeudDeLaListeChainee.listeChainee != LISTE_VIDE) { //Déplacement dans la liste chainée poour arriver à la fin
                    raf.seek(noeudDeLaListeChainee.listeChainee * TAILLE_NOEUD_OCTETS); //positionne le curseur sur le prochain stagiaire de la liste chaine.
                    noeudDeLaListeChainee = GestionFichiers.lectureNoeud();
                }

                //Ajout du stagiaire
                int indexStagiaireAAjouter = (int) (raf.length() / TAILLE_NOEUD_OCTETS);
                raf.seek(raf.getFilePointer() - (TAILLE_LISTECHAINEE_OCTETS + TAILLE_FILS_GAUCHE_OCTETS + TAILLE_FILS_DROIT_OCTETS));
                raf.writeInt(indexStagiaireAAjouter);
                raf.seek(raf.length());
                new Noeud(stagiaireAAjouter).ecrireNoeudBinaire(raf);

        }


        } else if (this.stagiaire.getNom().compareToIgnoreCase(stagiaireAAjouter.getNom()) < 0) {
            if (this.filsGauche == FILS_NUL) {
                int indexStagiaireAAjouter = (int) (raf.length() / TAILLE_NOEUD_OCTETS);
                raf.seek(raf.getFilePointer() - (TAILLE_FILS_GAUCHE_OCTETS + TAILLE_FILS_DROIT_OCTETS));
                raf.writeInt(indexStagiaireAAjouter);
                raf.seek(raf.length());
                new Noeud(stagiaireAAjouter).ecrireNoeudBinaire(raf);
            } else {
                raf.seek(this.filsGauche * TAILLE_NOEUD_OCTETS);
                Noeud noeudFilsGauche = GestionFichiers.lectureNoeud();
                noeudFilsGauche.ajouterNoeud(stagiaireAAjouter, raf);
            }
        } else {
            if (this.filsDroit == FILS_NUL) {
                int indexStagiaireAAjouter = (int) (raf.length() / TAILLE_NOEUD_OCTETS);
                raf.seek(raf.getFilePointer() - (TAILLE_FILS_DROIT_OCTETS));
                raf.writeInt(indexStagiaireAAjouter);
                raf.seek(raf.length());
                new Noeud(stagiaireAAjouter).ecrireNoeudBinaire(raf);
            } else {
                raf.seek(this.filsDroit * TAILLE_NOEUD_OCTETS);
                Noeud noeudFilsDroit = GestionFichiers.lectureNoeud();
                noeudFilsDroit.ajouterNoeud(stagiaireAAjouter, raf);
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

