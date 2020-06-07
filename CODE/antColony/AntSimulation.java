package antColony;

import java.io.File;
import java.io.IOException;
import java.util.LinkedList;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.SAXException;

import graph.Graph;
import simulator.PEC;
import simulator.Simulator;

/**
 * Class related to the Ant Simulation and main method.
 * @author Group 33
 *
 */

public class AntSimulation extends Simulator {

	/**
	 * Observation Number
	 */
	int number = 0;

	/**
	 * Number of move events
	 */
	int mevents = 0;

	/**
	 * Number of evaporation events
	 */
	int eevents = 0;

	/**
	 * Linked list with the shortest Hamiltonian Cycle
	 */
	LinkedList<Integer> cycle = new LinkedList<Integer>();

	/**
	 * Weight of the Hamiltonian Cycle
	 */
	int cycleWeight = -1;

	/**
	 * AntColony Class
	 */
	AntColony colony = new AntColony();

	/**
	 * DataSaxHandler Class
	 */
	DataSAXHandler DataHandler = new DataSAXHandler();

	@Override
	public String toString() {

		if (cycle.isEmpty()) {
			return "\nObservation " + number + ": \n \t\tPresent instant: \t\t" + currentTime + "\n \t\tNumber of move events: \t\t"
					+ mevents + "\n \t\tNumber of evaporation events: \t" + eevents + "\n \t\tHamiltonian cycle: \t\t<not found so far>";
		} else {
			String scycle = cycle.toString().replace("[", "{").replace("]", "}");

			return "\nObservation " + number + ":\n \t\tPresent instant: \t\t" + currentTime + "\n \t\tNumber of move events: \t\t"
			+ mevents + "\n \t\tNumber of evaporation events: \t" + eevents + "\n \t\tHamiltonian cycle: \t\t" + scycle;
		}

	}


	/**
	 * Main method
	 * @param args - XML File with tests
	 */
	@SuppressWarnings("unchecked")
	public static void main(String[] args) {

		if(args.length != 1){
			System.out.println("Usage: java ArtigoHandler <file_name.xml>");
			return;
		}

		SAXParserFactory factory = SAXParserFactory.newInstance();

		factory.setValidating(true);

		try{
			SAXParser parser = factory.newSAXParser();
			parser.parse(new File(args[0]),new DataSAXHandler());
		} catch(ParserConfigurationException e){
			System.out.println(e);
		} catch(SAXException e){
			System.out.println(e);
		} catch(IOException e){
			System.out.println(e);
		}



		Graph graph = DataSAXHandler.getGraph();
		Data data = DataSAXHandler.getData();

		AntSimulation antSim = new AntSimulation();
		antSim.simulationTime = data.finalinst;

		antSim.pec = new PEC();
		antSim.currentTime = 0;

		for (int i = 0; i < data.antcolsize; i++) {

			Ant ant = new Ant(graph.nestnode);
			antSim.colony.addAnt(ant);

			antSim.pec.addEvPEC(new AntMove(antSim.currentTime, data, ant, graph));

		}

		for(int j = 1; j <= 20; j++) {
			antSim.pec.addEvPEC(new Observation(j*(antSim.simulationTime/20),antSim));
		}


		antSim.currentEv = antSim.pec.nextEvPEC();
		antSim.mevents++;

		while (antSim.currentTime < antSim.simulationTime) {

			antSim.currentEv.simulateEvent(antSim.pec);
			Ant ant = new Ant(graph.nestnode);

			if (!antSim.pec.checkEmpty()) {
				antSim.currentTime = antSim.currentEv.getTime();
				antSim.currentEv = antSim.pec.nextEvPEC();




				if (antSim.currentEv instanceof AntMove) {
					ant = ((AntMove) antSim.currentEv).ant;
					antSim.mevents++;
				} else if (antSim.currentEv instanceof PheromoneEvaporation) {
					ant = ((PheromoneEvaporation) antSim.currentEv).ant;
					antSim.eevents++;
				}
			} else {
				antSim.currentTime = antSim.simulationTime;
			}

			if((antSim.currentEv instanceof AntMove) || (antSim.currentEv instanceof PheromoneEvaporation)) {

				if (ant.checkHamiltonian(graph.nbnodes) && antSim.currentTime < antSim.simulationTime) {

					for (int i = 1; i < ant.traversedPath.size(); i++) {

						int n1 = ant.traversedPath.get(i-1);
						int n2 = ant.traversedPath.get(i);

						if(graph.plevelBetween(n1, n2) == 0) {
							antSim.pec.addEvPEC(new PheromoneEvaporation(antSim.currentTime, ant, graph, data, n1, n2));
						}

					}

					ant.layPheromones(graph, data.plevel);


					if (ant.hamiltonianWeight(graph) < antSim.cycleWeight || antSim.cycleWeight == -1) {
						antSim.cycle = (LinkedList<Integer>) ant.traversedPath.clone();
						antSim.cycleWeight = ant.hamiltonianWeight(graph);
						antSim.cycle.removeLast();
					}

					ant.traversedPath.clear();
					ant.addNode(graph.nestnode);
				}

			}
		}
	}

}
