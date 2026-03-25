package cartes;

public enum Type {
	
	FEU("Feu rouge", "Feu vert", "Véhicule prioritaire"),
	ESSENCE("Panne d'essence", "Essence", "Citerne d'essence"),
	CREVAISON("Crevaison", "Roue de secours", "Increvable"),
	ACCIDENT("Accident", "Reparations", "As du volant");
	
	private String carteAttaque;
	private String carteParade;
	private String carteBotte;

	private Type(String carteAttaque, String carteParade, String carteBotte) {
		this.carteAttaque = carteAttaque;
		this.carteParade = carteParade;
		this.carteBotte = carteBotte;
	}
	
	public String getCarteAttaque() {
		return carteAttaque;
	}
	
	public String getCarteParade() {
		return carteParade;
	}

	public String getCarteBotte() {
		return carteBotte;
	}
}
