package algorithms;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import dataStructure.DGraph;
import dataStructure.Node;
import dataStructure.edge_data;
import dataStructure.graph;
import dataStructure.node_data;

/**
 * This empty class represents the set of graph-theory algorithms which should
 * be implemented as part of Ex2 - Do edit this class.
 * 
 * @author
 *
 */
public class Graph_Algo implements graph_algorithms {
	private graph current;
	private List<node_data> list;
	private double costAns = 0;

	@Override
	public void init(graph g) {
		this.current = g;
	}

	@Override
	public void init(String file_name) {

		try {
			FileInputStream file = new FileInputStream(file_name);
			ObjectInputStream in = new ObjectInputStream(file);

			this.current = (graph) in.readObject();

			in.close();
			file.close();

			System.out.println("Object has been deserialized");
			System.out.println(this.current);
		}

		catch (IOException ex) {
			System.out.println("IOException is caught");
		}

		catch (ClassNotFoundException ex) {
			System.out.println("ClassNotFoundException is caught");
		}

	}

	@Override
	public void save(String file_name) {
		String filename = file_name;

		try {
			FileOutputStream file = new FileOutputStream(filename);
			ObjectOutputStream out = new ObjectOutputStream(file);

			out.writeObject(this.current);

			out.close();
			file.close();

			System.out.println("Graph has been serialized");
		} catch (IOException ex) {
			System.out.println("IOException is caught");
		}
	}

	@Override
	public boolean isConnected() {
		boolean nei = false;
		for (node_data key : this.current.getV()) { // itearting through the hashmap
			Collection<edge_data> col = this.current.getE(key.getKey()); // creating collection on neighbors of the
			// specific node
			for (node_data path : this.current.getV()) { // itearting again on nodes
				if (path != key) { // if we are not on the same node
					for (edge_data e : col) {

						if ((e.getDest() == (path.getKey()))) { // if its a nei- no need to search a path
							nei = true;
							break;
						}
					}
					if (!nei) {// if path is not neighbor of key, go find a path
						initVisited(); // initializing the tag to be not visited- working on the same graph
						list = null;
						if (shortestPathDist(key.getKey(), path.getKey()) == Double.POSITIVE_INFINITY) {
							return false;
						}
					}
				}
				nei = false;
			}
		}
		return true;
	}

	private void initVisited() {
		for (node_data current : this.current.getV()) {
			current.setTag(0);
		}
	}

	public graph getGraph() {
		return this.current;
	}

// this function checks if we visited all the nodes in the graph,
// once we found a place we didnt visit we return 0
	private boolean isVisited() {
		for (node_data key : this.current.getV()) {
			if (key.getTag() == 0)
				return true;
		}
		return false;
	}

	private double sumPath() {
		if (list == null || list.isEmpty()) {
			return Double.POSITIVE_INFINITY;
		}
		double ans = 0;

		for (int j = list.size() - 1; j > 0; j--) {
			// getting the edges that are connected to the node_data in place j of the list
			Collection<edge_data> col = this.current.getE(list.get(j).getKey());
			for (edge_data e : col) { // iterating through the neighboors of this current node
				if (e.getDest() == list.get(j - 1).getKey())
					ans += e.getWeight();
			}
		}
		return ans;
	}

// this function returns the next node with the minimum weight- the next node to
// be dealing with
	private Integer isNextMin() {
		node_data ans = null;
		for (node_data key : this.current.getV()) {
			if (key.getTag() == 0 && key.getWeight() <= costAns) {
				costAns = key.getWeight();
				ans = key;
			}
		}

		this.current.getNode(ans.getKey()).setTag(1); // updating to visitedl
		for (node_data key : this.current.getV()) {
			if (key.getTag() == 0) {
				costAns = key.getWeight();
				break;
			}
		}
		return ans.getKey();
	}

// we initialize the src weight to be 0, and the others to be infinity
	private void setWeight(int src) {
		for (node_data key : this.current.getV()) {
			if (key.getKey() != src)
				key.setWeight(Double.POSITIVE_INFINITY);
			else
				key.setWeight(0);
		}
	}

	private boolean isContained(int src, int dest) {
		int count = 0;
		for (node_data key : this.current.getV()) {
			if (key.getKey() == src)
				count++;
			if (key.getKey() == dest)
				count++;
		}
		// two means they are both contained in the nodes in the graph
		return (count == 2) ? true : false;

	}

	@Override
	public double shortestPathDist(int src, int dest) {
		if (list == null) {
			shortestPath(src, dest);
			// System.out.println(list.toString());
		}
		return sumPath();
	}

	@Override
	public List<node_data> shortestPath(int src, int dest) {
		if (!isContained(src, dest)) {
			throw new RuntimeException("nodes must be in the graph");
		}

		Collection<edge_data> nei = this.current.getE(src);
		if (nei.isEmpty())
			return null;

		setWeight(src); // setting the start point
		// System.out.println(this.current.getNode(src).getWeight());
		// while we have more nodes to go to- inner function
		while (isVisited()) {
			Integer id = isNextMin();

			Collection<edge_data> col = this.current.getE(id);
			for (edge_data e : col) { // iterating through the neighboors of this current node
				// setting the value of the node.src+the sum of the edge
				double check = this.current.getNode(e.getSrc()).getWeight() + e.getWeight();
				// checking if the value of check is lower --> updating the new sum
				if (check < this.current.getNode(e.getDest()).getWeight()) {
					this.current.getNode(e.getDest()).setWeight(check);// setting the new cheap weight
					this.current.getNode(e.getDest()).setInfo(String.valueOf(e.getSrc()));// setting the predesscor of
					// this node
				}
			}

		}

		list = new ArrayList<node_data>();

		node_data temp = this.current.getNode(dest);
		while (temp != this.current.getNode(src)) {
			list.add(temp);
			if (temp.getInfo().equals("-1")) {
				list.clear();
				return list;
			}
			temp = this.current.getNode(Integer.parseInt(temp.getInfo()));
		}
		list.add(temp);
		return list;
	}

	@Override
	public List<node_data> TSP(List<Integer> targets) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public graph copy() {

		DGraph g = new DGraph();
		Collection<node_data> col = this.current.getV();

		// for the main hashmap.
		for (node_data n : col) {
			node_data newNode = new Node(n.getKey(), n.getLocation());
			g.addNode(newNode);
			Collection<edge_data> nei = this.current.getE(n.getKey());
		}
		return g;
	}

}
