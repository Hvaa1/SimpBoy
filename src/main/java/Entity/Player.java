/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Entity;

import java.awt.AlphaComposite;
import main.GamePanel;
import main.KeyControl;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import main.BoostTool;
import object.keyObject;
import object.shieldObject;
import object.swordObject;

/**
 *
 * @author DELL
 */
public class Player extends Entity {

    KeyControl keyC;
    public final int screenX ;
    public final int screenY;
   // public int hasKey = 0;
    public boolean attackCanceled = false;  
    public Player(GamePanel gp,KeyControl keyC){
        super(gp);
        this.keyC=keyC;
        screenX = gp.screenWidth/2-(gp.titleSize/2);
        screenY = gp.screenHeight/2 - (gp.titleSize/2);
        
        solidArea = new Rectangle();
        solidArea.x = 8;
        solidArea.y = 16;
        solidAreaDefaultX = solidArea.x;
        solidAreaDefaultY = solidArea.y;
        solidArea.width = 32;
        solidArea.height = 32;
//        attackArea.width = 36;
//        attackArea.height = 36;
        getPlayerImage();
        setDefaultValue();
        attackImage();
        setInventory();
    }
    public final void setDefaultValue(){
        worldX = gp.titleSize * 23;
        worldY = gp.titleSize * 21;
        speed = 4;
        direction ="down";
        //player status
        level = 1; 
        maxLife = 10;
        life = maxLife;
        strength = 1;
        dexterity = 1;
        exp = 0;
        nextLevelExp = 5;
        coin = 0;
        currentWeapon = new swordObject(gp);
        currentShield = new shieldObject(gp);
        attack = getAttack();
        defense = getDefense();
    }
    public void setDefaultPosition(){
        worldX = gp.titleSize * 23;
        worldY = gp.titleSize * 21;
        direction ="down";
    }
    public void restoredLife(){
        life = maxLife;
        invincible = false;
    }
    public void setInventory(){
        inventory.clear();
        inventory.add(currentWeapon);
        inventory.add(currentShield);
        inventory.add(new keyObject(gp));
        inventory.add(new keyObject(gp));
    }
    public int getAttack(){
        attackArea = currentWeapon.attackArea;
        return attack = strength * currentWeapon.attackValue;
    }
    public int getDefense(){
        return defense = dexterity * currentShield.defenseValue;
    }
    public final void getPlayerImage(){
        up1 = setUp("player/Body_Up1",gp.titleSize,gp.titleSize);
        up2 = setUp("player/Body_Up2",gp.titleSize,gp.titleSize);
        down1 = setUp("player/Body_Down1",gp.titleSize,gp.titleSize);
        down2 = setUp("player/Body_Down2",gp.titleSize,gp.titleSize);
        right1 = setUp("player/Body_Right1",gp.titleSize,gp.titleSize);
        right2 = setUp("player/Body_Right2",gp.titleSize,gp.titleSize);
        left1 = setUp("player/Body_Left1",gp.titleSize,gp.titleSize);
        left2 = setUp("player/Body_Left2",gp.titleSize,gp.titleSize);
        upLeft1= setUp("player/Body_UpLeft1",gp.titleSize,gp.titleSize);
        upLeft2 = setUp("player/Body_UpLeft2",gp.titleSize,gp.titleSize);
        downLeft1 = setUp("player/Body_DownLeft1",gp.titleSize,gp.titleSize);
        downLeft2 = setUp("player/Body_DownLeft2",gp.titleSize,gp.titleSize);
        upRight1 = setUp("player/Body_UpRight1",gp.titleSize,gp.titleSize);
        upRight2 = setUp("player/Body_UpRight2",gp.titleSize,gp.titleSize);
        downRight1 = setUp("player/Body_DownRight1",gp.titleSize,gp.titleSize);
        downRight2 = setUp("player/Body_DownRight2",gp.titleSize,gp.titleSize);
        
    }
    public void attackImage(){
        attackUp1 = setUp("player/attack/attack_up_1",gp.titleSize,gp.titleSize*2);
        attackUp2 = setUp("player/attack/attack_up_2",gp.titleSize,gp.titleSize*2);
        attackDown1 = setUp("player/attack/attack_down_1",gp.titleSize,gp.titleSize*2);
        attackDown2 = setUp("player/attack/attack_down_2",gp.titleSize,gp.titleSize*2);
        attackRight1 = setUp("player/attack/attack_right_1",gp.titleSize*2,gp.titleSize);
        attackRight2 = setUp("player/attack/attack_right_2",gp.titleSize*2,gp.titleSize);
        attackLeft1 = setUp("player/attack/attack_left_1",gp.titleSize*2,gp.titleSize);
        attackLeft2 = setUp("player/attack/attack_left_2",gp.titleSize*2,gp.titleSize);
    }
    @Override
    public void update() throws UnsupportedAudioFileException, LineUnavailableException {
        if (attacking == true){
            try {
                attacking();
            } catch (UnsupportedAudioFileException ex) {
                Logger.getLogger(Player.class.getName()).log(Level.SEVERE, null, ex);
            } catch (LineUnavailableException ex) {
                Logger.getLogger(Player.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        else if(keyC.down==true||keyC.left==true||keyC.right==true||keyC.up==true){
            if(keyC.up == true){
                direction = "up";
            }
            if (keyC.down == true){
                direction="down";
            }
            if (keyC.right == true){
                direction = "right";
            }
            if (keyC.left == true){
                direction = "left";
            }
            if (keyC.up == true && keyC.left ==true){
                direction = "upleft";
            }
            if(keyC.up == true && keyC.right == true){
                direction = "upright";
            }
            if(keyC.down == true && keyC.left == true){
                direction = "downleft";
            }
            if(keyC.down ==true &&keyC.right==true){
                direction = "downright";
            }
        
        // check collision
        collisionOn = false;
        gp.cChecker.checkTile(this);
        
        //check npc
        int npcIndex = gp.cChecker.checkEntity(this, gp.npc);
        interactNPC(npcIndex);
        //check object
        int objectIndex = gp.cChecker.checkObject(this, true);
        pickUpObject(objectIndex);
        //stepSound();
        //check monster
        int monsterIndex = gp.cChecker.checkEntity(this, gp.mon);
            try {
                contactMonster(monsterIndex);
            } catch (UnsupportedAudioFileException ex) {
                Logger.getLogger(Player.class.getName()).log(Level.SEVERE, null, ex);
            } catch (LineUnavailableException ex) {
                Logger.getLogger(Player.class.getName()).log(Level.SEVERE, null, ex);
            }
        // check event
        gp.eHandler.checkEvent();
        
        
        // if collision = false player can move
        if (collisionOn == false && keyC.enter == false){
            switch(direction){
            case "up" -> worldY -= speed;
            case "down" -> worldY += speed;
            case "left" -> worldX -= speed;
            case "right" -> worldX += speed;
            case "upleft" -> {
                worldY -= speed-1;
                worldX -= speed-1;
                }
            case "upright" -> {
                worldY -=  speed-1;
                worldX +=  speed-1;
                }
            case "downleft" -> {
                worldY +=  speed-1;
                worldX -=  speed-1;
                }
            case "downright" -> {
                worldY +=  speed-1;
                worldX +=  speed-1;
                }
            }
        }
        if(keyC.enter == true && attackCanceled == false){
            gp.playSetFile(4);
            attacking = true;
            spriteCount = 0;
        }
        attackCanceled = false;
        gp.keyC.enter = false;
        spriteCount ++;
        if (spriteCount>12){
            if(spriteNum == 1){
                spriteNum = 2;
            }
            else if(spriteNum == 2){
                spriteNum=1;
            }
            spriteCount=0;
            }
        }
        if (invincible == true){
            invincibleTime ++;
            if (invincibleTime > 60){
                invincible = false;
                invincibleTime = 0;
            }  
        }
        if(life <= 0){
            gp.gameState = gp.gameOverState;
            gp.stopMusic();
            gp.ui.commandNum = -1;
        }
    }
    public void attacking() throws UnsupportedAudioFileException, LineUnavailableException{
        spriteCount++;
        if (spriteCount <= 5){
            spriteNum = 1;
        }
        if (spriteCount > 5 && spriteCount <=25){
            spriteNum =2;
            
            int currentWorldX = worldX;
            int currentWorldY = worldY;
            int solidAreaWidth = solidArea.width;
            int solidAreaHeight = solidArea.height;
            
            switch(direction){
                case "up" -> worldY -= attackArea.height;
                case "down" -> worldY += attackArea.height;
                case "right" -> worldX += attackArea.width;
                case "left" -> worldX -= attackArea.width;
//                case "upLeft" ->                 {
//                    worldY -= attackArea.height;
//                    worldX -= attackArea.width;
//                }
//                case"upRight" -> {
//                    worldY -= attackArea.height;
//                    worldX += attackArea.width;
//                }
//                case "downLeft" -> {
//                    worldY += attackArea.height;
//                    worldX -= attackArea.width;
//                }
//                case "downRight" -> {
//                    worldY += attackArea.height;
//                    worldX += attackArea.width;
//                }
            }
            solidArea.width = attackArea.width;
            solidArea.height = attackArea.height;
            int monsterIndex = gp.cChecker.checkEntity(this, gp.mon);
            damageMon(monsterIndex);
            worldX = currentWorldX;
            worldY = currentWorldY;
            solidArea.width = solidAreaWidth;
            solidArea.height = solidAreaHeight;
        }
        if (spriteCount >25){
            spriteNum =1 ;
            spriteCount = 0;
            attacking = false;
        }
    }
    public void pickUpObject(int index) throws UnsupportedAudioFileException, LineUnavailableException{
        if (index != 999){
            if (gp.obj[gp.currentMap][index].type == typePickupOnly){
                gp.obj[gp.currentMap][index].use(this);
                gp.obj[gp.currentMap][index] = null;
            }
            else {
                String text;
            if(inventory.size()!= maxInventorySize){
                inventory.add(gp.obj[gp.currentMap][index]);
                text = "Nhan duoc 1 "+gp.obj[gp.currentMap][index].name +" !";
            }
            else {
                text = "Ba lo day !";
            }
            gp.ui.addMessage(text);
            gp.obj[gp.currentMap][index] = null;
            }
        }
    }
    
    @Override
    public void draw(Graphics2D g2D){
//        g2D.setColor(Color.red);
//        
//        g2D.fillRect(x, y , gp.titleSize, gp.titleSize);
        BufferedImage image = null;
        int tempScreenX = screenX;
        int tempScreenY = screenY;
        switch (direction){
            case "up" -> {
                if(attacking == false){
                    if(spriteNum == 1){
                        image = up1;
                    }
                    if(spriteNum == 2){
                        image = up2;
                    } 
                }
                if (attacking == true){
                    tempScreenY = screenY -gp.titleSize;
                    if(spriteNum == 1){image = attackUp1;}
                    if(spriteNum == 2){image = attackUp2;}
                }
            }
            case"down" ->{ 
                if(attacking == false){
                    if(spriteNum == 1){
                        image = down1;
                    }
                    if(spriteNum == 2){
                        image= down2;
                    }  
                }
                if (attacking == true){
                    if(spriteNum == 1){image = attackDown1;}
                    if(spriteNum == 2){image = attackDown2;}
                }
            }
                
            case"right" ->{
                if(attacking == false){
                    if(spriteNum == 1){
                        image = right1;
                    }
                    if(spriteNum == 2){
                        image = right2;
                    }
                }
                if (attacking == true){
                    if(spriteNum == 1){image = attackRight1;}
                    if(spriteNum == 2){image = attackRight2;}
                }
            }
            case"left" ->{
                if(attacking == false){
                    if(spriteNum == 1){
                        image=left1;
                    }
                    if(spriteNum == 2){
                        image = left2;
                    }
                }
                if (attacking == true){
                    tempScreenX = screenX - gp.titleSize;
                    if(spriteNum == 1){image = attackLeft1;}
                    if(spriteNum == 2){image = attackLeft2;}
                }
            }
            case"upleft" -> {
                if(attacking == false){
                    if(spriteNum == 1){
                        image=upLeft1;
                    }
                    if(spriteNum == 2){
                        image = upLeft2;
                    }
                }
                if (attacking == true){
                    if(spriteNum == 1){image = attackLeft1;}
                    if(spriteNum == 2){image = attackLeft2;}
                }
            }
            case"upright" -> {
                if(attacking == false){
                    if(spriteNum == 1){
                        image=upRight1;
                    }
                    if(spriteNum == 2){
                        image = upRight2;
                    }
                }
                if (attacking == true){
                    if(spriteNum == 1){image = attackRight1;}
                    if(spriteNum == 2){image = attackRight2;}
                }
            }
            case"downright" -> {
                if(attacking == false){
                    if(spriteNum == 1){
                        image=downRight1;
                    }
                    if(spriteNum == 2){
                        image = downRight2;
                    }
                }
                if (attacking == true){
                    if(spriteNum == 1){image = attackRight1;}
                    if(spriteNum == 2){image = attackRight2;}
                }
            }
            case"downleft" -> {
                if(attacking == false){
                    if(spriteNum == 1){
                        image=downLeft1;
                    }
                    if(spriteNum == 2){
                        image = downLeft2;
                    }
                }
                if (attacking == true){
                    if(spriteNum == 1){image = attackLeft1;}
                    if(spriteNum == 2){image = attackLeft2;}
                }
            }
        }
        if(invincible == true){
            g2D.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER ,0.3f));
        }
        g2D.drawImage(image, tempScreenX, tempScreenY, null);
        //reset
        g2D.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER ,1f));
    }
    public void interactNPC(int i){
        if(gp.keyC.enter == true){
            if(i!=999){ 
                attackCanceled = true;
                gp.gameState = gp.dialogueState;
                gp.npc[gp.currentMap][i].speak();
            }
        }
    }
    public void contactMonster(int i) throws UnsupportedAudioFileException, LineUnavailableException{
        if (i != 999){
            if(invincible == false){
               gp.playSetFile(4);
               int damage = gp.mon[gp.currentMap][i].attack - defense;
               if(damage<0){
                   damage = 0;
               }
               life -= damage; 
               invincible = true;
            }
        }
    }
    public void damageMon(int i) throws UnsupportedAudioFileException, LineUnavailableException{
        if(i!=999){
            if (gp.mon[gp.currentMap][i].invincible ==false){
                gp.playSetFile(3);
                int damage = attack - gp.mon[gp.currentMap][i].defense;
                if(damage<0){
                    damage = 0;
                }
                gp.mon[gp.currentMap][i].life -= damage;
                gp.ui.addMessage(damage + " Sat Thuong!");
                gp.mon[gp.currentMap][i].invincible = true;
                gp.mon[gp.currentMap][i].damageReact();
                if(gp.mon[gp.currentMap][i].life <=0){
                    gp.mon[gp.currentMap][i].dying = true;
                    gp.ui.addMessage("Giet "+gp.mon[gp.currentMap][i].name+" !");
                    gp.ui.addMessage("Exp + " + gp.mon[gp.currentMap][i].exp);
                    exp+= gp.mon[gp.currentMap][i].exp;
                    checkLevelUp();
                }
            }
        }
    }
    public void checkLevelUp(){
        if (exp >= nextLevelExp){
            level++;
            nextLevelExp = nextLevelExp*3;
            strength++;
            dexterity++;
            attack = getAttack();
            defense = getDefense();
            gp.ui.addMessage("Ban da len cap "+level);
        }
    }
    public void selectItem(){
        int itemIndex = gp.ui.getItemIndex();
        if(itemIndex < inventory.size()){
            Entity selectedItem = inventory.get(itemIndex);
            if(selectedItem.type == typeNormalSword ||
                    selectedItem.type == typeKatana
                    ||selectedItem.type == typeStick){
                currentWeapon = selectedItem;
                attack = getAttack();
            }
            if(selectedItem.type == typeShield){
                currentShield = selectedItem;
                defense = getDefense();
            }
            if(selectedItem.type == typeConsumable){
                selectedItem.use(this);
                inventory.remove(itemIndex);
            }
        }
    }
}
