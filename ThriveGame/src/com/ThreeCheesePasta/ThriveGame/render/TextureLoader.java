package com.ThreeCheesePasta.ThriveGame.render;

import org.newdawn.slick.Image;
import org.newdawn.slick.SpriteSheet;
import org.newdawn.slick.SlickException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class TextureLoader {
	private static String IMG_PATH = "images";
	
	private static Image[] tiles = new Image[3];
	
	public static void loadTiles() {
		try {
            SpriteSheet sheet = new SpriteSheet(IMG_PATH + "tileset.png", 48, 48);
            for (int i = 0; i < 8; i++)
            {
                tiles[i] = sheet.getSubImage(i, 0);
            }
        } catch (SlickException ex) {
            Logger.getLogger(TextureLoader.class.getName()).log(Level.SEVERE, null, ex);
        }
	}
	
	public static Image getTileImage(int id)
    {
        return tiles[id];
    }
	
}
