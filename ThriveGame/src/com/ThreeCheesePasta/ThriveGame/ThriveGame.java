package com.ThreeCheesePasta.ThriveGame;

import java.awt.Font;
import java.awt.event.KeyEvent;
import java.util.logging.Level;
import java.util.logging.Logger;


import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.BasicGame;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.TrueTypeFont;
import org.lwjgl.input.Keyboard;

import com.ThreeCheesePasta.ThriveGame.world.Tile;
import com.ThreeCheesePasta.ThriveGame.render.Renderer;
import com.ThreeCheesePasta.ThriveGame.worldGenerator.WorldGenerator;
import com.ThreeCheesePasta.ThriveGame.worldGenerator.MidPoint;
import com.ThreeCheesePasta.ThriveGame.world.TileHandler;
import com.ThreeCheesePasta.ThriveGame.render.TextureLoader;
import com.ThreeCheesePasta.ThriveGame.debug.*;

public class ThriveGame extends BasicGame {
	TrueTypeFont font;
	TileHandler tiles = new TileHandler();
	
	int[] heightMap = new int[16400];
	Tile[][] map = new Tile[4112][4112];
	
	float playerX = 100;
	float playerY = 2000;
	float playerSpeed = 0;
	int lastKey = 0;
	float scale = 1.0f;
	static float speedLim = 0.1f;
	
	public ThriveGame(String gamename) {
		super(gamename);
	}

	@Override
	public void init(GameContainer gc) throws SlickException {
		//Font stuff, just testing
		Font Consolas = new Font("Consolas", Font.PLAIN, 14);
		font = new TrueTypeFont(Consolas, false);
		//Init methods
		tiles.setTileProperties();
		TextureLoader.loadTiles();
		//Map stuffs
		heightMap = WorldGenerator.heightMap();
		map = WorldGenerator.mapGen(heightMap, tiles);
		//map = WorldGenerator.generateFlatMap(tiles);
		playerY = heightMap[(int) playerX];
		Logger.getLogger(Renderer.class.getName()).log(Level.INFO, "Slice1 x length: " + Integer.toString(map.length));
		Logger.getLogger(Renderer.class.getName()).log(Level.INFO, "Slice1 y length: " + Integer.toString(map[0].length));
		//Leave as last line
		Logger.getLogger(Renderer.class.getName()).log(Level.INFO, "INIT COMPLETE");
	}
	@Override
	public void update(GameContainer gc, int i) throws SlickException {
		if (Keyboard.isKeyDown(Keyboard.KEY_D)) {
			if (lastKey != 1) {
				playerSpeed = 0.05f;
			}
			lastKey = 1;
			playerX += playerSpeed;
			if (playerSpeed < speedLim) {
				playerSpeed += 0.0001;
			}
		}
		else if (Keyboard.isKeyDown(Keyboard.KEY_A)) {
			if (lastKey != 2) {
				playerSpeed = 0.05f;
			}
			lastKey = 2;
			playerX -= playerSpeed;
			if (playerSpeed < speedLim) {
				playerSpeed += 0.0001;
			}
		}
		if (Keyboard.isKeyDown(Keyboard.KEY_W)) {
			playerY += 0.1;
		}
		else if (Keyboard.isKeyDown(Keyboard.KEY_S)) {
			playerY -= 0.1;
		}
	}

	@Override
	public void render(GameContainer gc, Graphics g) throws SlickException {
		Renderer.render(g, map, gc.getWidth(), gc.getHeight(), playerX, playerY, scale);
		//Logger.getLogger(ThriveGame.class.getName()).log(Level.INFO, "Full render complete.");
		g.drawString("X: " + Float.toString(playerX), 0, 300);
		g.drawString("Y: " + Float.toString(playerY), 0, 350);
		g.drawString(Debugger.sysInfo(), 0, 32);
		
	}
	
	
	public static void main(String[] args) {
		try {
			AppGameContainer appgc;
			appgc = new AppGameContainer(new ThriveGame("Thrive - Prealpha Development Test 1"));
			appgc.setVSync(true);
			appgc.setDisplayMode(680, 420, false); //Width, height, fullscreen
			appgc.start();
		} catch (SlickException ex) {
			Logger.getLogger(ThriveGame.class.getName()).log(Level.SEVERE, null, ex);
		}
		
	}
}
