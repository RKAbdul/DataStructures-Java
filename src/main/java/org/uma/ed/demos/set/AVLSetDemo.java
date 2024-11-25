package org.uma.ed.demos.set;

import org.uma.ed.datastructures.range.Range;
import org.uma.ed.datastructures.set.AVLSet;
import org.uma.ed.datastructures.set.SortedSet;

import java.util.Random;

/**
 * Simple AVLSet demo.
 *
 * @author Pepe Gallardo, Data Structures, Grado en Informática. UMA.
 */
public class AVLSetDemo {
  public static void main(String[] args) {

    SortedSet<Integer> sortedSet1 = AVLSet.of(5, 3, 2, 1, 2, 0, 4, 3, 5, 1, 8);
    System.out.println("sortedSet1 = " + sortedSet1);
    System.out.println("sortedSet1.size() = " + sortedSet1.size());
    System.out.println("sortedSet1.contains(3) = " + sortedSet1.contains(3));
    System.out.println("sortedSet1.contains(6) = " + sortedSet1.contains(6));
    System.out.println("Deleting 3 and 5");
    sortedSet1.delete(3);
    sortedSet1.delete(5);
    System.out.println("sortedSet1 = " + sortedSet1);
    System.out.println("sortedSet1.minimum() = " + sortedSet1.minimum());
    System.out.println("sortedSet1.maximum() = " + sortedSet1.maximum());
    System.out.println("iterating sortedSet1");
    for (Integer x : sortedSet1) {
      System.out.println(x);
    }
    System.out.println("Clearing sortedSet1");
    sortedSet1.clear();
    System.out.println("sortedSet1 = " + sortedSet1);
    System.out.println();

    SortedSet<Integer> sortedSet2 = AVLSet.from(Range.inclusive(0, 5));
    System.out.println("sortedSet2 = " + sortedSet2);

    Random rnd = new Random();
    SortedSet<Integer> sortedSet3 = AVLSet.empty();
    for (int i = 0; i < 20; i++) {
      sortedSet3.insert(rnd.nextInt(100));
    }
    System.out.println("sortedSet3 = " + sortedSet3);
  }
}
