package cartes;

public abstract class Carte {

	@Override
	public boolean equals(Object objet) {
		if(objet instanceof Carte carte ) {
			return carte.equals(carte);
		}
		return false;
	}
	
}
