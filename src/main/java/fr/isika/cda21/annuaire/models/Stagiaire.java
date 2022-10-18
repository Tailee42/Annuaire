package fr.isika.cda21.annuaire.models;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.List;

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
        if (attribut == null) {
            attribut = "";
        } // TODO A voir pourquoi ça ne fonctionnne pas ?
        String attributLong = attribut;
        if (attribut.length() > TAILLE_MAX_STRING) {
            attributLong = attribut.substring(0, TAILLE_MAX_STRING - 1); //TAILLE_MAX_STRING car c'est l'indice.
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
                    if (myStagiaire.promo.compareToIgnoreCase(this.promo) == 0) {
                        return myStagiaire.getAnneeDeFormation() - this.anneeDeFormation;
                    } else {
                        return myStagiaire.promo.compareToIgnoreCase(this.promo);
                    }
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


    // NON UTILISER A SUPPRIMER ????
    /*public void rechercher(Stagiaire stagiaireRecherche, RandomAccessFile raf) {
        try {
            raf.seek(0);
            Stagiaire stagiaireLu = GestionFichiers.lectureNoeud().getStagiaire();
            if (stagiaireRecherche.compareTo(stagiaireLu) == 0) {
                stagiaireRecherche = stagiaireLu;
            } else if (stagiaireRecherche.compareTo(stagiaireLu) > 0) {
                raf.seek(GestionFichiers.lectureNoeud().getFilsGauche());
                rechercher(stagiaireRecherche, raf);
            } else {
                raf.seek(GestionFichiers.lectureNoeud().getFilsDroit());
                rechercher(stagiaireRecherche, raf);
            }
            int stagiaireListe = GestionFichiers.lectureNoeud().getListeChainee();
            if (stagiaireListe == -1) {
                raf.seek(stagiaireListe);
                rechercher(stagiaireRecherche, raf);
            }
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }*/

    public void rechercheAvancee(List<Stagiaire> rechercheAvancee, Stagiaire stagiaireToFind) {

        boolean nomIdentique = false;
        boolean prenomIdentique = false;
        boolean departementIdentique = false;
        boolean promoIdentique = false;
        boolean anneeIdentique = false;

        String attributVide= Stagiaire.mettreStringAttributALaTaille("");

        if (!stagiaireToFind.getNom().equals(attributVide)) {
            if (stagiaireToFind.getNom().compareToIgnoreCase(this.getNom()) == 0) {
                nomIdentique = true;
            }
        } else {
            nomIdentique = true;
        }

        if (stagiaireToFind.getPrenom().compareToIgnoreCase(attributVide) != 0) {
            if (stagiaireToFind.getPrenom().compareToIgnoreCase(this.getPrenom()) == 0) {
                prenomIdentique = true;
            }
        } else {
            prenomIdentique = true;
        }

        // dep
        if (stagiaireToFind.getDepartement().compareToIgnoreCase(attributVide) != 0) {
            if (stagiaireToFind.getDepartement().compareToIgnoreCase(this.getDepartement()) == 0) {
                departementIdentique = true;
            }
        } else {
            departementIdentique = true;
        }

        // promo
        if (stagiaireToFind.getPromo().compareToIgnoreCase(attributVide) != 0) {
            if (this.getPromo().contains(stagiaireToFind.getPromo().trim())) {
                promoIdentique = true;
            }
        } else {
            promoIdentique = true;
        }

        // annee
        if (stagiaireToFind.getAnneeDeFormation() != 0) {
            if (stagiaireToFind.getAnneeDeFormation() == this.getAnneeDeFormation()) {
                anneeIdentique = true;
            }
        } else {
            anneeIdentique = true;
        }

        if (nomIdentique && prenomIdentique && departementIdentique && promoIdentique && anneeIdentique) {
            rechercheAvancee.add(this);
        } else {
            // TODO: Retour visuel utilisateur.
            // System.out.println("Aucun resultat.");
        }
    }


    //Getters & Setters
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