package nl.hanze.CarParkSimulation.controller;

import sun.applet.Main;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import java.io.File;

/**
 * Class for playing sound files.
 *
 * @author Joey Boum Bletterman
 * @version 0.1 (13-04-2016)
 */
public class SoundPlayer {

    public static final String PATH = "/sounds/";
    public static final String EXIT = "exitItem.mp3";
    public static final File FILE = new File(PATH + EXIT);

    /**
     * Method for playing a sound file.
     * @param filename of the sound file.
     */
    public static synchronized void playSound(String path,String filename) {
        new Thread(new Runnable() {
            // The wrapper thread is unnecessary, unless it blocks on the
            // Clip finishing; see comments.
            public void run() {
                try {
                    System.out.println("Start");
                    Clip clip = AudioSystem.getClip();
                    AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(
                            Main.class.getResourceAsStream(PATH+EXIT));
                    clip.open(audioInputStream);
                    clip.start();
                    Thread.sleep(2);
                } catch (Exception e) {
                    System.err.println(e.getMessage());
                }
            }
        }).start();
    }

}
