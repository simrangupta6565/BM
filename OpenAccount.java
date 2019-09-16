package Project_Bank;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;

import java.sql.Statement;
import java.util.Random;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

public class OpenAccount extends JFrame 
implements ActionListener 
{
	JTextField  tf2;
	JLabel l1, l2;
	JButton b ;
	JComboBox box;
	
	public void showGUI4()
	{
		super.setBounds(500,50,1000,900);
		super.setTitle("OPEN ACCOUNT");
		super.setResizable(false);
		
		tf2 = new JTextField();
		tf2.setBounds(300,400,300,50);
		super.add(tf2);
		
		l1 = new JLabel("Bank:");
		l1.setBounds(200, 300, 150, 50);
		super.add(l1);
		
		l2 = new JLabel("Ammount:");
		l2.setBounds(200, 400, 150, 50);
		super.add(l2);
		
		String[] array = {"SBI","KOTAK","PNB","HDFC"};
		box = new JComboBox<>(array);
		box.setBounds(300,300,300,50);
		super.add(box);
		
		
		b = new JButton("CREATE ACCOUNT");
		b.setBounds(350,600,150,70);
		super.add(b);
		b.addActionListener(this);
		
		
		

		super.setLayout(null);
		
		super.setVisible(true);
		
		super.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

	}
	
	public static void main(String[] args)
	{
		OpenAccount l = new OpenAccount();
		l.showGUI4();
	}
	
	public String Open(String bn, int am)//Bank name and amount is passed in it.
			throws Exception
			{
				
				Class.forName("com.mysql.jdbc.Driver");
				
				Connection co = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/BANKS","root","Jee@453891");
				
				Statement st = co.createStatement();
				
				Random r = new Random();
				
				long n;
				
					
					n = r.nextLong();
					n = n % 100000000;
					if(n<0)
						n=-(n);
					
					String n2 ;
					n2 = Long.toString(n);
					
				
				st.executeUpdate("insert into "+bn+" values('"+bn+"_"+n2+"','"+am+"','1')");
				
				co.close();
				return (bn+"_"+n2);
			
			}
	
	public void actionPerformed(ActionEvent ae)
	{
		String items = null;
		String s;
		int i;
		
		
		if(ae.getSource()==b)
		{
			items = (String)box.getSelectedItem();
			s = tf2.getText();
			 i = Integer.parseInt(s);
			if(s.equals(""))
			{
				JOptionPane.showMessageDialog(this,"Please Enter a Amount");
			}
			else if(items.equals("SBI")&& i<1000)
			{
				JOptionPane.showMessageDialog(this,"Required atleast Rs.1000 to open Account in SBI" );
			}
			else if(items.equals("KOTAK")&&i<10000)
			{
				JOptionPane.showMessageDialog(this,"Required atleast Rs.10000 to open Account in KOTAK" );
			}
			else if(items.equals("PNB")&&i<2000)
			{
				JOptionPane.showMessageDialog(this,"Required atleast Rs.2000 to open Account in PNB" );
			}else if(items.equals("HDFC")&&i<5000)
			{
				JOptionPane.showMessageDialog(this,"Required atleast Rs.5000 to open Account in HDFC" );
			}
			
			if((items.equals("SBI")&& i>=1000)||(items.equals("KOTAK")&&i>=10000)||(items.equals("PNB")&&i>=2000)||(items.equals("HDFC")&&i>=5000))
			{
				OpenAccount o = new OpenAccount();
				try {
					String k = o.Open(items, i);
					JOptionPane.showMessageDialog(this,"Your Account NO: "+k+". Please note it down.");
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				MainMenu m = new MainMenu();
				m.showGUI3();
				super.dispose();
			}
	
		}
		
		
		
		
	}

	
}
