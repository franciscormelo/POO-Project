package graph;

import java.util.ArrayList;

/**
 * Implements a graph using an array list of nodes and edges. Provides a method that adds a node,one that adds an edge,one that returns the edges of a given node and one that returns the weight of an edge. 
 * @author Group33
 *
 */
public interface IGraph {

	/**
	 * Adds and creates a Node in an ArrayList of Nodes.<p>
	 * Receives the number of the node.
	 * @param idx Node number
	 */
	void addNode(int idx);
	
	/**
	 * Adds an edge in the graph.<p>
	 * This addition is made in an array list of Edges contained in the node and in the adjacent node objects.
	 * @param node Node number
	 * @param adjacent Adjacent node number
	 * @param weight Edge Weight 
	 * @param pheromones Level of pheromones in the edge 
	 */
	void addEdge(int node, int adjacent, int weight, float pheromones);



	/**
	 * Returns the edges of a node given a node number.
	 * @param nodeNumber Number of the node
	 * @return  List of edges of the node
	 */
	ArrayList<Edge> adjacency(int nodeNumber);


	/**
	 * Returns the weight of an edge between two adjacent nodes.
	 * @param n1 Adjacent node 1
	 * @param n2 Adjacent node 2
	 * @return Weight between two adjacent nodes
	 */
	int weightBetween(int n1, int n2);
}
