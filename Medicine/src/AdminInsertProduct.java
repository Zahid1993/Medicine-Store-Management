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


public class AdminInsertProduct extends JFrame {

	private JPanel contentPane;
	private JTextField t1;
	private JTextField t2;
	private JTextField t3;
	private JTextField t4;
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
					AdminInsertProduct frame = new AdminInsertProduct();
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
	public AdminInsertProduct() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 675, 388);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblCategory = new JLabel("Category");
		lblCategory.setForeground(new Color(128, 0, 128));
		lblCategory.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblCategory.setBounds(31, 42, 68, 14);
		contentPane.add(lblCategory);
		
		JComboBox comboBox = new JComboBox();
		comboBox.setBounds(109, 39, 206, 20);
		contentPane.add(comboBox);
		
		JLabel lblProduct = new JLabel("Product");
		lblProduct.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblProduct.setBounds(31, 85, 68, 14);
		contentPane.add(lblProduct);
		
		t1 = new JTextField();
		t1.setForeground(new Color(75, 0, 130));
		t1.setFont(new Font("Tahoma", Font.BOLD, 12));
		t1.setBounds(109, 82, 206, 20);
		contentPane.add(t1);
		t1.setColumns(10);
		
		JLabel lblCompany = new JLabel("Company");
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
		
		JLabel lblInfo = new JLabel("Info");
		lblInfo.setForeground(new Color(0, 128, 0));
		lblInfo.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblInfo.setBounds(31, 180, 46, 14);
		contentPane.add(lblInfo);
		
		t3 = new JTextField();
		t3.setFont(new Font("Tahoma", Font.BOLD, 12));
		t3.setBounds(109, 177, 206, 20);
		contentPane.add(t3);
		t3.setColumns(10);
		
		JLabel lblPrice = new JLabel("Price");
		lblPrice.setForeground(new Color(47, 79, 79));
		lblPrice.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblPrice.setBounds(31, 237, 46, 14);
		contentPane.add(lblPrice);
		
		t4 = new JTextField();
		t4.setForeground(new Color(72, 61, 139));
		t4.setFont(new Font("Tahoma", Font.BOLD, 12));
		t4.setBounds(109, 234, 86, 20);
		contentPane.add(t4);
		t4.setColumns(10);
		
		JButton btnDone = new JButton("Done");
		btnDone.setFont(new Font("Tahoma", Font.BOLD, 12));
		btnDone.setForeground(new Color(0, 0, 128));
		btnDone.setBounds(291, 265, 89, 23);
		contentPane.add(btnDone);
		
		JButton Menu = new JButton("Menu");
		Menu.setForeground(new Color(0, 0, 255));
		Menu.setFont(new Font("Tahoma", Font.BOLD, 12));
		Menu.setBounds(291, 299, 89, 23);
		contentPane.add(Menu);
		
		JLabel lblNewLabel = new JLabel("New label");
		lblNewLabel.setIcon(new ImageIcon(AdminInsertProduct.class.getResource("/Images/Insert Product.jpg")));
		lblNewLabel.setBounds(0, 0, 659, 350);
		contentPane.add(lblNewLabel);
		
	
		
		// self-written code for set values in comboBox
				try {
					String sql = "select * from category";

					con = getConnection();
					// myStat=con.createStatement();
					PreparedStatement pst = con.prepareStatement(sql);
					ResultSet myRs = pst.executeQuery(sql);
					

					while (myRs.next()) {
						 Name = myRs.getString("category_name");
						comboBox.addItem(Name);
						//System.out.println(myRs.getString("team1") + " "
						//		+ myRs.getString("team2"));

					}
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
				
				// Self-written code for defining the function of 'Done' button
				btnDone.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
						
						try {				
						//	int i = 0;
							Statement myStat=con.createStatement();

							String tmp = (String) comboBox.getSelectedItem();
							Statement myStmt = con.createStatement();
							Statement myStmt1 = con.createStatement();
							Statement myStmt2 = con.createStatement();
							String sql8,sql2, sql3,sql4,sql5, winner = null, loser = null;

							String p1 = t1.getText().trim();
							String p2 = t2.getText().trim();
							String p3 = t3.getText().trim();
							String p4 = t4.getText().trim();			
							String sql = "insert into item(product,company,info,category,price) "
									+ "values('"+p1+"','"+p2+"','"+p3+"','"+tmp+"','"+p4+"')";
							
					
							//Show message box
							int val = 0;
							if(p1.length()==0 || p2.length()==0 || p3.length()==0 || p4.length()==0) {
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
				Menu.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
						AdminMenu nw = null;    //"Info" is the next frame by clicking "Info" button
						try {
							nw = new AdminMenu();
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
