package org.uma.ed.demos.queue;

import org.uma.ed.datastructures.queue.ArrayQueue;
import org.uma.ed.datastructures.queue.LinkedQueue;
import org.uma.ed.datastructures.queue.Queue;

/**
 * Compares the performance of different implementations of queues.
 *
 * @author Pepe Gallardo, Data Structures, Grado en Informática. UMA.
 */
public class QueuesPerformance {

  public enum Implementation {Linked, WithArray}

  public static void main(String[] args) {

    final int numberOfTests = 10;

    double tLinked = avgTime(Implementation.Linked, numberOfTests);
    double tWithArray = avgTime(Implementation.WithArray, numberOfTests);

    System.out.printf("Average execution time for LinkedQueue is %f seconds\n", tLinked);
    System.out.printf("Average execution time for ArrayQueue is %f seconds\n", tWithArray);
    System.out.printf("ArrayQueue is %.2f times faster", tLinked / tWithArray);
  }

  public static double test(Implementation implementation, int seed, int numberOfElements) {
    long t0 = System.currentTimeMillis();
    Queue<Integer> queue = switch (implementation) {
      case WithArray -> ArrayQueue.empty();
      case Linked -> LinkedQueue.empty();
    };

    RandomQueue.fill(queue, seed, numberOfElements);
    long t1 = System.currentTimeMillis();
    return (t1 - t0) / 1e3; // execution time in seconds
  }

  static double avgTime(Implementation implementation, int numberOfTests) {
    double time = 0.0;

    for (int seed = 0; seed < numberOfTests; seed++) {
      time += test(implementation, seed, 10000000);
    }

    return time / numberOfTests;
  }
}