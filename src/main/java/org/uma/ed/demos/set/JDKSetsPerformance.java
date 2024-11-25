package org.uma.ed.demos.set;

import java.util.HashSet;
import java.util.Set;
import java.util.Random;
import java.util.TreeSet;

/**
 * Compares performance of TreeSet and HashSet from JDK.
 *
 * @author Pepe Gallardo, Data Structures, Grado en Informática. UMA.
 */
public class JDKSetsPerformance {
  public enum Implementation {Tree, Hash}

  public static void fill(Set<Integer> set, int seed, int numberOfOperations) {
    Random rnd = new Random(seed);
    for (int i = 0; i < numberOfOperations; i++) {
      int r = rnd.nextInt(4);
      switch (r) {
        case 0:
        case 1:
          int x = rnd.nextInt();
          set.add(x);
          break;
        case 2:
          int y = rnd.nextInt();
          set.remove(y);
          break;
        case 3:
          int z = rnd.nextInt();
          set.contains(z);
      }
    }
  }

  public static double test(Implementation implementation, int seed, int numberOfOperations) {
    long t0 = System.currentTimeMillis();
    Set<Integer> set = switch (implementation) {
      case Tree -> new TreeSet<>();
      case Hash -> new HashSet<>();
    };
    fill(set, seed, numberOfOperations);
    long t1 = System.currentTimeMillis();
    return (t1 - t0) / 1e3; // execution time in seconds
  }

  static double avgTime(Implementation implementation, int numberOfTests) {
    double time = 0.0;

    for (int seed = 0; seed < numberOfTests; seed++) {
      time += test(implementation, seed, 50000);
    }

    return time / numberOfTests;
  }

  public static void main(String[] args) {
    final int numberOfTests = 10;

      double t1 = avgTime(Implementation.Tree, numberOfTests);
      double t2 = avgTime(Implementation.Hash, numberOfTests);

      System.out.printf("Average execution time for TreeSet is %f seconds\n", t1);
      System.out.printf("Average execution time for HashSet is %f seconds\n", t2);
      System.out.println();

      System.out.print("TreeSet is reference\n");
      System.out.printf("HashSet is %.2f times faster\n", t1 / t2);
  }
}
