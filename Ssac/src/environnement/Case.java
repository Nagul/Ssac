package environnement;
import java.util.ArrayList;
import agent.agents.Agent;

public class Case {

	private TypeTerrain type;
	private EnvObject obj; //un objet par case maximum
	private ArrayList<TypeSmallObject> stuff ; // N petits objets par case
	private ArrayList<Agent> agents; //N agents par case
	
	public Case(TypeTerrain t, EnvObject o) {
		type = t;
		obj = o;
		agents = new ArrayList<Agent>();
	}
	
	public TypeTerrain getType() {
		return type;
	}

	public void setType(TypeTerrain type) {
		this.type = type;
	}

	public EnvObject getObj() {
		return obj;
	}

	public void setObj(EnvObject obj) {
		this.obj = obj;
	}

	public void removeObj() {
		this.obj = null;
	}
	
	public ArrayList<Agent> getAgents() {
		return agents;
	}

	public void addAgents(Agent agents) {
		this.agents.add(agents);
	}

	public void removeAgents(Agent agents) {
		this.agents.remove(agents);
	}
	
	public void addStuff(TypeSmallObject type) {
		this.stuff.add(type) ;
	}
	
	public void removeStuff(TypeSmallObject type) {
		this.stuff.remove(type) ;
	}
	
	public boolean contains(TypeSmallObject type) {
		return this.stuff.contains(type) ;
	}
}
