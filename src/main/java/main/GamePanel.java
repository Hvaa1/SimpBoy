/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package main;
import Entity.Entity;
import Entity.Player;
import java.awt.Dimension;
import java.awt.Color;
import java.awt.Font;
import javax.swing.JPanel;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import tile.TileManager;
/**
 *
 * @author DELL
 */
public class GamePanel extends JPanel implements Runnable {
    //screen setting
    public final int originalTitleSize = 16;
    public final int scale = 3;
    
    final public int titleSize = originalTitleSize * scale;
    public final int maxScreenCol = 16;
    public final int maxScreenRow = 12;
    public final int screenWidth = maxScreenCol * titleSize;
    public final int screenHeight = maxScreenRow * titleSize;
    
    public final int maxWorldCol = 50;
    public final int maxWorldRow = 50;
    public final int maxMap = 50;
    public int currentMap = 0;
    public final int worldWidth = maxWorldCol * titleSize;
    public final int worldHeight = maxWorldRow * titleSize;
    //FPS
    final int FPS = 60;
    
    //system
    TileManager tileM = new TileManager(this);
    public KeyControl keyC = new KeyControl(this);
    Thread gameThread ;
    public CollisionChecker cChecker = new CollisionChecker(this);
    public AssetSetter aSetter = new  AssetSetter(this);
    public UI ui=new UI(this);
    Sound music = new Sound(); 
    Sound se = new Sound();  
    config config = new config(this);
    
    //entity and objcet
    public Player player = new Player(this,keyC);
    public Entity obj[][] = new Entity[maxMap][20];
    public Entity npc[][]= new Entity[maxMap][20];
    public Entity mon[][] = new Entity[maxMap][20];
    ArrayList<Entity> entityList = new ArrayList<>();
    //state
    public int gameState ;
    public final int playState=1;
    public final int pauseState = 2;
    public final int dialogueState = 3;
    public final int titleState = 0;
    public final int characterState = 4;
    public final int optionState = 5;
    public final int gameOverState = 6;
    public final int transitionState = 7;
    //event
    public EventHandler eHandler = new EventHandler(this);
    
    public GamePanel(){
        this.setPreferredSize(new Dimension(screenWidth,screenHeight));
        this.setBackground(Color.black);
        this.setDoubleBuffered(true);
        this.addKeyListener(keyC);
        this.setFocusable(true);
    }
    public void setup() throws UnsupportedAudioFileException, LineUnavailableException{
        aSetter.setObject();
        aSetter.setNPC();
        aSetter.setMonster();
        playMusic(0);
        gameState = titleState;
    }
    public void retry(){
        player.setDefaultPosition();
        player.restoredLife();
        aSetter.setNPC();
        aSetter.setMonster();
    }
    public void restart(){
        player.setDefaultPosition();
        player.setDefaultValue();
        player.setInventory();
        player.restoredLife();
        aSetter.setNPC();
        aSetter.setMonster();
        aSetter.setObject();
        
    }
    
    public void startThread(){
        gameThread = new Thread(this);
        gameThread.start();
    }
    @Override
    public void run() {
        // 1000000000 nano giay tren 1 toc do khung hinh
        double drawInterval = 1000000000/FPS;
        long lastTime = System.nanoTime();
        long currentTime;
        double delta = 0;
        long timer = 0;
        double fps = 0;
        while(gameThread != null){
            currentTime = System.nanoTime();
            delta += (currentTime - lastTime)/drawInterval;
            timer += (currentTime - lastTime);
            lastTime = currentTime;
            if (delta >= 1){
                try {
                    //Cap nhat thong tin vi tri nhan vat
                    update();
                    //Ve lai nhan vat o vi tri moi
                    repaint();
                    delta--;
                    fps++;
                } catch (UnsupportedAudioFileException | LineUnavailableException ex) {
                    Logger.getLogger(GamePanel.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            if (timer >= 1000000000){
                System.out.println("FPS: "+fps);
                timer = 0;
                fps = 0;
            }
        }
    }
    public void update() throws UnsupportedAudioFileException, LineUnavailableException{
        if(gameState == playState){
            player.update();
            //NPC
            for (int i = 0;i< npc[1].length;i++) {
                if (npc[currentMap][i] != null) {
                    npc[currentMap][i].update();
                }
            }
            for (int i =0 ;i < mon[1].length;i++) {
                if (mon[currentMap][i] != null) {
                    if(mon[currentMap][i].alive == true && mon[currentMap][i].dying==false){
                        mon[currentMap][i].update();
                    }
                    if(mon[currentMap][i].alive == false){
                        mon[currentMap][i].checkDrop();
                        mon[currentMap][i] = null;
                    }
                }
            }
        }
        if (gameState == pauseState){      
        }
    }
    @Override
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        Graphics2D g2D = (Graphics2D) g;
        
        if (gameState == titleState){
            ui.draw(g2D);
        }
        else{
            //tile
            tileM.draw(g2D);
            entityList.add(player);
            for (int i = 0;i<npc[1].length;i++) {
                if (npc[currentMap][i] != null) {
                    entityList.add(npc[currentMap][i]);
                }
            }
            for (int i = 0;i<obj[1].length;i++) {
                if (obj[currentMap][i] != null) {
                    entityList.add(obj[currentMap][i]);
                }
            }
            for (int i = 0;i<mon[1].length;i++) {
                if (mon[currentMap][i] != null) {
                    entityList.add(mon[currentMap][i]);
                }
            }
            //sort
            Collections.sort(entityList,new Comparator <Entity>() {
                @Override
                public int compare(Entity e1, Entity e2) {
                    int result = Integer.compare(e1.worldX, e1.worldY);
                    return result;
                }
            });
            //draw entity
            for(int i=0;i<entityList.size();i++){
                entityList.get(i).draw(g2D);
            }
            for(int i=0;i<entityList.size();i++){
                entityList.remove(i);
            }
            //draw UI
            ui.draw(g2D);
        }
        
        if (keyC.showDebug == true){
            g2D.setFont(new Font("Arial",Font.PLAIN,20));
            g2D.setColor(Color.white);
            int x = 10;
            int y = 400;
            int lineHeight = 20;
            g2D.drawString("X:"+ (player.worldX + player.solidArea.x)/titleSize, x,y); 
            y+= lineHeight;
            g2D.drawString("Y: "+(player.worldY + player.solidArea.y)/titleSize , x, y);
        }
        g2D.dispose();
    }
    public void playMusic(int i) throws UnsupportedAudioFileException, LineUnavailableException{
        music.setFile(i);
        music.play();
        music.loop();
    }
    public void stopMusic(){
        music.stop();
    }
    public void playSetFile(int i) throws UnsupportedAudioFileException, LineUnavailableException{
        se.setFile(i);
        se.play();
    }
}
