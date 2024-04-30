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
public class katanaSwordObject extends Entity {
    
    public katanaSwordObject(GamePanel gp) {
        super(gp);
        
        type = typeKatana;
        name = "katana";
        down1 = setUp("object/katana",gp.titleSize,gp.titleSize);
        attackValue = 3;
        attackArea.width = 40;
        attackArea.height = 40;
        description = "CHIENNN!";
    }
    
}
