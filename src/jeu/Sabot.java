package jeu;

import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.ConcurrentModificationException;
import cartes.Carte;

public class Sabot implements Iterable<Carte> {

	private Carte[] cartes;
	private int nbCartes;
	int modCount = 0;

	public Sabot(Carte[] cartes) {
		this.cartes = cartes;
		this.nbCartes = cartes.length;
	}

	public boolean estVide() {
		return nbCartes == 0;
	}

	public void ajouterCarte(Carte carte) {
		if (nbCartes >= cartes.length) {
			throw new RuntimeException("Trop de cartes dans le sabot");
		}
		cartes[nbCartes] = carte;
		nbCartes++;
		modCount++;
	}

	@Override
	public Iterator<Carte> iterator() {
		return new SabotIterator();
	}
	
	public Carte piocher() {
		
		if (estVide()) {
			throw new NoSuchElementException("Sabot vide");
		}
		
		Iterator<Carte> iter = iterator();
		Carte carte = iter.next();
		iter.remove();
		return carte;
	}
	
	public Carte prendreCarte() {
        return piocher();
    }

///////////////////////////////////// CLASSE "SABOTITERATOR" //////////////////////////////////////////////

	private class SabotIterator implements Iterator<Carte> {

		private int position = 0;
		private boolean canRemove = false;
		private int expectedModCount = modCount;

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
				throw new IllegalStateException("remove apres le next");
			}

			for (int i = position - 1; i < nbCartes - 1; i++) {
				cartes[i] = cartes[i + 1];
			}
			
			nbCartes--;
			position--;
			modCount++;
			expectedModCount = modCount;
			canRemove = false;
		}

		private void concurrentModification() {
			if (modCount != expectedModCount) {
				throw new ConcurrentModificationException();
			}
		}
	}

//////////////////////////////////////////////////////////////////////////////////////////////////////////

}