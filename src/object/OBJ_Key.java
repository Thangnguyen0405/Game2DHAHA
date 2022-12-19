package object;

import main.GamePanel;

import javax.imageio.ImageIO;
import java.io.IOException;

public class OBJ_Key extends SuperObject {
    GamePanel gp;

    public OBJ_Key() {
        name = "Key";
        try {
            image = ImageIO.read(getClass().getResourceAsStream("/ObjectImage/key.png"));

        }catch(IOException e) {
            e.printStackTrace();
        }
        solidArea.x = 5;
    }
}
