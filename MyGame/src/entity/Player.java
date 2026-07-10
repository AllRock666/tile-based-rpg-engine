package entity;

import java.awt.Color;
import java.awt.Graphics2D;

import Main.GamePanel;
import Main.KeyHandler;

public class Player extends Entity{
	
	GamePanel gp;
	KeyHandler keyH;
	
	public final int screenX;
	public final int screenY;
	
	public Player(GamePanel gp, KeyHandler keyH) {
		super(gp);
		
		this.gp = gp;
		this.keyH = keyH;
		
		screenX = gp.maxScreenWidth / 2 - (gp.tileSize / 2);
		screenY = gp.maxScreenHeight / 2 - (gp.tileSize / 2);
		
		solidArea = new java.awt.Rectangle();
		solidArea.x = 8;
		solidArea.y = 16;
		solidArea.width = 32;
		solidArea.height = 32;
		
		setDefaultValues();
	}

	public void setDefaultValues() {
		worldX = gp.tileSize * 23; // Start near the middle of the 50x50 map
		worldY = gp.tileSize * 21;
		speed = 4;
	}

	public void update() {
		
		if(keyH.upPressed == true || keyH.downPressed == true || 
		   keyH.leftPressed == true || keyH.rightPressed == true) {
			
			if(keyH.upPressed == true) {
				direction = "up";
			} else if(keyH.downPressed == true ) {
				direction = "down";
			} else if(keyH.leftPressed == true ) {
				direction = "left";
			} else if(keyH.rightPressed == true) {
				direction = "right";
			}
			
			// CHECK TILE COLLISION
			collisionOn = false;
			gp.cChecker.checkTile(this);
			
			// CHECK OBJECT COLLISION
			int objIndex = gp.cChecker.checkObject(this, true);
			pickUpObject(objIndex);
			
			// IF COLLISION IS FALSE, PLAYER CAN MOVE
			if(collisionOn == false) {
				switch(direction) {
				case "up": worldY -= speed; break;
				case "down": worldY += speed; break;
				case "left": worldX -= speed; break;
				case "right": worldX += speed; break;
				}
			}
		}
	}

	public void pickUpObject(int i) {
		if(i != 999) {
			String objectName = gp.obj[i].name;
			
			switch(objectName) {
			case "Chest":
				gp.obj[i] = null; // Item disappears when picked up
				break;
			}
		}
	}

	public void draw(Graphics2D g2) {
		g2.setColor(Color.black);
		g2.fillRect(screenX, screenY, gp.tileSize, gp.tileSize);
	}
}
