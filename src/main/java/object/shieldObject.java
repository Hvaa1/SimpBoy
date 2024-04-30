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
public class shieldObject extends Entity {
    
    public shieldObject(GamePanel gp) {
        super(gp);
        
        type = typeShield;
        name = "shield";
        down1 = setUp("object/shield_wood",gp.titleSize,gp.titleSize);
        defenseValue = 1;
        description ="khien cung nhu kiem";
    }
    
}
