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
import javax.swing.JTable;
import javax.swing.JLabel;
import javax.swing.table.DefaultTableModel;
import javax.swing.JButton;

import java.awt.Font;

import javax.swing.ImageIcon;

import java.awt.Color;

import javax.swing.JComboBox;

import com.sun.org.apache.xalan.internal.xsltc.compiler.util.CompareGenerator;


public class AdminOrderList extends JFrame {

	private JPanel contentPane;
	private JTable table;
	private DefaultTableModel model; //for jtable to add attribute
	private PreparedStatement statement;
	Connection con;
	Statement myStat;
	ResultSet myRs;
	String Name;
	String Name1;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AdminOrderList frame = new AdminOrderList();
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
	public AdminOrderList() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 675, 388);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		table = new JTable();
		table.setForeground(Color.GRAY);
		table.setFont(new Font("Tahoma", Font.BOLD, 11));
		table.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"New column", "New column"
			}
		));
		table.setBounds(443, 53, 109, 185);
		contentPane.add(table);
		
		//self-written for jtable
				model = (DefaultTableModel) table.getModel();
				table.getColumnModel().getColumn(0).setPreferredWidth(40);  //set width of column
				table.getColumnModel().getColumn(1).setPreferredWidth(80);
		
		JLabel lblOrderId = new JLabel("Id");
		lblOrderId.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblOrderId.setBounds(443, 28, 46, 14);
		contentPane.add(lblOrderId);
		
		JLabel lblBill = new JLabel("Bill");
		lblBill.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblBill.setBounds(510, 28, 89, 14);
		contentPane.add(lblBill);
		
		JButton Menu = new JButton("Menu");
		Menu.setForeground(Color.BLUE);
		Menu.setFont(new Font("Tahoma", Font.BOLD, 12));
		Menu.setBounds(298, 296, 89, 23);
		contentPane.add(Menu);
		
		JComboBox comboBox = new JComboBox();
		comboBox.setBounds(133, 26, 189, 20);
		contentPane.add(comboBox);
		JButton Show = new JButton("Show");
		Show.setFont(new Font("Tahoma", Font.BOLD, 12));
		
		Show.setBounds(133, 67, 89, 23);
		contentPane.add(Show);
		
		JButton Clear = new JButton("Clear");
		Clear.setForeground(new Color(255, 0, 0));
		Clear.setFont(new Font("Tahoma", Font.BOLD, 12));
		Clear.setBounds(232, 67, 89, 23);
		contentPane.add(Clear);
		
		JLabel lblDates = new JLabel("Dates");
		lblDates.setForeground(new Color(0, 128, 128));
		lblDates.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblDates.setBounds(32, 29, 46, 14);
		contentPane.add(lblDates);
		
		JLabel lblNewLabel = new JLabel("New label");
		lblNewLabel.setIcon(new ImageIcon(AdminOrderList.class.getResource("/Images/Torder.jpg")));
		lblNewLabel.setBounds(0, 0, 671, 350);
		contentPane.add(lblNewLabel);
		
		
				
				// self-written code for set values in comboBox
				try {
					String sql = "select distinct date from bill";

					con = getConnection();
					// myStat=con.createStatement();
					PreparedStatement pst = con.prepareStatement(sql);
					ResultSet myRs = pst.executeQuery(sql);
					

					while (myRs.next()) {
						 Name = myRs.getString("date");	
						 comboBox.addItem(Name);
					}
						
				} 
				catch (Exception e1) {
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
							
							int ids;
							String bills=null;
							String info =null;
							String sql2 = "select * from bill where date = '" + tmp + "'";
							
							
							PreparedStatement p = con.prepareStatement(sql2);
							ResultSet my = p.executeQuery(sql2);
							
							while(my.next()){
							ids=my.getInt("id");
							bills=my.getString("bill");
						
							System.out.println(my.getString("id")+" "+my.getString("bill"));
							
							model.addRow(new Object[]{ids,bills}); //write data in jtable
								
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
