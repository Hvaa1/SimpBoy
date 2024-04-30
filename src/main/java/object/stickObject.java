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
public class stickObject extends Entity {
    
    public stickObject(GamePanel gp) {
        super(gp);
        
        type = typeStick;
        name = "Gay Nhu Y";
        down1 = setUp("object/stick",gp.titleSize,gp.titleSize);
        attackValue = 2;
        attackArea.width = 30;
        attackArea.height = 30;
        description = "Xem gay cua lao Ton";
    }
    
    
}
