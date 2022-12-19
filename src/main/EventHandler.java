package main;

import java.awt.*;

public class EventHandler {
    GamePanel gp;
    EventRect eventRect [][];

    int previousEventX, previousEventY;
    boolean canTouchEvent = true;

    public EventHandler(GamePanel gp){
        this.gp = gp;
        eventRect = new EventRect[gp.maxWorldCol][gp.maxWorldRow];

        int col = 0;
        int row = 0;
        while(col < gp.maxWorldCol && row < gp.maxWorldRow) {
            eventRect[col][row] = new EventRect();
            eventRect[col][row].x = 23;
            eventRect[col][row].y = 23;
            eventRect[col][row].width = 2;
            eventRect[col][row].height = 2;
            eventRect[col][row].eventRectDefaultX = eventRect[col][row].x;
            eventRect[col][row].eventRectDefaultY = eventRect[col][row].y;

            col++;
            if(col == gp.maxWorldCol){
                col = 0;
                row++;
            }
        }
    }

    public void checkEvent(){

        // check if the player character is more than 1 tile away from the last event
        int xDistance = Math.abs(gp.playerT.worldX - previousEventX);
        int yDistance = Math.abs(gp.playerT.worldY - previousEventY);
        int distance = Math.max(xDistance, yDistance);
        if(distance > gp.tileSize){
            canTouchEvent = true;
        }

        if(canTouchEvent == true){

            if(hit(27,16,"right") == true) {damagePit(27,16,gp.dialogueState);}

            if(hit(23,19,"any") == true) {damagePit(27,16,gp.dialogueState);}

//          if(hit(27,16,"right") == true){teleport(gp.dialogueState);}

            if(hit(23,12,"up") == true) {healingPool(23,12,gp.dialogueState);}
        }

    }
    public boolean hit(int col, int row, String reDirection){
        boolean hit = false;

        gp.playerT.solidArea.x = gp.playerT.worldX + gp.playerT.solidArea.x;
        gp.playerT.solidArea.y = gp.playerT.worldY + gp.playerT.solidArea.y;
        eventRect[col][row].x = col * gp.tileSize + eventRect[col][row].x;
        eventRect[col][row].y = row * gp.tileSize + eventRect[col][row].y;

        if(gp.playerT.solidArea.intersects(eventRect[col][row]) && eventRect[col][row].eventDone == false ) {
            if(gp.playerT.direction.contentEquals(reDirection) || reDirection.contentEquals("any")) {
                hit = true;

                previousEventX = gp.playerT.worldX;
                previousEventY = gp.playerT.worldY;

            }
        }

        gp.playerT.solidArea.x = gp.playerT.solidAreaDefaultX;
        gp.playerT.solidArea.y = gp.playerT.solidAreaDefaultY;
        eventRect[col][row].x = eventRect[col][row].eventRectDefaultX;
        eventRect[col][row].y = eventRect[col][row].eventRectDefaultY;

        return hit;
    }

//    public void teleport(int gameState){
//
//        gp.gameState = gameState;
//        gp.ui.currentDialogue = "teleport!";
//        gp.playerT.worldX = gp.tileSize*37;
//        gp.playerT.worldY = gp.tileSize*10;
//    }
    public void damagePit(int col, int row, int gameState) {

        gp.gameState = gameState;
        gp.ui.currentDialogue = "You fall into a pit!";
        gp.playerT.life -= 1;
//      eventRect[col][row].eventDone = true;
        canTouchEvent = false;

    }
    public void healingPool(int col, int row, int gameState) {

        gp.gameState = gameState;
        gp.ui.currentDialogue = "You drink the water.\nYour life has been recovered.";
        gp.playerT.life = gp.playerT.MAXlife;

    }


}
