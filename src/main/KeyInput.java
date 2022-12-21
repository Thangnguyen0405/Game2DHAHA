package main;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyInput implements KeyListener
{
    public boolean enterPressed;
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
        if(gp.gameState==gp.playState) {
            playState(code);
        }
        else if(gp.gameState == gp.pauseState) {
            pauseState(code);
        }
        else if(gp.gameState == gp.dialogueState) {
            dialogueState(code);
        }
        else if(gp.gameState == gp.TileState) {
            titleState(code);
        }
        else if (gp.gameState == gp.characterState){
            characterState(code);
        }
    }
    public void titleState(int code){
        if(code == KeyEvent.VK_SPACE)
        {
            gp.gameState = gp.playState;
        }
    }
    public void playState(int code){
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
            gp.gameState = gp.pauseState;
        }
        if(code == KeyEvent.VK_C){
            gp.gameState = gp.characterState;
        }
        if (code == KeyEvent.VK_ENTER){
            enterPressed = true;
        }
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
    public void pauseState(int code){
        if (code == KeyEvent.VK_P)
        {
            gp.gameState = gp.playState;
        }
    }
    public void dialogueState(int code){
        if (code == KeyEvent.VK_ENTER)
        {
            gp.gameState= gp.playState;
        }
    }
    public void characterState(int code){
        if(code == KeyEvent.VK_C){
            gp.gameState = gp.playState;
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
