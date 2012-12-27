	package agent.raisonnement ;

import agent.agents.Agent;

// Action dont l'ordre des sous-actions est libre
// Lors de la première tentative d'exécution, les sous-actions sont ordonnées par longueur estimée croissante.
public abstract class ParallelAction extends NodeAction {
	
	public ParallelAction(Agent master, Task parent) {
		super(master,parent) ;
	}
	
	public void unfold() {
		nextActions = subActions ;
		folded = false ;
	}
	
	public int estimateDuration() {
		int total = 0 ;
		for (Action action : subActions) {
			total += action.estimateDuration() ;
		}
		return total ;
	}
	
	// step : définie dans NodeAction
	// terminate : définie dans NodeAction
}
