package testFonctionnels;

import cartes.*;

public class TestJeuDeCartes {
    public static void main(String[] args) {
        JeuDeCartes jeu = new JeuDeCartes();
        
        // Affichage de la configuration du jeu
        System.out.println("--- Configuration du jeu ---");
        System.out.println(jeu.affichageJeuDeCartes());
        
        // Vérification de la methode donnerCartes()
        Carte[] paquet = jeu.donnerCartes();
        System.out.println("Nombre total de cartes générées : " + paquet.length);
    }
}
