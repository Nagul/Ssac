package affichage;

import java.util.ArrayList;

import agent.agents.Agent;
import agent.agents.AgentHumainImpl;
import agent.agents.TypeAgent;
import agent.goal.Goal;
import agent.goal.Role;

import com.trolltech.qt.gui.QFrame;
import com.trolltech.qt.gui.QGridLayout;
import com.trolltech.qt.gui.QLabel;

public class QFrame4Details extends QFrame {
	
	public QFrame4Details(Agent a) {
		super();
		
		QGridLayout layout = new QGridLayout();
		
		ArrayList<QLabel> labels = new ArrayList<QLabel>();
		labels.add(new QLabel("Energie"));
		labels.add(new QLabel("Faim"));
		labels.add(new QLabel("Soif"));
		labels.add(new QLabel("Age"));
		labels.add(new QLabel("Vivant"));
		labels.add(new QLabel("Type"));
		labels.add(new QLabel("Sexe"));
		labels.add(new QLabel("Objectifs"));
//		labels.add(new QLabel("Connaissances"));
//		labels.add(new QLabel("Planning"));
		if (a instanceof AgentHumainImpl) {
			labels.add(new QLabel("Role"));
		}
		
		double energie = a.getEnergie();
		double faim = a.getFaim();
		double soif = a.getSoif();
		double age = a.getAge();
		boolean vivant = a.isAlive();
		TypeAgent type = a.getType();
		String sexe = type.equals(TypeAgent.Humain) ? (a.getFemale() ? "Femme" : "Homme") : (a.getFemale() ? "Femelle" : "Mâle");
		Role role = null;
		if (a instanceof AgentHumainImpl) {
			role = ((AgentHumainImpl) a).getRole();
		}
		ArrayList<Goal> goals = (ArrayList<Goal>) a.getGoals();
		Goal currGoal = goals.get(0);
		
		ArrayList<QLabel> details = new ArrayList<QLabel>();
		details.add(new QLabel("" + energie));
		details.add(new QLabel("" + faim));
		details.add(new QLabel("" + soif));
		details.add(new QLabel("" + age));
		details.add(new QLabel(((Boolean) vivant).toString()));
		details.add(new QLabel(type.name()));
		details.add(new QLabel(sexe));
		details.add(new QLabel(currGoal.getTypeGoal().name()));
//		details.add(new QLabel("undef"));
//		details.add(new QLabel("undef"));
		if (a instanceof AgentHumainImpl) {
			details.add(new QLabel(role.name()));
		}
		
		for (int i = 0; i < labels.size(); i++) {
			layout.addWidget(labels.get(i), i, 0);
		}
		for (int i = 0; i < details.size(); i++) {
			layout.addWidget(details.get(i), i, 1);
		}
		
		setLayout(layout);
	}
	
}
