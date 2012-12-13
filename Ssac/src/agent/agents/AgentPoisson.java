package agent.agents;

import java.util.ArrayList;

import affichage.TestQt;
import agent.goal.GoalPredateur;
import agent.raisonnement.Connaissance;
import environnement.Coordonnee;
import environnement.TypeObject;
import environnement.TypeSmallObject;
import environnement.TypeTerrain;

public class AgentPoisson extends AgentImpl {

	private AgentPoisson bancLeader;
	
	/**
	 * Constructeur du poisson
	 * 
	 * @param co la coordonnee ou pop le poisson
	 * @param bl le "leader" du banc en question
	 */
	public AgentPoisson(Coordonnee co, int date, AgentPoisson bl) {
		super(co, date);
		bancLeader = bl;
		//Un poisson �a fuit tout
		goals.add(new GoalPredateur(this, TypeAgent.Ours));
		goals.add(new GoalPredateur(this, TypeAgent.Humain));
		goals.add(new GoalPredateur(this, TypeAgent.Mouton));
		goals.add(new GoalPredateur(this, TypeAgent.Loup));
	}
	
	public void voir() {
		int x,y,apx,opy;
		int a = this.getCoordonnee().getAbscisse();
		int o = this.getCoordonnee().getOrdonnee();
		for (x=-5; x<6; x++) {
			for(y=-5; y<6; y++) {
				apx = a + x;
				opy = o + y;
				if((Math.pow(x, 2) + Math.pow(y, 2) <= 9)
						&&(apx>-1)&&(apx<TestQt.environnement.getTerrain().getLongueur())
						&&(opy>-1)&&(opy<TestQt.environnement.getTerrain().getLargeur())
						&&(TestQt.environnement.getTerrain().getCase(apx, opy).getType() == TypeTerrain.EauDeMer
						||TestQt.environnement.getTerrain().getCase(apx, opy).getType() == TypeTerrain.EauDouce)) {
					this.addConnaissance(new Coordonnee(apx, opy), 
							new Connaissance(TestQt.environnement.getTerrain().getCase(apx, opy), TestQt.date + 10));
				}
			}
		}
	}

	@Override
	public Coordonnee find(TypeObject type) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ArrayList<TypeSmallObject> getFoods() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void eat() {
		// TODO Auto-generated method stub
		
	}
	
	public TypeAgent getType() {
		return TypeAgent.Poisson;
	}

}
