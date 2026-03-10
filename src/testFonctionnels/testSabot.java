package testFonctionnels;

import cartes.*;
import jeu.Sabot;
import java.util.Iterator;

public class testSabot {
    public static void main(String[] args) {
        // 1. On crée un petit jeu de cartes pour tester [cite: 130, 149]
        JeuDeCartes jeu = new JeuDeCartes();
        jeu.ajouterConfiguration(new Borne(25), 2);  // 2 cartes de 25KM 
        jeu.ajouterConfiguration(new Borne(50), 1);  // 1 carte de 50KM 
        jeu.ajouterConfiguration(new Botte(Type.FEU), 1); // 1 Prioritaire 

        // 2. On met ces cartes dans le sabot
        Sabot sabot = new Sabot(jeu.donnerCartes());

        // 3. Test de la pioche (Question 2.a) 
        System.out.println("--- Test Piocher ---");
        while (!sabot.estVide()) {
            System.out.println("je pioche " + sabot.piocher());
        }

        // 4. Test de l'itérateur (Question 2.b) 
        System.out.println("\n--- Test Itérateur ---");
        // On remplit à nouveau le sabot pour le test
        sabot = new Sabot(jeu.donnerCartes()); 
        
        for (Iterator<Carte> it = sabot.iterator(); it.hasNext(); ) {
            Carte c = it.next();
            System.out.println("Parcours itérateur : " + c);
            it.remove(); // On supprime la carte pendant le parcours 
        }
        
        System.out.println("Sabot vide après itération ? " + sabot.estVide());
    }
}