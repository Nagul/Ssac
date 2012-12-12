package
agent.goal;

import agent.agents.Agent;

public class GoalPartenaire extends Goal {
	
	public GoalPartenaire(Agent a) {
		super(a);
	}
	
	public void calculGoal() {
		priorite = 60;
	}

	@Override
	public void createPlanning() {
		// TODO Auto-generated method stub
		
	}
	
}
