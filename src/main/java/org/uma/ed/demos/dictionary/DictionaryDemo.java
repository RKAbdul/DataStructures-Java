package org.uma.ed.demos.dictionary;

import org.uma.ed.datastructures.dictionary.Dictionary.Entry;
import org.uma.ed.datastructures.dictionary.SortedDictionary;
import org.uma.ed.datastructures.dictionary.SortedLinkedDictionary;

/**
 * Simple class to test dictionary implementations.
 *
 * @author Pepe Gallardo, Data Structures, Grado en Informática. UMA.
 */
public class DictionaryDemo {
  public static void main(String[] args) {
    SortedDictionary<Integer, String> sortedDictionary = SortedLinkedDictionary.of(Entry.of(5, "ffive"), Entry.of(3,
        "three"), Entry.of(2, "two"), Entry.of(1, "one"), Entry.of(2, "two"), Entry.of(0, "zero"), Entry.of(4,
        "four"), Entry.of(3, "three"), Entry.of(5, "five"), Entry.of(1, "one"));

    System.out.println(sortedDictionary);
    String string2 = sortedDictionary.valueOf(3);
    System.out.println(string2);
    System.out.println();
  }
}
