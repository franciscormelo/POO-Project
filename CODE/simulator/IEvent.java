package simulator;

/**
 * Implements an Event. Provides a method to add an event to the PEC and a method to check the time of an event.
 * @author Group 33
 *
 */
interface IEvent {

	/**
	 * Generates and adds an event to the PEC. Receives the PEC (list of events to simulate).
	 * @param pec Pending Event Container (PEC)
	 */
	void simulateEvent(PEC pec);

	/**
	 * Returns the time of an event.
	 * @return time of the event
	 */
	double getTime();

}
