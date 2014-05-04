import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

import java.awt.*;
import java.awt.event.*;


public class Menu implements ActionListener{
	private JFrame f;
	private JButton goButton;
	private JButton exitButton;
	private Container background;
	
	private String adres;
	private String po;
	
	private boolean gettinginfo;

	public static void main(String[] args) {
		Menu m = new Menu();
	}
	
	public Menu(){
	
		
	}
	
	public String init(){
		gettinginfo = true;
		f = new JFrame("Welcome to your first blackout.");
		f.setSize(500,200);
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		f.setResizable(false);
		f.setVisible(true);
		
		background = f.getContentPane();
		background.setLayout(new GridLayout(0,2));
		
		goButton = new JButton("GO");
		exitButton = new JButton("EXIT");
		
		goButton.addActionListener(this);
		exitButton.addActionListener(this);
		
		background.add(goButton);
		background.add(exitButton);
		
		goButton.setBackground(Color.WHITE);
		exitButton.setBackground(Color.GRAY);
		
		goButton.setOpaque(true);
		exitButton.setOpaque(true);
		goButton.setBorderPainted(false);
		exitButton.setBorderPainted(false);
		
		while (true) {
			if (adres.length() > 0)
				break;
		}
		return adres + " " + po;

	}

	public void changeText(String s){
		goButton.setText(s);
		exitButton.setText(s);
	}

	public void chooser(String userDecision){
		switch(userDecision){
				case "Y":
				case "y":
				case "Yes":
				case "yes":
				goButton.setText("Goodbye!");
				goButton.setBackground(Color.black);
				exitButton.setText("Goodbye!");
				exitButton.setBackground(Color.black);
				f.setVisible(false);
				break;
			}
	}

	public void begin(String address, int port){
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource()==goButton){
			try {
				String address = JOptionPane.showInputDialog("First, enter your host server's address.");
				String port = JOptionPane.showInputDialog("Enter host's port number.");
				adres = address;
				po = port;
				gettinginfo = false;
				System.out.println("ASFDSDFS");
				
				
			} catch (Exception ehjh) {
				// lol
			}
		}else if(e.getSource()==exitButton){
			String userDecision = JOptionPane.showInputDialog("Are you sure you want to quit? Y/n");
			chooser(userDecision);
		}
	}	
	
}

