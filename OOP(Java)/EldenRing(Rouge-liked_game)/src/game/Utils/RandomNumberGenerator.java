package game.Utils;

import game.Runes.Rune;

import java.util.Random;

/**
 * A random number generator
 */
public class RandomNumberGenerator {
    /**
     * Getter for the random Int
     * @param bound Bounds of the Integer
     * @return New Random Integer
     */
    public static int getRandomInt(int bound) {
        return bound > 0 ? new Random().nextInt(bound) : 0;
    }

    /**
     * Getter for the random Integer
     * @param lowerBound lower bound of integer
     * @param upperBound upper bound of integer
     * @return New Random Integer
     */
    public static int getRandomInt(int lowerBound, int upperBound) {
        int range = upperBound - lowerBound + 1;
        return new Random().nextInt(range) + lowerBound;
    }
}
