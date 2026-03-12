package cartes;

public class Borne extends Carte {
	private int km;

	public Borne(int km) {
		this.km = km;
	}
	
	public int getKm() { 
		return km; 
		}
	@Override
	public String toString() {
		return km + "KM";
	}
	
	@Override
	public boolean equals(Object objet) {
		if(objet instanceof Borne borne ) {
			return borne.equals(borne);
		}
		return false;
	}

}
