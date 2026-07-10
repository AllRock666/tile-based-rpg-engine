package entity;

import Main.GamePanel;
import java.util.Random;

public class NPC_OldMan extends Entity {

    public NPC_OldMan(GamePanel gp) {
        super(gp);
        
        direction = "down";
        speed = 1; // Slower than player
    }
    
    public void setAction() {
        actionLockCounter++;
        
        if (actionLockCounter == 120) { // Every 2 seconds
            Random random = new Random();
            int i = random.nextInt(100) + 1; // 1 to 100
            
            if (i <= 25) {
                direction = "up";
            } else if (i > 25 && i <= 50) {
                direction = "down";
            } else if (i > 50 && i <= 75) {
                direction = "left";
            } else if (i > 75 && i <= 100) {
                direction = "right";
            }
            
            actionLockCounter = 0;
        }
    }
}
