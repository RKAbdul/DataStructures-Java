package org.uma.ed.demos.range;

import java.util.Iterator;
import org.uma.ed.datastructures.range.Range;

/**
 * Arithmetic sequeuences over intergers (a.k.a ranges) tests.
 *
 * @author Pepe Gallardo, Data Structures, Grado en Informática. UMA.
 */
public class RangeDemo {
  public static void main(String[] args) {
    for (Integer x : Range.exclusive(1, 11, 2)) {
      System.out.println(x);
    }
    System.out.println();

    for (Integer x : Range.inclusive(0, 10, 2)) {
      System.out.println(x);
    }
    System.out.println();

    for (Integer x : Range.exclusive(1, 10)) {
      System.out.println(x);
    }
    System.out.println();

    for (Integer x : Range.exclusive(10, 0, 1)) {
      System.out.println(x);
    }
    System.out.println();

    for (Integer x : Range.exclusive(10, 0, -1)) {
      System.out.println(x);
    }
    System.out.println();

    for (Integer x : Range.exclusive(10, 10, -1)) {
      System.out.println(x);
    }
    System.out.println();

    for (Integer x : Range.exclusive(10, 20, -1)) {
      System.out.println(x);
    }
    System.out.println();

    Iterator<Integer> it = Range.inclusive(1, 5, 0).iterator();
    for (int i = 0; i < 10; i++) {
      System.out.println(it.next());
    }
    System.out.println();
  }
}
