package Main;

import object.Obj_Chest;
import entity.NPC_OldMan;

public class AssetSetter {
    GamePanel gp;
    
    public AssetSetter(GamePanel gp) {
        this.gp = gp;
    }
    
    public void setObject() {
        gp.obj[0] = new Obj_Chest();
        gp.obj[0].worldX = gp.tileSize * 25; // Close to player start (23, 21)
        gp.obj[0].worldY = gp.tileSize * 21;
        
        gp.obj[1] = new Obj_Chest();
        gp.obj[1].worldX = gp.tileSize * 23;
        gp.obj[1].worldY = gp.tileSize * 23;
    }
    
    public void setNPC() {
        gp.npc[0] = new NPC_OldMan(gp);
        gp.npc[0].worldX = gp.tileSize * 21;
        gp.npc[0].worldY = gp.tileSize * 21;
    }
}
