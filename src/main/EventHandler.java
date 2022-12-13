package main;

import java.awt.*;

public class EventHandler {
    GamePanel gp;
    Rectangle eventRect;
    int eventRectDefaultX, eventRectDefaultY;
    public EventHandler(GamePanel gp){
        this.gp = gp;

        eventRect = new Rectangle();
        eventRect.x = 23;
        eventRect.y = 23;
        eventRect.width = 2;
        eventRect.height = 2;
        eventRectDefaultX = eventRect.x;
        eventRectDefaultY = eventRect.y;
    }

    public void checkEvent(){
        if(hit(27,16,"right") == true) {
            damagePit(gp.dialogueState);
        }
    }
    public boolean hit(int eventCol, int eventRow, String reDirection){
        boolean hit = false;

        gp.playerT.solidArea.x = gp.playerT.worldX + gp.playerT.solidArea.x;
        gp.playerT.solidArea.y = gp.playerT.worldY + gp.playerT.solidArea.y;
        eventRect.x = eventCol * gp.tileSize + eventRect.x;
        eventRect.y = eventRow * gp.tileSize + eventRect.y;

        if(gp.playerT.solidArea.intersects(eventRect)) {
            if(gp.playerT.direction.contentEquals(reDirection) || reDirection.contentEquals("any")) {
                hit = true;

            }
        }

        gp.playerT.solidArea.x = gp.playerT.solidAreaDefaultX;
        gp.playerT.solidArea.y = gp.playerT.solidAreaDefaultY;
        eventRect.x = eventRectDefaultX;
        eventRect.y = eventRectDefaultY;

        return hit;
    }
    public void damagePit(int gameState) {

        gp.gameState = gameState;
        gp.ui.currentDialogue = "You fall into a pit!";
        gp.playerT.life -= 1;

    }


}
