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
		int xpos = (int) (-1 * (Math.round(x) % tilePix));
		int ypos = (int) (-1 * (Math.round(y) % tilePix));
		
		//Find max and min x and y
		float widthTiles = width / tilePix;
		float heightTiles = height / tilePix;
		float spaceWidth = widthTiles / 2;
		float spaceHeight = heightTiles / 2;
		
		//Loops through array		
		for(int i = (int) Math.floor(x - spaceWidth - 7); i < x + spaceWidth + 7; i++) {
			if((i < 0) || (i >= map.length)) break;
			for(int j = (int) Math.floor((y + spaceHeight + 7)); j >= y - spaceHeight - 7; j--) {
				if((j < 0) || (j >= map[0].length)) continue;
				if(map[i][j] != null) {
					TextureLoader.getTileImage(map[i][j].id).draw(xpos, ypos, scale);
				}
				ypos += tilePix;
			}
			ypos = (int) (-1 * (Math.round(y) % tilePix));
			xpos += tilePix;
			
		}
		
		xpos = (int) (-1 * (x % tilePix));
		//TextureLoader.getTileImage(2).draw(0,0);
		return g;
	}

	
	/*public Graphics renderSlice(Graphics g, Tile[][] slice) {
		return g;
	}*/
}
