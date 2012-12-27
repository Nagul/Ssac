package affichage;

import agent.agents.Agent;
import agent.agents.AgentHumainImpl;
import agent.agents.AgentLoup;
import agent.agents.AgentMouton;
import agent.agents.AgentOiseau;
import agent.agents.AgentOurs;
import agent.agents.AgentPoisson;
import agent.agents.AgentRongeur;

import com.trolltech.qt.gui.QIcon;
import com.trolltech.qt.gui.QListWidgetItem;

public class QListWidgetItem4Agent extends QListWidgetItem {
	
	private Agent	agent;
	
	public QListWidgetItem4Agent(Agent agent) {
		super(new QIcon(), agent.toString());
		QIcon icon;
		if (agent instanceof AgentHumainImpl) {
			icon = new QIcon("./ressources/Human.png");
		} else if (agent instanceof AgentLoup) {
			icon = new QIcon("./ressources/Wolf.png");
		} else if (agent instanceof AgentMouton) {
			icon = new QIcon("./ressources/Sheep.png");
		} else if (agent instanceof AgentOiseau) {
			icon = new QIcon("./ressources/Fish.png");
		} else if (agent instanceof AgentOurs) {
			icon = new QIcon("./ressources/Bear.png");
		} else if (agent instanceof AgentPoisson) {
			icon = new QIcon("./ressources/Fish.png");
		} else {
			icon = new QIcon("./ressources/Rodent.png");
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
