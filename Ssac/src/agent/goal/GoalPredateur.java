package agent.goal;

import environnement.Coordonnee;
import affichage.TestQt;
import agent.agents.Agent;
import agent.agents.TypeAgent;
import agent.raisonnement.MoveAction;

public class GoalPredateur extends Goal {
	
	private TypeAgent predateur;
	
	public GoalPredateur(Agent a, TypeAgent p) {
		super(a);
		predateur = p;
	}
	
	public void calculGoal() {
		priorite = 99;
	}

	public void createPlanning() {
		//panique : selectionne une case au hasard à coté de lui et tente de s'y diriger.
		int x = TestQt.rand.nextInt(3) - 1;
		int y = TestQt.rand.nextInt(3) - 1;
		Coordonnee destination = new Coordonnee(agent.getCoordonnee().getAbscisse() + x,
				agent.getCoordonnee().getOrdonnee() + y);
		MoveAction action = new MoveAction(agent, this, destination);
		if(action.useful()) {
			this.agent.addToPlanning(action);	
		}
	}
	
	public TypeGoal getTypeGoal() {
		return TypeGoal.Predateur;
	}
}
