/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package tile;

import java.awt.Color;
import java.awt.Graphics2D;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import javax.imageio.ImageIO;
import main.BoostTool;
import main.GamePanel;

/**
 *
 * @author DELL
 */
public class TileManager {
    GamePanel gp;
    public Tile[] tile;
    public int mapTileNum[][][];
    public TileManager(GamePanel gp){
        this.gp = gp;
        tile = new Tile[50];
        mapTileNum = new int [gp.maxMap][gp.maxWorldCol][gp.maxWorldRow];
        
        getImageTile();
        loadMap("res/map/worldV3.txt",0);
        loadMap("res/map/interior01.txt",1);
    }
    public final void getImageTile(){
        //map Rung
//                setup(0, "mapRung/001", false);
//		setup(1, "mapRung/002", true);
//		setup(2, "mapRung/003", true);
//		setup(3, "mapRung/004", true);
//		setup(4, "mapRung/005", true);
//		setup(5, "mapRung/006", true);
//		setup(6, "mapRung/007", true);
//		setup(7, "mapRung/008", true);
//		setup(8, "mapRung/009", true);
//		setup(9, "mapRung/010", true);
//		setup(10, "mapRung/011", true);
//		setup(11, "mapRung/012", true);
//		setup(12, "mapRung/013", true);
//		setup(13, "mapRung/014", true);
//		setup(14, "mapRung/015", true);
//		setup(15, "mapRung/016", true);
//		setup(16, "mapRung/017", true);
//		setup(17, "mapRung/018", true);
//		setup(18, "mapRung/019", true);
//		setup(19, "mapRung/020", true);
//		setup(20, "mapRung/021", true);
//		setup(21, "mapRung/022", false);
//		setup(22, "mapRung/023", false);
//		setup(23, "mapRung/024", false);
//		setup(24, "mapRung/025", false);
//		setup(25, "mapRung/026", false);
//		setup(26, "mapRung/027", false);
//		setup(27, "mapRung/028", false);
//		setup(28, "mapRung/029", false);
//		setup(29, "mapRung/030", false);
//		setup(30, "mapRung/031", false);
//		setup(31, "mapRung/032", false);
//		setup(32, "mapRung/033", false);
//		setup(33, "mapRung/034", false);
//		setup(34, "mapRung/035", false);
//		setup(35, "mapRung/036", false);
//		setup(36, "mapRung/037", false);
//		setup(37, "mapRung/038", false);
//		setup(38, "mapRung/039", false);
//		setup(39, "mapRung/040", false);
//		setup(40, "mapRung/041", false);
//		setup(41, "mapRung/042", false);
//		setup(42, "mapRung/043", false);
//		setup(43, "mapRung/044", false);
//		setup(44, "mapRung/045", false);
//		setup(45, "mapRung/046", false);
//		setup(46, "mapRung/047", false);
//		setup(47, "mapRung/048", false);
//		setup(48, "mapRung/049", false);
//		setup(49, "mapRung/050", false);
//		setup(50, "mapRung/051", false);
//		setup(51, "mapRung/052", false);
//		setup(52, "mapRung/053", false);
//		setup(53, "mapRung/054", false);
//		setup(54, "mapRung/055", false);
//		setup(55, "mapRung/056", false);
//		setup(56, "mapRung/057", false);
//		setup(57, "mapRung/058", false);
//		setup(58, "mapRung/059", false);
//		setup(59, "mapRung/060", false);
//		setup(60, "mapRung/061", false);
//		setup(61, "mapRung/062", false);
//		setup(62, "mapRung/063", false);
//		setup(63, "mapRung/064", false);
//		setup(64, "mapRung/065", false);
//		setup(65, "mapRung/066", false);
//		setup(66, "mapRung/067", false);
//		setup(67, "mapRung/068", false);
//		setup(68, "mapRung/069", false);
//		setup(69, "mapRung/070", false);
//		setup(70, "mapRung/071", false);
//		setup(71, "mapRung/072", false);
//		setup(72, "mapRung/073", false);
//		setup(73, "mapRung/074", false);
//		setup(74, "mapRung/075", false);
//		setup(75, "mapRung/076", false);
//		setup(76, "mapRung/077", false);
//		setup(77, "mapRung/078", false);
//		setup(78, "mapRung/079", true);
//		setup(79, "mapRung/080", true);
//		setup(80, "mapRung/081", true);
//		setup(81, "mapRung/082", true);
//		setup(82, "mapRung/083", true);
//		setup(83, "mapRung/084", true);
//		setup(84, "mapRung/085", true);
//		setup(85, "mapRung/086", true);
//		setup(86, "mapRung/087", true);
//		setup(87, "mapRung/088", false);
//		setup(88, "mapRung/089", false);
//		setup(89, "mapRung/090", false);
//		setup(90, "mapRung/091", false);
//		
//		setup(91, "mapRung/092", false);
//		setup(92, "mapRung/093", false);
//		setup(93, "mapRung/094", false);
//		setup(94, "mapRung/095", false);
//		setup(95, "mapRung/096", false);
//		setup(96, "mapRung/097", false);
//		setup(97, "mapRung/098", false);
//		setup(98, "mapRung/099", false);
//		setup(99, "mapRung/100", false);
//		setup(100, "mapRung/101", false);
//		setup(101, "mapRung/102", false);
//		setup(102, "mapRung/103", false);
//		setup(103, "mapRung/104", false);
//		setup(104, "mapRung/105", false);
//		setup(105, "mapRung/106", false);
//		setup(106, "mapRung/107", false);
//		setup(107, "mapRung/108", false);
//		setup(108, "mapRung/109", false);
//		setup(109, "mapRung/110", true);
//		setup(110, "mapRung/111", true);
//		setup(111, "mapRung/112", true);
//		setup(112, "mapRung/113", false);
//		setup(113, "mapRung/114", true);
//		setup(114, "mapRung/115", true);
//		setup(115, "mapRung/116", true);
//
//		setup(116, "mapRung/117", false);
//		setup(117, "mapRung/118", false);
//		setup(118, "mapRung/119", false);
//		setup(119, "mapRung/120", false);
//		setup(120, "mapRung/121", false);
//		setup(121, "mapRung/122", false);
//		setup(122, "mapRung/123", true);
//		setup(123, "mapRung/124", true);
//		setup(124, "mapRung/125", true);
//		setup(125, "mapRung/126", true);
//		setup(126, "mapRung/127", true);
//		setup(127, "mapRung/128", true);
//		setup(128, "mapRung/129", true);
//		setup(129, "mapRung/130", true);
//		setup(130, "mapRung/131", true);
//		setup(131, "mapRung/132", false);
//		setup(132, "mapRung/133", false);
//                
//                //mapLang
//                setup(0, "mapLang/001", true);
//		setup(1, "mapLang/002", true);
//		setup(2, "mapLang/003", true);
//		setup(3, "mapLang/004", true);
//		setup(4, "mapLang/005", true);
//		setup(5, "mapLang/006", true);
//		setup(6, "mapLang/007", true);
//		setup(7, "mapLang/008", true);
//		setup(8, "mapLang/009", true);
//		setup(9, "mapLang/010", true);
//		setup(10, "mapLang/011", true);
//		setup(11, "mapLang/012", true);
//		setup(12, "mapLang/013", false);
//		setup(13, "mapLang/014", false);
//		setup(14, "mapLang/015", false);
//		setup(15, "mapLang/016", false);
//		setup(16, "mapLang/017", false);
//		setup(17, "mapLang/018", false);
//		setup(18, "mapLang/019", false);
//		setup(19, "mapLang/020", false);
//		setup(20, "mapLang/021", false);
//		setup(21, "mapLang/022", false);
//		setup(22, "mapLang/023", false);
//		setup(23, "mapLang/024", false);
//		setup(24, "mapLang/025", false);
//		setup(25, "mapLang/026", false);
//		setup(26, "mapLang/027", false);
//		setup(27, "mapLang/028", false);
//		setup(28, "mapLang/029", false);
//		setup(29, "mapLang/030", false);
//		setup(30, "mapLang/031", true);
//		setup(31, "mapLang/032", true);
//		setup(32, "mapLang/033", true);
//		setup(33, "mapLang/034", false);
//		setup(34, "mapLang/035", true);
//		setup(35, "mapLang/036", true);
//		setup(36, "mapLang/037", true);
//		setup(37, "mapLang/038", false);
//		setup(38, "mapLang/039", false);
//		setup(39, "mapLang/040", false);
//		setup(40, "mapLang/041", false);
//		setup(41, "mapLang/042", false);
//		setup(42, "mapLang/043", false);
//		setup(43, "mapLang/044", false);
//		setup(44, "mapLang/045", false);
//		setup(45, "mapLang/046", false);
//		setup(46, "mapLang/047", false);
//		setup(47, "mapLang/048", false);
//		setup(48, "mapLang/049", false);
//		setup(49, "mapLang/050", false);
//		setup(50, "mapLang/051", false);
//		setup(51, "mapLang/052", false);
//		setup(52, "mapLang/053", false);
//		setup(53, "mapLang/054", false);
//		setup(54, "mapLang/055", false);
//		setup(55, "mapLang/056", false);
//		setup(56, "mapLang/057", false);
//		setup(57, "mapLang/058", false);
//		setup(58, "mapLang/059", false);
//		setup(59, "mapLang/060", true);
//		setup(60, "mapLang/061", true);
//		setup(61, "mapLang/062", true);
//		setup(62, "mapLang/063", true);
//		setup(63, "mapLang/064", true);
//		setup(64, "mapLang/065", true);
//		setup(65, "mapLang/066", true);
//		setup(66, "mapLang/067", true);
//		setup(67, "mapLang/068", true);
//		setup(68, "mapLang/069", true);
//		setup(69, "mapLang/070", true);
//		setup(70, "mapLang/071", true);
//		setup(71, "mapLang/072", true);
//		setup(72, "mapLang/073", true);
//		setup(73, "mapLang/074", true);
//		setup(74, "mapLang/075", true);
//		setup(75, "mapLang/076", true);
//		setup(76, "mapLang/077", true);
//		setup(77, "mapLang/078", true);
//		setup(78, "mapLang/079", true);
//		setup(79, "mapLang/080", true);
//		setup(80, "mapLang/081", true);
//		setup(81, "mapLang/082", true);
//		setup(82, "mapLang/083", true);
//		setup(83, "mapLang/084", true);
//		setup(84, "mapLang/085", true);
//		setup(85, "mapLang/086", true);
//		setup(86, "mapLang/087", true);
//		setup(87, "mapLang/088", true);
//		setup(88, "mapLang/089", true);
//		setup(89, "mapLang/090", true);
//		setup(90, "mapLang/091", true);
//		setup(91, "mapLang/092", true);
//		setup(92, "mapLang/093", true);
//		setup(93, "mapLang/094", true);
//		setup(94, "mapLang/095", true);
//		setup(95, "mapLang/096", true);
//		setup(96, "mapLang/097", true);
//		setup(97, "mapLang/098", true);
//		setup(98, "mapLang/099", true);
//		setup(99, "mapLang/100", true);
//		setup(100, "mapLang/101", true);
        setup(0, "grass00", false);
        setup(1, "grass00", false);
        setup(2, "grass00", false);
        setup(3, "grass00", false);
        setup(4, "grass00", false);
        setup(5, "grass00", false);
        setup(6, "grass00", false);
        setup(7, "grass00", false);
        setup(8, "grass00", false);
        setup(9, "grass00", false);
        
        setup(10, "grass00", false);
        setup(11, "grass01", false);
        setup(12, "water00", true);
        setup(13, "water01", true);
        setup(14, "water02", true);
        setup(15, "water03", true);
        setup(16, "water04", true);
        setup(17, "water05", true);
        setup(18, "water06", true);
        setup(19, "water07", true);
        setup(20, "water08", true);
        setup(21, "water09", true);
        setup(22, "water10", true);
        setup(23, "water11", true);
        setup(24, "water12", true);
        setup(25, "water13", true);
        setup(26, "road00", false);
        setup(27, "road01", false);
        setup(28, "road02", false);
        setup(29, "road03", false);
        setup(30, "road04", false);
        setup(31, "road05", false);
        setup(32, "road06", false);
        setup(33, "road07", false);
        setup(34, "road08", false);
        setup(35, "road09", false);
        setup(36, "road10", false);
        setup(37, "road11", false);
        setup(38, "road12", false);
        setup(39, "earth", false);
        setup(40, "wall", true);
        setup(41, "tree", true);
        setup(42, "hut", true);
        setup(43, "floor01", false);
        setup(44, "table01", true);
    }
    public void setup(int i,String imagePath, boolean collision ){
        BoostTool bTool = new BoostTool();
        try{
            tile[i] = new Tile();
            tile[i].image =ImageIO.read(new File("res/title/"+ imagePath +".png"));
            tile[i].image = bTool.scaleImage(tile[i].image,gp.titleSize , gp.titleSize);
            tile[i].collision=collision;
        }catch(IOException e){
        }
    }
    public final void loadMap(String filePath ,int map){
        try {
            FileInputStream fis = new FileInputStream(filePath);
            try (BufferedReader br = new BufferedReader(new InputStreamReader(fis))) {
                int row = 0,col = 0;
                while (col<gp.maxWorldCol&&row<gp.maxWorldRow){
                    String line = br.readLine();
                    while(col<gp.maxWorldCol){
                        String number[] = line.split(" ");
                        int num = Integer.parseInt(number[col]);
                        mapTileNum[map][col][row] = num;
                        col++;
                    }
                    if (col==gp.maxWorldCol){
                        col = 0;
                        row++;
                    }
                    
                }
            }
        }
        catch(IOException e){
        }
    }
    public void draw(Graphics2D g2D){
        int worldCol =0,worldRow=0;
        
        while(worldCol<gp.maxWorldCol && worldRow<gp.maxWorldRow){
            int tileNum  = mapTileNum[gp.currentMap][worldCol][worldRow];
            int worldX = worldCol * gp.titleSize;
            int worldY = worldRow * gp.titleSize;
            int screenX = worldX - gp.player.worldX + gp.player.screenX;
            int screenY = worldY - gp.player.worldY + gp.player.screenY;
            if (worldX + gp.titleSize > gp.player.worldX - gp.player.screenX &&
                worldY - gp.titleSize < gp.player.worldY + gp.player.screenY &&
                worldX - gp.titleSize < gp.player.worldX + gp.player.screenX &&
                worldY + gp.titleSize > gp.player.worldY - gp.player.screenY)
            {
                g2D.drawImage(tile[tileNum].image, screenX, screenY,null);
            }
            worldCol++;
            if (worldCol == gp.maxWorldCol){
                worldCol=0;
                worldRow++;
            }
        }
    }
}
