package edu.neu.coe.info6205.sort.par;

import java.util.Arrays;
import java.util.concurrent.CompletableFuture;

/**
 * This code has been fleshed out by Ziyao Qiao. Thanks very much.
 * CONSIDER tidy it up a bit.
 */
class ParSort {

    public static int cutoff = 1000;

    public static void sort(int[] array, int from, int to) {
        if (to - from < cutoff) {
            Arrays.sort(array, from, to);
        } else {
            CompletableFuture<int[]> parsort1 = parsort(array, from, from + (to - from) / 2); //TO IMPLEMENT
            CompletableFuture<int[]> parsort2 = parsort(array, from + (to - from) / 2, to); //TO IMPLEMENT
            CompletableFuture<int[]> parsort = parsort1.thenCombine(parsort2, (xs1, xs2) -> {
                int[] result = new int[xs1.length + xs2.length];
                //TO IMPLEMENT
                int i = 0;
                int j = 0;
                int k = 0;
                while (i < xs1.length && j < xs2.length) {
                    result[k++] = xs1[i] < xs2[j] ? xs1[i++] : xs2[j++];
                }
                while (i < xs1.length)
                    result[k++] = xs1[i++];
                while (j < xs2.length)
                    result[k++] = xs2[j++];
                return result;
            });
            parsort.whenComplete((result, throwable) -> {
                if (throwable == null) {
                    System.arraycopy(result, 0, array, from, result.length);
                } else {
                    throwable.printStackTrace();
                }
            }).join();
        }
    }

    private static CompletableFuture<int[]> parsort(int[] array, int from, int to) {
        return CompletableFuture.supplyAsync(() -> {
            int[] result = Arrays.copyOfRange(array, from, to);
            sort(result, 0, result.length);
            return result;
        });
    }
}