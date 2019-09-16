package Project_Bank;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import java.sql.*;

public class SIGNUP extends JFrame
implements ActionListener

{

	JTextField tf1, tf2,tf3, tf4, tf5, tf6;
	JLabel l1, l2, l3, l4, l5, l6;
	JButton b,b2;
	
	
	public void showGUI2() 
	{
		super.setBounds(500,50,1000,900);
		super.setTitle("SIGNUP");
		super.setResizable(false);
		
		tf1 = new JTextField();
		tf1.setBounds(350, 100, 300, 50);
		super.add(tf1);
		 l1 =new JLabel("NAME:");
		 l1.setBounds(200, 100, 100, 50);
		 super.add(l1);
		 
			tf2 = new JTextField();
			tf2.setBounds(350, 200, 300, 50);
			super.add(tf2);
			 l2 =new JLabel("Email ADDRESS:");
			 l2.setBounds(200, 200, 100, 50);
			 super.add(l2);
			 
			 
				tf3 = new JTextField();
				tf3.setBounds(350, 300, 300, 50);
				super.add(tf3);
				 l3 =new JLabel("MOBILE:");
				 l3.setBounds(200, 300, 100, 50);
				 super.add(l3);
				 
				 
					tf4 = new JTextField();
					tf4.setBounds(350, 400, 300, 50);
					super.add(tf4);
					 l4 =new JLabel("PASSWORD:");
					 l4.setBounds(200, 400, 100, 50);
					 super.add(l4);
					 
					 tf5 = new JTextField();
						tf5.setBounds(350, 500, 300, 50);
						super.add(tf5);
						 l5 =new JLabel("CONFIRM PASSWORD:");
						 l5.setBounds(200, 500, 150, 50);
						 super.add(l5);
						 
						 tf6 = new JTextField();
							tf6.setBounds(350, 600, 300, 50);
							super.add(tf6);
							 l6 =new JLabel("ADDRESS:");
							 l6.setBounds(200, 600, 100, 50);
							 super.add(l6);
							 
				b = new JButton("SIGNUP");
				b.setBounds(300,700,150,70);
				super.add(b);
				b.addActionListener(this);
				
				super.setLayout(null);
				
				super.setVisible(true);
				
				super.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	
	}
	
	public static void main(String[] args)
	{
		SIGNUP s = new SIGNUP();
		s.showGUI2();
		
	}
	
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
	
	
	public void actionPerformed(ActionEvent ae)
	
	{
		
		if(ae.getSource()==b)
		{	
		String a = tf1.getText(),b= tf2.getText(),c= tf3.getText(),
				d = tf4.getText(),
				e = tf5.getText(),
				f = tf6.getText();
		if(a.equals("")||b.equals("")||e.equals("")||d.equals("")||e.equals("")||f.equals(""))
			JOptionPane.showMessageDialog(this, "Please Enter all Details");
		
		if(d.equals(e)==false)
		{
			
			
			JOptionPane.showMessageDialog(this, "Please enter the password again");
			tf5.setText("");
		
		}
		if(d.equals(e)&& !(a.equals(""))&& !(b.equals(""))&& !(c.equals(""))&& !(d.equals(""))&& !(e.equals(""))&& !(f.equals("")))
		{
			SIGNUP s = new SIGNUP();
		
		try {
			s.SignIn(a, b, c, f, d);
			JOptionPane.showMessageDialog(this, "Redirecting to next step.");
			MainMenu m = new MainMenu();
			m.showGUI3();
			super.dispose();
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		super.dispose();
		}
		
		}
	
		
	}

}
