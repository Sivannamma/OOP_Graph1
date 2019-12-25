package algorithms;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import dataStructure.DGraph;
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
		// TODO Auto-generated method stub
	}

	@Override
	public void save(String file_name) {
		// TODO Auto-generated method stub
	}

	@Override
	public boolean isConnected() {
		// TODO Auto-generated method stub
		return false;
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

// TO FIX THIS BUG *******
	private double sumPath() {
		double ans = 0;

//		for(int i =list.size()-1;i>=0;i--) {
//			node_data temp = list[i];
//			
//		}

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

	private void setWeight(int src) {
		for (node_data key : this.current.getV()) {
			if (key.getKey() != src)
				key.setWeight(Double.POSITIVE_INFINITY);
			else
				key.setWeight(0);
		}
	}

	@Override
	public double shortestPathDist(int src, int dest) {
		if (list == null) {
			shortestPath(src, dest);
			System.out.println(list.toString());
		}
		return sumPath();
	}

	@Override
	public List<node_data> shortestPath(int src, int dest) {
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

		for (node_data n : col) {
			g.addNode(n);
		}

		return g;
	}

}
