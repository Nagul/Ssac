package agent.agents;

import java.util.ArrayList;

import affichage.TestQt;
import agent.goal.GoalPredateur;
import agent.raisonnement.Connaissance;
import environnement.Coordonnee;
import environnement.TypeObject;
import environnement.TypeSmallObject;
import environnement.TypeTerrain;

public class AgentMouton extends AgentImpl {

	public AgentMouton(Coordonnee co, int date) {
		super(co, date);
		goals.add(new GoalPredateur(this, TypeAgent.Loup));
		goals.add(new GoalPredateur(this, TypeAgent.Ours));
		goals.add(new GoalPredateur(this, TypeAgent.Humain));
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
		return TypeAgent.Mouton;
	}
}
