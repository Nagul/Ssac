package agent.goal;

import agent.agents.Agent;

public class GoalAbris extends Goal {

	public GoalAbris(Agent a) {
		super(a);
	}
	
	public void calculGoal() {
		priorite = 90;
	}

	@Override
	public void createPlanning() {
		// TODO Auto-generated method stub
		
	}
	
	public TypeGoal getTypeGoal() {
		return TypeGoal.Abris;
	}
	
}
