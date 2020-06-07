package simulator;

/**
 * Simulator is an abstract class with the fields that define a simulation.
 * @author Group 33
 *
 */

public abstract class Simulator {

	/**
	 * Duration of the simulation
	 */
	protected double simulationTime;
	
	/**
	 * Current time of the simulation
	 */
	protected double currentTime;

	/**
	 * Pending Event Container - Priority queue with events
	 */
	protected PEC pec;
	
	/**
	 * Current event in the simulation
	 */
	protected Event currentEv;

}
