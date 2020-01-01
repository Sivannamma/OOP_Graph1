package dataStructure;

import java.util.ArrayList;
import java.util.List;

import algorithms.Graph_Algo;
import gui_graph.GUI_window;
import utils.Point3D;

public class test {
	public static void main(String[] args) {
		Node n1 = new Node(1, new Point3D(0, 0));
		Node n2 = new Node(2, new Point3D(340, 107));
		Node n3 = new Node(3, new Point3D(230, 195));
		Node n4 = new Node(4, new Point3D(200, 123));

		DGraph d1 = new DGraph();
		d1.addNode(n1);
		d1.addNode(n2);
		d1.addNode(n3);
		d1.addNode(n4);
		d1.connect(1, 2, 6);
		d1.connect(2, 3, 4);
		d1.connect(3, 4, 4);
		d1.connect(3, 1, 4);
		d1.connect(3, 4, 4);
		Graph_Algo d2 = new Graph_Algo();
		Graph_Algo d4 = new Graph_Algo();
		d2.init(d1);
//		System.out.println(d2.getGraph());
//		System.out.println(d2.isConnected());
		//d2.save("myGraph.txt");
//		d2.init("myGraph.txt");
		graph d3 = new DGraph();
		d3=d2.copy();
		d4.init(d3);
		d2.getGraph().removeNode(1);
		
		
		
		GUI_window g = new GUI_window(d4);
		System.out.println(d2.getGraph());
		g.setVisible(true);
		

	}
}
