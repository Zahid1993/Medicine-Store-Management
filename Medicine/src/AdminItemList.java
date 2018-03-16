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
import javax.swing.table.DefaultTableModel;
import javax.swing.JLabel;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JTable;
import javax.swing.ImageIcon;
import java.awt.Font;
import java.awt.Color;


public class AdminItemList extends JFrame {

	private JPanel contentPane;
	private PreparedStatement statement;
	private DefaultTableModel model; //jtable
	Connection con;
	Statement myStat;
	ResultSet myRs;
	String Name;
	private JTable table;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AdminItemList frame = new AdminItemList();
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
	public AdminItemList() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 675, 388);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblCategory = new JLabel("Category");
		lblCategory.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblCategory.setBounds(43, 36, 68, 14);
		contentPane.add(lblCategory);
		
		JComboBox comboBox = new JComboBox();
		comboBox.setBounds(121, 33, 206, 20);
		contentPane.add(comboBox);
		
		JButton Menu = new JButton("Menu");
		Menu.setBounds(291, 316, 89, 23);
		contentPane.add(Menu);
		
		JButton Show = new JButton("Show");
		Show.setForeground(new Color(0, 128, 128));
		Show.setFont(new Font("Tahoma", Font.BOLD, 12));
		Show.setBounds(171, 75, 89, 23);
		contentPane.add(Show);
		
		JButton Clear = new JButton("Clear");
		Clear.setForeground(new Color(255, 0, 0));
		Clear.setFont(new Font("Tahoma", Font.BOLD, 12));
		Clear.setBounds(171, 117, 89, 23);
		contentPane.add(Clear);
		
		table = new JTable();
		table.setFont(new Font("Tahoma", Font.PLAIN, 12));
		table.setForeground(new Color(0, 0, 0));
		table.setBackground(new Color(220, 220, 220));
		table.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"New column", "New column", "New column"
			}
		));
		
		
		//self-written for jtable
		model = (DefaultTableModel) table.getModel();
		table.getColumnModel().getColumn(0).setPreferredWidth(40);  //set width of column
		table.getColumnModel().getColumn(1).setPreferredWidth(80);
		table.getColumnModel().getColumn(2).setPreferredWidth(30);
		
		table.setRowHeight(14);										//set height of row
		table.setBounds(367, 49, 257, 236);
		contentPane.add(table);
		
		JLabel lblProduct = new JLabel("Product");
		lblProduct.setForeground(new Color(72, 61, 139));
		lblProduct.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblProduct.setBounds(367, 25, 46, 14);
		contentPane.add(lblProduct);
		
		JLabel lblCategory_1 = new JLabel("Category");
		lblCategory_1.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblCategory_1.setBounds(463, 25, 56, 14);
		contentPane.add(lblCategory_1);
		
		JLabel lblInfo = new JLabel("Info");
		lblInfo.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblInfo.setBounds(578, 25, 46, 14);
		contentPane.add(lblInfo);
		
		JLabel label = new JLabel("New label");
		label.setIcon(new ImageIcon(AdminItemList.class.getResource("/Images/Item list.jpg")));
		label.setBounds(0, 0, 659, 350);
		contentPane.add(label);

	
		
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
				
				
				// self-written code for action listener for 'Show' button
			    Show.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						
						try
						{
							int id,k;
							
							String tmp = (String) comboBox.getSelectedItem();
							System.out.println("Category:" + tmp);
							
							//Set the values in Jtable
							
							String product=null;
							String p_category=null;
							String info =null;
							String sql2 = "select * from item where category = '" + tmp + "'";
							
							
							PreparedStatement p = con.prepareStatement(sql2);
							ResultSet my = p.executeQuery(sql2);
							
							while(my.next()){
							product=my.getString("product");
							p_category=my.getString("category");
							info=my.getString("info");
							System.out.println(my.getString("product")+" "+my.getString("category")+" "+my.getString("info") );
							
							model.addRow(new Object[]{product,p_category,info}); //write data in jtable
								
						}
							
							
						}
									
					catch (Exception e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}

					}
				});
			    
			    
			 // self-written code for action listener for "Clear" button
				Clear.addActionListener(new ActionListener() {
					 public void actionPerformed(ActionEvent arg0) {
						 
						 model = (DefaultTableModel) table.getModel();
						 while(model.getRowCount()>0)
						 {
						 for(int l=0;l<model.getRowCount();l++)
						 model.removeRow(l);}
						 
						
						 
						 
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
