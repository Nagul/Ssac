package agent.goal;

import agent.agents.Agent;

public class GoalFaim extends Goal {

	public GoalFaim(Agent a) {
		super(a);
	}
	
	public void calculGoal() {
		priorite = (int) (100 - agent.getFaim());
	}

	@Override
	public void createPlanning() {
		// TODO Auto-generated method stub
		
	}
	
}
