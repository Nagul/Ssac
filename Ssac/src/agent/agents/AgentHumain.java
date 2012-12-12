package agent.agents;

import agent.goal.Role;

public interface AgentHumain extends Agent {

	/**
	 * Retourne le r�le de l'Agent
	 * 
	 * @return r�le de l'Agent
	 */
	public Role getRole();
	
	/**
	 * Retourne l'�nergie de l'Agent
	 * 
	 * @param role le nouveau role assigne � l'Agent
	 */
	public void setRole(Role role);
	
}
