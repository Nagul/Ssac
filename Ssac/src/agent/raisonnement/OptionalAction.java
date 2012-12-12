package agent.raisonnement ;

import agent.agents.Agent;

public abstract class OptionalAction extends Action {
		
	private Action option ;
	
	public OptionalAction(Agent master, Task parent) {
		super(master,parent) ;
		this.option = null ;
	}
	
	public void setOption(Action a) {
		if(option == null) {
			option = a ;
		}
	}
	
	public boolean useful() {
		return option.useful() ;
	}
	
	public void step() {
		if(option.useful()) {
			option.step() ;
		} else {
			parent.terminate(this,true) ;
		}
	}
	
	public int estimateDuration() {
		if(option.useful()) {
			return option.estimateDuration() ;
		} else {
			return 0 ;
		}
	}
	
	public void terminate(Action child, boolean success) {
		parent.terminate(this,success) ;
	}
}
