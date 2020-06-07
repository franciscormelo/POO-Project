
package antColony;


import simulator.Event;
import simulator.PEC;

/**
 * Class related to the observation event.
 * @author Group 33
 *
 */
class Observation extends Event {
	
	/**
	 * Class AntSimulation
	 */
	AntSimulation antSim;

	/**
	 * Observation Constructor - Receives the time of the event and an object of type AntSimulation
	 * @param time Time of the event
	 * @param antSim Object of type AntSimulation
	 */
	public Observation(double time, AntSimulation antSim) {
		super(time);
		this.antSim = antSim;

	}

	/* (non-Javadoc)
	 * @see simulator.IEvent#simulateEvent(simulator.PEC)
	 */
	@Override
	public void simulateEvent(PEC pec) {

		antSim.number++;
		System.out.println(antSim);
	}

	/* (non-Javadoc)
	 * @see simulator.IEvent#getTime()
	 */
	@Override
	public double getTime() {
		return time;
	}

}
