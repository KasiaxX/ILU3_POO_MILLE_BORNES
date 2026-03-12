package jeu;

import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.ConcurrentModificationException;
import cartes.Carte;

public class Sabot implements Iterable<Carte> {

	private Carte[] cartes;
	private int nbCartes;
	int modifCount = 0;

	public Sabot(Carte[] cartes) {
		this.cartes = cartes;
		this.nbCartes = cartes.length;
	}

	public boolean estVide() {
		return nbCartes == 0;
	}

	public void ajouterCarte(Carte carte) {
		if (nbCartes >= cartes.length) {
			throw new RuntimeException("Trop de cartes");
		}
		cartes[nbCartes] = carte;
		nbCartes++;
		modifCount++;
	}

	@Override
	public Iterator<Carte> iterator() {
		return new SabotIterator();
	}
	
	public Carte piocher() {
		
		if (estVide()) {
			throw new NoSuchElementException("vide");
		}
		
		Iterator<Carte> iter = iterator();
		Carte carte = iter.next();
		iter.remove();
		return carte;
	}
	
	public Carte prendreCarte() {
        return piocher();
    }

//class interne

	private class SabotIterator implements Iterator<Carte> {

		private int position = 0;
		private boolean canRemove = false;
		private int expectedModifCount = modifCount;

		@Override
		public boolean hasNext() {
			concurrentModification();
			return position < nbCartes;
		}

		@Override
		public Carte next() {
			concurrentModification();
			
			if (!hasNext()) {
				throw new NoSuchElementException();
			}
			canRemove = true;
			return cartes[position++];
		}

		@Override
		public void remove() {

			concurrentModification();
			
			if (!canRemove) {
				throw new IllegalStateException("il faut remove apres le Next");
			}

			for (int i = position - 1; i < nbCartes - 1; i++) {
				cartes[i] = cartes[i + 1];
			}
			
			nbCartes--;
			position--;
			modifCount++;
			expectedModifCount = modifCount;
			canRemove = false;
		}

		private void concurrentModification() {
			if (modifCount != expectedModifCount) {
				throw new ConcurrentModificationException();
			}
		}
	}

//////////////////////////////////////////////////////////////////////////////////////////////////////////

}