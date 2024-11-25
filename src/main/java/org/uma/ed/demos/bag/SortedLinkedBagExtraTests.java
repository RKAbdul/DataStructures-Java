package org.uma.ed.demos.bag;

import org.uma.ed.datastructures.bag.SortedLinkedBag;

/**
 * Simple class to test union, intersection and difference methods of SortedLinkedBag.
 *
 * @author Pepe Gallardo, Data Structures, Grado en Informática. UMA.
 */
public class SortedLinkedBagExtraTests {
  public static void main(String[] args) {
    SortedLinkedBag<Integer> b1 = SortedLinkedBag.empty();
    for (int x : new int[]{1, 1, 2, 1, 3, 7, 5, 5, 5, 10, 11}) {
      b1.insert(x);
    }

    SortedLinkedBag<Integer> b2 = SortedLinkedBag.empty();
    for (int x : new int[]{1, 1, 2, 1, 0, 3, 7, 5, 5, 9, 8, 15, 3}) {
      b2.insert(x);
    }

    System.out.print("b1: ");
    System.out.println(b1);
    System.out.print("b2: ");
    System.out.println(b2);

    System.out.print("union: ");
    SortedLinkedBag<Integer> u = SortedLinkedBag.copyOf(b1);
    u.union(b2);
    System.out.println(u);

    System.out.print("intersection: ");
    SortedLinkedBag<Integer> i = SortedLinkedBag.copyOf(b1);
    i.intersection(b2);
    System.out.println(i);

    System.out.print("difference: ");
    SortedLinkedBag<Integer> b3 = SortedLinkedBag.copyOf(b1);
    b3.difference(b2);
    System.out.println(b3);
  }
}
