package main;

import object.OBJ_Heart;
import object.OBJ_Key;
import object.SuperObject;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.text.DecimalFormat;

public class UI
{
    GamePanel gp;
    Graphics2D t2;
    BufferedImage heart_full, heart_half, heart_blank;
    Font arial_40,arial_80B, JokermanRegular;
    Font Edwardian_20;
    BufferedImage keyImage;
    public boolean messageOn= false;
    public String message = " ";
    int messageCounter = 0;
    double playtime = 0;
    DecimalFormat dFormat = new DecimalFormat("#0.0");
    public boolean gameFinished = false;
    public String currentDialogue = "";
    public int slotCol = 0;
    public int slotRow = 0;
    public int commandNum = 0;

    public UI(GamePanel gp)
    {
        this.gp = gp;
        arial_40 = new Font("Elephant",Font.PLAIN,20);
        arial_80B = new Font("Arial",Font.BOLD,80);
        JokermanRegular= new Font("Jokerman", Font.BOLD, 120);
        OBJ_Key key = new OBJ_Key();
        keyImage = key.image;

        // CREATE HUB OBJECT
        SuperObject heart = new OBJ_Heart(gp);
        heart_full = heart.image;
        heart_half = heart.image2;
        heart_blank = heart.image3;
    }
    public void showMessage(String text)
    {
        message = text;
        messageOn = true;

    }
    public void draw(Graphics2D t2)
    {
        this.t2=t2;
        t2.setFont(arial_80B);
        t2.setColor(Color.white);
        //TIlE STATE
        if(gp.gameState==gp.TileState)
        {
            drawTileScreen();
        }
        // PLAY STATE
        if(gp.gameState == gp.playState)
        {
            drawPlayerLife();
            t2.setFont(arial_40);
            t2.setColor(Color.white);
            t2.drawImage(keyImage, gp.tileSize , gp.tileSize*2 , gp.tileSize , gp.tileSize, null);
            t2.drawString("x" + gp.playerT.hasKey, 85, 130);

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
        // PAUSE STATE
        if(gp.gameState == gp.pauseState)
        {
            DrawPauseScreen();
            drawPlayerLife();
        }
        // DIALOGUE STATE
        if(gp.gameState == gp.dialogueState) {
            drawDialogueScreen();
            drawPlayerLife();
        }
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

        //CHARACTER STATE
        if(gp.gameState == gp.characterState){
            drawCharacterScreen();
            drawInventory();
        }

        //GAMEOVER STATE
        if(gp.gameState == gp.gameOverState){
            drawGameOverScreen();
        }
    }
    public void drawGameOverScreen(){

        t2.setColor(new Color(0,0,0,155));
        t2.fillRect(0,0,gp.screenWidth, gp.screenHeight);

        int x;
        int y;
        String text;
        t2.setFont(t2.getFont().deriveFont(Font.BOLD, 110f));

        text = "Game Over!";
        t2.setColor(Color.black);
        x = getXForCenteredText(text);
        y = gp.tileSize*4;
        t2.drawString(text,x,y);

        //Main
        t2.setColor(Color.white);
        t2.drawString(text, x-4, y-4);

        //Retry
        t2.setFont(t2.getFont().deriveFont(50f));
        text = "Retry";
        x = getXForCenteredText(text);
        y += gp.tileSize*4;
        t2.drawString(text,x,y);
        if(commandNum == 0) {
            t2.drawString(">", x-40, y);
        }

        //Back to the title screen
        text = "Quit";
        x = getXForCenteredText(text);
        y += 55;
        t2.drawString(text,x,y);
        if(commandNum == 1){
            t2.drawString(">", x-40, y);
        }
    }
    public void drawPlayerLife(){

        //gp.playerT.life = 6;

        int x = gp.tileSize/2;
        int y = gp.tileSize/2;
        int i = 0;
        // DRAW BLANK HEART
        while(i < gp.playerT.MAXlife/2) {
            t2.drawImage(heart_blank, x, y, null);
            i++;
            x += gp.tileSize;
        }

        x = gp.tileSize/2;
        y = gp.tileSize/2;
        i = 0;

        // DRAW CURRENT LIFE
        while(i < gp.playerT.life) {
            t2.drawImage(heart_half,x,y,null);
            i++;
            if(i < gp.playerT.life){
                t2.drawImage(heart_full, x, y,null);
            }
            i++;
            x += gp.tileSize;
        }

    }
    public void DrawPauseScreen()
    {
        t2.setFont(t2.getFont().deriveFont(Font.PLAIN,80F));
        String text = "PAUSED";
        int x = getXForCenteredText(text) ;
        int length = (int)t2.getFontMetrics().getStringBounds(text,t2).getWidth();
        x=gp.screenWidth/2 - length/2;
        int y = gp.screenHeight/2;
        t2.drawString(text,x,y);
    }
    public void drawTileScreen()
    {
        t2.setFont(JokermanRegular);
        String text = "Pirate King";
        int x = getXForCenteredText(text);
        int y = gp.tileSize*7;
        t2.setColor(Color.white);
        t2.drawString(text,x,y);

        t2.setFont(arial_40);
        String text2 = "Find Somthings Unlock SomeThing";
        int x1 = getXForCenteredText(text2);
        int y1 = gp.tileSize*8;
        t2.setColor(Color.white);
        t2.drawString(text2,x1,y1);

        t2.setFont(arial_40);
        String text3 = "Press SpaceBar To Start";
        int x2 = getXForCenteredText(text2);
        int y2 = gp.tileSize*11;
        t2.setColor(Color.white);
        t2.drawString(text3,x2,y2);

        t2.setFont(arial_40);
        String text4 = "Exit";
        int x3 = getXForCenteredText(text2);
        int y3 = gp.tileSize*13;
        t2.setColor(Color.white);
        t2.drawString(text4,x3,y3);
    }
    public void drawDialogueScreen() {
        //WINDOW
        int x = gp.tileSize * 2;
        int y = gp.tileSize / 2;
        int width = gp.screenWidth - (gp.tileSize * 4);
        int height =  gp.tileSize * 5;
        drawSubWindow(x,y,width,height);
        t2.setFont(arial_40);
        x += gp.tileSize;
        y += gp.tileSize;
        t2.drawString(currentDialogue,x,y);
    }
    public void drawCharacterScreen(){

        //CREATE A FRAME
        final int frameX = gp.tileSize;
        final int frameY = gp.tileSize;
        final int frameWidth = gp.tileSize*5;
        final int frameHeight = gp.tileSize*9;
        drawSubWindow(frameX, frameY, frameWidth, frameHeight);

        //TEXT
        t2.setColor(Color.white);
        t2.setFont(t2.getFont().deriveFont(25F));

        int testX = frameX + 20;
        int testY = frameY + gp.tileSize;
        final int lineHeight = 35;

        //NAME
        t2.drawString("Level", testX, testY);
        testY += lineHeight;
        t2.drawString("Life", testX, testY);
        testY += lineHeight;
        t2.drawString("Strength", testX, testY);
        testY += lineHeight;
        t2.drawString("Dexterity", testX, testY);
        testY += lineHeight;
        t2.drawString("Attack", testX, testY);
        testY += lineHeight;
        t2.drawString("Defense", testX, testY);
        testY += lineHeight;
        t2.drawString("Exp", testX, testY);
        testY += lineHeight;
        t2.drawString("Next Level", testX, testY);
        testY += lineHeight;
        t2.drawString("Coin", testX, testY);
        testY += lineHeight;
        t2.drawString("Weapon", testX, testY);
        testY += lineHeight;
        t2.drawString("Sheild", testX, testY);
        testY += lineHeight;

        //VALUES
        int tailX = (frameX + frameWidth) - 30;
        //Reset textY
        testY = frameY + gp.tileSize;
        String value;

        value = String.valueOf(gp.playerT.level);
        testX = getXForAlignToRightText(value, tailX);
        t2.drawString(value, testX, testY);
        testY += lineHeight;

        value = String.valueOf(gp.playerT.life + "/" + gp.playerT.MAXlife);
        testX = getXForAlignToRightText(value, tailX);
        t2.drawString(value, testX, testY);
        testY += lineHeight;

        value = String.valueOf(gp.playerT.strength);
        testX = getXForAlignToRightText(value, tailX);
        t2.drawString(value, testX, testY);
        testY += lineHeight;

        value = String.valueOf(gp.playerT.dexterity);
        testX = getXForAlignToRightText(value, tailX);
        t2.drawString(value, testX, testY);
        testY += lineHeight;

        value = String.valueOf(gp.playerT.attack);
        testX = getXForAlignToRightText(value, tailX);
        t2.drawString(value, testX, testY);
        testY += lineHeight;

        value = String.valueOf(gp.playerT.defense);
        testX = getXForAlignToRightText(value, tailX);
        t2.drawString(value, testX, testY);
        testY += lineHeight;

        value = String.valueOf(gp.playerT.exp);
        testX = getXForAlignToRightText(value, tailX);
        t2.drawString(value, testX, testY);
        testY += lineHeight;

        value = String.valueOf(gp.playerT.nextLevelExp);
        testX = getXForAlignToRightText(value, tailX);
        t2.drawString(value, testX, testY);
        testY += lineHeight;

        value = String.valueOf(gp.playerT.coin);
        testX = getXForAlignToRightText(value, tailX);
        t2.drawString(value, testX, testY);

        t2.drawImage(gp.playerT.currentWeapon.image, tailX - gp.tileSize + 25, testY + 20, null);
        testY += gp.tileSize +10;

        t2.drawImage(gp.playerT.currentShield.image, tailX - gp.tileSize + 25, testY, null);


    }
    public void drawInventory(){

        //FRAME
        int frameX = gp.tileSize * 20;
        int frameY = gp.tileSize ;
        int frameWidth = gp.tileSize * 6;
        int frameHeight = gp.tileSize * 5;
        drawSubWindow(frameX,frameY,frameWidth,frameHeight);

        //SLOT
        final int slotXstart = frameX + 20;
        final int slotYstart = frameY + 20;
        int slotX = slotXstart;
        int slotY = slotYstart;


        //CURSOR
        int cursorX = slotXstart + (gp.tileSize * slotCol);
        int cursorY = slotYstart + (gp.tileSize * slotRow);
        int cursorWidth = gp.tileSize;
        int cursorHeight = gp.tileSize;

        //DRAW CURSOR
        t2.setColor(Color.white);
        t2.setStroke(new BasicStroke(3));
        t2.drawRoundRect(cursorX,cursorY,cursorWidth,cursorHeight,10,10);

    }
    public void drawSubWindow(int x, int y, int width, int height){

        Color c = new Color(0,0,0,210);
        t2.setColor(c);
        t2.fillRoundRect(x, y, width, height, 35, 35);

        c = new Color(255,255,255);
        t2.setColor(c);
        t2.setStroke(new BasicStroke(5));
        t2.drawRoundRect(x+5, y+5, width-10, height-10,25,25);
    }
    public int getXForCenteredText(String text)
    {
        int length =(int)t2.getFontMetrics().getStringBounds(text,t2).getWidth();
        int x = gp.screenWidth/2 - length/2;
        return x;
    }

    public int getXForAlignToRightText(String text, int tailX)
    {
        int length =(int)t2.getFontMetrics().getStringBounds(text,t2).getWidth();
        int x = tailX - length;
        return x;
    }
}
