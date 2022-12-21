package entity;

import main.GamePanel;
import main.UtilityTool;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class Entity
{
    GamePanel gp;
    public BufferedImage up1,up2,down1,down2,right1,right2,left1,left2;
    public BufferedImage attackUp1, attackUp2, attackDown1, attackDown2, attackLeft1, attackLeft2, attackRight1, attackRight2;
    public Rectangle solidArea = new Rectangle(0,0,48,48);
    public int solidAreaDefaultX, solidAreaDefaultY;
    String dialogues[] = new String[20];

    //COUNTER
    public int actionLockCounter=0;
    public int invincibleCounter = 0;
    public int spriteCounter=0;

    //STATE
    public int worldX,worldY;
    public String direction;
    int DialogIndex =0;
    public int spriteNum=1;
    public boolean collisionOn = false;
    public boolean invincible = false;
    boolean attacking = false;

    //CHARACTER ATTRIBUTES
    public int type; //0=player, 1=npc, 2=monster
    public int speed;
    public int MAXlife;
    public int life;

    public Entity(GamePanel gp)
    {
        this.gp=gp;
    }
     public BufferedImage setup(String imageName, int width, int height)
    {
        UtilityTool uTool = new UtilityTool();
        BufferedImage image = null;
        try
        {
            image = ImageIO.read(getClass().getResourceAsStream( imageName +".png"));
            image = uTool.scaleImage(image, width,height);
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
        return image;
    }
    public void setAction() { }
    public void speak() { }
    public void update()
    {
        setAction();
        collisionOn = false;
        gp.cChecker.checkTile(this);
        gp.cChecker.checkObject(this,false);
        gp.cChecker.checkEntity(this,gp.npc);
        gp.cChecker.checkEntity(this,gp.monster);
        boolean contactPlayer = gp.cChecker.checkPlayer(this);

        if(this.type == 2 && contactPlayer == true){
            if(gp.playerT.invincible == false){
                gp.playerT.life -= 1;
                gp.playerT.invincible = true;
            }
        }

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
    public void draw(Graphics2D t2)
    {
        BufferedImage image = null;
        int screenX = worldX - gp.playerT.worldX + gp.playerT.screenX;
        int screenY = worldY - gp.playerT.worldY + gp.playerT.screenY;
        if (       worldX + gp.tileSize> gp.playerT.worldX - gp.playerT.screenX
                && worldX - gp.tileSize< gp.playerT.worldX+ gp.playerT.screenX
                && worldY + gp.tileSize> gp.playerT.worldY - gp.playerT.screenY
                && worldY - gp.tileSize< gp.playerT.worldY + gp.playerT.screenY)
        {
            t2.drawImage(image, screenX, screenY, gp.tileSize, gp.tileSize, null);
        }
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

