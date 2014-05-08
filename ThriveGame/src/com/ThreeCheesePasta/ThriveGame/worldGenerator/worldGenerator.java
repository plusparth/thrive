package com.ThreeCheesePasta.ThriveGame.worldGenerator;

import java.util.Random;
import java.util.Arrays;

import com.ThreeCheesePasta.ThriveGame.ThriveGame;
import com.ThreeCheesePasta.ThriveGame.world.Tile;
import com.ThreeCheesePasta.ThriveGame.world.TileHandler;

import java.util.logging.Level;
import java.util.logging.Logger;


public class WorldGenerator {
	public TileHandler tiles = new TileHandler();
	
	public static String[] biomeList = {"Plains",
	                             		"Mountains"};
	public static int[] biomeHeight = {5000,
									   10000};
	private static int sliceSize = 128;
	private static int worldDim = sliceSize * sliceSize;
	
	public Tile[][][] fullMap = new Tile[sliceSize][sliceSize][worldDim];
	
	//Assigns biomes to slices
	public int[] biomesToWorld() {
		Random random = new Random();
		int[] biomesInWorld = new int[1024];
		int numberOfBiomes = WorldGenerator.biomeList.length;
		for(int i = 0; i < 1024; i++) {
			biomesInWorld[i] = random.nextInt(numberOfBiomes);
		}
		return biomesInWorld;
	}
	
	//Generates heightmap for stuffs
	/*public static int[] heightMap(int width, int height, float displace, float roughness) {
		int power = (int) Math.floor(Math.pow(2,Math.ceil(Math.log(width) / Math.log(2)))) - 1;
		int[] points = new int[power + 1];
	
		points[0] = (int) Math.floor(height/2 + (Math.random()*displace*2) - displace);
		points[power] = points[0];
		
		//Midpoint displacement
	    displace *= roughness;
	    for(int i = 1; i < power; i *=2) {
	        for(int j = (power/i)/2; j < power; j+= power/i) {
	            points[j] = (points[j - (power / i) / 2] + points[j + (power / i) / 2]) / 2;
	            points[j] += (Math.random()*displace*2) - displace;
	        }
	        displace *= roughness;
	    }   
	    
		return points;
	}
	
	//Combines arrays into the 2d thing
	/*public Tile[][] addToArray(Tile[][] originalArray, int[] sliceHeight, int numOfSlice) {
		//Creates full array map
		for(int i = 0; i < sliceHeight.length; i++) { //Loops through entire slice to add dirt to thing
			originalArray[i][sliceHeight[i]][numOfSlice] = tiles.tileDirt;
		}
		return originalArray;
	}*/
	
	public static Tile[][] mapGen(double[] heightMap, TileHandler tiles) {
		Tile[][] map = new Tile[heightMap.length][heightMap.length];
		int c = 0;
		for(double x : heightMap) {
			System.out.println(x);
			int intx = (int) Math.round(x);
			if(intx < 10) intx = 10;
			if(intx >= map[0].length) intx = 1025;
			map[c][intx] = tiles.tileGrass;
			for(int i = intx - 1; i > intx - 5; i--) {
				if(i < 0) i = 0;
				if(i >= map[0].length) i = 1025;
				map[c][i] = tiles.tileDirt;
			}
			for(int j = intx - 5; j > 0; j--) {
				map[c][j] = tiles.tileStone;
				if(j < 0) j = 0;
				if(j >= map[0].length) j = 1025;
			}
			c++;
			System.out.println("One iteration complete.");
		}
		return map;
	}
	
	//FOR TESTING PURPOSES ONLY
	public static Tile[][] generateFlatMap(TileHandler tileHand) {
		Tile[][] thing = new Tile[1025][1025];
		for(int x = 0; x < thing.length; x++) {
			for(int y = 0; y < thing[0].length; y++) {
				if(y == 1010) {
					thing[x][y] = tileHand.tileGrass;
				}
				else if(y < 1010)
					if(y % 2 == 0) {
						thing[x][y] = tileHand.tileDirt;
					}
					else {
						thing[x][y] = tileHand.tileStone;
					}
			}
		}
		//Logger.getLogger(WorldGenerator.class.getName()).log(Level.INFO, thing[0][1023].displayName);
		/*for(int y = 0; y < thing[0].length; y++) {
			for(int x = 0; x < thing.length; x++) {
				System.out.print(Integer.toString(thing[x][y].id));
			}
			System.out.println();
		}
		*/
		Logger.getLogger(WorldGenerator.class.getName()).log(Level.INFO, "World generated");
		return thing;
	}
	
	
}
