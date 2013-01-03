package environnement;

import java.util.ArrayList;

import affichage.TestQt;
import agent.agents.Agent;
import agent.raisonnement.EventType;

public abstract class EnvObjectImpl implements EnvObject {

	private Coordonnee coordonnee;
	protected TypeObject type;
	
	public EnvObjectImpl(Coordonnee co) {
		coordonnee = co;
	}
	
	public Coordonnee getCoordonnee() {
		return coordonnee;
	}

	public TypeObject getType() {
		return type;
	}

	public Case getCase() {
		return TestQt.environnement.getTerrain().getCase(this.coordonnee) ;
	}
	
	@Override
	public abstract ArrayList<TypeSmallObject> getTools(EventType type);

	@Override
	public abstract boolean available(EventType type);

	@Override
	public abstract boolean use(Agent master, EventType type);

	@Override
	public abstract int estimateDuration(EventType type) ;

}
