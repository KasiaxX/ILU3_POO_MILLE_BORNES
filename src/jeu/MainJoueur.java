package jeu;

import java.util.ArrayList;
import java.util.List;

import cartes.Carte;

public class MainJoueur {
	private List <Carte> CartesDansMain;
	
	public MainJoueur() {
		this.CartesDansMain = new ArrayList<>();
	}
	
	public void prendre(Carte carte) {
		CartesDansMain.add(carte);
	}
	
	public void jouer(Carte carte) {
		assert CartesDansMain.contains(carte);
		CartesDansMain.remove(carte);
	}
	
//	// Partie 1 exo5
//	@Override
//	public String toString() {
//		
//	}
//
}
