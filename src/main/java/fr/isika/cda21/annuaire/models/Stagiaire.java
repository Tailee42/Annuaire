package fr.isika.cda21.annuaire.models;

import java.io.IOException;
import java.io.RandomAccessFile;

public class Stagiaire {
    private String nom;
    private String prenom;
    private String departement;
    private String promo;
    private int anneeDeFormation;
    public static final int TAILLE_MAX_STRING = 21;
    public static final int TAILLE_STAGIAIRE_OCTETS = TAILLE_MAX_STRING * 2 * 4 + 4;

    //Constructeurs
    public Stagiaire(String nom, String prenom, String departement, String promo, int anneeDeFormation) {
        this.nom = mettreStringAttributALaTaille(nom);
        this.prenom = mettreStringAttributALaTaille(prenom);
        this.departement = mettreStringAttributALaTaille(departement);
        this.promo = mettreStringAttributALaTaille(promo);
        this.anneeDeFormation = anneeDeFormation;
    }

    //Méthodes Spécifiques
    private static String mettreStringAttributALaTaille(String attribut) {
        String attributLong = attribut;
        if (attribut.length() > TAILLE_MAX_STRING) {
            attributLong = attribut.substring(0, TAILLE_MAX_STRING - 1);
        } else {
            for (int i = attribut.length(); i < TAILLE_MAX_STRING; i++) {
                attributLong += " ";
            }
        }
        return attributLong;
    }

    @Override
    public String toString() {
        return this.nom + " " + this.prenom + " " + this.departement + " " + this.promo + " " + this.anneeDeFormation + " ";
    }

    public int compareTo(Stagiaire myStagiaire) {
        if (myStagiaire.nom.compareToIgnoreCase(this.nom) == 0) {
            if (myStagiaire.prenom.compareToIgnoreCase(this.prenom) == 0) {
                if (myStagiaire.departement.compareToIgnoreCase(this.departement) == 0) {
                    return myStagiaire.promo.compareToIgnoreCase(this.promo) == 0 ? myStagiaire.getAnneeDeFormation() - this.anneeDeFormation : myStagiaire.promo.compareToIgnoreCase(this.promo);
                } else {
                    return myStagiaire.departement.compareToIgnoreCase(this.departement);
                }
            } else {
                return myStagiaire.prenom.compareToIgnoreCase(this.prenom);
            }
        } else {
            return myStagiaire.nom.compareToIgnoreCase(this.nom);
        }
    }

    public void ecritureStagiaireBinaire(RandomAccessFile raf) throws IOException {
        raf.writeChars(nom);
        raf.writeChars(prenom);
        raf.writeChars(departement);
        raf.writeChars(promo);
        raf.writeInt(anneeDeFormation);
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getDepartement() {
        return departement;
    }

    public void setDepartement(String departement) {
        this.departement = departement;
    }

    public String getPromo() {
        return promo;
    }

    public void setPromo(String promo) {
        this.promo = promo;
    }

    public int getAnneeDeFormation() {
        return anneeDeFormation;
    }

    public void setAnneeDeFormation(int anneeDeFormation) {
        this.anneeDeFormation = anneeDeFormation;
    }
}