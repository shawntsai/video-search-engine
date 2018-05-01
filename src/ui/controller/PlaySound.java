package ui.controller;

import java.io.*;

import javax.sound.sampled.*;

/**
 * 
 * <Replace this with a short description of the class.>
 * 
 * @author Giulio
 */
public class PlaySound {

    private InputStream waveStream;
    private Clip dataClip = null;
    private int stopTime = 0;
    private final String pathToAudioFile;


    private final int EXTERNAL_BUFFER_SIZE = 524288; // 128Kb

    /**
     * CONSTRUCTOR
     */
    public PlaySound(String filePath) {
	    this.pathToAudioFile = filePath;
    }

    public void play() throws PlayWaveException {
        try {
            this.waveStream = new FileInputStream(this.pathToAudioFile);
        } catch (FileNotFoundException e2) {
            e2.printStackTrace();
        }
        AudioInputStream audioInputStream = null;
        try {
            //add buffer for mark/reset support, modified by Jian
            InputStream bufferedIn = new BufferedInputStream(this.waveStream);
            audioInputStream = AudioSystem.getAudioInputStream(bufferedIn);
//            System.out.println("Audio frame length:" + audioInputStream.getFrameLength());

        } catch (UnsupportedAudioFileException e1) {
            throw new PlayWaveException(e1);
        } catch (IOException e1) {
            throw new PlayWaveException(e1);
        }

        try {
            dataClip = AudioSystem.getClip();
        } catch (LineUnavailableException e1) {
            throw new PlayWaveException(e1);
        }

        try {
            // Starts the music :P
            dataClip.open(audioInputStream);
            dataClip.setFramePosition(stopTime);  // Must always rewind!
            dataClip.loop(stopTime);
            dataClip.start();
        } catch (LineUnavailableException | IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    public void pause() {
        stopTime = dataClip.getFramePosition();
//        System.out.println("STOP AUDIO at: " + stopTime);
        dataClip.stop();

    }

    public void loop(){
        dataClip.loop(Clip.LOOP_CONTINUOUSLY);
    }

    public void stop(){
        stopTime = 0;
        dataClip.stop();
    }

    public void setStartTime(int time) {
        stopTime = time;
    }
}
