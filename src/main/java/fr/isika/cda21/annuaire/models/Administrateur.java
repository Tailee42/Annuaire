package fr.isika.cda21.annuaire.models;

public class Administrateur {

    // Attributs de classe. Private
    final String IDENTIFIANT = "Administrateur";
    final String MOTDEPASSE = "1234";

    // Constructeur
    public Administrateur() {
        super();
    }

    // Getters & setters
    public String getIdentifiant() {
        return IDENTIFIANT;
    }

    public String getMotDePasse() {
        return MOTDEPASSE;
    }

    // methodes spécifiques
    @Override
    public String toString() {
        return "Administrateur\nIdentifiant : " + IDENTIFIANT + "\n Mot de Passe : " + MOTDEPASSE + ".\n";
    }

}
