package nl.hanze.CarParkSimulation.controller;

import sun.applet.Main;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

/**
 * Class for playing sound files.
 *
 * @author Joey Boum Bletterman
 * @version 0.1 (13-04-2016)
 */
public class SoundPlayer {

    public static final String PATH = "sounds/";
    public static final String EXIT = "exit.wav";

    public static synchronized void playSound(String filename) {
        new Thread(new Runnable() {
            // The wrapper thread is unnecessary, unless it blocks on the
            // Clip finishing; see comments.
            public void run() {
                try {
                    Clip clip = AudioSystem.getClip();
                    AudioInputStream inputStream = AudioSystem.getAudioInputStream(
                            Main.class.getResourceAsStream(PATH + filename));
                    clip.open(inputStream);
                    clip.start();
                } catch (Exception e) {
                    System.err.println(e.getMessage());
                }
            }
        }).start();
    }
}
