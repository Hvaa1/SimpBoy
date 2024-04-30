/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package main;

import Entity.Entity;

/**
 *
 * @author DELL
 */

public class CollisionChecker {
    GamePanel gp;
    public CollisionChecker(GamePanel gp){
        this.gp=gp;
    }
    public void checkTile(Entity entity){
        int entityLeftWorldX = entity.worldX + entity.solidArea.x;
        int entityRightWorldX = entity.worldX + entity.solidArea.x + entity.solidArea.width;
        int entityTopWorldY = entity.worldY + entity.solidArea.y;
        int entityBottomWorldY = entity.worldY + entity.solidArea.y + entity.solidArea.height;
        
        int entityLeftCol = entityLeftWorldX/gp.titleSize;
        int entityRightCol = entityRightWorldX/gp.titleSize;
        int entityTopRow = entityTopWorldY/gp.titleSize;
        int entityBottomRow = entityBottomWorldY/gp.titleSize;
        
        switch (entity.direction) {
        case "up" -> {
            entityTopRow = (entityTopWorldY - entity.speed) / gp.titleSize;
            entityLeftCol = (entityLeftWorldX - entity.speed) / gp.titleSize;
            entityRightCol = (entityRightWorldX - entity.speed) / gp.titleSize;
            }
        case "down" -> {
            entityBottomRow = (entityBottomWorldY + entity.speed) / gp.titleSize;
            entityLeftCol = (entityLeftWorldX - entity.speed) / gp.titleSize;
            entityRightCol = (entityRightWorldX - entity.speed) / gp.titleSize;
            }
        case "right" -> {
            entityRightCol = (entityRightWorldX + entity.speed) / gp.titleSize;
            entityTopRow = (entityTopWorldY - entity.speed) / gp.titleSize;
            entityBottomRow = (entityBottomWorldY - entity.speed) / gp.titleSize;
            }
        case "left" -> {
            entityLeftCol = (entityLeftWorldX - entity.speed) / gp.titleSize;
            entityTopRow = (entityTopWorldY - entity.speed) / gp.titleSize;
            entityBottomRow = (entityBottomWorldY - entity.speed) / gp.titleSize;
            }
        case "upleft" -> {
            entityTopRow = (entityTopWorldY - entity.speed) / gp.titleSize;
            entityLeftCol = (entityLeftWorldX - entity.speed) / gp.titleSize;
            }
        case "upright" -> {
            entityTopRow = (entityTopWorldY - entity.speed) / gp.titleSize;
            entityRightCol = (entityRightWorldX + entity.speed) / gp.titleSize;
            }
        case "downleft" -> {
            entityBottomRow = (entityBottomWorldY + entity.speed) / gp.titleSize;
            entityLeftCol = (entityLeftWorldX - entity.speed) / gp.titleSize;
            }
        case "downright" -> {
            entityBottomRow = (entityBottomWorldY + entity.speed) / gp.titleSize;
            entityRightCol = (entityRightWorldX + entity.speed) / gp.titleSize;
            }
    }

        // Perform collision detection
        int tileNum1 = gp.tileM.mapTileNum[gp.currentMap][entityLeftCol][entityTopRow];
        int tileNum2 = gp.tileM.mapTileNum[gp.currentMap][entityRightCol][entityTopRow];
        int tileNum3 = gp.tileM.mapTileNum[gp.currentMap][entityLeftCol][entityBottomRow];
        int tileNum4 = gp.tileM.mapTileNum[gp.currentMap][entityRightCol][entityBottomRow];

        // Check for collision in all four corners
        if (gp.tileM.tile[tileNum1].collision || gp.tileM.tile[tileNum2].collision ||
            gp.tileM.tile[tileNum3].collision || gp.tileM.tile[tileNum4].collision) {
                entity.collisionOn = true;
        } else {
            entity.collisionOn = false;
        }
    }
    public int checkObject(Entity entity ,boolean player ){
        int index = 999;
        for (int i = 0;i<gp.obj[1].length;i++){
            if (gp.obj[gp.currentMap][i]!=null){
                //solid entity area position
                entity.solidArea.x = entity.worldX +entity.solidArea.x ;
                entity.solidArea.y = entity.worldY+entity.solidArea.y ;
                
                //object solid
                gp.obj[gp.currentMap][i].solidArea.x = gp.obj[gp.currentMap][i].worldX +gp.obj[gp.currentMap][i].solidArea.x;
                gp.obj[gp.currentMap][i].solidArea.y = gp.obj[gp.currentMap][i].worldY +gp.obj[gp.currentMap][i].solidArea.y;
                
                switch (entity.direction){
                    case"up" -> {
                        entity.solidArea.y -= entity.speed;
                    }
                    case "down" -> {
                        entity.solidArea.y += entity.speed;
                    }
                     case"left" -> {
                         entity.solidArea.x -= entity.speed;
                    }
                     case "right" -> {
                         entity.solidArea.x += entity.speed;
                    }
                     
                     case "upright" -> {
                             entity.solidArea.x += entity.speed;
                             entity.solidArea.y -= entity.speed;
                     }
                     case "upleft" -> {
                             entity.solidArea.x -=entity.speed;
                             entity.solidArea.y -= entity.speed;
                     }
                     case "downright" -> {
                             entity.solidArea.x += entity.speed;
                             entity.solidArea.y += entity.speed;
                     }
                    case "downleft" -> {
                            entity.solidArea.x -= entity.speed;
                            entity.solidArea.y += entity.speed;
                        }
                     
                    }
                    if(entity.solidArea.intersects(gp.obj[gp.currentMap][i].solidArea)){
                        if (gp.obj[gp.currentMap][i].collision == true){
                            entity.collisionOn=true;
                            }
                            if(player == true){
                                index =i;
                            }
                    }
                    entity.solidArea.x = entity.solidAreaDefaultX;
                    entity.solidArea.y = entity.solidAreaDefaultY;
                    gp.obj[gp.currentMap][i].solidArea.x = gp.obj[gp.currentMap][i].solidAreaDefaultX;
                    gp.obj[gp.currentMap][i].solidArea.y = gp.obj[gp.currentMap][i].solidAreaDefaultY;
                }
            }
        return index;
    }
    public int checkEntity(Entity entity,Entity[][] target){
        int index = 999;
        for (int i = 0;i<target[1].length;i++){
            if (target[gp.currentMap][i]!=null){
                //solid entity area position
                entity.solidArea.x = entity.worldX +entity.solidArea.x ;
                entity.solidArea.y = entity.worldY+entity.solidArea.y ;
                
                //object solid
                target[gp.currentMap][i].solidArea.x = target[gp.currentMap][i].worldX +target[gp.currentMap][i].solidArea.x;
                target[gp.currentMap][i].solidArea.y = target[gp.currentMap][i].worldY +target[gp.currentMap][i].solidArea.y;
                
                switch (entity.direction){
                    case"up" -> {
                        entity.solidArea.y -= entity.speed;
                    }
                    case "down" -> {
                        entity.solidArea.y += entity.speed;
                    }
                     case"left" -> {
                         entity.solidArea.x -= entity.speed;
                    }
                     case "right" -> {
                         entity.solidArea.x += entity.speed;
                    }
                     case "upright" -> {
                             entity.solidArea.x += entity.speed;
                             entity.solidArea.y -= entity.speed;
                     }
                     case "upleft" -> {
                             entity.solidArea.x -=entity.speed;
                             entity.solidArea.y -= entity.speed;
                     }
                     case "downright" -> {
                             entity.solidArea.x += entity.speed;
                             entity.solidArea.y += entity.speed;
                     }
                    case "downleft" -> {
                            entity.solidArea.x -= entity.speed;
                            entity.solidArea.y += entity.speed;
                        }
                     
                    }
                    if(entity.solidArea.intersects(target[gp.currentMap][i].solidArea)){
                        if(target[gp.currentMap][i]!=entity){
                            entity.collisionOn=true;
                            index =i;
                        }
                    }
                    entity.solidArea.x = entity.solidAreaDefaultX;
                    entity.solidArea.y = entity.solidAreaDefaultY;
                    target[gp.currentMap][i].solidArea.x = target[gp.currentMap][i].solidAreaDefaultX;
                    target[gp.currentMap][i].solidArea.y = target[gp.currentMap][i].solidAreaDefaultY;
                }
            }
        return index;
    }
    public boolean checkPlayer(Entity entity){
        boolean contactPlayer = false;
                //solid entity area position
        entity.solidArea.x = entity.worldX +entity.solidArea.x ;
        entity.solidArea.y = entity.worldY+entity.solidArea.y ;
                
                //object solid
        gp.player.solidArea.x = gp.player.worldX +gp.player.solidArea.x;
        gp.player.solidArea.y = gp.player.worldY +gp.player.solidArea.y;
                
        switch (entity.direction){
            case"up" -> {
                entity.solidArea.y -= entity.speed;
            }
            case "down" -> {
                entity.solidArea.y += entity.speed;
            }
            case"left" -> {
                entity.solidArea.x -= entity.speed;
            }
            case "right" -> {
                entity.solidArea.x += entity.speed;
            }
            case "upright" -> {
                entity.solidArea.x += entity.speed;
                entity.solidArea.y -= entity.speed;
            }
            case "upleft" -> {
                entity.solidArea.x -=entity.speed;
                entity.solidArea.y -= entity.speed;
            }
            case "downright" -> {
                entity.solidArea.x += entity.speed;
                entity.solidArea.y += entity.speed;
            }
            case "downleft" -> {
                entity.solidArea.x -= entity.speed;
                entity.solidArea.y += entity.speed;
            }  
        }
        if(entity.solidArea.intersects(gp.player.solidArea)){
            entity.collisionOn=true;
            contactPlayer = true;
        }
        entity.solidArea.x = entity.solidAreaDefaultX;
        entity.solidArea.y = entity.solidAreaDefaultY;
        gp.player.solidArea.x = gp.player.solidAreaDefaultX;
        gp.player.solidArea.y = gp.player.solidAreaDefaultY;
        return contactPlayer;
    }
}
