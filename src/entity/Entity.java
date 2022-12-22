package entity;

import main.GamePanel;
import main.UtilityTool;
import object.OBJ_Shield_Wood;
import object.OBJ_Sword_Normal;

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
    public Rectangle attackArea = new Rectangle(0,0,0,0);
    public int solidAreaDefaultX, solidAreaDefaultY;
    String dialogues[] = new String[20];

    //COUNTER
    public int actionLockCounter=0;
    public int invincibleCounter = 0;
    public int spriteCounter=0;
    int dyingCounter = 0;
    int hpBarCounter = 0;

    //STATE
    public int worldX,worldY;
    public String direction;
    int DialogIndex =0;
    public int spriteNum=1;
    public boolean collisionOn = false;
    public boolean invincible = false;
    boolean attacking = false;
    public boolean alive = true;
    public boolean dying = false;
    boolean hpBarOn = false;

    //CHARACTER ATTRIBUTES
    public int type; //0=player, 1=npc, 2=monster
    public int speed;
    public int MAXlife;
    public int life;
    public int level;
    public int strength;
    public int dexterity;
    public int attack;
    public int defense;
    public int exp;
    public int nextLevelExp;
    public int coin;
    public OBJ_Sword_Normal currentWeapon;
    public OBJ_Shield_Wood currentShield;


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
    public void damageReaction(){ }
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
                //We can give damage
                gp.playSE(6);
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
        if (spriteCounter >12)
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
        if(invincible == true){
            invincibleCounter++;
            if(invincibleCounter > 40){
                invincible = false;
                invincibleCounter = 0;
            }
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
                if (spriteNum == 1) { image = up1; }
                if (spriteNum == 2) { image = up2;}
            }
            case "down" -> {
                if (spriteNum == 1) { image = down1; }
                if (spriteNum == 2) { image = down2;}
            }
            case "left" -> {
                if (spriteNum == 1) { image = left1; }
                if (spriteNum == 2) { image = left2; }
            }
            case "right" -> {
                if (spriteNum == 1) { image = right1; }
                if (spriteNum == 2) { image = right2; }
            }
        }

        //Monster HP bar
        if(type == 2 && hpBarOn == true){

            double oneScale = (double)gp.tileSize/MAXlife;
            double hpBarValue = oneScale*life;

            t2.setColor(new Color(35, 35 ,35));
            t2.fillRect(screenX-1, screenY-16, gp.tileSize+2, 12);

            t2.setColor(new Color(255,0,30));
            t2.fillRect(screenX, screenY - 15, (int)hpBarValue, 10);

            hpBarCounter++;

            if(hpBarCounter > 600){
                hpBarCounter = 0;
                hpBarOn = false;
            }
        }

        if(invincible == true){
            hpBarOn = true;
            hpBarCounter = 0;
            changeAlpha(t2,0.4F);
            t2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.4f));
        }
        if(dying == true){
            dyingAnimation(t2);
        }

        t2.drawImage(image,screenX,screenY,gp.tileSize,gp.tileSize,null);

        changeAlpha(t2,1F);
    }
    public void dyingAnimation(Graphics2D t2){

        dyingCounter++;
        int i = 5;

        if(dyingCounter <= i){ changeAlpha(t2, 0f); }
        if(dyingCounter > i && dyingCounter <= i*2){ changeAlpha(t2, 1f); }
        if(dyingCounter > i*2 && dyingCounter <= i*3){ changeAlpha(t2, 0f); }
        if(dyingCounter > i*3 && dyingCounter <= i*4){ changeAlpha(t2, 1f); }
        if(dyingCounter > i*4 && dyingCounter <= i*5){ changeAlpha(t2, 0f); }
        if(dyingCounter > i*5 && dyingCounter <= i*6){ changeAlpha(t2, 1f); }
        if(dyingCounter > i*6 && dyingCounter <= i*7){ changeAlpha(t2, 0f); }
        if(dyingCounter > i*7 && dyingCounter <= i*8){ changeAlpha(t2, 1f); }
        if(dyingCounter > i*8){
            dying = false;
            alive = false;
        }
    }
    public void changeAlpha(Graphics2D t2, float alphaValue) {
        t2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, alphaValue));

    }
}


