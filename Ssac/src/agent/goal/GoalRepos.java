package agent.goal;

import agent.agents.Agent;
import agent.raisonnement.EventType;
import agent.raisonnement.SelfAction;

public class GoalRepos extends Goal {

	
	public GoalRepos(Agent a) {
		super(a);
	}
	
	public void createPlanning() {
		agent.addToPlanning(new SelfAction(agent,this,EventType.REPOS)) ;
	}
	
	public void calculGoal() {
		priorite = (int) (100 - agent.getEnergie());
	}
}
