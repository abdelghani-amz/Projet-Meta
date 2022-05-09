package gUI;

import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JRadioButton;
import javax.swing.ButtonGroup;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.awt.Font;
import javax.swing.JButton;
import java.awt.Color;
import java.awt.Component;

import backEnd.Puzzle ;
import backEnd.Solver;
import backEnd.SolverGA;
import backEnd.SolverPSO;
import backEnd.Chromosome;
import backEnd.Node;
import backEnd.Particle;

import javax.swing.UIManager;
import javax.swing.JTextField;
import javax.swing.Timer ;
import java.awt.Choice;
import javax.swing.JComboBox;

public class MainWindow extends JFrame implements ActionListener {

	private JPanel contentPane;
	private final ButtonGroup moderesol = new ButtonGroup();
	private JButton[] tiles ;
	private JTextField txtSolution;
	private JTextField textTime;
	private JTextField txtExplored;
	private JTextField txtgen;
	private JRadioButton buttonrev;
	private JRadioButton buttonmnahattan;
	private JRadioButton buttonmisplaced;
	private JRadioButton buttonbfs;
	private JRadioButton buttondfs;
	private JSpinner seuil;
	private JButton btnRandomise;
	private JButton btnResoudre;
	private JButton btnVisualisez;
	private JComboBox algoChoice;
	private JLabel lblMetric1;
	private JLabel lblMetric2;
	private JLabel lblSeuil;
	private JSpinner iterations;
	private JSpinner size;
	private JSpinner param1;
	private JSpinner param2;
	private JSpinner param3;
	private JLabel lblSize;
	private JLabel lblIterations;
	private JLabel lblParam1;
	private JLabel lblParam2;
	private JLabel lblParam3;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainWindow frame = new MainWindow();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public MainWindow() {
		setTitle("8 Puzzle Solver");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 715, 458);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		tiles = new JButton[9] ;
		
		
		buttondfs = new JRadioButton("Recherche en profondeur");
		buttondfs.setVisible(true);
		buttondfs.setSelected(true);
		moderesol.add(buttondfs);
		buttondfs.setBounds(6, 20, 176, 23);
		contentPane.add(buttondfs);
		
		buttonbfs = new JRadioButton("Recherche en largeur");
		buttonbfs.setVisible(true);
		moderesol.add(buttonbfs);
		buttonbfs.setBounds(6, 46, 195, 23);
		contentPane.add(buttonbfs);
		
		buttonmisplaced = new JRadioButton("A* Distance de Hamming");
		buttonmisplaced.setVisible(true);
		moderesol.add(buttonmisplaced);
		buttonmisplaced.setBounds(6, 72, 170, 23);
		contentPane.add(buttonmisplaced);
		
		buttonmnahattan = new JRadioButton("A* Distance de Manhattan");
		buttonmnahattan.setVisible(true);
		moderesol.add(buttonmnahattan);
		buttonmnahattan.setBounds(6, 98, 195, 23);
		contentPane.add(buttonmnahattan);
		
		buttonrev = new JRadioButton("A* Distance de Manhattan + renversement ");
		buttonrev.setVisible(true);
		moderesol.add(buttonrev);
		buttonrev.setBounds(6, 124, 280, 23);
		contentPane.add(buttonrev);
		
		JLabel lblSoluce = new JLabel("Solution: ");
		lblSoluce.setBounds(307, 24, 90, 14);
		contentPane.add(lblSoluce);
		
		JLabel lblTime = new JLabel("Temps d'execution: ");
		lblTime.setBounds(307, 55, 133, 14);
		contentPane.add(lblTime);
		
		lblMetric1 = new JLabel("Nombre d'\u00E9tats explor\u00E9s:");
		lblMetric1.setBounds(307, 86, 170, 14);
		contentPane.add(lblMetric1);
		
		lblMetric2 = new JLabel("Nombre d'\u00E9tats g\u00E9n\u00E9r\u00E9s:");
		lblMetric2.setBounds(307, 117, 166, 14);
		contentPane.add(lblMetric2);
		
		seuil = new JSpinner();
		seuil.setVisible(true);
		seuil.setModel(new SpinnerNumberModel(new Integer(31), new Integer(0), null, new Integer(1)));
		seuil.setBounds(188, 21, 44, 20);
		contentPane.add(seuil);
		
		lblSeuil = new JLabel("Seuil");
		lblSeuil.setVisible(true);
		lblSeuil.setFont(new Font("Tahoma", Font.ITALIC, 11));
		lblSeuil.setHorizontalAlignment(SwingConstants.CENTER);
		lblSeuil.setBounds(178, 0, 46, 31);
		contentPane.add(lblSeuil);
		
