package com.ThreeCheesePasta.ThriveGame.worldGenerator;

import java.util.Random;
import java.util.Arrays;

import com.ThreeCheesePasta.ThriveGame.world.Tile;
import com.ThreeCheesePasta.ThriveGame.world.TileHandler;


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
	public int[] heightMap(int first, int last, int width, int height, float displace, float roughness) {
		int power = (int) Math.floor(Math.pow(2,Math.ceil(Math.log(width) / Math.log(2))));
		int[] points = new int[power];
		//Take first point into consideration
		points[0] = first;
		points[power] = last;
		//If randomly generating either first or last point
		if(first < 0) { points[0] = (int) Math.floor(height/2 + (Math.random()*displace*2) - displace); }
		if(last < 0) { points[power] = (int) Math.floor(height/2 + (Math.random()*displace*2) - displace); }
		
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
	public Tile[][][] addToArray(Tile[][][] originalArray, int[] sliceHeight, int numOfSlice) {
		//Creates full array map
		for(int i = 0; i < sliceHeight.length; i++) { //Loops through entire slice to add dirt to thing
			originalArray[i][sliceHeight[i]][numOfSlice] = tiles.tileDirt;
		}
		return originalArray;
	}
	
	//FOR TESTING PURPOSES ONLY
	public static Tile[][] generateFlatMap(TileHandler tileHand) {
		Tile[][] thing = new Tile[1024][1024];
		for(int x = 0; x < thing.length; x++) {
			for(int y = 0; y < thing[0].length; y++) {
				if(y == 1010) {
					thing[x][y] = tileHand.tileGrass;
				}
				else if(y > 1000) {
					thing[x][y] = tileHand.tileDirt;
				}
				else {
					thing[x][y] = tileHand.tileStone;
				}
			}
		}
		
		return thing;
	}
	
	
}
