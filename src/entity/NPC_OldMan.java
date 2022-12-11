package entity;

import main.GamePanel;
import main.UtilityTool;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Random;

public class NPC_OldMan extends Entity
{
    public NPC_OldMan(GamePanel gp)
    {
        super(gp);

        direction = "down";
        speed =1;
        GetNPCImage();

    }
    public void GetNPCImage()
    {
        up1     = setup("/NPC_OldMan/oldman_up_1");
        up2     = setup("/NPC_OldMan/oldman_up_2");
        down1   = setup("/NPC_OldMan/oldman_down_1");
        down2   = setup("/NPC_OldMan/oldman_down_2");
        left1   = setup("/NPC_OldMan/oldman_left_1");
        left2   = setup("/NPC_OldMan/oldman_left_2");
        right1  = setup("/NPC_OldMan/oldman_right_1");
        right2  = setup("/NPC_OldMan/oldman_right_2");
    }
    public void setDialogue(){
        dialogues[0] = "Hello, thang ngu";
    }
    public void setAction()
    {
        actionLockCounter ++;
        Random random = new Random();
        int i =random.nextInt(100)+1;
        if(actionLockCounter ==120)
        {
            if(i<25)
            {
                direction = "up";
            }
            if( i >25 && i <=50)
            {
                direction = "down";
            }
            if(i>50 && i <=75)
            {
                direction = "left";
            }
            else if (i >75 && i <=100)
            {
                direction = "right";
            }
            actionLockCounter=0;
        }
    }
    public void speak(){

        gp.ui.currentDialogue = dialogues[0];
    }
}
