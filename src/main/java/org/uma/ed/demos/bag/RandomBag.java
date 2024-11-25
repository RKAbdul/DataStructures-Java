package org.uma.ed.demos.bag;

import java.util.Random;
import org.uma.ed.datastructures.bag.Bag;

/**
 * Performs random operations on a bag. Probability of insertions is twice that of deletions and counting occurrences.
 *
 * @author Pepe Gallardo, Data Structures, Grado en Informática. UMA.
 */
public class RandomBag {
  public static void fill(Bag<Integer> bag, int seed, int numberOfOperations) {
    Random rnd = new Random(seed);
    for (int i = 0; i < numberOfOperations; i++) {
      int r = rnd.nextInt(4);
      switch (r) {
        case 0:
        case 1:
          int x = rnd.nextInt();
          bag.insert(x);
          break;
        case 2:
          int y = rnd.nextInt();
          bag.delete(y);
          break;
        case 3:
          int z = rnd.nextInt();
          bag.occurrences(z);
      }
    }
  }
}
