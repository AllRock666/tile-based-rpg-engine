package tile;

import java.awt.Color;
import java.awt.Graphics2D;

import Main.GamePanel;

public class TileManager {
    GamePanel gp;
    public Tile[] tile;
    public int mapTileNum[][];
    
    public TileManager(GamePanel gp) {
        this.gp = gp;
        tile = new Tile[10];
        mapTileNum = new int[gp.maxWorldCol][gp.maxWorldRow];
        
        getTileImage();
        loadMap();
    }
    
    public void getTileImage() {
        // Tile 0: Land (Forest Green)
        tile[0] = new Tile();
        tile[0].color = new Color(34, 139, 34); 
        
        // Tile 1: Water (Deep Blue)
        tile[1] = new Tile();
        tile[1].color = new Color(0, 105, 148); 
        tile[1].collision = true;
    }
    
    public void loadMap() {
        for(int row = 0; row < gp.maxWorldRow; row++) {
            for(int col = 0; col < gp.maxWorldCol; col++) {
                // Create a massive island surrounded by water with some lakes
                if(row < 5 || row >= gp.maxWorldRow - 5 || col < 5 || col >= gp.maxWorldCol - 5) {
                    mapTileNum[col][row] = 1; // Water border
                } else if (Math.random() < 0.05) {
                    mapTileNum[col][row] = 1; // 5% chance of lake water
                } else {
                    mapTileNum[col][row] = 0; // Land
                }
            }
        }
    }
    
    public void draw(Graphics2D g2) {
        int worldCol = 0;
        int worldRow = 0;
        
        while(worldCol < gp.maxWorldCol && worldRow < gp.maxWorldRow) {
            int tileNum = mapTileNum[worldCol][worldRow];
            
            int worldX = worldCol * gp.tileSize;
            int worldY = worldRow * gp.tileSize;
            
            // Calculate where on the screen to draw this tile based on player's position
            int screenX = worldX - gp.player.worldX + gp.player.screenX;
            int screenY = worldY - gp.player.worldY + gp.player.screenY;
            
            // Optimization: Only draw if the tile is within the screen bounds
            if(worldX + gp.tileSize > gp.player.worldX - gp.player.screenX &&
               worldX - gp.tileSize < gp.player.worldX + gp.player.screenX &&
               worldY + gp.tileSize > gp.player.worldY - gp.player.screenY &&
               worldY - gp.tileSize < gp.player.worldY + gp.player.screenY) {
                
                g2.setColor(tile[tileNum].color);
                g2.fillRect(screenX, screenY, gp.tileSize, gp.tileSize);
            }
            
            worldCol++;
            if(worldCol == gp.maxWorldCol) {
                worldCol = 0;
                worldRow++;
            }
        }
    }
}
