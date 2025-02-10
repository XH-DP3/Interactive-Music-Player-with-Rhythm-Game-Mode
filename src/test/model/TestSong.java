package model;

import static org.junit.Assert.*;

import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;
import java.io.*;
import java.util.*;

public class TestSong {
    private Song mySong;

    @BeforeEach
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
    public void testSetDuration() {
        assertEquals(202, mySong.getDuration());
        mySong.setDuration(60);
        assertEquals(60, mySong.getDuration());
    }

    @Test
    public void testReadEmptyLyricsFile() {
        File f = new File("/Users/huangxinghao/Downloads/TestReadEmptyLyrics");
        assertTrue(mySong.readLyrics(f));
        List<String> myLyrics = mySong.getLyrics();
        assertEquals(0, myLyrics.size());
    }

    @Test
    public void testReadLyricsFile() {
        File f = new File("/Users/huangxinghao/Downloads/TestReadLyrics");
        assertTrue(mySong.readLyrics(f));
        List<String> myLyrics = mySong.getLyrics();
        assertEquals(6, myLyrics.size());   //I wrote 6 lines in the file
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
