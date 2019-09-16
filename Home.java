package Project_Bank;
import java.awt.Color;
import java.awt.Dimension;

import javax.swing.SwingUtilities;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextArea;

public class Home extends JFrame
implements ActionListener
{
	JButton  b1, b2;
	
	
	public void setGUI()
	{
		super.setBounds(500,50,1000,900);
		super.setTitle("HOME");
		super.setResizable(false);
		getContentPane().setBackground(new java.awt.Color(57,109,143)); 
		ImageIcon ic2 = new ImageIcon("C:\\signup.png");

		ImageIcon ic1 = new ImageIcon("C:\\Users\\DELL\\Pictures\\login3.png");
				
		b1 = new JButton("SIGN UP");
		b1.setBounds(300,200 ,400 , 150 );
		super.add(b1);
		b1.setIcon(ic2);
		b1.addActionListener(this);
		
		
		
		b2 = new JButton("LOGIN");
		b2.setBounds(300, 450, 400, 150);
		super.add(b2);
		b2.setIcon(ic1);
		b2.addActionListener(this);
		
		
		
		super.setLayout(null);
		
		super.setVisible(true);
		
		super.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
	}
	
	public static void main(String[] args) 
	{
		
		Home h = new Home();
		h.setGUI();
	
	}
	
	public void actionPerformed(ActionEvent ae)
	{
		
		if(ae.getSource()==b1)
		{
			SIGNUP s = new SIGNUP();
			s.showGUI2();
			super.dispose();
			
		}
		
		else if(ae.getSource()==b2)
		{
			LOGIN l = new LOGIN();
			l.showGUI1();
			super.dispose();

			}
		
	}
	

	
	

}
