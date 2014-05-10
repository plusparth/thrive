package com.ThreeCheesePasta.Thrive;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
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
import com.ThreeCheesePasta.Thrive.utils.OrthoCamController;

public class ThriveGame extends ApplicationAdapter {
	private SpriteBatch batch;
	private TiledMap renderMap;
	private TiledMapRenderer renderer;
	private OrthographicCamera camera;
	private OrthoCamController cameraController;
	private AssetManager assetManager;
	private Texture tiles;
	private Texture texture;
	private BitmapFont font;
	private int[][] map = new int[4112][4112];
	
	@Override
	public void create () {
		float w = Gdx.graphics.getWidth();
		float h = Gdx.graphics.getHeight();

		camera = new OrthographicCamera();
		camera.setToOrtho(false, (w / h) * 320, 320);
		camera.update();

		cameraController = new OrthoCamController(camera);
		Gdx.input.setInputProcessor(cameraController);

		font = new BitmapFont();
		batch = new SpriteBatch();
		
		

		{
			tiles = new Texture(Gdx.files.internal("assets/textures/tileSheet.png"));
			TextureRegion[][] splitTiles = TextureRegion.split(tiles, 32, 32);
			renderMap = new TiledMap();
			MapLayers layers = renderMap.getLayers();
			for (int l = 0; l < 20; l++) {
				TiledMapTileLayer layer = new TiledMapTileLayer(4112, 4112, 32, 32);
				for (int x = 0; x < 1025; x++) {
					for (int y = 0; y < 1025; y++) {
						int ty = (int)(Math.random() * splitTiles.length);
						int tx = (int)(Math.random() * splitTiles[ty].length);
						Cell cell = new Cell();
						cell.setTile(new StaticTiledMapTile(splitTiles[ty][tx]));
						layer.setCell(x, y, cell);
					}
				}
				layers.add(layer);
			}
		}
		renderer = new OrthogonalTiledMapRenderer(renderMap);
	}

	@Override
	public void render () {
		Gdx.gl.glClearColor(1, 1, 1, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		camera.update();
		renderer.setView(camera);
		renderer.render();
		batch.begin();
		font.draw(batch, "FPS: " + Gdx.graphics.getFramesPerSecond(), 10, 20);
		batch.end();
	}
}
