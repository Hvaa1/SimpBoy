/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Entity;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import javax.imageio.ImageIO;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import main.BoostTool;
import main.EventHandler;
import main.GamePanel;

/**
 *
 * @author DELL
 */
public class Entity {
    public GamePanel gp;
    //state
    public int worldX,worldY;
    public String direction= "down";
    public int spriteNum = 1;
    public boolean collisionOn = false;
    public boolean invincible = false;
    int dialogueIndex = 0;
    boolean attacking = false;
    
    
    public BufferedImage image,image1,image2,image3;
    public BufferedImage up1,up2,down1,down2,right1,right2,left1,left2,upLeft1,upLeft2,upRight1,upRight2,downRight1,downRight2,downLeft1,downLeft2;
    public BufferedImage attackUp1,attackUp2,attackDown1,attackDown2,attackLeft1,attackLeft2,attackRight1,attackRight2;
    public Rectangle solidArea = new Rectangle (0,0,48,48);
    public Rectangle attackArea = new Rectangle (0,0,0,0);
    public int solidAreaDefaultX ,solidAreaDefaultY;
    public boolean collision = false;
    String dialogue[] = new String[20];
    public boolean alive = true;
    public boolean dying = false;
    public boolean hpBarOn = false;
    public boolean levelUp = false;
    
    
    //count
    public int actionLockTime = 0;
    public int invincibleTime = 0;
    public int spriteCount = 0;
    public int dyingTime = 0;
    public int hpBarCount = 0;


    //character status
    public int speed;
    public int maxLife;
    public int life;
    public String name;
    public int level;
    public int strength;
    public int dexterity;
    public int attack;
    public int defense;
    public int exp;
    public int nextLevelExp;
    public int coin;
    public Entity currentWeapon;
    public Entity currentShield;
    
    //item attribute
    public ArrayList<Entity> inventory = new ArrayList<>();
    public final int maxInventorySize = 20;
    public int value;
    public int attackValue;
    public int defenseValue;
    public String description = "";
    
