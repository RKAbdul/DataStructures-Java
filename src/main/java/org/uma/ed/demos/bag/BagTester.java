/**
 * @author Pablo López, Data Structures, Grado en Informática. UMA.
 * <p>
 * A simple test driver for the Bag ADT.
 */

package org.uma.ed.demos.bag;

import org.uma.ed.datastructures.bag.Bag;
import org.uma.ed.datastructures.bag.SortedLinkedBag;

public class BagTester {

  public static void main(String[] args) {

    // choose Bag ADT implementation to test
    Bag<Character> bag = SortedLinkedBag.empty();
    // Bag<Character> bag = AVLBag.empty();
    // Bag<Character> bag = hashBag.empty();
    // Bag<Character> bag = ArrayBag.empty();

    String cervantes = "En un lugar de la Mancha, de cuyo nombre no quiero acordarme, " + "no ha " + "mucho" + " " +
        "tiempo que vivía un hidalgo de los de lanza en astillero, " + "adarga " + "antigua, rocín flaco y galgo " +
        "corredor. Una olla de algo más " + "vaca " + "que " + "carnero, salpicón las más noches, duelos y quebrantos" +
        " " + "los sábados, " + "lentejas " + "los viernes, algún palomino de añadidura los " + "domingos, consumían "
        + "las tres " + "partes de su hacienda.";

    String dickens = "IT WAS the best of times, it was the worst of times," + "it was " + "the " + "age " + "of " +
        "wisdom, it was the age of foolishness," + "it was the epoch of " + "belief, " + "it was the epoch of " +
        "incredulity," + "it was the season of Light, " + "it " + "was " + "the " + "season of Darkness," + "it was " +
        "the spring of hope, it was " + "the winter " + "of " + "despair," + "we had everything before us, we had " + "nothing before us," + "we " + "were all going direct to Heaven, we were " + "all going direct the other way" + "- " + "in short, the period was so far " + "like the present period, that some of" + " its" + " noisiest authorities " + "insisted on its being received," + "for good or for " + "evil, in the " + "superlative degree of comparison only.";

    // choose text for the test
    String text;
    text = cervantes;
    //text = dickens;

    System.out.println(bag.isEmpty());

    // 1. feed the bag with the text
    for (int i = 0; i < text.length(); i++) {
      bag.insert(text.charAt(i));
    }
    System.out.println(bag);
    printVowels(bag);

    // 2. remove some characters
    String toBeDeleted = "e Muy aaa cuccu monk java ghci duEee";
    for (int i = 0; i < toBeDeleted.length(); i++) {
      bag.delete(toBeDeleted.charAt(i));
    }
    System.out.println(bag);
    printVowels(bag);

    // 3. remove non letters left
    for (int i = 0; i < text.length(); i++) {
      char c = text.charAt(i);
      if (!Character.isLetter(c)) {
        removeAll(bag, c);
      }
    }
    System.out.println(bag);
    printVowels(bag);

    // 4. remove all occurrences of some letters
    String toBeNuked = "zywvu";
    for (int i = 0; i < toBeNuked.length(); i++) {
      removeAll(bag, toBeNuked.charAt(i));
    }
    System.out.println(bag);
    printVowels(bag);

    // 5. remove vowels left
    removeVowels(bag);
    System.out.println(bag);
    printVowels(bag);

    System.out.println(bag.isEmpty());
  }

  public static void printVowels(Bag<Character> bag) {
    final String vowels = "aeiou";
    for (int i = 0; i < vowels.length(); i++) {
      char vowel = vowels.charAt(i);
      System.out.println(vowel + " occurs " + bag.occurrences(vowel) + " times");
    }
  }

  public static <T extends Comparable<? super T>> void removeAll(Bag<T> bag, T c) {
    while (bag.occurrences(c) > 0) {
      bag.delete(c);
    }
  }

  public static void removeVowels(Bag<Character> b) {
    final String vowels = "aeiou";
    for (int i = 0; i < vowels.length(); i++) {
      char vowel = vowels.charAt(i);
      removeAll(b, vowel);
      removeAll(b, Character.toUpperCase(vowel));
    }
  }
}
