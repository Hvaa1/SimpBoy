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
public class coinObject extends Entity {
    GamePanel gp;
    public coinObject(GamePanel gp) {
        super(gp);
        this.gp = gp;
        type = typePickupOnly ;
        name = "Xu";
        value =1;
        down1 = setUp("object/coin_bronze",gp.titleSize,gp.titleSize);
    }
    @Override
    public void use(Entity entity){
        gp.ui.addMessage("Nhan duoc"+value + " "+ name );
        gp.player.coin +=value;
                
    }
    
}
