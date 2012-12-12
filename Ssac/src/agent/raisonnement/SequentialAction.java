package agent.raisonnement ;

import java.util.Iterator;

import agent.agents.Agent;

// Action dont l'ordre des sous-actions est fixe
public abstract class SequentialAction extends NodeAction {
	
	public SequentialAction(Agent master, Task parent) {
		super(master,parent) ;
	}
	
	public void unfold() {
		nextActions = subActions ;
		folded = false ;
	}
	
	// Temps estimé par la durée de la première action non-instantanée de la séquence
	public int estimateDuration() {
		Iterator<Action> iter = subActions.iterator() ;
		while (iter.hasNext()) {
			int d = iter.next().estimateDuration() ;
			if (d > 0) { return d ;}
		}
		return 0 ;
	}
	
	// step : définie dans NodeAction
	// terminate : définie dans NodeAction
}
