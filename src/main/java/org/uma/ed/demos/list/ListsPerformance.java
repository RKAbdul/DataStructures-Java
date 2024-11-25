package org.uma.ed.demos.list;

import org.uma.ed.datastructures.list.ArrayList;
import org.uma.ed.datastructures.list.LinkedList;
import org.uma.ed.datastructures.list.List;

/**
 * Compares the performance of different implementations of lists.
 *
 * @author Pepe Gallardo, Data Structures, Grado en Informática. UMA.
 */
public class ListsPerformance {

  public enum Implementation {WithArray, Linked}

  public static void main(String[] args) {

    final int numberOfTests = 10;

    double tLinked = avgTime(Implementation.Linked, numberOfTests);
    double tWithArray = avgTime(Implementation.WithArray, numberOfTests);

    System.out.printf("Average execution time for LinkedList is %f seconds\n", tLinked);
    System.out.printf("Average execution time for ArrayList is %f seconds\n", tWithArray);
    System.out.printf("ArrayList is %.2f times faster", tLinked / tWithArray);
  }

  public static double test(Implementation implementation, int seed, int length) {
    long t0 = System.currentTimeMillis();
    List<Integer> list = switch (implementation) {
        case WithArray -> ArrayList.empty();
        case Linked -> LinkedList.empty();
    };
    RandomList.fill(list, seed, length);
    long t1 = System.currentTimeMillis();
    return (t1 - t0) / 1e3; // execution time in seconds
  }

  static double avgTime(Implementation implementation, int numberOfTests) {
    double time = 0.0;

    for (int seed = 0; seed < numberOfTests; seed++) {
      time += test(implementation, seed, 100000);
    }

    return time / numberOfTests;
  }
}
