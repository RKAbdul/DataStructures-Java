package org.uma.ed.demos.stack;

import org.uma.ed.datastructures.range.Range;
import org.uma.ed.datastructures.stack.ArrayStack;
import org.uma.ed.datastructures.stack.LinkedStack;
import org.uma.ed.datastructures.stack.Stack;

/**
 * Simple Stack demo
 *
 * @author Pepe Gallardo, Data Structures, Grado en Informática. UMA.
 */
public class StackDemo {
  public static void main(String[] args) {
    Stack<Integer> stack1 = LinkedStack.empty();
    stack1.push(1);
    stack1.push(2);
    stack1.push(3);
    stack1.push(4);
    int x = stack1.top();
    System.out.println(x);
    stack1.pop();
    System.out.println(stack1);
    System.out.println();


    Stack<Integer> stack2 = ArrayStack.empty();
    stack2.push(1);
    stack2.push(2);
    stack2.push(3);
    System.out.println(stack2);
    System.out.println();

    Stack<Integer> stack3 = ArrayStack.of(1, 2, 3);
    System.out.println(stack3);
    System.out.println();

    System.out.println(stack1.equals(stack2));
    System.out.println(stack1.equals(stack3));
    System.out.println(stack2.equals(stack3));
    System.out.println();

    Stack<Integer> stack4 = ArrayStack.from(Range.inclusive(0, 10, 2));
    System.out.println(stack4);
  }
}
