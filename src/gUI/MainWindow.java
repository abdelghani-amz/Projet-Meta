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


import backEnd.Puzzle ;
import backEnd.Solver;
import backEnd.Node;
import javax.swing.UIManager;
import javax.swing.JTextField;
import javax.swing.Timer ;

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
		buttondfs.setSelected(true);
		moderesol.add(buttondfs);
		buttondfs.setBounds(6, 20, 176, 23);
		contentPane.add(buttondfs);
		
		buttonbfs = new JRadioButton("Recherche en largeur");
		moderesol.add(buttonbfs);
		buttonbfs.setBounds(6, 46, 195, 23);
		contentPane.add(buttonbfs);
		
		buttonmisplaced = new JRadioButton("A* Distance de Hamming");
		moderesol.add(buttonmisplaced);
		buttonmisplaced.setBounds(6, 72, 170, 23);
		contentPane.add(buttonmisplaced);
		
		buttonmnahattan = new JRadioButton("A* Distance de Manhattan");
		moderesol.add(buttonmnahattan);
		buttonmnahattan.setBounds(6, 98, 195, 23);
		contentPane.add(buttonmnahattan);
		
		buttonrev = new JRadioButton("A* Distance de Manhattan + renversement ");
		moderesol.add(buttonrev);
		buttonrev.setBounds(6, 124, 280, 23);
		contentPane.add(buttonrev);
		
		JLabel lblNewLabel = new JLabel("Solution: ");
		lblNewLabel.setBounds(307, 24, 90, 14);
		contentPane.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Temps d'execution: ");
		lblNewLabel_1.setBounds(307, 55, 133, 14);
		contentPane.add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("Nombre d'\u00E9tats explor\u00E9s:");
		lblNewLabel_2.setBounds(307, 86, 170, 14);
		contentPane.add(lblNewLabel_2);
		
		JLabel lblNewLabel_3 = new JLabel("Nombre d'\u00E9tats g\u00E9n\u00E9r\u00E9s:");
		lblNewLabel_3.setBounds(307, 117, 166, 14);
		contentPane.add(lblNewLabel_3);
		
		seuil = new JSpinner();
		seuil.setModel(new SpinnerNumberModel(new Integer(31), new Integer(0), null, new Integer(1)));
		seuil.setBounds(188, 21, 44, 20);
		contentPane.add(seuil);
		
		JLabel lblSeuil = new JLabel("Seuil");
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
		btnRandomise.setBounds(49, 187, 237, 40);
		contentPane.add(btnRandomise);
		
		btnResoudre = new JButton("Resoudre");
		btnResoudre.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				solve() ;
			}
		});
		btnResoudre.setBackground(UIManager.getColor("Button.background"));
		btnResoudre.setBounds(49, 248, 237, 40);
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
		btnVisualisez.setBounds(49, 309, 237, 40);
		contentPane.add(btnVisualisez);
				
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
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
		
		Node p = new Node(Integer.parseInt(statestr.toString()), "");
		Solver s = new Solver();
		String solution="" ;
		long starttime , endtime;
		
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
		
		txtSolution.setText(solution);
		txtExplored.setText(s.exploredCount()+"");
		txtgen.setText(s.generatedCount() +"");
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
}
