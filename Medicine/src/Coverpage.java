import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JButton;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Font;

import javax.swing.JTable;

import java.awt.Color;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.swing.ImageIcon;



public class Coverpage {

	private JFrame frame;
	private JTable table;
	private PreparedStatement statement;
	Connection con;
	Statement myStat;
	ResultSet myRs;
	String Name;


	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Coverpage window = new Coverpage();
					
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 * @throws Exception 
	 */
	public Coverpage() throws Exception {
		
		//self-written code
		Database c=new Database(); //calling database
									//DB c1=new DB();
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.getContentPane().setFont(new Font("Calibri", Font.BOLD, 12));
		frame.setBounds(100, 100, 651, 388);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		table = new JTable();
		table.setBounds(93, 120, 85, -10);
		frame.getContentPane().add(table);
		
		JButton btnNewButton = new JButton("Next");
		btnNewButton.setBackground(Color.ORANGE);
		
try{
			

		}
		catch(Exception e){
			System.out.println(e);
		}
		
		//Self-written code for defining the function of 'next' button
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Select nw = null;    //"Login" is the next frame by clicking "next" button
				try {
					nw = new Select();
					
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				nw.setVisible(true);
				frame.dispose();		//get exited from current frame
				
			}
		});
		btnNewButton.setBounds(285, 316, 89, 23);
		frame.getContentPane().add(btnNewButton);
		
		JLabel lblNewLabel = new JLabel("New label");
		lblNewLabel.setIcon(new ImageIcon(Coverpage.class.getResource("/Images/Coverpage.jpg")));
		lblNewLabel.setBounds(0, 0, 635, 360);
		frame.getContentPane().add(lblNewLabel);
		frame.setVisible(true);
	}
	
	public static Connection getConnection() throws Exception {

		try {

			String driver = "com.mysql.jdbc.Driver";
			String url = "jdbc:mysql://127.0.0.1:3306/medicine";
			String username = "root";
			String password = "root";
			Class.forName(driver);

			Connection conn = DriverManager.getConnection(url, username,
					password);
			 System.out.println("Connected");
			return conn;

		} catch (Exception e) {
			System.out.println(e);
		}
		return null;
	}
}
