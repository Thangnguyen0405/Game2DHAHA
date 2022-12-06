package main;

import java.awt.*;
import java.awt.image.BufferedImage;


public class UtilityTool
{

    public BufferedImage scaleImage(BufferedImage original, int width, int height)
    {
        BufferedImage scaleImage = new BufferedImage(width, height, original.getType());
        Graphics2D t2 = scaleImage.createGraphics();
        t2.drawImage(original, 0, 0, width, height, null);

        return scaleImage;
    }
}