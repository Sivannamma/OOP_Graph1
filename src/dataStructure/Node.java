package dataStructure;

import java.util.HashMap;

import utils.Point3D;

public class Node implements node_data {
	private int id; // the name of the node
	private int pred; // the node we came from - the parent
	private boolean visited; // variable that we know if we have been in some
	private double weight; // the cost from one vertext to another
	private HashMap<Integer, Edge> neighboors = new HashMap<Integer, Edge>();
	private static int NIL = -1;

//	public Node() { // **************************//
//		this.id = NIL; // as a default;
//		this.visited = false;
//		this.pred = NIL;
//		this.weight = Double.POSITIVE_INFINITY;
//	}

	public Node(int id) {
		this.id = id;
		this.weight = Double.POSITIVE_INFINITY;
		this.visited = false;
		this.pred = NIL;
	}

	public Node copy() { // copy function
		Node n = new Node(this.id);
		n.weight = this.weight;
		n.visited = this.visited;
		n.pred = this.pred;
		return n;
	}

	public void setMap(int dest, double cost) {
		if (this.neighboors.containsKey(dest))
			this.neighboors.get(dest).setCost(cost);
		else {
			Edge e = new Edge(this.id, dest, cost); // creating the edge
			this.neighboors.put(dest, e); // setting the neightboor in the hashmap
		}
	}

	public void deleteEdge(int dest) {
		this.neighboors.remove(dest);
	}

	public HashMap<Integer, Edge> getMap() {
		return neighboors;
	}

	@Override
	public int getKey() {
		return this.id;
	}

	@Override
	public Point3D getLocation() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setLocation(Point3D p) {
		// TODO Auto-generated method stub
	}

	@Override
	public double getWeight() {
		return this.weight;
	}

	@Override
	public void setWeight(double w) {
		this.weight = w;
	}

	@Override
	public String getInfo() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setInfo(String s) {
		// TODO Auto-generated method stub

	}

	@Override
	public int getTag() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void setTag(int t) {
		// TODO Auto-generated method stub

	}

	public String toString() {
		return "id: " + id + " pred: " + pred + " visited: " + visited + " weight: " + weight;
	}

}
