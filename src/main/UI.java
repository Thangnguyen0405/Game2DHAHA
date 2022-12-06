package main;

import object.OBJ_Key;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.text.DecimalFormat;

public class UI
{
    GamePanel gp;
    Font arial_40,arial_80B;

    BufferedImage keyImage;
    public boolean messageOn= false;
    public String message = " ";
    int messageCounter = 0;
    double playtime = 0;
    DecimalFormat dFormat = new DecimalFormat("#0.00");
    public boolean gameFinished = false;
    public UI(GamePanel gp)
    {
        this.gp = gp;

        arial_40 = new Font("Arial",Font.PLAIN,40);
        arial_80B = new Font("Arial",Font.BOLD,80);
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
            //Top Ending Message
            t2.setFont(arial_40);
            t2.setColor(Color.white);
            text= "You Found The Treasure";
            textLength = (int)t2.getFontMetrics().getStringBounds(text, t2).getWidth();
            x = gp.screenWidth/2 - textLength/2;
            y = gp.screenHeight/2- (gp.tileSize*3);
            t2.drawString(text, x ,y);
            //Bottom Ending Message
            t2.setFont(arial_80B);
            t2.setColor(Color.white);
            text= "Congratulations!" ;
            textLength = (int)t2.getFontMetrics().getStringBounds(text, t2).getWidth();
            x = gp.screenWidth/2 - textLength/2;
            y = gp.screenHeight/2 ;
            t2.drawString(text, x ,y);

            //Display Played Time
            t2.setFont(arial_40);
            t2.setColor(Color.white);
            text= "Time Played: "+ dFormat.format(playtime);
            textLength = (int)t2.getFontMetrics().getStringBounds(text, t2).getWidth();
            x = gp.screenWidth/2 - textLength/2;
            y = gp.screenHeight/2 + (gp.tileSize*2);
            t2.drawString(text, x ,y);
            //Stop The Game
            gp.gameThread= null;
        }
        else
        {
            t2.setFont(arial_40);
            t2.setColor(Color.lightGray);
            t2.drawImage(keyImage, gp.tileSize / 2, gp.tileSize / 2, gp.tileSize, gp.tileSize, null);
            t2.drawString("x" + gp.playerT.hasKey, 74, 65);

            playtime +=(double)1/60;
            t2.drawString("Played Time: "+dFormat.format(playtime),gp.tileSize*19,50);

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
