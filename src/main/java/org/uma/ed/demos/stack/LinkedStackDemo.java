package org.uma.ed.demos.stack;

import org.uma.ed.datastructures.stack.LinkedStack;
import org.uma.ed.datastructures.stack.Stack;

/**
 * Simple LinkedStack demo.
 *
 * @author Blas Ruiz, Data Structures, Grado en Informática. UMA.
 */
public class LinkedStackDemo {
  public static void main(String[] args) {
    Stack<Integer> stack = LinkedStack.empty();

    System.out.println("*** pushing numbers from 0 to 54");
    for (int i = 0; i < 55; i++) {
      stack.push(i);
    }

    System.out.println(stack);

    System.out.println("*** poping 10 elements and pushing number 1000");
    for (int i = 0; i < 10; i++) {
      stack.pop();
    }
    stack.push(1000);

    System.out.println(stack);

    System.out.println("*** poping all elements");
    while (!stack.isEmpty()) {
      stack.pop();
    }

    System.out.println(stack);

    System.out.println("*** pushing numbers from 0 to 13");
    for (int i = 0; i < 14; i++) {
      stack.push(i);
    }

    System.out.println(stack);
  }
}
