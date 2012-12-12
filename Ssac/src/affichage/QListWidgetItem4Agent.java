package affichage;

import agent.agents.Agent;
import agent.agents.TypeAgent;

import com.trolltech.qt.gui.QIcon;
import com.trolltech.qt.gui.QListWidgetItem;

public class QListWidgetItem4Agent extends QListWidgetItem {
	
	private Agent	agent;
	
	public QListWidgetItem4Agent(Agent agent) {
		super(new QIcon(), agent.toString());
		QIcon icon;
		if (agent.getType().equals(TypeAgent.Humain)) {
			icon = new QIcon("./ressources/dockUser.png");
		}
		else {
			icon = new QIcon("./ressources/dockWolf.png");
		}
		this.setIcon(icon);
		this.agent = agent;
	}
	
	public Agent getAgent() {
		return agent;
	}
	
	public void setAgent(Agent agent) {
		this.agent = agent;
	}
	
}
