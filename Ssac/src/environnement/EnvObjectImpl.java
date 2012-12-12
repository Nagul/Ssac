package environnement;

import java.util.ArrayList;

import agent.agents.Agent;
import agent.raisonnement.EventType;

public class EnvObjectImpl implements EnvObject {

	private Coordonnee coordonnee;
	private TypeObject type;
	
	public EnvObjectImpl(Coordonnee co, TypeObject t) {
		coordonnee = co;
		type = t;
	}
	
	public Coordonnee getCoordonnee() {
		return coordonnee;
	}

	public TypeObject getType() {
		return type;
	}

	@Override
	public ArrayList<TypeSmallObject> getTools(EventType type) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean available(EventType type) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean use(Agent master, EventType type) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public int estimateDuration(EventType type) {
		// TODO Auto-generated method stub
		return 0;
	}

}
