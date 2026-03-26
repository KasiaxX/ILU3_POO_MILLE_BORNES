package jeu;

import java.util.ArrayList;
import java.util.List;

import cartes.Carte;

public class ZoneDeJeu {
	
	private List <Carte> pileDeLimite;
	private List <Carte> pileBataille;
	private List <Carte> collectionDeBornes;
	
	public ZoneDeJeu() {
		pileDeLimite = new ArrayList<>();
		pileBataille = new ArrayList<>();
		collectionDeBornes = new ArrayList<>();
		
	}
	
}