		JButton btnTile3 = new JButton("3");
		btnTile3.setBackground(Color.LIGHT_GRAY);
		btnTile3.setFont(new Font("Tahoma", Font.PLAIN, 30));
		btnTile3.setBounds(534, 175, 70, 70);
		contentPane.add(btnTile3);
		tiles[2] = btnTile3 ;
		
		JButton btnTile6 = new JButton("6");
		btnTile6.setBackground(Color.LIGHT_GRAY);
		btnTile6.setFont(new Font("Tahoma", Font.PLAIN, 30));
		btnTile6.setBounds(534, 248, 70, 70);
		contentPane.add(btnTile6);
		tiles[5] = btnTile6 ;
		
		JButton btnTile8 = new JButton("8");
		btnTile8.setBackground(Color.LIGHT_GRAY);
		btnTile8.setFont(new Font("Tahoma", Font.PLAIN, 30));
		btnTile8.setBounds(461, 321, 70, 70);
		contentPane.add(btnTile8);
		tiles[7] = btnTile8 ;
		
		JButton btnTile7 = new JButton("7");
		btnTile7.setBackground(Color.LIGHT_GRAY);
		btnTile7.setFont(new Font("Tahoma", Font.PLAIN, 30));
		btnTile7.setBounds(388, 321, 70, 70);
		contentPane.add(btnTile7);
		tiles[6] = btnTile7 ;
		
		JButton btnTile5 = new JButton("5");
		btnTile5.setBackground(Color.LIGHT_GRAY);
		btnTile5.setFont(new Font("Tahoma", Font.PLAIN, 30));
		btnTile5.setBounds(461, 248, 70, 70);
		contentPane.add(btnTile5);
		tiles[4] = btnTile5 ;
		
		JButton btnTile2 = new JButton("2");
		btnTile2.setBackground(Color.LIGHT_GRAY);
		btnTile2.setFont(new Font("Tahoma", Font.PLAIN, 30));
		btnTile2.setBounds(461, 175, 70, 70);
		contentPane.add(btnTile2);
		tiles[1] = btnTile2 ;
		
		JButton btnTile4 = new JButton("4");
		btnTile4.setBackground(Color.LIGHT_GRAY);
		btnTile4.setFont(new Font("Tahoma", Font.PLAIN, 30));
		btnTile4.setBounds(388, 248, 70, 70);
		contentPane.add(btnTile4);
		tiles[3] = btnTile4 ;
		
		JButton btnTile1 = new JButton("1");
		btnTile1.setBackground(Color.LIGHT_GRAY);
		btnTile1.setFont(new Font("Tahoma", Font.PLAIN, 30));
		btnTile1.setBounds(388, 175, 70, 70);
		contentPane.add(btnTile1);
		tiles[0] = btnTile1 ;
		
		JButton btnTile9 = new JButton("9");
		btnTile9.setEnabled(false);
		btnTile9.setFont(new Font("Tahoma", Font.PLAIN, 30));
		btnTile9.setBackground(Color.LIGHT_GRAY);
		btnTile9.setBounds(534, 321, 70, 70);
		contentPane.add(btnTile9);
		btnTile9.setVisible(false);
		tiles[8] = btnTile9 ;
		
		for(JButton b : tiles) b.addActionListener(this);
		
