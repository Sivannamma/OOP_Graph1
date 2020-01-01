package dataStructure;

import java.util.HashMap;
import java.io.Serializable;

public class Edge implements edge_data, Serializable {
	private int src; // where the edge comes from
	private int dst; // where the edge ends
	private double cost;

	public Edge copy() { // copy function
		Edge n = new Edge(this.src, this.dst, this.cost);
		HashMap<Integer, edge_data> newHash = new HashMap<Integer, edge_data>();
		return n;
	}

	public Edge(int src, int dst, double cost) {
		this.src = src;
		this.dst = dst;
		this.cost = cost;
	}

	public void setCost(double cost) {
		this.cost = cost;
	}

	@Override
	public int getSrc() {
		return this.src;
	}

	@Override
	public int getDest() {
		return this.dst;
	}

	@Override
	public double getWeight() {
		return this.cost;
	}

	@Override
	public String getInfo() {
		return null;
	}

	@Override
	public void setInfo(String s) {
		return;
	}

	@Override
	public int getTag() {
		return 0;
	}

	@Override
	public void setTag(int t) {
		return;
	}

	public String toString() {
		return "[" + this.src + " ---" + this.cost + "--- " + this.dst + "]";
	}

}
