import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;



public class StartScreen extends JFrame {

//fields	
private JButton logIn;
private JButton newUser;
private JTextField userName;
private JLabel enterUserName;


public StartScreen(){
	super();
	 logIn=new JButton("Log in");
	 newUser= new JButton("Create new username");
	 userName= new JTextField("Enter username");
	 enterUserName= new JLabel("Enter username. If this your first time  click the create new username button");
	 Container center= new Container(); 
	 center.setLayout(new GridLayout());
	
	this.setSize(600,500);
	this.setLayout(new BorderLayout());
	 
	this.add(enterUserName,BorderLayout.CENTER);
	
	
	
	
	
	
}





}
