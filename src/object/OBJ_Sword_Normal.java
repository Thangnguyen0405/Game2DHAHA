package object;

import main.GamePanel;

import javax.imageio.ImageIO;
import java.io.IOException;

public class OBJ_Sword_Normal extends SuperObject {
    GamePanel gp;

    public OBJ_Sword_Normal(GamePanel gp){
        this.gp = gp;
        name = "Normal Sword";
        try
        {
            image = ImageIO.read(getClass().getResourceAsStream("/ObjectImage/sword_normal.png"));
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
        attackValue = 4;
    }

}
