package agent.agents;
import java.util.ArrayList;
import java.util.HashMap;

import affichage.TestQt;
import agent.goal.Goal;
import agent.goal.GoalFaim;
import agent.goal.GoalRepos;
import agent.goal.GoalSoif;
import agent.raisonnement.Action;
import agent.raisonnement.Connaissance;
import environnement.Case;
import environnement.Coordonnee;
import environnement.TypeSmallObject;

public abstract class AgentImpl implements Agent {

	private double energie;
	private double faim;
	private double soif;
	private double clock;
	protected ArrayList<Goal> goals;
	private HashMap<Coordonnee, Connaissance> listConn;
	private ArrayList<Action> planning;
	private boolean isAlive;
	private boolean female;

	private Coordonnee coordonnee;

	private ArrayList<TypeSmallObject> inventory ;
	private ArrayList<TypeSmallObject> wantedThings ;
	private ArrayList<TypeSmallObject> edibleFoods;
	private int birth;
	
	
	//TOUJOURS RAJOUTER UN g.calculGoal(); QUAND ON CREE UN AGENT
	/**
	 * Les Agents naissent sans connaissances du monde. <br>
	 * Full �nergie/faim/soif par commodit�. <br>
	 * Les goals d�pendent du type d'agent.
	 */
	public AgentImpl(Coordonnee co, int startDate) {
		energie = 100;
		faim = 100;
		soif = 100;
		birth = startDate;
		clock = startDate;
		goals = new ArrayList<Goal>();
		this.goals.add(new GoalFaim(this));
		this.goals.add(new GoalSoif(this));
		this.goals.add(new GoalRepos(this));
		listConn = new HashMap<Coordonnee, Connaissance>();
		planning = new ArrayList<Action>();
		isAlive = true;
		female = affichage.TestQt.rand.nextBoolean();
		coordonnee = co;
		edibleFoods = new ArrayList<TypeSmallObject>() ;
		inventory = new ArrayList<TypeSmallObject>();
		wantedThings = new ArrayList<TypeSmallObject>();
	}

	public double getEnergie() {
		return energie;
	}

	public void modEnergie(double mod) {
		this.energie += mod;
	}

	public double getFaim() {
		return faim;
	}

	public void modFaim(double mod) {
		this.faim += mod;
	}

	public double getSoif() {
		return soif;
	}

	public void modSoif(double mod) {
		this.soif += mod;
	}

	public double getAge() {
		return clock-birth;
	}

	public ArrayList<Goal> getGoals() {
		return goals;
	}

	public void addGoal(Goal goal) {
		this.goals.add(goal);
	}

	public void removeGoal(Goal goal) {
		this.goals.remove(goal);
	}

	public void updateGoal() {
		for(Goal g : goals) {
			g.calculGoal();
		}
	}

	public HashMap<Coordonnee, Connaissance> getListConnaissances() {
		return listConn;
	}

	public void addConnaissance(Coordonnee cor, Connaissance con) {
		listConn.put(cor, con);
	}

	public ArrayList<Action> getPlanning() {
		return planning;
	}
	
	public void clearPlanning() {
		// empty the planning !
	}
	public void addToPlanning(Action action) {
		// add action to planning !
	}

	public boolean isAlive() {
		return isAlive;
	}

	public void kill() {
		this.isAlive = false;
	}

	public Coordonnee getCoordonnee() {
		return coordonnee;
	}

	public void moveTo(Coordonnee newPosition) {
		coordonnee = newPosition ;
	}
		
	public Case getCase() {
		return TestQt.environnement.getTerrain().getCase(this.getCoordonnee()) ;
	}
	
	public Coordonnee find(TypeSmallObject target) {
		return null;
		
	}
	
	public boolean getFemale() {
		return female;
	}
	
	public boolean carries(TypeSmallObject type) {
		return inventory.contains(type) ;
	}
	
	public boolean carries(ArrayList<TypeSmallObject> types) {
		boolean ok = true ;
		for(TypeSmallObject t : types) {
			ok = (ok && this.carries(t)) ;
		}
		return ok ;
	}
	
	
	public void addWant(TypeSmallObject type) {
		wantedThings.add(type) ;
	}
	
	public void getWanted() {
		Case location = getCase() ;
		for(TypeSmallObject type : wantedThings) {
			if(location.contains(type)) {
				inventory.add(type) ;
				wantedThings.remove(type) ;
				location.removeStuff(type) ;
			}
		}
	}
	
	public void dropUnwanted() {
		Case location = getCase() ;
		for(TypeSmallObject type : inventory) {
			if(!wantedThings.contains(type)) {
				inventory.remove(type) ;
				location.addStuff(type) ;
			}
		}
	}
	
	public void delay(int duration) {
		clock += duration ;
	}
	
	//doit etre override par m�thodes de sous-classe
	public abstract void voir();
	
	public abstract TypeAgent getType();

}
