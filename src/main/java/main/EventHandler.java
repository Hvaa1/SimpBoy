/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package main;

import java.awt.Rectangle;

/**
 *
 * @author DELL
 */
public class EventHandler {
    GamePanel gp;
    EventRect eventRect[][][];
    int previousEventX,previousEventY;
    boolean canTouchEvent = true;
    int tempMap ,tempRow ,tempCol;
    public EventHandler(GamePanel gp){
        this.gp = gp;
        eventRect = new EventRect[gp.maxMap][gp.maxWorldCol][gp.maxWorldRow];
        int map =0;
        int col = 0;
        int row = 0;
        while(map <gp.maxMap&&col <gp.maxWorldCol && row<gp.maxWorldRow){
            eventRect[map][col][row] = new EventRect();
            eventRect[map][col][row].x = 23;
            eventRect[map][col][row].y = 23;
            eventRect[map][col][row].width = 2;
            eventRect[map][col][row].height = 2;
            eventRect[map][col][row].eventRectDefaultX = eventRect[map][col][row].x;
            eventRect[map][col][row].eventRectDefaultY =  eventRect[map][col][row].y;
            col ++;
            if (col == gp.maxWorldCol){
                col = 0;
                row ++;
                if(row == gp.maxWorldRow){
                    row = 0;
                    map++;
                }
            }
        }
    }
    public void checkEvent(){
        int xDistance = Math.abs(gp.player.worldX-previousEventX);
        int yDistance = Math.abs(gp.player.worldY-previousEventY);
        int distance = Math.max(xDistance, yDistance);
        if (distance > gp.titleSize){
            canTouchEvent = true;
        }
        if (canTouchEvent == true){
            if (hit(0,23,21,"right")==true){
                damagePit(gp.dialogueState);
            }
            else if (hit(0,23,7,"up")==true){
                healingPool(gp.dialogueState);
            }
            else if(hit(0,10,40,"any")==true){
                teleport(1,12,12);
            }
            else if(hit(1,12,12,"any")==true){
                teleport(0,10,40);
            }
        }
    }
    public boolean hit(int map,int col,int row , String reqDirection){
        boolean hit = false;
        
        if(map == gp.currentMap){
            gp.player.solidArea.x = gp.player.worldX+ gp.player.solidArea.x;
        gp.player.solidArea.y = gp.player.worldY + gp.player.solidArea.y;
        eventRect[map][col][row].x = col*gp.titleSize + eventRect[map][col][row].x;
        eventRect[map][col][row].y = row*gp.titleSize + eventRect[map][col][row].y;
        if (gp.player.solidArea.intersects(eventRect[map][col][row])){
            if (gp.player.direction.contentEquals(reqDirection)||reqDirection.contentEquals("any")){
                hit = true;
            }
        }
        gp.player.solidArea.x = gp.player.solidAreaDefaultX;
        gp.player.solidArea.y = gp.player.solidAreaDefaultY;
        eventRect[map][col][row].x = eventRect[map][col][row].eventRectDefaultX;
        eventRect[map][col][row].y = eventRect[map][col][row].eventRectDefaultY;
       
        } 
        return hit;
    }
    public void damagePit(int gameState){
        gp.gameState = gameState;
        gp.ui.currentDialogue = "ban da bi trap";
        gp.player.life -=1;
    }
    public void healingPool (int gameState){
        if (gp.keyC.enter==true){
            gp.gameState = gameState;
            gp.player.attackCanceled = true;
            gp.ui.currentDialogue = "ba da tai sinh";
            gp.player.life = gp.player.maxLife;
            gp.aSetter.setMonster();
        }
    }
    public void teleport(int map,int col,int row){
        gp.gameState = gp.transitionState;
        tempMap = map;
        tempRow = row;
        tempCol = col;
        canTouchEvent = false;
    }
}
