package jeu;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

import cartes.Attaque;
import cartes.Bataille;
import cartes.Borne;
import cartes.Botte;
import cartes.Carte;
import cartes.DebutLimite;
import cartes.FinLimite;
import cartes.Limite;
import cartes.Parade;
import cartes.Type;

public class ZoneDeJeu {
	
	private List <Carte> pileDeLimite = new LinkedList<>();
	private List <Carte> pileBataille = new LinkedList<>();
	private List <Carte> collectionDeBornes = new LinkedList<>();
	
	// TP4 Q1 : HashSet pour gérer les bottes uniques sans doublons
	private Set<Botte> ensembleBottes = new HashSet<>(); // Initialisation du HashSet
	
	
	public ZoneDeJeu() {
	}
	
	//TP4 Q2
	public boolean estPrioritaire() {
		return ensembleBottes.contains(Cartes.PRIORITAIRE);
	}
	
	//verifier si le joueur a la botte associee à un type
	private boolean possedeBotte(Type type) {
		return ensembleBottes.contains(new Botte(type));
	}
	
	// --- PARTIE 2 ----
	
	public int donnerLimitationVitesse() {
		//TP4 Q3
	    // Si la pile est prioritaire ou vide ou que la derniere carte posée est une FinLimite
	    if (estPrioritaire() || pileDeLimite.isEmpty() || pileDeLimite.get(0) instanceof FinLimite) {
	        return 200;
	    }
	    return 50;
	}
	
	//on doit sommer toutes les cartes Borne accumulées.
	public int donnerKmParcourus() {
	    int total = 0;
	    for (Carte carte : collectionDeBornes) {
	        if (carte instanceof Borne borne) {
	            total += borne.getKm();
	        }
	    }
	    return total;
	}
	
	//TP4 Q4 ajouter le depot des bottes
	//ranger la carte au bon endroit selon son type
	public void deposer(Carte carte) {
	    if (carte instanceof Borne) {
	        collectionDeBornes.add(0, carte);
	        
	    } else if (carte instanceof Limite) {
	        pileDeLimite.add(0, carte);
	        
	    } else if (carte instanceof Bataille) {
	        pileBataille.add(0, carte);
	        
	    } else if (carte instanceof Botte botte) {
	    	ensembleBottes.add(botte);
	    }
	}
	
	
	// --- PARTIE 3 ---
	
	
	public boolean peutAvancer() {
        // On peut avancer si le sommet de la pile bataille est un FEU_VERT
        if (pileBataille.isEmpty()) {
        	//TP4 Q5
        	return estPrioritaire();
        }
        Carte sommet = pileBataille.get(0);
        
        return sommet.equals(Cartes.FEU_VERT);
    }
	
	//TP4 Q6
	private boolean estDepotFeuVertAutorise() {
		if (estPrioritaire()) {
			return false;
		}
		
        if (pileBataille.isEmpty()) { 
        	return true;
        }
        Carte sommet = pileBataille.get(0);
        
        if (sommet.equals(Cartes.FEU_ROUGE) || (sommet instanceof Parade && !sommet.equals(Cartes.FEU_VERT))) {
        	return true;
        }  
 
        if (sommet instanceof Attaque attaque && possedeBotte(attaque.getType())) {
        	return true;
        }
        
        return false;
    }
	
	
	private boolean estDepotBorneAutorise(Borne borne) {
        boolean pasBloque = peutAvancer();
        
        boolean respectLimite = borne.getKm() <= donnerLimitationVitesse();
        
        boolean scoreOk = (donnerKmParcourus() + borne.getKm()) <= 1000;
        
        return pasBloque && respectLimite && scoreOk;
    }
	
	//TP4 Q7
	private boolean estDepotLimiteAutorise(Limite limite) {
		// Inutile de déposer une limite si on est prioritaire
		if (estPrioritaire()) { 
			return false; 
		}
			
        if (limite instanceof DebutLimite) {
        	//instanceof  : "Est-ce que cet objet a été créé à partir de cette classe ?"
        	//instanceof DebutLimite : vérifier le type exact de la carte qui se trouve au sommet de la pile
            return pileDeLimite.isEmpty() || pileDeLimite.get(0) instanceof FinLimite;
            
        } else if (limite instanceof FinLimite) {
        	return !pileDeLimite.isEmpty() && pileDeLimite.get(0) instanceof DebutLimite;
        }
        
        return false;
    }
	
	//TP4 Q8
	private boolean estDepotBatailleAutorise(Bataille bataille) {
		// Impossible d'attaquer ou parer si le joueur a déjà la botte d'immunité
		if (possedeBotte(bataille.getType())) { 
			return false;
		}
				
        if (bataille instanceof Attaque) {
            return peutAvancer(); // On peut attaquer si le joueur n'est pas deja bloque
            
        } else if (bataille instanceof Parade parade) {
            if (parade.equals(Cartes.FEU_VERT)) {
                return estDepotFeuVertAutorise();
            }
            // Pour les autres parades, il faut que le sommet soit l'attaque correspondante
            if (pileBataille.isEmpty()) {
            	return false;
            }
            
            Carte sommet = pileBataille.get(0);
            
            return (sommet instanceof Attaque && ((Attaque) sommet).getType().equals(parade.getType()));
        }
        return false;
    }

    
	//TP4 Q9
    public boolean estDepotAutorise(Carte carte) {
    	// Ajout des bottes sans condition
    	if (carte instanceof Botte) {
    		return true; 
    	}
    	
        if (carte instanceof Borne borne) {
        	return estDepotBorneAutorise(borne);
        }
        
        if (carte instanceof Limite limite) {
        	return estDepotLimiteAutorise(limite);
        }
        
        if (carte instanceof Bataille bataille) {
        	return estDepotBatailleAutorise(bataille);
        }
        
        return false;
    }
    
}
	
	
