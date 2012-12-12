package agent.raisonnement;

import java.util.ArrayList;

import agent.agents.Agent;
import environnement.TypeSmallObject;

public class SelfAction extends Action {
	
	private EventType type ;
	
	public SelfAction(Agent master, Task parent, EventType type) {
		super(master, parent) ;
		this.type = type ;
	}
	
	public boolean useful() {
		return true ;
	}
	
	public void step() {
		boolean stop = true ; // Action réalisée en une étape par défaut
		boolean success = true ; // Action réussie par défaut
		switch(type) {
			case RETRAIT :
				master.getWanted() ;
				master.delay(10) ;
			case DEPOT :
				master.dropUnwanted() ;
				master.delay(5) ;
			case REPOS :
				double rate = 10 ; // à remplacer par taux de repos variable
				stop = ((master.getEnergie() + rate) >= 100);
				master.modEnergie(rate) ;
				master.delay(10) ;
			case REPAS :
				success = false ;
				ArrayList<TypeSmallObject> foods = master.getFoods() ;
				for(TypeSmallObject f : foods) {
					success = success || master.carries(f) ;
				}
				master.eat() ;
				master.delay(10) ;
		}
		if(stop) {
			parent.terminate(this,success) ;
		}
	}
	
	public int estimateDuration() {
		return 10 ;
	}

	@Override
	public void terminate(Action child, boolean success) {
		// TODO Auto-generated method stub
		
	}
}
