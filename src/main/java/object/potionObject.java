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
public class potionObject extends Entity {
    GamePanel gp;
    public potionObject(GamePanel gp) {
        super(gp);
        this.gp = gp;
        
        type = typeConsumable;
        name = "Than Duoc";
        value = 2;
        down1 = setUp("object/potion_red",gp.titleSize,gp.titleSize);
        description ="cuu nhung pha choi ngu\ncua ban";
    }
    @Override
    public void use(Entity entity){
        gp.ui.addMessage("Ban dung 'than duoc' hoi"+value);
        entity.life += value;
        if(gp.player.life > gp.player.maxLife){
            gp.player.life = gp.player.maxLife;
        }
    } 
}
