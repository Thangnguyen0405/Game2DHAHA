package entity;

import main.GamePanel;
import main.KeyInput;
import main.UtilityTool;

import javax.imageio.ImageIO;
import javax.swing.plaf.basic.BasicTreeUI;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.FileInputStream;
import java.io.IOException;
import java.awt.Graphics2D;
import java.util.Objects;

public class Player extends Entity{
    public KeyInput Control;
    public final int screenX;
    public final int screenY;
    public int hasKey = 0;



    public Player(GamePanel gp, KeyInput Control)
    {
        super(gp);
        this.Control=Control;

        screenX=gp.screenWidth/2 - (gp.tileSize/2);
        screenY=gp.screenHeight/2 - (gp.tileSize/2);

        solidArea = new Rectangle();
        solidArea.x = 8  ;
        solidArea.y = 16 ;
        solidAreaDefaultX = solidArea.x;
        solidAreaDefaultY = solidArea.y;
        solidArea.width = 32;
        solidArea.height = 32;

        attackArea.width = 36;
        attackArea.height = 36;

        setDefaultValue();
        GetPlayerImage();
        GetPlayerAttackImage();
    }
    public void setDefaultValue()
    {

        worldX = gp.tileSize*23;
        worldY = gp.tileSize*21;
//        worldX = gp.tileSize*10;
//        worldY = gp.tileSize*13;
//        worldX = 100;
//        worldY = 100;
        speed=4;
        direction="up";

        // PLAYER STATUS
        MAXlife = 6;
        life = MAXlife;
    }
    public void GetPlayerImage()
    {
            up1=setup("/blueBoy/boy_up_1",gp.tileSize, gp.tileSize);
            up2=setup("/blueBoy/boy_up_2",gp.tileSize, gp.tileSize);
            down1=setup("/blueBoy/boy_down_1",gp.tileSize, gp.tileSize);
            down2 = setup("/blueBoy/boy_down_2",gp.tileSize, gp.tileSize);
            left1 = setup("/blueBoy/boy_left_1",gp.tileSize, gp.tileSize);
            left2 = setup("/blueBoy/boy_left_2",gp.tileSize, gp.tileSize);
            right1 = setup("/blueBoy/boy_right_1",gp.tileSize, gp.tileSize);
            right2 = setup("/blueBoy/boy_right_2",gp.tileSize, gp.tileSize);
    }
    public void GetPlayerAttackImage(){

        attackUp1 = setup("/blueBoy/boy_attack_up_1",gp.tileSize, gp.tileSize*2);
        attackUp2 = setup("/blueBoy/boy_attack_up_2",gp.tileSize, gp.tileSize*2);
        attackDown1 = setup("/blueBoy/boy_attack_down_1",gp.tileSize, gp.tileSize*2);
        attackDown2 = setup("/blueBoy/boy_attack_down_2",gp.tileSize, gp.tileSize*2);
        attackLeft1 = setup("/blueBoy/boy_attack_left_1",gp.tileSize*2, gp.tileSize);
        attackLeft2 = setup("/blueBoy/boy_attack_left_2",gp.tileSize*2, gp.tileSize);
        attackRight1 = setup("/blueBoy/boy_attack_right_1",gp.tileSize*2, gp.tileSize);
        attackRight2 = setup("/blueBoy/boy_attack_right_2",gp.tileSize*2, gp.tileSize);
    }

    public void update()
    {
        if(attacking == true){
            attacking();
        }

        if(Control.upPressed== true|| Control.downPressed == true||
                Control.rightPressed==true|| Control.leftPressed==true || Control.enterPressed == true)
        {
            if(Control.upPressed==true)
            {
                direction = "up";
            }
            else if(Control.downPressed==true)
            {
                direction = "down";
            }
            else if(Control.leftPressed==true)
            {
                direction = "left";
            }
            else if(Control.rightPressed==true)
            {
                direction = "right";
            }
            //CHECK TILE COLLISION
            collisionOn = false;
            gp.cChecker.checkTile(this);

            //CHECK NPC COLLISION
            int npcIndex = gp.cChecker.checkEntity(this,gp.npc);
            interactNPC(npcIndex);

            //CHECK OBJECT COLLISION
            int objIndex = gp.cChecker.checkObject(this,true);
            pickUpObject(objIndex);

            //CHECK MONSTER COLLISION
            int monsterIndex = gp.cChecker.checkEntity(this, gp.monster);
            contactMonster(monsterIndex);

            //CHECK EVENT
            gp.eHandler.checkEvent();

            gp.Control.enterPressed = false;

            if(collisionOn == false && Control.enterPressed == false)
            {
                switch (direction)
                {
                    case "up" -> worldY -= speed;
                    case "down" -> worldY += speed;
                    case "left" -> worldX -= speed;
                    case "right" -> worldX += speed;
                }
            }
            Control.enterPressed = false;
            spriteCounter++;
            if (spriteCounter > 12)
            {
                if(spriteNum == 1)
                {
                    spriteNum = 2;
                }
                else if(spriteNum == 2)
                {
                    spriteNum = 1;
                }
                spriteCounter=0;
            }
        }


        // This needs to be outside of key if statement!
        if(invincible == true){
            invincibleCounter++;
            if(invincibleCounter > 60){
                invincible = false;
                invincibleCounter = 0;
            }
        }
    }

