package Project_Bank;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

public class CheckBalance extends JFrame
implements ActionListener
{

	JTextField  tf1;
	JLabel l1, l2;
	JButton b ;
	JComboBox box;
	public void showGUI7() 
	{
		super.setBounds(500,50,1000,900);
		super.setTitle("CHECK BALANCE");
		super.setResizable(false);
		
		
		tf1 = new JTextField();
		tf1.setBounds(100,135,300,25);
		super.add(tf1);
		
		l1 = new JLabel("Bank:");
		l1.setBounds(50, 100, 50, 25);
		super.add(l1);
		
		l2 = new JLabel("Account Number:");
		l2.setBounds(50, 135, 50, 25);
		super.add(l2);
		
		String[] array = {"SBI","KOTAK","PNB","HDFC"};
		box = new JComboBox<>(array);
		box.setBounds(100,100,50,25);
		super.add(box);
		
		b = new JButton("Check Balance");
		b.setBounds(210,440,150,30);
		super.add(b);
		b.addActionListener(this);
		
	
		super.setLayout(null);
		
		super.setVisible(true);
		
		super.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		
	}
	
	public static void main(String[] args)
	{
		CheckBalance c = new CheckBalance();
		c.showGUI7();
		
	}
	
	public String action(String n , String a)
			throws Exception
	{
		
		Class.forName("com.mysql.jdbc.Driver");
		Connection co = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/BANKS","root","Jee@453891");
		Statement st = co.createStatement();
		 int  f1=0, i =0,s= 0,t=0; 
		 ResultSet rs2 = st.executeQuery("Select * from "+n+"");
		 while(rs2.next())
		 {
			 if(a.equals(rs2.getString("Account_Number")))
			 f1++;
		 }
		 if(f1>0)
		 {
			 ResultSet rs = st.executeQuery("Select * from "+n+" where Account_Number='"+a+"'");
			 while(rs.next())
			 {
				i =rs.getInt("Amount");
				 s = rs.getInt("status");
				 
			 }
			 if(s==1)
			 {
				 String s2 =Integer.toString(i);
				 return s2;
			 }
			 return ",";
		 }
		 else
			 return ".";
		
	}
	
	
	public void actionPerformed(ActionEvent ae)
	{
		
		if(ae.getSource()==b)
		{
			String bn =  (String)box.getSelectedItem(),
					ac =tf1.getText();
					
			if(ac.equals(""))
				JOptionPane.showMessageDialog(this,"Invalid Credentials");
			if(!(ac.equals(""))) 
			{
			CheckBalance w = new CheckBalance();
			try {
				String i = w.action(bn, ac);
				if(i.equals("."))
					JOptionPane.showMessageDialog(this,"Invalid Account");
				else if(i.equals(","))
					JOptionPane.showMessageDialog(this,"Account Deactivated");
				else
				{
					JOptionPane.showMessageDialog(this,"Rs. "+i+"");
					MainMenu m = new MainMenu();
					m.showGUI3();
					super.dispose();
				}
				
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			}
			
		}
	}


}
