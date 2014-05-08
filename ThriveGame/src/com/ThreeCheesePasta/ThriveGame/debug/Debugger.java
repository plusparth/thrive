package com.ThreeCheesePasta.ThriveGame.debug;

import java.text.NumberFormat;


public class Debugger {
	public static String sysInfo() {
		
		NumberFormat format = NumberFormat.getInstance();
		
		Runtime runtime = Runtime.getRuntime();
		StringBuilder sb = new StringBuilder();
		long maxMemory = runtime.maxMemory();
	    long allocatedMemory = runtime.totalMemory();
	    long freeMemory = runtime.freeMemory();

	    sb.append("Used memory: " + format.format((allocatedMemory - freeMemory) / 1024) + "\n");
	    sb.append("Free memory: " + format.format(freeMemory / 1024) + "\n");
	    sb.append("Allocated memory: " + format.format(allocatedMemory / 1024) + "\n");
	    sb.append("Max memory: " + format.format(maxMemory / 1024) + "\n");
	    sb.append("Total free memory: " + format.format((freeMemory + (maxMemory - allocatedMemory)) / 1024) + "\n");
	    
	    
	    return sb.toString();
	}
}
