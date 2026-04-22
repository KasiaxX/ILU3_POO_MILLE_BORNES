package cartes;

import java.util.LinkedHashMap;
import java.util.Map;

public class JeuDeCartes {
	
	// On remplace le tableau par une Map
	private Map<Carte, Integer> configuration;

	public JeuDeCartes() {
		// Initialisation de la map dans le constructeur
		configuration = new LinkedHashMap<>();
		
		configuration.put(new Botte(Type.FEU), 1);
		configuration.put(new Botte(Type.ESSENCE), 1);
		configuration.put(new Botte(Type.CREVAISON), 1);
		configuration.put(new Botte(Type.ACCIDENT), 1);

		configuration.put(new Attaque(Type.FEU), 5);
		configuration.put(new Attaque(Type.ESSENCE), 4);
		configuration.put(new Attaque(Type.CREVAISON), 3);
		configuration.put(new Attaque(Type.ACCIDENT), 3);

		configuration.put(new Parade(Type.FEU), 14);
		configuration.put(new Parade(Type.ESSENCE), 6);
		configuration.put(new Parade(Type.CREVAISON), 6);
		configuration.put(new Parade(Type.ACCIDENT), 6);

		configuration.put(new DebutLimite(), 4);
		configuration.put(new FinLimite(), 6);

		configuration.put(new Borne(25), 10);
		configuration.put(new Borne(50), 10);
		configuration.put(new Borne(75), 10);
		configuration.put(new Borne(100), 12);
		configuration.put(new Borne(200), 4);
	}
//			private Configuration[] typesCartes = { new Configuration(new Botte(Type.FEU), 1),
//			new Configuration(new Botte(Type.ESSENCE), 1), new Configuration(new Botte(Type.CREVAISON), 1),
//			new Configuration(new Botte(Type.ACCIDENT), 1),
//
//			new Configuration(new Attaque(Type.FEU), 5), new Configuration(new Attaque(Type.ESSENCE), 4),
//			new Configuration(new Attaque(Type.CREVAISON), 3), new Configuration(new Attaque(Type.ACCIDENT), 3),
//
//			new Configuration(new Parade(Type.FEU), 14), new Configuration(new Parade(Type.ESSENCE), 6),
//			new Configuration(new Parade(Type.CREVAISON), 6), new Configuration(new Parade(Type.ACCIDENT), 6),
//
//			new Configuration(new DebutLimite(), 4), new Configuration(new FinLimite(), 6),
//
//			new Configuration(new Borne(25), 10), new Configuration(new Borne(50), 10),
//			new Configuration(new Borne(75), 10), new Configuration(new Borne(100), 12),
//			new Configuration(new Borne(200), 4), };

	/*
	 * vérifie que le tableau de cartes renvoyé par la méthode donnerCartes et
	 * conforme à la configuration, c’est-à-dire que le nombre d’exemplaires
	 * souhaité pour chaque type de carte a bien été respecté
	 */
	public boolean checkCount() {
		// recuperer le paquet a verifier
		Carte[] paquet = donnerCartes(); // donnerCartes -> fabriquer le tab complet de tt les cartes

		// lire une par une les lignes de tab typesCartes
		//for (Configuration configuration : typesCartes) {
		for (Map.Entry<Carte, Integer> entry : configuration.entrySet()) {
			Carte carteConfig = entry.getKey();
			int nbAttendus = entry.getValue();
			
			int compteur = 0;

			// Inspecter chaque carte du paquet
			//for (Carte carte : paquet) {
			for (Carte carteDansPaquet : paquet) {
				//if (carte.equals(configuration.getCarte())) {
				if (carteDansPaquet.equals(carteConfig)) {
					compteur++;
				}
			}
			// Si le compte ne correspond pas à la configuration, il y a une erreur
			//if (compteur != configuration.getNbExemplaires()) {
			if (compteur != nbAttendus) {
				return false;
			}
		}

		return true;
	}

	private int nbTypes = 0;

	// Classe interne statique car elle depend pas de l'instance de JeuDeCartes
	private static class Configuration {
		private Carte carte;
		private int nbExemplaires;

		private Configuration(Carte carte, int nbExemplaires) {
			this.carte = carte;
			this.nbExemplaires = nbExemplaires;
		}

		public Carte getCarte() {
			return carte;
		}

		public int getNbExemplaires() {
			return nbExemplaires;
		}
	}

//	public Carte[] donnerCartes() {
//		// Calculer la taille totale du tab
//		int total = 0;
//		for (int i = 0; i < typesCartes.length; i++) {
//			total += typesCartes[i].getNbExemplaires();
//		}
//
//		// Remplir le tab final
//		Carte[] toutesCartes = new Carte[total];
//		int index = 0;
//		for (int i = 0; i < typesCartes.length; i++) {
//			for (int j = 0; j < typesCartes[i].getNbExemplaires(); j++) {
//				toutesCartes[index++] = typesCartes[i].getCarte();
//			}
//		}
//		return toutesCartes;
//	}
	
	public Carte[] donnerCartes() {
		int total = 0;
		// On calcule le total en sommant les valeurs de la Map
		for (Integer nbExemplaires : configuration.values()) {
			total += nbExemplaires;
		}

		Carte[] toutesCartes = new Carte[total];
		int index = 0;
		
		// Remplissage du tableau final
		for (Map.Entry<Carte, Integer> entry : configuration.entrySet()) {
			Carte carte = entry.getKey();
			int nbExemplaires = entry.getValue();
			
			for (int j = 0; j < nbExemplaires; j++) {
				toutesCartes[index++] = carte; // Attention, on utilise bien la même référence de carte
			}
		}
		return toutesCartes;
	}

//	public String affichageJeuDeCartes() {
//		StringBuilder stbild = new StringBuilder("JEU:\n\n");
//		for (int i = 0; i < typesCartes.length; i++) {
//			// éviter le null
//			if (typesCartes[i] != null) {
//				stbild.append(typesCartes[i].getNbExemplaires());
//				stbild.append(" ");
//				stbild.append(typesCartes[i].getCarte());
//				stbild.append("\n");
//			}
//		}
//		return stbild.toString();
//	}
	
	public String affichageJeuDeCartes() {
		StringBuilder stbild = new StringBuilder("JEU:\n\n");
		
		for (Map.Entry<Carte, Integer> entry : configuration.entrySet()) {
			stbild.append(entry.getValue());
			stbild.append(" ");
			stbild.append(entry.getKey());
			stbild.append("\n");
		}
		
		return stbild.toString();
	}
}
