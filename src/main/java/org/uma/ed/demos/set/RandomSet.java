package org.uma.ed.demos.set;

import java.util.Random;
import org.uma.ed.datastructures.set.Set;

/**
 * Performs random operations on a set. Probability of insertions is twice that of deletions and contains tests.
 *
 * @author Pepe Gallardo, Data Structures, Grado en Informática. UMA.
 */
public class RandomSet {
  public static void fill(Set<Integer> set, int seed, int numberOfOperations) {
    Random rnd = new Random(seed);
    for (int i = 0; i < numberOfOperations; i++) {
      int r = rnd.nextInt(4);
      switch (r) {
        case 0:
        case 1:
          int x = rnd.nextInt();
          set.insert(x);
          break;
        case 2:
          int y = rnd.nextInt();
          set.delete(y);
          break;
        case 3:
          int z = rnd.nextInt();
          set.contains(z);
      }
    }
  }

  /**
   * Performs random operations on a set. Probability of insertions is twice that of deletions and contains tests.
   * Elements are inserted in ascending order.
   *
   * @author Pepe Gallardo, Data Structures, Grado en Informática. UMA.
   */
  public static void fillAscending(Set<Integer> set, int seed, int numberOfOperations) {
    int element = Integer.MIN_VALUE;
    Random rnd = new Random(seed);
    for (int i = 0; i < numberOfOperations; i++) {
      int r = rnd.nextInt(4);
      switch (r) {
        case 0:
        case 1:
          element += rnd.nextInt(5);
          set.insert(element);
          break;
        case 2:
          int x = rnd.nextInt(element);
          set.delete(x);
          break;
        case 3:
          int y = rnd.nextInt(element);
          set.contains(y);
      }
    }
  }
}
