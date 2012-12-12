package agent.agents;

import agent.goal.Role;

public interface AgentHumain extends Agent {

	/**
	 * Retourne le rôle de l'Agent
	 * 
	 * @return rôle de l'Agent
	 */
	public Role getRole();
	
	/**
	 * Retourne l'énergie de l'Agent
	 * 
	 * @param role le nouveau role assigne à l'Agent
	 */
	public void setRole(Role role);
	
}
