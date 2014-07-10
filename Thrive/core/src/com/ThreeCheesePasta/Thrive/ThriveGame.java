package com.ThreeCheesePasta.thrive;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.maps.MapLayers;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapRenderer;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer.Cell;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.maps.tiled.tiles.StaticTiledMapTile;
import com.ThreeCheesePasta.thrive.utils.OrthoCamController;
import com.ThreeCheesePasta.thrive.world.Tile;
import com.ThreeCheesePasta.thrive.world.TileHandler;
import com.ThreeCheesePasta.thrive.world.WorldGenerator;

public class ThriveGame extends ApplicationAdapter {
    public float w;
    public float h;

    private float rotationSpeed;
    private SpriteBatch batch;
    private TiledMap renderMap;
    private TiledMapRenderer renderer;
    private OrthographicCamera camera;
    private OrthoCamController cameraController;
    private AssetManager assetManager;
    private Texture tiles;
    private BitmapFont font;
    private int[] heightMap;
    private Tile[][] map = new Tile[4112][4112];
    private TileHandler tileHandler;

    @Override
    public void create () {
        w = Gdx.graphics.getWidth();
        h = Gdx.graphics.getHeight();

        rotationSpeed = 0.5f;


        font = new BitmapFont();
        batch = new SpriteBatch();
        tileHandler = new TileHandler();

        tileHandler.setTileProperties();
        heightMap = WorldGenerator.heightMap();
        map = WorldGenerator.mapGen(heightMap, tileHandler);
        //map = WorldGenerator.generateFlatMap(tileHandler);

        {
            tiles = new Texture(Gdx.files.internal("assets/textures/tileSheet.png"));
            TextureRegion[][] splitTiles = TextureRegion.split(tiles, 32, 32);
            renderMap = new TiledMap();
            MapLayers layers = renderMap.getLayers();
            //for (int l = 0; l < 1; l++) {
            TiledMapTileLayer layer = new TiledMapTileLayer(map.length, map[0].length, 32, 32);
            layer.setName("world");
            for (int x = 0; x < map.length; x++) {
                for (int y = 0; y < map[0].length; y++) {
                    //System.out.println(Integer.toString(splitTiles[0].length));
                    //int ty = (int)(Math.random() * splitTiles.length);
                    //int tx = (int)(Math.random() * splitTiles[ty].length);
                    if(map[x][y] == null) continue;
                    Cell cell = new Cell();
                    cell.setTile(new StaticTiledMapTile(splitTiles[0][map[x][y].id]));
                    //System.out.println(Integer.toString(map[x][y].id));
                    layer.setCell(x, y, cell);
                }
            }
            layers.add(layer);
            //}
        }

        camera = new OrthographicCamera();
        camera.setToOrtho(false, 30, 20);
        camera.update();

        cameraController = new OrthoCamController(camera);
        Gdx.input.setInputProcessor(cameraController);

        System.out.println("Rendered");
        renderer = new OrthogonalTiledMapRenderer(renderMap, 1 / 32f);
        camera.translate(2000, heightMap[2000]);
    }

    @Override
    public void render () {
        Gdx.gl.glClearColor(0.3f, 1, 1, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        handleInput();
        camera.update();
        renderer.setView(camera);
        renderer.render();
        batch.begin();
        font.draw(batch, "FPS: " + Gdx.graphics.getFramesPerSecond(), 10, 20);
        batch.end();
    }

    @Override
    public void dispose () {
        batch.dispose();
        font.dispose();
        tiles.dispose();
        renderMap.dispose();
    }

    private void handleInput() {
        if(Gdx.input.isKeyPressed(Input.Keys.A)) {
            if (camera.position.x > w / 2)
                camera.translate(-0.5f, 0, 0);
        }
        if(Gdx.input.isKeyPressed(Input.Keys.D)) {
            if (camera.position.x < 4112 - w / 2)
                camera.translate(0.5f, 0, 0);
        }
        if(Gdx.input.isKeyPressed(Input.Keys.S)) {
            if (camera.position.y > h / 2)
                camera.translate(0, -0.5f, 0);
        }
        if(Gdx.input.isKeyPressed(Input.Keys.W)) {
            if (camera.position.y < 4112 - h / 2)
                camera.translate(0, 0.5f, 0);
        }
        /*if(Gdx.input.isKeyPressed(Input.Keys.W)) {
            camera.rotate(-rotationSpeed, 0, 0, 1);
        }
        if(Gdx.input.isKeyPressed(Input.Keys.E)) {
            camera.rotate(rotationSpeed, 0, 0, 1);
        }*/

    }
}
