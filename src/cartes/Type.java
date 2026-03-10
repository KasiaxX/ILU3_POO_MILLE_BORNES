package cartes;

public enum Type {
	
	FEU("feu rouge", "feu vert", "increvable"),
	ESSENCE("panne essence", "essence", "citerne d'essence"),
	CREVAISON("crevaison", "rue de secours", "as du volant"),
	ACCIDENT("accident", "reparations", "véhicule prioritaire");
	
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
