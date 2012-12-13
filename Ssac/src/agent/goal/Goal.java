package agent.goal;

import agent.agents.Agent;
import agent.raisonnement.Action;
import agent.raisonnement.Task;

public abstract class Goal implements Task {
	
	//plus la priorite est HAUTE, plus elle est importante.
	protected int priorite;
	//agent associ� au goal
	protected Agent agent;
	
	public Goal(Agent a) {
		priorite = 0;
		agent = a;
	}

	public int getPriorite() {
		return priorite;
	}

	// Ajouter les actions associ�es au goal dans le planning de l'agent
	public abstract void createPlanning() ;

	public void terminate(Action child, boolean success) {
		agent.updateGoal() ;
	}
	
	//priorit� g�n�tique dans le futur car c'est cooooooooooool
	public abstract void calculGoal();
	
	public abstract TypeGoal getTypeGoal();

}
