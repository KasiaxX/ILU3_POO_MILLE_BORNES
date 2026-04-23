package jeu;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

public class Jeu {
	private Sabot sabot;
	//TP4 partie3
	private List<Joueur> joueurs = new ArrayList<>();
	private Iterator<Joueur> iterateurJoueurs;

	public Jeu(Sabot sabot) {
		this.sabot = sabot;
		// La liste des joueurs ordonnés selon leur ordre d'inscription 
	}

	// Permet d'ajouter des joueurs à la partie 
	public void inscrire(Joueur... nouveauxJoueurs) {
		joueurs.addAll(Arrays.asList(nouveauxJoueurs));
	}
	
	public void distribuerCartes() {
		// 6 cartes par joueur
		for (int i = 0; i < 6; i++) {
			for (Joueur j : joueurs) {
				j.donner(sabot.piocher());
			}
		}
	}
	
	public Joueur donnerJoueurSuivant() {
		// Si l'itérateur est vide ou fini, on en recrée un (boucle infinie)
		if (iterateurJoueurs == null || !iterateurJoueurs.hasNext()) {
			iterateurJoueurs = joueurs.iterator();
		}
		return iterateurJoueurs.next();
	}
	
	public String jouerTour(Joueur joueur) {
		// 1. Le joueur pioche
		joueur.prendreCarte(sabot);
		
		// 2. Le joueur choisit ce qu'il va faire
		Coup coup = joueur.choisirCoup(new HashSet<>(joueurs));
		
		// 3. Il retire la carte de sa main
		joueur.retirerDeLaMain(coup.getCarteJouee());
		
		// 4. On applique l'action
		if (coup.getCible() == null) {
			// S'il jette la carte, elle retourne dans le sabot
			sabot.ajouterCarte(coup.getCarteJouee());
		} else {
			// S'il vise un joueur, la carte est déposée
			coup.getCible().deposer(coup.getCarteJouee());
		}
		
		return coup.toString();
	}

	// --- TP5 Partie1 ---
	// TP5 Q1 : Renvoie la liste des joueurs classes en décroissant de Km.
	public List<Joueur> classement() {
		// Utilisation d'un TreeSet avec un Comparator (sans lambda)
		Set<Joueur> classementSet = new TreeSet<>(new Comparator<Joueur>() {
			@Override
			public int compare(Joueur j1, Joueur j2) {
				int km1 = j1.donnerKmParcourus();
				int km2 = j2.donnerKmParcourus();
				
				if (km1 != km2) {
					// Ordre décroissant : on compare km2 par rapport à km1
					return Integer.compare(km2, km1);
				}
				// Pour éviter que le TreeSet ne supprime les joueurs avec le même score,
				// ordre alphabétique 
				return j1.getNom().compareTo(j2.getNom());
			}
		});

		// On ajoute tous les joueurs inscrits dans le Set (trier automatiquement)
		classementSet.addAll(joueurs);

		// On retourne le Set sous forme de List
		return new ArrayList<>(classementSet);
	}

	
	//TP5 Q2 : Affichage du classement final à la fin de la méthode lancer()
	public String lancer() {
		distribuerCartes();
		
		boolean fini = false;
		Joueur vainqueur = null;
		
		// La boucle du jeu : tant qu'il y a des cartes et pas de vainqueur
		while (!sabot.estVide() && !fini) {
			Joueur joueurCourant = donnerJoueurSuivant();
			
			// On affiche l'état et l'action du joueur
			System.out.println(joueurCourant.afficherEtatJoueur());
			System.out.println(jouerTour(joueurCourant) + "\n");
			
			// Condition de victoire !
			if (joueurCourant.donnerKmParcourus() == 1000) {
				fini = true;
				vainqueur = joueurCourant;
			}
		}
		
		if (vainqueur != null) {
			return vainqueur.getNom() + " a gagné la partie avec 1000km !";
		} else {
			return "Partie terminée : Le sabot est vide.";
		}
	}
}