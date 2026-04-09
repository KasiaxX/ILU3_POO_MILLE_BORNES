package jeu;

import java.util.ArrayList;
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

public class ZoneDeJeu {
	
	private List <Carte> pileDeLimite;
	private List <Carte> pileBataille;
	private List <Carte> collectionDeBornes;
	
	private Set<Botte> ensembleBotte;
	
	
	public ZoneDeJeu() {
		pileDeLimite = new ArrayList<>();
		pileBataille = new ArrayList<>();
		collectionDeBornes = new ArrayList<>();
		
	}
	
	// --- PARTIE 2 ----
	
	public int donnerLimitationVitesse() {
	    // Si la pile est vide ou que la derniere carte posée est une FinLimite
	    if (pileDeLimite.isEmpty() || pileDeLimite.get(pileDeLimite.size() - 1) instanceof FinLimite) {
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
	
	//ranger la carte au bon endroit selon son type
	public void deposer(Carte carte) {
	    if (carte instanceof Borne) {
	        collectionDeBornes.add(carte);
	        
	    } else if (carte instanceof Limite) {
	        pileDeLimite.add(carte);
	        
	    } else if (carte instanceof Bataille) {
	        pileBataille.add(carte);
	    }
	}
	
	
	// --- PARTIE 3 ---
	
	public boolean peutAvancer() {
        // On peut avancer si le sommet de la pile bataille est un FEU_VERT
        if (pileBataille.isEmpty()) {
        	return false;
        }
        Carte sommet = pileBataille.get(pileBataille.size() - 1);
        
        return sommet.equals(Cartes.FEU_VERT);
    }
	
	private boolean estDepotFeuVertAutorise() {
        if (pileBataille.isEmpty()) { 
        	return true;
        }
        Carte sommet = pileBataille.get(pileBataille.size() - 1);
        // Autorise si sommet est Feu Rouge ou une Parade (qui n'est pas déjà un vert)
        return sommet.equals(Cartes.FEU_ROUGE) || (sommet instanceof Parade && !sommet.equals(Cartes.FEU_VERT));
    }
	
	private boolean estDepotBorneAutorise(Borne borne) {
        boolean pasBloque = peutAvancer();
        
        boolean respectLimite = borne.getKm() <= donnerLimitationVitesse();
        
        boolean scoreOk = (donnerKmParcourus() + borne.getKm()) <= 1000;
        
        return pasBloque && respectLimite && scoreOk;
    }
	
	private boolean estDepotLimiteAutorise(Limite limite) {
        if (limite instanceof DebutLimite) {
            return pileDeLimite.isEmpty() || pileDeLimite.get(pileDeLimite.size() - 1) instanceof FinLimite;
            
        } else if (limite instanceof FinLimite) {
        	return !pileDeLimite.isEmpty() && pileDeLimite.get(pileDeLimite.size() - 1) instanceof DebutLimite;
        }
        
        return false;
    }
	
	private boolean estDepotBatailleAutorise(Bataille bataille) {
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
            
            Carte sommet = pileBataille.get(pileBataille.size() - 1);
            
            return (sommet instanceof Attaque && ((Attaque) sommet).getType().equals(parade.getType()));
        }
        return false;
    }

    
    public boolean estDepotAutorise(Carte carte) {
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
    
    // --- TP4 ---
    
    public boolean estPrioritaire() {
    	
    	
    	return false;
    }
}
	
	
