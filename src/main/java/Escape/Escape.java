package Escape;

import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Insets;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;

import Escape.Controller.Controller;
import Escape.Model.Arena;
import Escape.Service.Service;
import Escape.View.View;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

public class Escape extends JFrame {

	private static final long serialVersionUID = -3689415169655758824L;
	private JPanel contentPane;
	private Arena arena;
	private View view;
	private Controller control;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Escape frame = new Escape();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public Escape() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(300, 0, 0, 0);
		pack();
		Insets insets = getInsets();
		//this.
		setSize(new Dimension(insets.left + insets.right + 600,
		             insets.top + insets.bottom + 600));
		//setSize(new Dimension(600, 600));
		contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		arena = new Arena(6, 600);
        view = new View(arena);
        control = new Controller(arena, view);
        view.setControl(control);
		//contentPane.setBorder(null);
		setContentPane(contentPane);
		//contentPane.setLayout(null);
		
		this.add(view);
		this.setContentPane(view);
		initMenu();
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
        newGameMenuItem.setMnemonic(KeyEvent.VK_E);
        newGameMenuItem.setToolTipText("Save the actual score and start a new game!");
        newGameMenuItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent event) {
            	Service.saveGame(arena);
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
        file.add(exitMenuItem);
        menubar.add(file);

        setJMenuBar(menubar);
    }
}
