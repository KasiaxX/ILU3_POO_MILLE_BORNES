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
			//verifier que c'est bien carte borne + verifier les km
		return super.equals(objet) && km == ((Borne)objet).getKm();
	}

}
