package agent.goal;

import affichage.TestQt;
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
	
	public void createPlanning() {
		boolean wtf = true;
		int ind = 50;
		Coordonnee destination;
		while (wtf) {
			if (TestQt.environnement.getTerrain().blocked(agent, agent.getCoordonnee().getAbscisse(),
					agent.getCoordonnee().getOrdonnee() + ind)) {
				ind += 1;
			} else {
				wtf = false;
			}
		}
		destination = new Coordonnee(agent.getCoordonnee().getAbscisse(), agent.getCoordonnee().getOrdonnee() + ind);
		MoveAction action = new MoveAction(agent, this, destination);
		this.agent.addToPlanning(action);
	}
	
	public TypeGoal getTypeGoal() {
		return TypeGoal.Exploration;
	}
	
}
