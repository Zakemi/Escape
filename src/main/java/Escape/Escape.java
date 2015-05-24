package Escape;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Insets;

import javax.swing.JDesktopPane;
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
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.KeyEvent;

public class Escape extends JFrame {

	private static final long serialVersionUID = -3689415169655758824L;
	private JPanel contentPane;
	private Arena arena;
	private View view;
	private Controller control;
	private Rank rank;
	private String username = "Guest";
	private String DAOpassword = "pwd";
	public int szam = 0;

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

	public Escape() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(300, 0, 0, 0);
		pack();
		Insets insets = getInsets();
		//this.
		setSize(new Dimension(insets.left + insets.right + 600,
		             insets.top + insets.bottom + 630));
		//setSize(new Dimension(600, 600));
		contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		arena = new Arena(6, 600);
        view = new View(arena);
        control = new Controller(arena, view);
        view.setControl(control);
        rank = new Rank();
		//contentPane.setBorder(null);
		setContentPane(contentPane);
		//contentPane.setLayout(null);
		contentPane.setLayout(new BorderLayout());
		
		initMenu();
		JTabbedPane tabbedPane = new JTabbedPane();
		tabbedPane.addTab("Game", view);
		tabbedPane.addTab("Rank", rank);
		contentPane.add(tabbedPane);
	}
	
private void initMenu() {
        
        createMenuBar();

        setTitle("Escape");
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
    }

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
