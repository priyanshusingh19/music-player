package MusicPlayer.service;



import MusicPlayer.enums.Status;
import MusicPlayer.models.Audio;

import javax.sound.sampled.*;
import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.ListIterator;
import java.util.Scanner;

public class AudioStreamPlay {

    Scanner sc = new Scanner(System.in);
    private AudioInputStream audioInputStream;
    private Clip clip;
    private String path;
    private Status status = Status.stoped;

    public AudioStreamPlay() {
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public void play(String path) throws LineUnavailableException, IOException, UnsupportedAudioFileException {
        this.path = path;
        File file = new File(path);
        this.setStatus(Status.playing);
        AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(file);
        this.clip = AudioSystem.getClip();
        this.clip.open(audioInputStream);
        this.clip.start();
    }

    public void play(List<Audio> playlist) throws LineUnavailableException, IOException, UnsupportedAudioFileException {
       ListIterator<Audio> ltr = playlist.listIterator();
        while (ltr.hasNext()) {
            this.path = ltr.next().getPath();
            File file = new File(path);
            this.setStatus(Status.playing);
            System.out.println(" Currently Playing -");
            AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(file);
            this.clip = AudioSystem.getClip();
            this.clip.open(audioInputStream);
            this.clip.start();




          /*  JOptionPane.showMessageDialog(null, "press ok to pause");
            long cliptime = clip.getMicrosecondPosition();
            this.clip.stop();

            JOptionPane.showMessageDialog(null, " press ok to resume");
            clip.setMicrosecondPosition(cliptime);
            clip.start();

            JOptionPane.showMessageDialog(null, "press ok to stop");
            clip.stop();
            */

        }
    }

    public void play(Audio playlist) throws LineUnavailableException, IOException, UnsupportedAudioFileException, InterruptedException {

                File file = new File((String) playlist.getPath());
                AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(file);
                System.out.println(" Currently Playing - "+playlist.getTitle()+ " Duratiom - "+playlist.getDuration());
               // System.out.println("hello");
                this.setStatus(Status.playing);
                this.clip = AudioSystem.getClip();
                this.clip.open(audioInputStream);
                this.clip.start();
            }


    public void close()
    {

        this.setStatus(Status.closed);
            this.clip.close();
            this.clip.flush();
    }
    public void stop() {
        if (this.getStatus()==Status.playing) {
            this.setStatus(Status.stoped);
            this.clip.stop();
            System.out.println("Stopped");
        }
        else
            System.out.println("Already stopped");
    }
    public void resume() throws UnsupportedAudioFileException, LineUnavailableException, IOException {
        this.setStatus(Status.playing);
        this.clip.start();
    }

    public void resetAudioStream() throws UnsupportedAudioFileException, IOException,
            LineUnavailableException
    {
        audioInputStream = AudioSystem.getAudioInputStream(new File(this.path).getAbsoluteFile());
        clip.open(audioInputStream);
        clip.loop(Clip.LOOP_CONTINUOUSLY);
    }
}
