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

public class Withdraw extends JFrame
implements ActionListener
{	
	JTextField  tf1, tf2;
	JLabel l1, l2,l3;
	JButton b ;
	JComboBox box;
	public void showGUI6() 
	{
		super.setBounds(0,0,2000,1900);
		super.setTitle("WITHDRAW");
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
		
		tf2 = new JTextField();
		tf2.setBounds(100,200,300,25);
		super.add(tf2);
		
		l3 = new JLabel("Ammount:");
		l3.setBounds(50, 200, 50, 25);
		super.add(l3);
		
		
		b = new JButton("Withdraw Money");
		b.setBounds(210,440,150,30);
		super.add(b);
		b.addActionListener(this);
		
	
		super.setLayout(null);
		
		super.setVisible(true);
		
		super.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		
	}
	
	public static void main(String[] args)
	{
		Withdraw w = new Withdraw();
		w.showGUI6();
		
	}
	
	public int action(String n, String a, String m )
			throws Exception
	{
		int m1 = Integer.parseInt(m);
		Class.forName("com.mysql.jdbc.Driver");
		Connection co = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/BANKS","root","Jee@453891");
		Statement st = co.createStatement();
		int  f1=0, i =0,s= 0,t=0; 
		int f2= 0 ;//for insufficient money withdraw
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
				 t = i -m1;
				 if(n.equals("SBI")&& t<1000)
					f2=3; 
				 else if(n.equals("KOTAK")&&t<10000)
					 f2=4;
				 else if(n.equals("HDFC")&&t<5000)
					 f2=5;
				 else if(n.equals("PNB")&&t<2000)
					 f2=6;
				 else
				 {
					 st.executeUpdate("update "+n+" SET Amount ='"+t+"' where Account_Number='"+a+"'");
				 }
				co.close(); 
				 
			 }
		 }
		 int f = (f1==0)? 1: (s==0) ?2 : (f2==3) ? 3 :(f2==4) ? 4 : (f2==5) ? 5 :(f2==6) ? 6 :7;
		 return f;
	}
	
	public void actionPerformed(ActionEvent ae)
	{
		
		if(ae.getSource()==b)
		{
			String bn =  (String)box.getSelectedItem(),
					ac =tf1.getText(),
					am =tf2.getText();
			if(am.equals("")||ac.equals(""))
				JOptionPane.showMessageDialog(this,"Invalid Credentials");
			if(!(am.equals(""))&&!(ac.equals(""))) 
			{
			Withdraw w = new Withdraw();
			try {
				int i = w.action(bn, ac, am);
				JOptionPane.showMessageDialog(this,(i==1)?"Invalid Account Number":(i==2)?"Account is not active":(i==3||i==4||i==5||i==6)?"Insufficient Balance":(i==7)?"Please withdraw the Money":"Invalid Credentials");
				if(i==7) {MainMenu m = new MainMenu();
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
