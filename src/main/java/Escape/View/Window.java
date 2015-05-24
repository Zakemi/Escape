package Escape.View;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.JButton;

public class Window extends JFrame {

	private JPanel contentPane;
	private JTextField usernameField;
	public static String username = "";

	public Window() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		
	}
	
	/*public void getUsername(){
		contentPane.removeAll();
		JTextPane infoPane = new JTextPane();
		infoPane.setBounds(35, 20, 180, 19);
		infoPane.setText("Please add your username!");
		infoPane.setFocusable(false);
		contentPane.add(infoPane);
		
		usernameField = new JTextField();
		usernameField.setBounds(35, 50, 180, 19);
		contentPane.add(usernameField);
		usernameField.setColumns(10);
		usernameField.addFocusListener(new FocusListener() {
			
			@Override
			public void focusLost(FocusEvent e) {
				username = usernameField.getText();
			}
			
			@Override
			public void focusGained(FocusEvent e) {
			}
		});
		
		JButton okButton = new JButton("Ok");
		okButton.setBounds(90, 80, 70, 25);
		contentPane.add(okButton);
		okButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				if (!username.equals("")){
					System.exit(0);
				}
			}
		});
	}*/

}