    public void attacking(){

        spriteCounter++;

        if(spriteCounter <= 5){
            spriteNum = 1;
        }
        if(spriteCounter > 5 && spriteCounter <= 25){
            spriteNum = 2;

            //Save the current worldX, worldY, solidArea
            int currentWorldX = worldX;
            int currentWorldY = worldY;
            int solidAreaWidth = solidArea.width;
            int solidAreaHeight = solidArea.height;

            //Adjust player's worldX,Y for the attackArea
            switch(direction){
                case "up": worldY -= attackArea.height; break;
                case "down": worldY += attackArea.height; break;
                case "left": worldX -= attackArea.width; break;
                case "right": worldX += attackArea.width; break;
            }

            //attackArea becomes solidArea
            solidArea.width = attackArea.width;
            solidArea.height = attackArea.height;

            //Check monster collision with the updated worldX, worldY and solidArea
            int monsterIndex = gp.cChecker.checkEntity(this, gp.monster);
            damageMonster(monsterIndex);

            // After checking collision, restore the original data
            worldX = currentWorldX;
            worldY = currentWorldY;
            solidArea.width = solidAreaWidth;
            solidArea.height = solidAreaHeight;

        }
        if(spriteCounter > 25) {
            spriteNum = 1;
            spriteCounter = 0;
            attacking = false;
        }
    }

    public void pickUpObject(int i)
    {
        if(i != 999)
        {

            String objectName = gp.obj[i].name;
            switch (objectName)
            {
                case "Key":
                    gp.playSE(1);
                    hasKey++;
                    gp.obj[i] = null;
                    gp.ui.showMessage("You Got A Key");
                    break;
                case "Door":
                    gp.playSE(3);
                    if(hasKey > 0){
                        gp.obj[i] = null;
                        hasKey--;
                        gp.ui.showMessage("You Opened a Door");
                    }
                    else
                    {
                        gp.ui.showMessage("You Need A Key");
                    }
                    break;
                case "Boots":
                    gp.playSE(2);
                    gp.obj[i] = null;
                    speed += 4;
                    gp.ui.showMessage("You Picked Boots");
                    break;
                case "Chest":
                    gp.ui.gameFinished = true;
                    gp.stopMusic();
                    gp.playSE(4);
                    break;
            }

        }
    }
    public void interactNPC(int i)
    {
        if(gp.Control.enterPressed == true) {
            if (i != 999) {
                gp.gameState = gp.dialogueState;
                gp.npc[i].speak();
            } else {
                gp.playSE(7);
                attacking = true;
            }
        }
    }


    public void contactMonster(int i){

        if(i != 999){
            if(invincible == false) {
                gp.playSE(6);
                life -= 1;
                invincible = true;

            }

        }

    }

    public void damageMonster(int i){

        if(i != 999){

            if(gp.monster[i].invincible == false){

                gp.playSE(5);
                gp.monster[i].life -= 1;
                gp.monster[i].invincible = true;
                gp.monster[i].damageReaction();

                if(gp.monster[i].life <= 0){
                    gp.monster[i].dying = true;
                }
            }

        }

    }
    public void draw(Graphics2D t2)//cap nhat trang thai cua player tren man hinh
    {
        //t2.setColor(Color.white);
        //t2.fillRect(x,y,gp.tileSize,gp.tileSize);

        int tempScreenX = screenX;
        int tempScreenY = screenY;

        BufferedImage image = null;
        switch (direction) {
            case "up" -> {
                if (attacking == false) {
                    if (spriteNum == 1) {
                        image = up1;
                    }
                    if (spriteNum == 2) {
                        image = up2;
                    }
                }
                if (attacking == true) {
                    tempScreenY = screenY - gp.tileSize;
                    if (spriteNum == 1) {
                        image = attackUp1;
                    }
                    if (spriteNum == 2) {
                        image = attackUp2;
                    }
                }
            }

            case "down" -> {
                if (attacking == false) {
                    if (spriteNum == 1) {
                        image = down1;
                    }
                    if (spriteNum == 2) {
                        image = down2;
                    }
                }
                if (attacking == true) {
                    if (spriteNum == 1) {
                        image = attackDown1;
                    }
                    if (spriteNum == 2) {
                        image = attackDown2;
                    }
                }
            }

            case "left" -> {
                if (attacking == false) {
                    if (spriteNum == 1) {
                        image = left1;
                    }
                    if (spriteNum == 2) {
                        image = left2;
                    }
                }
                if (attacking == true) {
                    tempScreenX = screenX - gp.tileSize;
                    if (spriteNum == 1) {
                        image = attackLeft1;
                    }
                    if (spriteNum == 2) {
                        image = attackLeft2;
                    }
                }
            }

            case "right" -> {
                if (attacking == false) {
                    if (spriteNum == 1) {
                        image = right1;
                    }
                    if (spriteNum == 2) {
                        image = right2;
                    }
                }
                if (attacking == true) {
                    if (spriteNum == 1) {
                        image = attackRight1;
                    }
                    if (spriteNum == 2) {
                        image = attackRight2;
                    }
                }
            }


}
        if(invincible == true){

            t2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.3f));

        }
        t2.drawImage(image,tempScreenX,tempScreenY,null);

        //Reset alpha
        t2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1f));

        //DEBUG
//        t2.setFont(new Font("Arial", Font.PLAIN, 26));
//        t2.setColor(Color.white);
//        t2.drawString("Invincible:"+invincibleCounter, 10, 400);
    }
}