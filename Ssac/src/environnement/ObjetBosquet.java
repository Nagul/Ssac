package environnement;

import java.util.ArrayList;

import agent.agents.Agent;
import agent.raisonnement.EventType;

public class ObjetBosquet extends EnvObjectImpl {

	private int reste ;
	
	public ObjetBosquet(Coordonnee co) {
		super(co);
		type = TypeObject.BOSQUET ;
		reste = 30 ;
	}

	@Override
	public ArrayList<TypeSmallObject> getTools(EventType type) {
		ArrayList<TypeSmallObject> list = new ArrayList<TypeSmallObject>() ;
		if(type == EventType.UTILISATION) {
			list.add(TypeSmallObject.HACHE) ;
		}
		return list ;
	}

	@Override
	public boolean available(EventType type) {
		return ((type == EventType.UTILISATION) && (reste > 0));
	}

	@Override
	public boolean use(Agent master, EventType type) {
		if(type == EventType.UTILISATION) {
			getCase().addStuff(TypeSmallObject.BOIS) ;
			reste -= 1 ;
		}
		return true ;
	}

	@Override
	public int estimateDuration(EventType type) {
		if(type == EventType.UTILISATION) {
			return 30;
		} else {
			return 0 ;
		}
	}

}
