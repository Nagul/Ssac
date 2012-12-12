package agent.raisonnement;

public interface Task {
		
	public void terminate(Action child, boolean success);
}
