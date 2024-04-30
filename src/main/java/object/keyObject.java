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
public class keyObject extends Entity {
    public keyObject(GamePanel gp){
        super(gp);
        name = "Key";
        down1 = setUp("object/Key01",gp.titleSize,gp.titleSize);
    }
}
