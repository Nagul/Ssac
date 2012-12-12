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

	public boolean available(EventType type);

	public boolean use(Agent master, EventType type);

	public int estimateDuration(EventType type);
}
