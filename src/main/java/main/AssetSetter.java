/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package main;

import Entity.QuestNPC;
import Entity.tradeNPC;
import Monster.slimeMon;
import object.chestObject;
import object.coinObject;
import object.doorObject;
import object.katanaSwordObject;
import object.keyObject;
import object.potionObject;
import object.stickObject;

/**
 *
 * @author DELL
 */
public class AssetSetter {
    GamePanel gp;
    public AssetSetter(GamePanel gp){
        this.gp = gp;
    }
    public void setObject(){
        int mapNum = 0;
        int i =0;
        gp.obj[mapNum][i] = new keyObject(gp);
        gp.obj[mapNum][i].worldX = gp.titleSize * 42;
        gp.obj[mapNum][i].worldY = gp.titleSize * 22;
        i++;
        gp.obj[mapNum][i] = new coinObject(gp);
        gp.obj[mapNum][i].worldX = gp.titleSize * 23;
        gp.obj[mapNum][i].worldY = gp.titleSize *8;
        i++;
        gp.obj[mapNum][i] = new keyObject(gp);
        gp.obj[mapNum][i].worldX = gp.titleSize *21;
        gp.obj[mapNum][i].worldY = gp.titleSize *22;
        i++;
        gp.obj[mapNum][i] = new chestObject(gp);
        gp.obj[mapNum][i].worldX = gp.titleSize *10;
        gp.obj[mapNum][i].worldY = gp.titleSize * 8;
        i++;
        gp.obj[mapNum][i] = new doorObject(gp);
        gp.obj[mapNum][i].worldX = gp.titleSize *21;
        gp.obj[mapNum][i].worldY = gp.titleSize *23;
        i++;
        gp.obj[mapNum][i] = new katanaSwordObject(gp);
        gp.obj[mapNum][i].worldX = gp.titleSize *23;
        gp.obj[mapNum][i].worldY = gp.titleSize *39;
        i++;
        gp.obj[mapNum][i] = new stickObject(gp);
        gp.obj[mapNum][i].worldX = gp.titleSize *22;
        gp.obj[mapNum][i].worldY = gp.titleSize *39;
        i++;
        gp.obj[mapNum][i] = new potionObject(gp);
        gp.obj[mapNum][i].worldX = gp.titleSize *21;
        gp.obj[mapNum][i].worldY = gp.titleSize *39;
    }
    public void setNPC(){
        int mapNum = 0;
        int i= 0;
        gp.npc[mapNum][i] = new QuestNPC (gp);
        gp.npc[mapNum][i].worldX =gp.titleSize*21;
        gp.npc[mapNum][i].worldY = gp.titleSize*21;
        i++;
        gp.npc[mapNum][i] = new QuestNPC (gp);
        gp.npc[mapNum][i].worldX =gp.titleSize*22;
        gp.npc[mapNum][i].worldY = gp.titleSize*22;
        mapNum =1;
        i = 0;
        gp.npc[mapNum][i] = new tradeNPC (gp);
        gp.npc[mapNum][i].worldX =gp.titleSize*12;
        gp.npc[mapNum][i].worldY = gp.titleSize*7;
        
    }
    public void setMonster(){
        int mapNum = 0;
        int i =0;
        gp.mon[mapNum][i] = new slimeMon(gp);
        gp.mon[mapNum][i].worldX = gp.titleSize*24;
        gp.mon[mapNum][i].worldY = gp.titleSize*24;
        i++;
        
        gp.mon[mapNum][i] = new slimeMon(gp);
        gp.mon[mapNum][i].worldX = gp.titleSize*24;
        gp.mon[mapNum][i].worldY = gp.titleSize*25;
        i++;

        gp.mon[mapNum][i] = new slimeMon(gp);
        gp.mon[mapNum][i].worldX = gp.titleSize*24;
        gp.mon[mapNum][i].worldY = gp.titleSize*26;
        i++;

        gp.mon[mapNum][i] = new slimeMon(gp);
        gp.mon[mapNum][i].worldX = gp.titleSize*24;
        gp.mon[mapNum][i].worldY = gp.titleSize*27;
        i++;

        gp.mon[mapNum][i] = new slimeMon(gp);
        gp.mon[mapNum][i].worldX = gp.titleSize*24;
        gp.mon[mapNum][i].worldY = gp.titleSize*28;
    }
}
