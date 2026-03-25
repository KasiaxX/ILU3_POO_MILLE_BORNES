package cartes;

public abstract class Probleme extends Carte {
	protected Type type;

	protected Probleme(Type type) {
		this.type = type;
	}
	
	public Type getType() {
		return type;
	}
	
	@Override
	public boolean equals(Object objet) {
		if(objet instanceof Probleme probleme ) {
			return super.equals(probleme) && this.type.equals(probleme.getType());
		}
		return false;
	}


}
