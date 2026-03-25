package cartes;

public abstract class Carte {

	@Override
	public boolean equals(Object objet) {
		if(objet instanceof Carte carte ) {	//verifier si c'est une carte
			return this.getClass() == carte.getClass();	//verifier si'ils sont de la meme classe ex. 2 Bornes
		}
		return false;
	}
	
}
