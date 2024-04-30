/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Entity;

import main.GamePanel;
import object.keyObject;
import object.potionObject;
import object.swordObject;

/**
 *
 * @author DELL
 */
public class tradeNPC extends Entity{
    public tradeNPC(GamePanel gp){
        super(gp);
        direction = "down";
        speed = 1;
        getImage();
        setDialogue();
        setItem();
    }
    public void getImage(){
        
        up1 = setUp("Npc/QuestNPC",gp.titleSize,gp.titleSize);
        up2 = setUp("Npc/QuestNPC",gp.titleSize,gp.titleSize);
        down1 = setUp("Npc/QuestNPC",gp.titleSize,gp.titleSize);
        down2 = setUp("Npc/QuestNPC",gp.titleSize,gp.titleSize);
        right1 = setUp("Npc/QuestNPC",gp.titleSize,gp.titleSize);
        right2 = setUp("Npc/QuestNPC",gp.titleSize,gp.titleSize);
        left1 = setUp("Npc/QuestNPC",gp.titleSize,gp.titleSize);
        left2 = setUp("Npc/QuestNPC",gp.titleSize,gp.titleSize);
    }
    public void setDialogue(){
        dialogue[0] = "Mua nhanh khong dan phuong den!!!";     
    }
    public void setItem(){
        inventory.add(new potionObject(gp));
        inventory.add(new keyObject(gp));
        inventory.add(new swordObject(gp));
    }
}
