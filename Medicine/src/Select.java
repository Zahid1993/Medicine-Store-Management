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
import javax.swing.JButton;
import java.awt.Font;
import javax.swing.JLabel;
import javax.swing.ImageIcon;


public class Select extends JFrame {

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
					Select frame = new Select();
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
	public Select() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 675, 388);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JButton btnAdmin = new JButton("Admin");
		btnAdmin.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 14));
		btnAdmin.setBounds(279, 106, 119, 54);
		contentPane.add(btnAdmin);
		
		JButton btnUser = new JButton("User");
		btnUser.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 14));
		btnUser.setBounds(279, 193, 119, 54);
		contentPane.add(btnUser);
		
		JLabel lblNewLabel = new JLabel("New label");
		lblNewLabel.setIcon(new ImageIcon(Select.class.getResource("/Images/Select.jpg")));
		lblNewLabel.setBounds(0, 0, 659, 350);
		contentPane.add(lblNewLabel);
		
		try{
			Statement myStat=con.createStatement();
			
			String sql = null, sqll = null, sqlll = null, sqllll = null, sql2 = null, sql22 = null, sql5=null;
			
			String sql3="DROP TABLE IF EXISTS points_table";
			myStat.execute(sql3);
			String sql4="CREATE TABLE points_table (team VARCHAR(45),win int(64),lose INT(64),point INT(45),runrate FLOAT(45),PRIMARY KEY(team))";			
			myStat.execute(sql4);
		}
		catch(Exception e){
			System.out.println(e);
		}
		
		
		
		
		 //Self-written code for defining the function of 'Admin' button
		btnAdmin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Login nw = null;    //"Info" is the next frame by clicking "Info" button
				try {
					nw = new Login();
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				nw.setVisible(true);
				dispose();       //get exited from current frame
			}
		});
		
		 //Self-written code for defining the function of 'User' button
		btnUser.addActionListener(new ActionListener() {
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
