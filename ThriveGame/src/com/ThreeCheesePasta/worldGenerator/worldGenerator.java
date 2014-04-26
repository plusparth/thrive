package com.ThreeCheesePasta.worldGenerator;

import java.math.*;
import java.util.Random;
import java.util.Arrays;

import com.ThreeCheesePasta.world.Tile;
import com.ThreeCheesePasta.world.tileHandler;


public class worldGenerator {
	public String[] biomeList = {"Plains",
	                             "Mountains"};
	public int[] biomeHeight = {5000,
								10000};
	
	//Assigns biomes to slices
	public int[] biomesToWorld() {
		Random random = new Random();
		int[] biomesInWorld = new int[1024];
		int numberOfBiomes = this.biomeList.length;
		for(int i = 0; i < 1024; i++) {
			biomesInWorld[i] = random.nextInt(numberOfBiomes);
		}
		return biomesInWorld;
	}
	private int sliceSize = 128;
	private int worldDim = sliceSize * sliceSize;
	public int[] fullMap = new int[worldDim];
	public int[] heightMap(int first, int last, int width, int height, float displace, float roughness) {
		int power = (int) Math.floor(Math.pow(2,Math.ceil(Math.log(width) / Math.log(2))));
		int[] points = new int[power];
		if(first < 0) { points[0] = (int) Math.floor(height/2 + (Math.random()*displace*2) - displace); }
		if(last < 0) { points[power] = (int) Math.floor(height/2 + (Math.random()*displace*2) - displace); }
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
	public int[] addToArray(int[] originalArray, int[] adder) {
		int x = Arrays.asList(originalArray).indexOf(null);
		for(int i = 0; i < adder.length; i++) {
			originalArray[x] = adder[i];
			x++;
		}
		return originalArray;
	}
	
}
