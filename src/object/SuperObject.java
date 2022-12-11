package object;

import main.GamePanel;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;

public class SuperObject
{
    public BufferedImage image;





    public Rectangle solidArea = new Rectangle(0,0,48,48);
    public int solidAreaDefaultX = 0;
    public int solidAreaDefaultY = 0;
    public String name;
    public boolean collision = false;
    public int worldX, worldY;

    public void draw(Graphics2D t2, GamePanel gamePanel) {
    }
}
