package environnement;

import java.util.ArrayList;
import java.util.List;

import agent.agents.Agent;

public interface Environnement {
	/**
	 * Retourne les Agents appartenant � l'environnement
	 * 
	 * @return Les Agents appartenant � l'environnement
	 */
	ArrayList<Agent> getAgents();

	/**
	 * Ajoute un Agent � l'environnement
	 * 
	 * @param agent L'agent qui doit �tre ajout�          
	 */
	public void addAgent(Agent agent);

	/**
	 * Supprime un Agent de l'environnement
	 * 
	 * @param agent L'agent qui doit �tre supprim�
	 */
	public void removeAgent(Agent agent);

	/**
	 * Retourne les EnvObjects appartenant � l'environnement
	 * 
	 * @return les EnvObjects appartenant � l'environnement
	 */
	public List<EnvObject> getEnvObjects();

	/**
	 * Ajoute un EnvObjects � l'environnement
	 * 
	 * @param obj L'EnvObjects qui doit �tre ajout� 
	 */
	public void addEnvObject(EnvObject obj);

	/**
	 * Supprime un EnvObject de l'environnement
	 * 
	 * @param obj L'EnvObject qui doit �tre supprim�
	 */
	public void removeEnvObject(EnvObject obj);

	/**
	 * Avance le temps d'un pas
	 */
	public void step();

	/**
	 * Avance le temps de n pas
	 * 
	 * @param n le nombre de pas avanc�s
	 */
	public void step(int n);
	
	/**
	 * Retourne le terrain
	 * 
	 * @return le terrain
	 */
	public Terrain getTerrain();
	
}