package org.uma.ed.demos.queue;

import org.uma.ed.datastructures.queue.ArrayQueue;
import org.uma.ed.datastructures.queue.LinkedQueue;
import org.uma.ed.datastructures.queue.Queue;

/**
 * Checks that different implementations of queues behave in the same way.
 *
 * @author Pepe Gallardo, Data Structures, Grado en Informática. UMA.
 */
public class QueuesCorrectness {

  public static void main(String[] args) {
    final int numberOfTests = 100;
    final int numberOfElements = 100000;

    for (int seed = 0; seed < numberOfTests; seed++) {
      Queue<Integer> lq = LinkedQueue.empty();
      Queue<Integer> aq = ArrayQueue.empty();

      RandomQueue.fill(lq, seed, numberOfElements);
      RandomQueue.fill(aq, seed, numberOfElements);

      while (!lq.isEmpty()) {
        int x = lq.first();
        int y = aq.first();
        lq.dequeue();
        aq.dequeue();
        if (x != y) {
          System.out.println("Error");
          System.exit(1);
        }
      }

      if (!aq.isEmpty()) {
        System.out.println("Error");
        System.exit(1);
      }
    }

    System.out.println("All tests passed");
  }
}
