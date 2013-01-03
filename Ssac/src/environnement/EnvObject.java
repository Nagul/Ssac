package environnement;

import java.util.ArrayList;

import agent.agents.Agent;
import agent.raisonnement.EventType;

public interface EnvObject {

	/**
	 * Retourne les coordonnees de l'EnvObject
	 * 
	 * @return coordonnees de l'EnvObject
	 */
	public Coordonnee getCoordonnee();
	
	/**
	 * Retourne le type de l'EnvObject (constructions, terrain etc)
	 * 
	 * @return type de l'EnvObject
	 */
	public TypeObject getType();
	
	public ArrayList<TypeSmallObject> getTools(EventType type) ;
	
	/**
	 * Test d'utilisabilité
	 * 
	 * @param type Type d'action
	 * @return true si l'objet est utilisable pour ce type d'action
	 */
	public boolean available(EventType type);

	/**
	 * Usage effectif de l'objet
	 * @param master Agent utilisant l'objet
	 * @param type Type d'action
	 * @return true si l'action est terminée
	 */
	public boolean use(Agent master, EventType type);

	public int estimateDuration(EventType type);
}
