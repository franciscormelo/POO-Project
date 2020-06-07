package antColony;

import java.util.ArrayList;
import java.util.LinkedList;

import graph.Edge;
import graph.Graph;

/**
 * Ant class. An ant stores a traversed path using a linked list.
 * @author Group 33
 *
 */
class Ant {

	/**
	 * LinkedList to store the traversed path of the ant.
	 */
	LinkedList<Integer> traversedPath = new LinkedList<Integer>();


	/**
	 * Ant Constructor - Adds the nest node of the graph in the ant traversed path linked list. 
	 * Receives the number of the nest node of the graph.
	 * @param nest nest node of the graph
	 */
	public Ant(int nest) {
		traversedPath.add(nest);
	}

	/**
	 * Adds a node in the traversed path linked list of the ant. Receives the number of the node.
	 * @param n Node number
	 */

	public void addNode(int n) {
		traversedPath.add(n);
	}

	/**
	 * Removes all nodes in the traversed path from the end until the received node number appears the first time in the path.
	 * @param n number of the node specified to remove until
	 */
	public void removeUntilNode(int n) {

		int idx = traversedPath.indexOf(n);

		for (int i = traversedPath.size(); i > idx+1; i--) {
			traversedPath.removeLast();
		}

	}

	/**
	 * Receives a list of edges with adjacent nodes, 
	 * checks if the the adjacent nodes are in the traversed path and return a list of non visited nodes adjacent.
	 * @param adjList List of edges with adjacent nodes.
	 * @return Non visited nodes adjacent 
	 */
	public ArrayList<Edge> getJ(ArrayList<Edge> adjList) {

		ArrayList<Edge> setJ = new ArrayList<Edge>();

		for (int i = 0; i < adjList.size(); i++) {
			int n = traversedPath.indexOf(adjList.get(i).adjnode);

			if (n == -1) {
				setJ.add(adjList.get(i));
			}
		}

		return setJ;

	}

	/**
	 * Returns the current node of the ant, that is, the last node of the traversed path.
	 * @return The last visited node of the traversed path.
	 */
	public int currentNode() {
		return traversedPath.getLast();
	}

	/**
	 * Checks if the ant visited all nodes, that is, the size of the traversed path is equal to the number of nodes in the graph.
	 * @param nodeNum Number of nodes in the graph
	 * @return true if the traversed path is equal to the number of nodes, false otherwise
	 */
	public boolean checkFinish(int nodeNum) {
		return traversedPath.size() == nodeNum;
	}

	/**
	 * Checks if the ant completes a Hamiltonian cycle, that is, 
	 * the number of nodes in the graph + 1 (nest node is visited twice)
	 * is equal to number of nodes in the traversed path.
	 * @param nodeNum Number of nodes in the graph
	 * @return true if the ant completes a Hamiltonian cycle
	 */
	public boolean checkHamiltonian(int nodeNum) {
		return traversedPath.size() == nodeNum+1;
	}

	/**
	 * Computes the sum of the weights of all edges in the Hamiltoninan cycle.
	 * @param graph Object of type Graph
	 * @return Weight of the Hamiltonian cycle
	 */
	public int hamiltonianWeight(Graph graph) {

		int weight = 0;

		for (int i = 1; i < traversedPath.size(); i++) {
			weight += graph.weightBetween(traversedPath.get(i-1), traversedPath.get(i));
		}

		return weight;
	}

	/**
	 * Increases the value of pheromones in all edges constituting the Hamiltonian cycle.
	 * @param graph Object of type graph
	 * @param gamma Parameter concerning pheromone level
	 */
	public void layPheromones(Graph graph, double gamma) {

		int hWeight = hamiltonianWeight(graph);
		double incrementPheromones = (gamma * graph.weightSum)/hWeight;


		for (int i = 1; i < traversedPath.size(); i++) {

			graph.incrementPheromones(traversedPath.get(i-1), traversedPath.get(i), incrementPheromones);
		}
	}
}
