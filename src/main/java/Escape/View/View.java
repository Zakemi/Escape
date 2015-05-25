package Escape.View;

import java.awt.Color;
import java.awt.Image;
import java.awt.Insets;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTextArea;

import Escape.Controller.Controller;
import Escape.Model.Arena;

public class View extends JPanel implements KeyListener{

	private static final long serialVersionUID = -1275706944314909295L;
	private Arena arena;
	private Controller control;
	private JButton player = new JButton();
	private JButton enemy1 = new JButton();
	private JButton enemy2 = new JButton();
	private List<JButton> traps;
	private JTextArea score;
	
	public View(Arena arena) {
		super();
		this.setLayout(null);
		this.arena = arena;
		traps = new ArrayList<JButton>();
		for (int i=0; i<arena.getTraps().getList().size(); i++){
			traps.add(new JButton());
		}
		
		score = new JTextArea();
		score.setBounds(2, 2, 120, 17);
		score.setFocusable(false);
		if (control == null)
			score.setText("Player 0:0 Enemy");
		else
			score.setText("Player "+control.getPlayerScore()+" : "+control.getEnemyScore()+" Enemy");
		this.add(score);
		
		try {
			InputStream playerImg = ClassLoader.getSystemResourceAsStream("puppy-icon.png");
			InputStream enemyImg = ClassLoader.getSystemResourceAsStream("wolf-icon.png");
			InputStream trapImg = ClassLoader.getSystemResourceAsStream("trap-icon.png");
			Image playerImage = ImageIO.read(playerImg);
			Image enemyImage = ImageIO.read(enemyImg);
			Image trapImage = ImageIO.read(trapImg);
			player.setIcon(new ImageIcon(playerImage));
			enemy1.setIcon(new ImageIcon(enemyImage));
			enemy2.setIcon(new ImageIcon(enemyImage));
			for (int i=0; i<arena.getTraps().getList().size(); i++){
				traps.get(i).setIcon(new ImageIcon(trapImage));
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		player.setBounds(arena.getPlayer().getX(), arena.getPlayer().getY(), 100, 100);
		enemy1.setBounds(arena.getEnemy1().getX(), arena.getEnemy1().getY(), 100, 100);
		enemy2.setBounds(arena.getEnemy2().getX(), arena.getEnemy2().getY(), 100, 100);
		// to remote the spacing between the image and button's borders
		player.setMargin(new Insets(0, 0, 0, 0));
		enemy1.setMargin(new Insets(0, 0, 0, 0));
		enemy2.setMargin(new Insets(0, 0, 0, 0));
		// to add a different background
		player.setBackground(Color.GREEN);
		enemy1.setBackground(Color.RED);
		enemy2.setBackground(Color.RED);
		// to remove the border
		player.setBorder(null);
		enemy1.setBorder(null);
		enemy2.setBorder(null);
		enemy1.setFocusable(false);
		enemy2.setFocusable(false);
		this.add(player);
		this.add(enemy1);
		this.add(enemy2);
		for (int i=0; i<arena.getTraps().getList().size(); i++){
			traps.get(i).setBounds(arena.getTraps().getList().get(i).getX(), 
					arena.getTraps().getList().get(i).getY(), 100, 100);
			traps.get(i).setMargin(new Insets(0, 0, 0, 0));
			traps.get(i).setBackground(Color.RED);
			traps.get(i).setBorder(null);
			traps.get(i).setFocusable(false);
			this.add(traps.get(i));
		}

		player.addKeyListener(this);
	}
	
	public void updateView(){
		if(!arena.getPlayer().isActive()){
			player.setBackground(Color.BLACK);
		}
		else{
			player.setBackground(Color.GREEN);
		}
		player.setBounds(arena.getPlayer().getX(), arena.getPlayer().getY(), 100, 100);
		
		if(!arena.getEnemy1().isActive()){
			enemy1.setBackground(Color.BLACK);
		}
		else{
			enemy1.setBackground(Color.RED);
		}
		enemy1.setBounds(arena.getEnemy1().getX(), arena.getEnemy1().getY(), 100, 100);
			
		if(!arena.getEnemy2().isActive()){
			enemy2.setBackground(Color.BLACK);
		}
		else{
			enemy2.setBackground(Color.RED);
		}
		enemy2.setBounds(arena.getEnemy2().getX(), arena.getEnemy2().getY(), 100, 100);
		
		for(int i = 0; i < traps.size(); i++){
			traps.get(i).setBounds(arena.getTraps().getList().get(i).getX(), 
			arena.getTraps().getList().get(i).getY(), 100, 100);
		}
		
		score.setText("Player "+control.getPlayerScore()+" : "+control.getEnemyScore()+" Enemy");
	}

	@Override
	public void keyPressed(KeyEvent key) {
		if (key.getKeyCode() == KeyEvent.VK_LEFT){
			control.keyVK_LEFT();
		}
		if (key.getKeyCode() == KeyEvent.VK_RIGHT){
			control.keyVK_RIGHT();
		}
		if (key.getKeyCode() == KeyEvent.VK_DOWN){
			control.keyVK_DOWN();
		}
		if (key.getKeyCode() == KeyEvent.VK_UP){
			control.keyVK_UP();
		}
	}

	@Override
	public void keyReleased(KeyEvent arg0) {}

	@Override
	public void keyTyped(KeyEvent arg0) {}
	
	public JButton getPlayer() {
		return player;
	}

	public void setControl(Controller control) {
		this.control = control;
	}
}
