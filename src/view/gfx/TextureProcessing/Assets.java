package view.gfx.TextureProcessing;

import java.awt.image.BufferedImage;


public class Assets {

    private static int playerAttackWidth = 225/4, playerAttackHeight = 225 / 4;

    private static int playerWidth = 264/4, playerHeight = 262/4;
    private static int width = 661/20 , height = 661/20;


    public static BufferedImage player, dirt, grass, stone, tree, boulder, enemy , pause ,door;
    public static BufferedImage inventoryImage;

    public static BufferedImage[] playerDown;
    public static BufferedImage[] playerRight;
    public static BufferedImage[] playerUp;
    public static BufferedImage[] playerLeft;
    public static BufferedImage[] playerAttacks;
    public static BufferedImage[] pauseImages;
    public static BufferedImage[] goldImages;

    public static BufferedImage[] monsterDown;
    public static BufferedImage[] monsterRight;
    public static BufferedImage[] monsterLeft;
    public static BufferedImage[] monsterUp;
    public static BufferedImage[] fireBall;

    private static SpriteSheet monsterSheet;
    private static SpriteSheet sheet;
    private static SpriteSheet playerSheet;
    private static SpriteSheet playerAttackSheet;
    private static SpriteSheet pauseSheet;
    private static SpriteSheet goldSheet;
    private static SpriteSheet fireBallSheet;
    private static SpriteSheet inventory;


    public static void init(){

            initPlayerTex();
            initTerrainTex();
            initCreatureTex();
            initItemTex();
            initPauseGfx();
            initAttackTex();
            initInventory();
    }

    public static void initTerrainTex(){
        sheet = new SpriteSheet("/textures/terrain.png");

        dirt = sheet.crop(0 ,17 * height, width, height);
        grass = sheet.crop(0,0, width, height);
        stone = sheet.crop(width * 3,0, width, height);
        door = sheet.crop(width * 6, 0, width, height);
    }

    public static void initItemTex(){
        tree = sheet.crop(width * 1,6 * height, width, height);
        boulder = sheet.crop(width * 0,6 * height, width, height);

        initGold();
    }

    public static void initCreatureTex(){
        enemy = sheet.crop(0,10 * height, width, height);
        initMonsterTex();
    }

    public static void initPlayerTex(){

        playerSheet = new SpriteSheet("/textures/sheet.png");

        player = playerSheet.crop(0,0, Assets.playerWidth, Assets.playerHeight);

        initPlayerUp();
        initPlayerDown();
        initPlayerLeft();
        initPlayerRight();

    }

    public static void initMonsterTex(){

        int W = 835 / 12;
        int H = 557 / 8;

        monsterSheet = new SpriteSheet("/textures/monster.png");

        initMonsterDown(W, H);
        initMonsterUp(W, H);
        initMonsterLeft(W, H);
        initMonsterRight(W, H);
    }

    public static void initPlayerAttacks(){
        playerAttackSheet = new SpriteSheet("");
        playerAttacks[0] = playerAttackSheet.crop( 0, playerAttackHeight * 3, playerAttackWidth, playerAttackHeight);
        playerAttacks[1] = playerAttackSheet.crop(playerAttackWidth , playerAttackHeight * 3, playerAttackWidth, playerAttackHeight);
        playerAttacks[2] = playerAttackSheet.crop(playerAttackWidth * 2, playerAttackHeight * 3, playerAttackWidth, playerAttackHeight);
        playerAttacks[3] = playerAttackSheet.crop(playerAttackWidth * 3, playerAttackHeight * 3, playerAttackWidth, playerAttackHeight);
    }

    public static void initGold(){
        int W = 1151 / 6;
        int H = 171;

        goldSheet = new SpriteSheet("/textures/gold.png");

        goldImages = new BufferedImage[6];

        goldImages[0] = goldSheet.crop( 0, 0, W, H);
        goldImages[1] = goldSheet.crop(W , 0, W, H);
        goldImages[2] = goldSheet.crop(W * 2, 0, W, H);
        goldImages[3] = goldSheet.crop( W * 3, 0, W, H);
        goldImages[4] = goldSheet.crop(W * 4, 0, W, H);
        goldImages[5] = goldSheet.crop(W * 5, 0, W, H);
    }

    public static void initPauseGfx(){

        int W = 835 / 3, H = 547 / 2;

        pauseSheet = new SpriteSheet("/textures/pause.png");

        pauseImages = new BufferedImage[6];

        pauseImages[0] = pauseSheet.crop( 0, 0, W, H);
        pauseImages[1] = pauseSheet.crop(W , 0, W, H);
        pauseImages[2] = pauseSheet.crop(W * 2, 0, W, H);
        pauseImages[3] = pauseSheet.crop(0, H , W, H);
        pause = pauseImages[4] = pauseSheet.crop(W , H , W, H); //Pause sign
        pauseImages[5] = pauseSheet.crop(W * 2, H , W, H);
    }

