/*
 * Copyright (c) 2017. Phasmid Software
 */

package edu.neu.coe.info6205.randomwalk;

import java.util.Random;

public class RandomWalk {

    private int x = 0;
    private int y = 0;

    //Creating instance of random class for generating random moves
    private final Random random = new Random();

    /**
     * Private method to move the current position, that's to say the drunkard moves
     *
     * @param dx the distance he moves in the x direction
     * @param dy the distance he moves in the y direction
     */
    //this method is for updating current position by moving the drunkard in x and y dir
    private void move(int dx, int dy) {
        // TO BE IMPLEMENTED  do move
        x += dx;
        y += dy;


        // SKELETON
         //throw new RuntimeException("Not implemented");
        // END SOLUTION
    }

    /**
     * Perform a random walk of m steps
     *
     * @param m the number of steps the drunkard takes
     */
    //It will perform a random walk of 'm' steps by calling randomMove method
    private void randomWalk(int m) {
        // TO BE IMPLEMENTED
        for(int i = 0; i < m; i++){
            randomMove();
        }


//throw new RuntimeException("implementation missing");
    }

    /**
     * Private method to generate a random move according to the rules of the situation.
     * That's to say, moves can be (+-1, 0) or (0, +-1).
     */
    //It will generates random move in North/South or East/West directions and updates the position accordingly
    private void randomMove() {
        boolean ns = random.nextBoolean();
        int step = random.nextBoolean() ? 1 : -1;
        move(ns ? step : 0, ns ? 0 : step);
    }

    /**
     * Method to compute the distance from the origin (the lamp-post where the drunkard starts) to his current position.
     *
     * @return the (Euclidean) distance from the origin to the current position.
     */
    //Calculate the Euclidean distance from the origin to the current position
    public double distance() {
        // TO BE IMPLEMENTED
        // SKELETON
         return Math.sqrt(x * x + y * y);
        // END SOLUTION
    }

    /**
     * Perform multiple random walk experiments, returning the mean distance.
     *
     * @param m the number of steps for each experiment
     * @param n the number of experiments to run
     * @return the mean distance
     */
    public static double randomWalkMulti(int m, int n) {
        double totalDistance = 0;
        for (int i = 0; i < n; i++) {
            RandomWalk walk = new RandomWalk();
            walk.randomWalk(m);
            totalDistance = totalDistance + walk.distance();
        }
        return totalDistance / n;
    }

    public static void main(String[] args) {
        int [] steps = {5, 15, 25, 35, 45, 55, 65, 75}; //declaring sample 8 steps foe examples
        int n = 10; // number of experiments
        double totalDistance = 0;
        for(int m : steps){
            for (int i = 0; i < n; i++) {
                totalDistance = randomWalkMulti(m, n);
            }
            //Printing result of each count with mean distance and experiments
            System.out.println(m + " steps: " + totalDistance + " over " + n + " experiments");
        }
    }
}