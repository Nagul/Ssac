package agent.raisonnement;

import pathFinding.Path;
import agent.agents.Agent;
import environnement.Coordonnee;
import environnement.TypeObject;
import environnement.TypeSmallObject;

public class MoveAction extends Action {
	
	private Coordonnee target ;
	private Path path ;
	private int index ;
	
	// On peut fournir une coordonnéee cible...
	public MoveAction(Agent master, Task parent, Coordonnee target) {
		super(master,parent) ;
		this.target = target ;
		this.path = null ;
		index = 1 ;
	}
	
	//	... ou un type d'objet à atteindre
	public MoveAction(Agent master, Task parent, TypeObject type) {
		this(master, parent, master.find(type)) ;
	}
	
	public MoveAction(Agent master, Task parent, TypeSmallObject type) {
		this(master, parent, master.find(type)) ;
	}
	
	public boolean useful() {
		checkPath() ;
		return (path.getLength() == 1) ;
	}
	
	public void step() {
		checkPath() ;
		if(index < path.getLength()) {
			master.moveTo(path.getCoordonnee(index)) ;
			master.delay(1) ;
		}
		index += 1 ;
		if(index >= path.getLength()) {
			parent.terminate(this,true) ;
		}
	}
	
	public int estimateDuration() {
		// Heuristique : 1 par déplacement
		return path.getLength() - 1;
	}
	
	private void checkPath() {
		if(path == null) {
			// Generate the path !
		}
	}

	@Override
	public void terminate(Action child, boolean success) {
		// TODO Auto-generated method stub
		
	}
}
