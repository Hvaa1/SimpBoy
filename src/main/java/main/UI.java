/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package main;

import Entity.Entity;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import object.heartObject;
import object.keyObject;

/**
 *
 * @author DELL
 */
public class UI {
    GamePanel gp;
    Font maruMonica,prusiaB;    
    Graphics2D g2D;
    BufferedImage heartFull,heartHalf,heartBlank;
    public boolean messageOn = false;
//    public int messageCount=0;
//    public String message = " ";
    ArrayList<String> message = new ArrayList<>();
    ArrayList<Integer> messageCount = new ArrayList<>();
    public boolean gameFinished = false;
    public double playTime;
    DecimalFormat dFormat = new DecimalFormat("0.00");
    public String currentDialogue = "";
    public int commandNum = 0;
    public int titleScreenState = 0;
    public int slotCol = 0;
    public int slotRow = 0;
    public int count = 0;
    int subState = 0;
    public UI(GamePanel gp){
        this.gp=gp;
        
        try{
        FileInputStream is = new FileInputStream("res/Font/MaruMonica.ttf");
        maruMonica = Font.createFont(Font.TRUETYPE_FONT, is);
        is  = new FileInputStream("res/Font/Prusia Bold");
        prusiaB = Font.createFont(Font.TRUETYPE_FONT, is);
        } catch (FontFormatException | IOException e){}
        
        //create object
        Entity heart =new heartObject(gp);
        heartFull = heart.image1;
        heartHalf = heart.image2;
        heartBlank = heart.image3;
    }
    public void addMessage(String text){
//        message = text;
//        messageOn = true;
        message.add(text);
        messageCount.add(0);
    }
    public void draw(Graphics2D g2D){
        this.g2D = g2D;
        g2D.setFont(maruMonica);
        g2D.setColor(Color.white);
        //Title State
        if (gp.gameState == gp.titleState){
            drawTitleScreen();
        }
        //PLAY STATE 
        if(gp.gameState == gp.playState){
            drawPlayerLife();
            drawMessage();
        }
        //PAUSE STATE
        if(gp.gameState == gp.pauseState){
            drawPlayerLife();
            drawPauseScreen();
        }
        //DIALOGUE STATE
        if (gp.gameState == gp.dialogueState){
            drawPlayerLife();
            drawDialogueScreen();
        }
        //character state
        if(gp.gameState == gp.characterState){
            drawCharacterScreen();
            drawInventory();
        }
        //option stae
        if(gp.gameState == gp.optionState){
            drawOptionScreen();
        }
        //game over state
        if (gp.gameState == gp.gameOverState){
            drawGameOverState();
        }
        //transitionState
        if (gp.gameState == gp.transitionState){
            drawTransition();
        }
    }
    public void drawTransition(){
        count++;
        g2D.setColor(new Color(0,0,0,count*5));
        g2D.fillRect(0, 0, gp.screenWidth, gp.screenHeight);
        if(count  == 50){
            count = 0;
            gp.gameState = gp.playState;
            gp.currentMap = gp.eHandler.tempMap;
            gp.player.worldX = gp.titleSize *gp.eHandler.tempCol;
            gp.player.worldY = gp.titleSize * gp.eHandler.tempRow;
            gp.eHandler.previousEventX = gp.player.worldX;
            gp.eHandler.previousEventY = gp.player.worldY;
        }
    }
    public void drawOptionScreen(){
        g2D.setColor(Color.white);
        g2D.setFont(g2D.getFont().deriveFont(32F));
        //sub window
        int frameX = gp.titleSize*4;
        int frameY = gp.titleSize;
        int frameWidth = gp.titleSize*8;
        int frameHeight = gp.titleSize*10;
        drawSubWindow(frameX,frameY,frameWidth,frameHeight);
        switch(subState){ 
            case 0:optionTop(frameX,frameY);break;
            case 1:optionControl(frameX,frameY);break;
            case 2:optionEnd(frameX,frameY);break;
        }
        gp.keyC.enter = false;
    }
    public void optionTop(int frameX,int frameY){
        int textX;
        int textY;
        
        String text = "Cai Dat";
        textX = getXForCenter(text);
        textY = frameY +gp.titleSize;
        g2D.drawString(text, textX, textY);
        
        //music
        textX = frameX+gp.titleSize;
        textY += gp.titleSize*2;
        g2D.drawString("Nhac", textX, textY);
        if(commandNum == 0){
            g2D.drawString(">", textX-25, textY);
        }
        
        //se
        textY += gp.titleSize;
        g2D.drawString("SE", textX, textY);
        if(commandNum == 1){
            g2D.drawString(">", textX-25, textY);
        }
        //cotrol
        textY += gp.titleSize;
        g2D.drawString("Dieu khien", textX, textY);
        if(commandNum == 2){
            g2D.drawString(">", textX-25, textY);
            if (gp.keyC.enter == true){
                subState = 1;
                commandNum =0;
            }
        }
        //end
        textY += gp.titleSize;
        g2D.drawString("Thoat", textX, textY);
        if(commandNum == 3){
            g2D.drawString(">", textX-25, textY);
            if(gp.keyC.enter == true){
                subState = 2;
                commandNum = 0;
            }
        }
        //thoat
        textY += gp.titleSize*2;
        g2D.drawString("Tro Lai", textX, textY);
        if(commandNum == 4){
            g2D.drawString(">", textX-25, textY);
            if (gp.keyC.enter == true){
                gp.gameState = gp.playState;
                commandNum = 0;
            }
        }
        // music volume
        textX = frameX +(int)(gp.titleSize*4.5);
        textY =  frameY + gp.titleSize*2+24;
        g2D.setStroke(new BasicStroke(3));
        g2D.drawRect(textX, textY, 120, 24);//120/5
        int volumeWidth = 24*gp.music.volumeScale;
        g2D.fillRect(textX, textY, volumeWidth, 24);
        
        //se
        textY += gp.titleSize;
        g2D.drawRect(textX, textY, 120, 24);
        volumeWidth = 24*gp.se.volumeScale;
        g2D.fillRect(textX, textY, volumeWidth, 24);
        
        gp.config.saveConfig();
    }
    public void optionControl(int frameX,int frameY){
        int textX;
        int textY;
        
        //TITLE
        String text = "Dieu Khien";
        textX = getXForCenter(text);
        textY = frameY +gp.titleSize;
        g2D.drawString(text, textX, textY);
        
        textX = frameX + gp.titleSize;
        textY += gp.titleSize;
        g2D.drawString("Di Chuyen", textX, textY); textY +=gp.titleSize;
        g2D.drawString("Chap nhan/Tan Cong", textX, textY); textY+=gp.titleSize;
        g2D.drawString("Thuoc Tinh",textX,textY);textY+=gp.titleSize;
        g2D.drawString("Tam Dung", textX, textY);textY+= gp.titleSize;
        g2D.drawString("Cai Dat",textX,textY);textY+=gp.titleSize;
        
        textX = frameX + gp.titleSize*6;
        textY = frameY + gp.titleSize*2;
        g2D.drawString("WASD", textX, textY); textY +=gp.titleSize;
        g2D.drawString("ENTER", textX, textY); textY+=gp.titleSize;
        g2D.drawString("C",textX,textY);textY+=gp.titleSize;
        g2D.drawString("SPACE", textX, textY);textY+= gp.titleSize;
        g2D.drawString("ESC",textX,textY);textY+=gp.titleSize;
        
        //back
        textX = frameX + gp.titleSize;
        textY = frameY + gp.titleSize*9;
        g2D.drawString("Tro Lai",textX,textY);
        if (commandNum == 0){
            g2D.drawString(">", textX-25, textY);
            if(gp.keyC.enter == true){
                subState = 0;
                commandNum = 2;
            }
        }
    }
    public void optionEnd(int frameX,int frameY){
        int textX = frameX +gp.titleSize*2;
        int textY = frameY +gp.titleSize;
        currentDialogue = "Ban muon thoat????";
        for(String line:currentDialogue.split("\n")){
            g2D.drawString(line, textX, textY);
            textY+=40;
        }
        //yes
        String text = "Yes";
        textX = getXForCenter(text);
        textY+=gp.titleSize*3;
        g2D.drawString(text, textX, textY);
        if(commandNum ==0){
            g2D.drawString(">", textX-25, textY);
            if (gp.keyC.enter == true){
                subState = 0;
                gp.gameState = gp.titleState;
                titleScreenState = 0;
            }
        }
        //no
        text = "No";
        textX = getXForCenter(text);
        textY += gp.titleSize;
        g2D.drawString(text, textX, textY);
        if(commandNum == 1){
            g2D.drawString(">", textX-25, textY);
            if (gp.keyC.enter == true){
                subState = 0;
                commandNum = 3;
            }
        }
    }
    public void drawInventory(){
        int frameX = gp.titleSize*9;
        int frameY = gp.titleSize;
        int frameWidth = gp.titleSize*6;
        int frameHeight = gp.titleSize*5;
        drawSubWindow(frameX,frameY,frameWidth,frameHeight);
        
        final int slotXstart = frameX + 20;
        final int slotYstart = frameY + 20;
        int slotX = slotXstart;
        int slotY = slotYstart;
        int slotSize = gp.titleSize+3;
        //draw player's item
        for(int i= 0;i<gp.player.inventory.size();i++){
            if(gp.player.inventory.get(i)==gp.player.currentWeapon ||
                    gp.player.inventory.get(i) == gp.player.currentShield){
                g2D.setColor(new Color(240,190,90));
                g2D.fillRoundRect(slotX, slotY, gp.titleSize, gp.titleSize, 10, 10);
            }
            g2D.drawImage(gp.player.inventory.get(i).down1, slotX, slotY ,null);
            slotX += slotSize;
            if( i==4 ||i ==9 || i==14){
                slotX =slotXstart;
                slotY+=slotSize;
            }
        }
        int cursorX = slotXstart + (slotSize* slotCol);
        int cursorY = slotYstart + (slotSize *slotRow);
        int cursorWidth = gp.titleSize;
        int cursorHeight = gp.titleSize;
        //draw
        g2D.setColor(Color.white);
        g2D.setStroke(new BasicStroke(3));
        g2D.drawRoundRect(cursorX, cursorY,  cursorWidth, cursorHeight,10,10);
        
        //decription
        int dFrameX =frameX;
        int dFrameY = frameY + frameHeight+gp.titleSize;
        int dFrameWidth = frameWidth;
        int dFrameHeight = gp.titleSize*3;
        //decription text
        int textX = dFrameX +20;
        int textY = dFrameY + gp.titleSize;
        g2D.setFont(g2D.getFont().deriveFont(28F));
        int itemIndex = getItemIndex();
        if(itemIndex <gp.player.inventory.size()){
            drawSubWindow(dFrameX,dFrameY,dFrameWidth,dFrameHeight);
            for(String line : gp.player.inventory.get(itemIndex).description.split("\n")){
                g2D.drawString(line, textX, textY);
                textY += 32;
            }
        }
        
    }
    public int getItemIndex(){
        int itemIndex = slotCol+(slotRow*5);
        return itemIndex;
    }
    public void drawMessage(){
        int messageX = gp.titleSize/2;
        int messageY = gp.titleSize *4;
        g2D.setFont(g2D.getFont().deriveFont(Font.BOLD,32F));
        for(int i = 0;i<message.size();i++){
            if(message.get(i) != null){
                g2D.setColor(Color.black);
                g2D.drawString(message.get(i),messageX+2,messageY+2);
                g2D.setColor(Color.white);
                g2D.drawString(message.get(i), messageX, messageY);
                
                int count = messageCount.get(i) + 1;//messageCount ++
                messageCount.set(i, count);
                messageY += 50;
                
                if(messageCount.get(i)>120){
                    message.remove(i);
                    messageCount.remove(i);
                }
            }
        }
    }
    public void drawPlayerLife(){
        int x = gp.titleSize/2;
        int y = gp.titleSize/2;
        int i =0;
        
        //draw max life
        while (i< gp.player.maxLife/2){
            g2D.drawImage(heartBlank, x,y, null);
            i++;
            x += gp.titleSize;
        }
        //reset
        x = gp.titleSize/2;
        y = gp.titleSize/2;
        i =0;
        //draw current life
        while (i< gp.player.life){
            g2D.drawImage(heartHalf, x,y, null);
            i++;
            if(i<gp.player.life){
                g2D.drawImage(heartFull, x,y, null);
            }
            i++;
            x += gp.titleSize;
        }
    }
    public void drawTitleScreen(){
        if (titleScreenState ==0){
            g2D.setColor(new Color(0,0,0));
            g2D.fillRect(0, 0, gp.screenWidth, gp.screenHeight);
            g2D.setFont(g2D.getFont().deriveFont(Font.BOLD,96F));
            String text ="Smip Boy";
            int x = getXForCenter(text);
            int y = gp.titleSize*3;
        
            //shawdow
            g2D.setColor(Color.gray);
            g2D.drawString(text, x+5, y+5);
            //main color
            g2D.setColor(Color.white);
            g2D.drawString(text, x, y);
        
            //title image 
            x = gp.screenWidth/2 -(gp.titleSize*2)/2;
            y +=gp.titleSize*2;
            g2D.drawImage(gp.player.down1, x, y,gp.titleSize*2,gp.titleSize*2,null);
        
            //menu
            g2D.setFont(g2D.getFont().deriveFont(Font.BOLD,48F));
            text = "NEW GAME";
            x =getXForCenter(text);
            y += gp.titleSize*3.5;
            g2D.drawString(text,x,y);
            if ( commandNum == 0){
                g2D.drawString(">",x-gp.titleSize,y);
            }
        
            text = "LOAD GAME";
            x =getXForCenter(text);
            y += gp.titleSize;
            g2D.drawString(text,x,y);
            if ( commandNum == 1){
                g2D.drawString(">",x-gp.titleSize,y);
            }
        
            text = "QUIT";
            x =getXForCenter(text);
            y += gp.titleSize;
            g2D.drawString(text,x,y);
            if ( commandNum == 2){
                g2D.drawString(">",x-gp.titleSize,y);
            }
        }
        else if (titleScreenState == 1){
            g2D.setColor(Color.white);
            g2D.setFont(g2D.getFont().deriveFont(42F));
            
            String text = "Chao Mung Den Voi Binh Nguyen Vo Tan";
            int x = getXForCenter(text);
            int y = gp.titleSize*3;
            g2D.drawString(text, x, y);
        }
    }
    public void drawPauseScreen(){
        String text="PAUSE";
        g2D.setFont(g2D.getFont().deriveFont(Font.BOLD,80F));
        int y,x;
        x = getXForCenter(text);
        y = gp.screenHeight/2 ;
        g2D.drawString(text, x, y);
    }
    public void drawDialogueScreen(){
        //display 
        int x = gp.titleSize*2;
        int y = gp.titleSize/2;
        int height = gp.titleSize *4;
        int width = gp.screenWidth - (gp.titleSize*4);
        drawSubWindow(x,y,width,height);
        
        g2D.setFont(g2D.getFont().deriveFont(Font.PLAIN,28F));
        x += gp.titleSize;
        y += gp.titleSize;
        
        for (String line : currentDialogue.split("\n")){
            g2D.drawString(line, x, y);
            y += 40;
        }
    }
    public void drawSubWindow(int x ,int y,int width,int height){
        Color c =new Color(0,0,0,190);
        g2D.setColor(c);
        g2D.fillRoundRect(x, y, width, height, 35, 35);
        
        c = new Color (255,255,255);
        g2D.setColor(c);
        g2D.setStroke(new BasicStroke(5));
        g2D.drawRoundRect(x+5, y+5, width-10, height-10, 25, 25);
        
    }
    public void drawCharacterScreen(){
        
        //create a frame
        final int frameX = gp.titleSize;
        final int frameY = gp.titleSize;
        final int frameWidth = gp.titleSize*5;
        final int frameHeight = gp.titleSize*10;
        drawSubWindow(frameX,frameY,frameWidth,frameHeight);
        
        //text
        g2D.setColor(Color.white);
        g2D.setFont(g2D.getFont().deriveFont(32F));
        
        int textX = frameX + 20;
        int textY = frameY + gp.titleSize;
        final int lineHeight = 39;
        
        g2D.drawString("Cap Do", textX, textY);
        textY += lineHeight;
        g2D.drawString("Mau", textX, textY);
        textY += lineHeight;
        g2D.drawString("The Chat", textX, textY);
        textY += lineHeight;
        g2D.drawString("Suc Danh", textX, textY);
        textY += lineHeight;
        g2D.drawString("Phong Thu", textX, textY);
        textY += lineHeight;
        g2D.drawString("Kinh Nghiem", textX, textY);
        textY += lineHeight;
        g2D.drawString("Cap Tiep Theo", textX, textY);
        textY += lineHeight;
        g2D.drawString("Xu", textX, textY);
        textY += lineHeight+20;
        g2D.drawString("Vu Khi", textX, textY);
        textY += lineHeight+15;
        g2D.drawString("Khien", textX, textY);
        textY += lineHeight;
        
        //values
        int tailX = (frameX + frameWidth) -30;
        //reset testY
        textY = frameY+gp.titleSize;
        String value;
        
        value = String.valueOf(gp.player.level);
        textX = getXForAlignToRight(value,tailX);
        g2D.drawString(value,textX,textY);
        textY += lineHeight;
        
        value = String.valueOf(gp.player.life +"/"+ gp.player.maxLife);
        textX = getXForAlignToRight(value,tailX);
        g2D.drawString(value,textX,textY);
        textY += lineHeight;
        
        value = String.valueOf(gp.player.dexterity);
        textX = getXForAlignToRight(value,tailX);
        g2D.drawString(value,textX,textY);
        textY += lineHeight;
        
        value = String.valueOf(gp.player.attack);
        textX = getXForAlignToRight(value,tailX);
        g2D.drawString(value,textX,textY);
        textY += lineHeight;
        
        value = String.valueOf(gp.player.defense);
        textX = getXForAlignToRight(value,tailX);
        g2D.drawString(value,textX,textY);
        textY += lineHeight;
        
        value = String.valueOf(gp.player.exp);
        textX = getXForAlignToRight(value,tailX);
        g2D.drawString(value,textX,textY);
        textY += lineHeight;
        
        value = String.valueOf(gp.player.nextLevelExp);
        textX = getXForAlignToRight(value,tailX);
        g2D.drawString(value,textX,textY);
        textY += lineHeight;
        
        value = String.valueOf(gp.player.coin);
        textX = getXForAlignToRight(value,tailX);
        g2D.drawString(value,textX,textY);
        textY += lineHeight;
        
        g2D.drawImage(gp.player.currentWeapon.down1, tailX - gp.titleSize, textY-14,null);
        textY +=gp.titleSize;
        g2D.drawImage(gp.player.currentShield.down1, tailX - gp.titleSize, textY-14,null);
    }
    public int getXForCenter(String text){
        int lengthText =(int)g2D.getFontMetrics().getStringBounds(text, g2D).getWidth();
        int x =  gp.screenWidth/2 - lengthText/2;
        return x;
    }
    public int getXForAlignToRight(String text,int tailX){
        int length =(int)g2D.getFontMetrics().getStringBounds(text, g2D).getWidth();
        int x =  tailX- length;
        return x;
    }
    public void drawGameOverState(){
        g2D.setColor(new Color(0,0,0,150));
        g2D.fillRect(0, 0, gp.screenWidth, gp.screenHeight);
        
        int x;
        int y;
        String text;
        g2D.setFont(g2D.getFont().deriveFont(Font.BOLD,119F));
        
        text = "Game Over";
        //shadow
        g2D.setColor(Color.BLACK);
        x = getXForCenter(text);
        y = gp.titleSize *4;
        g2D.drawString(text, x, y);
        //main
        g2D.setColor(Color.white);
        g2D.drawString(text, x-4, y-4);
        
        //retry
        g2D.setFont(g2D.getFont().deriveFont(50F));
        text = "Thu Lai";
        x = getXForCenter(text);
        y+=gp.titleSize*4;
        g2D.drawString(text,x,y);
        if(commandNum == 0){
            g2D.drawString(">", x-40, y);
        }
        
        text ="Thoat";
        x = getXForCenter(text);
        y+=gp.titleSize;
        g2D.drawString(text,x,y);
        if(commandNum == 1){
            g2D.drawString(">", x-40, y);
        }
    }
}
