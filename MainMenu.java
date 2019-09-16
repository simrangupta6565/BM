package Project_Bank;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;

public class MainMenu extends JFrame
implements ActionListener
{
	JButton  b1, b2, b3, b4, b5, b6;
	
	public void showGUI3()
	{
		super.setBounds(500,50,1000,900);
		super.setTitle("MAIN MENU");
		super.setResizable(false);
		
		b1 = new JButton("OPEN ACCOUNT");
		b1.setBounds(350, 100, 300, 50);
		super.add(b1);
		b1.addActionListener(this);
		
		
		b2 = new JButton("DEPOSIT");
		b2.setBounds(350, 200, 300, 50);
		super.add(b2);
		b2.addActionListener(this);
		
		b3 = new JButton("WITHDRAW");
		b3.setBounds(350, 300, 300, 50 );
		super.add(b3);
		b3.addActionListener(this);
		
		
		b4 = new JButton("CHECK BALANCE");
		b4.setBounds(350, 400, 300, 50);
		super.add(b4);
		b4.addActionListener(this);
		
		b5 = new JButton("MONEY TRANSFER");
		b5.setBounds(350, 500, 300, 50);
		super.add(b5);
		b5.addActionListener(this);
		
		
		b6= new JButton("ACCOUNT DEACTIVATE");
		b6.setBounds(350, 600, 300, 50);
		super.add(b6);
		b6.addActionListener(this);
		
		
		
		super.setLayout(null);
		
		super.setVisible(true);
		
		super.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
	}
	
	public static void main(String[] args) 
	{
		
		MainMenu m = new MainMenu();
		m.showGUI3();
	
	}
	
	public void actionPerformed(ActionEvent ae)
	{
		
		if(ae.getSource()==b1)
		{

			OpenAccount o = new OpenAccount();
			o.showGUI4();
			super.dispose();
	
		}
		
		else if(ae.getSource()==b2)
		{
			Deposite d = new Deposite();
			d.showGUI5();
			super.dispose();

			}
		
		else if(ae.getSource()==b3)
		{
			Withdraw w = new Withdraw();
			w.showGUI6();
			super.dispose();

			}
		
		else if(ae.getSource()==b4)
		{
			CheckBalance b  = new CheckBalance();
			b.showGUI7();
			super.dispose();

			}
		
		else if(ae.getSource()==b5)
		{
			MoneyTransfer m = new MoneyTransfer();
			m.showGUI8();
			super.dispose();

			}
		
		else if(ae.getSource()==b6)
		{
			Deactivate d = new Deactivate();
			d.showGUI9();
			super.dispose();

			}
		
		
	}

	
	

	
	

}

