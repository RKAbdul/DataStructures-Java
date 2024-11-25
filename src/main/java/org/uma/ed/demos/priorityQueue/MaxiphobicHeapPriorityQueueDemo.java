package org.uma.ed.demos.priorityQueue;

import org.uma.ed.datastructures.priorityqueue.MaxiphobicHeapPriorityQueue;
import org.uma.ed.datastructures.priorityqueue.PriorityQueue;

/**
 * Simple MaxiphobicHeapPriorityQueueDemo demo.
 *
 * @author Pepe Gallardo, Data Structures, Grado en Informática. UMA.
 */
public class MaxiphobicHeapPriorityQueueDemo {
  public static void main(String[] args) {
    PriorityQueue<Integer> pQueue = MaxiphobicHeapPriorityQueue.of(5, 3, 2, 1, 2, 0, 4, 3, 5, 1, 8);
    System.out.println("pQueue = " + pQueue);
    System.out.println("pQueue.size() = " + pQueue.size());
    System.out.println("pQueue.isEmpty() = " + pQueue.isEmpty());
    System.out.println("pQueue.first() = " + pQueue.first());
    System.out.println("Dequeueing highest priority element");
    Integer first = pQueue.first();
    System.out.println("Dequeued " + first);
    pQueue.dequeue();
    System.out.println("pQueue = " + pQueue);
    System.out.println("Dequeueing all elements");
    while(!pQueue.isEmpty()) {
      Integer elem = pQueue.first();
      pQueue.dequeue();
      System.out.println("Dequeued " + elem);
    }
  }
}
