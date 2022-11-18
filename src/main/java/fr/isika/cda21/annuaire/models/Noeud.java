package fr.isika.cda21.annuaire.models;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.List;

public class Noeud {

    //attributs
    private Stagiaire stagiaire;
    private int listeChainee;
    private int parent;
    private int filsGauche;
    private int filsDroit;
    private int hauteur;
    private static final int FILS_NUL = -1; //car 0 est pour la racine
    private static final int LISTE_VIDE = -1;
    public static final int TAILLE_INT_OCTETS = 4;


    public static final int TAILLE_NOEUD_OCTETS = Stagiaire.TAILLE_STAGIAIRE_OCTETS + TAILLE_INT_OCTETS*5;

    //Constructeur
    public Noeud(Stagiaire stagiaire,int listeChainee, int parent, int filsGauche, int filsDroit, int hauteur) {
        super();
        this.stagiaire = stagiaire;
        this.listeChainee = listeChainee;
        this.parent = parent;
        this.filsGauche = filsGauche;
        this.filsDroit = filsDroit;
        this.hauteur = hauteur;
    }

    public Noeud(Stagiaire stagiaire, int parent, int hauteur) {
        super();
        this.stagiaire = stagiaire;
        this.listeChainee = LISTE_VIDE;
        this.parent = parent;
        this.filsGauche = FILS_NUL;
        this.filsDroit = FILS_NUL;
        this.hauteur = hauteur;
    }

    //méthodes spécifiques
    @Override
    public String toString() {
        return "Noeud{" +
                "stagiaire=" + stagiaire +
                ", listeChainee=" + listeChainee +
                ", parent=" + parent +
                ", filsGauche=" + filsGauche +
                ", filsDroit=" + filsDroit +
                ", hauteur=" + hauteur +
                '}';
    }

    public void ecrireNoeudBinaire(RandomAccessFile raf) throws IOException {
        this.stagiaire.ecritureStagiaireBinaire(raf);
        raf.writeInt(listeChainee);
        raf.writeInt(parent);
        raf.writeInt(filsGauche);
        raf.writeInt(filsDroit);
        raf.writeInt(hauteur);
    }

    public void ajouterNoeud(Stagiaire stagiaireAAjouter, GestionFichiers rafFichierDom) throws IOException {

        RandomAccessFile raf = rafFichierDom.getRaf();

        if (this.stagiaire.getNom().compareToIgnoreCase(stagiaireAAjouter.getNom()) == 0){
            // Ajout du stagiaire dans l'arbre binaire
            if (this.stagiaire.compareTo(stagiaireAAjouter) == 0) {

                // TODO Faire une sortie utilisateur
                System.out.println("Il existe déjà");

            } else {
                // Ajout du stagaire dans la liste chainée
                Noeud noeudDeLaListeChainee = this;

                while (noeudDeLaListeChainee.listeChainee != LISTE_VIDE) { //Déplacement dans la liste chainée pour arriver à la fin
                    raf.seek((long) noeudDeLaListeChainee.listeChainee * TAILLE_NOEUD_OCTETS); //positionne le curseur sur le prochain stagiaire de la liste chaine.
                    noeudDeLaListeChainee = rafFichierDom.lectureNoeud();
                }

                //Ajout du stagiaire
                ajoutDansFichierBinaire(raf, 5, stagiaireAAjouter); //recule de hauteur, fils droit, fils gauche, parent, liste Chainée

            }
            //Recherche de l'emplacement pour l'ajout
        } else if (this.stagiaire.getNom().compareToIgnoreCase(stagiaireAAjouter.getNom()) > 0) {
            if (this.filsGauche == FILS_NUL) {
                ajoutDansFichierBinaire(raf, 3, stagiaireAAjouter); //recule de hauteur, fils droit, fils gauche
            } else {
                raf.seek((long) this.filsGauche * TAILLE_NOEUD_OCTETS);
                Noeud noeudFilsGauche = rafFichierDom.lectureNoeud();
                noeudFilsGauche.ajouterNoeud(stagiaireAAjouter, rafFichierDom);
            }
        } else {
            if (this.filsDroit == FILS_NUL) {
                ajoutDansFichierBinaire(raf, 2, stagiaireAAjouter); //recule de hauteur, fils droit, fils gauche, parent, liste Chainée
            } else {
                raf.seek((long) this.filsDroit * TAILLE_NOEUD_OCTETS);
                Noeud noeudFilsDroit = rafFichierDom.lectureNoeud();
                noeudFilsDroit.ajouterNoeud(stagiaireAAjouter, rafFichierDom);
            }
        }
    }

