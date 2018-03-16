//Used for connecting to database
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.Statement;

public class Database {
	
	public Database() throws Exception{
		
		createTable();   //to create table in database
	}
	
	public static void createTable() throws Exception{
		try{
			
			Connection con=getConnection();	//to connect table in database
			Statement myStat=con.createStatement();
			
			String sqll=null, sqll1=null, sqll2=null, sqll3=null, sqll4=null,sqllll=null;
			PreparedStatement create;
			
			//for "login" table
			sqll="CREATE TABLE IF NOT EXISTS login (username varchar(45) NOT NULL, password varchar(45) NOT NULL,PRIMARY KEY(username))";			
			myStat.execute(sqll);	
			
			//for "mlogin" table for users
			sqllll="CREATE TABLE IF NOT EXISTS mlogin (id int NOT NULL AUTO_INCREMENT, username varchar(255) NOT NULL, password varchar(255) NOT NULL,PRIMARY KEY(id))";			
			myStat.execute(sqllll);
						
			//for "category" table			
			sqll2="CREATE TABLE IF NOT EXISTS category(id int NOT NULL AUTO_INCREMENT,category_name varchar(45) NOT NULL,PRIMARY KEY(id))";			
			myStat.execute(sqll2);
			
			//for "item" table
			sqll3="CREATE TABLE IF NOT EXISTS item(id int NOT NULL AUTO_INCREMENT,product varchar(45) NOT NULL,company varchar(45) NOT NULL,info varchar(45) NOT NULL,category varchar(255) NOT NULL,price int NOT NULL,PRIMARY KEY(id))";			
			myStat.execute(sqll3);
			
			//for "bill" table
			sqll4="CREATE TABLE IF NOT EXISTS bill(id int NOT NULL AUTO_INCREMENT,date varchar(255) NOT NULL,bill varchar(255) NOT NULL,PRIMARY KEY(id))";			
			myStat.execute(sqll4);
			

			
			//Insert value in "login" table
			sqll1 = "insert into login values('test','test')";
			System.out.println(sqll1);
			myStat.executeUpdate(sqll1);
																	
		}
		catch(Exception e){
			System.out.println(e);
		}
		finally{
			System.out.println("Function complete");
		}
	}

	public static Connection getConnection() throws Exception{
		try{
			//Information about database source
			String driver="com.mysql.jdbc.Driver";
			String url="jdbc:mysql://127.0.0.1:3306/medicine";		//"medicine" is the database(i.e. schema) name
			String username="root";
			String password="root";
			Class.forName(driver);
			
			
			Connection conn=DriverManager.getConnection(url,username,password);
			System.out.println("Connected");
			return conn;
		}
		catch(Exception e){
			System.out.println(e);
		}
		return null;
		
	}
}


