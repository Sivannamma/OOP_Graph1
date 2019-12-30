package gui_graph;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Menu;
import java.awt.MenuBar;
import java.awt.MenuItem;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import algorithms.Graph_Algo;
import dataStructure.node_data;

public class GUI_window extends JFrame implements ActionListener, MouseListener {
	Graph_Algo graph;
	JTextField text_src;
	JTextField text_dest;
	JTextField input;
	JButton button;
	JLabel label;

	public GUI_window(Graph_Algo graph) {
		this.graph = graph;
		text_src = new JTextField();
		text_dest = new JTextField();
		input = new JTextField();
		input.setVisible(false);
		text_src.setVisible(false);
		text_dest.setVisible(false);
		button = new JButton("");
		button.setVisible(false);
		label = new JLabel("Please enter a list of targets: ");
		label.setVisible(false);

		init_window();
	}

	private void init_window() {
		// the size of the window
		this.setSize(600, 600);
		// setting that the program is terminated when we close 'X' on the window as
		// well.
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		// not resizeable
		this.setResizable(false);
		// font
		Font f = new Font("Arial", Font.BOLD, 13);
		this.setFont(f);

		// set flow layout for the frame
		this.getContentPane().setLayout(new FlowLayout());

		// menu bar
		MenuBar menuBar = new MenuBar();
		this.setMenuBar(menuBar);

		// categories
		Menu files = new Menu("file");
		files.addActionListener(this);
		// adding to the tag student info
		MenuItem saveFile = new MenuItem("Save graph");
		saveFile.addActionListener(this);
		MenuItem loadFile = new MenuItem("Load to graph");
		loadFile.addActionListener(this);
		files.add(saveFile);
		files.add(loadFile);

		// categories
		Menu algorithms = new Menu("Algorithms");
		algorithms.addActionListener(this);
		// adding to the tag general actions
		MenuItem isConnected = new MenuItem("isConnected");
		isConnected.addActionListener(this);
		MenuItem shortPath = new MenuItem("shortestPath");
		shortPath.addActionListener(this);
		MenuItem shortPathWeight = new MenuItem("shortestPathWeight");
		shortPathWeight.addActionListener(this);
		MenuItem tsp = new MenuItem("TSP");
		tsp.addActionListener(this);
		algorithms.add(tsp);
		algorithms.add(shortPath);
		algorithms.add(shortPathWeight);
		algorithms.add(isConnected);

		// adding to the menu bar
		menuBar.add(files);
		menuBar.add(algorithms);

		// setting the text field
		text_src.setText("src");
		text_src.setBounds(450, 20, 40, 20);
		this.add(text_src);
		text_dest.setText("dest");
		text_dest.setBounds(500, 20, 40, 20);
		this.add(text_dest);
		// button_weight
		this.button.setLocation(450, 25);
		this.button.addActionListener(this);
		this.add(button);
		this.add(input);

		// label of list targets

		this.add(label);
		this.addMouseListener(this);

	}

	@Override
	public void mouseClicked(MouseEvent e) {

	}

	@Override
	public void mouseEntered(MouseEvent e) {

	}

	@Override
	public void mouseExited(MouseEvent e) {

	}

	@Override
	public void mousePressed(MouseEvent e) {

	}

	@Override
	public void mouseReleased(MouseEvent e) {

	}

	@Override
	public void actionPerformed(ActionEvent e) {

		String str = e.getActionCommand();
		setTextFalse();
		switch (str) {
		case "TSP": {
			// for the way it shows
			this.setLayout(null);
			label.setBounds(200, 30, 200, 30);
			input.setBounds(200, 60, 150, 25);
			input.setText("");
			button.setText("submit list");
			label.setVisible(true);
			Dimension size = button.getPreferredSize();
			button.setBounds(370, 60, size.width, size.height);
			input.setVisible(true);
			button.setVisible(true);
			break;
		}

		case "submit list": {
			String src = input.getText();
			List<Integer> list = new ArrayList<Integer>();
			for (int i = 0; i < src.length(); i++) {
				if (src.charAt(i) != ',') {
					list.add((int) src.charAt(i) - 48);
				}
			}
			List<node_data> newList = this.graph.TSP(list);
			setFalse();
			JOptionPane.showMessageDialog(null, newList, "TSP: ", JOptionPane.DEFAULT_OPTION);
			break;
		}
		case "Save graph": {
			JFileChooser fileChooser = new JFileChooser();
			fileChooser.setDialogTitle("Specify a file to save");

			int userSelection = fileChooser.showSaveDialog(this);
			if (userSelection == JFileChooser.APPROVE_OPTION) {
				File fileToSave = fileChooser.getSelectedFile();
				graph.save(fileToSave.getAbsolutePath());
			}

			break;
		}
		case "Load to graph": {

			break;
		}
		case "isConnected": {
			JOptionPane.showMessageDialog(null, this.graph.isConnected(), "Is the graph connected?",
					JOptionPane.DEFAULT_OPTION);

			break;
		}
		case "shortestPath": {
			button.setText("submit(path)");
			setTrue(); // calling to set the button to visible
			break;
		}

		case "shortestPathWeight": {
			button.setText("submit(weight)");
			setTrue(); // calling to set the button to visible
			break;
		}

		case "submit(weight)": {
			int src = Integer.parseInt(text_src.getText());
			int dest = Integer.parseInt(text_dest.getText());
			setFalse();
			JOptionPane.showMessageDialog(null, this.graph.shortestPathDist(src, dest), "The shortest cost:",
					JOptionPane.DEFAULT_OPTION);
			break;
		}
		case "submit(path)": {
			int src = Integer.parseInt(text_src.getText());
			int dest = Integer.parseInt(text_dest.getText());
			setFalse();
			JOptionPane.showMessageDialog(null, this.graph.shortestPath(src, dest), "The shortest path:",
					JOptionPane.DEFAULT_OPTION);
			break;
		}
		}
	}

	private void setTextFalse() {
		this.getContentPane().setLayout(new FlowLayout());
		label.setVisible(false);
		text_src.setVisible(false);
		text_dest.setVisible(false);
		button.setVisible(false);
		input.setVisible(false);

	}

// function to set all the buttons and text fields to false;
	private void setFalse() {
		// set flow layout for the frame
		this.getContentPane().setLayout(new FlowLayout());
		text_src.setText("src");
		text_dest.setText("dest");
		label.setVisible(false);
		text_src.setVisible(false);
		text_dest.setVisible(false);
		button.setVisible(false);
		input.setVisible(false);
	}

// function to set the parameters that we need to visible;
	private void setTrue() {
		button.setVisible(true);
		text_src.setVisible(true);
		text_dest.setVisible(true);
	}
}
