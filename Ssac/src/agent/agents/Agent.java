package agent.agents;
import java.util.ArrayList;
import java.util.HashMap;

import agent.goal.Goal;
import agent.raisonnement.Action;
import agent.raisonnement.Connaissance;
import environnement.Case;
import environnement.Coordonnee;
import environnement.TypeObject;
import environnement.TypeSmallObject;


public interface Agent {

	/**
	 * Retourne l'�nergie de l'Agent
	 * 
	 * @return �nergie de l'Agent
	 */
	public double getEnergie();
	
	/**
	 * Permet de modifier la barre d'�nergie de l'Agent suite � une action ou au passage du temps.
	 * 
	 * @param mod le changement en Energie
	 */
	public void modEnergie(double mod);
	
	/**
	 * Retourne la faim de l'Agent
	 * 
	 * @return Faim de l'Agent
	 */
	public double getFaim();
	
	/**
	 * Permet de modifier la barre de faim de l'Agent suite � une action ou au passage du temps.
	 * 
	 * @param mod le changement en Faim
	 */
	public void modFaim(double mod);
	
	/**
	 * Retourne la soif de l'Agent
	 * 
	 * @return Soif de l'Agent
	 */
	public double getSoif();
	
	/**
	 * Permet de modifier la barre de soif de l'Agent suite � une action ou au passage du temps.
	 * 
	 * @param mod le changement en Soif
	 */
	public void modSoif(double mod);
	
	/**
	 * Retourne l'age de l'Agent
	 * 
	 * @return l'age de l'Agent
	 */
	public double getAge();
	
	/**
	 * Retourne les goals de l'Agent
	 * 
	 * @return goals de l'Agent
	 */
	public ArrayList<Goal> getGoals();
	
	/**
	 * Ajoute un goal � la liste de goal de l'agent
	 * 
	 * @param goal le goal � ajouter
	 */
	public void addGoal(Goal goal);
	
	/**
	 * Supprime un goal � la liste de goal de l'agent
	 * 
	 * @param goal le goal � supprimer
	 */
	public void removeGoal(Goal goal);
	
	/**
	 * Update les priorit�s des goals
	 */
	public void updateGoal();
	
	/**
	 * Retourne les connaissances de l'Agent
	 * 
	 * @return connaissances de l'Agent
	 */
	public HashMap<Coordonnee, Connaissance> getListConnaissances();
	
	/**
	 * ajoute une connaissance � la liste de connaissance de l'agent
	 * 
	 * @param co la connaissance � ajouter
	 */
	public void addConnaissance(Coordonnee cor, Connaissance con);
	
	/**
	 * Retourne le Planning de l'Agent
	 * 
	 * @return planning de l'Agent 
	 */
	public ArrayList<Action> getPlanning();
	
	public void clearPlanning() ;
	public void addToPlanning(Action action) ;
	/**
	 * Retourne le status de l'Agent : mort ou vivant
	 * 
	 * @return status de l'Agent
	 */
	public boolean isAlive();
	
	/**
	 * Tue l'agent
	 */
	public void kill();
	
	/**
	 * Retourne les coordonnees de l'Agent
	 * 
	 * @return coordonnees de l'Agent
	 */
	public Coordonnee getCoordonnee();
	public void moveTo(Coordonnee newPosition) ;
	
	public Case getCase() ;
	public Coordonnee find(TypeSmallObject target) ;
	public Coordonnee find(TypeObject type);
	/**
	 * Retourne le sexe de l'Agent
	 * 
	 * @return true si sexe f�minin, false sinon.
	 */
	public boolean getFemale();
	
	public ArrayList<TypeSmallObject> getFoods() ;
	public void eat() ;
	/**
	 * Teste si l'agent transporte un ou plusieurs types d'objets
	 * 
	 * @return r�sultat du test
	 */
	public boolean carries(TypeSmallObject type);
	public boolean carries(ArrayList<TypeSmallObject> types) ;
	
	public void addWant(TypeSmallObject type) ;
	public void getWanted() ;
	public void dropUnwanted() ;
	
	/**
	 * D�place l'horloge interne de l'agent
	 * Celui-ci est bloqu� jusqu'� ce que l'horloge ma�tre rattrape l'horloge interne
	 */
	public void delay(int duration);
	
	/**
	 * update des connaissances de l'Agent en fonction de ce qu'il voit
	 */
	public void voir();
	
	public TypeAgent getType();

}
