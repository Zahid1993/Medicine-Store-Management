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
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.ImageIcon;
import java.awt.Font;
import java.awt.Color;


public class AdminDeleteCategory extends JFrame {

	private JPanel contentPane;
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
					AdminDeleteCategory frame = new AdminDeleteCategory();
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
	public AdminDeleteCategory() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 675, 388);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblCategory = new JLabel("Category");
		lblCategory.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblCategory.setBounds(84, 102, 70, 14);
		contentPane.add(lblCategory);
		
		JComboBox comboBox = new JComboBox();
		comboBox.setBounds(175, 99, 170, 20);
		contentPane.add(comboBox);
		
		JButton btnDelete = new JButton("Delete");
		btnDelete.setForeground(new Color(255, 0, 0));
		btnDelete.setFont(new Font("Tahoma", Font.BOLD, 12));
		btnDelete.setBounds(378, 98, 89, 23);
		contentPane.add(btnDelete);
		
		JButton Menu = new JButton("Menu");
		Menu.setForeground(new Color(0, 0, 205));
		Menu.setFont(new Font("Tahoma", Font.BOLD, 12));
		Menu.setBounds(300, 292, 89, 23);
		contentPane.add(Menu);
		
		JLabel lblNewLabel = new JLabel("New label");
		lblNewLabel.setIcon(new ImageIcon(AdminDeleteCategory.class.getResource("/Images/Delete Category..jpg")));
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
	    btnDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				try {				
				//	int i = 0;
					
					String tmp = (String) comboBox.getSelectedItem();
				
					Statement myStmt = con.createStatement();				
					
					String sql = "delete from category where category_name = '" + tmp + "' ";
					
			
				
					//Show message box
					int val=myStmt.executeUpdate(sql);//int val1=myStmt.executeUpdate(sql3);
						if(val>=1){
						JOptionPane.showMessageDialog(null, "Deleted successfully", "SQL info", JOptionPane.INFORMATION_MESSAGE);
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
