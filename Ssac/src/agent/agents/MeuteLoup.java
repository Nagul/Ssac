package agent.agents;

import java.util.ArrayList;

import environnement.Coordonnee;

public class MeuteLoup {
	
	private ArrayList<AgentLoup> loups;
	private AgentLoup maleAlpha;
	private AgentLoup femelleAlpha;
	private ArrayList<Coordonnee> territoire;

	public MeuteLoup(AgentLoup ma, AgentLoup fa) {
		loups = new ArrayList<AgentLoup>();
		maleAlpha = ma;
		femelleAlpha = fa;
		territoire = new ArrayList<Coordonnee>();
	}
	
	public void addLoup(AgentLoup loup) {
		loups.add(loup);
	}

	public ArrayList<AgentLoup> getLoups() {
		return loups;
	}

	public AgentLoup getMaleAlpha() {
		return maleAlpha;
	}

	public AgentLoup getFemelleAlpha() {
		return femelleAlpha;
	}
	
	public ArrayList<Coordonnee> getTerritoire() {
		return territoire;
	}
	
	//pour l'instant, choix du MA aléatoire
	public void chooseNewMA() {
		for(AgentLoup l : loups) {
			if(!l.getFemale() && l.getAge()>10) {
				maleAlpha = l;
				return;
			}
		}
		disbandMeute();
	}
	
	//pour l'instant, choix de la FA aléatoire
	public void chooseNewFA() {
		for(AgentLoup l : loups) {
			if(l.getFemale() && l.getAge()>10) {
				femelleAlpha = l;
				return;
			}
		}
		disbandMeute();
	}
	
	public void addTerritoire(Coordonnee co) {
		territoire.add(co);
	}
	
	public void removeTerritoire(Coordonnee co) {
		territoire.remove(co);
	}
	
	//meute plus viable (pas de male/femelle/adultes?)
	private void disbandMeute() {
		for(AgentLoup l : loups) {
			l.setMeute(null);
		}
	}
	
}
