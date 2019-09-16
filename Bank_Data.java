package Project_Bank;
import java.sql.*;

public class Bank_Data
{
	
	public void SignIn(String name, String email, String mobile, String add, String pw)
	throws Exception
	{
		Class.forName("com.mysql.jdbc.Driver");
		Connection co = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/BANKS","root","Jee@453891");
		Statement st = co.createStatement();
		st.executeUpdate("insert into UserInfo values('"+name+"','"+email+"','"+mobile+"','"+add+"',sha1('"+pw+"'))");
		co.close();
		//to update the data in user info while signing in 
	}
	
	public int LogIn(String email, String pw)
	throws Exception
	{
		Class.forName("com.mysql.jdbc.Driver");
		Connection co = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/BANKS","root","Jee@453891");
		Statement st = co.createStatement();
		ResultSet rs = st.executeQuery("select * from UserInfo");
		

		 int j=1, f1=0, f2=0;
		 rs.first();
		 
		 do
		 {
			 if(email == rs.getString("Email"))
			 {
				 f1 = j;
			 }
			 if(pw == rs.getString("Password"))
			 {
				 f2 = j;
			 }
			 j++;
			 
		 }while(rs.next());
		 
		 co.close();
		 int k =  (f1==0)? 1/*email not found*/ : (f2==0)? 2/*password not found*/ : (f1==f2) ? 3/*correct*/:4/*incorrect*/;
		 return k;
	
	}
	
	public static void main(String[] args) throws Exception 
	{
		Bank_Data b = new Bank_Data();
		b.SignIn("Bhanu", "Bhanu@gmail.com", "9876546723", "Kashmir", "Bhanu@123");
		
	}
	
	
	
}


