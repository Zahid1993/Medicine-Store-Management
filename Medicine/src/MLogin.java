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
import javax.swing.AbstractButton;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JPasswordField;

import java.awt.Font;
import java.awt.Color;

import javax.swing.ImageIcon;

public class MLogin extends JFrame {

	private JPanel contentPane;
	private JTextField username;
	private JPasswordField password;
	
	Connection con=null;
	Statement myStat;
	ResultSet result;
	private PreparedStatement statement;
	String userName="",pass="";

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MLogin frame = new MLogin();
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
	public MLogin() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 675, 388);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		username = new JTextField();
		username.setBackground(Color.WHITE);
		username.setBounds(239, 179, 86, 20);
		contentPane.add(username);
		username.setColumns(10);
		
		JLabel lblNewLabel = new JLabel("User Name");
		lblNewLabel.setForeground(Color.DARK_GRAY);
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblNewLabel.setBounds(128, 179, 86, 17);
		contentPane.add(lblNewLabel);
		
		JLabel lblPassword = new JLabel("Password");
		lblPassword.setForeground(Color.DARK_GRAY);
		lblPassword.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblPassword.setBounds(128, 223, 86, 14);
		contentPane.add(lblPassword);
		
		JButton btnLogIn = new JButton("Log in");
		btnLogIn.setForeground(Color.BLUE);
		btnLogIn.setFont(new Font("Comic Sans MS", Font.BOLD, 15));
		
		
				
		btnLogIn.setBounds(239, 263, 89, 30);
		contentPane.add(btnLogIn);
		
		password = new JPasswordField();
		password.setBackground(Color.WHITE);
		password.setBounds(239, 222, 86, 20);
		contentPane.add(password);
		
		JLabel lblUserLogin = new JLabel("User Login");
		lblUserLogin.setFont(new Font("Tahoma", Font.BOLD, 22));
		lblUserLogin.setBounds(160, 0, 188, 61);
		contentPane.add(lblUserLogin);
		
		JLabel lblNewLabel_1 = new JLabel("Or");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblNewLabel_1.setBounds(183, 308, 46, 14);
		contentPane.add(lblNewLabel_1);
		
		JButton SignUp = new JButton("Sign Up");
		SignUp.setForeground(Color.DARK_GRAY);
		SignUp.setFont(new Font("Tahoma", Font.BOLD, 12));
		SignUp.setBounds(239, 304, 89, 20);
		contentPane.add(SignUp);
		
		JLabel lblNewLabel_2 = new JLabel("New label");
		lblNewLabel_2.setIcon(new ImageIcon(MLogin.class.getResource("/Images/Login.jpg")));
		lblNewLabel_2.setBounds(0, 0, 659, 350);
		contentPane.add(lblNewLabel_2);
		
		//Self-written code for defining the function of 'Log in' button
		btnLogIn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					con = getConnection();
					ResultSet myRs=null;
					String getUser=username.getText();  //text field
					String getPass=password.getText();	//text field
					
					String sql = "select * from mlogin where username = ? and password = ?";
					PreparedStatement pst = con.prepareStatement(sql);
					
					pst.setString(1,getUser);  //matching with text field
					pst.setString(2,getPass);
					 myRs = pst.executeQuery();
					
					if(getUser.length()!=0&&getPass.length()!=0){   //i.e. if both fields are not empty
						if(myRs.next()){
							String u = myRs.getString("username");
							System.out.println(u);
							String p = myRs.getString("password");
							System.out.println(p);
							JOptionPane.showMessageDialog(null, "Login successfully", "SQL info", JOptionPane.INFORMATION_MESSAGE);
							new TOrder().setVisible(true);			//if condition is true;go to next Frame(i.e. TOrder) 
							dispose();
						}
						
						else{
							JOptionPane.showMessageDialog(null, "Wrong access..", "Input error", JOptionPane.ERROR_MESSAGE);
						}
					}
					
					else{
						JOptionPane.showMessageDialog(null, "Some fields missing..", "Input error", JOptionPane.ERROR_MESSAGE);
					}	
					
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			
			
			}
		});
		
		//Self-written code for defining the function of 'Signup' button
		SignUp.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				SignUp nw = null;    //"Signup" is the next frame by clicking "Info" button
				try {
					nw = new SignUp();
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				nw.setVisible(true);
				dispose();       //get exited from current frame
			}
		});
	
		
	}
	
public static Connection getConnection() throws Exception{
		
		try{
			
			String driver="com.mysql.jdbc.Driver";
			String url="jdbc:mysql://127.0.0.1:3306/medicine";
			String username="root";
			String password="root";
			Class.forName(driver);
			
			Connection conn=DriverManager.getConnection(url, username, password);
			//System.out.println("Connected");
			return conn;
			
		}
		catch(Exception e){
			System.out.println(e);
		}
		return null;
	}
}


