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
        setDialogue();

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
    public void setDialogue()
    {
        dialogues[0] = "Hello, You Might Go For The Key";
        dialogues[1] = "Hello, The Doors Were Locked";
        dialogues[2] = "Hello, You Might Found Something In The Hidden House In The Forest ";
        dialogues[3] = "Danger Is Always Around The Thing You Are Looking For";
        dialogues[4] = "Good Luck";
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
    public void speak()
    {
        if(dialogues[DialogIndex]==null)
        {
            DialogIndex=0;
        }
        gp.ui.currentDialogue = dialogues[DialogIndex];
        DialogIndex++;
    }
}
