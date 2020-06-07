package antColony;

/**
 * Class with attributes related to the data of the simulation, evaporation and move events.
 * @author Group 33
 *
 */

public class Data {
	//Simulation Data
	/**
	 * Final instant of the simulation
	 */
	double finalinst=0;
	
	/**
	 * Ant colony size
	 */
	int antcolsize=0;
	
	/**
	 * Pheromone level parameter
	 */
	double plevel=0;

	//Evaporation Data
	/**
	 * Parameter concerning the pheromone evaporation event
	 */
	double eta=0;
	
	/**
	 * Parameter concerning the pheromone evaporation event
	 */
	double rho=0;

	//Move Data
	/**
	 *  Parameter concerning the ant move event
	 */
	float alpha=0;
	
	/**
	 * Parameter concerning the ant move event
	 */
	float beta=0;
	
	/**
	 * Parameter concerning the ant move event
	 */
	float delta=0;

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Data [finalinst=" + finalinst + ", antcolsize=" + antcolsize + ", plevel=" + plevel + ", eta=" + eta
				+ ", rho=" + rho + ", alpha=" + alpha + ", beta=" + beta + ", delta=" + delta + "]";
	}


}
