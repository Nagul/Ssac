package agent.raisonnement;

import agent.agents.Agent;
import environnement.TypeSmallObject;

public class FetchAction extends SequentialAction {
	
	public FetchAction(Agent master, Task parent, TypeSmallObject target) {
		super(master,parent) ;
		master.addWant(target) ;
		
		subActions.add(new MoveAction(master, this, target)) ;
		subActions.add(new SelfAction(master, this, EventType.RETRAIT)) ;
	}

	@Override
	public boolean useful() {
		// TODO Auto-generated method stub
		return true;
	}
}
