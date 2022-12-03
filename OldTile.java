package tile;
import main.GamePanel;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Objects;

public class OldTile
{
    GamePanel gp;
    Tile[] tile;
    int mapTileNum[][];

    public TileManager(GamePanel gp)
    {
        this.gp = gp;
        tile = new Tile[100];
        mapTileNum = new int[gp.maxScreenCol][gp.maxScreenRow];
        getTileImage();
        loadMap();
    }
    public void getTileImage()
    {
        try
        {
//            tile[0]=new Tile();
//        tile[0].image = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/tile/earth.png")));
//            tile[1]=new Tile();
//        tile[1].image = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/tile/floor01.pnd")));
//            tile[2]=new Tile();
//        tile[2].image = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/tile/grass00.png")));
//            tile[3]=new Tile();
//        tile[3].image = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/tile/grass01.png")));
//            tile[4]=new Tile();
//        tile[4].image = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/tile/hut.png")));
//            tile[5]=new Tile();
//        tile[5].image = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/tile/road00.png")));
//            tile[6]=new Tile();
//        tile[6].image = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/tile/road01.png")));
//            tile[7]=new Tile();
//        tile[7].image = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/tile/road02.png")));
//            tile[8]=new Tile();
//        tile[8].image = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/tile/road03.png")));
//            tile[9]=new Tile();
//        tile[9].image = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/tile/road04.png")));
//            tile[10]=new Tile();
//        tile[10].image = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/tile/road05.png")));
//            tile[11]=new Tile();
//        tile[11].image = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/tile/road06.png")));
//            tile[12]=new Tile();
//        tile[12].image = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/tile/road07.png")));
//            tile[13]=new Tile();
//        tile[13].image = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/tile/road08.png")));
//            tile[14]=new Tile();
//        tile[14].image = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/tile/road09.png")));
//            tile[15]=new Tile();
//        tile[15].image = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/tile/road10.png")));
//            tile[16]=new Tile();
//        tile[16].image = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/tile/road11.png")));
//            tile[17]=new Tile();
//        tile[17].image = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/tile/road12.png")));
//            tile[18]=new Tile();
//        tile[18].image = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/tile/road13.png")));
//            tile[19]=new Tile();
//        tile[19].image = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/tile/table01.png")));
//            tile[20]=new Tile();
//        tile[20].image = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/tile/tree.png")));
//            tile[21]=new Tile();
//        tile[21].image = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/tile/wall.png")));
            tile[22]=new Tile();
            tile[22].image = ImageIO.read(tile.requireNonNull(getClass().getResourceAsStream("/tiles/water00.png")));
            tile[23]=new Tile();
            tile[23].image = ImageIO.read(tile.requireNonNull(getClass().getResourceAsStream("/tiles/water01.png")));
//            tile[24]=new Tile();
//        tile[24].image = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/tile/water02.png")));
//            tile[25]=new Tile();
//        tile[25].image = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/tile/water03.png")));
//            tile[26]=new Tile();
//        tile[26].image = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/tile/water04.png")));
//            tile[27]=new Tile();
//        tile[27].image = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/tile/water05.png")));
//            tile[28]=new Tile();
//        tile[28].image = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/tile/water06.png")));
//            tile[29]=new Tile();
//        tile[29].image = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/tile/water07.png")));
//            tile[30]=new Tile();
//        tile[30].image = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/tile/water08.png")));
//            tile[31]=new Tile();
//        tile[31].image = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/tile/water09.png")));
//            tile[32]=new Tile();
//        tile[32].image = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/tile/water10.png")));
//            tile[33]=new Tile();
//        tile[33].image = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/tile/water11.png")));
//            tile[34]=new Tile();
//        tile[34].image = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/tile/water12.png")));
//            tile[35]=new Tile();
//        tile[35].image = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/tile/water13.png")));
        }
        catch (IOException e){
            e.printStackTrace();
        }
    }
    public void loadMap()
    {
        try
        {
            InputStream is = getClass().getResourceAsStream("/maps/map01.txt");
            assert is != null;
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(is));
            int col=0;
            int row=0;
            while(col < gp.maxScreenCol && row < gp.maxScreenRow)
            {
                String line = bufferedReader.readLine();
                while(col< gp.maxScreenRow)
                {
                    String numbers[] = line.split(" ");
                    int num = Integer.parseInt(numbers[col]);
                    mapTileNum[col][row]=num;
                    col++;
                }
                if(col == gp.maxScreenCol)
                {
                    col = 0;
                    row++;
                }
            }
            bufferedReader.close();
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }
    public void draw(Graphics2D t2)
    {
        int col = 0;
        int row = 0;
        int x = 0;
        int y = 0;
        while(col<gp.maxScreenCol && row < gp.maxScreenRow)
        {
            int tileNum = mapTileNum[col][row];
            t2.drawImage(tile[tileNum].image, x, y, gp.tileSize, gp.tileSize, null);

            col++;
            x += gp.tileSize;
            if(col == gp.maxScreenCol)
            {
                col=0;
                x=0;
                row++;
                y+= gp.tileSize;
            }
        }
    }
}
