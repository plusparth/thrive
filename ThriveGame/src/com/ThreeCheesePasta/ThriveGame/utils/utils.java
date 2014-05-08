package com.ThreeCheesePasta.ThriveGame.utils;

public class utils {
	public static double[] removeLast(double[] array) {
		double[] newArray = new double[array.length - 1];
		for(int x = 0; x < array.length - 1; x++) {
			newArray[x] = array[x];
		}
		return newArray;
	}
	public static int[] dimensionalize(int[][] twoArray) {
		int[] finalArray = new int[twoArray.length * twoArray[0].length];
		int c = 0;
		for(int i = 0; i < twoArray.length; i++) {
			for(int j = 0; j < twoArray[0].length; j++) {
				finalArray[c] = twoArray[i][j];
				System.out.println(finalArray[c]);
				c++;
			}
		}
		return finalArray;
	}
}
