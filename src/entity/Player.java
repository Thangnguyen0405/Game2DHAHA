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



        setDefaultValue();
        GetPlayerImage();
    }
    public void setDefaultValue()
    {

        worldX = gp.tileSize*23;
        worldY = gp.tileSize*21;
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
            up1=setup("/blueBoy/boy_up_1");
            up2=setup("/blueBoy/boy_up_2");
            down1=setup("/blueBoy/boy_down_1");
            down2 = setup("/blueBoy/boy_down_2");
            left1 = setup("/blueBoy/boy_left_1");
            left2 = setup("/blueBoy/boy_left_2");
            right1 = setup("/blueBoy/boy_right_1");
            right2 = setup("/blueBoy/boy_right_2");
    }

    public void update()
    {
        if(Control.upPressed== true|| Control.downPressed == true||
                Control.rightPressed==true|| Control.leftPressed==true)
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
            //CHECK OBJECT COLLISION
            int objIndex = gp.cChecker.checkObject(this,true);
            interactNPC(npcIndex);
            pickUpObject(objIndex);
            if(collisionOn == false)
            {
                switch (direction)
                {
                    case "up" -> worldY -= speed;
                    case "down" -> worldY += speed;
                    case "left" -> worldX -= speed;
                    case "right" -> worldX += speed;
                }
            }
            spriteCounter++;
            if (spriteCounter >32)
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
        if(i != 999)
        {
            gp.gameState = gp.dialogueState;
            gp.npc[i].speak();
        }
    }
    public void draw(Graphics2D t2)//cap nhat trang thai cua player tren man hinh
    {
        //t2.setColor(Color.white);
        //t2.fillRect(x,y,gp.tileSize,gp.tileSize);
        BufferedImage image = null;
        switch (direction) {
            case "up" -> {
                if (spriteNum == 1) {
                    image = up1;
                }
                if (spriteNum == 2) {
                    image = up2;
                }
            }
            case "down" -> {
                if (spriteNum == 1) {
                    image = down1;
                }
                if (spriteNum == 2) {
                    image = down2;
                }
            }
            case "left" -> {
                if (spriteNum == 1) {
                    image = left1;
                }
                if (spriteNum == 2) {
                    image = left2;
                }
            }
            case "right" -> {
                if (spriteNum == 1) {
                    image = right1;
                }
                if (spriteNum == 2) {
                    image = right2;
                }
            }
        }
        t2.drawImage(image,screenX,screenY,gp.tileSize,gp.tileSize,null);
    }
}