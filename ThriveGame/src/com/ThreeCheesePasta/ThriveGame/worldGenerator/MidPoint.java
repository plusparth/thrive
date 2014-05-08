package com.ThreeCheesePasta.ThriveGame.worldGenerator;

import java.util.Arrays;
import java.util.Random;

public class MidPoint {

    private static final Random RANDOM = new Random();

    private final double[] heightmap;

    private MidPoint(double[] heightmap) {
        this.heightmap = heightmap;
    }

    private double[] getHeightmap() {
        return Arrays.copyOf(heightmap, heightmap.length);
    }
    private static double random(double range) {
        return range - ((2 * range) * RANDOM.nextDouble());
    }
    private static double getMidpoint(double a, double b) {
        return ((a - b) / 2) + b;
    }
    private static double getOffset(double current, double left, double right) {
        final int comparison = Double.compare(left, right);

        if (comparison < 0) {
            return getMidpoint(left, right);
        } else if (comparison > 0) {
            return getMidpoint(right, left);
        } else {
            return current;
        }
    }
    private static void midPoint(double[] heightmap, int iteration, int limit, int left, int right, double range, double deviation) {
        if (iteration == limit) {
            return;
        }

        final int midpoint = ((right - left) / 2) + left;

        heightmap[midpoint] = (getOffset(heightmap[midpoint], heightmap[left], heightmap[right]) + random(range));

        final double adjusted = range * deviation;

        midPoint(heightmap, iteration + 1, limit, left, midpoint, adjusted, deviation);
        midPoint(heightmap, iteration + 1, limit, midpoint, right, adjusted, deviation);
    }
    public static double[] getMidPoints(final int iterations, final double range, final double roughness) {
        final double[] heightmap = new double[(int) Math.pow(2, iterations) + 1];
        heightmap[0] = 512;
        heightmap[(int) Math.pow(2, iterations)] = 512;
        final double deviation = Math.pow(2.0, roughness);

        midPoint(heightmap, 0, iterations, 0, heightmap.length - 1, range, deviation);

        return heightmap;
    }
    public static MidPoint generate(final int iterations, final double range, final double roughness) {

        if (iterations >= 31) {
            throw new IllegalArgumentException("Too many iterations, must be 30 or less");
        }

        if (Double.isNaN(range) || Double.isInfinite(range) || !(range > 0.0)) {
            throw new IllegalArgumentException("Range must be a positive value greater than 0");
        }

        if (Double.isNaN(roughness) || Double.isInfinite(roughness) || !(roughness > 0.0)) {
            throw new IllegalArgumentException("Roughness must be a positive value greater than 0");
        }

        return new MidPoint(getMidPoints(iterations, range, roughness));
    }
}