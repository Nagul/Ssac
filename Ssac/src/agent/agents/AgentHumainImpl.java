package agent.agents;

import affichage.TestQt;
import agent.goal.GoalPredateur;
import agent.goal.Role;
import agent.raisonnement.Connaissance;
import environnement.Coordonnee;
import environnement.TypeTerrain;

public class AgentHumainImpl extends AgentImpl implements AgentHumain {

	private Role role;
	
	public AgentHumainImpl(Coordonnee co, int date) {
		super(co, date);
		role = Role.ENFANT;
		goals.add(new GoalPredateur(this, TypeAgent.Loup));
		goals.add(new GoalPredateur(this, TypeAgent.Ours));
	}

	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}
	
	public void voir() {
		int x,y,apx,opy;
		int a = this.getCoordonnee().getAbscisse();
		int o = this.getCoordonnee().getOrdonnee();
		for (x=-5; x<6; x++) {
			for(y=-5; y<6; y++) {
				apx = a + x;
				opy = o + y;
				if((Math.pow(x, 2) + Math.pow(y, 2) <= 30)
						&&(apx>-1)&&(apx<TestQt.environnement.getTerrain().getLongueur())
						&&(opy>-1)&&(opy<TestQt.environnement.getTerrain().getLargeur())
						&&(TestQt.environnement.getTerrain().getCase(apx, opy).getType() != TypeTerrain.Montagne)) {
					if((Math.abs(x)<2)&&(Math.abs(y)<2)
							||(TestQt.environnement.getTerrain().getCase(apx, opy).getType() != TypeTerrain.Foret)) {
						this.addConnaissance(new Coordonnee(apx, opy), 
								new Connaissance(TestQt.environnement.getTerrain().getCase(apx, opy), TestQt.date + 10));
					}
				}
			}
		}
	}
	
}
