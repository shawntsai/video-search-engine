package ui.controller;

import javax.swing.*;
import java.awt.image.BufferedImage;

import static java.lang.Thread.currentThread;
import static java.lang.Thread.sleep;

public class VideoPlayer {
    private final static int FRAME_RATE = 30;
    public Thread imageThread;
    public Thread audioThread;
    public int currentFrame;
    private PlayerStatus status;
    private int maxFrameNum;
    private JLabel displayArea;
    private JButton playButton;
    private BufferedImage[] sourceFrames;
    private PlaySound soundPlayer;
    private final static String AUDIO_ERR_MSG = "something went wrong";
    private final int imgToSoundRatio = 221184/150;


    public VideoPlayer(int maxFrameNum, JLabel area, BufferedImage[] sourceFrames,
                       PlaySound soundPlayer, JButton playButton) {
        this.currentFrame = 1;
        this.maxFrameNum = maxFrameNum;
        this.displayArea = area;
        this.sourceFrames = sourceFrames;
        this.soundPlayer = soundPlayer;
        this.playButton = playButton;
    }

    public void play() {
        status = PlayerStatus.Play;
        this.playButton.setEnabled(false);
        imageThread = new Thread(() -> {
            for(int cur = this.currentFrame; cur <= this.maxFrameNum; cur++) {
                displayArea.setIcon(new ImageIcon(sourceFrames[cur]));
                try {
                    sleep(1000/ FRAME_RATE);
                }catch(InterruptedException e) {
                    if(status == PlayerStatus.Stop) {
                      this.currentFrame = 1;
                    } else {
                        this.currentFrame = cur;
                        System.out.println("STOP AT which frame:" + cur);
                        // status = PlayerStatus.Play;
                    }
                    displayArea.setIcon(new ImageIcon(sourceFrames[this.currentFrame]));
                    currentThread().interrupt();
                    break;
                }
            }
            if(status == PlayerStatus.Play) {
                status = PlayerStatus.Stop;
                soundPlayer.stop();
                this.playButton.setEnabled(true);
                this.currentFrame = 1;
            }
        });
        audioThread = new Thread(() -> {
            try {
                soundPlayer.play();
            } catch (PlayWaveException e){
                e.printStackTrace();
                showErrMsg(AUDIO_ERR_MSG);
                return;
            }
        });
        imageThread.start();
        audioThread.start();

    }

    public void pause() {
        status = PlayerStatus.Pause;
        this.playButton.setEnabled(true);
        if(imageThread != null) {
            imageThread.interrupt();
            audioThread.interrupt();
            soundPlayer.pause();
            imageThread = null;
            audioThread = null;
        }
    }

    public void stop() {
        status = PlayerStatus.Stop;
        this.playButton.setEnabled(true);
        if(imageThread != null) {
            imageThread.interrupt();
            audioThread.interrupt();
            soundPlayer.stop();
            imageThread = null;
            audioThread = null;
        } else {
            this.currentFrame = 1;
            displayDefaultImg();
        }
    }

    public void setCurrentFrame(int num) {
        this.currentFrame = num;
        setCurrentSoundFrame(num);
    }

    public void setCurrentSoundFrame(int num) {
        this.soundPlayer.setStartTime(num * imgToSoundRatio);
    }

    public void displayDefaultImg() {
        Thread initThread = new Thread(()->
           this.displayArea.setIcon(new ImageIcon(sourceFrames[this.currentFrame]))
        );
        initThread.start();
    }

    private void showErrMsg(String msg) {
        JOptionPane.showMessageDialog(null,
                msg,
                "Error",
                JOptionPane.ERROR_MESSAGE);
    }
}
