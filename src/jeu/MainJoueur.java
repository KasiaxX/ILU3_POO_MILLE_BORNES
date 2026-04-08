package jeu;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import cartes.Carte;

public class MainJoueur implements Iterable<Carte>{
	private List <Carte> CartesDansMain;
	
	public MainJoueur() {
		this.CartesDansMain = new ArrayList<>();
	}
	
	public void prendre(Carte carte) {
		CartesDansMain.add(carte);
	}
	
	public void jouer(Carte carte) {
		assert CartesDansMain.contains(carte) : "il n'y a pas cette carte";
		CartesDansMain.remove(carte);
	}
	
	//affiche la liste des cartes 
	@Override
	public String toString() {
		//La classe ArrayList possede une implémentation de toString() 
		//qui affiche les elems sous la forme [Element1, Element2, ...]
		return CartesDansMain.toString();
	}

	//Permet d'utiliser la boucle for (Carte c : mainJoueur)
	@Override
	public Iterator<Carte> iterator() {
		return CartesDansMain.iterator();
	}
	

}
