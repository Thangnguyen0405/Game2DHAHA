package object;

import main.GamePanel;

import javax.imageio.ImageIO;
import java.io.IOException;

public class OBJ_Shield_Wood extends SuperObject {
    GamePanel gp;
    public OBJ_Shield_Wood(GamePanel gp){
        this.gp = gp;
        name = "Wood Shield";
        try
        {
            image = ImageIO.read(getClass().getResourceAsStream("/ObjectImage/shield_wood.png"));
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
        defenseValue = 1;
    }
}
