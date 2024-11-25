package org.uma.ed.demos.tree;

import org.uma.ed.datastructures.tree.Tree;
import org.uma.ed.datastructures.tree.Tree.Node;

/**
 * Class to demonstrate usage of Tree methods.
 *
 * @author Pepe Gallardo, Data Structures, Grado en Informática. UMA.
 */
public class TreeDemo {
  public static void main(String[] args) {
    Node<Integer> tree = Node.of(1, Node.of(2, Node.of(3)), Node.of(4), Node.of(5, Node.of(6), Node.of(7), Node.of(8)));

    System.out.println("The height of the tree is: " + Tree.height(tree)); // 3
    System.out.println("The size of the tree is: " + Tree.size(tree)); // 8
    System.out.println("The sum of the tree is: " + Tree.sum(tree)); // 36
    System.out.println("The maximum element in the tree is: " + Tree.maximum(tree, Integer::compareTo)); // 8
    System.out.println("The element 7 is " + Tree.count(tree, 7) + " times in the tree"); // 1
    System.out.println("The leaves of the tree are: " + Tree.leaves(tree)); // ArrayList(3, 4, 6, 7, 8)
    System.out.println("The preorder traversal of the tree is: " + Tree.preorder(tree));
    // ArrayList(1, 2, 3, 4, 5, 6, 7, 8)
    System.out.println("The postorder traversal of the tree is: " + Tree.postorder(tree));
    // ArrayList(3, 2, 4, 6, 7, 8, 5, 1)
    System.out.println("The breadth-first traversal of the tree is: " + Tree.breadthFirst(tree));
    // ArrayList(1, 2, 4, 5, 3, 6, 7, 8)
  }
}
