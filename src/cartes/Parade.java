package cartes;

public class Parade extends Bataille {

	public Parade(Type type) {
		super(type);
	}
	
	public String toString() {
		switch (type) {
			case FEU: {
				System.out.println("carte de attaque :" + type.getCarteParade());
			}
			case ESSENCE: {
				System.out.println("carte de attaque :" + type.getCarteParade());				
			}
			case CREVAISON: {
				System.out.println("carte de attaque :" + type.getCarteParade());				
			}
			case ACCIDENT: {
				System.out.println("carte de attaque :" + type.getCarteParade());				
			}
			default:
				throw new IllegalArgumentException("Unexpected value ");
			}
		
	}

}
