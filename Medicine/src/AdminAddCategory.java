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
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.ImageIcon;
import java.awt.Font;
import java.awt.Color;


public class AdminAddCategory extends JFrame {

	private JPanel contentPane;
	private JTextField t1;
	private PreparedStatement statement;
	Connection con;
	Statement myStat;
	ResultSet myRs;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AdminAddCategory frame = new AdminAddCategory();
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
	public AdminAddCategory() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 675, 388);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblCategory = new JLabel("Category");
		lblCategory.setForeground(Color.MAGENTA);
		lblCategory.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblCategory.setBounds(178, 168, 58, 14);
		contentPane.add(lblCategory);
		
		t1 = new JTextField();
		t1.setFont(new Font("Tahoma", Font.BOLD, 12));
		t1.setBounds(271, 165, 160, 20);
		contentPane.add(t1);
		t1.setColumns(10);
		
		JButton btnAdd = new JButton("Add");
		btnAdd.setFont(new Font("Tahoma", Font.BOLD, 12));
		btnAdd.setBounds(467, 164, 89, 23);
		contentPane.add(btnAdd);
		
		JButton Menu = new JButton("Menu");
		Menu.setForeground(Color.BLUE);
		Menu.setFont(new Font("Tahoma", Font.BOLD, 12));
		Menu.setBounds(312, 305, 89, 23);
		contentPane.add(Menu);
		
		JLabel lblNewLabel = new JLabel("New label");
		lblNewLabel.setIcon(new ImageIcon(AdminAddCategory.class.getResource("/Images/Add Category.jpg")));
		lblNewLabel.setBounds(0, 0, 659, 350);
		contentPane.add(lblNewLabel);
		
		// Self-written code for defining the function of 'Add' button
		btnAdd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				try {				
					con = getConnection();
					Statement myStmt = con.createStatement();
					

					String p1 = t1.getText().trim();
					
					
					String sql = "insert into category(category_name) "
							+ "values('"+p1+"')";
						
					//Show message box
					int val = 0;
					if(p1.length()==0) {
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
