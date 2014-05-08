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
	
	public static void fullRender(Tile[][] slice1/*, Tile[][] slice2, Tile[][] slice3*/) throws SlickException {
		
		int tilePix = 32;		
		int xpos = 0;
		int ypos = 0;
		Logger.getLogger(Renderer.class.getName()).log(Level.INFO, "Slice1 x length: " + Integer.toString(slice1.length));
		Logger.getLogger(Renderer.class.getName()).log(Level.INFO, "Slice1 y length: " + Integer.toString(slice1[0].length));
		//Loops through array
		
		for(int i = 0; i < slice1.length; i++) {
			for(int j = slice1[0].length - 1; j >= 0; j--) {
				if(slice1[i][j] != null) {
					TextureLoader.getTileImage(slice1[i][j].id).draw(xpos, ypos);
				}
				ypos += tilePix;
			}
			ypos = 0;
			xpos += tilePix;
		}
		
		xpos = 0;
		//TextureLoader.getTileImage(2).draw(0,0);
	}

	
	/*public Graphics renderSlice(Graphics g, Tile[][] slice) {
		return g;
	}*/
}
