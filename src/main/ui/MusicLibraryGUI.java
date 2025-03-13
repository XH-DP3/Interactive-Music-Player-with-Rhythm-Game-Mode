package ui;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.WindowConstants;

import model.FavoriteSongList;
import model.Song;
import model.SongList;

// Represent the song list panel including the music library, song list, and favorite list
public class MusicLibraryGUI extends JFrame{
    private JButton add;
    private JButton delete;
    private JButton mainMenu;
    private SongList musicLibrary;
    private Song s1 = new Song("Payphone", "Maroon 5", "Pop", 231);
    private Song s2 = new Song("Everybody Hurts", "Avril Lavigne", "Pop", 221);
    private Song s3 = new Song("Innocence", "Avril Lavigne", "Pop", 233);
    private Song s4 = new Song("Whataya Want from Me", "Adam Lambert", "Pop", 227);
    private Song s5 = new Song("Like I Do", "J.Tajor", "R&B", 149);

    // MODIFIES: this
    // EFFECTS: consturct the music library with default songs
    public MusicLibraryGUI() {
        musicLibrary = new SongList();
        musicLibrary.addSong(s1);
        musicLibrary.addSong(s2);
        musicLibrary.addSong(s3);
        musicLibrary.addSong(s4);
        musicLibrary.addSong(s5);
    }

    // EFFECTS: a helper method that will generate the layout
    private void layout(JFrame frame, int row, int col) {
        frame.setLayout(new GridLayout(row, col));
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setSize(500, 200);
        frame.setLocationRelativeTo(null);
	    frame.setVisible(true);
    }

    // EFFECTS: generating JButton for song and return the JButton
    private JButton generateJButtonForSong(Song mySong) {
        JButton button = new JButton(displaySong(mySong));
        return button;
    }

    // EFFECTS: return a string representation to display the song  
    private String displaySong(Song mySong) {
        return mySong.getTitle() + ", " + mySong.getAuthor() + ", " + mySong.getGenre() + ", " + mySong.getDuration();
    }

    // EFFECTS: invoke the music library page
    public void musicLibrary() {
        JFrame frame = new JFrame("Music Library");
        add = new JButton("Add song to your song list");
        delete = new JButton("Delete song from your song list");
        mainMenu = new JButton("Return to the main menu");
        frame.add(add);
        frame.add(delete);
        frame.add(mainMenu);
        layout(frame, 3, 1);
        addActionListeners();
    }

    // EFFECTS: add action listener for each JButton object and will invoke the corresponding method to perform actions. 
    private void addActionListeners() {
        add.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                addHelper();
            }
        });
    }

    // EFFECTS: display the available songs in the music library
    private void addHelper() {
        JFrame frame = new JFrame("Songs in Music Library");
        for (Song s : musicLibrary.getSongs()) {
            frame.add(generateJButtonForSong(s));
        }
        JButton previousPage = new JButton("Return to the previous page");
        frame.add(previousPage);
        layout(frame, musicLibrary.getSongs().size()+1, 1);
    }
}

