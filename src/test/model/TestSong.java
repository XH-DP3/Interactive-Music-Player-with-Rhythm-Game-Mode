package model;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;
import java.io.*;
import java.util.*;

public class TestSong {
    private Song mySong;

    @Before
    public void setup() {
        mySong = new Song("Patience", "Take That", "Pop", 202);
    }

    @Test
    public void testConstructor() {
        assertEquals("Patience", mySong.getTitle());
        assertEquals("Take That", mySong.getAuthor());
        assertEquals("Pop", mySong.getGenre());
        assertEquals(202, mySong.getDuration());
    }

    @Test
    public void testSetDifferentGenre() {
        assertEquals("Pop", mySong.getGenre());
        mySong.setGenre("Classic");
        assertEquals("Classic", mySong.getGenre());
    }

    @Test
    public void testSetSameGenre() {
        assertEquals("Pop", mySong.getGenre());
        mySong.setGenre("Pop");
        assertEquals("Pop", mySong.getGenre());
    }

    @Test
    public void testSetInvalidDuration() {
        assertEquals(202, mySong.getDuration());
        mySong.setDuration(-10);
        assertEquals(202, mySong.getDuration());
    }

    @Test
    public void testSetDuration() {
        assertEquals(202, mySong.getDuration());
        mySong.setDuration(60);
        assertEquals(60, mySong.getDuration());
    }

    @Test
    public void testReadEmptyLyricsFile() {
        File f = new File("src/test/model/TestReadEmptyLyrics.txt");
        try {
            mySong.readLyrics(f);
        } catch (IOException e) {
            e.printStackTrace();
        }
        List<String> myLyrics = mySong.getLyrics();
        assertEquals(0, myLyrics.size());
    }

    @Test
    public void testReadLyricsFile() {
        File f = new File("src/test/model/TestReadLyrics.txt");
        try {
            mySong.readLyrics(f);
        } catch (IOException e) {
            e.printStackTrace();
        }
        List<String> myLyrics = mySong.getLyrics();
        assertEquals(6, myLyrics.size());
    }

    @Test
    public void testPlaySong() {
        assertFalse(mySong.getPlayingStatus());
        mySong.playSong();
        assertTrue(mySong.getPlayingStatus());
    }

    @Test
    public void testPauseSong() {
        mySong.playSong();
        assertTrue(mySong.getPlayingStatus());
        mySong.pauseSong();
        assertFalse(mySong.getPlayingStatus());
    }

    @Test
    public void testMarkedAsFavorite() {
        mySong.markedAsFavorite();
        assertTrue(mySong.isFavorite());
    }
}
