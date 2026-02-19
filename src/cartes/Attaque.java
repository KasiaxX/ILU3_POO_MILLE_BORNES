package cartes;

public class Attaque extends Bataille {

	public Attaque(Type type) {
		super(type);
	}
	
	public String toString() {
		switch (type) {
			case FEU: {
				System.out.println("carte d'attaque :" + type.getCarteAttaque());
			}
			case ESSENCE: {
				System.out.println("carte d'attaque :" + type.getCarteAttaque());				
			}
			case CREVAISON: {
				System.out.println("carte d'attaque :" + type.getCarteAttaque());				
			}
			case ACCIDENT: {
				System.out.println("carte d'attaque :" + type.getCarteAttaque());				
			}
			default:
				throw new IllegalArgumentException("Unexpected value ");
			}
		
	}

}
