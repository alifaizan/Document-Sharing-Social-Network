package main.java;

import java.awt.event.ActionEvent;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JButton;

import java.awt.event.ActionListener;

import javax.swing.JTabbedPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JComboBox;
import javax.swing.JList;
import javax.swing.JCheckBox;


public class GUI {

    private JFrame frame;
    private JTextArea display;
    //Fields
    private JTextField consumerField;
    private JTextField producerField;
    private JTextField documentField;
    private JTextField tagsField;
    private JTextField searchField;
    private JTabbedPane tabbedPane;
    //Panels
    private JPanel Simulation;
    private JPanel viewProducerGraph;
    private JPanel viewConsumerGraph;
    //Buttons
    private JButton startsimButton;
    private JButton stopsimButton;
    private JButton searchButton;
    private JButton undoButton;
    private JButton showConsumerButton;
    private JButton showProducerGraph;
    private JScrollPane pane;
    //Labels
    private JLabel consumersLabel;
    private JLabel producerLabel;
    private JLabel documentsLabel;
    private JLabel tagsLabel; 
    private JLabel searchLabel;
    // New Sim Obj
	private Simulation sim;
	private JCheckBox documentCheckBox;
	private JCheckBox randomRankingStrategy;
	
	/**
	 * Launch the application.
	 

	/**
	 * Create the application.
	 */
	public GUI() {
		initialize();
		sim= new Simulation();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 681, 421);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
		frame.getContentPane().setLayout(null);
		
		tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBounds(31, 10, 624, 361);
		frame.getContentPane().add(tabbedPane);
		
		Simulation = new JPanel();
		tabbedPane.addTab("Simulation", null, Simulation, null);
		Simulation.setLayout(null);
		
		startsimButton = new JButton("Start Simulation");
		startsimButton.setBounds(74, 299, 143, 23);
		Simulation.add(startsimButton);
		
		pane = new JScrollPane();
		pane.setBounds(10, 104, 599, 184);
		Simulation.add(pane);
		
		display = new JTextArea();
		pane.setViewportView(display);
		
		consumerField = new JTextField();
		consumerField.setBounds(188, 11, 29, 20);
		Simulation.add(consumerField);
		consumerField.setColumns(10);
		
		consumersLabel = new JLabel("Number of consumers (1-50) ");
		consumersLabel.setBounds(10, 12, 168, 14);
		Simulation.add(consumersLabel);
		
		producerLabel = new JLabel("Number of Producers (1-50) ");
		producerLabel.setBounds(10, 45, 168, 14);
		Simulation.add(producerLabel);
		
		producerField = new JTextField();
		producerField.setColumns(10);
		producerField.setBounds(188, 45, 29, 20);
		Simulation.add(producerField);
		
		documentsLabel = new JLabel("Number of Documents (1-10) ");
		documentsLabel.setBounds(10, 76, 168, 14);
		Simulation.add(documentsLabel);
		
		documentField = new JTextField();
		documentField.setColumns(10);
		documentField.setBounds(188, 76, 29, 20);
		Simulation.add(documentField);
		
		tagsLabel = new JLabel("Number of Tags (1-10) ");
		tagsLabel.setBounds(251, 14, 143, 14);
		Simulation.add(tagsLabel);
		
		tagsField = new JTextField();
		tagsField.setColumns(10);
		tagsField.setBounds(390, 11, 29, 20);
		Simulation.add(tagsField);
		
		searchLabel = new JLabel("Search");
		searchLabel.setBounds(251, 42, 58, 14);
		Simulation.add(searchLabel);
		
		searchField = new JTextField();
		searchField.setColumns(10);
		searchField.setBounds(298, 39, 58, 23);
		Simulation.add(searchField);
		
		setSearchButton(new JButton("Search"));
		getSearchButton().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
		        User user = sim.runIteration();
		        user.act(sim.getDocuments(), main.java.Simulation.getNumberOfSearchResults());
		         
		        for ( final User user1 : sim.getUsers()) {
		        	 main.java.Simulation.printToGUI(user1.payoffHistory());
		        }
				
			}
		});
		getSearchButton().setBounds(356, 39, 83, 23);
		Simulation.add(getSearchButton());
		
		stopsimButton = new JButton("Stop Simulation");
		stopsimButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				print("Simulation terminated");
				System.exit(0);
			}
		});
		stopsimButton.setBounds(377, 299, 143, 23);
		Simulation.add(stopsimButton);
		
		undoButton = new JButton("Undo");
		undoButton.setBounds(503, 8, 89, 23);
		Simulation.add(undoButton);
		
		documentCheckBox = new JCheckBox("Document Strategy");
		documentCheckBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				DocumentPopularityStrategy document = new DocumentPopularityStrategy();
				document.Rank(sim);
			}
		});
		documentCheckBox.setBounds(445, 49, 165, 23);
		Simulation.add(documentCheckBox);
		
		randomRankingStrategy = new JCheckBox("RandomRankingStrategy");
		randomRankingStrategy.setBounds(445, 74, 174, 23);
		Simulation.add(randomRankingStrategy);
		startsimButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
					
				sim.setupSim();
			}
		});
		
		viewProducerGraph = new JPanel();
		tabbedPane.addTab("View Producer Graph", null, viewProducerGraph, null);
		viewProducerGraph.setLayout(null);
		
		showProducerGraph = new JButton("Show Producer Graph");
		showProducerGraph.setBounds(210, 299, 195, 23);
		viewProducerGraph.add(showProducerGraph);
		
		viewConsumerGraph = new JPanel();
		tabbedPane.addTab("View Consumer Graph", null, viewConsumerGraph, null);
		viewConsumerGraph.setLayout(null);
		
		showConsumerButton = new JButton("Show Consumer Graph");
		showConsumerButton.setBounds(209, 299, 194, 23);
		viewConsumerGraph.add(showConsumerButton);
		
	}
	
	
          	
	public void stop(){
		// TODO
		print("Simulation terminated");
		System.exit(0);
	}
	
	
	public void print(String s){
		display.append(s + "\n");
	}
	
	public int getConsumers(){
		
		return Integer.parseInt(consumerField.getText());
		
	}
	
	public int getProducers(){
		
		return Integer.parseInt(producerField.getText());
		
	}
	
	public int getDocuments(){
		
		return Integer.parseInt(documentField.getText());
		
	}
	
	public int getTags(){
		
		return Integer.parseInt(tagsField.getText());
		
	}
	
	public int getSearch(){
		
		return Integer.parseInt(searchField.getText());
		
	}

	public JButton getSearchButton() {
		return searchButton;
	}

	public void setSearchButton(JButton searchButton) {
		this.searchButton = searchButton;
	}
}
