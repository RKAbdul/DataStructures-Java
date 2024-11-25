package org.uma.ed.demos.stack;

import org.uma.ed.datastructures.stack.ArrayStack;
import org.uma.ed.datastructures.stack.LinkedStack;
import org.uma.ed.datastructures.stack.Stack;

/**
 * Compares the performance of different implementations of stacks.
 *
 * @author Pepe Gallardo, Data Structures, Grado en Informática. UMA.
 */
public class StacksPerformance {

  public enum Implementation {WithArray, Linked}

  public static void main(String[] args) {

    final int numberOfTests = 10;

    double tLinked = avgTime(Implementation.Linked, numberOfTests);
    double tWithArray = avgTime(Implementation.WithArray, numberOfTests);

    System.out.printf("Average execution time for LinkedStack is %f seconds\n", tLinked);
    System.out.printf("Average execution time for ArrayStack is %f seconds\n", tWithArray);
    System.out.printf("ArrayStack is %.2f times faster", tLinked / tWithArray);
  }

  public static double test(Implementation implementation, int seed, int numberOfElements) {
    long t0 = System.currentTimeMillis();
    Stack<Integer> stack = switch (implementation) {
      case WithArray -> ArrayStack.empty();
      case Linked -> LinkedStack.empty();
    };
    RandomStack.fill(stack, seed, numberOfElements);
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
