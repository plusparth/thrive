package com.ThreeCheesePasta.ThriveGame.render;

import java.util.logging.Level;
import java.util.logging.Logger;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;

import com.ThreeCheesePasta.ThriveGame.ThriveGame;
//import org.newdawn.slick.Image;
import com.ThreeCheesePasta.ThriveGame.world.Tile;
//import com.ThreeCheesePasta.ThriveGame.world.TileHandler;
import com.ThreeCheesePasta.ThriveGame.render.TextureLoader;

public class Renderer {
	
	public static Graphics render(Graphics g, Tile[][] map, int width, int height, float x, float y, float scale) throws SlickException {		
		int tilePix = 32;
		tilePix *= scale;
		
		//Find max and min x and y
		float widthTiles = width / tilePix;
		float heightTiles = height / tilePix;
		float spaceWidth = widthTiles / 2;
		float spaceHeight = heightTiles / 2;
		float xTiles = x / tilePix;
		float yTiles = y / tilePix;
		//Logger.getLogger(Renderer.class.getName()).log(Level.INFO, Float.toString(widthTiles)); //21
		//Logger.getLogger(Renderer.class.getName()).log(Level.INFO, Float.toString(heightTiles)); //13
		
		//Loops through array		
		for(int i = (int) x; i < xTiles; i++) {
			if((i < 0) || (i >= map.length)) break;
			int xpos = (int) ((i * tilePix) - x);
			if ((xpos > width)) continue;
			if((xpos < -50)) continue;
			for(int j = (int) yTiles; j >= y; j--) {
				System.out.println(Integer.toString(j));
				if((j < 0) || (j >= map[0].length)) continue;
				int ypos = (int) ((j * tilePix) - y);
				if ((xpos > width) || (ypos > height)) continue;
				if((xpos < -50) || (ypos < -50)) continue;
				System.out.println("Tile rendered at " + Integer.toString(xpos) + " " + Integer.toString(ypos));
				if(map[i][j] != null) {
					TextureLoader.getTileImage(map[i][j].id).draw(xpos, ypos, scale);
				}
			}
			
		}
		//TextureLoader.getTileImage(2).draw(0,0);
		return g;
	}

	
	/*public Graphics renderSlice(Graphics g, Tile[][] slice) {
		return g;
	}*/
}
