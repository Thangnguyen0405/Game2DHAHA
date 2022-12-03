package entity;

import main.GamePanel;
import main.KeyInput;
import javax.imageio.ImageIO;
import javax.swing.plaf.basic.BasicTreeUI;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.awt.Graphics2D;

public class Player extends Entity{
    public GamePanel gp;
    public KeyInput Control;
    public final int screenX;
    public final int screenY;
    public Player(GamePanel gp, KeyInput Control)
    {
        this.gp=gp;
        this.Control=Control;

        screenX=gp.screenWidth/2 - (gp.tileSize/2);
        screenY=gp.screenHeight/2 - (gp.tileSize/2);

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
    }
    public void GetPlayerImage()
    {
        try
        {
        up1 = ImageIO.read(getClass().getResourceAsStream("/player/boy_up_1.png"));
        up2 = ImageIO.read(getClass().getResourceAsStream("/player/boy_up_2.png"));
        down1 = ImageIO.read(getClass().getResourceAsStream("/player/boy_down_1.png"));
        down2 = ImageIO.read(getClass().getResourceAsStream("/player/boy_down_2.png"));
        left1 = ImageIO.read(getClass().getResourceAsStream("/player/boy_left_1.png"));
        left2 = ImageIO.read(getClass().getResourceAsStream("/player/boy_left_2.png"));
        right1 = ImageIO.read(getClass().getResourceAsStream("/player/boy_right_1.png"));
        right2 = ImageIO.read(getClass().getResourceAsStream("/player/boy_right_2.png"));
        }
        catch(IOException e)
        {
            e.printStackTrace();
        }
    }
    public void update() //Cap nhat su duy chuyen cua Player
    {
        if(Control.upPressed== true|| Control.downPressed == true||
                Control.rightPressed==true|| Control.leftPressed==true)
        {
        if(Control.upPressed==true)
        {
            //worldY -= speed;
            worldY -= speed;
            direction = "up";
        }
        else if(Control.downPressed==true)
        {
            //worldY += speed;
            worldY += speed;
            direction = "down";

        }
        else if(Control.leftPressed==true)
        {
            //worldX -= speed;
            worldX -= speed;
            direction = "left";

        }
        else if(Control.rightPressed==true)
        {
            //worldX += speed;
            worldX += speed;
            direction = "right";
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
    }}

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
