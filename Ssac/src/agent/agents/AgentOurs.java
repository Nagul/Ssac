package agent.agents;

import java.util.ArrayList;

import affichage.TestQt;
import agent.raisonnement.Connaissance;

import environnement.Coordonnee;
import environnement.TypeObject;
import environnement.TypeSmallObject;
import environnement.TypeTerrain;

public class AgentOurs extends AgentImpl {

	private ArrayList<Coordonnee> territoire;
	
	/**
	 * Constructeur pour la cr�ation des Ours originaux
	 * 
	 * @param co la coordonnee o� faire pop l'ours.
	 */
	public AgentOurs(Coordonnee co, int date) {
		super(co, date);
		territoire = new ArrayList<Coordonnee>();
	}
	
	/**
	 * Constructeur pour la cr�ation de nouveaux ours lors de la simulation
	 * 
	 * @param mere la m�re de l'ours.
	 */
	public AgentOurs(AgentOurs mere, int date) {
		super(mere.getCoordonnee(), date);
		territoire = mere.getTerritoire();
	}
	
	public ArrayList<Coordonnee> getTerritoire() {
		return territoire;
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
		return TypeAgent.Ours;
	}
	
}
