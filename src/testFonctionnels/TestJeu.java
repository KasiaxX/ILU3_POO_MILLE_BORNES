package testFonctionnels;

import cartes.Borne;
import cartes.JeuDeCartes;
import jeu.Jeu;
import jeu.Joueur;
import jeu.Sabot;

public class TestJeu {
	public static void main(String[] args) {
		// 1. Préparation du jeu
		JeuDeCartes jeuDeCartes = new JeuDeCartes();
		Sabot sabot = new Sabot(jeuDeCartes.donnerCartes());
		Jeu jeu = new Jeu(sabot);

		// 2. Création des joueurs
		Joueur luffy = new Joueur("Mimi");
		Joueur zoro = new Joueur("Olek");
		Joueur sanji = new Joueur("Alex");

		jeu.inscrire(luffy, zoro, sanji);

		// 3.  tester le classement
		luffy.deposer(new Borne(100));
		luffy.deposer(new Borne(50)); // Mimi a 150 km

		zoro.deposer(new Borne(200)); // Olek a 200 km

		sanji.deposer(new Borne(75)); // Alex a 75 km

		// 4. On lance la fin du jeu pour voir le classement
		String vainqueur = jeu.lancer();
		System.out.println("\n" + vainqueur);
	}
}