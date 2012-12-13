package agent.agents;

import java.util.ArrayList;

import affichage.TestQt;
import agent.goal.GoalPredateur;
import environnement.Coordonnee;
import environnement.TypeObject;
import environnement.TypeSmallObject;
import agent.raisonnement.Connaissance;

public class AgentOiseau extends AgentImpl {

	public AgentOiseau(Coordonnee co, int date) {
		super(co, date);
		goals.add(new GoalPredateur(this, TypeAgent.Loup));
		goals.add(new GoalPredateur(this, TypeAgent.Humain));
	}

	/*
	 * oiseau : disons 5 cases de vue de rayon, non g�n� par le terrain
	 */
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
						&&(opy>-1)&&(opy<TestQt.environnement.getTerrain().getLargeur()))
					this.addConnaissance(new Coordonnee(apx, opy), 
							new Connaissance(TestQt.environnement.getTerrain().getCase(apx, opy), TestQt.date + 10));
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
		return TypeAgent.Oiseau;
	}

}
