package Main;

import java.awt.Color;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JPanel;
import Main.KeyHandler;
import entity.Player;

public class GamePanel extends JPanel implements Runnable{
	
	final int OgTileSize = 16; //16*16 tile size
	final int scale = 3; //we scale the tile so it looks 48*48 in game
	
	public final int tileSize = OgTileSize * scale; //48 * 48 tile size
	
	public final int maxScreenRow = 12;
	public final int maxScreenCol = 16;
	
	public final int maxScreenWidth = tileSize * maxScreenCol;
	public final int maxScreenHeight = tileSize * maxScreenRow;
	
	// WORLD SETTINGS
	public final int maxWorldCol = 50;
	public final int maxWorldRow = 50;
	public final int worldWidth = tileSize * maxWorldCol;
	public final int worldHeight = tileSize * maxWorldRow;
	
	public tile.TileManager tileM = new tile.TileManager(this);
	public CollisionChecker cChecker = new CollisionChecker(this);
	public AssetSetter aSetter = new AssetSetter(this);
	
	// ENTITY AND OBJECT
	public KeyHandler keyH = new KeyHandler();
	public Player player = new Player(this, this.keyH);
	public object.SuperObject obj[] = new object.SuperObject[10];
	public entity.Entity npc[] = new entity.Entity[10];
	
	
	Thread gameThread;
	
	int FPS = 60;
	
	
	
	public GamePanel() {
		this.setPreferredSize(new Dimension(maxScreenWidth, maxScreenHeight));
		this.setBackground(Color.white);
		this.setDoubleBuffered(true);
		this.addKeyListener(keyH);
		this.setFocusable(true);
		this.requestFocus();
	}
	
	public void setupGame() {
		aSetter.setObject();
		aSetter.setNPC();
	}
	
	public void startGameThread() {
		
		gameThread = new Thread(this);
		gameThread.start();
	}

	@Override
/*	public void run() {
		
		double drawInterval = 1000000000/FPS;
		double nextDrawTime = System.nanoTime() + drawInterval;
		
		while(gameThread != null) {
			
			//long currentTime = System.nanoTime();
			
			//System.out.println("Yasham is gay.");
			/* 1. Update the Player position
			 * 2. Draw the screen with updated information
			
			
			update();
			repaint();
			
			try {
				double remainingTime = nextDrawTime - System.nanoTime();
				remainingTime = remainingTime/1000000;
				
				if(remainingTime < 0) {
					remainingTime = 0;
				}
				
				Thread.sleep((long) remainingTime);
				
				nextDrawTime += drawInterval;
			}
			catch (InterruptedException e) {
				e.printStackTrace();
			}
			
			}
	}*/
	
	
	public void run() {
		double drawInterval = 1000000000 / FPS;
		double delta = 0;
		long lastTime = System.nanoTime();
		long currentTime;
		int drawCount = 0;
		long timer = 0;
		
		while(gameThread != null) {
			currentTime = System.nanoTime();
			
			timer += (currentTime - lastTime);
			
			delta += (currentTime - lastTime) / drawInterval;
			
			lastTime = currentTime;
			
			
			if(delta >= 1) {
				update();
				repaint();
				delta--;
				drawCount++;
			}
			
			if(timer >= 1000000000) {
				
				System.out.println("FPS :" + drawCount);
				drawCount = 0;
				timer = 0;
			}
		}
	}
	
	public void update() {
		player.update();
		
		for(int i = 0; i < npc.length; i++) {
			if(npc[i] != null) {
				npc[i].update();
			}
		}
	}
	public void paintComponent(Graphics g) {
		
		super.paintComponent(g);
		
		Graphics2D g2 = (Graphics2D)g;
		
		tileM.draw(g2);
		
		for(int i = 0; i < obj.length; i++) {
			if(obj[i] != null) {
				obj[i].draw(g2, this);
			}
		}
		
		for(int i = 0; i < npc.length; i++) {
			if(npc[i] != null) {
				npc[i].draw(g2);
			}
		}
		
		player.draw(g2);
		g2.dispose();
		
	}
	

}
