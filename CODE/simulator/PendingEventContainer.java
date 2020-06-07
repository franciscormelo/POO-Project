package simulator;

/**
 * Implements a PEC using a priority queue. Provides a method to add an event to the PEC and a method to remove and return the event with the lowest time in the PEC.
 * @author Group 33
 *
 */

interface PendingEventContainer {

	/**
	 * Adds an event to the PEC.
	 * @param ev event to be added to the PEC
	 */
	void addEvPEC(Event ev);

	/**
	 * Removes and returns the event with the lowest time in the PEC if the PEC isn't empty.
	 * @return Event with the lowest time in the PEC
	 */
	Event nextEvPEC();

}
