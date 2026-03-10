package testFonctionnels;

import cartes.*;

public class TestJeuDeCartes {
	public static void main(String[] args) {
        JeuDeCartes jeu = new JeuDeCartes();
        
        // Exemples d'initialisation selon le tableau source 145
        jeu.ajouterConfiguration(new Borne(25), 10);
        jeu.ajouterConfiguration(new Borne(50), 10);
        jeu.ajouterConfiguration(new Attaque(Type.FEU), 5); // Feu Rouge
        jeu.ajouterConfiguration(new Parade(Type.FEU), 14); // Feu Vert
        jeu.ajouterConfiguration(new Botte(Type.FEU), 1);   // Prioritaire [cite: 123]
        
        // Affichage du résultat [cite: 150]
        System.out.println(jeu.affichageJeuDeCartes());
    }

}
