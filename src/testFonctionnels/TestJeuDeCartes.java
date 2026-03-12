package testFonctionnels;

import cartes.*;

public class TestJeuDeCartes {
	public static void main(String[] args) {
        JeuDeCartes jeu = new JeuDeCartes();
        
        //affichage
        System.out.println(jeu.affichageJeuDeCartes());
        
//        if (!jeu.checkCount()) {
//            System.out.println("erreur de nombre");
//        }
    }

}
