package main;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyInput implements KeyListener
{
    GamePanel gp;
    public boolean upPressed,downPressed,leftPressed,rightPressed;
    boolean checkDrawTime = false;
    public KeyInput(GamePanel gp)
    {
        this.gp=gp;
    }
    @Override
    public void keyTyped(KeyEvent e)
    {

    }
    @Override
    public void keyPressed(KeyEvent e)//Bam phim
    {
        int code = e.getKeyCode();
        if (code == KeyEvent.VK_UP|| code ==KeyEvent.VK_W)
        {
            upPressed = true;
        }
        else if (code == KeyEvent.VK_DOWN || code ==KeyEvent.VK_S)
        {
            downPressed = true;
        }
        else if (code == KeyEvent.VK_LEFT || code ==KeyEvent.VK_A)
        {
            leftPressed = true;
        }
        else if (code == KeyEvent.VK_RIGHT || code ==KeyEvent.VK_D)
        {
            rightPressed = true;
        }
        if (code == KeyEvent.VK_P)
        {
            if(gp.gameState == gp.playState)
            {
                gp.gameState = gp.pauseState;
            }
            else if(gp.gameState == gp.pauseState)
            {
                gp.gameState = gp.playState;
            }
        }
        //DEBUG
        if (code == KeyEvent.VK_T)
        {
            if(checkDrawTime == false)
            {
                checkDrawTime = true;
            }
            else if(checkDrawTime == true)
            {
                checkDrawTime =false;
            }
        }
    }
    @Override
    public void keyReleased(KeyEvent e)//nha phim
    {
        int code = e.getKeyCode();
        if (code == KeyEvent.VK_UP|| code ==KeyEvent.VK_W) {
            upPressed = false;
        }
        else if (code == KeyEvent.VK_DOWN || code ==KeyEvent.VK_S) {
            downPressed = false;
        }
        else if (code == KeyEvent.VK_LEFT || code ==KeyEvent.VK_A) {
            leftPressed = false;
        }
        else if (code == KeyEvent.VK_RIGHT || code ==KeyEvent.VK_D) {
            rightPressed = false;
        }
    }
}
