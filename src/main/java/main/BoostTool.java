/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package main;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

/**
 *
 * @author DELL
 */
public class BoostTool {
    public BufferedImage scaleImage(BufferedImage original , int width,int height){
        BufferedImage scaledImage = new BufferedImage(width,height,original.getType());
        Graphics2D g2D = scaledImage.createGraphics();
        g2D.drawImage(original, 0, 0, width, height, null);
        g2D.dispose();
        return scaledImage;
    }
}
