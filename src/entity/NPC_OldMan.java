package entity;

import main.GamePanel;
import main.UtilityTool;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.FileInputStream;
import java.io.IOException;

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
}
