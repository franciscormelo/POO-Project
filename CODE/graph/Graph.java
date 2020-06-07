package graph;

import java.util.ArrayList;

/**
 * Graph implementation using an array list of edges. <p>
 * To create a graph call Graph constructor.
 * @author Group 33
 *
 */
public class Graph implements IGraph{

	/**
	 * Number of nodes in the graph.
	 */
	public int nbnodes; 
	
	/**
	 * Nest node of the graph.
	 */
	public int nestnode;
	
	/**
	 * Sum of weights of all edges in the graph.
	 */
	public int weightSum = 0; 
	
	/**
	 * Array List of the nodes in the graph.
	 */
	public ArrayList<Node> nodes = new ArrayList<Node>();
	
	

	/**
	 * Graph class constructor.<p> Receives the two parameters that define the size and nest node of the graph.
	 * @param nbnodes Number of Nodes in the graph
	 * @param nestnode Nest node of the graph
	 */
	public Graph(int nbnodes, int nestnode) {
		super();
		this.nbnodes = nbnodes;
		this.nestnode = nestnode;
	}


	/* (non-Javadoc)
	 * @see graph.IGraph#addNode(int)
	 */
	public void addNode(int idx) {
		nodes.add(new Node(idx));
	}


	/* (non-Javadoc)
	 * @see graph.IGraph#addEdge(int, int, int, float)
	 */
	public void addEdge(int node, int adjacent, int weight, float pheromones) {
		Edge edge = new Edge(adjacent,weight,pheromones);
		nodes.get(node -1).edges.add(edge);

		Edge aux = new Edge(node,weight,pheromones);
		nodes.get(edge.adjnode-1).edges.add(aux);

	}

	/**
	 * Increments the sum of weights of all edges in the graph.
	 * @param incWeight Weight incrementation
	 */
	public void incrementWeight(int incWeight) {
		weightSum +=incWeight;
	}


	/* (non-Javadoc)
	 * @see graph.IGraph#adjacency(int)
	 */
	public ArrayList<Edge> adjacency(int nodeNumber){

		return nodes.get(nodeNumber-1).edges;
	}


	/* (non-Javadoc)
	 * @see graph.IGraph#weightBetween(int, int)
	 */
	public int weightBetween(int n1, int n2) {

		if (n1 < n2) {
			for (Node node: nodes) {
				if (node.nodeidx == n1) {
					for (Edge e: node.edges) {
						if (e.adjnode == n2) {
							return e.weight;
						}
					}
				}
			}
		} else if (n2 < n1) {
			for (Node node: nodes) {
				if (node.nodeidx == n2) {
					for (Edge e: node.edges) {
						if (e.adjnode == n1) {
							return e.weight;
						}
					}
				}
			}
		}

		return -1;

	}

	/**
	 * Returns the level of pheromones between two adjacent nodes N1 and N2.
	 * @param n1 Node 1
	 * @param n2 Node 2
	 * @return Pheromones level between the two adjacent nodes
	 */
	public float plevelBetween(int n1, int n2) {
		// fazer com exceptions, quando n1 == n2

		if (n1 < n2) {
			for (Node node: nodes) {
				if (node.nodeidx == n1) {
					for (Edge e: node.edges) {
						if (e.adjnode == n2) {
							return e.pheromones;
						}
					}
				}
			}
		} else if (n2 < n1) {
			for (Node node: nodes) {
				if (node.nodeidx == n2) {
					for (Edge e: node.edges) {
						if (e.adjnode == n1) {
							return e.pheromones;
						}
					}
				}
			}
		}

		return -1;

	}

	/**
	 * Increments the level of pheromones between two adjacent nodes N1 and N2.
	 * @param n1 Node N1
	 * @param n2 Node N2
	 * @param incrementPheromones Phermones incrementation
	 */
	public void incrementPheromones(int n1, int n2, double incrementPheromones) {

		for (Node node: nodes) {
			if (node.nodeidx == n1) {
				for (Edge e: node.edges) {
					if (e.adjnode == n2) {
						e.pheromones += incrementPheromones;
					}
				}
			}

		}
		for (Node node: nodes) {
			if (node.nodeidx == n2) {
				for (Edge e: node.edges) {
					if (e.adjnode == n1) {
						e.pheromones += incrementPheromones;
					}
				}
			}
		}


	}

	/**
	 * Decrements the level of pheromones between two adjacent nodes N1 and N2.
	 * @param n1 Node 1
	 * @param n2 Node 2
	 * @param rho Decrementation value
	 */
	public void decrementPheromones(int n1, int n2, double rho) {

		for (Node node: nodes) {
			if (node.nodeidx == n1) {
				for (Edge e: node.edges) {
					if (e.adjnode == n2) {
						if(e.pheromones - rho < 0) {
							e.pheromones = 0;
						} else {
							e.pheromones = e.pheromones - (float)rho;
						}

					}
				}
			}

		}
		for (Node node: nodes) {
			if (node.nodeidx == n2) {
				for (Edge e: node.edges) {
					if (e.adjnode == n1) {
						if(e.pheromones - rho < 0) {
							e.pheromones = 0;
						} else {
							e.pheromones = e.pheromones - (float)rho;
						}
					}
				}
			}
		}


	}
	
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Graph [nbnodes=" + nbnodes + ", nestnode=" + nestnode + ", nodes=" + nodes + "]";
	}
}
