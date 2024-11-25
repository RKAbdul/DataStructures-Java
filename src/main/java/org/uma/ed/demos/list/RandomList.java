/**
 * @author Pepe Gallardo, Data Structures, Grado en Informática. UMA.
 * <p>
 * Perform random operations on a list
 */

package org.uma.ed.demos.list;

import java.util.Random;
import org.uma.ed.datastructures.list.List;

/**
 * Performs random operations on a list. Probability of insertions is twice that of deletions and accessing elements.
 *
 * @author Pepe Gallardo, Data Structures, Grado en Informática. UMA.
 */
public class RandomList {
  public static void fill(List<Integer> list, int seed, int numberOfOperations) {
    Random rnd = new Random(seed);
    for (int i = 0; i < numberOfOperations; i++) {
      int r = rnd.nextInt(4);
      switch (r) {
        case 0:
        case 1: {
          int index = rnd.nextInt(list.size() + 1);
          list.insert(index, 0);
          break;
        }

        case 2:
          if (!list.isEmpty()) {
            int index = rnd.nextInt(list.size());
            list.delete(index);
          }
          break;

        case 3: {
          if (!list.isEmpty()) {
            int index = rnd.nextInt(list.size());
            int x = list.get(index);
          }
          break;
        }
      }
    }
  }
}
