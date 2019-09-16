package Project_Bank;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import java.sql.*;


public class LOGIN extends JFrame
implements ActionListener
{

	JTextField tf1, tf2;
	JLabel l1, l2;
	JButton b,b2;
	
	public void showGUI1()
	{
		super.setBounds(500,50,1000,900);
		super.setTitle("LOGIN");
		super.setResizable(false);
		
		tf1 = new JTextField();
		tf1.setBounds(300,300,300,50);
		super.add(tf1);
		
		tf2 = new JTextField();
		tf2.setBounds(300,400,300,50);
		super.add(tf2);
		
		l1 = new JLabel("Email:");
		l1.setBounds(200, 300, 150, 50);
		super.add(l1);
		
		l2 = new JLabel("Password:");
		l2.setBounds(200, 400, 150, 50);
		super.add(l2);
		
		b = new JButton("LOGIN");
		b.setBounds(350,600,150,70);
		super.add(b);
		b.addActionListener(this);
		
		b2 = new JButton("Forgot Password");
		b2.setBounds(450,455,150,30);
		super.add(b2);
		b2.addActionListener(this);
		

		super.setLayout(null);
		
		super.setVisible(true);
		
		super.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

	}
	
	public static void main(String[] args)
	{
		LOGIN l = new LOGIN();
		l.showGUI1();
	}
	
	public int LogIn(String email, String pw)
			throws Exception
			{
				r.getConnection("jdbc:mysql://127.0.0.1:3306/BANKS","root","Jee@453891");
				Statement st = co.createStatement();
				Class.forName("com.mysql.jdbc.Driver");
				Connection co = DriverManage
				

				 int  f1=0; 
				 ResultSet rs2 = st.executeQuery("Select * from UserInfo");
				 while(rs2.next())
				 {
					 if(email.equals(rs2.getString("Email")))
					 f1++;
				 }
			
				 String s = null;
				 int l=0;
				 
				 
				 ResultSet rs = st.executeQuery("select Email from UserInfo where Password = sha1('"+pw+"')");
				 while(rs.next())
				 {
					 
					 s= rs.getString("Email");
					 l++;
					 
				 }
				 
				 co.close();
				 int k =  (f1==0)? 1/*email not found*/ : (l==0)? 2/*password not found*/ : (s.equals(email) )? 3/*correct*/:4/*incorrect*/;
				 return k;
			
			}
	
	public void actionPerformed(ActionEvent ae)
	{
		if(ae.getSource()==b)
		{
			String a = tf1.getText(), b = tf2.getText();
			
			if(a.equals("")||b.equals(""))
			{
				JOptionPane.showMessageDialog(this,"Invalid Credential");
			}
			else
			{
				LOGIN l = new LOGIN();
				try {
					int i = l.LogIn(a, b);
					
					if(i==1)
					{
						JOptionPane.showMessageDialog(this,"Invalid Email");
						tf1.setText("");
					}
					if(i==2)
					{
						JOptionPane.showMessageDialog(this,"Invalid Password");
						tf2.setText("");
					}
					if(i==3)
					{
						JOptionPane.showMessageDialog(this,"Redirecting to next Step");
						MainMenu m = new MainMenu();
						m.showGUI3();
						super.dispose();
					}
					if(i==4)
					{
						JOptionPane.showMessageDialog(this,"Invalid Credential");
						tf1.setText("");
						tf2.setText("");
					}
		
					
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		
			}
	
		}
		
		if(ae.getSource()==b2)
		{
			BankEmail b = new BankEmail();
			String args = tf1.getText();
			int i=0;
			try {
				i = b.main(args);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			if(i==1)
			{
				JOptionPane.showMessageDialog(this,"Your Password has been sent to your email! ");
			}
			else
				JOptionPane.showMessageDialog(this,"");
		}
		
	}
	
	

}
