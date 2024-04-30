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
public class chestObject extends Entity {
    public chestObject(GamePanel gp){
        super(gp);
        name = "Chest";
        down1 = setUp("object/Chest 1",gp.titleSize,gp.titleSize);
    }
}
