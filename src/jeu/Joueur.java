package jeu;

import java.util.HashSet;
import java.util.Random;
import java.util.Set;

import cartes.Carte;

public class Joueur {
	private String nom;
	private ZoneDeJeu zone;
	private MainJoueur main;
	
	public Joueur(String nom) {
		this.nom = nom;
		this.zone = new ZoneDeJeu();
		this.main = new MainJoueur();
	}

	public String getNom() {
		return nom;
	}
	
	public MainJoueur getMain() {
		return main;
	}
	
	public void donner(Carte carte) {
		main.prendre(carte);
	}
	
	@Override
	public boolean equals(Object objet) {
		if(objet instanceof Joueur joueur) {
			return nom.equals(joueur.getNom());
		}
		return false;
	}
	
//	public void identique(Joueur joueur1, Joueur joueur2) {
//		if(joueur1.equals(joueur2)) {
//			System.out.println("Il existe deja le joueur avec le meme nom, changes le nick");
//		}
//	}
	
	//7. Pioche une carte dans le sabot et l'ajoute à la main du joueur.
	@Override
	public String toString() {
		return nom;
	}
	
	// Permet de retirer la carte une fois qu'elle est jouée 
		public void retirerDeLaMain(Carte carte) {
			main.jouer(carte);
		}
	
//	7. Pioche une carte dans le sabot et l'ajoute à la main du joueur.
	public Carte prendreCarte(Sabot sabot) {
		if(sabot.estVide()) {
			return null;
		}
		
//		On retire la carte du sabot
		Carte carte = sabot.piocher();
		
//		on l'ajoute a la main du joueur
		this.donner(carte);
		
		return carte;		
	}
	
	//TP4
	public Set<Coup> coupsPossibles(Set<Joueur> participants) {
		Set<Coup> possibles = new HashSet<>();
		for (Joueur participant : participants) {
			for (Carte carte : main) { // MainJoueur implémente Iterable
				Coup coup = new Coup(this, carte, participant);
				if (coup.estValide()) {
					possibles.add(coup);
				}
			}
		}
		return possibles;
	}
	
	public Set<Coup> coupsDefausse() {
		Set<Coup> defausse = new HashSet<>();
		for (Carte carte : main) {
			defausse.add(new Coup(this, carte, null));
		}
		return defausse;
	}
	
	public Coup choisirCoup(Set<Joueur> participants) {
		Set<Coup> possibles = coupsPossibles(participants);
		if (!possibles.isEmpty()) {
			return tirerCoupAleatoire(possibles);
		}
		return tirerCoupAleatoire(coupsDefausse());
	}
	
	// tirer un élément aléatoire d'un Set
	private Coup tirerCoupAleatoire(Set<Coup> coups) {
		int index = new Random().nextInt(coups.size());
		int i = 0;
		for (Coup coup : coups) {
			if (i == index) return coup;
			i++;
		}
		return null;
	}
	
	// Affiche l'état pour les traces du jeu
	public String afficherEtatJoueur() {
		boolean limite = zone.donnerLimitationVitesse() == 50;
		return nom + " :\n" +
			   "- Main : " + main + "\n" +
			   "- Limite : " + (limite ? "50 km/h" : "200 km/h");
	}
	
	public int donnerKmParcourus() {
	    return zone.donnerKmParcourus();
	}

	public int donnerLimitationVitesse() {
	    return zone.donnerLimitationVitesse();
	}

	public void deposer(Carte carte) {
	    zone.deposer(carte);
	}
	
	public boolean peutAvancer() {
	    return zone.peutAvancer();
	}


	public boolean estDepotAutorise(Carte carte) {
	    return zone.estDepotAutorise(carte);
	}

}
