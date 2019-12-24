package dataStructure;

public class Edge implements edge_data {
	private int src; // where the edge comes from
	private int dst; // where the edge ends
	private double cost;

	public Edge(int src, int dst, double cost) {
		this.src = src;
		this.dst = dst;
		this.cost = cost;
	}
	public void setCost(double cost) {
		this.cost=cost;
	}
	public double getCost() {
		return this.cost;
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
		return "[" + this.src + " ---" + this.cost + "--- " + this.dst + "]";
	}

}
