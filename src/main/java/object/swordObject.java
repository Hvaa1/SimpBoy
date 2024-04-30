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
public class swordObject extends Entity {
    public swordObject(GamePanel gp){
        super(gp);
        
        type = typeNormalSword;
        name = "sword";
        down1 = setUp("object/sword_normal",gp.titleSize,gp.titleSize);
        attackValue = 1;
        attackArea.width = 36;
        attackArea.height = 36;
        description = "Nap tien thi co kiem xin";
    }
    
}
