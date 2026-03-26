package jeu;

public class Joueur {
	private String nom;
	private ZoneDeJeu zone;
	
	public Joueur(String nom, ZoneDeJeu zone) {
		this.nom = nom;
		zone = new ZoneDeJeu();
	}

	public String getNom() {
		return nom;
	}
	
	@Override
	public boolean equals(Object objet) {
		if(objet instanceof Joueur joueur) {
			return nom.equals(joueur.getNom());
		}
		return false;
	}
	
	public void identique(Joueur joueur1, Joueur joueur2) {
		if(joueur1.equals(joueur2)) {
			System.out.println("Il existe deja le joueur avec le meme nom, changes le nick");
		}
	}
	
	@Override
	public String toString() {
		return nom;
	}


}
