package agent.goal;

import environnement.Coordonnee;
import affichage.TestQt;
import agent.agents.Agent;
import agent.agents.TypeAgent;
import agent.raisonnement.Connaissance;
import agent.raisonnement.MoveAction;

public class GoalPredateur extends Goal {
	
	private TypeAgent predateur;
	
	public GoalPredateur(Agent a, TypeAgent p) {
		super(a);
		predateur = p;
	}
	
	public void calculGoal() {
		priorite = 0;
		for (Coordonnee c : agent.getListConnaissances().keySet()) {
			//agent proche
			if (Math.pow(c.getAbscisse() - agent.getCoordonnee().getAbscisse(), 2)
					+ Math.pow(c.getOrdonnee() - agent.getCoordonnee().getOrdonnee(), 2) < 30) {
				//predateur ?
				for (Agent a : agent.getListConnaissances().get(c).getSouvenir().getAgents()) {
					if (a.getType().equals(predateur)) {
						priorite = 99;
						break;
					}
				}
			}
		}
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
