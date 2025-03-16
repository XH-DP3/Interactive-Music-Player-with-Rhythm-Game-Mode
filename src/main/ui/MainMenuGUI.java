package ui;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Map;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.WindowConstants;

import model.SongList;
import persistence.JsonWriter;

// Represents the main menu with options that the user can select
public class MainMenuGUI extends JFrame {
    private static final String MUSIC_LIBRARY_PATH = "data/musicLibrary.json";
    private static final String SONG_LIST_PATH = "data/mySongList.json";
    private static final String FAVORITE_LIST_PATH = "data/myFavoriteSongList.json";
    private JFrame frame;
    private SongListGUI songListGUI;
    private MusicLibraryGUI musicLibraryGUI;
    private Map<String, JButton> buttons;

    // MODIFIES: this
    // EFFECTS: contruct the main menu panel by invoking it
    public MainMenuGUI() {
        songListGUI = new SongListGUI(this);
        musicLibraryGUI = new MusicLibraryGUI(this, songListGUI);
        buttons = new HashMap<>();
        mainMenu();
    }

    // MODIFIES: this
    // EFFECTS: a helper method that will generate the layout
    private void layout(JFrame frame, int row, int col) {
        frame.setLayout(new GridLayout(row, col));
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setSize(500, 200);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    // MODIFIES: this
    // EFFECTS: display the main menu panel
    public void mainMenu() {
        frame = new JFrame("Main Menu");
        createButtons();
        layout(frame, 5, 1);
        addListsActionListeners();
        addPersistentActionListeners();
    }

    // MODIFIES: this
    // EFFECTS: create the buttons for the main menu panel
    private void createButtons() {
        JButton musicLibrary = new JButton("Check music library");
        JButton songList = new JButton("Check your song list");
        JButton favoriteList = new JButton("Check your favorite song list");
        JButton reload = new JButton("Reload your saved lists");
        JButton quit = new JButton("Quit the program");
        buttons.put("musicLibrary", musicLibrary);
        buttons.put("songList", songList);
        buttons.put("favoriteList", favoriteList);
        buttons.put("reload", reload);
        buttons.put("quit", quit);
        frame.add(musicLibrary);
        frame.add(songList);
        frame.add(favoriteList);
        frame.add(reload);
        frame.add(quit);
    }

    // MODIFIES: this
    // EFFECTS: add action listners for the lists-related operations
    private void addListsActionListeners() {
        buttons.get("musicLibrary").addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
                musicLibraryGUI.musicLibrary();
            }
        });
        buttons.get("songList").addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
                songListGUI.songList();
            }
        });
        buttons.get("favoriteList").addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
                // stub
            }
        });
    }

    // MODIFIES: this
    // EFFECTS: adding action listeners for persistent-related operations
    private void addPersistentActionListeners() {
        buttons.get("quit").addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
                quitHelper();
            }
        });
    }

    // EFFECTS: provide options to save the progress when the user click on the quit
    // button.
    private void quitHelper() {
        int choice = JOptionPane.showConfirmDialog(null,
                "Would you like to save your progress?", "Quit Options", JOptionPane.YES_NO_OPTION);
        if (choice == JOptionPane.NO_OPTION) {
            System.exit(1);
        } else {
            saveHelper();
        }
    }

    // EFFECTS: display the save page and provide options of objects which can be
    // saved
    private void saveHelper() {
        frame = new JFrame("Save Helper");
        JButton saveSongList = new JButton("Save your song list");
        JButton menu = new JButton("Return to the menu");
        buttons.put("saveSongList", saveSongList);
        buttons.put("menu", menu);
        frame.add(saveSongList);
        frame.add(menu);
        layout(frame, 2, 1);
        addSaveActionListeners();
    }

    // EFFECTS: add action listeners for saving and invoke writeToFile() method to perform saving
    private void addSaveActionListeners() {
        buttons.get("saveSongList").addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                writeToFile(SONG_LIST_PATH, songListGUI.getSongList());
                JOptionPane.showMessageDialog(null, "Your song list is saved", "Message",
                        JOptionPane.INFORMATION_MESSAGE);
                System.exit(1);
            }
        });
    }

    // EFFECTS: write list to filepath
    private void writeToFile(String filePath, SongList list) {
        JsonWriter writer = new JsonWriter(filePath);
        try {
            writer.open();
            writer.write(list);
            writer.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
}
