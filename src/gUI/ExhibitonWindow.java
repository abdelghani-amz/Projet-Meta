package gUI;


import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JButton;
import java.awt.Color;
import java.awt.Font;
import javax.swing.UIManager;

public class ExhibitonWindow extends JFrame {

	private JPanel contentPane;
	private JButton[] tiles ;

	/**
	 * Launch the application.
	 */
	/*
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ExhibitonWindow frame = new ExhibitonWindow();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
*/
	/**
	 * Create the frame.
	 */
	public ExhibitonWindow(JButton[] stateinit) {
		setForeground(Color.CYAN);
		setBackground(Color.WHITE);
		setUndecorated(true);
		//setUndecorated(true);
		setTitle("Visualisation");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 237, 239);
		contentPane = new JPanel();
		contentPane.setBackground(Color.DARK_GRAY);
		contentPane.setBorder(UIManager.getBorder("Table.focusCellHighlightBorder"));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		tiles = new JButton[9] ;
		
		JButton btnTile1 = new JButton("1");
		btnTile1.setFont(new Font("Tahoma", Font.PLAIN, 30));
		btnTile1.setBackground(Color.LIGHT_GRAY);
		btnTile1.setBounds(10, 11, 70, 70);
		contentPane.add(btnTile1);
		tiles[0] = btnTile1 ;
		
		JButton btnTile2 = new JButton("2");
		btnTile2.setFont(new Font("Tahoma", Font.PLAIN, 30));
		btnTile2.setBackground(Color.LIGHT_GRAY);
		btnTile2.setBounds(83, 11, 70, 70);
		contentPane.add(btnTile2);
		tiles[1] = btnTile2 ;
		
		JButton btnTile5 = new JButton("5");
		btnTile5.setFont(new Font("Tahoma", Font.PLAIN, 30));
		btnTile5.setBackground(Color.LIGHT_GRAY);
		btnTile5.setBounds(83, 83, 70, 70);
		contentPane.add(btnTile5);
		tiles[4] = btnTile5 ;
		
		JButton btnTile4 = new JButton("4");
		btnTile4.setFont(new Font("Tahoma", Font.PLAIN, 30));
		btnTile4.setBackground(Color.LIGHT_GRAY);
		btnTile4.setBounds(10, 83, 70, 70);
		contentPane.add(btnTile4);
		tiles[3] = btnTile4 ;
		
		JButton btnTile6 = new JButton("6");
		btnTile6.setFont(new Font("Tahoma", Font.PLAIN, 30));
		btnTile6.setBackground(Color.LIGHT_GRAY);
		btnTile6.setBounds(156, 83, 70, 70);
		contentPane.add(btnTile6);
		tiles[5] = btnTile6 ;
		
		JButton btnTile3 = new JButton("3");
		btnTile3.setFont(new Font("Tahoma", Font.PLAIN, 30));
		btnTile3.setBackground(Color.LIGHT_GRAY);
		btnTile3.setBounds(156, 11, 70, 70);
		contentPane.add(btnTile3);
		tiles[2] = btnTile3 ;
		
		JButton btnTile7 = new JButton("7");
		btnTile7.setFont(new Font("Tahoma", Font.PLAIN, 30));
		btnTile7.setBackground(Color.LIGHT_GRAY);
		btnTile7.setBounds(10, 156, 70, 70);
		contentPane.add(btnTile7);
		tiles[6] = btnTile7 ;
		
		JButton btnTile8 = new JButton("8");
		btnTile8.setFont(new Font("Tahoma", Font.PLAIN, 30));
		btnTile8.setBackground(Color.LIGHT_GRAY);
		btnTile8.setBounds(83, 156, 70, 70);
		contentPane.add(btnTile8);
		tiles[7] = btnTile8 ;
		
		JButton btnTile9 = new JButton("9");
		btnTile9.setFont(new Font("Tahoma", Font.PLAIN, 30));
		btnTile9.setBackground(Color.LIGHT_GRAY);
		btnTile9.setBounds(156, 156, 70, 70);
		contentPane.add(btnTile9);
		tiles[8] = btnTile9 ;
		
		for(int i = 0 ; i<9 ; i++) {
			tiles[i].setText(stateinit[i].getText());
			if(stateinit[i].getText().contentEquals("9")) {
				tiles[i].setVisible(false); 
			}
		}
	}
	
	public void performoves(String state) {

		for( int i=0 ; i<9; i++) {
			if (tiles[i].getText().contentEquals("9")) tiles[i].setVisible(true);
			
			tiles[i].setText(state.charAt(i)+"");
			if(tiles[i].getText().contentEquals("9")) {
				tiles[i].setVisible(false); 
			}
			
		}
	}

}
