/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Entity;

import java.util.Random;
import main.GamePanel;

/**
 *
 * @author DELL
 */
public class QuestNPC extends Entity {
    public QuestNPC(GamePanel gp){
        super(gp);
        direction = "down";
        speed = 1;
        getImage();
        setDialogue();
    }

    public final void getImage(){
        up1 = setUp("Npc/oldman_up_1",gp.titleSize,gp.titleSize);
        up2 = setUp("Npc/oldman_up_2",gp.titleSize,gp.titleSize);
        down1 = setUp("Npc/oldman_down_1",gp.titleSize,gp.titleSize);
        down2 = setUp("Npc/oldman_down_2",gp.titleSize,gp.titleSize);
        right1 = setUp("Npc/oldman_right_1",gp.titleSize,gp.titleSize);
        right2 = setUp("Npc/oldman_right_2",gp.titleSize,gp.titleSize);
        left1 = setUp("Npc/oldman_left_1",gp.titleSize,gp.titleSize);
        left2 = setUp("Npc/oldman_left_2",gp.titleSize,gp.titleSize);
//        upLeft1= setUp("player/Body_UpLeft1");
//        upLeft2 = setUp("player/Body_UpLeft2");
//        downLeft1 = setUp("player/Body_DownLeft1");
//        downLeft2 = setUp("player/Body_DownLeft2");
//        upRight1 = setUp("player/Body_UpRight1");
//        upRight2 = setUp("player/Body_UpRight2");
//        downRight1 = setUp("player/Body_DownRight1");
//        downRight2 = setUp("player/Body_DownRight2");
    }
    public void setDialogue(){
        dialogue[0] = "Hello World!";
        dialogue[1] = "Vanh dep trai nhat vu tru !";
        dialogue[2] = "Toi met qua roi ai cuu toi voi \n a a a a a a a a a a a a a a a a a";
        
    }
    @Override
    public void setAction(){
        actionLockTime++;
        if(actionLockTime ==120){
        Random random = new Random();
        int i = random.nextInt(100)+1 ;
            if(i<=25){
                direction = "up";
            }
            if (i >25 && i<=50){
                direction = "down";
            }
            if (i>50 && i <=75){
                direction = "left";
            }
            if (i>75 && i <=100){
                direction = "right";
            }
            actionLockTime = 0;
        }
    }
    @Override
    public void speak(){
       super.speak();
    }
}
