package org.uma.ed.demos.stack;

import org.uma.ed.datastructures.stack.ArrayStack;
import org.uma.ed.datastructures.stack.Stack;

/**
 * Reverses a message using a stack.
 *
 * @author Pepe Gallardo, Data Structures, Grado en Informática. UMA.
 */
public class Reverser {

  public static void main(String[] args) {

    String message = "hello world!";
    Stack<Character> stack = ArrayStack.empty();

    for (int i = 0; i < message.length(); i++) {
      stack.push(message.charAt(i));
    }

    System.out.println(stack);

    while (!stack.isEmpty()) {
      System.out.print(stack.top());
      stack.pop();
    }
  }
}
