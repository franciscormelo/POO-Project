package simulator;

import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * A PEC contains a priority queue of events to simulate.
 * @author Group 33
 *
 */

public class PEC implements PendingEventContainer {

	/**
	 * Stores the events to simulate
	 */
	private PriorityQueue<Event> events = new PriorityQueue<Event>(11, new EventComp());

	/* (non-Javadoc)
	 * @see simulator.PendingEventContainer#addEvPEC(simulator.Event)
	 */
	@Override
	public void addEvPEC(Event ev) {
		events.add(ev);
	}

	/* (non-Javadoc)
	 * @see simulator.PendingEventContainer#nextEvPEC()
	 */
	@Override
	public Event nextEvPEC() {

		if (!events.isEmpty()) {
			return events.remove();
		} 

		return null;
	}


	/**
	 * Comparator - Compares two events by their time. 
	 * @author group33
	 *
	 */
	class EventComp implements Comparator<Event> {

		/* (non-Javadoc)
		 * @see java.util.Comparator#compare(java.lang.Object, java.lang.Object)
		 */
		@Override
		public int compare(Event o1, Event o2) {
			if (o1.getTime() < o2.getTime()) {
				return -1;
			} else {
				return 1;
			}
		}

	}

	/** 
	 * Returns true if the priority queue is empty.
	 * @return True if the priority is empty, false otherwise
	 */
	public boolean checkEmpty() {
		return events.isEmpty();
	}
}
