package graph;

/**
 * Edge Class. An edge is defined by the adjacent node, the weight and the level oh pheromones between the two nodes.
 * @author Group 33
 *
 */
public class Edge {
	/**
	 * Adjacent node
	 */
	public int adjnode;
	
	/**
	 * Weight of the edge
	 */
	public int weight;
	
	/**
	 * Level of pheromones
	 */
	public float pheromones = 0; // phermones are initialized as 0




	/**
	 * Edge constructor. <p> Receives the parameters that define an edge.
	 * @param adjnode Adjacent Node
	 * @param weight Edge weight
	 * @param pheromones Level of pheromones
	 */
	public Edge(int adjnode, int weight, float pheromones) {
		super();
		this.adjnode = adjnode;
		this.weight = weight;
		this.pheromones = pheromones;
	}



	@Override
	public String toString() {
		return "Edge [adjnode=" + adjnode + ", weight=" + weight + ", pheromones=" + pheromones + "]";
	}

}
