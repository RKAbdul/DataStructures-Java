package org.uma.ed.demos.set;

import org.uma.ed.datastructures.set.SortedArraySet;
import org.uma.ed.datastructures.set.SortedSet;

import java.util.Comparator;
import java.util.Random;

/**
 * Simple SortedSet demo.
 *
 * @author Pepe Gallardo, Data Structures, Grado en Informática. UMA.
 */
public class SortedArraySetDemo {
  public static void main(String[] args) {
    Random rnd = new Random();

    SortedSet<Integer> set1 = SortedArraySet.empty();
    for (int i = 0; i < 20; i++) {
      set1.insert(rnd.nextInt(100));
    }
    System.out.println(set1);

    Comparator<Integer> reversedComparator = Comparator.reverseOrder();

    SortedSet<Integer> set2 = SortedArraySet.empty(reversedComparator);
    for (int i = 0; i < 20; i++) {
      set2.insert(rnd.nextInt(100));
    }
    System.out.println(set2);

    SortedSet<Integer> set3 = SortedArraySet.<Integer>empty(Comparator.reverseOrder());
    for (int i = 0; i < 20; i++) {
      set3.insert(rnd.nextInt(100));
    }
    System.out.println(set3);

    SortedSet<Integer> set4 = SortedArraySet.of(Comparator.reverseOrder(), 1, 7, 9, 0, 2, 5, 4, 1);
    System.out.println(set4);

    SortedSet<Integer> set5 = SortedArraySet.union(set3, set4);
    System.out.println(set5);

    SortedSet<Integer> set6 = SortedArraySet.intersection(set3, set4);
    System.out.println(set6);

    SortedSet<Integer> set7 = SortedArraySet.of(1, 7, 9, 0, 2, 5, 4, 1);
    System.out.println(set7);

    SortedSet<Integer> set8 = SortedArraySet.copyOf(set7);
    set8.delete(7);
    System.out.println(set8);
  }
}
