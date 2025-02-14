package model;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.Before;
import org.junit.Test;

public class TestSongList {
    
    private SongList list;
    private Song s1;
    private Song s2;
    private Song s3;

    @Before
    public void setup() {
        list = new SongList();
        s1 = new Song("Payphone", "Maroon 5", "Pop", 231);
        s2 = new Song("Everybody Hurts", "Avril Lavigne", "Pop", 221);
        s3 = new Song("Innocence", "Avril Lavigne", "Pop", 233);
    }

    @Test
    public void testConstructor() {
        assertEquals(0, list.getSize());
    }

    @Test
    public void testFindSongByTitleNotFound() {
        String title = "Best Song Ever";
        assertNull(list.findSongByTitle(title));
    }

    @Test
    public void testFindSongByTitleFound() {
        String title = "Payphone";
        assertEquals(s1.getTitle(), list.findSongByTitle(title).getTitle());
    }

    @Test
    public void testFindByAuthorNotFound() {
        String author = "One Direction";
        assertEquals(0, list.findSongByAuthor(author).size());
    }

    @Test
    public void testFindByAuthorOneSongFound() {
        String author = "Maroon 5";
        List<Song> l = list.findSongByAuthor(author);
        assertEquals(1, l.size());
        assertEquals(s1, l.get(0));
    }

    @Test
    public void testFindByAuthorMultipleSongsFound() {
        String author = "Avril Lavigne";
        List<Song> l = list.findSongByAuthor(author);
        assertEquals(2, l.size());
        assertEquals(s2, l.get(0));
        assertEquals(s3, l.get(1));
    }

    @Test
    public void testAddOneSongWithNoIndex() {
        assertTrue(list.addSong(s1));
        List<Song> l = list.getSongList();
        assertEquals(1, l.size());
        assertEquals(s1, l.get(0));
    }

    @Test
    public void testAddMultipleSongsWithNoIndex() {
        assertTrue(list.addSong(s1));
        assertTrue(list.addSong(s2));
        assertTrue(list.addSong(s3));
        List<Song> l = list.getSongList();
        assertEquals(3, l.size());
        assertEquals(s1, l.get(0));
        assertEquals(s2, l.get(1));
        assertEquals(s3, l.get(2));
    }

    @Test
    public void testAddExtraSongWithIndex() {
        testAddExtraSongWithIndex();
        Song s4 = new Song("Whataya Want from me", "Adam Lambert", "Pop", 227);
        assertTrue(list.addSong(s4, 1));
        assertEquals(s4, list.getSongList().get(1));
    }

    @Test
    public void testDeleteSongWithAnIndex() {
        assertTrue(list.addSong(s1));
        assertTrue(list.addSong(s2));
        assertTrue(list.addSong(s3));
        assertTrue(list.deleteSong(0));
        assertEquals(2, list.getSize());
        assertFalse(list.getSongList().contains(s1));
    }

    @Test
    public void testDeleteSongWithExistedSongName() {
        assertTrue(list.addSong(s1));
        assertTrue(list.addSong(s2));
        assertTrue(list.addSong(s3));
        assertTrue(list.deleteSong(s1.getTitle()));
        assertEquals(2, list.getSize());
        assertFalse(list.getSongList().contains(s1));
    }

    @Test
    public void testDeleteSongWithNonExistedSongName() {
        assertTrue(list.addSong(s1));
        assertTrue(list.addSong(s2));
        assertTrue(list.addSong(s3));
        assertFalse(list.deleteSong("Unknown Song"));
        assertEquals(3, list.getSize());
    }

    @Test
    public void testReset() {
        assertTrue(list.addSong(s1));
        assertTrue(list.addSong(s2));
        assertTrue(list.addSong(s3));
        assertEquals(3, list.getSize());
        list.reset();
        assertEquals(0, list.getSize());
    }

    @Test
    public void setSortByDurationStarLowest() {
        assertTrue(list.addSong(s1));
        assertTrue(list.addSong(s2));
        assertTrue(list.addSong(s3));
        list.sortByDuration(true);
        List<Song> sortedList = list.getSongList();
        assertEquals(s2, sortedList.get(0));
        assertEquals(s1, sortedList.get(1));
        assertEquals(s3, sortedList.get(3));
    }

    @Test
    public void setSortByDurationStarHighest() {
        assertTrue(list.addSong(s1));
        assertTrue(list.addSong(s2));
        assertTrue(list.addSong(s3));
        list.sortByDuration(false);
        List<Song> sortedList = list.getSongList();
        assertEquals(s3, sortedList.get(0));
        assertEquals(s1, sortedList.get(1));
        assertEquals(s2, sortedList.get(3));
    }
}
