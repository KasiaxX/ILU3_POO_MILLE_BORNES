package jeu;

import cartes.Attaque;
import cartes.Botte;
import cartes.Parade;
import cartes.Type;

public interface Cartes {
	
	// Une botte pour être immunisé et prioritaire
	Botte PRIORITAIRE = new Botte(Type.FEU);
	
	// L'attaque qui bloque le joueur
	Attaque FEU_ROUGE = new Attaque(Type.FEU);
	
	// La parade qui permet de repartir
	Parade FEU_VERT = new Parade(Type.FEU);

}
