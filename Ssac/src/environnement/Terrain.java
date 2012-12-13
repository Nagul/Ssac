package environnement;

import java.util.HashMap;

import pathFinding.TileBasedMap;
import affichage.TestQt;
import agent.agents.Agent;
import agent.agents.AgentHumainImpl;
import agent.agents.AgentLoup;
import agent.agents.AgentMouton;
import agent.agents.AgentOiseau;
import agent.agents.AgentOurs;
import agent.agents.AgentPoisson;
import agent.agents.AgentRongeur;
import agent.raisonnement.Connaissance;

public class Terrain implements TileBasedMap {
	
	private final int	longueur;
	private final int	largeur;
	private Case[][]	cases;
	
	public Terrain(int lo, int la) {
		longueur = lo;
		largeur = la;
		cases = new Case[lo][la];
	}
	
	// pour générer une carte à partir d'une hashmap d'un agent, peut etre pas opti à voir
	public Terrain(HashMap<Coordonnee, Connaissance> map) {
		longueur = TestQt.environnement.getTerrain().getLongueur();
		largeur = TestQt.environnement.getTerrain().getLargeur();
		cases = new Case[longueur][largeur];
		
		for (Coordonnee co : map.keySet()) {
			setTerrain(co.getAbscisse(), co.getOrdonnee(), ((Connaissance) map.get(co)).getSouvenir().getType());
		}
	}
	
	// générer une carte vierge, c'est sale oui...
	public Terrain(boolean crados) {
		if (crados) {
			longueur = TestQt.environnement.getTerrain().getLongueur();
			largeur = TestQt.environnement.getTerrain().getLargeur();
			cases = new Case[longueur][largeur];
			for (int i = 0; i < longueur; i++) {
				for (int j = 0; j < largeur; j++) {
					setTerrain(i,j,TypeTerrain.Terre);
				}
			}
		}
		else {
			longueur = TestQt.environnement.getTerrain().getLongueur();
			largeur = TestQt.environnement.getTerrain().getLargeur();
			cases = new Case[longueur][largeur];
		}
	}
	
	public Case getCase(int a, int o) {
		return cases[a][o];
	}
	
	public Case getCase(Coordonnee c) {
		return getCase(c.getAbscisse(), c.getOrdonnee());
	}
	
	public int getLongueur() {
		return longueur;
	}
	
	public int getLargeur() {
		return largeur;
	}
	
	public void setTerrain(int a, int o, TypeTerrain t) {
		cases[a][o] = new Case(t, null);
	}
	
	//Méthodes pour implanter TileBasedMap
	public boolean blocked(Agent agent, int x, int y) {
		if (agent instanceof AgentHumainImpl) {
			return (cases[x][y].getType() == TypeTerrain.Montagne);
		}
		if (agent instanceof AgentLoup) {
			return (cases[x][y].getType() == TypeTerrain.Montagne);
		}
		if (agent instanceof AgentMouton) {
			return (cases[x][y].getType() == TypeTerrain.Montagne);
		}
		if (agent instanceof AgentOiseau) {
			return false;
		}
		if (agent instanceof AgentOurs) {
			return (cases[x][y].getType() == TypeTerrain.Montagne);
		}
		if (agent instanceof AgentPoisson) {
			return (cases[x][y].getType() == TypeTerrain.Terre || cases[x][y].getType() == TypeTerrain.Montagne || cases[x][y].getType() == TypeTerrain.Foret);
		}
		if (agent instanceof AgentRongeur) {
			return (cases[x][y].getType() == TypeTerrain.EauDeMer || cases[x][y].getType() == TypeTerrain.Montagne || cases[x][y].getType() == TypeTerrain.EauDouce);
		}
		throw new Error("Erreur terrain blocked");
	}
	
	public float getCost(Agent agent, int sx, int sy, int tx, int ty) {
		if (agent instanceof AgentHumainImpl) {
			switch (cases[sx][sy].getType()) {
				case EauDeMer:
					return 4;
				case EauDouce:
					return 4;
				case Foret:
					return 2;
				case Terre:
					return 1;
				default:
					throw new Error("Erreur terrain getCost");
			}
		}
		if (agent instanceof AgentLoup) {
			switch (cases[sx][sy].getType()) {
				case EauDeMer:
					return 6;
				case EauDouce:
					return 6;
				case Foret:
					return 1;
				case Terre:
					return 1;
				default:
					throw new Error("Erreur terrain getCost");
			}
		}
		if (agent instanceof AgentMouton) {
			switch (cases[sx][sy].getType()) {
				case EauDeMer:
					return 6;
				case EauDouce:
					return 6;
				case Foret:
					return 2;
				case Terre:
					return 1;
				default:
					throw new Error("Erreur terrain getCost");
			}
		}
		if (agent instanceof AgentOiseau) {
			switch (cases[sx][sy].getType()) {
				case EauDeMer:
					return 1;
				case EauDouce:
					return 1;
				case Foret:
					return 1;
				case Montagne:
					return 1;
				case Terre:
					return 1;
				default:
					throw new Error("Erreur terrain getCost");
			}
		}
		if (agent instanceof AgentOurs) {
			switch (cases[sx][sy].getType()) {
				case EauDeMer:
					return 2;
				case EauDouce:
					return 2;
				case Foret:
					return 1;
				case Terre:
					return 1;
				default:
					throw new Error("Erreur terrain getCost");
			}
		}
		if (agent instanceof AgentPoisson) {
			switch (cases[sx][sy].getType()) {
				case EauDeMer:
					return 1;
				case EauDouce:
					return 1;
				default:
					throw new Error("Erreur terrain getCost");
			}
		}
		if (agent instanceof AgentRongeur) {
			switch (cases[sx][sy].getType()) {
				case Foret:
					return 1;
				case Terre:
					return 1;
				default:
					throw new Error("Erreur terrain getCost");
			}
		}
		throw new Error("Erreur terrain getCost");
	}
	
}
