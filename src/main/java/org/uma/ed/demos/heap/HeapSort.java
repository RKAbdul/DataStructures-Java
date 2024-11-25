package org.uma.ed.demos.heap;

import java.util.Arrays;
import java.util.Random;
import org.uma.ed.datastructures.heap.BinaryHeap;
import org.uma.ed.datastructures.heap.Heap;
import org.uma.ed.datastructures.heap.MaxiphobicHeap;
import org.uma.ed.datastructures.heap.WBLeftistHeap;

/**
 * Simple demo program for testing heap sort algorithm.
 *
 @author Pablo López, Data Structures, Grado en Informática. UMA.
 */
public class HeapSort {

  public static int[] heapSort(int[] integers, Heap<Integer> heap) {
    heap.clear();

    int size = integers.length;
    for (int x : integers) {
      heap.insert(x);
    }

    int[] sorted = new int[size];
    for (int i = 0; i < size; i++) {
      sorted[i] = heap.minimum();
      heap.deleteMinimum();
    }
    return sorted;
  }

  public static void main(String[] args) {
    tests(BinaryHeap.empty(), "BinaryHeap<Integer>");
    tests(WBLeftistHeap.empty(), "WeightBiasedLeftistHeap<Integer>");
    tests(MaxiphobicHeap.empty(), "MaxiphobicHeap<Integer>");
  }

  public static void print(int[] integers) {
    for (int x : integers) {
      System.out.print(x + " ");
    }
    System.out.println();
  }

  public static void randomHeap(int seed, int heapSize, Heap<Integer> heap) {
    Random rnd = new Random(seed);

    heap.clear();
    for (int i = 0; i < heapSize; i++) {
      heap.insert(rnd.nextInt());
    }
  }

  public static boolean testHeapSort(int seed, int size, Heap<Integer> heap) {
    Random rnd = new Random(seed);

    int[] integers = new int[size];
    for (int i = 0; i < size; i++) {
      integers[i] = rnd.nextInt();
    }

    int[] sorted = heapSort(integers, heap);

    if (!heap.isEmpty()) {
      return false;
    }
    Arrays.sort(integers);
    return Arrays.equals(integers, sorted);
  }

  static void tests(Heap<Integer> heap, String implementation) {
    final int NUMBER_OF_TESTS = 10000;
    final int NUMBER_OF_ELEMENTS = 1000;

    long t0 = System.currentTimeMillis();
    for (int seed = 0; seed < NUMBER_OF_TESTS; seed++) {
      if (!testHeapSort(seed, NUMBER_OF_ELEMENTS, heap)) {
        System.out.println("Error on test");
        System.exit(1);
      }
    }
    System.out.println("Use " + implementation);
    System.out.println("-> " + NUMBER_OF_TESTS + " tests passed OK");
    long t1 = System.currentTimeMillis();
    System.out.printf("-> Took %f seconds\n", (t1 - t0) / 1e3 / NUMBER_OF_TESTS);
  }
}