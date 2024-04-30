/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package main;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author DELL
 */
public class config {
    GamePanel gp;
    public config(GamePanel gp){
        this.gp = gp;
    }
    public void saveConfig(){
        try { 
            BufferedWriter bw = new BufferedWriter (new FileWriter("config.txt"));
            //music
            bw.write(String.valueOf(gp.music.volumeScale));
            bw.newLine();
            
            //se
            bw.write(String.valueOf(gp.se.volumeScale));
            bw.newLine();
            
            bw.close();
        } catch (IOException ex) {
            Logger.getLogger(config.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public void loadConfig(){
        try {
            BufferedReader br = new BufferedReader(new FileReader("config.txt"));
            String a = br.readLine();
            
            //music
            gp.music.volumeScale = Integer.parseInt(a);
            
            //se
            a = br.readLine();
            gp.se.volumeScale = Integer.parseInt(a);
            
            br.close();
            
        } catch (Exception e) {
           e.printStackTrace();
        }
    }
        
}
