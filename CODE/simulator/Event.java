package simulator;

/**
 * An event is an abstract class defined by the time it occurs.
 * @author Group 33
 *
 */

public abstract class Event implements IEvent {

	/**
	 * Time of the event
	 */
	protected double time;


	/** 
	 * Event constructor. Receives the time of the event.
	 * @param time time of the event
	 */
	public Event(double time) {
		this.time = time;
	}

}
