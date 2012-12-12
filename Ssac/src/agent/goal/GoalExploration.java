package agent.goal;

import agent.agents.Agent;

public class GoalExploration extends Goal {

	public GoalExploration(Agent a) {
		super(a);
	}
	
	public void calculGoal() {
		priorite = 20;
	}

	@Override
	public void createPlanning() {
		// TODO Auto-generated method stub
		
	}
	
}