    //type
    public int type;// 0-player, 1-npc,2-monste
    public final int typeMonster = 2;
    public final int typeNormalSword = 3;
    public final int typeKatana = 4;
    public final int typeStick = 5;
    public final int typeShield = 6;
    public final int typeConsumable = 7;
    public final int typePickupOnly = 8;
    public Entity (GamePanel gp){
        this.gp=gp;
    }
    public BufferedImage setUp(String imagePath,int width ,int height){
        BoostTool bTool = new BoostTool();
        BufferedImage scaledImage = null;
        try{
            scaledImage = ImageIO.read(new File("res/"+imagePath+".png"));
            scaledImage = bTool.scaleImage(scaledImage, width, height);
        }catch(IOException e){
            
        }
        return scaledImage;
    }
    public void use(Entity entity){
    }
    public void setAction(){
    }
    public void damageReact(){
    }
    public void checkDrop(){
    }
    public void dropItem(Entity dropItem){
        for (int i = 0;i<gp.obj[1].length;i++){
            if(gp.obj[gp.currentMap][i] ==null){
                gp.obj[gp.currentMap][i] = dropItem;
                gp.obj[gp.currentMap][i].worldX = worldX;
                gp.obj[gp.currentMap][i].worldY = worldY;
                break;
            }
        }
    }
    public void speak(){
        if (dialogue[dialogueIndex] == null){
            dialogueIndex = 0;
        }
        gp.ui.currentDialogue = dialogue[dialogueIndex];
        dialogueIndex++;
        switch (gp.player.direction){
            case"up" -> direction="down";
            case "down" -> direction = "up";
            case "right" -> direction = "left";
            case"left" -> direction = "right";
            case"downleft"-> direction = "right";
            case"upleft"-> direction = "right";
            case"downright"-> direction = "left";
            case"upright"-> direction = "left";
        }
    }
    public void update() throws UnsupportedAudioFileException, LineUnavailableException{
        setAction();
        collisionOn = false;
        gp.cChecker.checkTile(this);
        gp.cChecker.checkObject(this, false);
        gp.cChecker.checkEntity(this, gp.npc);
        gp.cChecker.checkEntity(this, gp.mon);
        boolean contactPlayer = gp.cChecker.checkPlayer(this);
        
        if(this.type == 2 && contactPlayer == true){
            if(gp.player.invincible == false){
                gp.playSetFile(4);
                int damage = attack - gp.player.defense;
                if(damage<0){
                   damage = 0;
               }
                gp.player.life -= damage;
                gp.player.invincible = true;
            }
        }
         // if collision = false player can move
        if (collisionOn == false){
            switch(direction){
            case "up" -> worldY -= speed;
            case "down" -> worldY += speed;
            case "left" -> worldX -= speed;
            case "right" -> worldX += speed;
//            case "upleft" -> {
//                worldY -= speed-1;
//                worldX -= speed-1;
//                }
//            case "upright" -> {
//                worldY -=  speed-1;
//                worldX +=  speed-1;
//                }
//            case "downleft" -> {
//                worldY +=  speed-1;
//                worldX -=  speed-1;
//                }
//            case "downright" -> {
//                worldY +=  speed-1;
//                worldX +=  speed-1;
//                }
            }
        }
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
         if (invincible == true){
            invincibleTime ++;
            if (invincibleTime > 40){
                invincible = false;
                invincibleTime = 0;
            }  
        }
    }
    public void draw(Graphics2D g2D){
        BufferedImage image = null;
        int screenX = worldX - gp.player.worldX + gp.player.screenX;
        int screenY = worldY - gp.player.worldY + gp.player.screenY;
        if (worldX + gp.titleSize > gp.player.worldX - gp.player.screenX &&
                worldY - gp.titleSize < gp.player.worldY + gp.player.screenY &&
                worldX - gp.titleSize < gp.player.worldX + gp.player.screenX &&
                worldY + gp.titleSize > gp.player.worldY - gp.player.screenY){
            
                switch (direction){
                case "up" -> {
                    if(spriteNum == 1){
                        image = up1;
                    }
                    if(spriteNum == 2){
                        image = up2;
                    }
                }
                case"down" ->{ 
                    if(spriteNum == 1){
                        image = down1;
                    }   
                    if(spriteNum == 2){
                        image= down2;
                    }
                }
                case"right" ->{
                    if(spriteNum == 1){
                        image = right1;
                    }
                    if(spriteNum == 2){
                        image = right2;
                    }
                }
                case"left" ->{
                    if(spriteNum == 1){
                        image=left1;
                    }
                    if(spriteNum == 2){
                        image = left2;
                    }
                }
            }
            if(type == 2 && hpBarOn == true){
                double oneScale = (double)gp.titleSize/maxLife;
                double hpBarValue = oneScale*life; 
                g2D.setColor(new Color(35,35,35));
                g2D.fillRect(screenX - 1 , screenY-16, gp.titleSize+2, 12);
                g2D.setColor(new Color(255,0,30));
                g2D.fillRect(screenX, screenY-15, (int)hpBarValue, 10);
                hpBarCount++;
                if(hpBarCount>60){
                    hpBarCount = 0;
                    hpBarOn = false;
                }
            }
            if(invincible == true){
                hpBarOn = true;
                hpBarCount = 0;
                changeAlpha(g2D,0.4f);
            }
            if (dying == true){
                dyingAnimation(g2D);
            }
            g2D.drawImage(image, screenX, screenY, null);
            changeAlpha(g2D,1f);
        }
    }
    public void dyingAnimation(Graphics2D g2D){
        dyingTime ++;
        int i =5;
        if(dyingTime <= i){
            changeAlpha(g2D,0f);  
        }
        if (dyingTime > i && dyingTime <=i*2){
            changeAlpha(g2D,1f);
        }
        if (dyingTime > i*2 && dyingTime <=i*3){
            changeAlpha(g2D,0f);
        }
        if (dyingTime > i*3 && dyingTime <=i*4){
            changeAlpha(g2D,1f);
        }
        if (dyingTime > i*4 && dyingTime <=i*5){
            changeAlpha(g2D,0f);
        }
        if (dyingTime > i*5 && dyingTime <=i*6){
            changeAlpha(g2D,1f);
        }
        if (dyingTime > i*6 && dyingTime <=i*7){
            changeAlpha(g2D,0f);
        }
        if (dyingTime > i*7 && dyingTime <=i*8){
            changeAlpha(g2D,1f);
        }
        if (dyingTime > i*8){
            dying = false;
            alive =false;
        }
    }
    public void changeAlpha(Graphics2D g2D ,float alphaValue){
        g2D.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER ,alphaValue));
    }
}

