package edu.neu.coe.info6205.sort.elementary;
import edu.neu.coe.info6205.util.Benchmark_Timer;

import java.util.Arrays;
import java.util.Random;
import java.util.function.Consumer;
public class InsertionSortBenchmark {

    public static void main(String[] args) {
        int[] array = {100, 200, 400, 800, 1600};

        // Benchmark insertion sort for different initial array orderings
        benchmark("Random Order", InsertionSortBenchmark::randomOrder, array);
        benchmark("Ordered", InsertionSortBenchmark::ordered, array);
        benchmark("Partially Ordered", InsertionSortBenchmark::partiallyOrdered, array);
        benchmark("Reverse Ordered", InsertionSortBenchmark::reverseOrdered, array);
    }

    private static void benchmark(String description, Consumer<Integer[]> dataGenerator, int[] ns) {
        System.out.println("Benchmarking for " + description + " array:");

        for (int n : ns) {
            Integer[] array = new Integer[n];
            dataGenerator.accept(array);

            // Measure the running time using Benchmark_Timer
            Benchmark_Timer<Integer[]> timer = new Benchmark_Timer<>(
                    description + " (n=" + n + ")",
                    arr -> new InsertionSort<Integer>().mutatingSort(arr)
            );

            double time = timer.run(array, 5); // Run the benchmark 5 times
            System.out.println("  n=" + n + ", Time: " + time + " seconds");
        }

        System.out.println();
    }
    private static void randomOrder(Integer[] array) {
        Random random = new Random();
        Arrays.setAll(array, i -> random.nextInt());
    }

    private static void ordered(Integer[] array) {
        Arrays.setAll(array, i -> i);
    }

    private static void partiallyOrdered(Integer[] array) {
        Random random = new Random();
        Arrays.setAll(array, i -> i + random.nextInt(10));
    }

    private static void reverseOrdered(Integer[] array) {
        Arrays.setAll(array, i -> array.length - i);
    }
}

