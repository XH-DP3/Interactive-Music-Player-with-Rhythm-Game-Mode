package model;

// Represents a list contains songs that are marked as favorite
public class FavoriteSongList extends SongList{

    // REQUIRES: (index >= 0 && index < getSize()) && (!songList.contains(mySong)) && mySong.isFavorite()
    // MODIFIES: this
    // EFFECT: add mySong at the specific index to the favotite list and return true
    @Override
    public boolean addSong(int index, Song mySong) {
        // stub
        return false;
    }

    // REQUIRES: !songList.contains(mySong)
    // MODIFIES: this
    // EFFECT: add mySong to the end of the favorite list and return true;
    @Override
    public boolean addSong(Song mySong) {
        // stub
        return false;
    }
}
