package testFonctionnels;

import cartes.*;
import jeu.Sabot;

import java.util.ConcurrentModificationException;
import java.util.Iterator;

public class testSabot {
    
    // 4.2.a : Utilisation de piocher()
    public void questionA() {
        System.out.println("--- Question A : Vider avec piocher() ---");
        JeuDeCartes jeu = new JeuDeCartes();
        Sabot sabot = new Sabot(jeu.donnerCartes());

        while (!sabot.estVide()) {
            Carte carte = sabot.piocher();
            System.out.println("je pioche " + carte);
        }
    }

    // 4.2.b : Utilisation de l'itérateur et remove()
    public void questionB() {
        System.out.println("\n--- Question B : Vider avec l'itérateur ---");
        JeuDeCartes jeu = new JeuDeCartes();
        Sabot sabot = new Sabot(jeu.donnerCartes());

        for (Iterator<Carte> iterator = sabot.iterator(); iterator.hasNext();) {
            System.out.println("je pioche " + iterator.next());
            iterator.remove();
        }
    }

    // 4.2.c : Tests des ConcurrentModificationException
    public void questionC() {
        System.out.println("\n--- Question C : Exceptions de modification concurrente ---");

        // Cas 1 : Piocher pendant l'itération
        System.out.println("Test 1 : Appel de piocher() dans la boucle");
        JeuDeCartes jeu1 = new JeuDeCartes();
        Sabot sabot1 = new Sabot(jeu1.donnerCartes());
        
        try {
            for (Iterator<Carte> iterator = sabot1.iterator(); iterator.hasNext();) {
                Carte carte = iterator.next();
                System.out.println("Vue par itérateur : " + carte);
                
                // Modification illégale de la structure externe
                sabot1.piocher(); 
            }
        } catch (ConcurrentModificationException e) {
            System.out.println("Succès : ConcurrentModificationException correctement levée !");
        }

        // Cas 2 : Ajouter une carte pendant l'itération
        System.out.println("\nTest 2 : Appel de ajouterCarte() dans la boucle");
        JeuDeCartes jeu2 = new JeuDeCartes();
        Sabot sabot2 = new Sabot(jeu2.donnerCartes());
        
        // On pioche d'abord une carte pour éviter l'exception "Trop de cartes" (dépassement capacité du tableau)
        sabot2.piocher();
        
        try {
            for (Iterator<Carte> iterator = sabot2.iterator(); iterator.hasNext();) {
                Carte carte = iterator.next();
                System.out.println("Vue par itérateur : " + carte);
                
                // Modification illégale (ajout de l'As du volant)
                sabot2.ajouterCarte(new Botte(Type.ACCIDENT)); 
            }
        } catch (ConcurrentModificationException e) {
            System.out.println("Succès : ConcurrentModificationException correctement levée !");
        }
    }

    public static void main(String[] args) {
        testSabot tests = new testSabot();
        tests.questionA();
        tests.questionB();
        tests.questionC();
    }
}