package agent.raisonnement;
import java.util.List;

import agent.agents.Agent;
import environnement.Case;
import environnement.EnvObject;
import environnement.TypeTerrain;


public class Connaissance {
	/*
	 *  Les connaissances qu'apprennent les agents de l'environnement.
	 *  Certaines sont permanentes (ou se trouve le lac, la maison ...), 
	 *  certaines sont temporaires (j'ai vu des animaux sauvages dangereux pas loin)
	 */
	
	/*
	 * A PRIORI, une connaissance : un objet/agent/terrain � une coordonnee donn�e
	 * grosso modo la connaissance (incompl�te car pas tout nous int�resse) d'une case
	 * connaissance du type de terrain et de l'envobject permanent, agents : temporaires
	 * temporaire : oubliable == date en step de la fin;
	 */
	private Case souvenir;
	private int oubliable;
	
	public Connaissance(Case c, int o) {
		souvenir = c;
		oubliable = o;
	}
	
	public Connaissance(TypeTerrain t, EnvObject eo, List<Agent> agents, int o) {
		souvenir = new Case(t, eo);
		for(Agent a : agents) {
			souvenir.addAgents(a);
		}
		oubliable = o;
	}
	
	public Case getSouvenir() {
		return souvenir;
	}
	
	public int getOubliable() {
		return oubliable;
	}
}
