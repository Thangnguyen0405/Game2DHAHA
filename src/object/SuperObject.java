package object;

import main.GamePanel;
import main.UtilityTool;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;

public class SuperObject
{
    public BufferedImage image, image2, image3;
    public String name;
    public boolean collision = false;
    public int worldX, worldY;
    public Rectangle solidArea = new Rectangle(0,0,48,48);

    public int solidAreaDefaultX = 0;
    public int solidAreaDefaultY = 0;

    UtilityTool uTool = new UtilityTool();
    public void draw(Graphics2D t2, GamePanel gp)
    {
        int screenX = worldX - gp.playerT.worldX + gp.playerT.screenX;
        int screenY = worldY - gp.playerT.worldY + gp.playerT.screenY;
        if (       worldX + gp.tileSize> gp.playerT.worldX - gp.playerT.screenX
                && worldX - gp.tileSize< gp.playerT.worldX+ gp.playerT.screenX
                && worldY + gp.tileSize> gp.playerT.worldY - gp.playerT.screenY
                && worldY - gp.tileSize< gp.playerT.worldY + gp.playerT.screenY)
        {
            t2.drawImage(image, screenX, screenY, gp.tileSize, gp.tileSize, null);
        }
    }
}
