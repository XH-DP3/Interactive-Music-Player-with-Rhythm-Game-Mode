package model;

// Represents a list contains songs that are marked as favorite
public class FavoriteSongList extends SongList {

    // Construct an empty list of my favorite song
    public FavoriteSongList() {
        super();
    }

    // REQUIRES: !favoriteSongs.contains(mySong) && mySong.isFavorite()
    // MODIFIES: this
    // EFFECTS: add mySong to the end of the favorite list and return true;
    @Override
    public boolean addSong(Song mySong) {
        if (mySong.isFavorite()) {
            return super.addSong(mySong);
        }
        return false;
    }
}
