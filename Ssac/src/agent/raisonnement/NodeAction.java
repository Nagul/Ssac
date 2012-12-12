package agent.raisonnement ;

import java.util.ArrayList;

import agent.agents.Agent;

// Action comportant des sous-actions
public abstract class NodeAction extends Action {
	
	protected ArrayList<Action> nextActions ;
	private Action currentAction ;
	protected boolean folded ;
	protected ArrayList<Action> subActions;
	
	public NodeAction(Agent master, Task parent) {
		super(master,parent) ;
		subActions = new ArrayList<Action>() ;
		nextActions = new ArrayList<Action>() ;
		currentAction = null ;
	}
	
	public void step() {
		if(folded) {
			unfold() ;
		}
		currentAction.step() ;
	}
	
	public abstract void unfold() ;
	
	public void terminate(Action child, boolean success) {
		if(success) {
			if (nextActions.isEmpty()) {
				parent.terminate(this, true) ;
			} else {
				currentAction = nextActions.remove(0) ;
			}
		} else {
			parent.terminate(this, false) ;
		}
	}
}
