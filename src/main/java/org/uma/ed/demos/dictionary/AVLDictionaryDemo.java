package org.uma.ed.demos.dictionary;

import org.uma.ed.datastructures.dictionary.AVLDictionary;
import org.uma.ed.datastructures.dictionary.Dictionary.Entry;
import org.uma.ed.datastructures.dictionary.SortedDictionary;

/**
 * Simple class to test AVLDictionary implementations.
 *
 * @author Pepe Gallardo, Data Structures, Grado en Informática. UMA.
 */
public class AVLDictionaryDemo {
  public static void main(String[] args) {
    SortedDictionary<String, Integer> sortedDictionary =
        AVLDictionary.of(Entry.of("five", 5), Entry.of("three", 3), Entry.of("two", 2), Entry.of("one", 1),
            Entry.of("zero", 0), Entry.of("four", 4));

    System.out.println("Sorted dictionary: " + sortedDictionary);
    System.out.println("sortedDictionary.size() = " + sortedDictionary.size());
    System.out.println("sortedDictionary.isDefinedAt(\"three\") = " + sortedDictionary.isDefinedAt("three"));
    System.out.println("sortedDictionary.isDefinedAt(\"six\") = " + sortedDictionary.isDefinedAt("six"));
    System.out.println("sortedDictionary.valueOf(\"three\") = " + sortedDictionary.valueOf("three"));
    System.out.println("Deleting \"three\"");
    sortedDictionary.delete("three");
    System.out.println("sortedDictionary = " + sortedDictionary);
    System.out.println("sortedDictionary.minimum() = " + sortedDictionary.minimum());
    System.out.println("sortedDictionary.maximum() = " + sortedDictionary.maximum());
    System.out.println("iterating sortedDictionary:");
    for (var entry : sortedDictionary) {
      System.out.println(entry);
    }
  }
}
