package graph;

import java.util.ArrayList;

/**
 * A node class is defined by a node number and a list of edges(adjacent nodes).
 * @author Group 33
 *
 */
public class Node {

	/**
	 * Number of the Node
	 */
	public int nodeidx = 0;

	/**
	 * Array List containing the edges of the node.
	 */
	 ArrayList<Edge> edges = new ArrayList<Edge>()  ;

	/**
	 * Node class constructor. <p> Receives the node number.
	 * @param nodeidx Node Number
	 */
	public Node(int nodeidx) {
		super();
		this.nodeidx = nodeidx;
	}


	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Node [nodeidx=" + nodeidx + ", edges=" + edges + "]" + "\n";
	}
}

