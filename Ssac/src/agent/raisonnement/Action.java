package agent.raisonnement;

import agent.agents.Agent;

// Action g�n�rique
public abstract class Action implements Task {
	
	// Ex�cutant de l'action
	protected Agent master ;
	
	// Goal/Action parent
	protected Task parent ;
	
	public Action(Agent master, Task parent) {
		this.master = master ;
		this.parent = parent ;
	}

	public abstract boolean useful() ;
	
	public abstract void step() ;
	
	public abstract int estimateDuration() ;
}
