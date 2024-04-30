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
public class heartObject extends Entity {
    public heartObject(GamePanel gp){
        super(gp);
        name = "Heart";
        image1 = setUp ("object/heart_full",gp.titleSize,gp.titleSize);
        image2 = setUp ("object/heart_half",gp.titleSize,gp.titleSize);
        image3 = setUp("object/heart_blank",gp.titleSize,gp.titleSize);
    }
}
