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

public class Deposite extends JFrame
implements ActionListener
{
	JTextField  tf1, tf2;
	JLabel l1, l2,l3;
	JButton b ;
	JComboBox box;
	
	public void showGUI5()
	{
		super.setBounds(500,50,1000,900);
		super.setTitle("DEPOSITE");
		super.setResizable(false);
		
		
		tf1 = new JTextField();
		tf1.setBounds(300,400,300,50);
		super.add(tf1);
		
		l1 = new JLabel("Bank:");
		l1.setBounds(200, 300, 150, 50);
		super.add(l1);
		
		l2 = new JLabel("Account Number:");
		l2.setBounds(200, 400, 150, 50);
		super.add(l2);
		
		String[] array = {"SBI","KOTAK","PNB","HDFC"};
		box = new JComboBox<>(array);
		box.setBounds(300,300,300,50);
		super.add(box);
		
		tf2 = new JTextField();
		tf2.setBounds(300,500,300,50);
		super.add(tf2);
		
		l3 = new JLabel("Ammount:");
		l3.setBounds(200, 500, 150, 50);
		super.add(l3);
		
		
		b = new JButton("Deposit");
		b.setBounds(350,600,150,70);
		super.add(b);
		b.addActionListener(this);
		
	
		super.setLayout(null);
		
		super.setVisible(true);
		
		super.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

	}
	
	public static void main(String[] args)
	{
		Deposite d = new Deposite();
		d.showGUI5();
		
	}
	
	public int action(String n, String a, String m )
			throws Exception
	{
		int m1 = Integer.parseInt(m);
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
				 t = i + m1;

					st.executeUpdate("update "+n+" SET Amount ='"+t+"' where Account_Number='"+a+"'");
			 }
			 co.close();
		 }
		 int f =(f1==0)?/*Account not found*/ 1 :(s==0)/*Deactivated account*/ ? 2: (f1>0&s==1)/*deposit*/ ? 3 :4/*Invalid Credentials*/;
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
			Deposite d = new  Deposite();
			try {
				int i = d.action(bn, ac, am);
				JOptionPane.showMessageDialog(this,(i==1)?"Invalid Account Number":(i==2)?"Account is not active":(i==3)?"Money Deposited":"Invalid Credentials");
				if(i==3) {MainMenu m = new MainMenu();
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
