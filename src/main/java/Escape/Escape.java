package Escape;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Insets;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;

import Escape.Controller.Controller;
import Escape.Model.Arena;
import Escape.Service.Service;
import Escape.View.Rank;
import Escape.View.View;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.io.IOException;
import java.io.InputStream;
import java.util.logging.LogManager;

/**
 * The main class of the program. 
 */
public class Escape extends JFrame {

	static {
		InputStream	in = Escape.class.getResourceAsStream("/logging.properties");
		if (in != null) {
			try {
				LogManager.getLogManager().readConfiguration(in);
			} catch(IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	/**
	 * The <code>serialVersionUID</code> of the class.
	 */
	private static final long serialVersionUID = -3689415169655758824L;
	
	/**
	 * The main JPanel of the <code>frame</code>.
	 */
	private JPanel contentPane;
	
	/**
	 * The main <code>Arena</code> object of the program.
	 */
	private Arena arena;
	
	/**
	 * Part of the Game tab, the main <code>View</code> object.
	 */
	private View view;
	
	/**
	 * The main <code>Controller</code> object of the program.
	 */
	private Controller control;
	
	/**
	 * Part of the Rank tab, the main <code>Rank</code> object.
	 */
	private Rank rank;
	
	/**
	 * The name of the player.
	 * Default is "Guest".
	 */
	private String username = "Guest";
	
	/**
	 * The password for the database.
	 */
	private String DAOpassword = "pwd";

	/**
	 * Main method of the program.
	 * Creates the main JFrame object and asks the user to set <code>DAOpassword</code> 
	 * and <code>username</code> before start the game.
	 * 
	 * @param args command-line parameters
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Escape frame = new Escape();
					frame.setVisible(true);
					do{
						frame.DAOpassword = JOptionPane.showInputDialog(frame, "Enter password for database!");
					} while(frame.DAOpassword.equals("pwd"));
					do{
		            	frame.username = JOptionPane.showInputDialog(frame, "Enter your in-game name!");
					} while(frame.username.equals("") || frame.username == null);
					frame.rank.setDAOpassword(frame.DAOpassword);
					frame.rank.refreshRank();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Constructor for the main JFrame object.
	 * Sets the <code>frame</code> and initialize the <code>arena</code>, <code>view</code>,
	 * <code>control</code>, <code>rank</code> variables, add tabs.
	 * Calls the <code>initMenu</code> for add menu.
	 */
	public Escape() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setTitle("Escape");
		setBounds(300, 0, 0, 0);
		pack();
		Insets insets = getInsets();
		setSize(new Dimension(insets.left + insets.right + 600,
		             insets.top + insets.bottom + 630));
		contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		arena = new Arena(6, 600);
        view = new View(arena);
        control = new Controller(arena, view);
        view.setControl(control);
        rank = new Rank();
		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout());
		
		createMenuBar();
		JTabbedPane tabbedPane = new JTabbedPane();
		tabbedPane.addTab("Game", view);
		tabbedPane.addTab("Rank", rank);
		tabbedPane.setFocusable(false);
		contentPane.add(tabbedPane);

        setLocationRelativeTo(view.getPlayer());
	}

	/**
	 * Creates the Menu and add to the main JFrame.
	 * Creates the "New Game", "Save Game" and "Exit" items and
	 * add ActionListener for control actions.
	 */
    private void createMenuBar() {

        JMenuBar menubar = new JMenuBar();

        JMenu file = new JMenu("File");
        file.setMnemonic(KeyEvent.VK_F);

        JMenuItem newGameMenuItem = new JMenuItem("New Game");
        newGameMenuItem.setMnemonic(KeyEvent.VK_E);
        newGameMenuItem.setToolTipText("Start a new game");
        newGameMenuItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent event) {
                Service.newGame(arena, control, view);
            }
        });
        
        JMenuItem saveGameMenuItem = new JMenuItem("Save Game");
        saveGameMenuItem.setMnemonic(KeyEvent.VK_E);
        saveGameMenuItem.setToolTipText("Save the actual score and start a new game!");
        saveGameMenuItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent event) {
        		System.out.println(control.getPlayerScore()+control.getEnemyScore());
            	Service.saveGame(control, username, DAOpassword);
                Service.newGame(arena, control, view);
            }
        });
        
        JMenuItem exitMenuItem = new JMenuItem("Exit");
        exitMenuItem.setMnemonic(KeyEvent.VK_E);
        exitMenuItem.setToolTipText("Exit application");
        exitMenuItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent event) {
                System.exit(0);
            }
        });

        file.add(newGameMenuItem);
        file.add(saveGameMenuItem);
        file.add(exitMenuItem);
        menubar.add(file);

        setJMenuBar(menubar);
    }
}
