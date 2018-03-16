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
import java.awt.Font;
import javax.swing.ImageIcon;
import java.awt.Color;


public class AdminUpdateCategory extends JFrame {

	private JPanel contentPane;
	private JTextField t1;
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
					AdminUpdateCategory frame = new AdminUpdateCategory();
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
	public AdminUpdateCategory() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 675, 388);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblCategory = new JLabel("Category");
		lblCategory.setForeground(new Color(128, 128, 0));
		lblCategory.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblCategory.setBounds(335, 58, 68, 14);
		contentPane.add(lblCategory);
		
		JComboBox comboBox = new JComboBox();
		comboBox.setBounds(443, 55, 206, 20);
		contentPane.add(comboBox);
		
		JLabel lblInfo = new JLabel("Name");
		lblInfo.setForeground(new Color(128, 0, 0));
		lblInfo.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblInfo.setBounds(335, 119, 46, 14);
		contentPane.add(lblInfo);
		
		t1 = new JTextField();
		t1.setForeground(new Color(139, 0, 0));
		t1.setFont(new Font("Tahoma", Font.BOLD, 12));
		t1.setBounds(443, 115, 206, 20);
		contentPane.add(t1);
		t1.setColumns(10);
		
		JButton btnDone = new JButton("Done");
		btnDone.setFont(new Font("Tahoma", Font.BOLD, 12));
		btnDone.setBounds(509, 164, 89, 23);
		contentPane.add(btnDone);
		
		JButton Menu = new JButton("Menu");
		Menu.setForeground(new Color(25, 25, 112));
		Menu.setFont(new Font("Tahoma", Font.BOLD, 12));
		Menu.setBounds(314, 300, 89, 23);
		contentPane.add(Menu);
		
		JLabel lblNewLabel = new JLabel("New label");
		lblNewLabel.setIcon(new ImageIcon(AdminUpdateCategory.class.getResource("/Images/Update category.jpg")));
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
				
					Statement myStmt = con.createStatement();	
					String tmp = (String) comboBox.getSelectedItem();
					//int sn = tmp.length()
					//if(nol.length()==0)

					String p1 = t1.getText().trim();
					
					
					String sql1=null,sql2=null;
					if(p1.length()!=0)
					{
						sql1 = "update category set category_name= '" + p1
								+ "'   where category_name= '" + tmp + "'";
					}
					else{
						JOptionPane.showMessageDialog(null, "Fields missing..Insert name", "Input error", JOptionPane.ERROR_MESSAGE);
					}
					
					
	
					//Show message box
					int val=myStmt.executeUpdate(sql1);
	
						if(val>=1)
						{
						JOptionPane.showMessageDialog(null, "Updated successfully", "SQL info", JOptionPane.INFORMATION_MESSAGE);
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
