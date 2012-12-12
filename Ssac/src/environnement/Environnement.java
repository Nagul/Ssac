package environnement;

import java.util.ArrayList;
import java.util.List;

import agent.agents.Agent;

public interface Environnement {
	/**
	 * Retourne les Agents appartenant à l'environnement
	 * 
	 * @return Les Agents appartenant à l'environnement
	 */
	ArrayList<Agent> getAgents();

	/**
	 * Ajoute un Agent à l'environnement
	 * 
	 * @param agent L'agent qui doit être ajouté          
	 */
	public void addAgent(Agent agent);

	/**
	 * Supprime un Agent de l'environnement
	 * 
	 * @param agent L'agent qui doit être supprimé
	 */
	public void removeAgent(Agent agent);

	/**
	 * Retourne les EnvObjects appartenant à l'environnement
	 * 
	 * @return les EnvObjects appartenant à l'environnement
	 */
	public List<EnvObject> getEnvObjects();

	/**
	 * Ajoute un EnvObjects à l'environnement
	 * 
	 * @param obj L'EnvObjects qui doit être ajouté 
	 */
	public void addEnvObject(EnvObject obj);

	/**
	 * Supprime un EnvObject de l'environnement
	 * 
	 * @param obj L'EnvObject qui doit être supprimé
	 */
	public void removeEnvObject(EnvObject obj);

	/**
	 * Avance le temps d'un pas
	 */
	public void step();

	/**
	 * Avance le temps de n pas
	 * 
	 * @param n le nombre de pas avancés
	 */
	public void step(int n);
	
	/**
	 * Retourne le terrain
	 * 
	 * @return le terrain
	 */
	public Terrain getTerrain();
	
}