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
	
	public Rank(){
		ranks = new JTextPane();
		ranks.setBounds(100, 100, 500, 300);
		refreshRank();
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

	private void refreshRank(){
		List<GameState> gamestate = Service.getTop5();
		
		SimpleAttributeSet first = new SimpleAttributeSet();
		StyleConstants.setFontFamily(first, Font.MONOSPACED);
		StyleConstants.setFontSize(first, 20);
		StyleConstants.setBold(first, true);
		StyleConstants.setForeground(first, Color.RED);
		
		SimpleAttributeSet second = new SimpleAttributeSet();
		StyleConstants.setFontFamily(second, Font.MONOSPACED);
		StyleConstants.setFontSize(second, 20);
		StyleConstants.setBold(second, true);
		StyleConstants.setForeground(second, Color.ORANGE);
		
		SimpleAttributeSet third = new SimpleAttributeSet();
		StyleConstants.setFontFamily(third, Font.MONOSPACED);
		StyleConstants.setFontSize(third, 20);
		StyleConstants.setBold(third, true);
		StyleConstants.setForeground(third, Color.YELLOW);
		
		SimpleAttributeSet other = new SimpleAttributeSet();
		StyleConstants.setFontFamily(other, Font.MONOSPACED);
		StyleConstants.setFontSize(other, 20);
		StyleConstants.setBold(other, true);
		StyleConstants.setForeground(other, Color.BLACK);

		ranks.setText("");
		try {
			ranks.getDocument().insertString(ranks.getDocument().getLength(), 
					String.format("\n%6s%20s%7s%6s%6s  \n", "Rank", "Name", "Player", "Enemy", "Full"), other);
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
					ranks.getDocument().insertString(ranks.getDocument().getLength(), window, first);
				else if (i==1)
					ranks.getDocument().insertString(ranks.getDocument().getLength(), window, second);
				else if (i==2)
					ranks.getDocument().insertString(ranks.getDocument().getLength(), window, third);
				else
					ranks.getDocument().insertString(ranks.getDocument().getLength(), window, other);
			} catch (BadLocationException e) {
				e.printStackTrace();
			}
		}
		//ranks.setText(window);
		
	}
	
}
