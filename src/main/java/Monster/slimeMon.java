/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Monster;

import Entity.Entity;
import java.util.Random;
import main.GamePanel;
import object.coinObject;
import object.katanaSwordObject;

/**
 *
 * @author DELL
 */
public class slimeMon extends Entity {
    public slimeMon (GamePanel gp){
        super(gp);
        type = typeMonster;
        name  = "Green Slime";
        speed = 1;
        maxLife = 3;
        life = maxLife;
        attack = 5;
        defense = 0;
        exp = 5;
        
        solidArea.x = 3;
        solidArea.y = 18;
        solidArea.height=30;
        solidArea.width = 42;
        solidAreaDefaultX = solidArea.x;
        solidAreaDefaultY = solidArea.y;
        
        getImage();
    }
    public void getImage (){
        up1 = setUp("Monster/greenslime_down_1",gp.titleSize,gp.titleSize);
        up2 = setUp("Monster/greenslime_down_2",gp.titleSize,gp.titleSize);
        down1 = setUp("Monster/greenslime_down_1",gp.titleSize,gp.titleSize);
        down2 = setUp("Monster/greenslime_down_2",gp.titleSize,gp.titleSize);
        right1 = setUp("Monster/greenslime_down_1",gp.titleSize,gp.titleSize);
        right2 = setUp("Monster/greenslime_down_2",gp.titleSize,gp.titleSize);
        left1 = setUp("Monster/greenslime_down_1",gp.titleSize,gp.titleSize);
        left2 = setUp("Monster/greenslime_down_2",gp.titleSize,gp.titleSize);
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
    public void damageReact(){
        actionLockTime = 0;
        direction = gp.player.direction;
    }
    @Override
    public void checkDrop(){
        int i = new Random().nextInt(100)+1;
        if (i<99){
            dropItem(new coinObject(gp));
        }
        if (i>=99 && i< 100){
            dropItem(new katanaSwordObject(gp));
        }
    }
    
}