		btnRandomise = new JButton("Randomiser");
		btnRandomise.setBackground(UIManager.getColor("Button.background"));
		btnRandomise.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				randomise();
			}
		});
		btnRandomise.setBounds(49, 230, 237, 40);
		contentPane.add(btnRandomise);
		
		btnResoudre = new JButton("Resoudre");
		btnResoudre.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				solve() ;
			}
		});
		btnResoudre.setBackground(UIManager.getColor("Button.background"));
		btnResoudre.setBounds(49, 291, 237, 40);
		contentPane.add(btnResoudre);
		
		txtSolution = new JTextField();
		txtSolution.setFont(new Font("Tahoma", Font.PLAIN, 10));
		txtSolution.setEditable(false);
		txtSolution.setColumns(10);
		txtSolution.setBounds(407,21,282,20);
		contentPane.add(txtSolution) ;
		
		textTime = new JTextField();
		textTime.setEditable(false);
		textTime.setColumns(10);
		textTime.setBounds(452, 52, 237, 20);
		contentPane.add(textTime);
		
		txtExplored = new JTextField();
		txtExplored.setEditable(false);
		txtExplored.setColumns(10);
		txtExplored.setBounds(476, 83, 213, 20);
		contentPane.add(txtExplored);
		
		txtgen = new JTextField();
		txtgen.setEditable(false);
		txtgen.setColumns(10);
		txtgen.setBounds(476, 114, 213, 20);
		contentPane.add(txtgen);
		
		btnVisualisez = new JButton("Visualisez solution");
		btnVisualisez.setEnabled(false);
		btnVisualisez.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				visualize() ;
			}
		});
		btnVisualisez.setBackground(UIManager.getColor("Button.background"));
		btnVisualisez.setBounds(49, 352, 237, 40);
		contentPane.add(btnVisualisez);
		
		String[] s = {"Méthodes de résolution classiques", "Résolution par algorithme génétique", "Résolution par algorithme PSO"} ; 
		algoChoice = new JComboBox(s);
		algoChoice.setBounds(49, 191, 237, 22);
		algoChoice.addActionListener(this) ;

		contentPane.add(algoChoice);
		
		size = new JSpinner();
		size.setVisible(false);
		size.setModel(new SpinnerNumberModel(new Integer(100), new Integer(1), null, new Integer(1)));
		size.setBounds(186, 21, 79, 20);
		contentPane.add(size);
		
		iterations = new JSpinner();
		iterations.setVisible(false);
		iterations.setModel(new SpinnerNumberModel(new Integer(1000), new Integer(0), null, new Integer(1)));
		iterations.setBounds(186, 52, 79, 20);
		contentPane.add(iterations);
		
		param1 = new JSpinner();
		param1.setVisible(false);
		param1.setModel(new SpinnerNumberModel(new Float(0.5), new Float(0), null, new Float(0.1)));
		param1.setBounds(186, 83, 79, 20);
		contentPane.add(param1);
		
		param2 = new JSpinner();
		param2.setVisible(false);
		param2.setModel(new SpinnerNumberModel(new Float(0.5), new Float(0), null, new Float(0.1)));
		param2.setBounds(186, 114, 79, 20);
		contentPane.add(param2);
		
		param3 = new JSpinner();
		param3.setVisible(false);
		param3.setModel(new SpinnerNumberModel(new Float(0.5), new Float(0), null, new Float(0.1)));
		param3.setBounds(186, 145, 79, 20);
		contentPane.add(param3);
		
		lblSize = new JLabel("New label");
		lblSize.setVisible(false);
		lblSize.setBounds(10, 24, 166, 14);
		contentPane.add(lblSize);
		
		lblIterations = new JLabel("New label");
		lblIterations.setVisible(false);
		lblIterations.setBounds(10, 55, 166, 14);
		contentPane.add(lblIterations);
		
		lblParam1 = new JLabel("New label");
		lblParam1.setVisible(false);
		lblParam1.setBounds(10, 86, 166, 14);
		contentPane.add(lblParam1);
		
		lblParam2 = new JLabel("New label");
		lblParam2.setVisible(false);
		lblParam2.setBounds(10, 117, 166, 14);
		contentPane.add(lblParam2);
		
		lblParam3 = new JLabel("New label");
		lblParam3.setVisible(false);
		lblParam3.setBounds(10, 148, 166, 14);
		contentPane.add(lblParam3);
				
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		
		
		if(e.getSource() instanceof JButton) {
			JButton btn = (JButton) e.getSource() ;
			JButton blank = null ;
			for(JButton b : tiles) {
				if(b.getText().contentEquals("9")) {
					blank = b ; 
					break;
				}
			}
			if((Math.abs(blank.getBounds().x - btn.getBounds().x) == 73 && btn.getBounds().y == blank.getBounds().y) ||( Math.abs(blank.getBounds().y - btn.getBounds().y) == 73 ) && btn.getBounds().x == blank.getBounds().x) {			
				blank.setText(btn.getText());
				blank.setEnabled(true);
				btn.setText("9");
				btn.setVisible(false);
				blank.setVisible(true);
				btn.setEnabled(false);
				btnVisualisez.setEnabled(false);
				return;
			}
		}
		
		else {
			String s = (String) algoChoice.getSelectedItem() ;
			if(s.equals("Méthodes de résolution classiques")) {
				lblMetric1.setText("Nombre d'états explorés:");
				lblMetric2.setText("Nombre d'états générés:");
				
				buttonbfs.setVisible(true);
				buttondfs.setVisible(true);
				buttonmisplaced.setVisible(true);
				buttonmnahattan.setVisible(true);
				buttonrev.setVisible(true);
				seuil.setVisible(true);
				lblSeuil.setVisible(true);
				
				lblSize.setVisible(false);
				size.setVisible(false);
				
				lblIterations.setVisible(false);
				iterations.setVisible(false);
				
				lblParam1.setVisible(false);
				param1.setVisible(false);
				
				lblParam2.setVisible(false);
				param2.setVisible(false);
				
				lblParam3.setVisible(false);
				param3.setVisible(false);

			}
			else if(s.equals("Résolution par algorithme génétique")) {
				lblMetric1.setText("Nombre de generations:");
				lblMetric2.setText("Nombre de chromosome:");
				buttonbfs.setVisible(false);
				buttondfs.setVisible(false);
				buttonmisplaced.setVisible(false);
				buttonmnahattan.setVisible(false);
				buttonrev.setVisible(false);
				seuil.setVisible(false);
				lblSeuil.setVisible(false);
				
				lblSize.setText("Taille d'une génération");
				lblSize.setVisible(true);
				size.setVisible(true);
				
				lblIterations.setText("Nombre max de génération");
				lblIterations.setVisible(true);
				iterations.setVisible(true);
				
				lblParam1.setText("Taux de mutation");
				lblParam1.setVisible(true);
				param1.setVisible(true);
				
				lblParam2.setText("Taux de croisement");
				lblParam2.setVisible(true);
				param2.setVisible(true);
				
				lblParam3.setVisible(false) ;
				param3.setVisible(false);
			}
			else {
				lblMetric1.setText("Nombre d'iterations:");
				lblMetric2.setText("Nombre de particule:");
				buttonbfs.setVisible(false);
				buttondfs.setVisible(false);
				buttonmisplaced.setVisible(false);
				buttonmnahattan.setVisible(false);
				buttonrev.setVisible(false);
				seuil.setVisible(false);
				lblSeuil.setVisible(false);
				
				lblSize.setText("Taille d'une essaim");
				lblSize.setVisible(true);
				size.setVisible(true);
				
				lblIterations.setText("Nombre max d'iterations");
				lblIterations.setVisible(true);
				iterations.setVisible(true);
				
				lblParam1.setText("Coeff cognitive c1");
				lblParam1.setVisible(true);
				param1.setVisible(true);
				
				lblParam2.setText("Coeff social c2");
				lblParam2.setVisible(true);
				param2.setVisible(true);
				
				lblParam3.setText("Coeff d'inertie w");
				lblParam3.setVisible(true) ;
				param3.setVisible(true);

			}
		}
	}
	
	public void randomise() {
		String goal = "123456789" ;
		StringBuilder builder ;
		List<Character> list = new ArrayList<Character>();
		for(char c : goal.toCharArray()) {
	        list.add(c);
	    }
		
		do {
			Collections.shuffle(list);
		    builder = new StringBuilder();
		    for(char c : list) {
		        builder.append(c);
		    }			
		}while(! new Puzzle(Integer.parseInt(builder.toString())).isSolvable()) ;
		
		for(JButton b : tiles) {
			if((b.getText().contentEquals("9"))) {
				b.setEnabled(true);
				b.setVisible(true);
			}
		}
		
		for(int i = 0 ; i < 9 ; i ++) {
			
			tiles[i].setText(Character.toString(builder.charAt(i)));
			if(builder.charAt(i) == '9') {
				tiles[i].setEnabled(false);
				tiles[i].setVisible(false); 
			}
			
			btnVisualisez.setEnabled(false);
		}
		
	}
	
	public void solve() {
		StringBuilder statestr = new StringBuilder() ;
		
		for(JButton b : tiles) {
			statestr.append(b.getText());
		}
		
		String solution="" ;
		long starttime = 0 , endtime = 0 ;
		String method = (String) algoChoice.getSelectedItem() ;
		
		if(method.equals("Méthodes de résolution classiques")) {
			Node p = new Node(Integer.parseInt(statestr.toString()), "");
			Solver s = new Solver();
			
			if(buttondfs.isSelected()){
				try {
				    seuil.commitEdit();
				} catch ( java.text.ParseException e ) {  }
				int seuilvalue = (Integer) seuil.getValue();
				starttime = System.currentTimeMillis(); 
				solution = s.dFS(p,seuilvalue ) ;
				endtime = System.currentTimeMillis();
			}
			else if(buttonbfs.isSelected()) {
				starttime = System.currentTimeMillis(); 
				solution = s.bFS(p) ;
				endtime = System.currentTimeMillis();
			}
			else if(buttonmnahattan.isSelected()) {
				starttime = System.currentTimeMillis(); 
				solution = s.aStarIterative(p, new Solver.SortByManhattan()) ;
				endtime = System.currentTimeMillis();
			}
			else if(buttonmisplaced.isSelected()) {
				starttime = System.currentTimeMillis(); 
				solution = s.aStarIterative(p, new Solver.SortByMisplaced()) ;
				endtime = System.currentTimeMillis();
			}
			else {
				starttime = System.currentTimeMillis(); 
				solution = s.aStarIterative(p, new Solver.SortByManhattan_RevPenalty()) ;
				endtime = System.currentTimeMillis();
			}
			
			if(solution.contentEquals("X")) {
				JOptionPane.showMessageDialog(this, "Resolution impossible. Veuillez augmentez le seuil.", "Seuil Insuffisant", JOptionPane.ERROR_MESSAGE);
				return ;
			}
			txtExplored.setText(s.exploredCount()+"");
			txtgen.setText(s.generatedCount() +"");
			
		}
		else if (method.equals("Résolution par algorithme génétique")) {
			Chromosome.INITIAL_STATE = new Puzzle(Integer.parseInt(statestr.toString())) ;
			SolverGA s2 = new SolverGA() ;
			
			try {
			    size.commitEdit();
			    iterations.commitEdit(); 
			    param1.commitEdit();
			    param2.commitEdit();
			} catch ( java.text.ParseException e ) {  }
			s2.MAX_POP_SIZE = (Integer) size.getValue() ;
			s2.mut_rate = (Float) param1.getValue() ;
			s2.cross_rate = (Float) param2.getValue() ;
			starttime = System.currentTimeMillis(); 
			s2.initPopulation();
			solution =  s2.runEvolution( (Integer)iterations.getValue()) ;
			endtime = System.currentTimeMillis();
			
			if(solution.contentEquals("X")) {
				JOptionPane.showMessageDialog(this, "Veuillez reessayer avec d'autres paramétres.", "Solution non trouvée", JOptionPane.ERROR_MESSAGE);
				return ;
			}
			txtExplored.setText(s2.iterations +"");
			txtgen.setText(s2.pop_size +"");
			
		}
		else {
			Particle.INITIAL_STATE = new Puzzle(Integer.parseInt(statestr.toString())) ;
			SolverPSO s3 = new SolverPSO() ;
			
			try {
			    size.commitEdit();
			    iterations.commitEdit(); 
			    param1.commitEdit();
			    param2.commitEdit();
			    param3.commitEdit();
			} catch ( java.text.ParseException e ) {  }
			
			s3.c1 = (Float) param1.getValue();
			s3.c2 = (Float) param2.getValue();
			s3.w = (Float) param3.getValue() ;
			s3.MAX_SWARM_SIZE = (Integer) size.getValue() ;
			starttime = System.currentTimeMillis(); 
			s3.initSwarm();
			solution =  s3.runPSO( (Integer)iterations.getValue()) ;
			endtime = System.currentTimeMillis();
			
			if(solution.contentEquals("X")) {
				JOptionPane.showMessageDialog(this, "Veuillez reessayer avec d'autres paramétres.", "Solution non trouvée", JOptionPane.ERROR_MESSAGE);
				return ;
			}
			
			txtExplored.setText(s3.nb_iteration +"");
			txtgen.setText(s3.swarm_size +"");
		}
		
		txtSolution.setText(solution);
		textTime.setText(endtime-starttime + "ms");
		btnVisualisez.setEnabled(true);
	}
	
	public void visualize() {
		
		btnResoudre.setEnabled(false);
		btnRandomise.setEnabled(false);
		
		ExhibitonWindow w = new ExhibitonWindow(tiles) ;
		w.setVisible(true);
		w.setLocationRelativeTo(this);
		
		
		StringBuilder statestr = new StringBuilder() ;
		for(JButton b : tiles) {
			statestr.append(b.getText());
		}
		
		Puzzle p = new Puzzle(Integer.parseInt(statestr.toString()));
		String[] states = p.performMoves(txtSolution.getText());		
		
		Timer timer = new Timer(500, null);
		ActionListener taskPerformer = new ActionListener() {
			public int j = 0 ;
		      public void actionPerformed(ActionEvent evt) {
		    	  if (j>=txtSolution.getText().length()) {
		    		  try {
						Thread.sleep(1500);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
		    		  timer.stop();
		    		  w.dispose() ;
		    		  btnResoudre.setEnabled(true);
		    		  btnRandomise.setEnabled(true);
		    		  return ;
		    	  }
		    	  w.performoves(states[j]);
		    	  j++ ;
		      }		      
		};
		timer.addActionListener(taskPerformer);
		timer.setRepeats(true);
		timer.start() ;
	}
}//
