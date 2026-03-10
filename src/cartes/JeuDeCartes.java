package cartes;

public class JeuDeCartes {

	private Configuration[] typesCartes = new Configuration[19];
	private int nbTypes = 0;

	// Classe interne statique car elle ne dépend pas de l'instance de JeuDeCartes
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

	// Méthode pour ajouter une configuration au tableau
	public void ajouterConfiguration(Carte carte, int nb) {
		if (nbTypes < typesCartes.length) {
			typesCartes[nbTypes++] = new Configuration(carte, nb);
		}
	}
	
	public Carte[] donnerCartes() {
	    // 1. Calculer la taille totale du tableau 
	    int total = 0;
	    for (int i = 0; i < nbTypes; i++) {
	        total += typesCartes[i].getNbExemplaires();
	    }

	    // 2. Remplir le tableau final 
	    Carte[] toutesCartes = new Carte[total];
	    int index = 0;
	    for (int i = 0; i < nbTypes; i++) {
	        for (int j = 0; j < typesCartes[i].getNbExemplaires(); j++) {
	            toutesCartes[index++] = typesCartes[i].getCarte();
	        }
	    }
	    return toutesCartes;
	}
	
	public String affichageJeuDeCartes() {
	    StringBuilder sb = new StringBuilder("JEU:\n\n");
	    for (int i = 0; i < typesCartes.length; i++) {
	        // Condition essentielle pour éviter le "null"
	        if (typesCartes[i] != null) { 
	            sb.append(typesCartes[i].getNbExemplaires())
	              .append(" ")
	              .append(typesCartes[i].getCarte().toString())
	              .append("\n");
	        }
	    }
	    return sb.toString();
	}
}
