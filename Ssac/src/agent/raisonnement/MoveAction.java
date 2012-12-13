package agent.raisonnement;

import pathFinding.AStarPathFinder;
import pathFinding.Path;
import affichage.TestQt;
import agent.agents.Agent;
import agent.goal.GoalExploration;
import environnement.Coordonnee;
import environnement.Terrain;
import environnement.TypeObject;
import environnement.TypeSmallObject;
import environnement.TypeTerrain;

public class MoveAction extends Action {
	
	private Coordonnee	target;
	private Path		path;
	private int			index;
	
	// On peut fournir une coordonnéee cible...
	public MoveAction(Agent master, Task parent, Coordonnee target) {
		super(master, parent);
		this.target = target;
		this.path = null;
		index = 1;
	}
	
	//	... ou un type d'objet à atteindre
	public MoveAction(Agent master, Task parent, TypeObject type) {
		this(master, parent, master.find(type));
	}
	
	public MoveAction(Agent master, Task parent, TypeSmallObject type) {
		this(master, parent, master.find(type));
	}
	
	public boolean useful() {
		checkPath();
		return (path.getLength() == 1);
	}
	
	public void step() {
		checkPath();
		if (index < path.getLength()) {
			// check de si je vais entrer dans un truc pas de la terre ou pas
			Coordonnee dest = path.getCoordonnee(index);
			// terre : ok
			if (TestQt.environnement.getTerrain().getCase(dest).getType().equals(TypeTerrain.Terre)) {
				master.moveTo(dest);
				master.delay(1);
			}
			// pas terre et pas la destination (sinon on va boucler sur cette �tape) : on regenere le path
			else if (index <= path.getLength() - 1) {
				AStarPathFinder aStar = new AStarPathFinder(TestQt.environnement.getTerrain(), Integer.MAX_VALUE, false);
				Path newPath = aStar.findPath(master, master.getCoordonnee().getAbscisse(), master.getCoordonnee().getOrdonnee(), target.getAbscisse(),
						target.getOrdonnee());
				
				index = 1;
				dest = path.getCoordonnee(index);
				path = newPath;
				master.moveTo(dest);
				master.delay(1);
			}
		}
		index += 1;
		if (index >= path.getLength()) {
			parent.terminate(this, true);
		}
	}
	
	public int estimateDuration() {
		// Heuristique : 1 par déplacement
		return path.getLength() - 1;
	}
	
	private void checkPath() {
		// Generate the path !
		if (path == null) {
			// explo : blind mode, tout schuss
			if (this.parent instanceof GoalExploration) {
				AStarPathFinder aStar = new AStarPathFinder(new Terrain(true), Integer.MAX_VALUE, false);
				Path newPath = aStar.findPath(master, master.getCoordonnee().getAbscisse(), master.getCoordonnee().getOrdonnee(), target.getAbscisse(),
						target.getOrdonnee());
				path = newPath;
			}
			// sinon : cheat mode, carte static totale
			else {
				AStarPathFinder aStar = new AStarPathFinder(TestQt.environnement.getTerrain(), Integer.MAX_VALUE, false);
				Path newPath = aStar.findPath(master, master.getCoordonnee().getAbscisse(), master.getCoordonnee().getOrdonnee(), target.getAbscisse(),
						target.getOrdonnee());
				path = newPath;
			}
		}
	}
	
	@Override
	public void terminate(Action child, boolean success) {
		// TODO Auto-generated method stub
		
	}
}
