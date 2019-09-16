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

public class MoneyTransfer extends JFrame
implements ActionListener
{
	JTextField  tf1, tf2,tf3;
	JLabel l1, l2,l3, l4,l5;
	JButton b ;
	JComboBox box1,box2;

	public void showGUI8() 
	{
		super.setBounds(500,50,1000,900);
		super.setTitle("Transfer Money");
		super.setResizable(false);
		
		
		tf1 = new JTextField();
		tf1.setBounds(300,135,300,25);
		super.add(tf1);
		
		l1 = new JLabel("Sender's Bank:");
		l1.setBounds(50, 100, 150, 25);
		super.add(l1);
		
		l2 = new JLabel("Senders Account Number:");
		l2.setBounds(50, 135, 150, 25);
		super.add(l2);
		
		String[] array = {"SBI","KOTAK","PNB","HDFC"};
		box1 = new JComboBox<>(array);
		box1.setBounds(300,100,100,25);
		super.add(box1);
		
		tf2 = new JTextField();
		tf2.setBounds(300,235,300,25);
		super.add(tf2);
		
		l3 = new JLabel("Reciever's Bank:");
		l3.setBounds(50, 200, 150, 25);
		super.add(l3);
		
		l4 = new JLabel("Reciever's Account Number:");
		l4.setBounds(50, 235, 150, 25);
		super.add(l4);
		
		
		box2 = new JComboBox<>(array);
		box2.setBounds(300,200,100,25);
		super.add(box2);
		
		tf3 = new JTextField();
		tf3.setBounds(300,300,300,25);
		super.add(tf3);
		
		l5 = new JLabel("Ammount:");
		l5.setBounds(50, 300, 100, 25);
		super.add(l5);
		
		b = new JButton("Transfer Money");
		b.setBounds(210,440,150,30);
		super.add(b);
		b.addActionListener(this);
		
	
		super.setLayout(null);
		
		super.setVisible(true);
		
		super.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		
		
	}
	
	public static void main(String[] args)
	{
		MoneyTransfer t = new MoneyTransfer();
		t.showGUI8();
		
	}
	
	public int action(String n1, String a1, String n2, String a2, String m)
	throws Exception
	{
		int m1 = Integer.parseInt(m);
		Class.forName("com.mysql.jdbc.Driver");
		Connection co = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/BANKS","root","Jee@453891");
		Statement st = co.createStatement();
		int  f1=0, i1=0 ,i2=0, s1=0, s2= 0,t1=0, t2=0; 
		int f2= 0 ;//for insufficient money withdraw
		 ResultSet rs2 = st.executeQuery("Select * from "+n1+"");
		 while(rs2.next())
		 {
			 if(a1.equals(rs2.getString("Account_Number")))
			 f1++;
			 
		 }
		 ResultSet rs3 = st.executeQuery("Select * from "+n2+"");
		 while(rs3.next())
		 {
			 if(a2.equals(rs3.getString("Account_Number")))
			 f2++;
			 
		 }
		 if(f1>0&&f2>0)
		 {
			 ResultSet rs = st.executeQuery("Select * from "+n1+" where Account_Number='"+a1+"'");
			 while(rs.next())
			 {
				i1 =rs.getInt("Amount");
				 s1 = rs.getInt("status");
				 
			 }
			 ResultSet rs4 = st.executeQuery("Select * from "+n2+" where Account_Number='"+a2+"'");
			 while(rs4.next())
			 {
				i2 =rs4.getInt("Amount");
				 s2 = rs4.getInt("status");
				 
			 }
			 if(s1==1&&s2==1)
			 {
				 t1 = i1 -m1;
				 t2 = i2 +m1;
				 if(n1.equals("SBI")&& t1<1000)
					f2=3; 
				 else if(n1.equals("KOTAK")&&t1<10000)
					 f2=4;
				 else if(n1.equals("HDFC")&&t1<5000)
					 f2=5;
				 else if(n1.equals("PNB")&&t1<2000)
					 f2=6;
				 else
				 {
					 st.executeUpdate("update "+n1+" SET Amount ='"+t1+"' where Account_Number='"+a1+"'");
					 st.executeUpdate("update "+n2+" SET Amount ='"+t2+"' where Account_Number='"+a2+"'");
				 }
				co.close(); 
				 
			 }
		 }
		 int f = (f1==0)? 1: (f2==0) ?2 : (s1==0) ? 3 :(s2==0) ? 4 : (f2==3) ? 5 :(f2==4) ? 6 :(f2==5) ? 7 :(f2==6)? 8:9;
		 return f;
		
	}
	
	public void actionPerformed(ActionEvent ae)
	{
		if(ae.getSource()==b)
		{
			String bn1 =  (String)box1.getSelectedItem(),
					ac1 =tf1.getText();
			String bn2 =  (String)box2.getSelectedItem(),
					ac2 =tf2.getText();
			String am = tf3.getText();
			if(ac1.equals("")||ac2.equals("")||am.equals(""))
			{
				JOptionPane.showMessageDialog(this,"Invalid Credentials");
			}
			else
			{
				MoneyTransfer m = new MoneyTransfer();
				try {
					int i = m.action(bn1, ac1, bn2, ac2,am);
					JOptionPane.showMessageDialog(this,(i==1) ? "Sennder's Incorrect account" : (i==2) ? "Reciever's Incorrect Account": (i==3) ?"Sender's account inactive" :(i==4) ?"Receiver's account inactive": (i==5)?"Rs1000 Minimum Balance Required": (i==6)?"Rs10000 Minimum Balance Required": (i==7)?"Rs5000 Minimum Balance Required": (i==8)?"Rs2000 Minimum Balance Required":"Money Transfered");
					
					if(i==9)
					{
						MainMenu m1 = new MainMenu();
						m1.showGUI3();
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
