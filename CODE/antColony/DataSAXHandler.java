package antColony;

import org.xml.sax.Attributes;
import org.xml.sax.SAXParseException;
import org.xml.sax.helpers.DefaultHandler;

import graph.Graph;

/**
 * Class related to the reading of the XML file using SAX HANDLER.
 * @author Group 33
 *
 */
public class DataSAXHandler extends DefaultHandler {

	/**
	 * Data Class
	 */
	private static Data data = new Data();
	/**
	 * Graph Class
	 */
	private static Graph graph;

	/**
	 * String read
	 */
	private String read_string="";
	/**
	 * Current reading node
	 */
	private static int current_node = 0;
	/**
	 * Current adjacent node
	 */
	private static int adj = 0;


	/* (non-Javadoc)
	 * @see org.xml.sax.helpers.DefaultHandler#startDocument()
	 */
	public void startDocument(){
	}


	/* (non-Javadoc)
	 * @see org.xml.sax.helpers.DefaultHandler#endDocument()
	 */
	public void endDocument(){	
	}



	/* (non-Javadoc)
	 * @see org.xml.sax.helpers.DefaultHandler#startElement(java.lang.String, java.lang.String, java.lang.String, org.xml.sax.Attributes)
	 */
	public void startElement(String uri, String name, String tag, Attributes atts){

		if(tag.contentEquals("simulation")) {
			data.finalinst = Float.parseFloat(atts.getValue("finalinst"));
			data.antcolsize = Integer.parseInt(atts.getValue("antcolsize"));
			data.plevel = Float.parseFloat(atts.getValue("plevel"));
			return;
		}
		if(tag.contentEquals("graph")) {
			
			graph = new Graph(Integer.parseInt(atts.getValue("nbnodes")),Integer.parseInt(atts.getValue("nestnode")));

			if (graph.nbnodes <= 1) {
			    throw new IllegalArgumentException("Number of nodes in the graph must be higher than 1. Change the value in the XML file.");
			}
			
			for(int i=0; i<graph.nbnodes; i++) {
				
				graph.addNode(i+1);
			}
			return;
		}
		if(tag.contentEquals("node")) {
			current_node = Integer.parseInt(atts.getValue("nodeidx"));
			return;
		}
		if(tag.contentEquals("weight")) {

			adj = Integer.parseInt(atts.getValue("targetnode"));
			return;
		}

		if(tag.contentEquals("move")) {
			data.alpha = Float.parseFloat(atts.getValue("alpha"));
			data.beta = Float.parseFloat(atts.getValue("beta"));
			data.delta = Float.parseFloat(atts.getValue("delta"));
			return;
		}

		if(tag.contentEquals("evaporation")) {
			data.eta = Float.parseFloat(atts.getValue("eta"));
			data.rho = Float.parseFloat(atts.getValue("rho"));
			return;
		}
	}


	/* (non-Javadoc)
	 * @see org.xml.sax.helpers.DefaultHandler#endElement(java.lang.String, java.lang.String, java.lang.String)
	 */
	public void endElement(String uri, String name, String tag){
		if(tag.contentEquals("weight")) {
			
			int weight = Integer.parseInt(read_string);
			
			graph.addEdge(current_node, adj, weight, 0);

			graph.incrementWeight(weight);
			
			return;
		}

		if(tag.contentEquals("node")) {
			return;
		}
	}


	/* (non-Javadoc)
	 * @see org.xml.sax.helpers.DefaultHandler#characters(char[], int, int)
	 */
	public void characters(char[] ch, int start, int length){
		read_string=new String(ch,start,length);
	}


	/* (non-Javadoc)
	 * @see org.xml.sax.helpers.DefaultHandler#warning(org.xml.sax.SAXParseException)
	 */
	public void warning(SAXParseException e)throws SAXParseException{
		System.out.println("Warning! "+ e.getMessage());
	}


	/* (non-Javadoc)
	 * @see org.xml.sax.helpers.DefaultHandler#error(org.xml.sax.SAXParseException)
	 */
	public void error(SAXParseException e)throws SAXParseException{
		System.out.println("Error! "+ e.getMessage());
	}


	/* (non-Javadoc)
	 * @see org.xml.sax.helpers.DefaultHandler#fatalError(org.xml.sax.SAXParseException)
	 */
	public void fatalError(SAXParseException e) throws SAXParseException{
		System.out.println("Fatal error! "+ e.getMessage()+"\nAbortando");
		System.exit(-1);
	}



	/**
	 * Data getter .
	 * @return Object of type Data
	 */
	public static Data getData() {
		return data;
	}


	/**
	 * Data setter.
	 * @param data Object of type Data
	 */
	public static void setData(Data data) {
		DataSAXHandler.data = data;
	}

	/**
	 * Graph getter.
	 * @return Object of type Graph
	 */
	public static Graph getGraph() {
		return graph;
	}

	/**
	 * Graph setter.
	 * @param graph Object of type Graph
	 */
	public static void setGraph(Graph graph) {
		DataSAXHandler.graph = graph;
	}
}


