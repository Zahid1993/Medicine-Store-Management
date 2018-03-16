import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.ImageIcon;

import java.awt.Font;
import java.awt.Color;


public class SignUp extends JFrame {

	private JPanel contentPane;
	private JTextField t1;
	private JTextField t2;
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
					SignUp frame = new SignUp();
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
	public SignUp() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 675, 388);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblProduct = new JLabel("User name");
		lblProduct.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblProduct.setBounds(31, 85, 68, 14);
		contentPane.add(lblProduct);
		
		t1 = new JTextField();
		t1.setForeground(new Color(75, 0, 130));
		t1.setFont(new Font("Tahoma", Font.BOLD, 12));
		t1.setBounds(109, 82, 206, 20);
		contentPane.add(t1);
		t1.setColumns(10);
		
		JLabel lblCompany = new JLabel("Password");
		lblCompany.setForeground(new Color(128, 0, 128));
		lblCompany.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblCompany.setBounds(31, 134, 68, 14);
		contentPane.add(lblCompany);
		
		t2 = new JTextField();
		t2.setForeground(new Color(184, 134, 11));
		t2.setFont(new Font("Tahoma", Font.BOLD, 12));
		t2.setBounds(109, 131, 206, 20);
		contentPane.add(t2);
		t2.setColumns(10);
		
		JButton btnDone = new JButton("Done");
		btnDone.setFont(new Font("Tahoma", Font.BOLD, 12));
		btnDone.setForeground(new Color(0, 0, 128));
		btnDone.setBounds(291, 265, 89, 23);
		contentPane.add(btnDone);
		
		JButton Login = new JButton("Login");
		Login.setForeground(new Color(0, 0, 255));
		Login.setFont(new Font("Tahoma", Font.BOLD, 12));
		Login.setBounds(291, 299, 89, 23);
		contentPane.add(Login);
		
		JLabel lblNewLabel = new JLabel("New label");
		lblNewLabel.setIcon(new ImageIcon(AdminInsertProduct.class.getResource("/Images/Insert Product.jpg")));
		lblNewLabel.setBounds(0, 0, 659, 350);
		contentPane.add(lblNewLabel);
		
				// Self-written code for defining the function of 'Done' button
				btnDone.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
						
						try {				
						//	int i = 0;
							
							con = getConnection();
							// myStat=con.createStatement();
						//	PreparedStatement pst = con.prepareStatement(sql);
						//	ResultSet myRs = pst.executeQuery(sql);
							Statement myStat=con.createStatement();
							
							
							

							Statement myStmt = con.createStatement();
							Statement myStmt1 = con.createStatement();
							Statement myStmt2 = con.createStatement();
							String sql8,sql2, sql3,sql4,sql5, winner = null, loser = null;

							String p1 = t1.getText().trim();
							String p2 = t2.getText().trim();
							
							//if(p1.length()==0 || p2.length()==0) {
							//	JOptionPane.showMessageDialog(null, "Fields missing..", "Input error", JOptionPane.ERROR_MESSAGE);	
							//}
			
						//	String sql = "insert into mlogin values('"+p1+"','"+p2+"')";
							String sql = "insert into mlogin(username,password) "
									+ "values('"+p1+"','"+p2+"')";
							
					
							//Show message box
							int val = 0;
							if(p1.length()==0 || p2.length()==0) {
									JOptionPane.showMessageDialog(null, "Fields missing..", "Input error", JOptionPane.ERROR_MESSAGE);	
								}
							else
							{
								 val=myStmt.executeUpdate(sql);
							}
							
							if(val>=1) {
								JOptionPane.showMessageDialog(null, "Added successfully", "SQL info", JOptionPane.INFORMATION_MESSAGE);
							
							}
								
							
						}

						catch (Exception e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}


					}
				});
				
				
				 //Self-written code for defining the function of 'Menu' button
				Login.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
						MLogin nw = null;    //"Info" is the next frame by clicking "Info" button
						try {
							nw = new MLogin();
						} catch (Exception e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
						nw.setVisible(true);
						dispose();       //get exited from current frame
					}
				});
				
				
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
		// System.out.println("Connected");
		return conn;

	} catch (Exception e) {
		System.out.println(e);
	}
	return null;
}
}
