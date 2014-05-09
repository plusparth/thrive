package com.ThreeCheesePasta.ThriveGame.worldGenerator;

import java.util.Random;
import java.util.Arrays;

import com.ThreeCheesePasta.ThriveGame.ThriveGame;
import com.ThreeCheesePasta.ThriveGame.world.Tile;
import com.ThreeCheesePasta.ThriveGame.world.TileHandler;

import java.util.logging.Level;
import java.util.logging.Logger;

import com.ThreeCheesePasta.ThriveGame.utils.utils;


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
	public static int[] heightMap() {
		int[][] heightMap = new int[16][257];
		for (int h = 0; h < heightMap.length; h++) {
			Random rand = new Random();
	
			//Generates biome
			int biomeNum = rand.nextInt(2);
			//System.out.print(biomes[biomeNum] + " ");
			
			
			//Creates local variables
			
			//Minimum distortion
			int rangeMin = 0;
			//Maximum, in float
			float rangeMaxFl = 0;
			//Integer Max, will be Math.floor of float max later as it gives more leeway
			int rangeMax = 0;
			//The amount rangeMax decreases per midpoint level
			float slopeDec = 0;
			//The minimum height to start on
			int startHeightMin = 0;
			//The range for starting
			int startHeightRange = 0;
			
			if (biomeNum == 0) {
				rangeMin = 0;
				rangeMaxFl = 0f;
				rangeMax = 0;
				slopeDec = 0;
				startHeightMin = 2000;
				startHeightRange = 20;
			}
			
			else if (biomeNum == 1) {
				rangeMin = -1;
				rangeMaxFl = 84;
				rangeMax = 84;
				slopeDec = 42f;
				startHeightMin = 2100;
				startHeightRange = 32;
			}
			
			//Generates starting point for first slice
			if (h == 0) {
				heightMap[h][0] = startHeightMin + rand.nextInt(startHeightRange);
			}
			//Uses last point after first slice
			else {
				heightMap[h][0] = heightMap[h - 1][heightMap[0].length - 1];
			}
			
			//Generates last point
			heightMap[h][heightMap[0].length - 1] = startHeightMin + rand.nextInt(startHeightRange);
			//System.out.print(heightMap[0] + " ");
			//System.out.print(heightMap[32] + " ");
			
			//Loops through powers of two
			//TODO: Change this 8 to be actually calculated as log2 of heightMap[0].length
			for (double i = 8; i >= 1; i--) {
				
				//Loops through all tiles using current power of two
				for (int index = (int)Math.pow(2, i - 1); index < heightMap[0].length; index += (int)Math.pow(2, i)) {
					
					//Gets average
					heightMap[h][index] = (int) Math.ceil((heightMap[h][index + (int)Math.pow(2, i - 1)] + heightMap[h][index - (int)Math.pow(2, i - 1)]) / 2);
					//Special Generation for grasslands, makes zero variation more common than -1 or 1
					if (biomeNum == 0) {
						int randomNum = rand.nextInt(21);
						if (randomNum == 0) {
							heightMap[h][index] -= 1;
						}
						if (randomNum == 20) {
							heightMap[h][index] += 1;
						}
					}
					//Normal distortion
					else {
						heightMap[h][index] +=  rangeMin + rand.nextInt((rangeMax) - rangeMin + 1);
					}
				}
				
				//Decreases maximum value in range
				rangeMaxFl -= slopeDec;
				rangeMax = (int)Math.ceil(rangeMaxFl);
				
				//Decreases decreasing-value, creates a nice smooth curve on terrain
				slopeDec /= 2;
				
			}	
		}
		return utils.dimensionalize(heightMap);
	}
	
	public static Tile[][] mapGen(int[] heightMap, TileHandler tiles) {
		Tile[][] map = new Tile[heightMap.length][heightMap.length];
		int c = 0;
		for(int x : heightMap) {
			System.out.println(x);
			int intx = x;
			if(intx < 10) intx = 10;
			if(intx >= map[0].length) intx = map[0].length - 1;
			map[c][intx] = tiles.tileGrass;
			for(int i = intx - 1; i > intx - 5; i--) {
				if(i < 0) i = 0;
				if(i >= map[0].length) i = map[0].length - 1;
				map[c][i] = tiles.tileDirt;
			}
			for(int j = intx - 5; j > 0; j--) {
				map[c][j] = tiles.tileStone;
				if(j < 0) j = 0;
				if(j >= map[0].length) j = map[0].length - 1;
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
	
	private static int[] avgHeight(double[] heightMap) {
		double[] newMap = new double[heightMap.length - 1];
		int[] finalMap = new int[newMap.length / 2];
		if(heightMap.length % 2 != 0) newMap = heightMap = utils.removeLast(heightMap);
		for(int i = 0; i < newMap.length; i += 2) {
			finalMap[i / 2] = (int) Math.floor((newMap[i] + newMap[i + 1]) / 2);
		}
		return finalMap;
	}
	
	
}
