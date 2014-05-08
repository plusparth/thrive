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
	
	public static Graphics render(Graphics g, Tile[][] map, int width, int height, float x, float y) throws SlickException {		
		int tilePix = 32;		
		int xpos = 0;
		int ypos = 0;
		
		//Find max and min x and y
		float widthTiles = width / 32;
		float heightTiles = height / 32;
		float spaceWidth = widthTiles / 2;
		float spaceHeight = heightTiles / 2;
		
		//Loops through array		
		for(int i = (int) (x - spaceWidth); i < x + spaceWidth; i++) {
			if((i < 0) || (i >= map.length)) break;
			for(int j = (int) (y + spaceHeight + 1); j >= y - spaceHeight - 1; j--) {
				if((j < 0) || (j >= map[0].length)) continue;
				if(map[i][j] != null) {
					g.drawImage(TextureLoader.getTileImage(map[i][j].id), xpos, ypos);
				}
				ypos += tilePix;
			}
			ypos = 0;
			xpos += tilePix;
			
		}
		
		xpos = 0;
		//TextureLoader.getTileImage(2).draw(0,0);
		return g;
	}

	
	/*public Graphics renderSlice(Graphics g, Tile[][] slice) {
		return g;
	}*/
}
