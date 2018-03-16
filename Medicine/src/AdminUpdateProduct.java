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


public class AdminUpdateProduct extends JFrame {

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
					AdminUpdateProduct frame = new AdminUpdateProduct();
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
	public AdminUpdateProduct() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 675, 388);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblCategory = new JLabel("Category");
		lblCategory.setForeground(new Color(0, 128, 128));
		lblCategory.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblCategory.setBounds(48, 52, 68, 14);
		contentPane.add(lblCategory);
		
		JComboBox comboBox = new JComboBox();
		comboBox.setBounds(110, 49, 206, 20);
		contentPane.add(comboBox);
		
		JLabel lblProduct = new JLabel("Product");
		lblProduct.setForeground(new Color(95, 158, 160));
		lblProduct.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblProduct.setBounds(48, 107, 68, 14);
		contentPane.add(lblProduct);
		
		JLabel lblInfo = new JLabel("Info");
		lblInfo.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblInfo.setBounds(48, 159, 46, 14);
		contentPane.add(lblInfo);
		
		t1 = new JTextField();
		t1.setBounds(110, 156, 206, 20);
		contentPane.add(t1);
		t1.setColumns(10);
		
		JLabel lblPrice = new JLabel("Price");
		lblPrice.setForeground(new Color(72, 61, 139));
		lblPrice.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblPrice.setBounds(48, 237, 46, 14);
		contentPane.add(lblPrice);
		
		t2 = new JTextField();
		t2.setForeground(new Color(165, 42, 42));
		t2.setFont(new Font("Tahoma", Font.BOLD, 12));
		t2.setBounds(110, 234, 86, 20);
		contentPane.add(t2);
		t2.setColumns(10);
		
		JButton btnDone = new JButton("Done");
		btnDone.setFont(new Font("Tahoma", Font.BOLD, 12));
		btnDone.setForeground(new Color(0, 0, 128));
		btnDone.setBounds(318, 275, 89, 23);
		contentPane.add(btnDone);
		
		JButton Menu = new JButton("Menu");
		Menu.setForeground(new Color(0, 0, 255));
		Menu.setFont(new Font("Comic Sans MS", Font.PLAIN, 12));
		Menu.setBounds(318, 309, 89, 23);
		contentPane.add(Menu);
		
		JComboBox comboBox_1 = new JComboBox();
		comboBox_1.setBounds(110, 104, 206, 20);
		contentPane.add(comboBox_1);
		
		JLabel lblNewLabel = new JLabel("New label");
		lblNewLabel.setIcon(new ImageIcon(AdminUpdateProduct.class.getResource("/Images/Update product.jpg")));
		lblNewLabel.setBounds(0, 0, 671, 350);
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
		btnDone.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				try {				
				//	int i = 0;
					Statement myStat=con.createStatement();
				
					String tmp = (String) comboBox_1.getSelectedItem();
			
					Statement myStmt = con.createStatement();	

					String p1 = t1.getText().trim();
					String p2 = t2.getText().trim();
					
					
					String sql1=null,sql2=null;

					if(p1.length()!=0 || p2.length()!=0)
					{
						sql1 = "update item set info = '" + p1
								+ "'   where product= '" + tmp + "'";
						
						sql2 = "update item set price = '" + p2
								+ "'   where product= '" + tmp + "'";
					}
					else{
						JOptionPane.showMessageDialog(null, "Fields missing...", "Input error", JOptionPane.ERROR_MESSAGE);
					}
					//Show message box
					int val=myStmt.executeUpdate(sql1);
					int val1=myStmt.executeUpdate(sql2);
					if(val>=1&&val1>=1)
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
