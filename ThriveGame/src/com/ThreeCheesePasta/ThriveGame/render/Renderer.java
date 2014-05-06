package com.ThreeCheesePasta.ThriveGame.render;

import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
//import org.newdawn.slick.Image;
import com.ThreeCheesePasta.ThriveGame.world.Tile;
//import com.ThreeCheesePasta.ThriveGame.world.TileHandler;
import com.ThreeCheesePasta.ThriveGame.render.TextureLoader;

public class Renderer {
	
	public int tilePix = 48;
	
	public static Graphics fullRender(Graphics g, Tile[][] slice1/*, Tile[][] slice2, Tile[][] slice3*/) throws SlickException {
		TextureLoader.loadTiles();
		int xpos = 0;
		int ypos = 0;
		
		//Loops through array
		for(int i = 0; i < slice1.length; i++) {
			for(int j = slice1[0].length - 1; j >= 0; j--) {
				TextureLoader.getTileImage(slice1[i][j].id).draw(xpos, ypos);;
				ypos += 48;
			}
			ypos = 0;
			xpos += 48;
		}
		return g;
	}

	
	/*public Graphics renderSlice(Graphics g, Tile[][] slice) {
		return g;
	}*/
}
