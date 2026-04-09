package utils;

import java.util.*;

public class GestionCartes {

	private static final Random rand = new Random();

	private GestionCartes() {
		throw new IllegalStateException("Utility Class");
	}

	// EXTRAIRE : version 1
	public static <T> T extraire(List<T> liste) {

		int index = rand.nextInt(liste.size());
		return liste.remove(index);
	}

	// EXTRAIRE : version 2
	public static <T> T extraireIterateur(List<T> liste) {

		int indice = rand.nextInt(liste.size());

		ListIterator<T> iterator = liste.listIterator(indice);
		//TODO boucle inutile utiliser la propriété du ListIterator
		T elem = iterator.next();
		iterator.remove();

		return elem;
	}

//	public static <T> T extraireIterator(List<T> liste) {
//		int indice = rand.nextInt(liste.size());
//	
//		ListIterator<T> it = liste.listIterator(indice);
//		T element = it.next();
//		it.remove();
//	
//		return element;
//	}

	// MELANGER
	public static <T> List<T> melanger(List<T> liste) {
		List<T> listeMelangee = new ArrayList<>();

		while (!liste.isEmpty()) {
			listeMelangee.add(extraire(liste));
		}
		return listeMelangee;
	}

	// VerfierMelanger

	public static <T> boolean verifierMelange(List<T> liste1, List<T> liste2) {
		if (liste1.size() != liste2.size())
			return false;

		for (T element : liste1) {
			if (Collections.frequency(liste1, element) != Collections.frequency(liste2, element)) {
				return false;
			}
		}
		return true;
	}

	// RASSEMBLER
	public static <T> List<T> rassemblerV2(List<T> liste) {

		List<T> listeRassemblee = new ArrayList<>();

		while (!liste.isEmpty()) {
			T element = liste.remove(0);
			listeRassemblee.add(element);
			Iterator<T> it = liste.iterator();

			while (it.hasNext()) {
				T elem = it.next();

				if (elem.equals(element)) {
					listeRassemblee.add(elem);
					it.remove();
				}
			}
		}
		return listeRassemblee;
	}

//	public static <T> List<T> rassemberV2(List<T> liste) {
//		List<T> listeRassemblee = new ArrayList<>();
//		for (T element : liste) {
//			if (!listeRassemblee.contains(element)) {
//				for (T e : liste) {
//					if (e.equals(element)) {
//						listeRassemblee.add(e);
//					}
//				}
//			}
//		}
//		return listeRassemblee;
//	}

	// Verifier RASSEMBLEMENT

	//TODO utiliser 2 méthodes en utilisant la propriété du constructeur du ListIterator 
	public static <T> boolean verifierRassemblement(List<T> liste) {
		if (liste.isEmpty())
			return true;

		ListIterator<T> it1 = liste.listIterator();
		T precedent = it1.next();

		while (it1.hasNext()) {
			T courant = it1.next();

			if (!courant.equals(precedent)) {
				// it1.nextIndex() donne la osition exacte pour commancer la recherche
				
				boolean elemTrouverApres = chercherElemApresIndex(liste, precedent, it1.nextIndex());
				
				if(elemTrouverApres) {
					return false;
				}
 				// On met à jour la valeur precedente
				precedent = courant;
			}
		}
		return true;
	}
	
	private static <T> boolean chercherElemApresIndex(List<T> liste, T element, int indexDepart) {
		
		ListIterator<T> iterator = liste.listIterator(indexDepart);
		
		while (iterator.hasNext()) {
			if (iterator.next().equals(element)) {
				return true;
			}
		}
		return false; //elem n'existe plus apres cette index
		
		
		
	} 
	
	
}

//	public static <T> boolean verifierRassemblement(List<T> liste) {
//		ListIterator<T> it1 = liste.listIterator();
//
//		while (it1.hasNext()) {
//		T courant = it1.next();
//		ListIterator<T> it2 = liste.listIterator(it1.nextIndex());
//	
//		while (it2.hasNext()) {
//			T suivant = it2.next();
//			if (suivant.equals(courant)) {
//				return false;
//			}
//			if (!suivant.equals(courant)) {
//				break;
//			}
//		}
//		
//		return true;
//		}
//}
