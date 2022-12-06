package main;

import object.OBJ_Key;

import java.awt.*;
import java.awt.image.BufferedImage;

public class UI
{
    GamePanel gp;
    Font arial_40;

    BufferedImage keyImage;
    public boolean messageOn= false;
    public String message = " ";
    int messageCounter = 0;
    public boolean gameFinished = false;
    public UI(GamePanel gp)
    {
        this.gp = gp;

        arial_40 = new Font("Arial",Font.PLAIN,40);
        OBJ_Key key = new OBJ_Key();
        keyImage = key.image;
    }
    public void showMessage(String text)
    {
        message = text;
        messageOn = true;

    }
    public void draw(Graphics2D t2) {
        if (gameFinished)
        {
            String text;
            int textLength;
            int x;
            int y;
            t2.setFont(arial_40);
            t2.setColor(Color.white);
            x = gp.screenWidth/2;
            y = gp.screenHeight/2;
        }
        else
        {
            t2.setFont(arial_40);
            t2.setColor(Color.white);
            t2.drawImage(keyImage, gp.tileSize / 2, gp.tileSize / 2, gp.tileSize, gp.tileSize, null);
            t2.drawString("x" + gp.playerT.hasKey, 74, 65);

            if (messageOn) {
                t2.setFont(t2.getFont().deriveFont(30F));
                t2.drawString(message, gp.tileSize / 2, gp.tileSize * 5);

                messageCounter++;
                if (messageCounter > 50) {
                    messageCounter = 0;
                    messageOn = false;
                }
            }
        }
    }
}
