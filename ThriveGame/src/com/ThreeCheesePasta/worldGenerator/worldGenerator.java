package com.ThreeCheesePasta.worldGenerator;

import java.util.Random;
import java.util.Arrays;

import com.ThreeCheesePasta.world.Tile;
import com.ThreeCheesePasta.world.tileHandler;


public class worldGenerator {
	public static String[] biomeList = {"Plains",
	                             		"Mountains"};
	public static int[] biomeHeight = {5000,
									   10000};
	private static int sliceSize = 128;
	private static int worldDim = sliceSize * sliceSize;
	
	public int[][][] fullMap = new int[sliceSize][sliceSize][worldDim];
	
	//Assigns biomes to slices
	public int[] biomesToWorld() {
		Random random = new Random();
		int[] biomesInWorld = new int[1024];
		int numberOfBiomes = worldGenerator.biomeList.length;
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
	public int[][] addToArray(int[][] originalArray, int[] adder, int numOfSlice) {
		for(int i = 0; i < adder.length; i++) {
			originalArray[i][numOfSlice] = adder[i];
		}
		return originalArray;
	}
	
}