    public static void initPlayerUp(){
        playerUp = new BufferedImage[4];

        playerUp[0] = playerSheet.crop( 0, playerHeight * 3, playerWidth, playerHeight);
        playerUp[1] = playerSheet.crop(playerWidth , playerHeight * 3, playerWidth, playerHeight);
        playerUp[2] = playerSheet.crop(playerWidth * 2, playerHeight * 3, playerWidth, playerHeight);
        playerUp[3] = playerSheet.crop(playerWidth * 3, playerHeight * 3, playerWidth, playerHeight);

    }

    public static void initPlayerDown(){
        playerDown = new BufferedImage[4];

        playerDown[0] = playerSheet.crop(playerWidth * 0, playerHeight * 0, playerWidth, playerHeight);
        playerDown[1] = playerSheet.crop(playerWidth * 1, playerHeight * 0, playerWidth, playerHeight);
        playerDown[2] = playerSheet.crop(playerWidth * 2, playerHeight * 0, playerWidth, playerHeight);
        playerDown[3] = playerSheet.crop(playerWidth * 3, playerHeight * 0, playerWidth, playerHeight);
    }

    public static void initPlayerLeft(){
        playerLeft = new BufferedImage[4];

        playerLeft[0] = playerSheet.crop(playerWidth * 0, playerHeight * 1, playerWidth, playerHeight);
        playerLeft[1] = playerSheet.crop(playerWidth * 1, playerHeight * 1, playerWidth, playerHeight);
        playerLeft[2] = playerSheet.crop(playerWidth * 2, playerHeight * 1, playerWidth, playerHeight);
        playerLeft[3] = playerSheet.crop(playerWidth * 3, playerHeight * 1, playerWidth, playerHeight);
    }

    public static void initPlayerRight(){
        playerRight = new BufferedImage[4];

        playerRight[0] = playerSheet.crop(playerWidth * 0, playerHeight * 2, playerWidth, playerHeight);
        playerRight[1] = playerSheet.crop(playerWidth * 1, playerHeight * 2, playerWidth, playerHeight);
        playerRight[2] = playerSheet.crop(playerWidth * 2, playerHeight * 2, playerWidth, playerHeight);
        playerRight[3] = playerSheet.crop(playerWidth * 3, playerHeight * 2, playerWidth, playerHeight);
    }

    public static void initMonsterUp(int W, int H){
        monsterUp = new BufferedImage[3];

        monsterUp[0] = monsterSheet.crop(W, H * 3, W, H );
        monsterUp[1] = monsterSheet.crop(W, H * 3, W, H );
        monsterUp[2] = monsterSheet.crop(W * 2, H * 3, W, H );
    }

    public static void initMonsterDown(int W, int H){
        monsterDown = new BufferedImage[3];

        monsterDown[0] = monsterSheet.crop(W, 0, W, H );
        monsterDown[1] = monsterSheet.crop(W, 0, W, H );
        monsterDown[2] = monsterSheet.crop(W * 2, 0, W, H );
    }

    public static void initMonsterLeft(int W, int H){
        monsterLeft = new BufferedImage[3];

        monsterLeft[0] = monsterSheet.crop(W, H * 1, W, H );
        monsterLeft[1] = monsterSheet.crop(W, H * 1, W, H );
        monsterLeft[2] = monsterSheet.crop(W * 2, H * 1, W, H );

    }

    public static void initMonsterRight(int W, int H){
        monsterRight = new BufferedImage[3];

        monsterRight[0] = monsterSheet.crop(W, H * 2, W, H );
        monsterRight[1] = monsterSheet.crop(W, H * 2, W, H );
        monsterRight[2] = monsterSheet.crop(W * 2, H * 2, W, H );

    }

    public static void initAttackTex(){
        initFireBall();
    }

    public static void initFireBall(){
        int W = 960 / 6;
        int H = 768 / 4;

        fireBallSheet = new SpriteSheet("/textures/fireball.png");
        fireBall = new BufferedImage[4];

        fireBall[0] = fireBallSheet.crop(0, H , W, H );
        fireBall[1] = fireBallSheet.crop(W, H , W, H );
        fireBall[2] = fireBallSheet.crop(W * 2, H , W, H );
        fireBall[3] = fireBallSheet.crop(W * 3, H , W, H );
    }

    public static void initInventory(){

       inventory = new SpriteSheet("/textures/inventory.png");

       inventoryImage = inventory.crop(0,0, 720, 653);

    }

}
