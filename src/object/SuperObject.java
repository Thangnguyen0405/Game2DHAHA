package object;

import main.GamePanel;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;

public class SuperObject
{
    public BufferedImage image;
    public String name;
    public boolean collision = false;
    public int worldX, worldY;
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
