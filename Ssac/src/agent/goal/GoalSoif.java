package agent.goal;

import agent.agents.Agent;

public class GoalSoif extends Goal {

	public GoalSoif(Agent a) {
		super(a);
	}
	
	public void calculGoal() {
		priorite = (int) (100 - agent.getSoif());
	}

	@Override
	public void createPlanning() {
		// TODO Auto-generated method stub
		
	}
	
	public TypeGoal getTypeGoal() {
		return TypeGoal.Soif;
	}
}
