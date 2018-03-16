import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import java.awt.Font;
import java.awt.Color;


public class AdminMenu extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AdminMenu frame = new AdminMenu();
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
	public AdminMenu() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 675, 388);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JButton Insert = new JButton("Add Product");
		Insert.setFont(new Font("Tahoma", Font.BOLD, 12));
		Insert.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}
		});
		Insert.setBounds(401, 69, 135, 37);
		contentPane.add(Insert);
		
		JButton Delete = new JButton("Delete Product");
		Delete.setFont(new Font("Tahoma", Font.BOLD, 12));
		Delete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}
		});
		Delete.setBounds(401, 162, 135, 33);
		contentPane.add(Delete);
		
		JButton Update = new JButton("Update Product");
		Update.setFont(new Font("Tahoma", Font.BOLD, 12));
		Update.setBounds(401, 117, 135, 33);
		contentPane.add(Update);
		
		JButton AddCategory = new JButton("Add Category");
		AddCategory.setFont(new Font("Tahoma", Font.BOLD, 12));
		AddCategory.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}
		});
		AddCategory.setBounds(145, 69, 150, 37);
		contentPane.add(AddCategory);
		
		JButton OrderList = new JButton("Order list");
		OrderList.setFont(new Font("Tahoma", Font.BOLD, 12));
		OrderList.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}
		});
		OrderList.setBounds(145, 213, 150, 33);
		contentPane.add(OrderList);
		
		JButton Home = new JButton("Home");
		Home.setForeground(Color.BLUE);
		Home.setFont(new Font("Sylfaen", Font.BOLD, 13));
		Home.setBounds(296, 11, 102, 33);
		contentPane.add(Home);
		
		JButton btnUpdateCategory = new JButton("Update Category");
		btnUpdateCategory.setFont(new Font("Tahoma", Font.BOLD, 12));
		btnUpdateCategory.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}
		});
		btnUpdateCategory.setBounds(145, 117, 150, 33);
		contentPane.add(btnUpdateCategory);
		
		JButton btnDeleteCategory = new JButton("Delete Category");
		btnDeleteCategory.setFont(new Font("Tahoma", Font.BOLD, 12));
		btnDeleteCategory.setBounds(145, 161, 150, 33);
		contentPane.add(btnDeleteCategory);
		
		JButton ProductList = new JButton("Product List");
		ProductList.setFont(new Font("Tahoma", Font.BOLD, 12));
		ProductList.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}
		});
		ProductList.setBounds(401, 214, 135, 33);
		contentPane.add(ProductList);
		
		JLabel lblNewLabel = new JLabel("New label");
		lblNewLabel.setIcon(new ImageIcon(AdminMenu.class.getResource("/Images/AdminMenu.jpg")));
		lblNewLabel.setBounds(0, 0, 659, 350);
		contentPane.add(lblNewLabel);
		
		//Self-written code for defining the function of 'Insert' button
		Insert.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
						AdminInsertProduct nw = null;    
						try {
							nw = new AdminInsertProduct();
							
						} catch (Exception e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
						nw.setVisible(true);
						dispose();		//get exited from current frame
						
					}
				});
		
		//Self-written code for defining the function of 'Delete' button
		Delete.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
						AdminDeleteProduct nw = null;    
						try {
							nw = new AdminDeleteProduct();
							
						} catch (Exception e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
						nw.setVisible(true);
						dispose();		//get exited from current frame
						
					}
				});
		
		//Self-written code for defining the function of 'Update' button
				Update.addActionListener(new ActionListener() {
							public void actionPerformed(ActionEvent arg0) {
								AdminUpdateProduct nw = null;    
								try {
									nw = new AdminUpdateProduct();
									
								} catch (Exception e1) {
									// TODO Auto-generated catch block
									e1.printStackTrace();
								}
								nw.setVisible(true);
								dispose();		//get exited from current frame
								
							}
						});
				
				//Self-written code for defining the function of 'AddCategory' button
				AddCategory.addActionListener(new ActionListener() {
							public void actionPerformed(ActionEvent arg0) {
								AdminAddCategory nw = null;   
								try {
									nw = new AdminAddCategory();
									
								} catch (Exception e1) {
									// TODO Auto-generated catch block
									e1.printStackTrace();
								}
								nw.setVisible(true);
								dispose();		//get exited from current frame
								
							}
						});
				
				
				//Self-written code for defining the function of 'UpdateCategory' button
				btnUpdateCategory.addActionListener(new ActionListener() {
							public void actionPerformed(ActionEvent arg0) {
								AdminUpdateCategory nw = null;    
								try {
									nw = new AdminUpdateCategory();
									
								} catch (Exception e1) {
									// TODO Auto-generated catch block
									e1.printStackTrace();
								}
								nw.setVisible(true);
								dispose();		//get exited from current frame
								
							}
						});
				
				//Self-written code for defining the function of 'DeleteCategory' button
				btnDeleteCategory.addActionListener(new ActionListener() {
							public void actionPerformed(ActionEvent arg0) {
								AdminDeleteCategory nw = null;    
								try {
									nw = new AdminDeleteCategory();
									
								} catch (Exception e1) {
									// TODO Auto-generated catch block
									e1.printStackTrace();
								}
								nw.setVisible(true);
								dispose();		//get exited from current frame
								
							}
						});
				//Self-written code for defining the function of 'Order list' button
				OrderList.addActionListener(new ActionListener() {
							public void actionPerformed(ActionEvent arg0) {
								AdminOrderList nw = null;    
								try {
									nw = new AdminOrderList();
									
								} catch (Exception e1) {
									// TODO Auto-generated catch block
									e1.printStackTrace();
								}
								nw.setVisible(true);
								dispose();		//get exited from current frame
								
							}
						});
				
				//Self-written code for defining the function of 'Product list' button
				ProductList.addActionListener(new ActionListener() {
							public void actionPerformed(ActionEvent arg0) {
								AdminItemList nw = null;    
								try {
									nw = new AdminItemList();
									
								} catch (Exception e1) {
									// TODO Auto-generated catch block
									e1.printStackTrace();
								}
								nw.setVisible(true);
								dispose();		//get exited from current frame
								
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
	}
}