    private void ajoutDansFichierBinaire(RandomAccessFile raf, int nombreDeParametresAReculer, Stagiaire stagiaireAAjouter) throws IOException {
        int indexNoeudParent = (int) (raf.getFilePointer() / TAILLE_NOEUD_OCTETS) - 1;
        int indexStagiaireAAjouter = (int) (raf.length() / TAILLE_NOEUD_OCTETS);
        raf.seek(raf.getFilePointer() - ((long) TAILLE_INT_OCTETS * nombreDeParametresAReculer));
        raf.writeInt(indexStagiaireAAjouter);
        raf.seek(raf.length());
        new Noeud(stagiaireAAjouter, indexNoeudParent, hauteur + 1).ecrireNoeudBinaire(raf);
    }


    public void ordreAlphabetique(List<Stagiaire> listeDeStagiaire, GestionFichiers rafFichierDom) throws IOException {

        RandomAccessFile raf = rafFichierDom.getRaf();

        // parcours GND
        Noeud noeudDeLaListeChainee = this;

        if (this.filsGauche != -1) {
            raf.seek((long) this.filsGauche * TAILLE_NOEUD_OCTETS); // on positionne le curseur au niveau du fils gauche
            Noeud noeudFilsGauche = rafFichierDom.lectureNoeud(); // on lit le noeud fils gauche
            noeudFilsGauche.ordreAlphabetique(listeDeStagiaire, rafFichierDom);
        } //G

        listeDeStagiaire.add(stagiaire); // on ajoute le stagiaire à la liste de stagiaire
        //N

        while (noeudDeLaListeChainee.listeChainee != LISTE_VIDE) {
            raf.seek((long) noeudDeLaListeChainee.listeChainee * TAILLE_NOEUD_OCTETS);
            noeudDeLaListeChainee = rafFichierDom.lectureNoeud();

            listeDeStagiaire.add(noeudDeLaListeChainee.stagiaire);
        } //N

        if (this.filsDroit != -1) {
            raf.seek((long) this.filsDroit * TAILLE_NOEUD_OCTETS); // on place le curseur au niveau du fils droit
            Noeud noeudFilsDroit = rafFichierDom.lectureNoeud(); // on lit le noeud fils droit
            noeudFilsDroit.ordreAlphabetique(listeDeStagiaire, rafFichierDom);
        } //D
    }

    //Methode recherche
    public void rechercheStagiaire(List<Stagiaire> listeResultats, Stagiaire stagiaireRecherche, GestionFichiers rafFichierDom)
            throws IOException {

        RandomAccessFile raf = rafFichierDom.getRaf();

        // Compare les noms du stagiaire recherché et stagiaire courant
        if (stagiaireRecherche.getNom().compareToIgnoreCase(this.stagiaire.getNom()) == 0) {

            // noms identiques, Ajout des resultats correspondant à la liste de résultats
            listeResultats.add(this.stagiaire);

            // Si une liste chaînée existe dans le noeud actuel, on se déplace pour lire la liste
            if (this.listeChainee != LISTE_VIDE) {
                raf.seek((long) this.listeChainee * TAILLE_NOEUD_OCTETS);
                // Déclare noeudSuivant pour pouvoir transmettre la méthode
                Noeud noeudSuivant = rafFichierDom.lectureNoeud();
                noeudSuivant.rechercheStagiaire(listeResultats, stagiaireRecherche, rafFichierDom);
            }

            // recursivité filsGauche
        } else if (stagiaireRecherche.getNom().compareToIgnoreCase(this.stagiaire.getNom()) < 0) {
            if (this.filsGauche == FILS_NUL) {
                // Retour visuel Aucun stagiaire correspondant
                System.out.println("Aucun stagiaire correspondant à gauche.");
            } else {
                raf.seek((long) this.filsGauche * TAILLE_NOEUD_OCTETS);
                Noeud noeudFilsGauche = rafFichierDom.lectureNoeud();
                noeudFilsGauche.rechercheStagiaire(listeResultats, stagiaireRecherche, rafFichierDom);
            }
            // recursivité filsDroit
        } else {
            if (this.filsDroit == FILS_NUL) {
                // retour visuel pas de résultats
                System.out.println("Aucun stagiaire correspondant.");
            } else {

                raf.seek((long) this.filsDroit * TAILLE_NOEUD_OCTETS);
                Noeud noeudFilsDroit = rafFichierDom.lectureNoeud();
                noeudFilsDroit.rechercheStagiaire(listeResultats, stagiaireRecherche, rafFichierDom);
            }
        }
    }


