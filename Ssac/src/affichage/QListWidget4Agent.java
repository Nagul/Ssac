package affichage;

import java.util.ArrayList;

import agent.agents.Agent;
import agent.agents.AgentHumainImpl;
import agent.agents.TypeAgent;
import agent.goal.Goal;
import agent.goal.Role;
import agent.goal.TypeGoal;

import com.trolltech.qt.gui.QListWidget;
import com.trolltech.qt.gui.QListWidgetItem;

public class QListWidget4Agent extends QListWidget {
	
	private SortType	sortType;
	
	public QListWidget4Agent() {
		super();
		
		sortType = SortType.TypeAgent;
	}
	
	public void setSortType(SortType st) {
		sortType = st;
	}
	
	public void refresh() {
		sort(sortType);
	}
	
	public void sort(SortType st) {
		setSortType(st);
		ArrayList<QListWidgetItem4Agent> listAgent = listItems();
		clearGUIItems();
		
		if (sortType.equals(SortType.TypeAgent)) {
			sortByTypeAgent(listAgent);
		}
		else if (sortType.equals(SortType.Goal)) {
			sortByGoal(listAgent);
		}
		else if (sortType.equals(SortType.Role)) {
			sortByRole(listAgent);
		}
	}
	
	public ArrayList<QListWidgetItem4Agent> listItems() {
		ArrayList<QListWidgetItem4Agent> listAgents = new ArrayList<QListWidgetItem4Agent>();
		for (int i = 0; i < count(); i++) {
			QListWidgetItem item = item(i);
			if (item instanceof QListWidgetItem4Agent) {
				listAgents.add((QListWidgetItem4Agent) item);
			}
		}
		
		return listAgents;
	}
	
	private void clearGUIItems() {
		while (count() > 0) {
			takeItem(0);
		}
	}
	
	private void sortByTypeAgent(ArrayList<QListWidgetItem4Agent> listAgents) {
		for (TypeAgent type : TypeAgent.values()) {
			ArrayList<QListWidgetItem4Agent> agents = new ArrayList<QListWidgetItem4Agent>();
			for (QListWidgetItem4Agent agent : listAgents) {
				if (agent.getAgent().getType().equals(type)) {
					agents.add(agent);
				}
			}
			if (!agents.isEmpty()) {
				addItem(type.name());
				for (QListWidgetItem4Agent agent : agents) {
					addItem(agent);
				}
				addItem("");
			}
		}
	}
	
	private void sortByGoal(ArrayList<QListWidgetItem4Agent> listAgents) {
		for (TypeGoal corpus : TypeGoal.values()) {
			ArrayList<QListWidgetItem4Agent> agents = new ArrayList<QListWidgetItem4Agent>();
			for (QListWidgetItem4Agent agent : listAgents) {
				ArrayList<Goal> goals = (ArrayList<Goal>) agent.getAgent().getGoals();
				Goal currGoal = goals.get(0);
				for (Goal g : goals) {
					if (g.getPriorite() > currGoal.getPriorite()) {
						currGoal = g;
					}
				}
				
				if (currGoal.getTypeGoal().equals(corpus)) {
					agents.add(agent);
				}
			}
			if (!agents.isEmpty()) {
				addItem(corpus.name());
				for (QListWidgetItem4Agent agent : agents) {
					addItem(agent);
				}
				addItem("");
			}
		}
	}
	
	private void sortByRole(ArrayList<QListWidgetItem4Agent> listAgents) {
		for (Role role : Role.values()) {
			ArrayList<QListWidgetItem4Agent> agents = new ArrayList<QListWidgetItem4Agent>();
			for (QListWidgetItem4Agent agent : listAgents) {
				Agent a = agent.getAgent();
				if (a instanceof AgentHumainImpl) {
					if (((AgentHumainImpl) a).getRole().equals(role)) {
						agents.add(agent);
					}
				}
			}
			if (!agents.isEmpty()) {
				addItem(role.name());
				for (QListWidgetItem4Agent agent : agents) {
					addItem(agent);
				}
				addItem("");
			}
		}
		
		ArrayList<QListWidgetItem4Agent> agents = new ArrayList<QListWidgetItem4Agent>();
		for (QListWidgetItem4Agent agent : listAgents) {
			Agent a = agent.getAgent();
			if (!(a instanceof AgentHumainImpl)) {
				agents.add(agent);
			}
		}
		if (!agents.isEmpty()) {
			addItem("Autres");
			for (QListWidgetItem4Agent agent : agents) {
				addItem(agent);
			}
			addItem("");
		}
	}
	
}
