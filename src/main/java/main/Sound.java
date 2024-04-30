/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package main;

/**
 *
 * @author DELL
 */

import java.io.File;
import java.io.IOException;
import java.net.URL;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.FloatControl;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

public class Sound {
    Clip clip;
    File file[] = new File[30]; 
    FloatControl fc;
    int volumeScale =3;
    float volume;
    public Sound (){
        file[0] = new File("res/Music/BlueBoyAdventure.wav");
        file[1] = new File("res/Music/coin.wav");
        file[2] = new File("res/Music/dooropen.wav");
        file[3] = new File("res/Music/hitmonster.wav");
        file[4] = new File("res/Music/receivedamage.wav");
    }
    public void setFile(int i) throws UnsupportedAudioFileException, LineUnavailableException{
        try{
            AudioInputStream ais = AudioSystem.getAudioInputStream(file[i]);
            clip = AudioSystem.getClip();
            clip.open(ais);
            fc = (FloatControl)clip.getControl(FloatControl.Type.MASTER_GAIN);
            checkVolume();
        }
        catch(IOException e){
        }
        
    }
    public void play(){
        clip.start();
    }
    public void loop(){
        clip.loop(Clip.LOOP_CONTINUOUSLY);
    }
    public void stop(){
        clip.stop();
    }
    public void checkVolume(){
         switch (volumeScale){
             case 0 : volume = -80f;break;
             case 1 : volume = -20f;break;
             case 2 : volume = -12f;break;
             case 3 : volume = -5f;break;
             case 4 : volume = 1f;break;
             case 5 : volume = 6f;break;
         }
         fc.setValue(volume);
    }
}
