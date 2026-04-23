package jeu;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import cartes.Carte;

public class MainJoueur implements Iterable<Carte>{
	private List <Carte> cartesDansMain;
	
	public MainJoueur() {
		this.cartesDansMain = new LinkedList<>();
	}
	
	public void prendre(Carte carte) {
		cartesDansMain.add(carte);
	}
	
	public void jouer(Carte carte) {
		assert cartesDansMain.contains(carte) : "il n'y a pas cette carte";
		cartesDansMain.remove(carte);
	}
	
	//affiche la liste des cartes 
	@Override
	public String toString() {
		//La classe ArrayList possede une implémentation de toString() 
		//qui affiche les elems sous la forme [Element1, Element2, ...]
		return cartesDansMain.toString();
	}

	//Permet d'utiliser la boucle for (Carte c : mainJoueur)
	@Override
	public Iterator<Carte> iterator() {
		return cartesDansMain.iterator();
	}
	

}
