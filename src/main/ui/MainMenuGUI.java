package ui;

import java.awt.GridLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.WindowConstants;

// Represents the main menu with options that the user can select
public class MainMenuGUI extends JFrame{
    public MainMenuGUI() {
        JFrame frame = new JFrame("Main Menu");
	    frame.setLayout(new GridLayout(4,1));
	    frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
	    frame.setSize(500,200);
        JButton musicLibrary = new JButton("Check music library");
        JButton songList = new JButton("Check your song list");
        JButton favoriteList = new JButton("Check your favorite song list");
        JButton reload = new JButton("Reload your saved lists");
	    frame.add(musicLibrary);
        frame.add(songList);
        frame.add(favoriteList);
        frame.add(reload);
	    frame.setVisible(true);
    }
}
