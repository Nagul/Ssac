package agent.raisonnement;

import agent.agents.Agent;
import environnement.EnvObject;

public class WorkAction extends Action {
	
	private EnvObject station ;
	private EventType type ;
	
	public WorkAction(Agent master, Task parent, EnvObject station, EventType type) {
		super(master,parent) ;
		this.station = station ;
		this.type = type ;
	}
	
	public boolean useful() {
		return true ;
	}
	
	public void step() {
		if(station.available(type) && master.carries(station.getTools(type))) {
			boolean isOver = station.use(master,type) ;
			if(isOver) {
				parent.terminate(this,true) ;
			}
		} else {
			parent.terminate(this,false) ;
		}
	}
	
	public int estimateDuration() {
		return station.estimateDuration(type) ;
	}

	@Override
	public void terminate(Action child, boolean success) {
		// TODO Auto-generated method stub
		
	}
}
