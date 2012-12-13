package agent.goal;

import agent.agents.Agent;
import agent.raisonnement.MoveAction;
import environnement.Coordonnee;

public class GoalExploration extends Goal {
	
	public GoalExploration(Agent a) {
		super(a);
	}
	
	public void calculGoal() {
		priorite = 20;
	}
	
	@Override
	public void createPlanning() {
		Goal task = agent.getGoals().get(0);
		Coordonnee destination = new Coordonnee(agent.getCoordonnee().getAbscisse(), agent.getCoordonnee().getOrdonnee() + 100);
		MoveAction action = new MoveAction(agent, task, destination);
		this.agent.addToPlanning(action);
	}
	
	public TypeGoal getTypeGoal() {
		return TypeGoal.Exploration;
	}
	
}
