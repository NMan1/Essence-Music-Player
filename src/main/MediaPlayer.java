package main;

import main.controllers.MainFrameController;
import java.util.ArrayList;

public final class MediaPlayer {

    private static MainFrameController mainFrameController;

    private static Track currentTrack;

    private static int currentPosition = 0;

    private static ArrayList<Track> trackQueue;

    private static float volume = 1;

    private static float lastVolume = 1;

    private static Boolean paused = true;

    public static void setMainController(MainFrameController mainFrameController) {
        MediaPlayer.mainFrameController = mainFrameController;
    }

    public static void setTrack(Track track) {
        currentTrack = track;
        currentPosition = 0;
        paused = false;
        mainFrameController.updatePlayer(currentTrack);
    }

    public static Track getCurrentTrack() {
        return currentTrack;
    }

    public static Boolean getPaused() {
        return paused;
    }

    public static void setPaused(Boolean paused) {
        MediaPlayer.paused = paused;
    }

    public static int getCurrentPosition() {
        return currentPosition;
    }

    public static void setCurrentPosition(int currentPosition) {
        MediaPlayer.currentPosition = currentPosition;
    }

    public static float getVolume() {
        return volume;
    }

    public static void setVolume(float volume) {
        MediaPlayer.volume = volume;
    }

    public static float getLastVolume() {
        return lastVolume;
    }

    public static void setLastVolume(float lastVolume) {
        MediaPlayer.lastVolume = lastVolume;
    }
}
