package Escape.View;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextPane;
import javax.swing.text.BadLocationException;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;

import Escape.Service.Service;
import JDBC.GameState;

public class Rank extends JPanel {
	
	JTextPane ranks;
	private String DAOpassword;
	
	public Rank(){
		this.DAOpassword = DAOpassword;
		ranks = new JTextPane();
		ranks.setBounds(100, 100, 500, 300);
		//refreshRank();
		this.add(ranks);
		
		JButton refresh = new JButton("Refresh");
		refresh.setBounds(550, 500, 100, 50);
		refresh.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				refreshRank();
			}
		});
		this.add(refresh);
	}

	public void setDAOpassword(String dAOpassword) {
		DAOpassword = dAOpassword;
	}

	public void refreshRank(){
		List<GameState> gamestate = Service.getTop5(DAOpassword);
		
		SimpleAttributeSet rowStyle = new SimpleAttributeSet();
		StyleConstants.setFontFamily(rowStyle, Font.MONOSPACED);
		StyleConstants.setFontSize(rowStyle, 20);
		StyleConstants.setBold(rowStyle, true);
		StyleConstants.setForeground(rowStyle, Color.BLACK);

		ranks.setText("");
		try {
			ranks.getDocument().insertString(ranks.getDocument().getLength(), 
					String.format("\n%6s%20s%7s%6s%6s  \n", "Rank", "Name", "Player", "Enemy", "Full"), rowStyle);
		} catch (BadLocationException e1) {
			e1.printStackTrace();
		}
		
		
		for(int i=0; i<gamestate.size(); i++){
			String window = "";
			String username = gamestate.get(i).getUsername();
			if (username.length()>20)
				username = username.substring(0, 19);
			window = String.format("%6d%20s%7d%6d%6d  \n", i+1, username, 
					gamestate.get(i).getPlayerScore(), gamestate.get(i).getEnemyScore(), gamestate.get(i).getFullScore());
			try {
				if ( i== 0)
					StyleConstants.setForeground(rowStyle, Color.RED);
				else if (i==1)
					StyleConstants.setForeground(rowStyle, Color.ORANGE);
				else if (i==2)
					StyleConstants.setForeground(rowStyle, Color.YELLOW);
				else
					StyleConstants.setForeground(rowStyle, Color.BLACK);
				ranks.getDocument().insertString(ranks.getDocument().getLength(), window, rowStyle);
			} catch (BadLocationException e) {
				e.printStackTrace();
			}
		}
		
	}
	
}
