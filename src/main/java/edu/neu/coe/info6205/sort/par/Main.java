package edu.neu.coe.info6205.sort.par;

import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.ForkJoinPool;

/**
 * This code has been fleshed out by Ziyao Qiao. Thanks very much.
 * CONSIDER tidy it up a bit.
 */
public class Main {

    public static void main(String[] args) {
        System.out.println("Degree of parallelism: " + ForkJoinPool.getCommonPoolParallelism());
        Random random = new Random();
        int[] sizes = {500000, 1000000, 2000000}; // Array sizes to test

        // Iterate over each array size
        for (int size : sizes) {
            int[] array = new int[size];
            ArrayList<Long> timeList = new ArrayList<>();
            System.out.println("\nArray Size: " + size);

            // Adjust the loop for cutoff values and sorting
            for (int j = 50; j < 100; j++) {
                ParSort.cutoff = 10000 * (j + 1);
                // for (int i = 0; i < array.length; i++) array[i] = random.nextInt(10000000);
                long startTime = System.currentTimeMillis();

                for (int t = 0; t < 10; t++) {
                    for (int i = 0; i < array.length; i++) array[i] = random.nextInt(10000000);
                    ParSort.sort(array, 0, array.length);
                }

                long endTime = System.currentTimeMillis();
                long time = (endTime - startTime);
                timeList.add(time);

                System.out.println("Cutoff: " + ParSort.cutoff + "\t\t10 Times Time: " + time + "ms");
            }

            // Save results for the current array size
            saveResults(timeList, size);
        }
    }

    // Refactored method to save results for each array size
    private static void saveResults(ArrayList<Long> timeList, int size) {
        String filename = "./src/result_" + size + ".csv"; // Unique file for each array size
        try (FileOutputStream fos = new FileOutputStream(filename);
             OutputStreamWriter isr = new OutputStreamWriter(fos);
             BufferedWriter bw = new BufferedWriter(isr)) {

            bw.write("Cutoff,Time\n"); // Header for CSV file
            for (int j = 0; j < timeList.size(); j++) {
                String content = (double) 10000 * (j + 51) / size + "," + (double) timeList.get(j) / 10 + "\n";
                bw.write(content);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}