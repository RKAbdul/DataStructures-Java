package org.uma.ed.demos.set;

import java.util.Comparator;
import java.util.Random;
import org.uma.ed.datastructures.set.SortedLinkedSet;
import org.uma.ed.datastructures.set.SortedSet;

/**
 * Simple SortedSet demo.
 *
 * @author Pepe Gallardo, Data Structures, Grado en Informática. UMA.
 */
public class SortedLinkedSetDemo {
  public static void main(String[] args) {
    Random rnd = new Random();

    SortedSet<Integer> set1 = SortedLinkedSet.empty();
    for (int i = 0; i < 20; i++) {
      set1.insert(rnd.nextInt(100));
    }
    System.out.println(set1);

    Comparator<Integer> reversedComparator = Comparator.reverseOrder();

    SortedSet<Integer> set2 = SortedLinkedSet.empty(reversedComparator);
    for (int i = 0; i < 20; i++) {
      set2.insert(rnd.nextInt(100));
    }
    System.out.println(set2);

    SortedSet<Integer> set3 = SortedLinkedSet.<Integer>empty(Comparator.reverseOrder());
    for (int i = 0; i < 20; i++) {
      set3.insert(rnd.nextInt(100));
    }
    System.out.println(set3);

    SortedSet<Integer> set4 = SortedLinkedSet.of(Comparator.reverseOrder(), 1, 7, 9, 0, 2, 5, 4, 1);
    System.out.println(set4);

    SortedSet<Integer> set5 = SortedLinkedSet.union(set3, set4);
    System.out.println(set5);

    SortedSet<Integer> set6 = SortedLinkedSet.intersection(set3, set4);
    System.out.println(set6);

    SortedSet<Integer> set7 = SortedLinkedSet.of(1, 7, 9, 0, 2, 5, 4, 1);
    System.out.println(set7);

    SortedSet<Integer> set8 = SortedLinkedSet.copyOf(set7);
    set8.delete(7);
    System.out.println(set8);
  }
}
