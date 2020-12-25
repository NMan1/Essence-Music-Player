package main;

import javafx.scene.image.Image;

public class Track {

    public String name;

    public String album;

    public String artist;

    public Image albumCover;

    public int length;

    public Track(String name, String album, String artist, int length) {
        this.name = name;
        this.album = album;
        this.artist = artist;
        this.length = length;
    }
}
