package cartes;

public abstract class Carte {

	@Override
	public boolean equals(Object objet) {
		return getClass() == objet.getClass();	//verifier si'ils sont de la meme classe ex. 2 Bornes
	}
	
}