    public int supprimerNoeud(Stagiaire stagiaireASupprimer, GestionFichiers rafFichierDom) throws IOException {

        RandomAccessFile raf = rafFichierDom.getRaf();

        int indexDuStagiaire = (int) ((raf.getFilePointer() - TAILLE_NOEUD_OCTETS) / TAILLE_NOEUD_OCTETS);

        //Recherche du stagiaire
        if (this.stagiaire.getNom().compareToIgnoreCase(stagiaireASupprimer.getNom()) > 0) {

            raf.seek((long) this.filsGauche * TAILLE_NOEUD_OCTETS);// pour positionner le curseur au début du fils gauche on
            // multiplie l'index du fils gauche par la taille d'un noeud
            Noeud noeudFilsGauche = rafFichierDom.lectureNoeud(); // on lit les valeurs du fils gauche où le curseur
            // s'est arrêté pour stocker les informations

            this.filsGauche = noeudFilsGauche.supprimerNoeud(stagiaireASupprimer, rafFichierDom);
            raf.seek((long) indexDuStagiaire * TAILLE_NOEUD_OCTETS + Stagiaire.TAILLE_STAGIAIRE_OCTETS + TAILLE_INT_OCTETS * 2); //car on est au début de notre Noeud. 2 pour arriver au début du fils gauche
            raf.writeInt(this.filsGauche);

        } else if (this.stagiaire.getNom().compareToIgnoreCase(stagiaireASupprimer.getNom()) < 0) { // on ne veut pas le contraire de
            // strictement plus petit (qui est strictement plus grand ou égal)
            // mais on veut juste le strictement plus grand.
            raf.seek((long) this.filsDroit * TAILLE_NOEUD_OCTETS);
            Noeud noeudFilsDroit = rafFichierDom.lectureNoeud();

            this.filsDroit = noeudFilsDroit.supprimerNoeud(stagiaireASupprimer, rafFichierDom);
            raf.seek((long) indexDuStagiaire * TAILLE_NOEUD_OCTETS + Stagiaire.TAILLE_STAGIAIRE_OCTETS + TAILLE_INT_OCTETS * 3); //car on est au début de notre Noeud. 3 pour arriver au début du fils droit
            raf.writeInt(this.filsDroit);

        } else { // cas où on a trouvé le nom du stagiaire à supprimer
            // Et il correspond au noeud de l'arbrebinaire et il n'y  pas de liste chainée.
            if (this.stagiaire.compareTo(stagiaireASupprimer) == 0 && this.listeChainee == LISTE_VIDE) { // cas où le stagiaire à supprimé est dans l'arbre binaire et n'a pas de liste chainée
                //Cas de la feuille ou cas avec un seul fils.
                if (this.filsGauche == FILS_NUL || this.filsDroit == FILS_NUL) {
                    if (this.filsGauche != FILS_NUL) {
                        return filsGauche;
                    } else {
                        return filsDroit;
                    }
                    //cas avec 2 fils.
                } else {
                    // il faut récupérer l'indice du stagiaire que l'on va supprimer
                    // (this.stagiaire) pour pouvoir l'utiliser après getSuccesseur qui a déplacé le
                    // curseur.

                    Noeud noeudDeRemplacement = this.getSuccesseur(rafFichierDom);
                    raf.seek((long) indexDuStagiaire * TAILLE_NOEUD_OCTETS); // on a positionné notre curseur au bon
                    // endroit: le début du stagiaire à supprimer.
                    noeudDeRemplacement.stagiaire.ecritureStagiaireBinaire(raf);// on a écrit les informations de notre
                    // stagiaire successeur et de sa liste chaînée dans notre noeud
                    raf.writeInt(noeudDeRemplacement.listeChainee);

                    raf.seek((long) this.filsDroit * TAILLE_NOEUD_OCTETS);
                    Noeud noeudFilsDroit = rafFichierDom.lectureNoeud();
                    this.filsDroit = noeudFilsDroit.supprimerNoeud(noeudDeRemplacement.stagiaire, rafFichierDom);
                    raf.seek((long) indexDuStagiaire * TAILLE_NOEUD_OCTETS + Stagiaire.TAILLE_STAGIAIRE_OCTETS + TAILLE_INT_OCTETS * 3); //car on est au début de notre Noeud. 3 pour arriver au début du fils droit
                    raf.writeInt(this.filsDroit);

                    return indexDuStagiaire;
                }

            } else if (this.stagiaire.compareTo(stagiaireASupprimer) == 0) { // Le stagiaire à supprimer est dans l'arbre et il a une liste chainee. Il faut que l'arbre pointe vers le deuxième maillon de la chaine.
                // on écrit le stagiaire suivant et son index de liste sur le stagiaire à supprimer
                Noeud noeudActuel = this;
                int indexNoeudActuel = indexDuStagiaire;

                raf.seek((long) noeudActuel.listeChainee * TAILLE_NOEUD_OCTETS);
                Noeud noeudChaineSuivant = rafFichierDom.lectureNoeud();

                raf.seek((long) indexNoeudActuel * TAILLE_NOEUD_OCTETS);
                noeudChaineSuivant.stagiaire.ecritureStagiaireBinaire(raf);

                raf.seek((long) indexDuStagiaire * TAILLE_NOEUD_OCTETS + Stagiaire.TAILLE_STAGIAIRE_OCTETS);
                raf.writeInt(noeudChaineSuivant.listeChainee);

            } else  { //cas où le stagiaire à supprimer est dans la liste chainée mais pas au début.
                Noeud noeudCourant = this;
                int indexNoeudCourant = indexDuStagiaire;
                int indexPrecedent = LISTE_VIDE;
                while (noeudCourant.stagiaire.compareTo(stagiaireASupprimer) != 0) {
                    raf.seek((long) noeudCourant.listeChainee * TAILLE_NOEUD_OCTETS);
                    indexPrecedent = indexNoeudCourant;
                    indexNoeudCourant = noeudCourant.listeChainee;
                    noeudCourant = rafFichierDom.lectureNoeud();
                }

                raf.seek((long) indexPrecedent * TAILLE_NOEUD_OCTETS + Stagiaire.TAILLE_STAGIAIRE_OCTETS);
                raf.writeInt(noeudCourant.listeChainee);
            }
        }
        return indexDuStagiaire;
    }


    //pour récupérer les informations du noeud qui succecede celui à supprimer (ici le plus petit des plus grands)
    private Noeud getSuccesseur(GestionFichiers rafFichierDom) throws IOException {

        RandomAccessFile raf = rafFichierDom.getRaf();

        raf.seek((long) this.filsDroit * TAILLE_NOEUD_OCTETS);
        Noeud noeudTemporaire = rafFichierDom.lectureNoeud();
        while (noeudTemporaire.filsGauche != FILS_NUL) {
            raf.seek((long) noeudTemporaire.filsGauche * TAILLE_NOEUD_OCTETS);
            noeudTemporaire = rafFichierDom.lectureNoeud();
        }
        return noeudTemporaire;
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

    public int getListeChainee() {
        return listeChainee;
    }

    public void setListeChainee(int listeChainee) {
        this.listeChainee = listeChainee;
    }
}


