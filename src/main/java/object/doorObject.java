/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package object;

import Entity.Entity;
import main.GamePanel;

/**
 *
 * @author DELL
 */
public class doorObject extends Entity {
    public doorObject(GamePanel gp){
        super(gp);
        name = "Door";
        down1 = setUp("object/door",gp.titleSize,gp.titleSize);
        collision  = true;
        
        solidArea.x = 0;
        solidArea.y = 16;
        solidArea.width = 48;
        solidArea.height = 32;
        solidAreaDefaultX = solidArea.x;
        solidAreaDefaultY = solidArea.y;
    }
}
