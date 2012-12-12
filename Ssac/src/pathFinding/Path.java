package pathFinding;

import java.util.ArrayList;

import environnement.Coordonnee;

/**
 * A path determined by some path finding algorithm. A series of coordonnee from
 * the starting location to the target location. This includes a step for the
 * initial location.
 * 
 * @author Kevin Glass
 */
public class Path {
	/** The list of coordonnee building up this path */
	private ArrayList<Coordonnee> coordonnee = new ArrayList<Coordonnee>();
	
	/**
	 * Create an empty path
	 */
	public Path() {
		
	}

	/**
	 * Get the length of the path, i.e. the number of coordonnee
	 * 
	 * @return The number of coordonnee in this path
	 */
	public int getLength() {
		return coordonnee.size();
	}
	
	/**
	 * Get the step at a given index in the path
	 * 
	 * @param index The index of the step to retrieve. Note this should
	 * be >= 0 and < getLength();
	 * @return The step information, the position on the map.
	 */
	public Coordonnee getCoordonnee(int index) {
		return (Coordonnee) coordonnee.get(index);
	}
	
	/**
	 * Get the x coordinate for the step at the given index
	 * 
	 * @param index The index of the step whose x coordinate should be retrieved
	 * @return The x coordinate at the step
	 */
	public int getAbscisse(int index) {
		return getCoordonnee(index).getAbscisse();
	}

	/**
	 * Get the y coordinate for the step at the given index
	 * 
	 * @param index The index of the step whose y coordinate should be retrieved
	 * @return The y coordinate at the step
	 */
	public int getOrdonnee(int index) {
		return getCoordonnee(index).getOrdonnee();
	}
	
	/**
	 * Append a step to the path.  
	 * 
	 * @param x The x coordinate of the new step
	 * @param y The y coordinate of the new step
	 */
	public void appendCoordonnee(int x, int y) {
		coordonnee.add(new Coordonnee(x,y));
	}

	/**
	 * Prepend a step to the path.  
	 * 
	 * @param x The x coordinate of the new step
	 * @param y The y coordinate of the new step
	 */
	public void prependCoordonnee(int x, int y) {
		coordonnee.add(0, new Coordonnee(x, y));
	}
	
	/**
	 * Check if this path contains the given step
	 * 
	 * @param x The x coordinate of the step to check for
	 * @param y The y coordinate of the step to check for
	 * @return True if the path contains the given step
	 */
	public boolean contains(int x, int y) {
		return coordonnee.contains(new Coordonnee(x,y));
	}
	
}

