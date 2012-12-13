package environnement;
import java.util.ArrayList;
import java.util.List;

import affichage.TestQt;
import agent.agents.Agent;


public class EnvironnementImpl implements Environnement {
	
	private ArrayList<Agent> listAgent;
	private ArrayList<EnvObject> listEnvObject;
	private Terrain terrain;
	
	public EnvironnementImpl (int longueur, int largeur) {
		listAgent = new ArrayList<Agent>();
		listEnvObject = new ArrayList<EnvObject>();
		terrain = new Terrain(longueur, largeur);
	}
	
	public ArrayList<Agent> getAgents() {
		return listAgent;
	}

	public void addAgent(Agent agent) {
		listAgent.add(agent);
	}

	public void removeAgent(Agent agent) {
		listAgent.remove(agent);
	}

	public List<EnvObject> getEnvObjects() {
		return listEnvObject;
	}

	public void addEnvObject(EnvObject obj) {
		listEnvObject.add(obj);
	}

	
	public void removeEnvObject(EnvObject obj) {
		listEnvObject.remove(obj);
	}

	public void step() {
		/*
		 * passage du temps, donc gérer :
		 * decay des agents (faim, soif, fatigue)
		 * déplacements des agents
		 * catastrophes naturelles
		 * actions des agents
		 * mise à jour des raisonnements des agents
		 */
		affichage.TestQt.date += 1;
		for(Agent a : listAgent) {
			a.modFaim(-1);//valeur à déterminer
			a.modSoif(-1);//valeur à déterminer
			a.voir();
			a.updateGoal();
			a.getGoals().get(0).createPlanning();
			if (a.getPlanning().size() != 0) {
				a.getPlanning().get(0).step();
			}
		}
	}

	public void step(int n) {
		int i;
		for(i=0; i<n; i++) {
			step();
		}
	}

	public Terrain getTerrain() {
		return terrain;
	}
	
}
