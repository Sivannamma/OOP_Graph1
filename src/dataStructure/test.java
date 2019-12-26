package dataStructure;

import algorithms.Graph_Algo;
import utils.Point3D;

public class test {
	public static void main(String[] args) {
		Node n1 = new Node(1, new Point3D(15, 23));
		Node n2 = new Node(2, new Point3D(34, 67));
		Node n3 = new Node(3, new Point3D(23, 65));
		Node n4 = new Node(4, new Point3D(15, 23));
		Node n5 = new Node(5, new Point3D(34, 67));
		Node n6 = new Node(6, new Point3D(23, 65));
		Node n7 = new Node(7, new Point3D(23, 65));
		Node n8 = new Node(8, new Point3D(23, 65));
		DGraph d1 = new DGraph();
		d1.addNode(n1);
		d1.addNode(n2);
		d1.addNode(n3);
		d1.addNode(n4);
		d1.addNode(n5);
		d1.addNode(n6);
		d1.addNode(n7);
		d1.addNode(n8);
		d1.connect(1, 2, 1);
		d1.connect(1, 4, 7);
		d1.connect(1, 3, 5);
		d1.connect(1, 5, 6);
		d1.connect(2, 3, 6);
		d1.connect(2, 6, 9);
		d1.connect(3, 8, 6);
		d1.connect(3, 7, 8);
		d1.connect(4, 6, 1);
		d1.connect(5, 4, 3);
		d1.connect(5, 6, 3);
		d1.connect(6, 3, 1);
		d1.connect(6, 7, 2);
		d1.connect(6, 8, 2);

		Graph_Algo d2 = new Graph_Algo();
		d2.init(d1);
		System.out.println(d2.getGraph());
		System.out.println(d2.shortestPathDist(1, 7));

	}

}
