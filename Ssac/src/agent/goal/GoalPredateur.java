package agent.goal;

import agent.agents.Agent;
import agent.agents.TypeAgent;

public class GoalPredateur extends Goal {
	
	private TypeAgent predateur;
	
	public GoalPredateur(Agent a, TypeAgent p) {
		super(a);
		predateur = p;
	}
	
	public void calculGoal() {
		priorite = 99;
	}

	@Override
	public void createPlanning() {
		// TODO Auto-generated method stub
		
	}
	
}
