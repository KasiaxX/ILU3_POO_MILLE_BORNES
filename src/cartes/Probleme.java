package cartes;

public abstract class Probleme extends Carte {
	protected Type type;

	protected Probleme(Type type) {
		this.type = type;
	}
	
	public Type getType(Type type) {
		return type;
	}

}
