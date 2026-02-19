package cartes;

public class Botte extends Probleme {

	public Botte(Type type) {
		super(type);
	}
	
	public String toString() {
		switch (type) {
			case FEU: {
				System.out.println("carte de attaque :" + type.getCarteBotte());
			}
			case ESSENCE: {
				System.out.println("carte de attaque :" + type.getCarteBotte());				
			}
			case CREVAISON: {
				System.out.println("carte de attaque :" + type.getCarteBotte());				
			}
			case ACCIDENT: {
				System.out.println("carte de attaque :" + type.getCarteBotte());				
			}
			default:
				throw new IllegalArgumentException("Unexpected value ");
			}
		
	}
	
	

}
