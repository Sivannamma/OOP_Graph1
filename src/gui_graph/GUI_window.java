package gui_graph;

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

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import algorithms.Graph_Algo;

public class GUI_window extends JFrame implements ActionListener, MouseListener {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	Graph_Algo graph;
	JTextField text_src;
	JTextField text_dest;
	JButton button_weight;
	JButton button_path;

	public GUI_window(Graph_Algo graph) {
		this.graph = graph;
		text_src = new JTextField();
		text_dest = new JTextField();
		text_src.setVisible(false);
		text_dest.setVisible(false);
		button_weight = new JButton("submit(weight)");
		button_weight.setVisible(false);
		button_path = new JButton("submit(path)");
		button_path.setVisible(false);

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
		this.button_weight.setLocation(450, 25);
		this.button_weight.addActionListener(this);
		this.add(button_weight);
		// button_path
		this.button_path.setLocation(450, 25);
		this.button_path.addActionListener(this);
		this.add(button_path);

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

		switch (str) {
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
			this.graph.isConnected();

			break;
		}
		case "shortestPath": {
			setTrue('p'); // calling to set the button to visible
			break;
		}

		case "shortestPathWeight": {
			setTrue('w'); // calling to set the button to visible
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

// function to set all the buttons and text fields to false;
	private void setFalse() {
		text_src.setText("src");
		text_dest.setText("dest");
		text_src.setVisible(false);
		text_dest.setVisible(false);
		button_path.setVisible(false);
		button_weight.setVisible(false);
	}

// function to set the parameters that we need to visible;
	private void setTrue(char which) {
		if (which == 'p') // means we want the button for path
			button_path.setVisible(true);
		if (which == 'w') // means we want the weight button
			button_weight.setVisible(true);

		text_src.setVisible(true);
		text_dest.setVisible(true);
	}
}
