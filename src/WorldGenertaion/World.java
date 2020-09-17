package WorldGenertaion;

import GamePackage.Handler;
import Tiles.Tile;
import java.awt.*;
import Utils.*;


public class World {

    private int width, height; //in tiles
    private int[][] worldTiles;
    private int spawnX, spawnY;
    private Handler handler;

    public World(String path, Handler handler) {
        this.handler = handler;
        loadWorld(path);
    }

    public World(Handler handler) {
        this.handler = handler;
        generateWorld();
    }

    public void tick() {
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                getTile(x, y).tick();
            }
        }
    }

    public void render(Graphics graphics) {
        //optimize rendering process
        int xStart = (int) Math.max(0, handler.getGame().getGameCam().getXOffset() / Tile.TILE_WIDTH - 1);
        int yStart = (int) Math.max(0, handler.getGame().getGameCam().getYOffset() / Tile.TILE_HEIGHT - 1);
        int xEnd = (int) Math.min(width, (handler.getGame().getGameCam().getXOffset() + handler.getGame().width) / Tile.TILE_WIDTH + 1);
        int yEnd = (int) Math.min(height, (handler.getGame().getGameCam().getYOffset() + handler.getGame().width) / Tile.TILE_HEIGHT + 1);


        for (int y = yStart; y < yEnd; y++) {
            for (int x = xStart; x < xEnd; x++) {
                getTile(x, y).render(graphics,
                        (int) (x * Tile.TILE_WIDTH - handler.getGame().getGameCam().getXOffset()),
                        (int) (y * Tile.TILE_HEIGHT - handler.getGame().getGameCam().getYOffset()));
            }
        }
    }

    public Tile getTile(int x, int y) {

        if (x < 0 || y < 0 || x >= width || y >= height) return Tile.grassTile;

        Tile result = Tile.tiles[worldTiles[y][x]];

        if (result == null)
            return Tile.dirtTile;
        else
            return result;
    }

    private void generateWorld() {

    }

    private void loadWorld(String path) {
        String file = Utils.loadFileAsString(path);

        String[] tokens = file.split("\\s+");
        width = Utils.parseInt(tokens[0]);
        height = Utils.parseInt(tokens[1]);
        spawnX = Utils.parseInt(tokens[2]);
        spawnY = Utils.parseInt(tokens[3]);

        worldTiles = new int[height][width];

        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                worldTiles[y][x] = TileFromLineToMatrix(y, x, tokens);
            }
        }
    }

    private int TileFromLineToMatrix(int y, int x, String[] tokens) {
        return Utils.parseInt(tokens[y * width + x + 4]);
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public int getSpawnX() {
        return spawnX;
    }

    public int getSpawnY() {
        return spawnY;
    }
}
