package edu.neu.coe.info6205.union_find;
import java.util.Random;
public class UFClient {
    // Static method to count connections
    public static int count(int n) {
        UF_HWQUPC uf = new UF_HWQUPC(n);

        Random random = new Random();
        int conn = 0;

        while (uf.components() > 1) {
            int a = random.nextInt(n);
            int b = random.nextInt(n);

            if (!uf.connected(a, b)) {
                uf.union(a, b);
                conn++;
            }
        }
        return conn;
    }

    // Main method to run the experiment
    public static void main(String[] args) {
        if (args.length != 1) {
            System.out.println("Usage: java UFClient <number_of_sites>");
            return;
        }
        int n = Integer.parseInt(args[0]);
        int conn = count(n);
        System.out.println("Number of connections made = " + conn);
    }
}
