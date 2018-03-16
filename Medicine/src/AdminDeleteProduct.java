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


public class AdminDeleteProduct extends JFrame {

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
					AdminDeleteProduct frame = new AdminDeleteProduct();
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
	public AdminDeleteProduct() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 675, 388);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblCategory = new JLabel("Category");
		lblCategory.setForeground(new Color(25, 25, 112));
		lblCategory.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblCategory.setBounds(10, 75, 70, 14);
		contentPane.add(lblCategory);
		
		JComboBox comboBox = new JComboBox();
		comboBox.setBounds(77, 72, 230, 20);
		contentPane.add(comboBox);
		
		JLabel lblProduct = new JLabel("Product");
		lblProduct.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblProduct.setBounds(10, 130, 57, 14);
		contentPane.add(lblProduct);
		
		JComboBox comboBox_1 = new JComboBox();
		comboBox_1.setBounds(77, 127, 230, 20);
		contentPane.add(comboBox_1);
		
		JButton btnDelete = new JButton("Delete");
		btnDelete.setForeground(new Color(255, 0, 0));
		btnDelete.setFont(new Font("Tahoma", Font.BOLD, 12));
		btnDelete.setBounds(144, 177, 89, 23);
		contentPane.add(btnDelete);
		
		JButton Menu = new JButton("Menu");
		Menu.setForeground(new Color(0, 0, 205));
		Menu.setFont(new Font("Tahoma", Font.BOLD, 12));
		Menu.setBounds(300, 282, 89, 23);
		contentPane.add(Menu);
		
		JLabel lblNewLabel = new JLabel("New label");
		lblNewLabel.setIcon(new ImageIcon(AdminDeleteProduct.class.getResource("/Images/Delete Product.jpg")));
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
			
		
		// self-written code for comboBox to show value in combobox_1
	    comboBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				comboBox_1.removeAllItems();
				
				try {
					int i = 0;
					String tmp = (String) comboBox.getSelectedItem();
					String sql1 = "select product from item where category = ?";
					
					PreparedStatement pst = con.prepareStatement(sql1);
					pst.setString(1, tmp);
					// pst.setInt(2,i);
					myRs = pst.executeQuery();

					while (myRs.next()) {
						String add = myRs.getString("product");
						System.out.println(add);
						comboBox_1.addItem(add);
					}
				}

				catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}

			}
		});
	    
	    
		// Self-written code for defining the function of 'Done' button
	    btnDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				try {				
				//	int i = 0;
					
					String tmp = (String) comboBox_1.getSelectedItem();
				
					Statement myStmt = con.createStatement();				
					
					String sql = "delete from item where product = '" + tmp + "' ";
					
			
				
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
