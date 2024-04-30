/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package main;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

/**
 *
 * @author DELL
 */
public class KeyControl implements KeyListener {
    public boolean up,down,right,left,enter;
    public GamePanel gp;
    //Debug
    boolean showDebug = false;
    public KeyControl(GamePanel gp){
        this.gp=gp;
    }
    @Override
    public void keyTyped(KeyEvent e) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void keyPressed(KeyEvent e) {
        int code = e.getKeyCode();
        //title state
        if (gp.gameState == gp.titleState){
            titleState(code);
        }
        //Play state
        else if (gp.gameState == gp.playState){
            playState(code);
        }
        //pause
        else if (gp.gameState == gp.pauseState){
            pauseState(code);
        }
        //dialogue
        else if (gp.gameState == gp.dialogueState){
            dialogueState(code);
        }
        //character
        else if(gp.gameState == gp.characterState){
            characterState(code);
        }
        else if (gp.gameState == gp.optionState){
            optionState(code);
        }
        else if (gp.gameState == gp.gameOverState){
            gameOverState(code);
        }
        
    }
    public void titleState(int code){
        if (gp.ui.titleScreenState ==0){
                if (code == KeyEvent.VK_UP || code == KeyEvent.VK_W){
                    gp.ui.commandNum --;
                    if (gp.ui.commandNum < 0){
                        gp.ui.commandNum =2;
                    }
                }
                if (code == KeyEvent.VK_DOWN || code == KeyEvent.VK_S){
                    gp.ui.commandNum ++;
                    if (gp.ui.commandNum >2){
                        gp.ui.commandNum = 0;
                    }
                }
                if(code == KeyEvent.VK_ENTER){
                    if (gp.ui.commandNum == 0){
                        gp.ui.titleScreenState = 1 ;
                    }
                    if (gp.ui.commandNum == 1){
                    
                    }
                    if(gp.ui.commandNum == 2){
                        System.exit(0);
                    }
                }
            }
            else if (gp.ui.titleScreenState == 1){
                if(code == KeyEvent.VK_ENTER){
                    gp.gameState = gp.playState;
                }
            }
    }
    public void playState(int code){
        if (code == KeyEvent.VK_UP || code == KeyEvent.VK_W){
            up = true;
        }
        if (code == KeyEvent.VK_RIGHT || code == KeyEvent.VK_D){
            right = true;
        }
        if (code == KeyEvent.VK_DOWN || code == KeyEvent.VK_S){
            down = true;
        }
        if (code == KeyEvent.VK_LEFT || code == KeyEvent.VK_A){
            left = true;
        }  
        if (code == KeyEvent.VK_SPACE){
            gp.gameState = gp.pauseState;
        }
        if(code == KeyEvent.VK_C){
            gp.gameState = gp.characterState;
        }
        if (code == KeyEvent.VK_ENTER){
            enter = true;
        }
            //Debug
        if (code == KeyEvent.VK_T){
            if (showDebug == false){
                showDebug = true;
            }
            else if (showDebug == true){
                showDebug = false;
            }
        }
        if(code == KeyEvent.VK_R){
            switch (gp.currentMap){
                case 0 :gp.tileM.loadMap("res/map/worldV3.txt",0);break;
                case 1 : gp.tileM.loadMap("res/map/interior01.txt",1);break;
            }
        }
        if(code ==KeyEvent.VK_ESCAPE){
            gp.gameState = gp.optionState;
        }
    }
    public void pauseState(int code){
        if (code == KeyEvent.VK_SPACE){
                gp.gameState = gp.playState;
            }
    }
    public void dialogueState(int code){
        if (code == KeyEvent.VK_ENTER){
                gp.gameState = gp.playState;
            }
    }
    public void characterState(int code){
        if(code == KeyEvent.VK_C){
                gp.gameState = gp.playState;
        }
        if (code == KeyEvent.VK_UP || code == KeyEvent.VK_W){
            if(gp.ui.slotRow != 0){
                gp.ui.slotRow--;
            }
        }
        if (code == KeyEvent.VK_RIGHT || code == KeyEvent.VK_D){
            if(gp.ui.slotCol != 4){
                gp.ui.slotCol++;
            }
        }
        if (code == KeyEvent.VK_DOWN || code == KeyEvent.VK_S){
            if(gp.ui.slotRow !=3){
                gp.ui.slotRow++;
            }
        }
        if (code == KeyEvent.VK_LEFT || code == KeyEvent.VK_A){
            if(gp.ui.slotCol!=0){
                gp.ui.slotCol--;
            }
        }
        if(code == KeyEvent.VK_ENTER){
            gp.player.selectItem();
        }
    }
    public void optionState(int code){
        if(code == KeyEvent.VK_ESCAPE){
                gp.gameState = gp.playState;
        }
        if(code == KeyEvent.VK_ENTER){
            enter = true;
        }
        int maxCommandNum = 0;
        switch(gp.ui.subState){
            case 0 :maxCommandNum = 4;break;
            case 2 :maxCommandNum = 1;
        }
        if(code == KeyEvent.VK_W || code == KeyEvent.VK_UP){
            gp.ui.commandNum--;
            if(gp.ui.commandNum < 0){
                gp.ui.commandNum = maxCommandNum;
            }
        }
        if(code == KeyEvent.VK_S || code == KeyEvent.VK_DOWN){
            gp.ui.commandNum++;
            if(gp.ui.commandNum > maxCommandNum ){
                gp.ui.commandNum = 0;
            } 
        }
        if (code == KeyEvent.VK_A){
            if (gp.ui.subState == 0){
                if(gp.ui.commandNum == 0 && gp.music.volumeScale > 0){
                    gp.music.volumeScale --;
                    gp.music.checkVolume();
                }
                if(gp.ui.commandNum == 1 && gp.se.volumeScale > 0){
                    gp.se.volumeScale --;
                }
            }
        }
        if (code ==KeyEvent.VK_D){
            if (gp.ui.subState == 0){
                if(gp.ui.commandNum == 0 && gp.music.volumeScale <5){
                    gp.music.volumeScale ++;
                    gp.music.checkVolume();
                }
                if(gp.ui.commandNum == 1 && gp.se.volumeScale <5){
                    gp.se.volumeScale ++;
                }
            }
        }
    }
    public void gameOverState(int code){
        if (code == KeyEvent.VK_UP || code == KeyEvent.VK_W){
            gp.ui.commandNum --;
            if (gp.ui.commandNum < 0){
                gp.ui.commandNum =1;
            }
        }
        if (code == KeyEvent.VK_DOWN || code == KeyEvent.VK_S){
            gp.ui.commandNum ++;
            if (gp.ui.commandNum > 1){
                gp.ui.commandNum = 0;
            }
        }
        if (code == KeyEvent.VK_ENTER){
            if (gp.ui.commandNum == 0){
                gp.gameState = gp.playState;
                gp.retry();
                try {
                    gp.playMusic(0);
                } catch (UnsupportedAudioFileException ex) {
                    Logger.getLogger(KeyControl.class.getName()).log(Level.SEVERE, null, ex);
                } catch (LineUnavailableException ex) {
                    Logger.getLogger(KeyControl.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            else if(gp.ui.commandNum == 1){
                gp.gameState = gp.titleState;
                gp.restart();
            }
        }
    }
    @Override
    public void keyReleased(KeyEvent e) {
        int code = e.getKeyCode();
        if (code == KeyEvent.VK_UP || code == KeyEvent.VK_W){
            up = false;
        }
        if (code == KeyEvent.VK_RIGHT || code == KeyEvent.VK_D){
            right = false;
        }
        if (code == KeyEvent.VK_DOWN || code == KeyEvent.VK_S){
            down = false;
        }
        if (code == KeyEvent.VK_LEFT || code == KeyEvent.VK_A){
            left = false;
        }  
    }
    
    
}
