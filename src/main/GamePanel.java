package main;
import entity.Entity;
import entity.Player;
import object.SuperObject;
import tile.TileManager;

import javax.swing.JPanel;
import java.awt.*;

public class GamePanel extends JPanel implements Runnable
{
    int FPS = 60;
    //Set Man Hinh
    final int originalTileSize =16;
    final int scale=3;
    public final int tileSize = originalTileSize * scale;
    public final int maxScreenCol =27;
    public final int maxScreenRow = 15;
    //
    public final int screenWidth = tileSize * maxScreenCol;//1920pixels
    public final int screenHeight = tileSize * maxScreenRow;//1080pixels
    //MAP SETTINGS
    public final int maxWorldCol = 50;
    public final int maxWorldRow = 50;
    public final int worldWidth = tileSize* maxWorldCol;
    public final int worldHeight = tileSize * maxWorldRow;
    //

    public KeyInput Control = new KeyInput(this);//KeyInput.java
    //
    TileManager tileM= new TileManager(this);
    Sound sound = new Sound();
    Thread gameThread;

    public CollisionChecker cChecker = new CollisionChecker(this);
    public AssetSetter aSetter = new AssetSetter(this);
    public UI ui = new UI(this);
    public EventHandler eHandler = new EventHandler(this);
    public Player playerT = new Player(this, Control);
    public SuperObject obj[] = new SuperObject[100];
    public Entity npc[] = new Entity[10];
    public Entity Monster[] = new Entity[20];
    public int gameState;
    public final int playState = 1;
    public final int pauseState =2;
    public final int dialogueState = 3;
    public final int TileState = 0;
    
    public GamePanel()
    {
        this.setPreferredSize(new Dimension(screenWidth,screenHeight));
        //Khoi dong game theo kich thuoc da quy dinh
        this.setBackground(Color.black);
        this.setDoubleBuffered(true);
        this.addKeyListener(Control);//Keyinput.java
        //Them phan dieu khien bang ban phim vao game
        this.setFocusable(true);//KeyInput.java
        //Lam cho cai GamePanel "Focus" vao ban phim

    }
    public void setUpGame()
    {
        aSetter.setObject();
        aSetter.setNPC();
        playMusic(0);
        gameState = TileState;
    }
    public void startGameThread()
    {
        gameThread = new Thread(this);
        gameThread.start();
    }
    @Override
//    public void run() //Vong lap game
//    {
//        double drawInterval = 1000000000/FPS;
//        double nextDrawTime = System.nanoTime() + drawInterval;
//        long timer=0;
//        double drawCount = 0;
//        while(gameThread != null)
//        {
//            long currentTime = System.nanoTime();
//            System.out.println("Current Time" + currentTime);
//            System.out.println("The game loop is running");
//            update();
//            repaint();
//            drawCount++;
//            try {
//                double remainingTime = nextDrawTime - System.nanoTime();
//                remainingTime = remainingTime/1000000;
//                Thread.sleep((long) remainingTime);
//                timer+=remainingTime/1000000;
//                nextDrawTime += drawInterval;
//                if(remainingTime<0)
//                {
//                    remainingTime = 0;
//                }
//                //
//                if(timer >= 1000000000)
//                {
//                    System.out.println("FPS"+drawCount);
//                }
//
//                }
//            catch (InterruptedException e)
//                {
//                e.printStackTrace();
//                }
//        }
//    }
    public void run()
    {
        double drawInterval = 1000000000/FPS;
        double delta=0;
        long lastTime = System.nanoTime();
        long currentTime;
        long timer=0;
        int drawCount=0;
        while (gameThread !=null)
        {
            currentTime = System.nanoTime();
            delta +=(currentTime - lastTime)/drawInterval;
            timer +=(currentTime - lastTime);
            lastTime= currentTime;
            if(delta >=0)
            {
                update();
                repaint();
                delta --;
                drawCount++;
            }
            if(timer >=1000000000)
            {
               System.out.println("FPS"+ drawCount);
               drawCount = 0;
               timer =0;
            }
        }
    }


    public void update() //Hien thi su duy chuyen cua Player
        {

            if(gameState == playState)
            {
               playerT.update();
               for(int i =0;i <npc.length;i++)
               {
                   if(npc[i]!=null)
                   {
                       npc[i].update();
                   }
               }
            }
            else if(gameState == pauseState)
            {
                //nothing
            }
        }

    public void paintComponent(Graphics t)//cap nhat trang thai cua player tren man hinh
        {
        super.paintComponent(t);
        Graphics2D t2 = (Graphics2D)t;

        //
            long drawStart = 0 ;

            if(Control.checkDrawTime == true)
            {
                drawStart = System.nanoTime();
            }
            if(gameState==TileState)
            {
                ui.draw(t2);
            }
            else {
                //
                tileM.draw(t2);
                for (int i = 0; i < obj.length; i++) {
                    if (obj[i] != null) {
                        obj[i].draw(t2, this);
                    }
                }
                for (int i = 0; i < npc.length; i++) {
                    if (npc[i] != null) {
                        npc[i].draw(t2);
                    }
                }

        //
        playerT.draw(t2);
        ui.draw(t2);
            }
            //DEBUG
            if(Control.checkDrawTime ==true )
            {
                //debug
                long drawEnd = System.nanoTime();
                long passed = drawEnd - drawStart;
                t2.setColor(Color.white);
                t2.drawString("Draw Time" + passed, 10, 4000);
                System.out.println("Draw Time" + passed);
                t2.dispose();
            }

        }
    public void playMusic(int i)
    {
        sound.setFile(i);
        sound.play();
        sound.loop();
    }
    public void stopMusic()
    {
        sound.stop();
    }
    public void playSE(int i )
    {
        sound.setFile(i);
        sound.play();
    }
}


