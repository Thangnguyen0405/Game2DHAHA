package main;

import Monster.MON_Bat;
import entity.NPC_OldMan;
import object.OBJ_Boots;
import object.OBJ_Chest;
import object.OBJ_Door;
import object.OBJ_Key;

public class AssetSetter {
    GamePanel gp;

    public AssetSetter(GamePanel gp) {
        this.gp = gp;
    }

    public void setObject() {
        gp.obj[0] = new OBJ_Key();
        gp.obj[0].worldX = 9 * gp.tileSize;
        gp.obj[0].worldY = 33 * gp.tileSize;

        gp.obj[1] = new OBJ_Key();
        gp.obj[1].worldX = 10 * gp.tileSize;
        gp.obj[1].worldY = 18 * gp.tileSize;

        gp.obj[2] = new OBJ_Key();
        gp.obj[2].worldX = 37 * gp.tileSize;
        gp.obj[2].worldY = 10 * gp.tileSize;

//        gp.obj[3] = new OBJ_Chest();
//        gp.obj[3].worldX = 10 * gp.tileSize;
//        gp.obj[3].worldY = 8 * gp.tileSize;

        gp.obj[4] = new OBJ_Chest();
        gp.obj[4].worldX = 10 * gp.tileSize;
        gp.obj[4].worldY = 9 * gp.tileSize;

//

        gp.obj[6] = new OBJ_Door();
        gp.obj[6].worldX = 10 * gp.tileSize;
        gp.obj[6].worldY = 12 * gp.tileSize;

        gp.obj[7] = new OBJ_Door();
        gp.obj[7].worldX = 8 * gp.tileSize;
        gp.obj[7].worldY = 29 * gp.tileSize;

        gp.obj[8] = new OBJ_Door();
        gp.obj[8].worldX = 12 * gp.tileSize;
        gp.obj[8].worldY = 24 * gp.tileSize;

        gp.obj[9] = new OBJ_Boots();
        gp.obj[9].worldX = 23 * gp.tileSize;
        gp.obj[9].worldY = 40 * gp.tileSize;

    }

    public void setNPC() {
//        gp.npc[0] = new NPC_OldMan(gp);
//        gp.npc[0].worldX = gp.tileSize *12;
//        gp.npc[0].worldY = gp.tileSize *20;

        gp.npc[1] = new NPC_OldMan(gp);
        gp.npc[1].worldX = gp.tileSize *10;
        gp.npc[1].worldY = gp.tileSize *32;

//        gp.npc[2] = new NPC_OldMan(gp);
//        gp.npc[2].worldX = gp.tileSize *12;
//        gp.npc[2].worldY = gp.tileSize *23;

        gp.npc[3] = new NPC_OldMan(gp);
        gp.npc[3].worldX = gp.tileSize *37;
        gp.npc[3].worldY = gp.tileSize *10;
    }

    public void setMonster(){
        int i = 0;


        gp.monster[i] = new MON_Bat(gp);
        gp.monster[i].worldX = gp.tileSize*11;
        gp.monster[i].worldY = gp.tileSize*10;
        i++;

        gp.monster[i] = new MON_Bat(gp);
        gp.monster[i].worldX = gp.tileSize*11;
        gp.monster[i].worldY = gp.tileSize*32;
        i++;

        gp.monster[i] = new MON_Bat(gp);
        gp.monster[i].worldX = gp.tileSize*12;
        gp.monster[i].worldY = gp.tileSize*20;
        i++;

        gp.monster[i] = new MON_Bat(gp);
        gp.monster[i].worldX = gp.tileSize*11;
        gp.monster[i].worldY = gp.tileSize*10;
        i++;

        gp.monster[i] = new MON_Bat(gp);
        gp.monster[i].worldX = gp.tileSize*11;
        gp.monster[i].worldY = gp.tileSize*37;
        i++;

        gp.monster[i] = new MON_Bat(gp);
        gp.monster[i].worldX = gp.tileSize*13;
        gp.monster[i].worldY = gp.tileSize*25;
        i++;

        gp.monster[i] = new MON_Bat(gp);
        gp.monster[i].worldX = gp.tileSize*14;
        gp.monster[i].worldY = gp.tileSize*27;
        i++;

        gp.monster[i] = new MON_Bat(gp);
        gp.monster[i].worldX = gp.tileSize*10;
        gp.monster[i].worldY = gp.tileSize*11;
        i++;

        gp.monster[i] = new MON_Bat(gp);
        gp.monster[i].worldX = gp.tileSize*10;
        gp.monster[i].worldY = gp.tileSize*14;
        i++;

        gp.monster[i] = new MON_Bat(gp);
        gp.monster[i].worldX = gp.tileSize*8;
        gp.monster[i].worldY = gp.tileSize*20;
        i++;
    }


}
