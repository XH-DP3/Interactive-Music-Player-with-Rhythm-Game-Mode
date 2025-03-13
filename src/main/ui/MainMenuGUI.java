package ui;

import java.awt.Component;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;
import javax.swing.WindowConstants;

// Represents the main menu with options that the user can select
public class MainMenuGUI extends JFrame{
    private JButton musicLibrary;
    private JButton songList;
    private JButton favoriteList;
    private JButton reload;
    private MusicLibraryGUI songListPanel = new MusicLibraryGUI();

    public MainMenuGUI() {
        super("Main Menu");
	    setLayout(new GridLayout(4,1));
	    setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
	    setSize(500,200);
        createButtons();
        setLocationRelativeTo(null);
	    setVisible(true);
    }

    // EFFECTS: create the buttons
    private void createButtons() {
        musicLibrary = new JButton("Check music library");
        songList = new JButton("Check your song list");
        favoriteList = new JButton("Check your favorite song list");
        reload = new JButton("Reload your saved lists");
	    add(musicLibrary);
        add(songList);
        add(favoriteList);
        add(reload);
        addActionListeners();
    }

    // EFFECTS: add action listners()
    private void addActionListeners() {
        musicLibrary.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {    
                handleMusicLibraryClicked();
            }
        });
        songList.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {    
                handleSongListClicked();
            }
        });
        favoriteList.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {    
                handleFavoriteListClicked();
            }
        });
    } 

    // EFFECTS: handle the case when music library button is clicked
    private void handleMusicLibraryClicked() {
        songListPanel.musicLibrary();
    }

    // EFFECTS: handle the case when song list button is clicked
    private void handleSongListClicked() {
        // stub
    }

    // EFFECTS: handle the case when favorite list button is clicked
    private void handleFavoriteListClicked() {
        // stub
    }

    // EFFECTS: handle the case when reload button is clicked
    private void handleReloadClicked() {
        // stub
    }
}
