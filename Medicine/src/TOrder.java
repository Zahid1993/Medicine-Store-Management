import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.print.PrinterException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.DefaultListModel;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;
import javax.swing.JToggleButton;
import javax.swing.JSpinner;
import javax.swing.JTextField;
import javax.swing.SpinnerNumberModel;
import javax.swing.AbstractListModel;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.ImageIcon;

import com.sun.istack.internal.logging.Logger;

import java.awt.Color;
import java.awt.Font;
import java.awt.SystemColor;

import javax.swing.JTextPane;


public class TOrder extends JFrame {

	private JPanel contentPane;
	private JTextField t;
	private PreparedStatement statement;
	Connection con;
	Statement myStat;
	ResultSet myRs;
	String Name;
	float total=0;
	int q=0;
	private DefaultTableModel model; //for jtable to add attribute
	private JTable table;
	private JTextField t1;
	private JTextField time;
	private JTextField date;
	Date datevalues;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TOrder frame = new TOrder();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	//self-written code for date and time
	public void clock()
	{
		Thread clock = new Thread()		//create a thread
		{
			public void run()
			{
				try {
					for(;;){
					Calendar cal=new GregorianCalendar();
					int day=cal.get(Calendar.DAY_OF_MONTH);
					int month=cal.get(Calendar.MONTH)+1; 
					int year=cal.get(Calendar.YEAR);
					
					int second=cal.get(Calendar.SECOND);
					int minute=cal.get(Calendar.MINUTE);
					int hour=cal.get(Calendar.HOUR);
					
					//lblClock.setText(hour+":"+minute+":"+second);
					time.setText(hour+":"+minute+":"+second);
					date.setText(day+"/"+month+"/"+year);
					
					sleep(1000);
					}
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		};
	clock.start(); //start the thread
	}
	/**
	 * Create the frame.
	 */
	public TOrder() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 675, 388);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblCategory = new JLabel("Category");
		lblCategory.setForeground(Color.MAGENTA);
		lblCategory.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblCategory.setBounds(23, 33, 63, 14);
		contentPane.add(lblCategory);
		
		JComboBox comboBox = new JComboBox();
		comboBox.setBounds(117, 31, 130, 20);
		contentPane.add(comboBox);
		
		JLabel lblProduct = new JLabel("Product");
		lblProduct.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblProduct.setBounds(23, 71, 63, 14);
		contentPane.add(lblProduct);
		
		JComboBox comboBox_1 = new JComboBox();
		comboBox_1.setBounds(117, 69, 130, 20);
		contentPane.add(comboBox_1);
		
		DefaultListModel model1 = new DefaultListModel();
		
		JLabel lblQuantity = new JLabel("Quantity");
		lblQuantity.setForeground(SystemColor.inactiveCaptionText);
		lblQuantity.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblQuantity.setBounds(23, 116, 63, 14);
		contentPane.add(lblQuantity);
		
		SpinnerNumberModel spinmodel=new SpinnerNumberModel();//self-written
		JSpinner spinner = new JSpinner(spinmodel); //argument(spinmodel)is given by self
		
		spinner.setBounds(117, 114, 29, 20);
		contentPane.add(spinner);
		
		JButton Ok = new JButton("Ok");
		Ok.setForeground(new Color(0, 128, 128));
		Ok.setFont(new Font("Tahoma", Font.BOLD, 12));
		Ok.setBounds(117, 155, 79, 23);
		contentPane.add(Ok);
		
		JButton btnCancel = new JButton("Cancel Any product");
		btnCancel.setForeground(Color.RED);
		btnCancel.setFont(new Font("Tahoma", Font.BOLD, 12));
		btnCancel.setBounds(425, 235, 177, 23);
		contentPane.add(btnCancel);
		
		JButton btnOrder = new JButton("Order");
		btnOrder.setForeground(new Color(0, 128, 0));
		btnOrder.setFont(new Font("Tahoma", Font.BOLD, 12));
		btnOrder.setBounds(354, 269, 89, 23);
		contentPane.add(btnOrder);
		
		t = new JTextField();
		t.setForeground(new Color(25, 25, 112));
		t.setFont(new Font("Tahoma", Font.BOLD, 12));
		t.setBounds(539, 204, 63, 20);
		contentPane.add(t);
		t.setColumns(10);
		
		JLabel lblTotalBill = new JLabel("Total");
		lblTotalBill.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblTotalBill.setForeground(Color.BLUE);
		lblTotalBill.setBounds(492, 207, 63, 14);
		contentPane.add(lblTotalBill);
		
		JButton btnPrint = new JButton("Print");
		btnPrint.setFont(new Font("Tahoma", Font.BOLD, 12));
		btnPrint.setBounds(565, 269, 89, 23);
		contentPane.add(btnPrint);
		
		JButton Home = new JButton("Home");
		Home.setForeground(Color.WHITE);
		Home.setFont(new Font("Comic Sans MS", Font.BOLD, 12));
		Home.setBackground(Color.BLUE);
		Home.setBounds(303, 316, 89, 23);
		contentPane.add(Home);
		
		JButton Reset = new JButton("Reset");
		Reset.setFont(new Font("Tahoma", Font.BOLD, 12));
		Reset.setBounds(466, 269, 89, 23);
		contentPane.add(Reset);
		
		table = new JTable();
		table.setFont(new Font("Tahoma", Font.BOLD, 12));
		table.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"New column", "New column", "New column"
			}
		));
		table.setBounds(415, 50, 187, 140);
		
		//self-written
		model=(DefaultTableModel) table.getModel();
		table.getColumnModel().getColumn(0).setPreferredWidth(10);  //set width of column
		table.getColumnModel().getColumn(1).setPreferredWidth(80);
		table.getColumnModel().getColumn(2).setPreferredWidth(20);
		
		contentPane.add(table);
		
		t1 = new JTextField();
		t1.setForeground(new Color(0, 0, 128));
		t1.setFont(new Font("Tahoma", Font.BOLD, 12));
		t1.setBounds(452, 204, 29, 20);
		contentPane.add(t1);
		t1.setColumns(10);
		
		JLabel lblOId = new JLabel("O. Id");
		lblOId.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblOId.setForeground(Color.DARK_GRAY);
		lblOId.setBounds(415, 207, 46, 14);
		contentPane.add(lblOId);
		
		JLabel lblQuantity_1 = new JLabel("Quantity");
		lblQuantity_1.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblQuantity_1.setBounds(415, 34, 57, 14);
		contentPane.add(lblQuantity_1);
		
		JLabel lblProduct_1 = new JLabel("Product");
		lblProduct_1.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblProduct_1.setBounds(485, 34, 46, 14);
		contentPane.add(lblProduct_1);
		
		JLabel lblPrice = new JLabel("Price");
		lblPrice.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblPrice.setBounds(558, 34, 46, 14);
		contentPane.add(lblPrice);
		
		JLabel lblClock = new JLabel("Clock");
		lblClock.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblClock.setBounds(23, 207, 46, 14);
		contentPane.add(lblClock);
		
		time = new JTextField();
		time.setFont(new Font("Tahoma", Font.BOLD, 18));
		time.setBounds(109, 202, 106, 28);
		contentPane.add(time);
		time.setColumns(10);
		
		JLabel lblDate = new JLabel("Date");
		lblDate.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblDate.setBounds(23, 250, 46, 14);
		contentPane.add(lblDate);
		
		date = new JTextField();
		date.setFont(new Font("Tahoma", Font.BOLD, 12));
		date.setBounds(109, 247, 106, 20);
		contentPane.add(date);
		date.setColumns(10);
		
		JLabel lblNewLabel = new JLabel("New label");
		lblNewLabel.setIcon(new ImageIcon(TOrder.class.getResource("/Images/Torder.jpg")));
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
	    
	    
	 // self-written code for ok button
	 		Ok.addActionListener(new ActionListener() {
	 			public void actionPerformed(ActionEvent arg0) {
	 			
	 				try{
	 					String tmpp = (String) comboBox_1.getSelectedItem();
	 					Connection connn=getConnection();
	 					ResultSet resultt;
	 					statement=connn.prepareStatement("select product,price from item where product='" + tmpp + "'");
	 			        resultt=statement.executeQuery();
	 			
	 				
	 		        
	 		        float p;
	 		        
	 				    //writing in orderlist
	 					while(resultt.next()){
	 						Number n=spinmodel.getNumber();
	 						q=n.intValue();      //q for quantity getting from spinner
	 						
	 						String product =resultt.getString("product");
	 						
	 						String price =resultt.getString("price");
	 						p = Float.parseFloat(price);
	 						System.out.println("\nPrice ="+ p);
	 						
	 						float value = p * q;
	 						System.out.println("\nValue ="+ value);
	 						model1.addElement(q+"    "+product+"    "+value);
	 						model.addRow(new Object[]{q,product,value}); //write data in jtable
	 						
	 						total= total + value;
	 					}
	 			}
	 				
	 			catch (Exception e) {
	 				// TODO Auto-generated catch block
	 				e.printStackTrace();
	 			}
	
	 			}
	 		});
	 		
	 		//self written code for "cancel any item" button action
	 		btnCancel.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					
					int index=table.getSelectedRow();
					model.removeRow(index);
					btnCancel.setEnabled(true);
				}
			});
	 		
	 		//self written code for "order" button action
	 		btnOrder.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
				
					float sum=0;
					for(int l=0;l<model.getRowCount();l++)
					{
						sum = sum + Float.parseFloat(model.getValueAt(l, 2).toString());
					}
					
					 System.out.println("Sum ="+sum);
					 String tsum=Float.toString(sum);
					 t.setText(tsum);
					 
					 String date_value=date.getText();
					 
					 /*
					 SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yy");
					 try {
						datevalues = sdf.parse(date_value);
					} catch (ParseException e2) {
						// TODO Auto-generated catch block
						e2.printStackTrace();
					}*/
					
					 
					 try{
						 Statement myStmt = con.createStatement();
						 
						 String sql1 = "select count(id) from bill;";
						 
							con = getConnection();
							myStat=con.createStatement();
							myRs=myStat.executeQuery(sql1);
							int i=0;
							while(myRs.next()){
								 i=myRs.getInt("count(id)");  // t= number of team
								System.out.println("Order id ="+i);	
								i=i+1;						
								String s=Integer.toString(i);
								t1.setText(s);								
					 }
							
							String sql = "insert into bill(id,date,bill) "
								+ "values('"+i+"','"+date_value+"','"+sum+"')";
						    myStmt.executeUpdate(sql);
							
							
					
				}
					 catch (Exception e1) {
							System.out.println(e1);
						}
					 
			
					
				}
			});
	 		
	 		//self written code for "reset" button action
	 		Reset.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					 model = (DefaultTableModel) table.getModel();
					 while(model.getRowCount()>0)
					 {
					 for(int l=0;l<model.getRowCount();l++)
					 model.removeRow(l);}
				   	 t.setText(null);
				   	t1.setText(null);
				}
			});
	 		
	 		
	 		
	 		//self written code for "Print" button action

	 		
		 		
		 		btnPrint.addActionListener(new ActionListener() {
		 			public void actionPerformed(ActionEvent arg0) {
						
						Boolean complete = null;
						try {
							complete = table.print();
						} catch (PrinterException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						if(complete)
						{
							JOptionPane.showMessageDialog(null, "Done Printing ...");
						}
						
						else{
							JOptionPane.showMessageDialog(null, "Printing ...");
						}
						
					}
				});
	 		
										
				
			
	 		
	 		 //Self-written code for defining the function of 'Home' button
			Home.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					Select nw = null;    //"Info" is the next frame by clicking "Info" button
					try {
						nw = new Select();
					} catch (Exception e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					nw.setVisible(true);
					dispose();       //get exited from current frame
				}
			});
			
			//calling clock class
			clock();
	 		
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
