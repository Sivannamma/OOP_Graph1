package dataStructure;

import java.util.Collection;
import java.util.HashMap;

public class DGraph implements graph {
	private HashMap<Integer, node_data> map = new HashMap<Integer, node_data>();

	@Override
	public node_data getNode(int key) {
		return map.get(key);
	}

	@Override
	public edge_data getEdge(int src, int dest) {
		if (this.map.get(src) instanceof Node) // if its an instance, we cast and search for the dest in the neightboors
												// hashmap
			return ((Node) (this.map.get(src))).getMap().get(dest);
		else
			throw new RuntimeException("the element is not an instance of Node");
	}

	@Override
	public void addNode(node_data n) {
		this.map.put(n.getKey(), n);
	}

	@Override
	public void connect(int src, int dest, double w) {
		if (this.map.get(src) instanceof Node) // if its an instance, we cast and search for the dest in the neightboors
			// hashmap
			((Node) this.map.get(src)).setMap(dest, w);
		else
			throw new RuntimeException("the element is not an instance of Node");

	}

	@Override
	public Collection<node_data> getV() {
		return (Collection<node_data>) this.map;
	}

	@Override
	public Collection<edge_data> getE(int node_id) {
		if (this.map.get(node_id) instanceof Node) // if its an instance, we cast and search for the dest in the
													// neightboors hashmap
			return (Collection<edge_data>) ((Node) this.map.get(node_id)).getMap();
		else
			throw new RuntimeException("the element is not an instance of Node");
	}

	@Override
	public node_data removeNode(int key) {
		return this.map.remove(key);
	}

	@Override
	public edge_data removeEdge(int src, int dest) {
		if (this.map.get(src) instanceof Node) // if its an instance, we cast and search for the dest in the neightboors
			// hashmap
			((Node) this.map.get(src)).deleteEdge(dest);
		else
			throw new RuntimeException("the element is not an instance of Node");
		return null;
	}

	@Override
	public int nodeSize() {
		return this.map.size();
	}

	@Override
	public int edgeSize() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int getMC() {
		// TODO Auto-generated method stub
		return 0;
	}

}
