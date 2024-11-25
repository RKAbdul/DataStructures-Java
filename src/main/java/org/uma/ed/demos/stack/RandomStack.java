package org.uma.ed.demos.stack;

import java.util.Random;
import org.uma.ed.datastructures.stack.Stack;

/**
 * Performs random operations on a stack. Probability of pushes is twice that of pops.
 *
 * @author Pepe Gallardo, Data Structures, Grado en Informática. UMA.
 */
public class RandomStack {
  public static void fill(Stack<Integer> stack, int seed, int numberOfOperations) {
    Random rnd = new Random(seed);
    for (int i = 0; i < numberOfOperations; i++) {
      int r = rnd.nextInt(3);
      switch (r) {
        case 0:
        case 1:
          stack.push(0);
          break;
        case 2:
          if (!stack.isEmpty()) {
            stack.pop();
          }
      }
    }
  }
}
