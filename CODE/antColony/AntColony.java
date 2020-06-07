package antColony;

import java.util.ArrayList;

/**
 * Class related to an ant colony. An ant colony is implemented using an array list of ants.
 * @author Group 33
 *
 */
public class AntColony {
	
	/**
	 * Array list to store the colony of ants.
	 */
	ArrayList<Ant> colony = new ArrayList<>();

	/**
	 * Adds an ant to an array list of ants (colony of ants).
	 * @param ant Ant object
	 */
	public void addAnt(Ant ant) {
		colony.add(ant);
	}

}
