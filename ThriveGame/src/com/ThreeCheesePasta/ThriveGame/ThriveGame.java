package com.ThreeCheesePasta.ThriveGame;

import java.awt.Font;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.BasicGame;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.TrueTypeFont;
import com.ThreeCheesePasta.ThriveGame.world.Tile;
import com.ThreeCheesePasta.ThriveGame.render.Renderer;
import com.ThreeCheesePasta.ThriveGame.worldGenerator.WorldGenerator;
import com.ThreeCheesePasta.ThriveGame.world.TileHandler;
import com.ThreeCheesePasta.ThriveGame.render.TextureLoader;
import com.ThreeCheesePasta.ThriveGame.debug.*;

public class ThriveGame extends BasicGame {
	TrueTypeFont font;
	TileHandler tiles = new TileHandler();
	
	Tile[][] testSlice = new Tile[1024][1024];
	
	public ThriveGame(String gamename) {
		super(gamename);
	}

	@Override
	public void init(GameContainer gc) throws SlickException {
		Font Consolas = new Font("Consolas", Font.PLAIN, 14);
		font = new TrueTypeFont(Consolas, false);
		tiles.setTileProperties();
		TextureLoader.loadTiles();
		testSlice = WorldGenerator.generateFlatMap(tiles);
		Renderer.fullRender(testSlice);
		Logger.getLogger(ThriveGame.class.getName()).log(Level.INFO, "Full render complete.");
		
	}
	@Override
	public void update(GameContainer gc, int i) throws SlickException {
	}

	@Override
	public void render(GameContainer gc, Graphics g) throws SlickException {
		//g = Renderer.fullRender(g, testSlice);
		//Logger.getLogger(ThriveGame.class.getName()).log(Level.INFO, "Full render complete.");
		g.drawString(Debugger.sysInfo(), 0, 32);
		//WorldGenerator.generateFlatMap(new TileHandler());
	}
	
	public static void main(String[] args) {
		try {
			AppGameContainer appgc;
			appgc = new AppGameContainer(new ThriveGame("Thrive - Prealpha Development Test 1"));
			appgc.setVSync(true);
			appgc.setDisplayMode(1920, 1080, false); //Width, height, fullscreen
			appgc.start();
		} catch (SlickException ex) {
			Logger.getLogger(ThriveGame.class.getName()).log(Level.SEVERE, null, ex);
		}
		
	}
}
