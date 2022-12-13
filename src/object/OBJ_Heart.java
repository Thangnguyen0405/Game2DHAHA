package object;

import main.GamePanel;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class OBJ_Heart extends SuperObject {
    GamePanel gp;
    public OBJ_Heart(GamePanel gp) {

        this.gp = gp;

        name = "Heart";
        try {
            image = ImageIO.read(getClass().getResourceAsStream("/ObjectImage/heart_full.png"));
            image2 = ImageIO.read(getClass().getResourceAsStream("/ObjectImage/heart_half.png"));
            image3 = ImageIO.read(getClass().getResourceAsStream("/ObjectImage/heart_blank.png"));
            image = uTool.scaleImage(image, gp.tileSize, gp.tileSize);
            image2 = uTool.scaleImage(image2, gp.tileSize, gp.tileSize);
            image3 = uTool.scaleImage(image3, gp.tileSize, gp.tileSize);
        }catch(IOException e) {
            e.printStackTrace();
        }
        solidArea.x = 5;
    }
}
