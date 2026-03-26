package cartes;

public class JeuDeCartes {

	private Configuration[] typesCartes = { new Configuration(new Botte(Type.FEU), 1),
			new Configuration(new Botte(Type.ESSENCE), 1), new Configuration(new Botte(Type.CREVAISON), 1),
			new Configuration(new Botte(Type.ACCIDENT), 1),

			new Configuration(new Attaque(Type.FEU), 5), new Configuration(new Attaque(Type.ESSENCE), 4),
			new Configuration(new Attaque(Type.CREVAISON), 3), new Configuration(new Attaque(Type.ACCIDENT), 3),

			new Configuration(new Parade(Type.FEU), 14), new Configuration(new Parade(Type.ESSENCE), 6),
			new Configuration(new Parade(Type.CREVAISON), 6), new Configuration(new Parade(Type.ACCIDENT), 6),

			new Configuration(new DebutLimite(), 4), new Configuration(new FinLimite(), 6),

			new Configuration(new Borne(25), 10), new Configuration(new Borne(50), 10),
			new Configuration(new Borne(75), 10), new Configuration(new Borne(100), 12),
			new Configuration(new Borne(200), 4), };

	/*
	 * vérifie que le tableau de cartes renvoyé par la méthode donnerCartes et
	 * conforme à la configuration, c’est-à-dire que le nombre d’exemplaires
	 * souhaité pour chaque type de carte a bien été respecté
	 */
	public boolean checkCount() {
		// recuperer le paquet a verifier
		Carte[] paquet = donnerCartes(); // donnerCartes -> fabriquer le tab complet de tt les cartes

		// lire une par une les lignes de tab typesCartes
		for (Configuration configuration : typesCartes) {
			int compteur = 0;

			// Inspecter chaque carte du paquet
			for (Carte carte : paquet) {
				if (carte.equals(configuration.getCarte())) {
					compteur++;
				}
			}
			// Si le compte ne correspond pas à la configuration, il y a une erreur
			if (compteur != configuration.getNbExemplaires()) {
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

	public Carte[] donnerCartes() {
		// Calculer la taille totale du tab
		int total = 0;
		for (int i = 0; i < typesCartes.length; i++) {
			total += typesCartes[i].getNbExemplaires();
		}

		// Remplir le tab final
		Carte[] toutesCartes = new Carte[total];
		int index = 0;
		for (int i = 0; i < typesCartes.length; i++) {
			for (int j = 0; j < typesCartes[i].getNbExemplaires(); j++) {
				toutesCartes[index++] = typesCartes[i].getCarte();
			}
		}
		return toutesCartes;
	}

	public String affichageJeuDeCartes() {
		StringBuilder stbild = new StringBuilder("JEU:\n\n");
		for (int i = 0; i < typesCartes.length; i++) {
			// éviter le null
			if (typesCartes[i] != null) {
				stbild.append(typesCartes[i].getNbExemplaires());
				stbild.append(" ");
				stbild.append(typesCartes[i].getCarte());
				stbild.append("\n");
			}
		}
		return stbild.toString();
	}
}
