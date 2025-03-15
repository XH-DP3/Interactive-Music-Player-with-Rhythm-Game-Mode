package ui;

import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.WindowConstants;

import model.Song;
import model.SongList;

// Represent the user's song list
public class SongListGUI {
    private SongList mySongList;
    private MainMenuGUI mainMenuGUI;
    private MusicLibraryGUI musicLibraryGUI;
    private JFrame frame;
    private JLabel label;
    private JButton add;
    private JButton deletePage;
    private JButton delete;
    private JButton markAsFavorite;
    private JButton previous;
    private JButton menu;
    private JCheckBox box;
    private Map<JCheckBox, Song> mapSongs = new HashMap<>();

    // MODIFIES: this
    // EFFECTS: construct an empty song list with refernece to the previous page
    // (main menu and music library)
    public SongListGUI(MainMenuGUI mainMenuGUI) {
        mySongList = new SongList();
        this.mainMenuGUI = mainMenuGUI;
    }

    // EFFECTS: a helper method that will generate the layout
    private void layout(JFrame frame, int row, int col) {
        frame.setLayout(new GridLayout(row, col));
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setSize(1000, 700);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    // EFFECTS: return a string representation to display the song
    private String displaySong(Song mySong) {
        return "Title: " + mySong.getTitle() + ", "
                + "   Author: " + mySong.getAuthor() + ", "
                + "   Is Favorite: " + mySong.isFavorite();
    }

    // EFFECTS: print song info
    private void printSongInfo(Song mySong) {
        JPanel panel = new JPanel();
        JLabel songLabel = new JLabel(displaySong(mySong));
        ImageIcon originalIcon = new ImageIcon(mySong.getImageFilePath());
        Image resizedImage = originalIcon.getImage().getScaledInstance(100, 100, Image.SCALE_SMOOTH);
        ImageIcon resizedIcon = new ImageIcon(resizedImage);
        JLabel imageLabel = new JLabel(resizedIcon);
        panel.setLayout(new GridLayout(1, 2));
        panel.add(songLabel);
        panel.add(imageLabel);
        frame.add(panel);
    }

    // EFFECTS: create the buttons for the song list panel
    private void createButtons() {
        add = new JButton("Add song to your favorite list");
        deletePage = new JButton("Delete song from your song list");
        markAsFavorite = new JButton("Mark song as favorite");
        frame.add(add);
        frame.add(deletePage);
        frame.add(markAsFavorite);
    }

    // MODIFIES: this
    // EFFECTS: adding mySong from the music library to the song list
    public void addSongToSongList(Song mySong) {
        mySongList.addSong(mySong);
    }

    // MODIFIES: this
    // EFFECTS: invoke the song list page
    public void songList() {
        frame = new JFrame("Your Song List");
        if (mySongList.getSize() == 0) {
            label = new JLabel("Your song list is empty");
            frame.add(label);
        } else {
            for (Song s : mySongList.getSongs()) {
                printSongInfo(s);
            }
            createButtons();
        }
        menu = new JButton("Return to the menu");
        frame.add(menu);
        layout(frame, mySongList.getSize() + 5, 1);
        addActionListeners();
    }

    // MODIFIES: this
    // EFFECTS: add action listener for each JButton object and will invoke the
    // corresponding method to perform actions.
    private void addActionListeners() {
        menu.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
                mainMenu();
            }
        });
        deletePage.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
                deleteHelper();
            }
        });
        delete.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
                delete();
            }
        });

    }

    // EFFECTS: return to the main menu
    private void mainMenu() {
        mainMenuGUI.mainMenu();
    }

    // MODIFIES: this
    // EFFECTS: display songs in the songlist as buttons and click on
    private void deleteHelper() {
        if (mySongList.getSize() == 0) {
            JOptionPane.showMessageDialog(frame, "Your song list has no songs!");
            songList();
        }
        frame = new JFrame("Select the songs you want to delete");
        for (Song s : mySongList.getSongs()) {
            generateCheckBox(s);
        }
        delete = new JButton("Delete");
        previous = new JButton("Return to the previous page");
        menu = new JButton("Return to the main menu");
        frame.add(delete);
        frame.add(previous);
        frame.add(menu);
        layout(frame, mySongList.getSize() + 3, 1);
        addActionListeners();
    }

    // MODIFIES: this
    // EFFECTS: generating a checkbox for each song in the songlist
    private void generateCheckBox(Song mySong) {
        JCheckBox box = new JCheckBox(displaySong(mySong));
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.add(box);
        frame.add(panel);
        mapSongs.put(box, mySong);
    }

    private void delete() {
        Set<JCheckBox> boxes = mapSongs.keySet();
        for (JCheckBox b : boxes) {
            if (b.isSelected()) {
                Song s = mapSongs.get(b);
                mySongList.deleteSong(s.getTitle());
            }
            frame.dispose();
            songList();
        }
    }
}
