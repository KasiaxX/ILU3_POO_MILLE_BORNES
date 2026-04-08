package jeu;

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
