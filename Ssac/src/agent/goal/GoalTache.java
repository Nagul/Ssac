package agent.goal;

import agent.agents.Agent;

public class GoalTache extends Goal {

	private Role role;
	
	public GoalTache(Agent a, Role r) {
		super(a);
		role = r;
	}
	
	public void calculGoal() {
		priorite = 50;
	}

	@Override
	public void createPlanning() {
		// TODO Auto-generated method stub
		
	}
}
