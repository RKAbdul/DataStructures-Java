package org.uma.ed.demos.searchTree;

import org.uma.ed.datastructures.searchtree.BST;
import org.uma.ed.datastructures.searchtree.SearchTree;

/**
 * Simple SearchTree demo.
 *
 * @author Pepe Gallardo, Data Structures, Grado en Informática. UMA.
 */
public class SearchTreeDemo {

  public static void main(String[] args) {
    SearchTree<Integer> t = BST.empty();

    int[] array = new int[]{8, 5, 7, 1, 9, 6};

    for (int x : array) {
      t.insert(x);
    }

    System.out.println("Minim " + t.minimum());
    System.out.println("Maxim " + t.maximum());

    t.deleteMinimum();

    System.out.println("Minim " + t.minimum());
    System.out.println("Maxim " + t.maximum());

    t.deleteMinimum();

    System.out.println("Minim " + t.minimum());
    System.out.println("Maxim " + t.maximum());

    t.deleteMaximum();

    System.out.println("Minim " + t.minimum());
    System.out.println("Maxim " + t.maximum());

    t.deleteMinimum();

    System.out.println("Minim " + t.minimum());
    System.out.println("Maxim " + t.maximum());

    t.deleteMaximum();

    System.out.println("Minim " + t.minimum());
    System.out.println("Maxim " + t.maximum());
  }
}
