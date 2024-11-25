package org.uma.ed.demos.queue;

import java.util.Random;
import org.uma.ed.datastructures.queue.Queue;

/**
 * Performs random operations on a queue. Probability of enqueues is twice that of dequeues.
 *
 * @author Pepe Gallardo, Data Structures, Grado en Informática. UMA.
 */
public class RandomQueue {
  public static void fill(Queue<Integer> queue, int seed, int numberOfOperations) {
    Random rnd = new Random(seed);
    for (int i = 0; i < numberOfOperations; i++) {
      int r = rnd.nextInt(3);
      switch (r) {
        case 0:
        case 1:
          queue.enqueue(0);
          break;
        case 2:
          if (!queue.isEmpty()) {
            queue.dequeue();
          }
      }
    }
  }
}
