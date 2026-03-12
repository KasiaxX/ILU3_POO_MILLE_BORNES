package testFonctionnels;

import cartes.*;
import jeu.Sabot;
import java.util.Iterator;

public class testSabot {
        //crée un jeu 
        JeuDeCartes jeu = new JeuDeCartes();
        //mttre les cartes dans le sabot
        Sabot sabot = new Sabot(jeu.donnerCartes());

//        //pioche 
//        System.out.println("--- Test Piocher ---");
//        while (!sabot.estVide()) {
//            System.out.println("je pioche " + sabot.piocher());
//        }
//
//        //itérateur  
//        System.out.println("\n--- Test Itérateur ---");
//        // On remplit à nouveau le sabot
//        sabot = new Sabot(jeu.donnerCartes()); 
//        
//        for (Iterator<Carte> iterator = sabot.iterator(); iterator.hasNext(); ) {
//            Carte carte = iterator.next();
//            System.out.println("Parcours itérateur : " + carte);
//            iterator.remove(); // On supprime la carte pendant le parcours 
//        }
//        
//        System.out.println("Sabot vide après itération ? " + sabot.estVide());
//    }
        
     // 4.2.a
    	public void questionA() {

    		while (!sabot.estVide()) {
    			Carte carte = sabot.piocher();
    			System.out.println("Je pioche " + carte);
    		}
//    		Console :
//    		Je pioche Accident
//    		Je pioche Accident
//    		Je pioche Accident
//    		Je pioche R�paration
//    		Je pioche R�paration
//    		Je pioche R�paration
//    		Je pioche As du volant
    	}

    	// 4.2.b
    	public void questionB() {
    		for (Iterator<Carte> iterator = sabot.iterator(); iterator.hasNext();) {
    			System.out.println("Je pioche " + iterator.next());
    			iterator.remove();
    		}
    	}

    	// 4.2.c
    	public void questionC() {
    		Carte cartePiochee = sabot.piocher();
    		System.out.println("Je pioche " + cartePiochee);
    		for (Iterator<Carte> iterator = sabot.iterator(); iterator.hasNext();) {
    			Carte carte = iterator.next();
    			System.out.println("Je pioche " + carte);
    			iterator.remove();
    			Carte cartePioche = sabot.piocher();
    			sabot.ajouterCarte(new Botte(cartes.Type.ACCIDENT));
    		}
    		Iterator<Carte> iterator = sabot.iterator();
    		System.out.println("\nLa pioche contient encore des cartes ? " + iterator.hasNext());
    	}

    	public static void main(String[] args) {
    		testSabot testPioche = new testSabot();
//    		testPioche.questionA();
//    		testPioche.questionB();
//    		testPioche.questionC();
    	}
}